package com.example.enter_01.vfin.firebase.Firestore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.main.model.ModelMain;
import com.example.enter_01.vfin.utility.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class Update {
    private static Update instance = null;

    public static Update getInstance() {
        if (instance == null)
            instance = new Update();

        return instance;
    }


    public <T> void updateDocument(DocumentReference documentReferenc, ProductModel
            productModel, final CallBackData callBackData) {

        documentReferenc
                .set(productModel
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callBackData.onSuccessAll(null);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callBackData.onFail(e.getMessage().toString());
                    }
                });


    }

    public <T> void updateDocument(Task<Void> documentReferenc
            , final CallBackData callBackData) {
        documentReferenc.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callBackData.onSuccess(task.getResult());
                } else {
                    callBackData.onFail(task.getException().getMessage());
                }
            }
        });

    }


    public <T> void readDataCollectionRealTime(com.google.firebase.firestore.Query
                                                       collectionReference,
                                               final T t, final CallBackData
                                                       callBackData) {
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("addSnapshotListener", "Listen failed.", e);
                    callBackData.onFail(e.toString());
                    return;
                }
                ArrayList<Object> arrayList = new ArrayList<>();
                for (DocumentChange dc : value.getDocumentChanges()) {
                    arrayList.add(dc.getDocument().toObject(t.getClass()));
                }
                callBackData.onSuccessAll(arrayList);


            }
        });
    }

    public <T> void readDataDocument(DocumentReference documentReference, final T t, final CallBackData callBackData) {
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    callBackData.onFail(e + "");
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


    public interface CallBackData {
        <T> void onSuccess(T t);

        <T> void onSuccessAll(ArrayList<T> tArrayList);

        void onFail(String error);
    }


}
