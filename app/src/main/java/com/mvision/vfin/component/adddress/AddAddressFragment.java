package com.mvision.vfin.component.adddress;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.adddress.model.AddressModel;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class AddAddressFragment extends BaseFragment implements AddAddressContract.View{


    private AddAddressPresenter presenter ;

    public static AddAddressFragment newInstance() {
        AddAddressFragment fragment = new AddAddressFragment();
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializePresenter() {
        presenter = new AddAddressPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_add_address;
    }

    @Override
    public void showProvince() {

    }

    @Override
    public void showDistrict() {

    }

    @Override
    public void showSubDistrict() {
        AddressModel addressModel = new AddressModel();
        addressModel.setDetails("");
        addressModel.setDistrict("");
        addressModel.setHouseNo("");
        addressModel.setName("");
        addressModel.setProvince("");
        addressModel.setReceiverName("");
        addressModel.setSubDistrict("");
        presenter.sentAddress(addressModel);
    }
}
