[English README](./README_EN.md)


需要 node > 10

## 启动

前置该装 cocoapods，react-native-cli，自行搜索。

clone 项目后

```
$ npm install

# pod 依赖安装，这一步可能你会遇到很多问题
$ cd ios && pod install

$ cd ../
$ react-native run-ios
```

pod 安装和 react-native 启动时遇到很多问题，记载了下来，[RN 0.57.8 与 0.60.5 的环境安装问题记录](https://www.cnblogs.com/everlose/p/13359557.html)

有以下几个问题

* Could not find iPhone X simulator
* duplicate symbol '_RCTRemoteNotificationReceived'
* error: The sandbox is not in sync with the Podfile.lock. Run 'pod install' or update your CocoaPods installation.
* ReactNative pod install 卡在 RealmJS (4.0.0-beta.2)
* ReactNative pod install 卡在 boost-for-react-native (1.63.0)
* Error: Unable to resolve module `@react-native-community/toolbar-android`


## 调试

遇到问题

* Unable to find module for EventDispatcher React Native

解决方法：Stop remote JS debugging and then reload the app

## 编译成不同版本的 IOS

寻找可用的 IOS simulator 设备

```
$ xcrun simctl list devices
== Devices ==
-- iOS 12.4 --
    iPhone 5s (3A35381B-C648-414B-95FA-899597BA891D) (Shutdown) 
    iPhone 6 Plus (A708C909-FF19-4986-B87B-ECD48F10C1A7) (Shutdown) 
    iPhone 6 (3CA7222F-9D89-49B1-A83E-FF6B0D151521) (Shutdown) 
    iPhone 6s (ECAADBE3-7A53-4B3E-AB32-6A5EC51D28DB) (Shutdown) 
    iPhone 6s Plus (C0500D92-B768-4A31-8D22-6603AE90BA41) (Shutdown) 
    iPhone SE (1st generation) (65CFAF7E-E329-4C37-A30E-F85740810A30) (Shutdown) 
    iPhone 7 (8E794E6C-4D4C-42CE-9EFE-CE72EE947E0D) (Shutdown) 
    iPhone 7 Plus (534A50B1-D383-4686-B800-D7E008614EFA) (Shutdown) 
    iPhone 8 (0C894F5C-237A-4A8D-B28D-BBFE016C7359) (Shutdown) 
    iPhone 8 Plus (D4CEE197-F1B5-403B-AF18-D116E0CD5947) (Shutdown) 
    iPhone X (60C02F2F-D8E3-4BD7-B767-6F0D61991469) (Shutdown) 
    iPhone Xs (29ABC7C5-F1E7-4ADC-8D0D-AF3F0ACB89C4) (Shutdown) 
    iPhone Xs Max (183A477A-E85A-4863-8DA9-8E38031D6601) (Shutdown) 
    iPhone Xʀ (FEEE6885-5988-4A40-958C-BFE2BCE847C9) (Shutdown) 
    iPad Air (D9327D8F-C3ED-43F5-B469-C917F66F4B28) (Shutdown) 
    iPad Air 2 (681F0A12-547D-4BD3-9F02-60E49BD7838C) (Shutdown) 
    iPad Pro (9.7-inch) (B4487B9D-30DF-43EF-8750-B1F326D18DA9) (Shutdown) 
    iPad Pro (12.9-inch) (43A657BC-E524-4FBE-A95F-632E59B99E5B) (Shutdown) 
    iPad (5th generation) (5816F23D-7F15-44FA-8B1F-6229559432C8) (Shutdown) 
    iPad Pro (12.9-inch) (2nd generation) (4FBAF06A-D280-4511-9139-B061D0F2493F) (Shutdown) 
    iPad Pro (10.5-inch) (13B162DF-5208-4958-AED3-BC6F054D4291) (Shutdown) 
    iPad (6th generation) (3813C8DA-AC3D-4A4E-BACD-A8CC3FF8B1A6) (Shutdown) 
    iPad Pro (11-inch) (1st generation) (FC38FB2F-2DF9-4345-BA12-AA5EE7BFBCC7) (Shutdown) 
    iPad Pro (12.9-inch) (3rd generation) (0526CF2B-9685-4504-8336-AC762D2D283F) (Shutdown) 
    iPad Air (3rd generation) (CF2C82EA-E75C-462A-9651-2128C211D549) (Shutdown) 
-- iOS 13.5 --
    iPhone 8 (EB10DFC7-4171-48B5-AF16-F620645B88DC) (Shutdown) 
    iPhone 8 Plus (B485D041-7DC7-4320-B11F-A9E8CEDEFE24) (Shutdown) 
    iPhone X (A0FC582F-177C-468C-9E75-36A9762AD283) (Shutdown) 
    iPhone 11 (03BF5192-3E2F-4AC8-A1CF-52682784497D) (Shutdown) 
    iPhone 11 Pro (D3D5A329-189D-46A2-AAEF-9EDC3F9304CE) (Shutdown) 
    iPhone 11 Pro Max (8F80B247-01A1-43F6-A9B3-5E655C72296F) (Shutdown) 
    iPhone SE (2nd generation) (F2D85EDA-181E-4FCE-9130-094C3BC7BB2C) (Shutdown) 
    iPad Pro (9.7-inch) (3DED65AE-2CB2-4ADD-A367-DA9DE3D55497) (Shutdown) 
    iPad (7th generation) (5CE94EAF-6267-44C0-A506-12D21075CDD2) (Shutdown) 
    iPad Pro (11-inch) (2nd generation) (E9619B21-D17F-4111-8D9A-DDA417A2FC0B) (Shutdown) 
    iPad Pro (12.9-inch) (4th generation) (AFC119C5-1391-4B04-AC03-ACADDE8B4A6C) (Shutdown) 
    iPad Air (3rd generation) (12108A85-B885-4EC6-A50A-DB24EA54BB7D) (Shutdown) 
```

想起个 IOS 12，那么这样做

```
react-native run-ios --simulator "iPhone 7 Plus"
```

截至 24 年 我的系统和 xcode 都已经升级到最新,  ios 会编译不过提示 `Called object type 'facebook::flipper::SocketMessageHandler' (aka 'int') is not a function or function pointer`

需要升级 flipper.  找到 ios/Podfile 文件, 找到 `flipper` 的依赖

```
// 原代码是 use_flipper!() 改为以下的代码强行升级 Flipper
use_flipper!({ 'Flipper' => '0.250.0' })
```


## Android 调试的障碍

试图用真机 android 调试

1. 寻找插入的设备, 并连接进入开发者模式

```
% adb devices

List of devices attached
bb4167d8        device
```

2. 启动

```
% npx react-native run-android

..... 省略中间日志, 显示错误

error Failed to install the app. Make sure you have the Android development environment set up: https://reactnative.dev/docs/environment-setup.
Error: Command failed: ./gradlew app:installDebug -PreactNativeDevServerPort=8081
```

使用 Android Studio 打开 nim_reactnative_demo/android 目录. 发现错误 `Cannot run program "node": error=2, No such file or directory`

```
# 可能是 nvm 的缘故, 使 android 找不到 node . 
# 完全关闭 android studio 后, 利用下面的命令启动
% open -a /Applications/Android\ Studio.app
```

Android Studio 在手机上安装了 app 后, 手机可能会出现无法连接服务的红屏幕报错, 可能需要运行

```
% adb reverse tcp:8081 tcp:8081
# 再重启 metro 服务
% npx react-native run-android
```

[启动后发现 android 的 图标全挂了](https://stackoverflow.com/questions/73552392/react-native-0-69-5-react-native-elements-icon-component-showing-just-x). 需要去 `android/app/build.gradle` 去添加图标字体路径

```
apply from: "../../node_modules/react-native-vector-icons/fonts.gradle"
```

## iOS 推送

1. 项目添加推送能力

打开 MyProject.xcworkspace，选择项目"MyProject",继续选择选项卡"Signing & Capabilities", 点击"+"按钮，添加2项新的能力

- `Background Mode` capability and tick `Remote Notifications`.
- `Push Notifications` capability

2. 你需要在 AppDelegate 中启用推送通知的支持以及注册相应的事件。 

在AppDelegate.h开头:

```objective-c
#import <UserNotifications/UNUserNotificationCenter.h>
```
然后添加 'UNUserNotificationCenterDelegate' 

在RN v0.71以上版本

```objective-c
@interface AppDelegate : RCTAppDelegate <UNUserNotificationCenterDelegate>
```
在RN v0.70一下版本

```objective-c
@interface AppDelegate : UIResponder <UIApplicationDelegate, RCTBridgeDelegate, UNUserNotificationCenterDelegate>
```

在AppDelegate.m开头：

```objective-c
#import <UserNotifications/UserNotifications.h>
#import <RNCPushNotificationIOS.h>
```

在AppDelegate 实现,添加下面代码

```objective-c
// Required for the register event.
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
 [RNCPushNotificationIOS didRegisterForRemoteNotificationsWithDeviceToken:deviceToken];
}
// Required for the notification event. You must call the completion handler after handling the remote notification.
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo
fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler
{
  [RNCPushNotificationIOS didReceiveRemoteNotification:userInfo fetchCompletionHandler:completionHandler];
}
// Required for the registrationError event.
- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
 [RNCPushNotificationIOS didFailToRegisterForRemoteNotificationsWithError:error];
}
// Required for localNotification event
- (void)userNotificationCenter:(UNUserNotificationCenter *)center
didReceiveNotificationResponse:(UNNotificationResponse *)response
         withCompletionHandler:(void (^)(void))completionHandler
{
  [RNCPushNotificationIOS didReceiveNotificationResponse:response];
}
```

继续添加下面的代码

```objective-c
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  ...
  // Define UNUserNotificationCenter
  UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
  center.delegate = self;

  return YES;
}

//Called when a notification is delivered to a foreground app.
-(void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions options))completionHandler
{
  completionHandler(UNNotificationPresentationOptionSound | UNNotificationPresentationOptionAlert | UNNotificationPresentationOptionBadge);
}
```



## 切换 java 版本

```
% /usr/libexec/java_home -V
Matching Java Virtual Machines (2):
    17.0.12 (x86_64) "Oracle Corporation" - "Java SE 17.0.12" /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
    1.8.0_302 (x86_64) "Amazon" - "Amazon Corretto 8" /Users/chenziyun/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
```

修改 JAVA_HOME 环境变量

```
% vim ~/.zshrc 
export JAVA_HOME="/Users/chenziyun/Library/Java/JavaVirtualMachines/corretto-1.8.0_302/Contents/Home"

% source ~/.zshrc
```
