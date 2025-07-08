# 1.Android Studio的使用

## 运行环境

写的Android代码要求在Andoid上运行，所以电脑无法直接运行

1. 使用虚拟机：AndroidStudio自带的虚拟机或是下载模拟器
2. 使用数据线链接手机，选择管理文件，手机要打开开发者模式（关于手机，不停点击版本），在系统管理和升级中进入开发者选项，打开usb调试
3. ![image-20250701235752246](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250701235752246.png)



## APP的工程结构

最大的层次：项目

项目中可以有多个模块![image-20250702000054644](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702000054644.png)

模块对应实际的APP，编译运行APP指的是运行某个模块

![image-20250702000309364](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702000309364.png)

APP文件夹下面的main文件夹是我们的主要代码

1. AndroidManifest.xml：是APP的运行配置文件

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <manifest xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:tools="http://schemas.android.com/tools">
   
       <application
           android:allowBackup="true"
           android:dataExtractionRules="@xml/data_extraction_rules"
           android:fullBackupContent="@xml/backup_rules"
           android:icon="@mipmap/ic_launcher"
           android:label="@string/app_name"
           android:roundIcon="@mipmap/ic_launcher_round"
           android:supportsRtl="true"
           android:theme="@style/Theme.MyApplication"
           tools:targetApi="31">
           <activity
               android:name=".MainActivity"
               android:exported="true">
               <intent-filter>
                   <action android:name="android.intent.action.MAIN" />
   
                   <category android:name="android.intent.category.LAUNCHER" />
               </intent-filter>
           </activity>
       </application>
   
   </manifest>
   ```

   根节点manifest

   子节点application:

   * allowBackup：是否允许应用备份
   * icon：指定APP在手机上显示的图标
   * label：指定APP在手机上显示的名称
   * roundicon：APP的圆角图标
   * theme：显示风格
   * 这些属性的内容有些用@开头的一串表示，这些是表示引用模块中的某个文件（不带扩展名），比如`@mipmap/ic_launcher_round`表示mipmap文件夹下面的ic_launcher_round文件![image-20250702002221420](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702002221420.png)
   * 属性前面以android开头表示这是android框架下面定义的标准属性

   子节点activity:

   * activity页面是什么：Activity 是一个具有用户界面的组件，代表一个单独的屏幕，比如一个新闻app的主界面就是一个activity页面，点击进入一条新闻会跳转到另一个activity界面，在编程中表示为一个继承自Activity类或其子类的类（这里的MainActivity就是）

   * activity节点用于配置activity界面，一个activity节点对应一个activity类

   * 一、注册 Activity 类：`android:name=".MainActivity"`，表示当前包下的MainActivity类

   *  二、配置运行时行为:![image-20250702003512392](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702003512392.png)

   * 三、定义意图过滤器（Intent Filter）：

     ` <action android:name="android.intent.action.MAIN" />`action表示要响应的动作类型，MAIN表示该活动界面是主界面（APP启动时的界面）

2. Java子目录：用于存放Java源代码，初始的java文件时MainActivity.java

3. res子目录：用于存放当前模块的资源文件

   ![image-20250702000737833](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702000737833.png)

   * drawable:用于存放图形描述文件和图片文件

   * layout:用于存放界面布局文件![image-20250702000820116](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702000820116.png)

   * mipmap:用于存放启动图标![image-20250702000919375](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250702000919375.png)

   * values目录：用于存放常量定义文件，比如字符串常量strings.xml,颜色常量：color.xml

     ```xml
     <resources>
         <string name="app_name">My Application</string>
     </resourcs>
     <!--strings.xml-->
     ```

     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <resources>
         <color name="black">#FF000000</color>
         <color name="white">#FFFFFFFF</color>
     </resources>
     <!--color.xml-->
     ```

			## 创建活动页面

### 创建XML文件

点击layout文件夹，然后按如下方法操作

![image-20250704181236108](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250704181236108.png)

### 创建java文件

![image-20250704183127784](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250704183127784.png)

在java包下面新建类

在新建的类中加载页面布局

```java
//AppCompatActivity提供向后兼容的界面，并且提供了一些方法来帮助开发者实现Material Design。
public class mianActivity2 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        //onCreate初始化活动页面
        super.onCreate(savedInstanceState);
        //setContentView 是 Android 中用于设置 Activity 显示的布局界面的方法。它的主要作用是将一个布局文件（XML 文件）或一个 View 控件绑定到当前 Activity 上，作为该 Activity 的内容视图。
        setContentView(R.layout.activity_main2);
    }

}
```

### 在AndroidManifest文件中注册界面

```xml
<activity android:name=".mianActivity2"
    android:exported="true">
</activity>
```

### 简化操作

![image-20250704184528218](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250704184528218.png)

