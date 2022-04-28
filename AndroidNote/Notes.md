___
# C1 概述
Android系统架构：
* Linux内核层：提供硬件底层驱动
* 系统运行库层：一些C/C++库为提供特性资产，还有Android运行时库（Android Runtime），主要包括ART运行环境（5.0之前为Dalvik虚拟机）
* 应用框架层：提供构建应用程序时用到的各种API
* 应用层：所有安装在手机中的应用程序，包括系统应用
       

Android两个基本概念：
* 应用提供多个入口点：Android应用都是将各种可单独调用的组件加以组合构建而成，反映出的现象是程序即可通过启动器点按应用图标启动（启动后进入主Activity），也可以在其他程序调用程序内任一Activity
* 应用适应不同的设备：可以针对不同的屏幕尺寸创建不同的布局，让系统根据当前的设备尺寸确定要使用的布局。
如果应用需要使用特定的硬件，可以在运行时查询该设备是否具有该硬件，如果没有，则停用相关功能，也可以指定特定的硬件，这样play就不会在没有该硬件的设备上安装应用（play商店里的无法在本设备上安装该应用）
        
build.gradle：模块下的控制该模块的构建，全局项目下的控制全局的构建      

Android应用基础知识：
* SDK工具会将代码连同数据和资源文件编译为一个APK，该APK包括了应用的所有内容
* Android操作系统是一种多用户Linux系统，其中每个应用都是一个不同的用户
* 默认情况下，系统会为每个应用分配一个唯一的Linux用户ID（系统使用，应用透明），系统会为应用中的所有文件设置权限，只有分配该应用的用户ID才能访问这些文件
* 每个进程都拥有自己的虚拟机，因此应用代码独立于其他应用而运行
* 默认情况下，每个应用都在其自己的Linux进程内运行，Android系统会在需要执行任何应用组件时启动该进程，当不再需要该进程或系统必须为其他应用恢复内存时，其便会关闭该进程       









Android程序的设计讲究逻辑和视图分离，因此不推荐在Activity中直接编写界面，通常是在布局文件中编写界面，然后在Activity中将布局文件引入进来   
图片资源一般放在drawable-xxhdpi即可，这是主流的设备分辨率目录       
<br>








___
# C2 Kotlin基础
### 变量：
Kotlin通过一个特殊的编译器将代码转为class文件然后交由JVM虚拟机运行
Kotlin抛弃了基本数据类型，全部使用了对象数据类型
尽量将变量申明为val，val只是对象引用不可变，对象本身的改变并不限制
### 函数：
函数格式：`fun 函数名（参数列表）：返回值类型{函数体}`   
当函数体内只有一行代码时，可以将{}省略，然后用=将函数声明与函数体进行连接（同时可以省略返回值类型，返回值类型会被自动推导）   
### 逻辑控制：
if：与Java基本一样，只是增加了返回值的功能，条件分支的最后一行将会被作为返回值返回
when：类似switch，格式：when（参数）{ 匹配值 ->{执行逻辑} }，同样具有返回值的功能，也可以用来进行类型匹配，也可以不带参数，将判断的表达式完整地写在when的结构体中
### 循环：
while：与Java一样
for：for-i被抛弃，for-each被加强
* 关键字：..：闭区间，until：左闭右开，step：在循环内递增的增量，downTo，降序闭区间，
### OO
* 在class前加上open关键字，使类可以被继承    
* 继承方式:`class A:superclass()`
* 主构造函数：每个类默认存在一个不带参数的主构造函数，也可以指定参数，没有结构体，直接定义在类名的后面即可     
* 主构造函数的逻辑：写在init代码块里即可
* 子类的构造函数必须调用父类的构造函数，继承时父类名后的括号内没内容的话表明的就是调用父类的无参构造函数    
* 次构造函数：一般情况下通过给主构造函数设立默认值的方式可以避免使用次构造函数，次构造函数必须调用主构造函数，次构造函数使用`constructor`关键字来定义
* 接口：实现接口时不需要加（），而且允许接口内的函数有默认实现（JDK1.8后Java也支持），子类不实现默认实现的方法的话，该方法会使用默认逻辑
* 修饰符：抛弃default，加上了internal（同一模块中的类可见），默认使用的是public
* 数据类：data class，自动加上toString等方法
* 单例类：class关键字改为object即可，调用方式类似与Java静态方法的调用

### Lambda编程：
Lambda表达式语法结构：`{参数名1：参数类型，参数名2：参数类型 ->函数体}`，`->`表示参数列表的结束，即左边为参数列表，右边为函数体,函数体最后一行代码会自动作为表达式的返回值，    
参数列表中只有一个参数时，可以不用声明参数名，直接使用`it`来代替    





kotlin完全舍弃new关键字，因此创建匿名类实例的时候需要使用`object：`关键字  
### 空指针检查：
kotlin将空指针异常的检查提前到编译时期    
如果变量或参数可为空，则需在声明时在类名后加上可空声明符`?`    
### 判空辅助工具：
* `?.`：当对象不为空正常调用相应的方法，为空则什么都不做
* `?:`：左右两边都接收一个表达式，左边表达式结果不为空就返回左边的结果，否则返回右边的结果
### 函数参数
* 参数默认值尽量配合键值对传参的形式来使用    

#回调：回调函数的简称，指一段以参数形式传递给其他代码的可执行代码，该函数编写方是A，但是调用方是B，A调用了B的一个函数F1，调用时将回调函数F2作为参数传递给F1，然后B执行F1，执行完成后将结果传给F2，然后执行F2

<br>







# C3 Activity活动 && Intent意图
## Activity：
Activity的启动和组合方式是Android平台应用模型的基本组成部分，Android系统通过调用各Activity实例中对应各生命周期的回调方法来运行Activity实例中的代码    
Activity可以看作Android应用重新的最小单元，一个完整的应用程序通常包含多个Activity，其中有一个为主Activity，通常情况下是用户启动应用的第一个界面，但用户与应用的互动开始点可以是其他Activity，应用也可以调用其他应用的某一个Activity，而不是每次都从主Activity启动    

