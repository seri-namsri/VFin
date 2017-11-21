package com.example.enter_01.vfin.firebase.fcm;

/**
 * Created by kosian on 6/20/2016.
 */

import com.example.enter_01.vfin.utility.Utility;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private String device_token;
    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        device_token = token;
        Utility.savePreferences(getApplicationContext(),"tokenFCM",token);
    }



}