可以直接选择创建一个活动界面，as会自动创建对应的类，xml文件和AndroidManifest

创建完界面后，新的界面并不会自动显示，需要进行页面跳转才能看到

## 页面跳转

使用`startActivity(new Intent(当前界面.this,目标界面.class))`

附带知识：

* 按钮控件和点击事件：

  ```java
  Button backbutton = findViewById(R.id.activity3_back_button);
  backbutton.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
      startActivity(intent);
  });
  ```

​		通过id来找到按钮控件，设置点击事件表示点击按钮就会执行对应的操作

* 界面设置

  ```xml
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:id="@+id/main"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity3">
      <TextView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="这是第二页"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <Button
          android:id="@+id/activity2_next_button"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="下一页"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintHorizontal_bias="0.9"/>
      <Button
          android:id="@+id/activity2_back_button"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="上一页"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintHorizontal_bias="0.1"/>
  
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```

​		constraintlayout是一种界面类型，可以使用layout_constraintEnd_toEndOf等命令来约束控件

​		linerlayout是线性界面，会遵循从左到右，从上到下的顺序排列控件

# 2.简单控件

## 1.文本

### 1.文本内容设置

共有三种方法：

1. 直接在xml文件里面设置

   ```xml
   android:text="Hello World!"
   ```

2. 在java源代码中引用控件，并且使用setText方法设置内容

   ```java
   TextView t = findById(R.id.(实际的id));
   t.setText("content");
   ```

   注意要导入R包

   这种方法可以在源文件中实现对文本的修改

3. 使用@string的资源

   ![image-20250704202208663](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250704202208663.png)

在values文件夹下又strings.xml用于储存字符串常量，可以使用"@string/name"来对名字为name的字符串进行引用

```java
<resources>
    <string name="app_name">My Application</string>
    <string name="back">上一页</string>
    <string name="next">下一页</string>
</resources>
```

这样可以更方便的管理文本内容



### 2.文本大小设置

使用setTextSize方法或是直接在xml文件中修改

### 3.文本颜色设置

在xml文件中使用textColor来设置，值的前面要加上#

颜色可以作为一种资源在colors.xml中she'zhi



## 2.预定义样式和字体导入

预定义样式：

![image-20250704222948910](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250704222948910.png)

在values文件夹下创建style文件，在里面预定义样式：

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <!-- ===== 全局颜色 ===== -->
    <color name="colorBackground">#121212</color>
    <color name="colorTextPrimary">#FFFFFF</color>
    <color name="colorTextSecondary">#B0B0B0</color>
    <color name="colorButtonPrimary">#6C63FF</color>
    <color name="colorButtonSecondary">#4A4A4A</color>

    <!-- ===== 全局圆角形状（圆角按钮背景） ===== -->
    <!-- 若使用 background="@drawable/rounded_button"，见 drawable 文件定义 -->

    <!-- ===== 全局字体样式 ===== -->

    <!-- 页面正文 -->
    <style name="TextBody">
        <item name="android:textColor">@color/colorTextPrimary</item>
        <item name="android:textSize">16sp</item>
        <item name="android:fontFamily">@font/inter</item>
    </style>

    <!-- 按钮文字 -->
    <style name="TextButton">
        <item name="android:textColor">@color/colorTextPrimary</item>
        <item name="android:textSize">14sp</item>
        <item name="android:fontFamily">@font/inter</item>
        <item name="android:textStyle">bold</item> <!-- 使用变量字体映射到 500/700 -->
    </style>

    <!-- 页面标题 -->
    <style name="TextTitle">
        <item name="android:textColor">@color/colorTextPrimary</item>
        <item name="android:textSize">20sp</item>
        <item name="android:fontFamily">@font/inter</item>
        <item name="android:textStyle">bold</item>
    </style>

    <!-- 次要提示文字 -->
    <style name="TextSecondary">
        <item name="android:textColor">@color/colorTextSecondary</item>
        <item name="android:textSize">14sp</item>
        <item name="android:fontFamily">@font/inter</item>
    </style>

    <!-- ===== 主按钮样式 ===== -->
    <style name="PrimaryButton">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">48dp</item>
        <item name="android:backgroundTint">@color/colorButtonPrimary</item>
        <item name="android:textColor">@color/colorTextPrimary</item>
        <item name="android:fontFamily">@font/inter</item>
        <item name="android:textStyle">bold</item>
        <item name="android:background">@drawable/rounded_button</item>
    </style>

    <!-- ===== 次按钮样式 ===== -->
    <style name="SecondaryButton">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">48dp</item>
        <item name="android:backgroundTint">@color/colorButtonSecondary</item>
        <item name="android:textColor">@color/colorTextPrimary</item>
        <item name="android:fontFamily">@font/inter</item>
        <item name="android:textStyle">bold</item>
        <item name="android:background">@drawable/rounded_button</item>
    </style>

