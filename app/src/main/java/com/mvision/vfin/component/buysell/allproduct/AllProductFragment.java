package com.mvision.vfin.component.buysell.allproduct;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.utility.Utility;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class AllProductFragment extends BaseFragment implements AllProductContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private AllProductPresenter presenter;

    public static AllProductFragment newInstance() {
        AllProductFragment fragment = new AllProductFragment();
        return fragment;
    }


    @Override
    public void showMessageFail(String msg) {
        Utility.ShowMsg(getActivity(),msg);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        try {
            progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
        }
    }


    @Override
    protected void initializePresenter() {
        presenter = new AllProductPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getProductAll();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_all_product;
    }

    private ProductAdapter productAdapter;


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public static String clickProductDetail = "clickProductDetail";

    @Override
    public void setUpViewAllProduct(ArrayList<ProductRealTimeModel> allProduct) {
        try {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            if (productAdapter!=null){
                productAdapter.addData(allProduct);
            }else{
                productAdapter = new ProductAdapter(allProduct, new ProductAdapter.CallBackClick() {
                    @Override
                    public void clickItemProduct(ProductRealTimeModel productModel) {
                /*    Bundle bundle = new Bundle();
                    bundle.putParcelable(clickProductDetail,Parcels.wrap(productModel));
                    startActivityFromFragment(ProductDetailActivity.class,bundle);*/
                    }

                    @Override
                    public void clickItemProductBuy(ProductRealTimeModel productModel, long price, String
                            memberIdOwner) {
                        presenter.buyProduct(productModel);
                    }
                });
                recyclerView.setAdapter(productAdapter);
            }

        } catch (Exception e) {
        }


    }

    @Override
    public void removeItem(int position) {
        productAdapter.removeData(position);
    }

    @Override
    public void changeItem(int position) {
        productAdapter.dataChange(position);
    }

    @Override
    public void changeItemFail(int position) {
        productAdapter.dataChangeFail(position);
    }
}
