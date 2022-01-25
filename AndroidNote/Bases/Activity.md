# [Activity](https://developer.android.google.cn/guide/components/activities/intro-activities)
Android应用关键组件，Activity的启动和组合方式是该平台应用模型的基本组成，Android系统通过调用其生命周期特定阶段相对应的特定回调方法来启动Activity实例中的代码。  
该组件为为应用与用户互动的入口点，在移动应用中，用户与应用的交互并不是始终在同一位置开启，Activity的出现就是为了实现这一范式，当一个应用调用另一个应用时，调用方会调用另一个应用的某个Activity，而不是启动整个应用。  
通常情况下，一个界面就是一个Activity，应用一般也会包含多个界面，及包含多个Activity，这时，就需要一个Activity被指定为主Activity，这个Activity也是用户启动应用时展示的界面   
应用中各Activity协调工作，但各Activity中应尽量解耦，各Activity应在应用的清单文件中注册相关信息，并必须适当地管理Activity地生命周期    
## Activity生命周期：应该配合使用[生命周期感知型组件](https://developer.android.google.cn/topic/libraries/architecture/lifecycle#kotlin)，这样可以使依赖组件的代码从生命周期方法移入组件本身中
### onCreate：
* 系统创建Activity时触发,在该方法内应执行基本的启动逻辑，该逻辑在Activity的整个生命周期内应只发生一次。
* 该方法会接收一个savedIntanceState参数，该参数是一个保存先前Activity状态的Bundle对象，如果该Activity之前未存在，该对象为空
### onStart：onCreate完成后，会回调该函数，Activity进入已启动状态，在该方法内应进行与用户互动之前地最后准备工作

### onResume：系统在Activity开始与用户互动之前调用此回调，此时，该Activity位于栈顶，并会捕获所有用户地输入，应用地大部分核心功能应在该方法内实现

### onPause：Activity失去焦点并进入已暂停状态时回调，此时Activity仍可见

### onStop ：当Activity对用户不可见时回调，在该方法调用完成后，系统会调用onStart（Activity重新与用户互动）或onDestory（Activity彻底终止）

### onDestory：系统销毁Activity时调用，该方法内应实现资源释放




