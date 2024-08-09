### Android 手动配置

#### 手动link

请在 app gradle 文件 dependencies 添加此模块。并在MainApplication中mReactNativeHost的getPackages方法中添加此模块（NIMPushPackage）。

#### AndroidManifest.xml 配置

- 小米推送

推送服务和广播

```xml
<receiver
		android:exported="true"
		android:name="com.netease.nimlib.mixpush.mi.MiPushReceiver">
	<intent-filter android:priority="0x7fffffff">
		<action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE"/>
		<action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED"/>
		<action android:name="com.xiaomi.mipush.ERROR"/>
	</intent-filter>
</receiver>
```


- 魅族推送

自定义权限

```xml
<!-- 兼容 Flyme5 的权限配置-->
<uses-permission android:name="com.meizu.flyme.push.permission.RECEIVE"/>
<permission
android:name="${applicationId}.push.permission.MESSAGE"
android:protectionLevel="signature"/>
<uses-permission android:name="{applicationId}.push.permission.MESSAGE"/>
		<!-- 兼容 Flyme3 的权限配置-->
<uses-permission android:name="com.meizu.c2dm.permission.RECEIVE"/>
<permission
android:name="${applicationId}.permission.C2D_MESSAGE"
android:protectionLevel="signature"/>
<uses-permission android:name="${applicationId}.permission.C2D_MESSAGE"/>
```

推送服务和广播

```xml
<receiver
		android:exported="true"
		android:name="com.netease.nimlib.mixpush.mz.MZPushReceiver"
		android:permission="com.meizu.cloud.push.permission.MESSAGE">
	<intent-filter android:priority="0x7fffffff">
		<!-- 接收 push 消息 -->
		<action android:name="com.meizu.flyme.push.intent.MESSAGE"/>
		<!-- 接收 register 消息 -->
		<action android:name="com.meizu.flyme.push.intent.REGISTER.FEEDBACK"/>
		<!-- 接收 unregister 消息 -->
		<action android:name="com.meizu.flyme.push.intent.UNREGISTER.FEEDBACK"/>
		<!-- 兼容低版本 Flyme3 推送服务配置 -->
		<action android:name="com.meizu.c2dm.intent.REGISTRATION"/>
		<action android:name="com.meizu.c2dm.intent.RECEIVE"/>
		
		<category android:name="${applicationId}"/>
	</intent-filter>
</receiver>
```

- vivo推送

推送服务和广播

```xml
<receiver
		android:exported="true"
		android:name="com.netease.nimlib.mixpush.vivo.VivoPushReceiver">
	<intent-filter>
		
		<!-- 接收 push 消息 -->
		<action android:name="com.vivo.pushclient.action.RECEIVE"/>
	</intent-filter>
</receiver>
<service
android:exported="true"
android:name="com.vivo.push.sdk.service.CommandClientService"
android:permission="com.push.permission.UPSTAGESERVICE"/>

<meta-data
android:name="com.vivo.push.api_key"
android:value="vivo推送appkey"/>
<meta-data
android:name="com.vivo.push.app_id"
android:value="vivo推送appid"/>
```

- OPPO推送

自定义权限

```xml
    <!--  oppo推送配置权限-->
<uses-permission android:name="com.coloros.mcs.permission.RECIEVE_MCS_MESSAGE"/>
<uses-permission android:name="com.heytap.mcs.permission.RECIEVE_MCS_MESSAGE"/>
```

推送服务和广播

```xml
<!--Oppo推送配置项 需要配置以下两项-->
<!-- 兼容Q以下版本 继承CompatibleDataMessageCallbackService-->
<service
		android:exported="true"
		android:name="com.netease.nimlib.mixpush.oppo.OppoPushService"
		android:permission="com.coloros.mcs.permission.SEND_MCS_MESSAGE">
	<intent-filter>
		<action android:name="com.coloros.mcs.action.RECEIVE_MCS_MESSAGE"/>
	</intent-filter>
</service>
		<!-- 兼容Q版本 继承DataMessageCallbackService-->
<service
android:exported="true"
android:name="com.netease.nimlib.mixpush.oppo.OppoAppPushService"
android:permission="com.heytap.mcs.permission.SEND_PUSH_MESSAGE">
<intent-filter>
	<action android:name="com.heytap.mcs.action.RECEIVE_MCS_MESSAGE"/>
	<action android:name="com.heytap.msp.push.RECEIVE_MCS_MESSAGE"/>
</intent-filter>
</service>

<service
android:exported="true"
android:name="com.heytap.msp.push.service.CompatibleDataMessageCallbackService"
android:permission="com.coloros.mcs.permission.SEND_MCS_MESSAGE">
<intent-filter>
	<action android:name="com.coloros.mcs.action.RECEIVE_MCS_MESSAGE"/>
</intent-filter>
</service>

<service
android:exported="true"
android:name="com.heytap.msp.push.service.DataMessageCallbackService"
android:permission="com.heytap.mcs.permission.SEND_PUSH_MESSAGE">
<intent-filter>
	<action android:name="com.heytap.mcs.action.RECEIVE_MCS_MESSAGE"/>
	<action android:name="com.heytap.msp.push.RECEIVE_MCS_MESSAGE"/>
</intent-filter>
</service>
```



