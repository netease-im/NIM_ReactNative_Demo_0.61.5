import {createStackNavigator} from '@react-navigation/stack';
import React from 'react';
import Pages from './pages';

const Stack = createStackNavigator();

const App = (
  <Stack.Navigator initialRouteName="session" headerMode="none" mode="card">
    <Stack.Screen name="session" component={Pages.session} />
    
    <Stack.Screen name="login" component={Pages.login} />
    <Stack.Screen name="register" component={Pages.register} />
    
    <Stack.Screen name="contact" component={Pages.contact} />
    <Stack.Screen name="general" component={Pages.general} />
    <Stack.Screen name="chat" component={Pages.chat} />
    <Stack.Screen name="chatHistroy" component={Pages.chatHistroy} />
    <Stack.Screen name="sysmsg" component={Pages.sysmsg} />
    <Stack.Screen name="namecard" component={Pages.namecard} />
    <Stack.Screen name="searchUser" component={Pages.searchUser} />
    <Stack.Screen name="myinfo" component={Pages.myinfo} />
    <Stack.Screen name="about" component={Pages.about} />
    <Stack.Screen name="test" component={Pages.test} />
  </Stack.Navigator>
);

// const App = createStackNavigator(
//   {
//     login: {
//       screen: Pages.login,
//     },
//     register: {
//       screen: Pages.register,
//     },
//     // home: {
//     //   screen: TabNavigator,
//     // },
//     session: {
//       screen: Pages.session,
//     },
//     contact: {
//       screen: Pages.contact,
//     },
//     general: {
//       screen: Pages.general,
//     },
//     chat: {
//       screen: Pages.chat,
//     },
//     chatHistroy: {
//       screen: Pages.chatHistroy,
//     },
//     sysmsg: {
//       screen: Pages.sysmsg,
//     },
//     namecard: {
//       screen: Pages.namecard,
//     },
//     searchUser: {
//       screen: Pages.searchUser,
//     },
//     // 个人信息页
//     myinfo: {
//       screen: Pages.myinfo,
//     },
//     about: {
//       screen: Pages.about,
//     },
//     test: {
//       screen: Pages.test,
//     },
//   },
//   {
//     initialRouteName: 'session',
//     headerMode: 'none',
//     navigationOptions: {
//       gesturesEnabled: false,
//     },
//     mode: 'card',
//   },
// );

export default App;