</resources>
```

导入外部字体：

![image-20250704223145202](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250704223145202.png)

在res文件夹下面创建font文件夹，然后将ttf文件放入，使用时用`@font/文件名`即可

==导入外部字体，在color.xml和style.xml中预定义一些样式和颜色，在string.xml中预定义字符串，直接使用==

## 3.设置动画

在res/anim文件夹下面创建动画效果文件

![image-20250705101350928](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705101350928.png)

```xml
<translate
    android:fromXDelta="100%"
    android:toXDelta="0%"
    android:duration="400"/>
```

这里做了一个平移效果，表示原位置在右侧界面外（100%）

最终到屏幕正中央（0%）

```java
overridePendingTransition(R.anim.enterfromleft, R.anim.existfromright);
```

应用动画效果



按钮动画

```java
// 设置缩放动画（带回弹）
ScaleAnimation scale = new ScaleAnimation(
    1.0f, 0.8f,
    1.0f, 0.8f,
    Animation.RELATIVE_T_SELF, 0.5f
);
scale.setDuration(150);
scale.setRepeatCount(1);
scale.setRepeatMode(Animation.REVERSE);

v.startAnimation(scale);
```

## 4.视图

手机的短边叫做宽，长边叫做高

**宽与高的取值方式**：

1. match_parent：与上级视图保持一致
2. wrap_content：与内容自适应
3. 以dp为单位的具体尺寸

![image-20250705104959192](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705104959192.png)

```xml
<TextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="宽度采用match_parent!"
    android:background="#ff0000"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintVertical_bias="0.3"/>
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="宽度采用warp_content!"
    android:background="#ff0000"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintVertical_bias="0.1"/>
<TextView
    android:layout_width="30dp"
    android:layout_height="wrap_content"
    android:text="宽度采用具体数值!"
    android:background="#ff0000"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintVertical_bias="0.7"/>
```





**嵌套与间距**

视图是可以嵌套的，只要把视图节点写到另一个视图节点的内部，视图之间就有了父子关系，之前的match_parent就是适应父节点

layout_margin可以用于设置视图的间距

![image-20250705113352900](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705113352900.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity2">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="30dp"
        android:background="#BF3131">
        <View
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#FFF"
            android:layout_margin="30dp"
            />
    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

这里嵌套了三层视图，并且设置了30dp的间距



layout_gravity设置了当前视图相对于上级视图的对其方式



**布局**

1. **linear线性布局**：

   内部视图之间的排列顺序是固定的，从上到下排或是从左到右排

   horizontal是横向排列，vertical是纵向排列，由orientation属性设置

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:id="@+id/main"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:orientation="vertical"
       tools:context=".MainActivity3">
       <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:layout_margin="30dp"
           android:background="@color/white"
           android:orientation="horizontal"
           android:layout_weight="0.2">
           <Button
               android:id="@+id/backbutton3"
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="上一页"
               android:layout_weight="0.5"
               />
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="横向排列1"
               android:layout_weight="0.1"
               />
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="横向排列2"
               android:layout_weight="0.1"
               />
       </LinearLayout>
       <LinearLayout
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:layout_margin="30dp"
           android:background="@color/white"
           android:orientation="vertical"
           android:layout_weight="0.8">
           <Button
               android:id="@+id/nextbutton3"
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="下一页"
               />
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="纵向排列1"
               />
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="纵向排列2"
               />
       </LinearLayout>
   
   </LinearLayout>
   ```

最外层线性视图设置为纵向

里面嵌套了两个线性布局，一个横向，一个纵向，同时通过layout_weight来设置占比权重

![image-20250705120250158](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705120250158.png)

2.**网格布局GridLayout**

使用columnCount和rowCount来指定网格的列数和行数

默认从左到右，从上到下排列

```xml
<?xml version="1.0" encoding="utf-8"?>
<GridLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:columnCount="2"
    android:rowCount="3"
    tools:context=".MainActivity4">
    <TextView
        android:layout_width="160dp"
        android:layout_height="180dp"
        android:text="第一格"
        android:textAlignment="center"
        android:gravity="center"
        android:layout_gravity="center"
        android:background="#C2D7E2"
        />
    <TextView
        android:layout_width="160dp"
        android:layout_height="180dp"
        android:text="第二格"
        android:textAlignment="center"
        android:gravity="center"
        android:layout_gravity="center"
        android:background="#FDFBFB"
        />
    <TextView
        android:layout_width="160dp"
        android:layout_height="180dp"
        android:text="第三格"
        android:textAlignment="center"
        android:gravity="center"
        android:layout_gravity="center"
        android:background="#E0CDCD"
        />
    <TextView
        android:layout_width="160dp"
        android:layout_height="180dp"
        android:text="第四格"
        android:textAlignment="center"
        android:gravity="center"
        android:layout_gravity="center"
        android:background="#DCEDC8"
        />
    <Button
        android:id="@+id/backbutton4"
        android:layout_width="160dp"
        android:layout_height="180dp"
        android:backgroundTint="@color/white"
        android:text="上一页"
        android:textColor="@color/black"
        android:layout_gravity="center"
        />
    <Button
        android:id="@+id/nextbutton4"
        android:layout_width="160dp"
        android:layout_height="180dp"
        android:backgroundTint="@color/white"
        android:text="下一页"
        android:textColor="@color/black"
        android:layout_gravity="center"
        />


</GridLayout>
```

