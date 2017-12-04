package com.mvision.vfin.component.main;

import com.google.gson.Gson;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.profile.ProfileManager;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.View view ;

    public MainPresenter (MainContract.View view ){
        this.view = view;
    }

    @Override
    public void showView() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("walletAndAbility/" + PreferencesMange
                .getInstance().getMemberID());
        Log.e("PreferencesMange",PreferencesMange.getInstance().getMemberID());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Gson gson = new Gson();
                ModelCoinAndBit modelCoinAndBit = gson.fromJson(gson.toJson(dataSnapshot.getValue
                        ()),ModelCoinAndBit.class);
                view.setUpView(modelCoinAndBit);
             //   callBackData.onSuccess(dataSnapshot.getValue(ProductRealTimeModel.class));
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
             //   Log.w("getAllProductFormRealtime", "Failed to read value.", error.toException());
            }
        });



    }
}
