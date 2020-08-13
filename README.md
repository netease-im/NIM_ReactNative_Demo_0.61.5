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

pod 安装和 react-native 启动时遇到很多问题，我记载了下来，[RN 0.57.8 与 0.60.5 的环境安装问题记录](https://www.cnblogs.com/everlose/p/13359557.html)

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

## 调试推送 token 

想要调试推送的 token 的话，需要用 xcode 在真机上运行，注意有些权限、证书配置比较苦恼，附上我当时调试的心得

http://jira.netease.com/browse/MMC2-12382

## 连 IPhone 真机开发

开发的 profiles 下载 NIMReactNativeDemo 的 profile，证书可以用自己的 apple 开发者账号，如果是新人，联系 IOS 开发张根宁/徐冬，需要把自己apple 开发账号添加进入群组，才能编译使用。

![](https://yx-web-nosdn.netease.im/quickhtml%2Fassets%2Fyunxin%2Fnim_reactnative_demo%2FZIE%20Web%20Demo.png)

上面说的方法只是 debug 模式的，如果你要做一个 release 版本，那需要 NiMReactNativeDemo_Distribution 的 profile，其证书向徐冬要。

## 连 IPhone 真机测试推送

推送需要用 APN 证书，证书一般一年就会过期，过期后还得再找 IOS 帮忙申请新的证书

![]( https://yx-web-nosdn.netease.im/quickhtml%2Fassets%2Fyunxin%2Fnim_reactnative_demo%2Fasdoijkzmoiashdfadsafzqw.png)

本地测试推送时使用 Easy APNS Provider 工具，它需要得知 device Token，以及 cer 证书，联系陈通塔or马莹莹or张根宁去得到 p12 文件和 cer 文件。如下图，测试包使用测试的 cer 连接 gateway.sandbox.push.apple.com ，填写好 token 后就可以发送

![](https://yx-web-nosdn.netease.im/quickhtml%2Fassets%2Fyunxin%2Fnim_reactnative_demo%2Fzmczaoiqjzcuhzadgf.png)

效果如下

![]( https://yx-web-nosdn.netease.im/quickhtml%2Fassets%2Fyunxin%2Fnim_reactnative_demo%2Fuwskfjziuawkofijsu.jpg)

想测试一个完整的推送流程，则需要给服务端李兴上文的 p12 格式证书，并告知他pushname，appid等信息，其格式如下

```
release 模式
appid：org.reactjs.native.example.NIM-ReactNative-Demo
pushname：RN_APNS_PUSH_PRODUCT
password: 111111
cert: 见线上的 p12 文件
type：1

debug 模式
appid：org.reactjs.native.example.NIM-ReactNative-Demo
pushname：RN_APNS_PUSH_SANDBOX
password: 111111
cert: 见开发的 p12 文件
type：0
```

在 xcode 上按三角号启用 debug 模式，并注意 demo 工程下的 iosPushConfig 对象里的 tokenName 是上文的debug 模式里的 RN_APNS_PUSH_SANDBOX。接着就可以去测试 debug 版本的推送情况

如果想测 release 版本的，先要打包成release，先打包成 archive，再从 archive 打包成 ipa 文件，接着从 window -> devices and simulators 看到具体设备，把ipa拖进去就可以安装。

![](https://yx-web-nosdn.netease.im/quickhtml%2Fassets%2Fyunxin%2Fnim_reactnative_demo%2Fszdgdsaoiqiofdua.png)

在手机上安装完成文件后就可以测试 release 版本的推送情况了