![](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705123002031.png)

实现格子均分并且填满的案例

```xml
<Button
    android:id="@+id/btn_clear"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:text="C"
    android:layout_column="0"
    android:layout_row="4"
    android:layout_columnWeight="1"
    android:layout_rowWeight="1" />
```

设置width和height属性为0，然后设置权重为1

**3.滚动视图**

ScrollView:竖直滚动视图

HorizentalScrollView:水平滚动视图

要点：

![image-20250705175715697](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705175715697.png)

![image-20250705175238440](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705175238440.png)

将height设置成match_parent,同时增加属性fillCiewport="true"即可使视图充满屏幕

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity5">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:background="@color/white">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            >

            <TextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:text="TextView" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:text="TextView" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:text="TextView"
            />
            <Button
                android:id="@+id/nextbutton5"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="下一页" />
        </LinearLayout>

    </ScrollView>

    <HorizontalScrollView
        android:layout_width="wrap_content"
        android:layout_height="200dp"
        android:color="#009688">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal"
            >

            <TextView
                android:layout_width="200dp"
                android:layout_height="match_parent"
                android:text="TextView" />

            <TextView
                android:layout_width="200dp"
                android:layout_height="match_parent"
                android:text="TextView"
            />
            <TextView
                android:layout_width="200dp"
                android:layout_height="match_parent"
                android:text="TextView"
            />
            <Button
                android:id="@+id/backbutton5"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="上一页" />
            </LinearLayout>
    </HorizontalScrollView>

</LinearLayout>
```

![image-20250705175834189](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705175834189.png)

## 5.按钮控件

1. textAllCaps属性：设置为true表示所有按钮中的文字全大写
2. onclick:表示点击后调用某个方法，但是不常用，因为这样会导致源文件中方法含义不明确

**点击事件和长按事件**：

setOnClickListener

setOnLongClickListener

点击监听器和长按监听器

这两个监听器由View类派生而来，所以TextView等也可以实现这两个监听器

注意长按监听器的方法是boolean类型，要有返回值

```java
Button backButton = findViewById(R.id.backbutton2);
backButton.setOnLongClickListener(v -> {
    startActivity(new Intent(this, MainActivity.class));
    return true;
});
```

## 6.图像视图ImageView

```xml
<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/test" />
```

将图片放在drawable文件夹里面，然后使用src属性来对图片进行引用

![image-20250705182633445](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705182633445.png)

缩放类型scaleType

![image-20250705182834175](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705182834175.png)

可以不对单独的按钮控件进行监听，而是对多个按钮控件进行监听，只需要

1. 实现接口implements View.OnClickListener

2. 重写public** **void** **onClick**(**View** v) 方法，在方法中判断按钮的类型(v对应的id)即可，然后按钮可以同一调用

3. findViewById(R.id.btn_cancel).setOnClickListener(**this**);类似这样的点击事件来进行处理

**图像按钮ImageButton**

可以展示图片，同时具有按钮的功能

注意这里的图片展示默认为center，如果尺寸过大可能展示不完全，需要调整展示模式

![image-20250705183530637](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250705183530637.png)

## 实现计算器

1. 界面设计：

   * 整体是一个LinearLayout

   * 最上方有一个TextView来展示名称
   * 然后是一个TextView用于显示表达式和计算结果
   * 然后是一个GridLayout用于拜访按钮

2. 后端逻辑

   1. 类：calculator，主要用于维护表达式和最终的计算
      * 成员变量：String用于维护表达式，设置为私有，需要通过特定的方法进行添加
      * 成员方法：
        1. add()添加字符串进入表达式，同时调用检查和更新
        2. inspect()检查添加进的字符串是否是正确的（辅助add）
        3. 用于更新表达式的方法refresh
        4. 用于删除表达式的方法del
        5. 用于清空表达式的方法clear
        6. 计算函数
   2. 点击事件：重写OnClick，点击按钮将对应的字符串加入表达式（调用add）,如果是删除或者清空，则调用类中对应的函数





#### **圆角设置**

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#E0E0E0" /> <!-- 按钮背景颜色 -->
    <corners android:radius="8dp" /> <!-- 圆角半径 -->
    <stroke android:width="1dp" android:color="#BDBDBD" /> <!-- 可选边框 -->
</shape>
```

