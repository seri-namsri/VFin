package com.example.enter_01.vfin.firebase.Firestore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.enter_01.vfin.utility.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class Insert {
    private static Insert instance = null;

    public static Insert getInstance() {
        if (instance == null)
            instance = new Insert();

        return instance;
    }



    public <T>  void insertCollection(CollectionReference collectionReference, final T t, final
    CallBackData callBackData){
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Object>arrayList = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        arrayList.add(document.toObject(t.getClass()));
                    }
                    callBackData.onSuccessAll(arrayList);
                } else {
                    callBackData.onFail(task.getException().toString());
                }
            }
        });
    }

    public <T>  void readDataCollection(com.google.firebase.firestore.Query collectionReference, final T t, final CallBackData
            callBackData){
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Object>arrayList = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        arrayList.add(document.toObject(t.getClass()));
                    }
                    callBackData.onSuccessAll(arrayList);
                } else {
                    callBackData.onFail(task.getException().toString());
                }
            }
        });
    }

    public <T>  void readDataCollectionRealTime(com.google.firebase.firestore.Query
                                                     collectionReference,
                                         final T t, final CallBackData
            callBackData){
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("addSnapshotListener", "Listen failed.", e);
                    callBackData.onFail(e.toString());
                    return;
                }
                ArrayList<Object>arrayList = new ArrayList<>();
                for (DocumentChange dc : value.getDocumentChanges()) {
                    arrayList.add(dc.getDocument().toObject(t.getClass()));
                }
                callBackData.onSuccessAll(arrayList);


            }
        });
    }

   public  <T> void readDataDocument(DocumentReference documentReference, final T t, final CallBackData callBackData){
        documentReference .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    callBackData.onFail(e+"");
                    Log.w("addSnapshotListener", "Listen failed.", e);
                    return;
                }
                if (documentSnapshot.exists()) {
                    try {
                        callBackData.onSuccess(documentSnapshot.toObject(t.getClass()));

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        callBackData.onFail(e1.getMessage());
                    }

                }
            }

        });

    }



    public interface CallBackData{
        <T>  void onSuccess(T t);
        <T>  void onSuccessAll(ArrayList<T> tArrayList);
        void onFail(String error);
    }




}
