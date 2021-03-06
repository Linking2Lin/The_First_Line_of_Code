# [BroadcastReceiver广播接收器](https://developer.android.google.cn/guide/components/broadcasts?hl=zh-cn)
Android应用和系统都可以收发广播消息，广播在关注事件发生时发送，系统会在发生各种系统事件时发送，比如充电，应用可以自定义广播以通知其他应用可能感兴趣的事件，比如某些东西开始下载   
广播机制主要是为了实现应用和系统传递信号，比如我的某一程序开始运行了，如果其他程序需要在该程序开始运行后进行一些操作，那么这一程序通过广播来通知其他程序该程序已经启动（事件），其他程序接收到了该通知（注册这个事件为感兴趣），接下来各程序开始执行实现设定好的操作    
* 发送广播：通过Intent，广播消息本身会被封装在一个Intent对象当中
* 接收广播：BroadcastReciver（四大组件之一）
## 广播类型：
* 标准广播：完全异步，广播发出后，所有的接收器同一时刻都会收到该广播
* 有序广播：同步执行，同一时刻只有一个接收器会收到该消息，当这个接收器内的逻辑处理完成后才会继续传递，接收器也可以选择截断该广播，这样后面的接收器就不会收到这条广播了
## 广播接收器：
用来接收广播并对广播进行逻辑处理的组件，使用时需要先进行注册，注册方式有动态注册和静态注册
* 动态注册：在上下文注册（即代码里注册），可以自由控制注册与注销，但是必须在程序启动后才能开始接收广播
* 静态注册：在清单文件中注册，API26以上不允许在清单内注册隐式广播（未指定接收程序的广播），采用这种方式注册的广播接收器可以在程序未启动时就开始接收广播
