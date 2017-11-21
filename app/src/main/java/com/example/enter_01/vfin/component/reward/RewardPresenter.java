package com.example.enter_01.vfin.component.reward;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.reward.pojo.CategoryModel;
import com.example.enter_01.vfin.component.reward.pojo.ModelRewardMerge;
import com.example.enter_01.vfin.component.reward.pojo.RewardModel;
import com.example.enter_01.vfin.firebase.Firestore.Query;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class RewardPresenter extends Presenter<RewardContract.View> implements RewardContract.Presenter{

    private RewardContract.View view ;

    public RewardPresenter(RewardContract.View view){
        this.view = view ;
    }

    @Override
    public void getReward() {
        view.showLoading();



         RewardManage.getInstance().getCagegory(new Query.CallBackData() {
             @Override
             public <T> void onSuccess(T t) {

             }

             @Override
             public <T> void onSuccessAll(ArrayList<T> tArrayList) {




                 final ArrayList<CategoryModel>arrayList = (ArrayList<CategoryModel>) tArrayList;
                 final ArrayList<ModelRewardMerge>modelRewardMerges = new ArrayList<>();
                 final int[] i = {0};
                 for (final CategoryModel categoryModel : arrayList){

                     RewardManage.getInstance().getRewardByCat(new Query.CallBackData() {
                         @Override
                         public <T> void onSuccess(T t) {

                         }

                         @Override
                         public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                             ArrayList<RewardModel> rewardModels = (ArrayList<RewardModel>) tArrayList;
                             final ModelRewardMerge rewardMerge = new ModelRewardMerge();
                             rewardMerge.catId = rewardModels.get(0).cat_id;
                             rewardMerge.catName = rewardModels.get(0).cat_name;
                             rewardMerge.rewardModels = rewardModels;
                             modelRewardMerges.add(rewardMerge);

                             if (i[0]==arrayList.size()-1){
                                 view.hideLoading();
                                 view.setUpViewReward(modelRewardMerges);
                             }
                             i[0]++ ;


                         }

                         @Override
                         public void onFail(String error) {

                         }
                     },categoryModel.getCat_id());

                 }
             }

             @Override
             public void onFail(String error) {

             }
         });
    }
}
