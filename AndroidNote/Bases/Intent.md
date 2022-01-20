# [Intent](https://developer.android.google.cn/guide/components/intents-filters)
Intent:一个消息传递对象，可以用来从其他应用组件请求操作，也可以与被启动的组件交换信息，通常用来执行启动Activity、启动服务和传递广播这三个操作，该对象封装了程序的调用意图，保证了编程模型的一致性，实现了组件间的解耦
## Intent类型：
* 显式：通过提供目标应用的软件包名称或完全限定的组件类名来指定可处理Intent的应用
* 隐式：不指定特定的组件，而是声明要执行的常规操作，从而允许其他应用中的组件来处理
使用显式Intent时，系统会直接启动Intent中声明的组件，而当使用隐式Intent时，Android会将Intent中内容与在设备上应用清单文件中声明的Intent过滤器进行比
  较，从而找到要启动的相应组件，系统会启动该组件，然后将Intent对象传递给它。如果系统找到了多个Intent过滤器符合要求，系统会显示一个对话框，展示符合的组件，让用户选择启动哪个组件
***为了确保应用的安全性，启动服务时需确保始终使用显式Intent方式来启动，且不要为服务声明Intent过滤器***  
  ***要接收隐式Intent的话，必须将CATEGORY_DEFAULT包含在Intent过滤器中***