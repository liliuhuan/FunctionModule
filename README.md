# FunctionModule
模块介绍

app：一些技术的合计，aidl，动画，注解，aop，fragment，权限，数据库，自定义view，js与原生交互

Lock9ViewDemo：9宫格认证解锁

moduleA，moduleB：组件化开发demo

mylibrary：一些公共部分的封装，http请求，常用公共类，自定义view

pswketboard：放微信支付部分UI

rsaAndaes：数据加密解密

signleandMutil：单多选


5.06.0AndroidChaeter:材料特性总结

6.0Fingerprint ： 指纹解锁，官方版

7.0ShortCut : 应用程序快捷键

shareAppHome ：7.0拍照图片存储适配provider，其他应用刁起及结果回传

shareOtherApp : 应用间数据传递及调用

rsalibrary:
library打包成jar包引用在app中  ，

aar 配置
 ①.将aar包复制到lib目录下
 ②.配置工程app的build.gradle文件：
 加入
   repositories {
       flatDir {
        dirs 'libs'
       }
  compile(name:'httputils-debug', ext:'aar')
  
TransitionAnimation : 转场动画