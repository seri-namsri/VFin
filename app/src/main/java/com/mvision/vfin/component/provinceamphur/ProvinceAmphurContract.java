package com.mvision.vfin.component.provinceamphur;

import com.mvision.vfin.api.response.AmphurResponseModel;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public interface ProvinceAmphurContract {
    interface View extends BaseView {
        void setProvince(ProvinceResponseModel provinceResponseModel);
        void sentProvince(Province province);
        void setTitleBar(String title);
        void setAmphur(AmphurResponseModel amphurResponseModel);
        void sentAmphur(Amphur amphur);
    }

    interface Presenter {
        void getProvinceOrAmphur();
        void clickProvince(Province province);
        void clickAmphur(Amphur amphur);
    }
}
