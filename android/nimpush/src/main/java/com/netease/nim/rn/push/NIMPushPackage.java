package com.netease.nim.rn.push;

import android.util.Log;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import com.netease.nimlib.rnpush.log.RNLog;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NIMPushPackage implements ReactPackage {

    private static final String TAG = "NIMPushPackage";

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        RNLog.d(TAG, "createNativeModules: ");
        return Arrays.asList(new NativeModule[]{
                new NIMPushModule(reactContext),
        });
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }


}
