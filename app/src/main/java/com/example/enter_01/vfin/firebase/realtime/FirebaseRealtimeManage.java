package com.example.enter_01.vfin.firebase.realtime;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hytexts_Android on 15/6/2560.
 */

public class FirebaseRealtimeManage {

    private FirebaseDatabase mFirebaseInstance;
    private CallBackFirebaseRealtime callBackFirebaseRealtime;




    public void setUpRealTimeDatabase(String pathBase, CallBackFirebaseRealtime callBackFirebaseRealtime) {
        this.callBackFirebaseRealtime = callBackFirebaseRealtime;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseInstance.getReference(pathBase).addChildEventListener(childEventListener);
    }

    public void setUpRealTimeDatabaseOrderBy(String pathBase, String orderBy, CallBackFirebaseRealtime callBackFirebaseRealtime) {
        this.callBackFirebaseRealtime = callBackFirebaseRealtime;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseInstance.getReference(pathBase).orderByChild(orderBy).addChildEventListener(childEventListener);
    }




    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
           /* try {
                if (!dataSnapshot.getKey().equals(Utility.getDeviceID(Contextor.getInstance().getContext())))
                    callBackFirebaseRealtime.onChildAdded();
            } catch (Exception e) {
            }*/
            callBackFirebaseRealtime.onChildAdded(dataSnapshot);

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            callBackFirebaseRealtime.onChildChanged(dataSnapshot);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
       /*     try {
                if (dataSnapshot.getKey().equals(Utility.getDeviceID(Contextor.getInstance().getContext())))
                    callBackFirebaseRealtime.onChildRemoved();
            } catch (Exception e) {
            }*/
            callBackFirebaseRealtime.onChildRemoved(dataSnapshot);
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            callBackFirebaseRealtime.onChildMoved(dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            callBackFirebaseRealtime.onCancelled();

        }
    };


    public void stopFileBaseRealtime(){
        if (childEventListener != null) {
            mFirebaseInstance.getReference().removeEventListener(childEventListener);
        }
    }



    public interface CallBackFirebaseRealtime {
        void onChildAdded(DataSnapshot dataSnapshot);

        void onChildChanged(DataSnapshot dataSnapshot);

        void onChildRemoved(DataSnapshot dataSnapshot);

        void onChildMoved(DataSnapshot dataSnapshot);

        void onCancelled();
    }


}
