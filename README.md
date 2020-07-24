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
