package com.netease.nim.rn.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.heytap.msp.push.HeytapPushManager;
import com.hihonor.push.sdk.HonorPushClient;
import com.netease.nimlib.rnpush.log.RNLog;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.rnpush.RNPushClient;
import com.netease.nimlib.sdk.rnpush.RNPushEventHandler;
import com.netease.nimlib.sdk.rnpush.RNPushOptions;
import com.netease.nimlib.sdk.mixpush.MixPushConfig;
import java.util.Map;

import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class NIMPushModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final String TAG = "NIMPushModule";

    private static boolean isInit = false;

    public NIMPushModule(ReactApplicationContext reactContext) {
        super(reactContext);
        RNLog.d(TAG, "NIMPushModule: create");
        // handle new intent
        reactContext.addActivityEventListener(this);
    }

    @Override
    public String getName() {
        return "NIMPushModule";
    }


    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        return super.getConstants();
    }

    @ReactMethod
    public void toast(String message) {
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void init(
            String configJson,
            final Callback tokenCallback) {

        RNLog.d(TAG, "init: " + configJson);

        RNPushOptions options = new RNPushOptions();

        // 1. 推送配置
        MixPushConfig pushConfig = new MixPushConfig();
	    try {
		    JSONObject config = new JSONObject(configJson);
        String xmAppId = config.optString("xmAppId");
        String xmAppKey = config.optString("xmAppKey");
        String xmCertificateName = config.optString("xmCertificateName");

        String mzAppId = config.optString("mzAppId");
        String mzAppKey = config.optString("mzAppKey");
        String mzCertificateName = config.optString("mzCertificateName");

        String hwAppId = config.optString("hwAppId");
        String hwCertificateName = config.optString("hwCertificateName");

        String fcmCertificateName = config.optString("fcmCertificateName");

        String vivoCertificateName = config.optString("vivoCertificateName");

        String oppoAppId = config.optString("oppoAppId");
        String oppoAppKey = config.optString("oppoAppKey");
        String oppoAppSercet = config.optString("oppoAppSercet");
        String oppoCertificateName = config.optString("oppoCertificateName");

        String honorCertificateName = config.optString("honorCertificateName");
        pushConfig.xmAppId = xmAppId;
        pushConfig.xmAppKey = xmAppKey;
        pushConfig.xmCertificateName = xmCertificateName;

        pushConfig.mzAppId = mzAppId;
        pushConfig.mzAppKey = mzAppKey;
        pushConfig.mzCertificateName = mzCertificateName;

        pushConfig.hwAppId = hwAppId;
        pushConfig.hwCertificateName = hwCertificateName;

        pushConfig.fcmCertificateName = fcmCertificateName;
        pushConfig.vivoCertificateName = vivoCertificateName;
        pushConfig.oppoAppId = oppoAppId;
        pushConfig.oppoAppKey = oppoAppKey;
        pushConfig.oppoAppSercet = oppoAppSercet;
        pushConfig.oppoCertificateName = oppoCertificateName;
        pushConfig.honorCertificateName = honorCertificateName;

	    }
	    catch (Throwable e) {
		   RNLog.e(TAG, "init error: " + e.getMessage());
	    }




        options.mixPushConfig = pushConfig;

        // 2. 事件回调
        options.eventHandler = new RNPushEventHandler() {

            @Override
            public void onPushToken(int pushType, String tokenName, String token) {
                RNLog.d(TAG, "onPushToken: pushType = " + pushType + " tokenName = " + tokenName + " token = " + token);
                // 提交 token 到服务器
                tokenCallback.invoke(pushType, tokenName, token);
            }
        };

        initPushSDK(getReactApplicationContext());

        RNPushClient.init(getReactApplicationContext(), options);
    }

    @ReactMethod
    public void showNotification(String icon, String title, String content, String time) {
        RNLog.e(getName(), "showNotification");
        RNPushClient.showNotification(icon, title, content, time);
    }

    @ReactMethod
    public void clearAllNotification() {
        RNPushClient.clearAllNotification();
    }

    @ReactMethod
    public void onLogin(String account,
                        int pushType,
                        boolean hasPushed,
                        String lastDeviceId) {
        RNLog.e(getName(), "onLogin account = " + account + " pushtype = " + pushType + " haspushed = " + hasPushed + " deviceInfo = " + lastDeviceId);
        RNPushClient.onLogined(account, pushType, hasPushed, lastDeviceId);

        if (getCurrentActivity() != null) {
            handleIntent(getCurrentActivity().getIntent());
        }
    }

    @ReactMethod
    public void setDeviceId(String deviceId) {
        RNLog.e(getName(), "setDeviceId deviceId = " + deviceId);
        RNPushClient.saveDeviceId(deviceId);
    }

    @ReactMethod
    public void getDeviceInfo(Callback callback) {
        String deviceInfo = RNPushClient.getDeviceInfo();
        callback.invoke(deviceInfo);
    }

    @ReactMethod
    public void onLogout() {
        RNPushClient.onLogout();
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        RNPushClient.clearAllNotification();
    }

    @Override
    public void onNewIntent(Intent intent) {
        // new intent
        // 来自notification 或者 来自launcher点击
        if (RNPushClient.fromNotification(intent) || fromLauncher(intent)) {
            handleIntent(intent);
        }
    }

    private static boolean fromLauncher(Intent intent) {
        return intent != null
                && intent.getCategories() != null
                && intent.getCategories().contains(Intent.CATEGORY_LAUNCHER);

    }

    private static void initPushSDK(Context context){
        if(isInit){
            return;
        }
        try {
            NIMClient.config(context,null, SDKOptions.DEFAULT);
            RNLog.i(TAG,"config NIMClient finish");
        }
        catch (Exception e) {
            RNLog.i(TAG,"init NIMClient exception = "+e);
        }

        try
        {
            // 初始化OPPO PUSH服务，创建默认通道
            HeytapPushManager.init(context, true);
            RNLog.i(TAG,"init OPPO PUSH finish");
        }
        catch (Throwable e)
        {
            RNLog.i(TAG,"init OPPO PUSH exception = "+e);
        }

        try {
            HonorPushClient.getInstance().init(context,false);
        }
        catch (Throwable e) {
            RNLog.i(TAG,"init Honor PUSH exception = "+e);
        }

        isInit = true;
    }
}