在drawable中设置对应形态

在button等控件中引用，注意有时候需要禁用默认颜色才能显示颜色

```java
android:background="@drawable/button1"
app:backgroundTint="@null"
```





#### 按键设置（圆角加上点击效果）

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--默认状态-->
    <item android:state_pressed="false">
        <shape>
            <corners android:radius="10dp" />
            <solid android:color="@color/button1BackgroundBegin" />
        </shape>
    </item>
    <!--点击后状态-->
    <item>
        <shape>
            <corners android:radius="10dp" />
            <solid android:color="@color/button1BackgroundEnd" />
        </shape>
    </item>

</selector>
```



# 大作业笔记



主页面采用drawLayout控件

通过include将主页面引入（app_bar_main）

使用navigation来设置侧边栏

navigation中headerLayout用于定义顶部信息，menu用于定义菜单



在app_bar_main中，总体采用线性布局，最上方定义一个Toolbar，用作搜索框（实现点击跳转到搜索页面）和菜单栏换出



## 标签栏与页面切换

接着在下面定义一个TabLayout，再配合viewpager实现滑动切换，其中TabLayout用于展示标签栏，ViewPager用于实现滑动切换页面。

将TabLayout和ViewPager进行联动，通过点击标签栏实现页面切换



**fragment**:

Fragment是一种可以嵌入在Activity当中的UI片段

用来组建Activity界面的局部模块, 也可以说一个Actiivty界面可以由多个Fragment组成

其行为与Activity很相似, 有自己对应的布局(包含具体的View), 它有自己的生命周期，接收自己的输入事件，并且可以从运行中的activity中添加或移除

一个fragment必须总是嵌入在一个activity中，同时fragment的生命周期受activity的影响

**FragmentStateAdapter**：

为viewPager2提供fragment的绑定和管理，由于fragment不能独立存在，所以在构造FragmentStateAdapter时必须传入一个Activity或是fragment用于托管



**创建流程**

1. **创建 Adapter,绑定到ViewPager**

   ```java
   //----------------------实现主题栏-----------------------------------------------
           //初始化tablayout和viewpager，用于实现滑动新闻分类
           tablayout = findViewById(R.id.tablayout);
           viewpager = findViewById(R.id.viewpager);
   
           //viewpager需要设置Adapter,这里使用FragmentStateAdapter
           //ViewPager是容器，负责显示页面和处理手部滑动
           //FragmentStateAdapter是Fragment的适配器，负责向ViewPager提供Fragment，并且管理Fragment的生命周期
           //Fragment是每个页面具体显示的内容
           viewpager.setAdapter(new FragmentStateAdapter(this) {
               //这里创建了一个匿名的FragmentStateAdapter对象
               @NonNull
               @Override
               //这个方法的返回值是一个Fragment对象，当ViewPager需要显示一个页面的时候就会调用
               //这个方法，根据位置返回一个Fragment对象
               public Fragment createFragment(int position) {
                   //使用newInstance()方法创建一个Fragment对象，传入的主题参数使用titles[position]获取
                   return TabNewsFragment.newInstance(titles[position]);
               }
   
               @Override
               //告诉ViewPager一共有多少个页面
               public int getItemCount() {
                   //总的页面数就是标题的个数
                   return titles.length;
               }
           });
   
           //tablayout点击事件
           tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
               @Override
               public void onTabSelected(TabLayout.Tab tab) {
                   //设置viewPager选中当前页
                   //tab.getPosition()获取当前选中的tab的索引
                   viewpager.setCurrentItem(tab.getPosition(),true);
               }
   
               @Override
               public void onTabUnselected(TabLayout.Tab tab) {
   
               }
   
               @Override
               public void onTabReselected(TabLayout.Tab tab) {
   
               }
           });
   
           //viewPager和tab_layout关联在一起
           TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tablayout, viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
               @Override
               public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                   tab.setText(titles[position]);
               }
           });
   
           //启用绑定
           tabLayoutMediator.attach();
   //----------------------实现主题栏-----------------------------------------------
   ```

2. 创建一个继承了Fragment类的类用于创建新的Fragment页面，具体创建方法后面讲述
3. 在getItemCount()中返回页面的总个数
4. `createFragment(position)` 按需调用，比如调用setCurrentItem(position)，就会调用`createFragment(position)` 创建对应的页面并且显示
5. 设置tabLayout的点击事件，里面有语句`viewpager.setCurrentItem(tab.getPosition(),true);`，其中tab.getPosition()会获取当前点击的标签的位置序号，通过调用setCurrentItem(position)来创建并且显示对应的页面

**TabNewsFragment类**：

继承了Fragment类，相当于一个页面模板，可以动态创建结构相同但是内容不同的fragment

```java
package com.java.test5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabNewsFragment extends Fragment {

    //因为Bundle储存数据是使用键值对的，所以需要设置常量参数作为key，实际想要传递的数据作为 value
    //我们需要一个参数就是页面的主题
    private static final String ARG_PARAM = "title";

    // TODO: Rename and change types of parameters
    private String title;

    public TabNewsFragment() {

    }

    public static TabNewsFragment newInstance(String title) {
        //newInstance()方法返回一个TabNewsFragment对象
        TabNewsFragment fragment = new TabNewsFragment();
        //Bundle类是Android中用于在Activity之间传递数据的类
        /*
        ①putXxx(String key,Xxx value):Xxx表示一系列的数据类型，
        比如String,int,float,Parcelable，Serializable等类型，
        以键-值对形式保存数据。
        ②getXxx(String key):根据key值获取Bundle中的数据。
        */
        //创建Bundle对象，将参数传入并且使用setArguments()方法将Bundle对象与Fragment对象关联起来
        //Bundle对象的作用在于，当旋转屏幕等情况下，Fragment需要销毁后再重新创建，此时会保存Bundle对象用于重建
        //如果直接赋值给成员变量，那么销毁的时候数据就会丢失
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    //生命周期开始，注意onCreate()方法是系统自动调用的，
    // newInstance()方法只是预先创建对象并且传入参数，
    // 等到需要页面的时候其返回的TabNewsFragment对象会自动调用这个方法创建液面1
    public void onCreate(Bundle savedInstanceState) {
        //调用父类的onCreate()方法，初始化页面
        super.onCreate(savedInstanceState);
        //将之前的参数传入
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_news, container, false);
    }
}
```

可以不用管内部具体是怎么实现的，只需要知道

1. 使用newInstance方法可以创建页面
2. 需要调整传入的参数个数
3. 自定义页面的样式
4. 在FragmentStateAdapter的createFragment方法中调用newInstance创建并且返回对应的页面
5. 在getItemCount()中返回页面的总个数

## 如何引入库

在libs文件中写库的名称和版本：

```xml
[versions]
agp = "8.10.1"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
appcompat = "1.7.1"
material = "1.12.0"
constraintlayout = "2.2.1"
lifecycleLivedataKtx = "2.9.1"
lifecycleViewmodelKtx = "2.9.1"
navigationFragment = "2.9.1"
navigationUi = "2.9.1"
//库的版本
gson = "2.8.9"
glide = "4.16.0"
okhttp = "4.11.0"