- FCM 谷歌推送

推送服务和广播

```xml
<service
		android:exported="false"
		android:name="com.netease.nimlib.mixpush.fcm.FCMTokenService">
	<intent-filter>
		<action android:name="com.google.firebase.MESSAGING_EVENT"/>
	</intent-filter>
</service>

<!--设置收到 fcm 通知展示的图标和颜色-->
<meta-data
    android:name="com.google.firebase.messaging.default_notification_icon"
    android:resource="@drawable/xxx" />
<meta-data
    android:name="com.google.firebase.messaging.default_notification_color"
    android:resource="@color/xxx" />
```

- 华为推送

推送服务和广播

```xml

<service
		android:exported="false"
		android:name="com.netease.nimlib.mixpush.hw.HWPushService">
	<intent-filter>
		<action android:name="com.huawei.push.action.MESSAGING_EVENT"/>
	</intent-filter>
</service>
```

- 荣耀推送
```xml
 <service
		android:exported="false"
		android:name="com.netease.nimlib.mixpush.honor.HonorPushService">
	<intent-filter>
		<action android:name="com.hihonor.push.action.MESSAGING_EVENT"/>
	</intent-filter>
</service>

<meta-data
android:name="com.hihonor.push.app_id"
android:value="你的荣耀推送appId" />
```

#### 推送 SDK 引入

- 小米推送

请至官网下载 MiPush_SDK_Client_6_0_1-C_3rd.aar，添加到 app 的libs 目录下。


- 魅族推送

请在 app gradle 文件 dependencies 添加 
    `implementation 'com.meizu.flyme.internet:push-internal:4.3.0'`；

- vivo推送

请至官网下载 vivo_pushSDK_v4.0.4.0_504.aar，添加到 app 的libs 目录下。

- OPPO推送

请至官网下载 com.heytap.msp_3.5.1.aar，添加到 app 的libs 目录下。

- FCM 推送

请在 app gradle 文件 dependencies 添加

```groovy
implementation 'com.google.android.gms:play-services-base:18.5.0'
implementation 'com.google.firebase:firebase-messaging:24.0.0'
```
，在 app gradle 文件末尾添加

```groovy
apply plugin: 'com.google.gms.google-services'
```

将FCM 生成的 `goole-services.json` 添加到 app 根目录。

此外，在工程根目录的 gradle 文件，在 buildscript > dependencies 中增加插件配置。
```groovy
classpath 'com.google.gms:google-services:4.3.15'
```

- 华为推送

请在 app gradle 文件 dependencies 添加
```groovy
implementation 'com.huawei.hms:push:6.12.0.300'
```
，在 app gradle 文件开头添加
```groovy
apply plugin: 'com.huawei.agconnect'
```

将华为推送生成的`agconnect-services.json` 添加到 app 根目录。

此外，在工程根目录的 gradle 文件，在 buildscript > dependencies 中增加 AGC 插件配置。
```groovy
classpath 'com.huawei.agconnect:agcp:1.6.0.300'
```

在allprojects的 repositories 节点下，添加
```groovy
maven {url 'http://developer.huawei.com/repo' }
```

- 荣耀推送

请在 app gradle 文件 dependencies 添加
```groovy
implementation 'com.hihonor.mcs:push:7.0.61.303'
```

将荣耀推送生成的`mcs-services.json` 添加到 app 根目录。

此外，在工程根目录的 gradle 文件中，在allprojects的 repositories 节点下，添加
```groovy
maven {url 'https://developer.hihonor.com/repo'}
```


