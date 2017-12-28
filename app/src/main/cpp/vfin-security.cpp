#include <jni.h>
#include <string>
#include <string.h>


const std::string baseApi = "https://apis-v2.dev.v-fin.com/";
const std::string keyDebug = "ze/hAwwUCE98cPRS0rtzJMwWB/A=";
const std::string keyProduct = "wnPjb4RrrObo1+c3+wrooZs2AqM=";


jboolean checkValidSignature(JNIEnv *env, jstring keyHash) {
    const char *c = keyDebug.c_str();
    const char *cProduct = keyProduct.c_str();
    const char *key2 = env->GetStringUTFChars(keyHash, NULL);
    if (strcmp(key2, c) == 0) {
        return true;
    } else if (strcmp(key2, cProduct) == 0) {
        return true;
    } else {
        return false;
    }
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_getBaseApi(JNIEnv *env, jobject /* this */, jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = baseApi;
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}



extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_getKeyAes(JNIEnv *env, jobject /* this */, jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "FA14DF738AD3B1DC9EA13BD6C748CJD5";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_getIvKey(JNIEnv *env, jobject /* this */, jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "AV8LmxOdzwLbK0nE";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiLogin(JNIEnv *env, jobject /* this */, jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/login";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiLoginFaceBook(JNIEnv *env, jobject /* this */,
                                                             jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/connectWithFacebook";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiSaveAddress(JNIEnv *env, jobject /* this */,
                                                             jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/member/saveAddress";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiGetAddress(JNIEnv *env, jobject /* this */,
                                                             jstring
keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/member/getAddress";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiUpdateAddressIsPrimary(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/member/updateAddressIsPrimary";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiGetMember(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/member/getMember";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiUpdateMemberProfile(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/member/updateMemberProfile";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiForgotPassword(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/forgotPassword";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiVerifyOtp(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/verifyOtp";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiUpdatePassword(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/updatePassword";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiVerifyAccount(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/verifyAccount";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiRegister(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "memberservice/v2/user/register";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiGetWalletTransaction(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "walletservice/v1/wallet/getWalletTransaction";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiListRedemption(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/trade/listRedemption";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiCalFee(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/orders/calFee/";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiCreateOrder(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/orders/createOrder";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiTradeBuy(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/trade/buy";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiTradeBid(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/trade/bid";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiFindMyItem(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/trade/findMyItem/";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiTradeProduct(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "marketservice/v1/trade/product/";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiMessageList(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "messageservice/v1/message/list/";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_mvision_vfin_component_configkey_GetKey_apiMessageDelete(JNIEnv *env, jobject
/* this */,jstring keyHash) {

    std::string result;

    if (checkValidSignature(env,keyHash)) {
        result = "messageservice/v1/message/delete/";
    } else {
        result = "";
    }

    return env->NewStringUTF(result.c_str());
}