[生命周期](![img.png](img.png))：
* onCreate：Activity第一次被创建时调用，一般用来执行Activity的初始化操作
* onStart：Activity由不可见变为可见时调用
* onResume：准备好与用户进行交互时调用
* onPause：系统准备启动或恢复另一个Activity时调用
* onStop：Activity完全不可见时调用
* onDestroy：Activity被销毁前调用
* onRestart：Activity由停止状态变为运行状态前调用
* onSaveInstanceState：携带Bundle类型的参数，采用键值对的形式来保存数据，该方法会在Activity被回收前调用，而oncreate参数中的Bundle会带有回收前保存的数据       
Activity启动模式，清单文件内指定：
  * standard：默认模式，在栈顶入栈一个新的Activity实例，栈内是否已纯在
  * singleTop：栈顶如果是该Activity的实例，就直接使用，否则重新入栈新的实例
  * singleTask：栈内有已纯在的实例就直接使用并将上面的全部出栈，没有的话再新建实例
  * singleInstance：启用新的返回栈管理该Activity的实例，通常用于与其他程序共享的Activity，这样不同的程序在访问该Activity时，公用同一个返回栈



## Intent:
各组件间进行交互的一种载体
用来启动其他组件的方式通常有两种：
* 显示启动：代码里直接构造，指明意图
* 隐式启动：配合IntentFliter使用，各控件指明响应的意图信息（一个action，多个category，data用来指定响应的数据），代码中构造Intent时只指定信息，系统会通过构造的intent对象里包含的信息，寻找到与信息适配的控件，然后启动         

向其他控件传递数据：
* 使用一系列putExtra（）向下一个组件传递，数据使用键值对形式储存    
* 向上一个Avtivity返回数据：使用registerForActivityResult（API29后，之前使用startActivityForResult，29后弃用）    
  
  <br>

## C3 kotlin：
### 标准函数：
* `let`：在对象上调用，将调用对象本身传递进lambda表达式中
* `with`：接收两个参数，第一个为任意类型的对象，第二个为Lambda表达式，该函数会在Lambda表达式中提供第一个参数的上下文，并使用Lambda表达式的最后一行代码作为返回值，常用于连续调用同一对象的多个方法
* `run`：接收一个Lambda参数，在某个对象上调用，在Lambda表达式中提供调用对象的上下文，同样使用最后一行作为返回值返回
* `apply`：接收调用同上，只是返回对象本身，适用于在一段代码内需要对某一对象多次处理的情况
### 静态方法：
使用上，工具类直接使用单例类来干，其他情况下将函数放入`companion object{}`内，实现类似与Java的形式       
也可以使用顶层方法的形式，对于顶层方法：kotlin内直接调用，Java中静态方法形式       

<br>









# C4 UI与控件
适配相关：
* px：像素，屏幕上的实际像素点，每个px对应屏幕上的一个点
* dp：也叫dip，设备独立像素，一种基于屏幕密度的抽象单位，在每英寸160像素点的显示器上，1dp=1px，随着屏幕密度的改变，dp与px的换算也会发生改变
* sp：主要用来处理字体的大小,可以根据用户的字体大小首选项进行缩放，即当控件文字大小指定为sp时，当系统文字大小进行更改后，控件文字大小也会随之改变
换算关系：
  px与dp换算：
  px = dp*（density/160），density为屏幕密度，density=dpi/160，dpi（像素密度），表示每英寸距离屏幕中有多少个像素点
