# C1 
Android系统架构：
* Linux内核层：提供硬件底层驱动
* 系统运行库层：一些C/C++库为提供特性资产，还有Android运行时库（Android Runtime），主要包括ART运行环境（5.0之前为Dalvik虚拟机）
* 应用框架层：提供构建应用程序时用到的各种API
* 应用层：所有安装在手机中的应用程序，包括系统应用

Android程序的设计讲究逻辑和视图分离，因此不推荐在Activity中直接编写界面，通常是在布局文件中编写界面，然后在Activity中将布局文件引入进来   
图片资源一般放在drawable-xxhdpi即可，这是主流的设备分辨率目录       

# C2
Kotlin通过一个特殊的编译器将代码转为class文件然后交由虚拟机运行   

Kotlin抛弃了基本数据类型，全部使用了对象数据类型    

尽量将变量申明为val，val只是对象引用不可变，对象本身的改变并不限制   







# C11
WebView的使用   

OkHttp的使用   

xml和json的传输和解析   
* xml常用解析方式：1.pull 2.SAX 3.DOM 
* json常用解析方式：1.JSONObject 2.GSON    
网络请求的回调实现方式


  