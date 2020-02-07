import 'react-native-gesture-handler';
import React, { Component } from 'react';
import { BackHandler, Alert } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { Provider, observer } from 'mobx-react';
import NetInfo from "@react-native-community/netinfo";
import stores from './src/store';
import App from './src';

export default class NimApp extends Component {
  constructor(props) {
    super(props);
    BackHandler.addEventListener(
      'hardwareBackPress',
      this.onBackButtonPressAndroid,
    );
    this.unsubscribeNetInfo = NetInfo.addEventListener(state => {
      if (!state.isConnected) {
        Alert.alert('提示', '网络已断开，请连接网络重试');
      }
    });
  }

  componentWillUnmount() {
    BackHandler.removeEventListener(
      'hardwareBackPress',
      this.onBackButtonPressAndroid,
    );
    this.unsubscribeNetInfo();
  }
  onBackButtonPressAndroid = () => true
  render() {
    return (
      <Provider {...stores}>
        <NavigationContainer>{App}</NavigationContainer>
      </Provider>
    );
  }
}
