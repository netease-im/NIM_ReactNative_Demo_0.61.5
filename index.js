/**
 * @format
 */

import { AppRegistry, Platform } from 'react-native';
import App from './App';
import {name as appName} from './app.json';

global.ISIOS = false;
global.ISANDROID = false;
if (Platform.OS.toLowerCase() === 'android') {
  global.ISANDROID = true;
} else if (Platform.OS.toLowerCase() === 'ios') {
  global.ISIOS = true;
}

AppRegistry.registerComponent(appName, () => App);