[libraries]
junit = { group = "junit", name = "junit", version.ref = "junit" }
ext-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
lifecycle-livedata-ktx = { group = "androidx.lifecycle", name = "lifecycle-livedata-ktx", version.ref = "lifecycleLivedataKtx" }
lifecycle-viewmodel-ktx = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-ktx", version.ref = "lifecycleViewmodelKtx" }
navigation-fragment = { group = "androidx.navigation", name = "navigation-fragment", version.ref = "navigationFragment" }
navigation-ui = { group = "androidx.navigation", name = "navigation-ui", version.ref = "navigationUi" }
//新加入的库
gson = { module = "com.google.code.gson:gson", version.ref = "gson" }
glide = { module = "com.github.bumptech.glide:glide", version.ref = "glide" }
okhttp = { module = "com.squareup.okhttp3:okhttp", version.ref = "okhttp" }
```

然后再build.gradle中引入：

```
implementation(libs.gson)
implementation(libs.glide)
implementation(libs.okhttp)
```

最后点击sync

![image-20250708094239982](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250708094239982.png)

## 新闻详情

### 如何获取新闻数据

##### 调用现成的API

```java
package com.java.huhaoran;
import android.util.Log;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;


// 目标是解析新闻数据，并且返回一个NewsResponse对象，
// 注意NewsResponse是一个内部类，创建的时候应该使用FetchNews.NewsResponse来创建
public class FetchNews {
    // 接口地址模板
    private static final String BASE_URL = "https://api2.newsminer.net/svc/news/queryNewsList";
    public static NewsResponse fetchNews(String size, String startDate, String endDate, String[] words, String categories, String page) {
        String url = BASE_URL + "?size=" + size;

        if (startDate != null && !startDate.trim().isEmpty()) {
            url += "&startDate=" + startDate;
        }

        if (endDate != null && !endDate.trim().isEmpty()) {
            url += "&endDate=" + endDate;
        }
        else {
            url += "&endDate=当前时间";
        }

        url += "&words=";
        if (words != null && words.length > 0) {
            for(int i = 0; i < words.length; i++) {
                url += words[i];
                if (i < words.length - 1) {
                    url += ",";
                }
            }
        }

        if (categories != null && !categories.trim().isEmpty()) {
            url += "&categories=" + categories;
        }

        url += "&page=" + page;

        // 创建 OkHttpClient
        // OkHttpClient是用于发送请求的客户端对象
        OkHttpClient client = new OkHttpClient();

        // 构建请求
        // request是请求对象，用于描述发送的Http请求
        //使用了OkHttp提供的构建器模式，url是请求的地址，.build()方法构建出请求的对象
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            // 同步发送请求
            //response是响应对象，用于描述请求得到的响应
            //client.newCall(request) 创建一个Call类型对象，代表一次http网络请求
            //参数是请求对象，表示该网络请求的内容
            //execute() 方法发送请求并获取响应
            Response response = client.newCall(request).execute();

            //如果请求成功，那么response的body里面就包含了我们要的新闻数据，格式为jason
            if (response.isSuccessful()) {
                // 获取返回的 JSON 字符串
                String json = response.body().string();

                // 打印原始 JSON 字符串
                System.out.println("原始JSON：\n" + json.substring(0, Math.min(500, json.length())) + "...\n");

                // 使用 Gson 解析
                Gson gson = new Gson();
                //接受一个json字符串，返回指定类型的对象
                //这里转换采用了字符串匹配，将json字符串的字段按照字段名与类的成员变量进行匹配
                //如果字段名和成员变量的名字一样，那么字段值就会自动赋给成员变量
                NewsResponse newsResponse = gson.fromJson(json, NewsResponse.class);
                return newsResponse;


            } else {
                System.out.println("请求失败，状态码：" + response.code());
                return null;
            }

        } catch (IOException e) {
            Log.e( "FetchNews","网络请求异常：" + e.getMessage());
        }

