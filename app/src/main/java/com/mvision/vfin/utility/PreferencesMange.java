package com.mvision.vfin.utility;

import com.mvision.vfin.component.configkey.GetKey;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class PreferencesMange {
    private static PreferencesMange instance = null;

    public static PreferencesMange getInstance() {
        if (instance == null)
            instance = new PreferencesMange();

        return instance;
    }

    public String getMemberID() {
        return MCryp.getInstance().decrypt(GetKey.getInstance().getKeyAes(GetKey.getInstance().getSignatures()),
                GetKey.getInstance().getIvKey(GetKey.getInstance().getSignatures())
                , Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "member_id"));
    }

    public void setMemberID(String member_id) {
        Utility.savePreferences(Contextor.getInstance().getContext(), "member_id", MCryp
                .getInstance().encrypt(GetKey.getInstance().getKeyAes(GetKey.getInstance().getSignatures()),
                        GetKey.getInstance().getIvKey(GetKey.getInstance().getSignatures()),
                        member_id));
    }

    public void removeMemberID() {
        Utility.removeSavedPreferences(Contextor.getInstance().getContext(), "member_id");
    }


    public String getTokenSession() {
        return (Utility.loadSavedPreferences(Contextor.getInstance().getContext(),
                "tokenSession").length()==0) ? " " :
                MCryp.getInstance().decrypt(GetKey.getInstance().getKeyAes(GetKey.getInstance().getSignatures()),
                        GetKey.getInstance().getIvKey(GetKey.getInstance().getSignatures())
                        , Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "tokenSession"));
    }

    public void setTokenSession(String tokenSession) {
        Utility.savePreferences(Contextor.getInstance().getContext(), "tokenSession", MCryp
                .getInstance().encrypt(GetKey.getInstance().getKeyAes(GetKey.getInstance().getSignatures()),
                        GetKey.getInstance().getIvKey(GetKey.getInstance().getSignatures()),
                        tokenSession));
    }

    public void setTokenFCM(String deviceToken) {
        Utility.savePreferences(Contextor.getInstance().getContext(), "tokenFCM", MCryp
                .getInstance().encrypt(GetKey.getInstance().getKeyAes(GetKey.getInstance().getSignatures()),
                        GetKey.getInstance().getIvKey(GetKey.getInstance().getSignatures()),
                        deviceToken));
    }

    public String getTokenFCM() {
        return MCryp.getInstance().decrypt(GetKey.getInstance().getKeyAes(GetKey.getInstance().getSignatures()),
                GetKey.getInstance().getIvKey(GetKey.getInstance().getSignatures())
                , Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "tokenFCM"));
    }


    public void removeTokenSession() {
        Utility.removeSavedPreferences(Contextor.getInstance().getContext(), "tokenSession");
    }

    public void setLanguage(String la) {
        Utility.savePreferences(Contextor.getInstance().getContext(), "language", la);
    }

    public String getLanguage() {
        return Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "language");
    }
}