常用控件：
* TextView：用来在界面上显示一段文本信息
* Button:通过设立监听器，实现点击事件的一个控件，可以看作是带点击功能的TextView
* EditText：允许用户在控件里输入和编辑内容，并可以在程序里对这些内容进行处理
* ImageView：用于在界面上展示图片的一个控件
* ProgressBar：在界面上展示一个进度条
* AlterDialog：在当前界面弹出一个对话框，该对话框置顶于所有界面元素之上，可以屏蔽其他控件的交互能力     
* ListView（推荐使用RecyclerView）：提供一个列表来展示大量数据，比如音乐播放器的歌曲列表，可以通过自定义子项来实现复杂的界面
___
##### RecyclerView的使用：一个动态列表，用来显示大量数据，开发者提供数据并定义每个列表项的外观，交由库根据需要动态创建元素
[示例程序](https://github.com/android/views-widgets-samples/tree/main/RecyclerViewKotlin/)

关键类：
* RecyclerView：控件，包含与数据对应视图的ViewGroup
* ViewHolder：列表中每个独立元素都由ViewHolder对象进行定义，创建ViewHolder时，没有关联任何数据，当创建后，RecyclerView会将其绑定到对应的数据，通常扩展RecyclerView.ViewHolder来实现定义
* Adapter： RecyclerView会请求视图，然后在Adapter中调用方法，将视图绑定到数据上，通常使用RecyclerView.Adapter来定义
* 布局管理器:负责排列列表中的各个元素，可以使用自带，也可以自定义自己的布局管理器，布局管理器均继承于LayouyManager抽象类
  ListView的布局排列是由自身去管理的，RecyclerView的布局排列交给了LayoutManager，通过继承LayoutManager来实现接口进行扩展，就可以定制RecyclerView的布局排列了

点击事件：不同于ListView的子项点击，RecyclerView的点击交由具体的View去注册

常用布局：
* LinearLayout：线性布局，将包含的控件在线性方向上依次排列
* RelativeLayout：相对布局，通过相对定位的方式让控件出现在布局的任何位置
* FrameLayout：帧布局，所有控件默认摆放在布局左上角，
* ConstraintsLayout：约束布局，通过给控件添加约束条件来确定位置(推荐)        

___

## C4 kotlin：
延迟初始化：通常在全局变量上使用，使用`lateinit`关键字，表明该变量的初始化会在稍后进行，这样在其他地方使用时就不需要再进行判空处理，但是一定要确保在使用前进行初始话，否则会抛出异常       

密封类(可以看作枚举的一种扩展)：一种受限的类继承结构，该类只能有几种子类去继承它，密封类与其所有子类只能定义在同一文件的顶层位置，可以配合when使用，
当when条件传入一个密封类变量为条件时，编译器会检查该类有哪些子类，并强制要求将该类的全部子类对应条件进行处理，否则编译不通过


<br>








# C5 [Fragment碎片](https://developer.android.google.cn/guide/fragments?hl=zh-cn) 
碎片,表示应用界面中可重复使用的一部分，可以定义和管理自己的布局，拥有自己的生命周期，也可以处理自己的输入事件，Fragment不能独立存在，必须由Activity或另一个Fragment托管，Fragment的视图层次结构会成为宿主的视图层次结构的一部分，或附加到宿主的视图层次结构       
## C5 kotlin：扩展函数与运算符重载
扩展函数：在不修改某个类源码的情况下，向该类添加新的函数，最好定义为顶层方法，以便函数拥有全局的访问域
语法：`fun 类名.方法名(参数1:类型,参数2:类型)：返回值{函数体}`
运算符重载：在指定函数（运算符对应的函数）的前面添加`operator`关键字,然后编写逻辑       

<br><br/>









# C6 广播与广播接收器(broadcastsreciver)
广播：借助Intent来发送        
* 标准广播：异步，不可被截断，发出后，所有的接收器几乎会在同一时刻收到该消息
* 有序广播：同步，可被截断，同一时刻只有一个接收器会收到该消息，并且可以选择是否将其截断
       
隐式广播：为指明发送给哪个程序的广播，大多数系统广播都属于隐式广播，8.0后，所有隐式广播都不允许使用静态注册的方式来接收       

接收器注册：给接收器注册感兴趣的广播，当收到时在接收器内部进行逻辑处理       
* 静态注册：在清单文件内注册，隐式广播（未指明发送给哪个程序的广播）不允许通过该方式来注册，使用方式需要再清单文件内添加IntentFilter
* 动态注册：在代码中注册，通过构造包含广播action的intentFilter，然后通过registerReceiver注册接收器，实现动态注册，动态注册的接收器必须取消注册，缺点是程序必须启动才能接收到广播
涉及到权限：一些敏感的权限必须在清单文件内进行声明，6.0及以上还需进行运行时权限检查   
       

***需要注意的是：接收器内是不允许开启线程的，当接收器的onReceive内执行了耗时操作时，会导致ANR的出现***

发送广播：
* 无序：使用Intent来发送，首先构建Intent对象，然后传入广播，接着需要调用`setPackageName`方法来指定广播是发送给哪个应用程序，避免该广播成为隐式广播，然后调用sendBroadcast   
* 有序：构造方式同上，发送使用sendOrderdBroadcast，在接收器注册时指定优先级，如果要截断，在onreceive内进行截断（调用abortBroadcast）

## C6 高阶函数
高阶函数定义：一个接收函数作为参数或返回值是另一个函数的函数    

在Kotlin中，同整型、浮点型一样，函数有其专门的类型：函数类型，函数类型的定义语法基本规则为：`(参数1类型，参数2类型...) -> 返回值类型`，`->`左边声明函数接收什么样的参数，右边声明函数返回什么样的类型，没有返回值的话使用Unit       

Lambda表达式是最常见也是最普遍的高阶函数调用方式       

完整高阶函数定义：应在函数类型的前面加上`类名.`，表示该函数类型是定义在哪个类中的   

Lambda表达式在底层被转换成了匿名类的实现方式，这就表明每调用一次Lambda表达式，都会产生一个新的匿名类实例，会造成额外的开销，为了解决这个问题，在定义高阶函数时尽量使用内联函数的形式    

语法：在fun关键字前加上`inline`关键字即可，编译器会将内联函数中的代码在编译时替换到调用它的地方，这样就不会产生额外的开销

如果只想内联参数中其中的一个，那么在参数名前加上`noinline`即可，表明该表达式不需要内联，内联的函数类型在编译时会被进行代码替换，因此其是没有真正的参数属性的，
非内联的函数类型可以自由地传递给其他任何函数，因为其就是一个真实的参数，而内联的函数类型参数只允许传递给另外一个内联函数，这是内联函数最大的局限性
内联函数所引用的Lambda表达式中是可以使用return来进行函数返回的，而非内联只能进行局部返回
 

crossinline？？？      保证在内联函数的Lambda表达式中一定不会出现return






# C7 数据持久化相关
数据持久化：将内存中的瞬时数据保存到存储设备中，保证数据不会丢失，以便下次使用       
持久化技术：一种让数据在瞬时状态和持久状态间进行转换的机制       
Android持久化主要是三种方式实现：文件存储、SharedPreferences和数据库存储

---
文件存储：最基本的方式，不对内容进行处理，所有数据都是原封不动的保存到文件中，要是想用来储存较为复杂的结构化数据，需要定义一套自己的格式规范，方便将数据从文件中重新解析出来
存储：       
使用Context类中的openFileOutput（）方法，该方法接收两个参数，第一个为文件名，不能包括路径，因为所有的文件都默认存储到`/data/data/<package name>/files/`下，第二个为文件的操作模式
，一个是覆盖式写入（private），另一个是追加式写入（append），该方法返回的是一个`FileOutputStream`对象，得到该对象后以Java流形式写入
       
读取：
使用openFileOutput（），只接收一个参数，即要读取的文件名，系统会自动到`/data/data/<package name>/files/`目录下加载该文件，并返回一个FileInputStream对象，得到该对象后以Java流形式读取数据       
___

SharedPreferences存储：使用键值对的形式来存储数据,本质是xml实现       
获得SharedPreferences对象：
* Context类中的`getSharedPreferences()`方法：该方法接收两个参数，第一个用于指定SharedPreferences文件的名称，如果不存在则会创建一个，SharedPreferences文件都是存放在
  `data/data/<package name>/shared_prefs`目录下，第二个参数用来指定操作模式，目前只能指定MODE_PRIVATE一种，表示只有当前的应用程序才可对该文件进行读写       
* Activity类中的`getPreferences()`方法：该方法只接收一个参数来指定操作模式，该方法会自动将当前Activity的类名作为SharedPreferences文件的文件名       
得到对象后存储数据：    
* 调用SharedPreferences对象的`edit()`方法获取一个`SharedPreferences.Editor`对象
* 向Edit对象中添加数据，使用各`put()`方法
* 调用`apply()`方法将添加的数据提交，完成数据存储操作，该方法是异步操作，先将操作提交到内存，后异步提交到磁盘，也可以使用`commit()`，该方法会返回一个布尔表示是否提交成功，而且该方法会同步提交磁盘
读取SharedPreference内的数据：        
取得SharedPreference对象，用各get方法取出数据,get时需要指定一个默认值，如果没有取得相应的值，会使用默认值来代替     
   
___

***SQLite数据库存储***
SQLite:一款轻量级的关系型数据库，支持标准的SQL语法，还遵循了数据库的ACID事务，该数据库内嵌于Android系统中       
SQLite的作用与目的：缓存应用所需要的数据，同文件直接存储和SharedPreference一样，都是作应用的一个数据仓库，只不过SQLite是使用数据库来结构化管理数据
### 创建数据库：    
Android提供了一个`SQLiteOpenHelper`帮助类来管理数据库，***该类为抽象类***，使用时需自定义类继承实现该类，必须重写onCreate和onUpgrade
两方法，在这两个方法中实现创建和升级数据库的逻辑，
该类还有两个实例方法`getReadableDatabase`和`getWritableDatabase`，这两方法都可以创建或打开一个现有的数据库（存在就打开，不存在就创建一个新的）并返回一个可对数据库进行读写操作
的对象，区别在于当数据库不可写入的时候（如磁盘空间已满），getReadableDatabase返回的对象将以只读形式打开数据库，writable会直接出现异常       
SQLiteOpenHelper中有两个构造方法可以重写，一般重写参数少的那个，该方法接收4个参数
* 1. Context：
* 2. 数据库名：创建数据库时使用该名称
* 3. 查询数据时返回一个自定义的Cursor：一般传入null
* 4. 表示当前数据库版本号，一般用来对数据库执行升级操作       
构造出SQLiteOpenHelper实例后，再调用该实例的`getReadableDatabase`或`getWritableDatabase`方法就可以创建数据库了，数据库文件会存放在`/data/data/<package name>/database/`目录下，此时，重写的onCreate方法也会得到执行，通常在这里执行一些建表的逻辑     


SQLite内的数据类型：
* integer：整型
* real：浮点
* text：文本
* blob：二进制              

### 添加数据：       
CRUD:对应4种操作：CREATE（添加）,RETRIEVE（查询）,UPDATE（更新）,DELETE（删除）
操作:
1. 借助Helper返回的`SQLiteDatabase`对象       
* 添加数据：`SQLiteDatabase中的insert()`方法，该方法接收3个参数，第一个是表名，第二个参数用来在未指定添加数据的情况下给某些可为空的列自动赋值NULL，
           第三个为一个ContentValues对象，它提供了一系列put方法重载，用来向ContentValues中添加数据，只需要将表中的每个列名以及相应的待添加数据传入即可       
* 更新数据：`update（）`方法，该方法接收4个参数，第一个为表名，第二个为ContentValues，第三，第四用于约束更新某一行或某几行的数据，不指定的话默认更新所有行      
* 删除数据：`delete（）`方法，该方法接收3个参数，第一个为表名，第二第三用于约束删除某一行或某几行的数据，不指定默认删除所有行
* 查询数据：`query（）`方法，该方法最少接收7个参数，第一个为表名，第二个用来指定查询哪几列，不指定默认查询所有列，第三第四用于约束查询某一行或某几行的数据，不指定默认查询所有行，
第五个用于指定需要去group by的列，不指定则不对查询结果进行group by操作，第六个用于对group by之后的数据进行进一步过滤，不指定则不过滤，第七个用于指定查询结果的排列方式，不指定则使用默认排序方
  式，该方法返回一个Cursor对象，查询到的所有数据都将从这个对象中取出
  
2. 使用SQL来进行操作：
* 查询数据使用SQLiteDatabase对象的rawQuery（）方法
* 其他操作使用execSQL（）方法       

***对数据库的操作现在推荐使用`Room`框架来进行***
___

## C7 高阶函数应用
***牢记高阶函数的定义和函数类型的定义，以此进行运用***

___

# C8 ContentProvider 内容提供器:跨程序分享数据的实现方式,主要用来管理结构化方式存放的数据
使用场景：1.访问其他程序暴露出来的数据   2.构建提供器，向其他程序暴露自己的数据
## ContentProvider:
第七章中的三种方式（文件，SharedPreferences，sqlite数据库）所保存的数据只能供本程序访问使用，如果需要进行跨程序数据共享，Android提供了ContentProvider来实现不同程序间的数据共享       
ContentProvider：该组件的作用是提供一种机制在不同的应用程序间实现数据共享的同时保证被访问数据的安全性，目前该方式是Android实现跨程序共享数据的标准方式，该组件可以选择只共享一部分数据，保证了隐私数据的安全性

当一个程序需要将其数据暴露给其他程序使用时，该程序通过通过`ContentProvider`来实现数据暴露，而其他程序通过`ContentResolver`来操作ContentProvider暴露出来的数据
当程序暴露出数据后，不论其本身是否启动，其他程序都去操作其暴露出来的数据



### 运行时权限：
6.0中引入，使用户不需要在安装时一次性授予所有申请的权限，而是可以在软件运行过程中对某一项权限申请进行授权，即使拒绝，不会影响需要该权限功能外的其他功能的使用，Android中权限大致分为两类       
* 普通权限：不会直接威胁用户安全和隐私的权限，该类权限申请系统会自动授权，不需要用户手动授权，在清单文件内声明即可
* 危险权限：可能触及到用户隐私或对设备安全性造成影响的权限，该类权限必须由用户手动授予，否则无法使用相应功能，共11组30个权限              

在进行运行时权限处理时使用的是权限名，原则上，用户同意了某个权限申请后，同组的其他权限也会被系统自动授权       
运行时权限检查的步骤：(在Activity中进行)
* 检查是否已经授权：使用ContextCompat.checkSelfPermission()方法，该方法接收两个参数，第一个为context，第二个为具体的权限名，判断该函数的返回值是否为PackageManager.PERMISSION_GRANTED，是的话说明已经授权，否则没有授权
* 进行下一步：1.已授权：执行逻辑 2.没授权：调用ActivityCompat.requestPermissions()方法向用户申请权限，该方法接收三个参数，第一个为Activity的实例，第二个为一个String数组，包含要申请的权限名，第三个为请求码，确保唯一即可
* 当调用完ActivityCompat.requestPermissions()方法后，系统会回调到onRequestPermissionsResult()方法中，授权结果会封装在grantResults参数中，开发者需重写该方法，根据授权结果进行下一步处理

### ContentProvider:
该组件的使用方式一般有两种：
* 1. 使用现有的ContentProvider读取和操作相应程序中的数据(一般用来获取系统应用的数据),即通过暴露的接口获取其他程序的数据
* 2. 创建自己的ContentProvider，给程序的数据提供外部访问接口，即暴露接口给其他程序，让其他程序访问自己的数据      
  
#### 使用现有的ContentProvider：
如果一个应用程序通过ContentProvider对其数据提供了外部访问接口，那么任何其他的应用程序都可以对该部分数据进行访问，系统自带的通讯录、短信等程序都提供了类似的访问接口，
程序想访问ContentProvider中共享的数据，需借助`ContentResolver`类来实现,
可以通过Context中的getContentResolver（）方法取得该类的实例，该类中提供了一系列的方法用来对数据进行增删改查，insert、update、delete、query，这几个方法接收的都是一个Uri参数
       
Uri：内容URI，该参数为ContentProvider中的数据建立了唯一标识符，主要由两部分组成：authority和path，authority用来区分不同的应用程序，一般使用应用包名，path则是对同一程序中不同的表做区分的，通常加在authority的后面，同时头部需要添加协议声明    
标准格式：content://com.example.app.provider/table       

***思路：***       
* 访问其他程序提供的数据：获得该程序提供的内容URI，然后借助ContentResolver进行增删改查即可

#### 创建自己的ContentProvider：
继承`ContentProvider`并重写其中的6个抽象方法       
在这些方法内，第一个参数往往是一个Uri对象，该参数表明了调用方期待访问的表和数据，该参数一般有两种类型：
1. 标准的一个Uri格式，表示希望访问表内所有数据
2. 在标准格式后加上id，表示访问表中id的数据
通常使用通配符来匹配：
* “*” 表示匹配任意长度的任意字符
* “#”表示匹配任意长度的数字
所以，一个能匹配任意表的内容Uri格式可以为：content://com.example.app.provider/*
所以，一个能匹配表a中任一行数据的内容Uri格式可以为：content://com.example.app.provider/a/#
接着借助`UriMatcher`类，该类提供了一个`addURI()`的方法，该方法接收3个参数，可以分别把authority、path、和一个自定义代码传入，
这样，当调用UriMatcher的match方法时，就可以将一个Uri对象传入，返回值是某个能匹配这个Uri对象的自定义代码，利用这个代码就可以判断出调用方希望访问的是哪张表中的数据了


  
  
## C8 Kt 泛型和委托:
泛型：一般情况下，每个变量都有其具体的类型，泛型的出现使得我们在不具体指定类型的情况下进行编程，提升了代码的扩展性       
基本使用方法：
* 泛型类：`class Class<T>{fun method(param:T):T{}}`
* 泛型方法：`fun <T> method(para:T):T{}`

委托：一种设计模式，理念是操作对象自己不会处理某段逻辑，而是将工作委托给另一个辅助对象去处理
kotlin中分为类委托和属性委托
* 类委托：将一个类的具体实现委托给另一个类去完成
* 属性委托：将一个属性(字段)的具体实现委托给另一个类去完成









---     



# C9 多媒体
## C9 Android:
### 通知：一般在程序进入后台时使用       
通知渠道：8.0引入，每条通知都要属于一个渠道，应用程序自由创建当前应用拥有的通知渠道，但是渠道的控制权交由用户，用户自由控制这些渠道的重要程度，是否响铃、是否震动以及该渠道通知是否开启，
开发者划分自己程序的所有通知渠道并将通知分类进各对应的渠道       
使用方式：
* 1. 获得通知管理器的实例，使用系统提供实例即可
* 2. 创建通知渠道（8.0及以上）
* 3. 构造通知对象，构造时需指定渠道   
* 4. 调用管理器的notify方法，将通知进行发送         
  
PendingIntent：简单点说就是一个等待合适时机执行的Intent，通常用来给通知加上点击操作       

在构造通知对象时，将setContenttext换为setStyle，可以是实现在通知里嵌入长文本、图片等       


### 调用摄像头和相册:




#### Activity Results API：官方推荐获取Activity、Fragment获取返回数据的方式，用来替代startactivityForResult
* ActivityResultContract：协议，定义了如何传递数据和如何处理返回的数据，一个抽象类，需要继承来创建自己的协议，每个ActivityResultContract都需要定义输入和输出类型
* ActivityResultLauncher：启动器，调用ActivityResult的Launch方法来启动页面跳转，作用相当于原来的startActivity       


### 播放多媒体文件，音频和视频




## C9 Kotlin:
infix函数：kotlin中的一个语法糖，使用该语法糖，可以调整函数调用的语法规则，更接近英文语法，例如：`A.to(B)`可以转为`A to B`       

使用限制：必须同时满足以下两点才能使用
1. infix函数不能定义为顶层函数，必须是某个类的成员函数，可以使用扩展函数的方式将它定义到某个类中
2. infix函数必须且仅接收一个参数，参数类型没有限制







            

# C10 Service： 
Android实现程序后台运行的解决方案，用来执行不需要和用户交互且需要长期执行的任务，Service不依赖于任何用户界面，依赖于创建Service时所在的进程，该组件不会自动开启线程，默认运行于主线程，因此，耗时任务需要手动开启线程
  
## 异步消息处理机制：
Android中异步消息机制主要由4部分组成：Message、Handler、MessageQueue、Looper
* Message：线程间传递的消息对象，内部可以携带少量信息，用来在不同的线程间传递数据
* Handler：处理者，用来发送和处理消息，发送使用Handler中的`sendMessage（）`、`post（）`等方法，发送出的消息经过处理后，会回到Handler的`handleMessage（）`中
* MessageQueue：消息队列，用来存放所有通过Handler发送的消息，该部分消息会一直存在与消息队列中，等待被处理，每个线程中只会有一个MessageQueue对象
* Looper：相当于每个线程中MessageQueue的管家，调用Looper的`loop`方法后，会进入一个死循环，当MessageQueue中存在一条消息时，会将其取出并传递到handleMessage方法中，每个线程中只会存在一个Looper对象


## AsyncTask:11之后废弃，官方推荐使用协程来代替


### Service：（重点在于绑定服务）
8.0开始，只有应用保持在可见状态下，Service才可稳定运行，应用进入后台时，随时可能被回收，如果需要一些任务保持在后台运行，可以使用前台Service或WorkManager

Android中启动Service有两种方式：
1. 通过Context的startService（）：该方法启动，访问者与Service之间没有关联，即使访问者退出，Service也任然运行
2. 通过Context的bindService（）：该方法启动，访问者与Service绑定在一起，访问者一旦退出，Service也会终止


Activity内通过`startService()`启动的服务在启动Activity是没有控制权的，service启动后就与activity无关
如果Activity要与Service进行交互，需要使用`bindService()`将服务与活动进行绑定，该方法接收三个参数
1. intent对象，用来指定要启动的Service
2. 一个`serviceConnection`对象，用于接听访问者（绑定者）与Service之间的连接情况，
当访问者与Service之间连接成功时将回调该对象内`onServiceConnected()`方法，当宿主进程终止导致Service与访问者之间断开连接时，回调`onServiceDisconnected`方法（使用unbindService断开连接时，该方法并不会被调用）
3. 一个标记位（Int），用来标记绑定时是否自动创建Service对象
        

ServiceConnection对象的onServiceConnected方法中有一个IBinder对象，该对象用来实现与被绑定Service的通信
当开发Service时，该Service必须提供一个onBind方法，在绑定本地Service的情况下，onBind方法返回的Ibinder对象会传给ServiceConnection对象内的onServiceConnected方法中的service参数（上面第二个），
这样访问者就可以使用该对象与Service进行通信
对于Service的onBind方法所返回的IBinder对象，可以理解为Service组件返回的代理对象，Service允许通过该对象来访问Service内部的数据，这样就可以实现Activity与Service之间的通信

### Service生命周期：
不同的启动方式对应的生命周期不同：
* startService启动：如果该service之前未被创建，会先调用onCreate，之后调用onStartCommand，如果之前已经被创建，则不会调用onCreate，就算onStartCommand多次执行，每个Service只会存在一个实例，直到外部stopService执行
* bindService启动：之前未被创建，同样先执行onCreate然后执行onBind，如果连接未断开，Service会一直保持运行状态

### 前台Service：类似通知的使用方式，可以保证程序被退出时service仍在运行 

### IntentService(11 后废弃，推荐使用WorkManager或JonIntentService代替)
由于Service中的代码默认运行在主线程上，为了避免ANR，需要在服务中另开线程进行耗时处理，但这样会导致必须手动去关闭，否则Service会一直处于运行状态
为了更好的创建一个异步的、会自动停止的Service，Android提供了一个IntentService类,该类的服务在启动时会自动开启线程执行`onHandleIntent`中的代码，执行完毕后会自动关闭服务



## 泛型 KT special

### 泛型实化：
Java类型擦除：泛型对类型的约束只存在于编译期，对于`List<T>`运行时T的实际类型是被擦除的，JVM只知道其是一个List对象，但并不知道其本来只允许包含那种类型的元素，也就是在运行期间是无法知道T的实际类型的       
所有基于JVM的语言的泛型功能都是通过类型擦除实现的，但是kotlin提供了内联函数，内联函数中的代码在编译时会自动替换到调用它的地方，这样就不存在泛型擦除的问题了，代码会在编译后直接使用实际的类型来替代内联函数中的泛型声明
也就是说，Kotlin中是可以将内联函数中的泛型进行实化的      

使用限制：
* 函数必须是内联函数
* 在声明泛型的地方必须加上reified关键字来表示该泛型要进行实化       

语法：`inline fun <reified T> getDenericType(){}`

应用：通过泛型实化和高阶函数实现启动Activity等的简化(C10.kotlins.reifieds)

### 协变和逆变,核心在于A与B有继承关系，那么通过协逆变使其以它们为参数的泛型类也建立联系
***约定：一个泛型类或泛型接口中的方法，参数列表是接收数据的地方，因此称为in位置，而返回值是输出数据的地方，称为out位置`fun method(para:T(in位置)):T(out位置){}`***

协变的定义：如果定义了一个`MyClass<T>`的泛型类，其中`A`是`B`的子类，同时`MyClass<A>`又是`MyClass<B>`的子类，那么`MyClass`在`T`这个类型上是协变的

如果一个泛型类在泛型上的数据是只读的话，则其是没有类型转换安全隐患的，而要实现这一点的话，则需要让`MyClass<T>`类中所有方法都不能接收`T`类型的参数，换句话说，`T`只能出现在`out`位置上，不能出现在`in`位置上

逆变的定义：如果定义了一个`MyClass<T>`的泛型类，其中`A`是`B`的子类，同时`MyClass<B>`又是`MyClass<A>`的子类，那么`MyClass`在`T`这个类型上是逆变的

逆变的`T`位置与协变相反



___
# C11 网络技术

核心点：
* 通过HTTP进行网络交互：两种方式：HttpURLConnection和OkHttp（Retrofit是OkHttp的封装）
* 数据解析方式：XML和JSON，这是现在主流的数据载体方式
* 回调机制和Retrofit：Re是现在主流的网络库
* Kotlin的协程（轻量化线程）



### WebView:内置浏览器的控件，可以在控件内实现简单的浏览器功能

### HTTP（超文本传输协议）访问网络：
工作原理（简单概括）：客户端向服务端发出一条请求，服务端收到请求后返回数据给客户端，客户端然后处理返回的数据

#### HttpURLConnection方式（原生）： 
使用：
1. 获取HttpURLConnection实例：`val connection = url.openConnection() as HttpURLConnection`
2. 设置请求使用方法：GET：从服务器获取数据，POST：向服务器提供数据 `connection.requestMethod = `
3. 设置附加信息，如连接超时、读取超时等`connection. ...`
4. 调用connection的输入流，得到服务器返回数据的输入流，对数据进行处理 `val input = connection.inputStream`
5. 关闭HTTP连接`connection.disconnect()`

#### OkHttp方式:
使用：
1. 创建OkHttpClient实例：`val client = OkHttpClient()`
2. 发起GET请求：创建一个空Request对象：`val request = Request.Builder().build()`，在build前连缀其他方法来构建完整的Request对象
3. 调用`newCall()`方法来创建一个Call对象并调用其`execute()`来发送请求并获取返回数据`val response = client.newCall(request).execute()`,返回的是一个Response对象，里面封装了服务器返回的数据,需要对该对象进行处理
4. 发起POST请求：构建一个RequestBody对象来存放待提交的参数`val requestBody = FormBody.Builder().add()... .build()`
5. 向request对象中添加RequestBody，
6. 之后同GET请求相同

#### 数据载体：

##### XML(可扩展标记语言)：
常用解析方式：
* PULL（官方推荐）：基于流的形式，读取节点事件然后回调开发者编写的逻辑
* SAX：也是基于流的形式
* DOM：一次性将xml全部加载进内存并映射为树结构对象

##### JSON(JavaScript Object Notation，JS对象标记)
常用解析方式：
* JSONObject：官方提供，
* GSON：Google的一个开源库，自动将JSON对象映射为使用语言对象

#### 网络请求回调形式
通常情况下，一个程序需要在不同地方使用网络功能，而发送请求的代码基本是相同的，因此，应该将通用的网络操作提取到一个公共类中并提供通用方法，当发起网络请求时，调用该方法即可

回调函数：一段以参数形式传递给其他代码的可执行代码
假设我们要执行某项任务，该任务需要依赖某服务S，那么该任务可以分为PA（调用S前部分），PB（调用S后部分）
常规模式：PA、PB由服务方执行，也就是PA执行完后，等S执行完后，再去执行PB，即：调用完S后我去执行PB
回调模式：服务方执行PA，然后将PB交给服务S，即告诉服务方执行完S然后去执行PB，即：调用完S后你接着执行PB  


***不论HttpURLConnection还是OkHttp，最终回调接口都是运行在子线程中的***

#### Retrofit：基于OkHttp进一步开发出来的应用层网络通信库
设计思想：
* 同一应用中发起的网络请求绝大多数指向的是同一个服务器域名
* 服务器提供的接口通常是可以根据功能来分类的，将服务器接口合理归类能让代码结构变得更加合理
* 开发者习惯于调用一个接口，获取其返回值这样的编码方式，即不关心网络的具体通信细节，
Retrofit基于以上几点来设计
* 首先可以配置一个根目录，然后在指定接口地址时使用相对路径即可
* 其次，Retrofit允许对服务器接口进行分类，将功能同属一类的服务器接口定义到同一接口文件中
* 再者，也不需要关注网络通信的细节，只需要在接口文件中声明一系列方法和返回值，然后通过注解的方式指定该方法对应哪个服务器接口，以及需要哪些参数，这样当调用该方法时，
Retrofit会自动向对应的服务器接口发起请求并将响应的数据解析为返回值声明的类型       

通常情况下，Retrofit的接口文件以具体功能种类名开头，并以Service结尾       

大致使用步骤：
1. 使用`Retrofit.Builder`来构建一个`Retrofit`对象，
2. 调用retrofit对象的create方法，传入具体Service接口对应类型，创建一个该接口的动态代理对象
3. 通过该动态代理对象来执行各种操作

***当发起请求时，Retrofit会自动在内部开启子线程，当数据回调到Callback中之后，Retrofit又会自动切换回主线程***
### Android网络交互方式：
* HTTP连接：请求-响应模式，客户端向后端发起请求，后端解析请求，然后返回数据，客户端解析返回的数据
* socket连接：长连接模式，相当于在客户端和服务器端建立一条双向通道，
### 网络请求方式
* 使用HttpURLConnection：原生方式
* 使用OkHttp：第三方（包括Retrofit）


OkHttp使用方式：
* 获取`OkHttpClient()`对象
* 构建`Request`对象
* 使用`OkHttpClient()`对象的`newCall()`方法将请求执行并获取返回的数据对象
* 处理返回的数据对象

***9.0后应用程序默认只允许使用HTTPS类型的网络请求，HTTP默认不再支持，需要在清单文件内进行额外配置***
### 数据媒介：
* xml：常用解析方式：1.pull解析(流 逐行读取) 2.SAX解析(流 逐行读取) 3.DOM解析(一次性加载进内存形成树状结构)
* JSON：常用解析方式：1.JSONObject，原生 2.借用GSON来进行解析(将JSON格式的字符串自动映射为对象)


### Retrofit与回调机制
* Retrofit：最常用的网络库，基于OkHttp，也可以结合RxJava来使用 
* 回调机制：因为网络请求属于耗时操作（虽然一般用不了多长时间），如果在UI线程内执行网络操作，容易引发ANR，所以借用回调机制来规避


### 协程（可以看作轻量级线程,官方首推的异步解决方案）
Kt提供的一种并发非阻塞的方法
可挂起函数：执行可以暂停和恢复的函数，Kt中有专门的数据结构来存放函数的内部状态，以便稍后继续执行

* delay():将当前执行的任务暂停指定的毫秒数
* yield():相当于降低该协程的优先级

应用场景：
假设有多个不能并行运行的任务，顺序运行这些任务可能会导致除了少数任务之外的所有任务都无法完成，如果任务长时间运行或永不结束，则顺序执行是不可取的，这时可以考虑使用协程让多个任务协同运行，并在所有的任务上取得稳定的进展。
  



























# C12 Material Design



# C13 Jetpack
2018年谷歌推出的一个开发组件工具集，其内部的组件多数不依赖于任何Android系统版本，通常定义在AndroidX库中，该工具集主要由基础、架构、行为、界面4个部分组成

## 主要架构组件：

## ViewModel：旨在以注重生命周期的方式存储和管理界面相关的数据，分担一部分Activity的工作，专门用来存放与界面相关的数据，让Activity只做数据的显示，拿掉其内部对数据的逻辑处理
只要是界面上能看到的数据，相关的变量都应存放在ViewModel中，而不是Activity中，这样在一定程度上会减少Activity中的逻辑       
另外，ViewModel还有一个非常重要的特性：当手机发生屏幕旋转时，Activity会重新创建，同时存放在Activity中的数据会丢失，而ViewModel的生命周期与Activity不同，它可以保证在手机屏幕发生旋转时不会被重新
创建，只有当Activity退出时才会跟着Activity一起销毁，因此，将界面相关数据存放在ViewModel中，即使旋转手机屏幕，界面上的数据也不会丢失    

ViewModel的创建：不能直接去创建`ViewModel`的实例，而是一定要通过`ViewModelProvider`来获取`ViewModel`的实例,因为ViewModel具有其独立的生命周期且长于Activity
语法规则：`ViewModelProvider(活动或碎片的实例).get(<MyViewModel>::class.java)`

向viewModel传递参数：借助`ViewModelProvider.Factory`
        
## Lifecycles:主要用来响应另一个组件的生命周期变化以执行相应的操作


LifecycleObserver：观察者
LifecycleOwner：被观察者

## LiveData：一种可观察的数据存储类，与常规观察类不同的是其具有生命周期感知能力，该能力确保其仅更新处于活跃生命周期的应用组件观察者
（一种响应式编程组件，可以包含任何类型的数据，并在数据发生变化时通知给观察者，该组件通常与ViewModel结合使用）

`MutableLiveData`：一种可变的liveData，主要有三种读写数据的方法：
* getValue：获取其中包含的数据
* setValue：给LiveData设置数据，只能在主线程中调用
* postValue：给LiveData设置数据，非主线程中调用

***永远只暴露不可变的LiveData给外部，确保非ViewModel中只能观察LiveData的数据变化，而不能给其设置数据***
也就是用ViewModel和LiveData配合使用，确保Activity只有数据的访问权，没有直接操作权
LiveData提供的两种转换方法：
* map():将实际包含数据的LiveData和仅用于观察数据的LiveData进行转换
* switchMap():如果ViewModel中的某个LiveData对象是调用其他方法获取的，那么就可以借助switchMap方法，将这个LiveData对象转换为另一个可观察的LiveData对象

LiveData之所以能够称为Activity与ViewModel之间通信的桥梁，依靠的就是Lifecycles组件，LiveData在内部使用了Lifecycles组件来自我感知生命周期的变化       

## Room：官方推出的ORM（Object Relational Mapping，对象关系映射）框架，用来将面向对象的语言和面向关系的数据库之间建立一种映射关系，核心在于使用面向对象的思维来和数据库进行交互
整体结构：
* Entity：用来定义封装实际数据的实体类，每个实体类都会在数据库中有一张对应的表，并且表中的列是根据实体类中的字段自动生成的
* Dao：数据访问对象，通常在此对数据库的各项操作进行封装，在实际编程中，逻辑层就不需要和底层数据库打交道了，直接与Dao层进行交互
* Database：用来定义数据库中的关键信息，包括数据库的版本号、包含哪些实体类以及提供Dao层的访问实例

对数据库的访问只有增删改查4种操作，但业务需求确是千变万化的，Dao层要做的是覆盖所有的业务需求，使得业务方永远只需要与Dao层进行交互，而不需要和底层数据库打交道       

## WorkManager:执行后台任务的另外选择，用来处理一些要求定时执行的任务，它可以根据操作系统的版本自动选择底层的实现方式，此外还支持周期性任务、链式任务处理等功能
与Service完全不同
其只是一个处理定时任务的工具，可以保证在应用退出甚至手机重启的情况下，之前注册的任务任然会得到执行，因此，该组件适合用来执行一些定期和服务器进行交互的任务，比如周期性地同步数据等
需要注意的是：使用该组件注册的周期性任务不一定会准时执行，系统为了减少资源损耗，可能会将触发时间临近的几个任务放在一起执行
基本用法：
* 定义一个后台任务，并实现具体的任务逻辑：定义一个类继承Worker类，并调用其唯一的构造函数，然后重写父类中的`doWork()`,在这个函数内编写具体的后台任务逻辑
* 配置该后台任务的运行条件和约束信息，并构建后台任务请求
* 将该后台任务请求传入`WorkManager`的`enqueue()`方法中，系统会在合适的时间运行

国产手机上WorkManager可能无法正常使用，因为国产手机在定制Android时会允许用户一键杀死非白名单应用，被杀死的应用程序即无法接收广播，也无法允许WorkManager后台任务       

## C13 kt： DSL（领域特定语言Domain Specific Language）：编程语言赋予开发者的一种特殊能力，通过它可以编写出一些看似脱离其原始语法结构的代码，从而构建出一种专有的语法结构
常用实现方式：高阶函数


# C14 开发技巧

## 获取全局context：构建自己的类继承Application，然后在清单文件内指定应用初始化时实例化自己的类

## 深色适配：核心思想在于对每个界面都进行深色与浅色的设计，然后根据系统当前主体去加载对应的界面配置

## Intent传递对象：通常有两种方式
* Serializable：序列化，将一个对象转换为可存储或可传送的状态，使用方式只需让类实现Serizable接口即可，需要注意的是反序列化后是一个新的对象
* Parcelable：原理是将一个完整的对象分解，而分解后的每一部分都是Intent所支持的数据类型

## Java与Kt相互转化：
* Java转Kt：利用编译器就行转化
* Kt转Java：将Kt代码生成Kt字节码，然后反编译字节码转为Java代码，但是转换完的Java代码可能无法正常执行


# C15 实践
## MVVM架构：（Model-View-ViewModel），
程序主要分为三部分，
* Model，数据模型部分
* View：界面展示部分
* ViewModel：连接数据与界面的桥梁
再细分的话，程序可以再分为若干层，
* UI层包含平时的Activity、布局文件等与界面相关的东西，
* ViewModel层持有和UI层元素相关的数据，
* 仓库层的话主要用来判断调用方请求的数据来源并返回获取到的数据，本地的话使用数据库、sp等来实现，网络的话通常使用Retrofit访问服务器提供的Webservice接口来实现
***必须确保引用单向，引用也不能跨层持有***