        return null;
    }


    // JSON 数据结构类
    //定义了用来接受json解析结果的类
    static class NewsResponse {
        //每一条新闻字段名为data，这里设置了名为data的列表，那么一条新闻数据就会成为列表中的一个元素
        List<NewsItem> data;
    }

    static class Organizations {
        String mention;
        String count;
    }


    static class KeyWord {
        String word;
        String score;
    }

    static class Location {
        String mention;
    }


    static class NewsItem {
        String title;
        String publishTime;
        List<KeyWord> keywords;
        List<Organizations> organizations;
        List<Location> locations;
        String content;
        String source;
        String category;
        String image;
        String video;
    }
}
```

这里需要使用刚刚引入的OkHttp和gson库，其中okhttp用于发送http请求来获取json格式的数据

gson库用于解析json

一些注意事项：

1. size,page参数不能缺，一定要有，word参数可以留空，但是要有word=这个参数名称在，其他的参数可以留空

2. 图片，视频返回的是链接

3. 图片链接格式：

   空：可能直接没有，可能返回[]

   有内容：![image-20250708120414452](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250708120414452.png)

   用[]框起来，如果有多个链接，则用英文逗号和空格隔开

4. words参数之间可以使用逗号连接

##### **打开网络权限**

在manifest中做出如下改变：

![image-20250708100626089](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250708100626089.png)

增加红色框中的两行

然后在xml文件夹下面增加配置文件：

![image-20250708100727890](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250708100727890.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config xmlns:android="http://schemas.android.com/apk/res/android">
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

##### 重要！！！



不能够再在主线程中进行网络请求，因为网络请求可能很慢，容易卡住ui，应该开启一个子线程来进行网络请求

例如：

```java
        //直接通过
	new Thread(() -> {
            // 发起网络请求（运行在子线程）
            FetchNews.NewsResponse response = FetchNews.fetchNews(
                    "10", "2019-01-01", "2023-01-01", new String[]{"拜登"}, "科技", "2"
            );

            if (response != null && response.data != null && !response.data.isEmpty()) {
                String title = response.data.get(0).title;

                // ⚠️ 子线程不能直接更新 UI，需要回到主线程
                runOnUiThread(() -> {
                    TextView textView = findViewById(R.id.email);
                    textView.setText(title);
                });
            }
        }).start();
