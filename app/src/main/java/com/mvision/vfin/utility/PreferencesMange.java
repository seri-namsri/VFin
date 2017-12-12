package com.mvision.vfin.utility;

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

    public String getMemberID(){
        Log.e("memberCode",Utility.loadSavedPreferences(Contextor.getInstance().getContext(),
                "member_id"));
        return Utility.loadSavedPreferences(Contextor.getInstance().getContext(),"member_id");
    }

    public void removeMemberID(){
         Utility.removeSavedPreferences(Contextor.getInstance().getContext(),"member_id");
    }


    public String getTokenSession(){
        Log.e("getTokenSession",Utility.loadSavedPreferences(Contextor.getInstance().getContext(),"tokenSession"));
        return Utility.loadSavedPreferences(Contextor.getInstance().getContext(),"tokenSession");
    }

    public void removeTokenSession(){
         Utility.removeSavedPreferences(Contextor.getInstance().getContext(),"tokenSession");
    }
}
