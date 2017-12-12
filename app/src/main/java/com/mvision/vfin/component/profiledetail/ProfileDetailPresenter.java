package com.mvision.vfin.component.profiledetail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileDetailPresenter extends Presenter<ProfileDetailContract.View> implements ProfileDetailContract.Presenter {

    private ProfileDetailContract.View view;
    private int requestCodeName = 1;
    private int requestCodeEmail = 2;
    private int requestCodeBirthDay = 3;
    private int requestCodeUploadProfile = 7;
    private int requestCodeAddDress = 8;
    private int requestCodeEditAddress = 9;
    private int requestCodeChangeAddress = 10;
    private int requestCodeChangeGender = 11;
    private int requestCodeChangePersonalId = 12;

    public ProfileDetailPresenter(ProfileDetailContract.View view) {
        this.view = view;
    }

    private MemberResponseModel memberResponseModel;

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        memberResponseModel = Parcels.unwrap(extras.getParcelable("profile"));
    }

    @Override
    public void getProfile() {
        try {
            view.setUpViewProfile(memberResponseModel);
            view.setUpViewAddress(memberResponseModel.result.address);

        } catch (NullPointerException e) {
        }
    }

    @Override
    public void clickEditProfile(int id) {
        int requestCode = 0;
        Bundle bundle = new Bundle();
        bundle.putParcelable("profile", Parcels.wrap(memberResponseModel));
        switch (id) {
            case R.id.linearLayoutName:
                requestCode = requestCodeName;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_name);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutEmail:
                requestCode = requestCodeEmail;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_email);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutBirdDay:
                requestCode = requestCodeBirthDay;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_birtday);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutPassword:
                requestCode = 0;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_password);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutGender:
                requestCode = requestCodeChangeGender;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_gender);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.linearLayoutIdentiFication:
                requestCode = requestCodeChangePersonalId;
                bundle.putInt("layoutEditProfile", R.layout.layout_change_iden);
                view.startEditProfile(bundle, requestCode);
                break;
            case R.id.imageViewProfile:
                upLoadImage();
                break;
            case R.id.buttonAddressAdd:
                view.startChangeAddress(requestCodeChangeAddress);
                break;
        }

    }
    int permsRequestCode = 200;
    private void upLoadImage() {
        if (shouldAskPermission()) {
            String[] perms = {"android.permission.READ_EXTERNAL_STORAGE"};

                //  verifyStoragePermissions()
                view.onRequestPermissions(perms, permsRequestCode);



        } else {
            view.showGallery();
        }
    }

    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);

    }

    @Override
    public void changeData(int id, Intent data) {
        try {
            MemberUpdate memberUpdate = Parcels.unwrap(data.getExtras().getParcelable("memberUpdate"));
            if (id == requestCodeName) {
                memberResponseModel.result.firstName = memberUpdate.getFirstName();
                memberResponseModel.result.lastName = memberUpdate.getLastName();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeEmail) {
                memberResponseModel.result.email = memberUpdate.getEmail();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeBirthDay) {
                memberResponseModel.result.dateOfBirth = memberUpdate.getDateOfBirth();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeChangeGender) {
                memberResponseModel.result.gender = memberUpdate.getGender();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeChangePersonalId) {
                memberResponseModel.result.personalId = memberUpdate.getPersonalId();
                view.setUpViewProfile(memberResponseModel);
            } else if (id == requestCodeAddDress || id == requestCodeEditAddress || id == requestCodeChangeAddress) {
                AddressModel addressModel = Parcels.unwrap(data.getExtras().getParcelable("addressModel"));
                view.setUpViewAddress(addressModel);
            }

        } catch (Exception e) {
            try {
                if (id == requestCodeUploadProfile) {

                    //  InputStream is = Contextor.getInstance().getContext().getContentResolver()
                    //      .openInputStream(data.getData());
                    resizeImage(data, new CallBackImage() {
                        @Override
                        public void data(byte[] image) {
                            upDateImage(image);
                        }
                    });


                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    private interface CallBackImage {
        void data(byte[] image);
    }

    private void resizeImage(final Intent data, final CallBackImage callBackImage) {


        new Thread(new Runnable() {
            public void run() {
                try {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = Contextor.getInstance().getContext().getContentResolver().query
                            (selectedImage,
                                    filePathColumn, null, null, null);

                    if (cursor == null || cursor.getCount() < 1) {
                        return; // no cursor or no record. DO YOUR ERROR HANDLING
                    }

                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                    if (columnIndex < 0) // no column index
                        return; // DO YOUR ERROR HANDLING

                    String picturePath = cursor.getString(columnIndex);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inJustDecodeBounds = false;
                    opts.inPreferredConfig = Bitmap.Config.RGB_565;
                    opts.inDither = true;
                    cursor.close(); // close cursor
                    Bitmap photo = BitmapFactory.decodeFile(picturePath, opts);
                    //  Matrix matrix = new Matrix();
                    //    matrix.postRotate(getImageOrientation(picturePath));


                    Bitmap rotatedBitmap = photo;

                    final int maxSize = 260;
                    int outWidth;
                    int outHeight;
                    int inWidth = rotatedBitmap.getWidth();
                    int inHeight = rotatedBitmap.getHeight();
                    if (inWidth > inHeight) {
                        outWidth = maxSize;
                        outHeight = (inHeight * maxSize) / inWidth;
                    } else {
                        outHeight = maxSize;
                        outWidth = (inWidth * maxSize) / inHeight;
                    }

                    rotatedBitmap = Bitmap.createScaledBitmap(rotatedBitmap, outWidth, outHeight, false);
                    //   rotatedBitmap = Bitmap.createScaledBitmap(photo, (int) (rotatedBitmap.getWidth() * 0.1), (int) (rotatedBitmap.getHeight() * 0.1), true);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    callBackImage.data(byteArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void upDateImage(byte[] imageBytes) {
        ProfileDetailManage.getInstance().upLoadImage(imageBytes, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                UpdateProfileResponseModel updateProfileResponseModel = (UpdateProfileResponseModel) t;
                memberResponseModel.result.avatarLink = updateProfileResponseModel.result
                        .avatarLink;
                view.setUpViewProfile(memberResponseModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {
                view.showMessageFail(error);
            }
        });
    }


    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    @Override
    public void getfinishFragment() {
        view.finishFragment(memberResponseModel);
    }

    @Override
    public void uploadImageProfile(File file) {

    }

    @Override
    public void checkPermissions(int requestCode, String[] permissions, int[] grantResults) {
           view.showGallery();
    }
}