```




### 实现新闻列表



之前设置了ViewPager来进行页面的切换，每一个页面都是一个fragment，fragment_tab_news.xml是所有页面的通用布局文件，具体的内容会根据页面的不同发生改变

fragment_tab_news.xml中使用了RecyclerView，这是一个可以滚动的列表，用于展示一条条的新闻（注意要加上依赖`implementation(libs.recyclerview)`）（它只是一个容器，并不关心里面放了什么，具体放的东西由adapter确定）

RecyclerView的具体的样式需要定义一个adapter来进行确定，在adapter中获取数据并且指定数据展示的样式（item_news.xml），然后将这一条条的新闻给RecyclerView进行展示

adapter负责告诉RecyclerView：

* 有多少条新闻（`getItemCount()`）；
* 每一条新闻长什么样（`onCreateViewHolder()`）；
* 每一条新闻的内容怎么填充（`onBindViewHolder()`）



适配器类的定义：

* `class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>`，该类继承自RecyclerView中的Adapter内部类，泛型参数就是它自己内部定义的holder类

* `private List<FetchNews.NewsItem> newsList;`成员变量就是新闻列表
* `public NewsAdapter(List<FetchNews.NewsItem> list)`构造适配器的时候会传入具体的新闻列表，后面就会根据这个新闻列表来构建页面
* `static class NewsViewHolder extends RecyclerView.ViewHolder`静态内部类NewsViewHolder，用于管理一个item中的控件，它保存了item中每个控件的引用，这样就只需要使用一次findViewById来获取控件的引用，从而节约资源
* `public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) `根据之前定义的item_news.xml来创建对应形式的View，然后创建用于管理这个View的NewsViewHold对象并返回
* `public void onBindViewHolder(NewsViewHolder holder, int position)`给第position条新闻设置内容

```java
package com.java.huhaoran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


//定义RecyclerView的适配器
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    //成员变量是新闻列表
    private List<FetchNews.NewsItem> newslist;
    //构造函数将列表传入
    public NewsAdapter(List<FetchNews.NewsItem> newslist) {
        this.newslist = newslist;
    }
    //创建ViewHolder
    static class NewsViewHolder extends RecyclerView.ViewHolder {
        //成员变量就是一条item中每个控件的引用
        TextView title, publisher, time;
        ImageView image;
        public NewsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            publisher = (TextView) view.findViewById(R.id.publisher);
            time = (TextView) view.findViewById(R.id.time);
            image = (ImageView) view.findViewById(R.id.image);
        }

    }
    //创建ViewHolder
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建item的布局,inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        //参数：要使用的布局文件，父布局，是否将布局文件添加为父布局的子元素
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    //获取图片链接,因为直接爬取得到的图片链接可能有中括号和,分割，所以需要处理
    String[] getLinks(String input) {
        if(input.length() <= 2) {
            return null;
        }
        input = input.substring(1, input.length()-1);
        String[] links = input.split(",\\s*");
        return links;
    }

    //为item设置内容,holder是当前item的ViewHolder,position是当前item的位置
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        //获取需要的那条新闻
        FetchNews.NewsItem newsitem = newslist.get(position);
        holder.title.setText(newsitem.title);
        holder.publisher.setText(newsitem.publisher);
        holder.time.setText(newsitem.publishTime);
        String[] links = getLinks(newsitem.image);
        if(links != null && links.length > 0) {
            // 使用 Glide 加载网络图片
            Glide.with(holder.itemView.getContext())  // 使用 ViewHolder 的 itemView 的 Context
                    .load(links[0])                       // 图片 URL
                    .into(holder.image);              // 绑定到 ImageView
        }

    }
    //获取item的总数
    @Override
    public int getItemCount() {
        return newslist.size();
    }
}
```

在TabNewsFragment中创建视图：

```java
@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = view.findViewById(R.id.news_tab_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    // 开线程请求数据
    new Thread(() -> {
        //根据newInstance时传入的title，请求数据
        FetchNews.NewsResponse response = FetchNews.fetchNews("10", "1980-01-01", "", new String[]{}, title, "1");
        if (response != null && response.data != null) {
            requireActivity().runOnUiThread(() -> {
                NewsAdapter adapter = new NewsAdapter(response.data);
                recyclerView.setAdapter(adapter);
            });
        }
    }).start();
}
```



流程示意：

1. 当用户滑到某个页面（比如军事），ViewPager就会调用creatFragment(position)来创建页面
2. 在creatFragment里面会调用TabNewsFragment.newInstance("军事")，
3. onCreate() 获取参数 "军事" 
4. onCreateView() 加载 fragment_tab_news.xml
5. 设置 RecyclerView、Adapter
6. 调用 FetchNews.fetchNews(...) 获取军事类新闻
7. 主线程更新 Adapter 展示新闻数据（item_news.xml 样式）







#### 一个可能实现点赞，收藏，评论的方案

在FetchNews.NewsItem中增加属性记录点赞数，收藏数，评论列表，并且默认为空

在服务器中保存键值对，key是新闻标题，value是新闻相关信息，每次爬取新闻都需要去数据库中检查是否记录了相关点赞评论信息

后端服务器能否实现？？









# bug

1. 在刚刚打开app时点击第6个标签页就会闪退，点击其他页面就不会，先点击其他页面再点击第6个页面也不会

   解决：viewpager.setOffscreenPageLimit(titles.length);加上这一行代码，将所有页面全部预先缓存就不会闪退了

   不知道为什么，反正这样可以解决

一些图片无法加载出来