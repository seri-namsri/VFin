package com.example.enter_01.vfin.component.profile;

import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Log;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileManager {

    private static ProfileManager instance = null;
    private static FirebaseFirestore db ;
    public static ProfileManager getInstance() {
        if (instance == null)
            instance = new ProfileManager();
        return instance;
    }

    public void getMember(String memberId,final Query.CallBackData callBackData){
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataDocument( db.document("member/"+memberId),new Member(), callBackData);
    }

    public void getMemberNorealTime(String memberId,final Query.CallBackData callBackData){
        db = FirebaseFirestore.getInstance();
        try {
            Query.getInstance().readDataDocumentNorealTime( db.document("member/"+memberId),new Member(),
                    callBackData);
        }catch (Exception e){}

    }



}
