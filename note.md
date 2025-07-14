

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





## runOnUiThread



```
runOnUiThread(Runnable runnable)
```

在一个新开的线程中是不允许直接修改ui界面的，这时候可以使用runOnUiThread，接受一个Runnable接口的对象，在run方法中可以执行ui的修改，常常用于网络请求中修改界面



## notifyItemRangeInserted方法的使用

*notifyItemRangeInserted*方法用于通知*RecyclerView*，从指定位置开始，有一定数量的新数据项被插入。这个方法接受两个参数：*startPosition*和*itemCount*。*startPosition*是数据插入的起始位置，*itemCount*是被插入的数据数量。

例如，如果你在列表的第5个位置插入了3个新的数据项，你应该这样调用这个方法：

adapter.notifyItemRangeInserted(5, 3);

这将导致*RecyclerView*从第5个位置开始刷新3个数据项，而不会影响其他位置的数据



## fragment生命周期中的三个初始化方法

1. onCreate():用于初始化视图以外的内容，比如初始化变量等

   

2. onCreateView(): 在onCreateView()方法中，你需要创建Fragment的视图层次结构（UI）。你应该使用LayoutInflater来将布局文件实例化为一个View对象，并在这个方法中返回该视图对象。这个方法被视为Fragment生命周期的一部分，用于创建Fragment的用户界面。在这个方法中，你只负责创建视图，还不能对视图进行操作，例如设置监听器等。这个方法返回的视图将会被显示在Fragment的容器中。

   也就是创建fragment的视图（View）对象，这个View对象的类型不确定，可以是线性等一切布局

   

3. onViewCreated(): onViewCreated()方法是在onCreateView()方法完成后被调用的。在onViewCreated()方法中，你可以对Fragment的视图进行初始化、设置监听器、绑定数据等操作。你可以使用view.findViewById()方法获取到视图中的控件，并对其进行操作。这个方法可以访问到Fragment的视图，并允许你对其进行进一步的操作。在onViewCreated()方法中，你可以安全地操作Fragment的视图，包括设置监听器、绑定数据、处理用户交互等操作。



## setCurrentItem

```java
viewpager.setCurrentItem(position, false/true)
```

该方法用于设置一个ViewPager跳转到第position个页面，后面的false/true用于指定是否启用滑动的特效

比如想要实现tabLayout和viewPager的联动：

```java
//tablayout点击事件
tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //设置TabLayout的选中事件
        //tab.getPosition()获取当前选中的tab的索引
        //setCurrentItem()方法可以实现跳转到ViewPager的某个页面
        viewpager.setCurrentItem(tab.getPosition(),false);
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
```

需要两个部分，第一部分设置tab的点击监听器，里面就是用setCurrentItem用于实现ViewPager的跳转，可以根据tab的点击跳转到对应的页面，后面再将两者绑定在一起，实现ViewPager的滑动对TabLayout的联动

|                   功能                   | `TabLayout.OnTabSelectedListener` | `TabLayoutMediator` |
| :--------------------------------------: | :-------------------------------: | :-----------------: |
|     **点击 `Tab` 切换 `ViewPager`**      |              ✅ 支持               |       ✅ 支持        |
| **滑动 `ViewPager` 更新 `Tab` 选中状态** |             ❌ 不支持              |       ✅ 支持        |
|      **自动设置 `Tab` 的文字/图标**      |             ❌ 不支持              |       ✅ 支持        |



## 使用Intent传递信息

在一个activity中，可以定义一个Intent类对象，并且指定目标页面，使用putExtra指定Intent携带的信息（以键值对的形式），然后使用startActivity(intent);将intent发送过去，在对应页面可以使用geiIntent()来获取Intent从而得到信息

```Java
Intent intent = new Intent(this, MainActivity.class);
intent.putExtra("targetPage", 3);
startActivity(intent);
finish();
```

```Java
int targetPage = getIntent().getIntExtra("targetPage",0);
viewpager.setCurrentItem(targetPage);
```



## Adapter初步



**MCV架构**：

![img](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/11147289.jpg)

Model:数据模型，负责程序的核心运算和逻辑判断，会通过view获取用户信息，然后从数据库查询相关信息，然后进行运算，再将结果通过view呈现出来（后端部分）

view:就是图形界面，可以于用户进行交互

Controller:控制器作为View和Model之间的桥梁，可以接受用户操作，然后调用Model层处理数据，将处理结果发给view层绘制界面（一般是通过Adapter），比如Activity.java就是典型的Controller

例如：实现一个计算器程序，用户在View层中点击按钮，然后用户的操作在Controller层中通过点击事件获取到，Controller调用后端的Model(比如说一个Calculator类)，将用户的表达式输入或是进行计算，然后Controller获取后端Model的计算结果，通过Adapter在View层进行显示

Adapter可以看作是View的辅助工具，将前端界面绘制需要的数据作为Adapter的成员变量，利用这些变量在Adapter里面定义绘制的逻辑，等到需要绘制界面的时候只需要构造Adapter的对象，同时将需要的数据传入（构造函数），再将Adapter类的对象和视图对象绑定在一起，这样就可以实现前后端之间的交互

还可以在Adapter中设置相关方法，当后端数据发生变化时可以调用这些方法修改前端界面



注意，adapter是用于辅助视图创建的，理论上完全可以在Controller中创建视图并且将视图添加进容器中，但是这样不够简便，而且没有复用和懒加载，性能比较差，比如：

```java
LinearLayout container = findViewById(R.id.news_container);

for (NewsItem item : newsList) {
    View newsView = LayoutInflater.from(this).inflate(R.layout.item_news, container, false);
    
    TextView title = newsView.findViewById(R.id.title);
    title.setText(item.title);

    container.addView(newsView);
}
```

`LayoutInflater.from(this)`创建了一个LayoutInflater类的对象，调用`.inflate(R.layout.item_news, container, false)`表示创建出一个view类型的对象，其中R.layout.item_news是这个view的布局文件，container是要将这个view加入的容器，false代表展示不加入

 container.addView(newsView);将刚刚创建的view加入容器中



**继承并使用BaseAdapter**:

核心方法：

视图的结构：一般由多个item构成，每个item给予一个编号称为position（这个item在数据列表中的索引，与屏幕上的滑动无关），item的样式可以单独定义xml文件

1. public int getCount(): 适配器中数据集的数据个数；

2. public Object getItem(int position): 获取数据集中与索引对应的数据项（也就是索引为position的哪个item）

3. public long getItemId(int position): 获取第position个item对应的ID；

4. public View getView(int position,View convertView,ViewGroup parent): 

   用于创建第position个item，position就是其索引，convertView是之前使用过但是废弃了（不在屏幕中）的视图，可以进行复用，parent是父容器，因为在创建视图时会用到

工作流程：

1. **获取数据项个数** — `getCount()`

当视图控件（比如`ListView`）准备显示列表时，**会先调用适配器的`getCount()`方法**，以确定列表中总共有多少条数据（即多少个Item需要展示）。

- 这个方法返回的数据个数决定了列表的长度。
- 视图控件根据这个数字决定滑动范围、显示条目数量等。

2. **创建和显示每个Item视图** — 循环调用`getView(position, convertView, parent)`

然后，视图控件会循环调用`getView()`方法为每一个`position`（从0到`getCount()-1`）创建或者复用对应的列表项视图：

- **传入参数 `position`**：表示当前需要展示的是第几个数据项。
- **传入参数 `convertView`**：
  - 这是“回收”的旧视图，用来避免重复创建，提升效率。
  - 第一次加载时通常是`null`，会创建新视图。
  - 滑动列表时，旧的、滚动出屏幕的视图会传进来复用。
- `getView()`负责返回一个“装载了数据”的视图，供视图控件显示。



复用机制：因为整个页面中的View的形式大部分都是一样的，只是内容不同，当一个item划出屏幕时，它会被废弃掉，而android会将这些已经废弃掉的view传入getView进行复用，所以一般在getView中会先判断convertView是否为空，如果不为空，那么就不用创建视图了，可以直接复用

为了减少调用findViewById的次数，我们一般会创建一个内部类ViewHolder用于保存一个item中所有需要调整内容的控件的引用，然后通过setTag方法将ViewHolder的一个对象绑定在一个View上面，这样下一次复用的时候就不需要再获取View中的item了，可以直接getTag



## SharedPreference

android内置的一种数据持久化存储的方案，用于储存简单的键值对数据，存储在设备本地的一个xml文件中

轻量化，但是容量较小，不能频繁请求，没有加密，所以适合储存一些如用户设置，偏好，登录状态，开关状态等简单的设置数据



**使用**：

1. 获取对象

   `SharedPreferences sp = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);`使用getSharePreferences方法，如果之前已经有了user_prefs这个文件，那么就会返回这个文件对应的对象，如果没有，会创建文件并且返回对象。

   这个方法是单例模式，只会返回一个对象，避免了并发修改，比构造函数更好

2. 获取数据

   `sp.getType(key, default)`，Type是具体的一个类别，如果有，返回对应的value，如果没有，返回默认值

3. 写入数据

   `SharedPreferences.Editor editor = sp.edit();`获取Editor

   editor.putType(key,value)方法用于放入数据

   最后要用editor.apply()提交

4. 删除数据

   ```java
   editor.remove("username");  // 删除指定键
   editor.clear();             // 清空所有数据
   ```

## 使用Gson来将对象序列化和将对象从json中复原

```java
Gson gson = new Gson();
String json = gson.toJson(list);
System.out.println(json);
ArrayList<String> fromJson = gson.fromJson(json, ArrayList.class);
for(String s1 : fromJson) {
    System.out.println(s1);
}
```

使用toJson可以将对象变为json字符串，使用fromJson(json, type.class)可以将对象恢复

SharedPreferment不能储存List，但是可以使用Gson将其化为json字符串进行储存



## 适配器中数据更改

涉及用户操作更改数据，同时要在界面上显示的情况，一般我们会==在Adapter中设计更改数据的方法==，同时使用notifyDataSetChanged()来进行界面更新

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

还要加上这个：<uses-permission android:name="android.permission.INTERNET" />

有时还要加上这个`android:usesCleartextTraffic="true"`允许明文访问，否则无法使用http



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

1. 首先，我们创建了一个TabLayout，作为上方的标签栏，然后创建了一个ViewPager2，作为展示具体页面的容器

2. 但是ViewPager2只是一个容器，它并不知道里面需要展示什么东西，所以我们需要设置一个Adapter来决定ViewPager2展示的内容，这里我们选择创建一个`FragmentStateAdapter`，也就是ViewPager2中的一个页面是一个Fragment，具体创建过程如下：

   1. ```java
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
      ```

   2. `FragmentStateAdapter`决定了两件事情，首先，告诉ViewPager2一共需要多少个页面`int getItemCount()`（这里就是我们的标题数），然后将每个页面需要展示的fragment(根据页面的position来确定)返回给ViewPager2（` Fragment createFragment(int position)`），具体创建fragment的过程被外包给TabNewsFragment类来完成，TabNewsFragment提供了对外API：`TabNewsFragment.newInstance(titles[position]);`，可以实现根据新闻主题创建fragment，这里调用了这个API来创建具体需要展示的fragment，接下来看TabNewsFragment类的实现：

      1. ```java
         public static TabNewsFragment newInstance(String title) {
             //newInstance()方法返回一个TabNewsFragment对象
             TabNewsFragment fragment = new TabNewsFragment();
             //Bundle类是Android中用于在Activity之间传递数据的类
             //创建Bundle对象，将参数传入并且使用setArguments()方法将Bundle对象与Fragment对象关联起来
             //Bundle对象的作用在于，当旋转屏幕等情况下，Fragment需要销毁后再重新创建，此时会保存Bundle对象用于重建
             //如果直接赋值给成员变量，那么销毁的时候数据就会丢失
             Bundle args = new Bundle();
             args.putString(ARG_PARAM, title);
             fragment.setArguments(args);
             return fragment;
         }
         ```

      2. 首先是newInstance方法，在这里创建出了TabNewsFragment类对象fragment，将其需要的参数（title）包装进Bundle后将其绑定在fragment中，并且返回，注意这里采用了懒加载，此时并没有生成具体的页面，只是创建了页面的对象并且绑定相关的参数，通过这个对象可以产生我们需要的页面，但是只有在需要展示页面的时候才产生
      3. **fragment的结构**：在fragment_tab_news.xml中定义了fragment的样式，主要是一个RecyclerView，用于展示一条条的新闻，**RecyclerView**同样也是一个容器，它也不知道具体应该展示什么，所以需要为其设置**Adapter**：
         1. 首先，我们设置了多个不同的item_news.xml，用于规定不同图片数的新闻条的样式
         2. 然后在NewsAdapter中，定义RecyclerView的Adadpter，他会决定两件事：产生多少个item,每个item都是什么样式的
         3. `getItemCount()`方法返回item个数（新闻条数），用于产生对应数量的item
         4. NewsAdapter中维护了一个newslist，是它要展示的新闻，构造它的时候需要将这个newslist传入，NewsAdapter就可以实现将这些news展示出来
         5. `int getItemViewType(int position)`用于确定某个item需要采用的样式类别
         6. 里面有一个内部类NewsViewHolder，每一个item对应一个其对象，用于管理item中控件的引用
         7. `public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)`用于产生一个item，他会根据type来加载item的样式并且产生对应的NewsViewHolder类对象
         8. 最后，在方法`public void onBindViewHolder(NewsViewHolder holder, int position)`，将每条新闻的具体内容设置在每条item中，实现了新闻的展示
      4. 当需要展示页面的时候，系统会自动调用`onCreate`,`onCreateView`,`onViewCreated`,onCreate获取参数，OnCreateView用于初始化fragment的样式，onViewCreate用于加载RecyclerView
         1. 在onViewCreate中，首先创建一个RecyclerView类的对象
         2. 然后开一个线程获取新闻列表
         3. 最后使用新闻列表构造Adapter,并且将这个Adapter绑定到RecyclerView类的对象，从而实现页面的创建

3. 为了实现主题的切换，我们创建了一个TabLayoutMediator，用于TabLayout和ViewPager2的同步，创建过程如下：

   1. 构造函数 `TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager, TabConfigurationStrategy tabConfigurationStrategy)`，tabLayout和viewPager是想要同步的两个控件,TabConfigurationStrategy规定了每个tab的内容（也就是类型名）
   2. 启用绑定，当TabLayout切换时，ViewPager2也会随之切换



```java
+--------------------+
|    MainActivity    |
+--------------------+
          |
          | 创建 TabLayout 和 ViewPager2
          v
+-----------------------------+
|   设置 FragmentStateAdapter |
+-----------------------------+
          |
          | 调用 createFragment(position)
          v
+--------------------------------------+
|  TabNewsFragment.newInstance(title)  |
+--------------------------------------+
          |
          | 创建 Fragment 对象并 setArguments(title)
          v
+--------------------------+
| TabNewsFragment 生命周期 |
+--------------------------+
          |
          | onCreate()   -->  读取 title 参数
          | onCreateView() --> 加载 RecyclerView 布局
          | onViewCreated()
          v
+---------------------------------------------------+
| 启动子线程 FetchNews.fetchNews(...) 获取新闻列表  |
+---------------------------------------------------+
          |
          v
+----------------------------------+
|  构造 NewsAdapter(newslist)     |
+----------------------------------+
          |
          v
+----------------------------+
| RecyclerView.setAdapter() |
+----------------------------+
          |
          v
+----------------------------+
| 显示新闻列表（多种样式）   |
+----------------------------+


```





示例：

1. 当用户滑到某个页面（比如军事），TabLayout和ViewPager会实现同步

2. ViewPager就会调用creatFragment(position)来创建页面

3. 在creatFragment里面会调用TabNewsFragment.newInstance("军事")，创建出TabNewsFragment类对象并且绑定title参数

4. 当需要展示出页面时，系统自动调用onCreate() 获取参数 "军事" 

5. onCreateView() 加载 fragment_tab_news.xml

6. 调用onViewCreate()方法，在该方法中会创建出具体的页面展示效果

   1. 创建出RecyclerView类的对象，作为页面展示容器
   2. 开一个线程，调用fetchNews(title)方法，获取新闻列表
   3. 构造RecyclerView的Adapter，并且绑定，实现页面的展示

   







### 优化图片展示

使用cardview作为图片展示台，并且设置圆角样式，`android:layout_centerInParent="true"`设置居中

内部的imageView使用相等的weight来设置为等分，同时图片之间设置margin分隔

```xml
<!-- 图片区域 -->
<androidx.cardview.widget.CardView
    android:id="@+id/image_container"
    android:layout_width="260dp"
    android:layout_height="wrap_content"
    android:layout_centerInParent="true"
    android:layout_below="@id/title"
    android:layout_marginTop="4dp"
    android:layout_marginBottom="8dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="2dp"
    android:layout_marginStart="0dp"
    android:layout_marginEnd="0dp"
    android:background="@android:color/white">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="150dp"
        android:padding="8dp"
        android:gravity="center"
        android:orientation="horizontal">

        <ImageView
            android:id="@+id/image1"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:scaleType="centerCrop"
            android:layout_marginEnd="4dp"
            android:background="@drawable/image_rounded_background" />

        <ImageView
            android:id="@+id/image2"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:scaleType="centerCrop"
            android:layout_marginStart="2dp"
            android:layout_marginEnd="2dp"
            android:background="@drawable/image_rounded_background" />


    </LinearLayout>
</androidx.cardview.widget.CardView>
```



### 优化页面加载

FragmentStateAdapter为了节省内存，只会缓存一定数量的页面（使用viewpager.setOffscreenPageLimit(limit);）来设置缓存的范围，在当前页面左右limit范围内的页面会被保存，超过这个范围内的页面会被销毁

如果切换了Tab导致当前页面被销毁，下一次想要切换到这个页面就要重新进行网络请求，比较耗时间

可以尝试缓存新闻列表，每次销毁fragment但是新闻列表不销毁，可以节约网络请求的时间

```java
//MainActivity.java
//newsCache用于缓存新闻数据，避免重复请求
public static Map<String, List<FetchNews.NewsItem>> newsCache = new HashMap<>();
```



```java
//TabNewsFragment.java
//----------------------------实现数据的加载-----------------------

//如果有缓存数据，就不开线程去请求了
if(newsCache.containsKey(title)) {
    List<FetchNews.NewsItem> responseData = newsCache.get(title);
    if (responseData != null) {
        if (!isAdded() || getActivity() == null) return; // 添加这个判断
        requireActivity().runOnUiThread(() -> {
            if (!isAdded() || getActivity() == null) return; // 再保险地判断一次
            NewsAdapter adapter = new NewsAdapter(responseData);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));
        });
    }
    return;
}
// 开线程请求数据
new Thread(() -> {
    //根据newInstance时传入的title，请求数据
    String title_tmp = new String();
    if(title.equals("全部")) {
        title_tmp = "";
    }
    else {
        title_tmp = title;
    }
    FetchNews.NewsResponse response = FetchNews.fetchNews("15", "1900-01-01", "2025-7-10", new String[]{}, title_tmp, "1");
    if (response != null && response.data != null) {
        // 缓存数据
        newsCache.put(title, response.data);
        if (!isAdded() || getActivity() == null) return; // 添加这个判断
        requireActivity().runOnUiThread(() -> {
            if (!isAdded() || getActivity() == null) return; // 再保险地判断一次
            NewsAdapter adapter = new NewsAdapter(response.data);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));
        });
    }

}).start();
//----------------------------实现数据的加载-----------------------
```



### 实现刷新和更多

使用了[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)来实现这个效果

```xml
<!--fragment_tab_news.xml-->

<com.scwang.smart.refresh.layout.SmartRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/refreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <com.scwang.smart.refresh.header.BezierRadarHeader
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/news_tab_recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clipToPadding="false"
        android:padding="8dp"/>
    <com.scwang.smart.refresh.footer.BallPulseFooter
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</com.scwang.smart.refresh.layout.SmartRefreshLayout>
```

首先更新xml文件，将RecyclerView置于martRefreshLayout控件下，这个控件可以实现上拉和下拉的效果，还可以实现监听器

BezierRadarHeader，BallPulseFooter分别是上拉和下拉的动画效果



==TabNewsFragment类是用于创建一个fragment的，一个fragment对应一个TabNewsFragment的对象，这个类对象就相当于是这个fragment的管理者，里面有这个fragment的相关数据，有fragment的三个生命周期方法，用于储存fragment的相关数据和管理它的生命周期==



新增了

```java
private RecyclerView recyclerView;
private NewsAdapter newsAdapter;
private int currentPage = 1;
boolean isLoading = false;
```

这四个成员变量，前两个是当前页面的RecyclerView及其适配器的引用，因为接下来要在上下拉的时候对其进行更新，所以将其作为成员变量引用起来会更加方便

currentPage是当前网络请求到的页数，因为如果一直请求第一页那么请求到的数据是一样的，必须要递增页数获取新的数据

isLoading是表示当前是否在加载数据，因为请求数据是新开一个进程，所以要增加是否正在加载的判断，防止反复请求



因为有了上拉和下拉，所以页面的初始化和更新逻辑有所改变：

两种更新逻辑：

1. 新请求一些数据，将这些数据添加到当前缓存中，更新界面（上拉更多）
2. 新请求一些数据，清空缓存，将数据放入（下拉刷新）

更新Adapter和更新界面的逻辑放在了NewsAdapter中：

```java
public void appendData(List<FetchNews.NewsItem> moreData) {
    int previousSize = this.newslist.size(); // 获取当前数据集的大小
    this.newslist.addAll(moreData);          // 将新数据追加到现有列表
    notifyItemRangeInserted(previousSize, moreData.size()); // 通知适配器新增了数据，从第previousSize个item开始，后面moreData.size()个item都要更新
}

//方便刷新数据
public void updateData(List<FetchNews.NewsItem> newData) {
    this.newslist = newData;          // 直接替换整个数据集
    notifyDataSetChanged();           // 通知 RecyclerView 所有项需要刷新
}
```

因为成员变量中已经维护了当前页面RecyclerView对象的Adapter，所以要r更新页面只需要通过adapte调用这两个函数即可



第一种更新方式：

```java
private void loadMoreNewsData() {
    // 防止重复加载
    if (isLoading) return;
    isLoading = true;

    // 递增页码
    currentPage++;

    // 从网络请求下一页数据
    new Thread(() -> {
        //开线程请求数据
        String title_tmp = title.equals("全部") ? "" : title;
        FetchNews.NewsResponse response = FetchNews.fetchNews("15", "2020-01-01", "", new String[]{}, title_tmp, String.valueOf(currentPage)); // 使用 currentPage
        if (response != null && response.data != null) {
            //如果请求到数据了，则要将数据添加到缓存，并且更新界面
            // 将新数据追加到缓存

            //在缓存Map中找到对应标题的缓存
            List<FetchNews.NewsItem> cachedData = newsCache.get(title);
            if (cachedData != null) {
                // 如果缓存中有数据，则将新的数据追加到对应标题的缓存List中，然后将List放入
                cachedData.addAll(response.data);
                newsCache.put(title, cachedData);
            } else {
                // 如果缓存中没有数据（理论上不应该发生，因为首次加载已经缓存），就直接将新获取的数据加入
                newsCache.put(title, response.data);
            }

            //更新ui界面（因为当前在一个新线程，不在主线程中是无法直接更新界面的，需要使用runOnUiThread）
            requireActivity().runOnUiThread(() -> {
                //增加数据，同时更新界面
                newsAdapter.appendData(response.data);
                RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                refreshLayout.finishLoadMore(); // 结束加载动画
                isLoading = false; // 允许下次加载
            });
        } else {
            requireActivity().runOnUiThread(() -> {
                RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                refreshLayout.finishLoadMore(false); // 加载失败
                isLoading = false; // 允许下次加载
            });
        }
    }).start();
}
```



第二种更新方式：

```java
private void loadMoreNewsData() {
    // 防止重复加载
    if (isLoading) return;
    isLoading = true;

    // 递增页码
    currentPage++;

    // 从网络请求下一页数据
    new Thread(() -> {
        //开线程请求数据
        String title_tmp = title.equals("全部") ? "" : title;
        FetchNews.NewsResponse response = FetchNews.fetchNews("15", "2020-01-01", "", new String[]{}, title_tmp, String.valueOf(currentPage)); // 使用 currentPage
        if (response != null && response.data != null) {
            //如果请求到数据了，则要将数据添加到缓存，并且更新界面
            // 将新数据追加到缓存

            //在缓存Map中找到对应标题的缓存
            List<FetchNews.NewsItem> cachedData = newsCache.get(title);
            if (cachedData != null) {
                // 如果缓存中有数据，则将新的数据追加到对应标题的缓存List中，然后将List放入
                cachedData.addAll(response.data);
                newsCache.put(title, cachedData);
            } else {
                // 如果缓存中没有数据（理论上不应该发生，因为首次加载已经缓存），就直接将新获取的数据加入
                newsCache.put(title, response.data);
            }

            //更新ui界面（因为当前在一个新线程，不在主线程中是无法直接更新界面的，需要使用runOnUiThread）
            requireActivity().runOnUiThread(() -> {
                //增加数据
                newsAdapter.appendData(response.data);
                RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                refreshLayout.finishLoadMore(); // 结束加载动画
                isLoading = false; // 允许下次加载
            });
        } else {
            requireActivity().runOnUiThread(() -> {
                RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);
                refreshLayout.finishLoadMore(false); // 加载失败
                isLoading = false; // 允许下次加载
            });
        }
    }).start();
}
```



最开始，调用onCreatView来初始化页面：

此时我们获取了RecyclerView和Adapter的引用，同时进行了页面布局的初始化

```java
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // 使用 SmartRefreshLayout 的布局
    View view = inflater.inflate(R.layout.fragment_tab_news, container, false);
    recyclerView = view.findViewById(R.id.news_tab_recycler);
    RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);

    // 设置 RecyclerView 的 LayoutManager
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    // 初始化 Adapter，初始为空列表
    newsAdapter = new NewsAdapter(new ArrayList<>());
    recyclerView.setAdapter(newsAdapter);
    recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));

    // 返回视图
    return view;
}
```



注册上下拉监听器：

==注意，监听器只需要注册一次，也就是注册监听器的方法执行一次之后监听器就生效了，之后监听到事件就都会执行里面的代码，但是如果一次都没有被执行，则不会生效==

```java
private void initRefreshLayout() {
    RefreshLayout refreshLayout = getView().findViewById(R.id.refreshLayout);

    // 下拉刷新
    refreshLayout.setOnRefreshListener(refreshlayout -> {
        newsCache.remove(title); // 移除缓存
        loadNewsData(true); // 加载第一页
    });

    // 上拉加载更多
    refreshLayout.setOnLoadMoreListener(refreshlayout -> {
        // 加载下一页
        loadMoreNewsData();
    });
}
```



在onViewCreate中注册监听器和初始化页面

```java
@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // 初始化 SmartRefreshLayout
    initRefreshLayout();

        if(newsCache.containsKey(title)) {
            newsAdapter.updateData(newsCache.get(title));
        }
        else {
            // 首次加载数据（无缓存时）
            loadNewsData(true);
        }
}
```



缓存机制：为了避免切换Tab会导致页面重新加载的问题，我增加了全局的Map newsCache用于存储每个页面的新闻列表

每次刷新或是获取更多都会更新缓存，在onViewCreate中，如果有缓存，则会通过缓存加载页面



### 实现标签栏的增删改查

思路：

维护两个全局的list分别存放已使用的标签和未使用的标签，要关注顺序

使用SharedPreference进行持久化保存

由于SharedPreference无法对list进行保存，所以使用了Gson里的toJson和fromJson两个方法，list转化为json字符串进行储存和读取

创建一个TabsPreference类，封装了两个list的加载和存储操作

主界面启动的时候会对两个list进行初始化，如果之前储存过标签顺序，就读取出来，如果没有，采用默认的



创建一个界面TabsManager用于标签栏的改动

总体是一个线性布局，上下分成两份，均为Grid，item是一个个的标签

设置两个Grid的Adapter用于进行界面的生成，考虑传进两个数组，然后将数组中的主题标签依此排开

```java
public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if(convertView == null) {
        holder = new ViewHolder();
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_manager, parent, false);
        holder.title = (TextView) convertView.findViewById(R.id.title);
        holder.delButton = (ImageView) convertView.findViewById(R.id.delButton);
        convertView.setTag(holder);
    }
    else {
        holder = (ViewHolder) convertView.getTag();
    }
    //第position个item显示第position个标题
    holder.title.setText(titles.get(position));
    //如果开启编辑模式，就显示删除图标,注意全部这个分类不能够删除
    //在xml中不设置ImageView中的src属性，而是在getView中动态设置
    if(editMode) {
        if(position != 0) {
            holder.delButton.setImageResource(R.drawable.delbutton);
            holder.delButton.setVisibility(View.VISIBLE);
            Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
            holder.delButton.startAnimation(shake);
            holder.title.startAnimation(shake);
            //设置item的点击事件
            //注意点击事件只要注册一次就可以一直生效，所以我们可以直接在适配器中设置点击事件
            holder.title.setOnClickListener(v -> {
                //只要点击，就调用接口中的方法来调整list
                mOnTitlesTransferListener.onTitlesTransfer(titles.get(position));
            });
        }
        else {
            holder.delButton.setImageResource(R.drawable.forbit_change);
            holder.delButton.setVisibility(View.VISIBLE);
            holder.delButton.clearAnimation();
            holder.title.clearAnimation();

        }
    }
    else {
        holder.delButton.setVisibility(View.GONE); // 编辑模式为 false 时隐藏
        holder.title.clearAnimation();
        holder.delButton.clearAnimation();
        holder.title.setOnClickListener(null);
    }


    if(position == 0) {
        holder.title.setTextColor(mContext.getResources().getColor(R.color.light_black));;
    }else {
        holder.title.setTextColor(mContext.getResources().getColor(R.color.black)); // 用你默认的颜色
    }
    return convertView;
}

public void setEditMode(boolean editMode) {
    this.editMode = editMode;
    notifyDataSetChanged();
}
```

设置变量editMode用于指示是否处于编辑状态，如果处于编辑状态，给item附加上抖动效果，同时设置点击事件监听器（注意点击事件监听器只要设置一次就可以一直生效，所以是可以在创建页面时设置的），用于更新数据

如果不处于编辑状态，就要把点击事件，抖动效果等删除掉（因为item可能会复用，所以获取的convertView可能会带有效果，要显式删除）

**如何改变编辑状态**

在adapter中设置setEditMode方法，修改编辑状态，同时使用notifyDataSetChanged()来修改ui界面，在activity设置点击事件，一点击就反转当前编辑状态即可（常用方法，在adpter中设置数据调整的方法，当构建ui的数据发生改变，调用该方法）

**如何同步数据**

一般使用接口回调在activity和adapter之间，adapter和adapter之间进行数据交互

在adapter的点击事件中调用了mOnTitlesTransferListener.onTitlesTransfer(titles.get(position));adapter中维护了一个接口类型的变量，并且有一个传入接口类型引用的方法，这样就可以在activity中设置方法并且传入adapter中调用

```java
//在Activity中定义两个Adapter中的接口，实现交互
titlesAdapter.setOnTitlesTransferListener(new OnTitlesTransferListener() {
    @Override
    public void onTitlesTransfer(String tag) {
        //改变两个list
        MainActivity.titles.remove(titles.indexOf(tag));
        MainActivity.titlesNoUse.add(tag);
        titlesAdapter.remove(titlesAdapter.titles.indexOf(tag));
        titlesNoUseAdapter.add(tag);
    }
});
```

**如何在编辑标签栏的同时让TabLayout和ViewPager同步改变**

一般使用`startActivityForResult`来进行activity之间的数据交互

有以下三个要点：

1. 发送请求并且跳转页面：

   ```java
   ImageView tab_manager = findViewById(R.id.tab_manager);
   tab_manager.setOnClickListener(v -> {
       Intent intent = new Intent(MainActivity.this, TabsManager.class);
       //启动界面并且接受返回结果
       startActivityForResult(intent, REQUEST_CODE);
   });
   ```

​	 startActivityForResult(intent, REQUEST_CODE);表示跳转到指定页面并且接受返回的数据

2. 目标activity返回结果：

   ```java
   Intent resultIntent = new Intent();
   //设置intent携带的数据
   resultIntent.putExtra("update_titles", new ArrayList<String>(titles));
   //将resultIntent设为结果进行返回，RESULT_OK是预定义的常量，表示成功
   setResult(RESULT_OK, resultIntent);
   finish();
   ```

3. 在原本的activity中接受结果并且进行相关的操作

   需要重写onActivityResult方法

   ```java
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       //判断返回码
       if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
           //获取返回的数据
           List<String> update_titles = data.getStringArrayListExtra("update_titles");
           if (update_titles != null) {
               //更新标题列表
               MainActivity.titles = update_titles;
               //再次设置tablayout和viewpager
               viewpager.setAdapter(new FragmentStateAdapter(this) {
                   //这里创建了一个匿名的FragmentStateAdapter对象
                   @NonNull
                   @Override
                   //这个方法的返回值是一个Fragment对象，当ViewPager需要显示一个页面的时候就会调用
                   //这个方法，根据位置返回一个Fragment对象
                   public Fragment createFragment(int position) {
                       //使用newInstance()方法创建一个Fragment对象，传入的主题参数使用titles[position]获取
                       return TabNewsFragment.newInstance(titles.get(position));
                   }
   
                   @Override
                   //告诉ViewPager一共有多少个页面
                   public int getItemCount() {
                       //总的页面数就是标题的个数
                       return titles.size();
                   }
               });
   
           }
           //viewPager和tab_layout关联在一起
           TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tablayout, viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
               @Override
               public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                   tab.setText(titles.get(position));
               }
   
           });
   
           //启用绑定
           tabLayoutMediator.attach();
       }
   }
   ```

**TabsManager**:



如何切换模式：

在adapter中，

1. 在getView中设置图标的显示状态，根据editMode来确定是否显示

```java
if(editMode) {
    if(position != 0) {
        holder.delButton.setImageResource(R.drawable.delbutton);
        holder.delButton.setVisibility(View.VISIBLE);
    }
    else {
        holder.delButton.setImageResource(R.drawable.forbit_change);
        holder.delButton.setVisibility(View.VISIBLE);
    }
}
else {
    holder.delButton.setVisibility(View.GONE); // 编辑模式为 false 时隐藏
}
```

2. 设置setEditorMode方法，可以设置editMode并且提醒改变视图

   ```java
   public void setEditMode(boolean editMode) {
       this.editMode = editMode;
       notifyDataSetChanged();
   }
   ```

   

在TabsManager中：

维护两个GridView引用和两个Adapter的引用

成员变量:int modeIndex，boolean editMode，维护了一个String类的长量数组，有编辑和完成两个值

modeIndex用于指示按钮上的文字，editMode用于指示编辑模式

按钮的点击事件会转变editMode和modeIndex，然后调用setEditorMode方法来改变item，同时改变按钮的文字



设置动画：

在res/anim中创建一个shake.xml作为抖动效果，然后再Adapter中进行应用

```java
if(editMode) {
    if(position != 0) {
        holder.delButton.setImageResource(R.drawable.delbutton);
        holder.delButton.setVisibility(View.VISIBLE);
        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
        holder.delButton.startAnimation(shake);
        holder.title.startAnimation(shake);
    }
    else {
        holder.delButton.setImageResource(R.drawable.forbit_change);
        holder.delButton.setVisibility(View.VISIBLE);
        holder.delButton.clearAnimation();
        holder.title.clearAnimation();
    }
}
```



**点击效果**：

点击按键，会将Adapter中的list中的元素删除，同时发送一个信息到另一个Adapter中进行添加

点击完成按钮后，会将当前的list存入SharedPreference，然后将全局的两个list进行更改，更新主界面的列表



**接口回调**

在Adapter中定义接口并且使用接口，在Activity的java文件中定义并且实例化接口，并且传入Adapter

**实现Adapter和Adapter，Adapter和Activity之间的交互**

在Adapter中定义接口并且使用接口，在Activity的java文件中定义并且实例化接口，这样接口方法中可以使用Activity中的控件和变量，并且和Adapter中定义的相关控件进行联系（比如说为getView中创建的控件设置点击事件）

adapter之间的交互：可以分别设置并且使用接口，在activity中定义接口，这样可以以activity为中转实现交互





### 新闻栏的详情界面

详情页面：总体是一个RelativeLayout，左上方有退出键，下方有点赞，收藏等图标栏。新闻内容区是一个ScollView布局,可以进行滑动，里面嵌套一个线性布局，用于展示新闻信息。还有一个图片展示栏

在NewsAdapter中对每一个item设置点击事件，点击后跳转到新闻详情界面，同时发送newslist中对应的该item的信息

```java
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
        intent.putExtra("title", newsitem.title);
        intent.putExtra("content", newsitem.content);
        intent.putExtra("image", newsitem.image);
        intent.putExtra("publisher", newsitem.publisher);
        intent.putExtra("publishTime", newsitem.publishTime);
        intent.putExtra("video", newsitem.video);
        v.getContext().startActivity(intent);
    }
});
```

在新闻详情界面对应的java文件中会从intent中获取新闻信息，并且展示

```java
//从主界面获取新闻信息
String titleText = getIntent().getStringExtra("title");
String publishTimeText = getIntent().getStringExtra("publishTime");
String publisherText = getIntent().getStringExtra("publisher");
String contentText = getIntent().getStringExtra("content");
String image = getIntent().getStringExtra("image");
String video = getIntent().getStringExtra("video");
String[] imageLinks = null;
```

**展示图片**：

因为不知道图片有多少张，也不知道图片在界面的位置，所以考虑设置一个固定的图片展台，可以横向滑动查看图片，给图片设置点击事件，点击后可以跳转到一个页面展示完整的图片

```xml
<androidx.cardview.widget.CardView
    android:id="@+id/image_card"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    android:layout_marginBottom="16dp"
    android:visibility="gone"
    app:cardCornerRadius="8dp"
    app:cardElevation="4dp"
    app:cardUseCompatPadding="true">

    <HorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:fillViewport="true"
        android:scrollbars="horizontal">

        <LinearLayout
            android:id="@+id/image_container"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:orientation="horizontal"
            android:gravity="center_vertical"
            android:padding="8dp" />

    </HorizontalScrollView>
</androidx.cardview.widget.CardView>
```

cardview主要是呈现一个装饰的展台效果，注意这里`android:visibility="gone"`默认隐藏，当没有图片时这个框不显示

里面嵌套一个滚动视图，由于滚动视图的限制，里面只能放一个子控件，所以为了应对多个图片的情况需要放一个LinearLayout来放图片

在activity对应的java文件中设置图片（因为逻辑比较简单，所以就不像之前的新闻列表一样写适配器了）

```java
if(imageLinks != null && imageLinks.length > 0) {
    imageCard.setVisibility(View.VISIBLE);
    for (String url : imageLinks) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                300, // 宽度，可根据需要改为 ViewGroup.LayoutParams.WRAP_CONTENT
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(8, 0, 8, 0);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(this).load(url).into(imageView);
        imageContainer.addView(imageView);
    }
}
else {
    imageCard.setVisibility(View.GONE);
}
```

* 如果有图片就将cardView设置为可见
* 遍历图片链接，创建一个ImageView来展示图片
* LayoutParams是LinearLayout中用于子视图向父视图传递布局参数的类，这里创建这个类对象params，并且设置了长，宽和margin
* 然后将params设置到imageView上面去，并且设置imageView的展示方式
* 使用Glide将图片加载到imageView上
* 将imageView加入到外层的容器(LinearLayout)中，这样图片就展示了出来
* 注意如果没有图片要显示设定cardView不显示，防止复用视图时出问题

为了方便用户看图片，专门设置了一个活动页面PictureDisplayActivity，用于展示图片

对于详情页中的图片列表设置点击事件，点击就跳转到图片页，同时传入图片链接，在页面中单独展示图片

图片页使用了PhotoView来展示，可以实现图片缩放功能，其余地方设置点击事件，点击就退出图片页

==注意PhotoView的配置除了要在libs中配，还要在setting中加上一行`maven { url = uri("https://jitpack.io") }`==

```xml
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } //加上这行
    }
}
```

**视频播放**

原本使用videoView，但是没能成功播放，转而使用exoplayer

```java
//如果有视频
if (video != null && !video.trim().isEmpty()) {
    playerview.setVisibility(View.VISIBLE);

    player = new ExoPlayer.Builder(this).build();
    playerview.setPlayer(player);

    MediaItem mediaItem = MediaItem.fromUri(video);
    player.setMediaItem(mediaItem);
    player.prepare();
}
else {
    playerview.setVisibility(View.GONE);
}
```





#### 调用glm的API

##### 导入相关依赖

1. ```java
   glm = { group = "cn.bigmodel.openapi", name = "oapi-java-sdk", version.ref = "glm" }
   glm = "release-V4-2.0.2"
   //这是glm的相关依赖
   ```

2. ```java
   okhttp = { group = "com.squareup.okhttp3", name = "okhttp", version = "4.12.0" }
   gson = { group = "com.google.code.gson", name = "gson", version = "2.10.1" }
   //使用okhttp和gson进行网络请求和json解析，注意助教的demo使用了hutool，这个在android里不能用
   ```

3. 设置网络

   在manifest.xml中：

   ![image-20250712162318674](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250712162318674.png)

   网络配置文件（xml/network_security_config.xml）：

   ```java
   <?xml version="1.0" encoding="utf-8"?>
   <network-security-config>
       <base-config cleartextTrafficPermitted="true" />
   </network-security-config>
   ```

##### 调用api

**示例代码**：

```java
private static String testInvoke() {
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .build();

    JsonObject json = new JsonObject();
    json.addProperty("model", "glm-4-plus");  // 这里用更强的模型，可以根据需要换

    JsonArray messages = new JsonArray();

    // 用户告诉模型任务
    JsonObject systemMessage = new JsonObject();
    systemMessage.addProperty("role", "user");
    systemMessage.addProperty("content", "请帮我总结以下新闻内容，提炼重点和关键信息");
    messages.add(systemMessage);

    // 新闻内容，换成你真实新闻文本
    JsonObject newsMessage = new JsonObject();
    newsMessage.addProperty("role", "user");
    newsMessage.addProperty("content", "【新闻标题】：全球气候变化峰会召开\n" +
            "【新闻内容】：全球各国领导人近日齐聚一堂，讨论应对气候变化的紧迫措施。专家指出，气候变暖带来的极端天气频发，严重影响农业生产和人类生活。会议强调加速清洁能源发展和碳排放减少的必要性。各国承诺将在未来十年内大幅提升环保投入，共同守护地球家园。");
    messages.add(newsMessage);

    // 明确让模型总结的提示
    JsonObject promptMessage = new JsonObject();
    promptMessage.addProperty("role", "user");
    promptMessage.addProperty("content", "请用简洁的语言总结这条新闻，突出关键信息和意义。");
    messages.add(promptMessage);

    json.add("messages", messages);

    String jsonBody = json.toString();

    RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
    Request request = new Request.Builder()
            .url("https://open.bigmodel.cn/api/paas/v4/chat/completions")
            .addHeader("Authorization", "Bearer " + API_KEY)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build();

    try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return "Error: " + response.code() + " " + response.message();
        }
    } catch (IOException e) {
        e.printStackTrace();
        return "Exception: " + e.getMessage();
    }
}
```

关键对象：

1. OkHttpClient client用来发送网络请求
2. Request request要发送的请求
3. JsonObject json = new JsonObject();一个Json对象，用来构建请求的内容
4. Response response返回的内容

glm的api可以接受一段json格式的请求，返回的也是一段json字符串，使用时，应该构造一个json类型的对象，在上面附加上请求的相关参数，然后用它来构建请求，再发送出去

常用参数

| 参数名称        | 类型           | 必填 | 参数描述                                                     |
| :-------------- | :------------- | :--- | :----------------------------------------------------------- |
| model           | String         | 是   | 要调用的模型编码。                                           |
| messages        | List<Object>   | 是   | 调用语言模型时，当前对话消息列表作为模型的提示输入，以JSON数组形式提供，例如{"role": "user", "content": "Hello"}。可能的消息类型包括系统消息、用户消息、助手消息和工具消息。 |
| request_id      | String         | 否   | 由用户端传递，需要唯一；用于区分每次请求的唯一标识符。如果用户端未提供，平台将默认生成。 |
| do_sample       | Boolean        | 否   | 当do_sample为true时，启用采样策略；当do_sample为false时，温度和top_p等采样策略参数将不生效，模型输出随机性会大幅度降低。默认值为true。 |
| stream          | Boolean        | 否   | 该参数在使用同步调用时应设置为false或省略。表示模型在生成所有内容后一次性返回所有内容。默认值为false。如果设置为true，模型将通过标准Event Stream逐块返回生成的内容。当Event Stream结束时，将返回一个data: [DONE]消息。 |
| temperature     | Float          | 否   | 采样温度，控制输出的随机性，必须为正数 取值范围是：[0.0,1.0]， 默认值为 0.75，值越大，会使输出更随机，更具创造性；值越小，输出会更加稳定或确定 建议您根据应用场景调整 top_p 或 temperature 参数，但不要同时调整两个参数 |
| top_p           | Float          | 否   | 用温度取样的另一种方法，称为核取样 取值范围是：[0.0, 1.0]，默认值为 0.90 模型考虑具有 top_p 概率质量 tokens 的结果 例如：0.10 意味着模型解码器只考虑从前 10% 的概率的候选集中取 tokens 建议您根据应用场景调整 top_p 或 temperature 参数，但不要同时调整两个参数 |
| max_tokens      | Integer        | 否   | 控制生成的响应的最大 tokens 数量, 模型支持的最大max_tokens请参考[模型概览。](https://www.bigmodel.cn/dev/howuse/model) |
| response_format | Object         | 否   | 指定模型输出格式，默认为 text, { "type": "text" }：文本输出模式，模型返回普通的文本输出。 { "type": "json_object" }：JSON输出模式，模型返回有效的 JSON 输出。 Beta 版本采用工程实现方式，实现细节请参考[说明文档](https://www.bigmodel.cn/dev/guidelines/JsonFormat) 。 |
| stop            | List           | 否   | 模型遇到stop指定的字符时会停止生成。目前仅支持单个stop词，格式为["stop_word1"]。 |
| tools           | List           | 否   | 模型可以调用的工具。                                         |
| type            | String         | 否   | 工具列表：包括函数调用、知识库检索和网络搜索。参数配置请参考**Tools格式**。 |
| tool_choice     | String或Object | 否   | 用于控制模型选择调用哪个函数的方式，仅在工具类型为function时补充。默认auto，目前仅支持auto。 |
| user_id         | String         | 否   | 终端用户的唯一ID，帮助平台对终端用户的非法活动、生成非法不当信息或其他滥用行为进行干预。ID长度要求：至少6个字符，最多128个字符。 |

最后使用这样的一个类提取content

```java
class ChatResponse {
    Choice [] choices;
    class Choice {
        Message message;
    }

    class Message {
        String content;
    }
}
```



注意要在gradle里将sdk最低版本降到26

glm的依赖似乎有问题

![image-20250712172950844](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/image-20250712172950844.png)

##### 生成新闻总结

```java
package com.java.huhaoran;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SummarizeByGLM {
    private static final String API_KEY = "7e9dbe5b59904cc396ae3dc9c3a17202.bRiYAY52gnozmJ09";

    public static String summarize(String newsTitle, String newsContent) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();


        //构建请求体
        JsonObject requestBody = new JsonObject();

        //加上模型信息
        requestBody.addProperty("model", "glm-4");

        //任务信息用一个列表储存
        JsonArray messages = new JsonArray();

        //添加任务目标
        JsonObject systemJson = new JsonObject();
        systemJson.addProperty("role", "user");
        systemJson.addProperty("system", "你是一个新闻摘要助手，请总结新闻重点");
        messages.add(systemJson);

        //添加新闻内容
        JsonObject newsMessage = new JsonObject();
        newsMessage.addProperty("role", "user");
        String content = "以下是**新闻内容**：\n  **【新闻标题】**：" + newsTitle +
                "\n  **【新闻内容】**：" + newsContent;
        newsMessage.addProperty("content", content);
        messages.add(newsMessage);

        //再次明确任务
        JsonObject promptMessage = new JsonObject();
        promptMessage.addProperty("role", "user");
        promptMessage.addProperty("content", "请用简洁的语言总结这条新闻，突出关键信息和意义，要求总结的内容以“【新闻简报】：”开头");
        messages.add(promptMessage);

        //将信息加入
        requestBody.add("messages", messages);
        //得到请求体的字符串
        String jsonBody = requestBody.toString();
        //构建请求
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("https://open.bigmodel.cn/api/paas/v4/chat/completions")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        //返回请求的结果
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "Error: " + response.code() + " " + response.message();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }


    }
}
```

建一个类用来获取ai的返回数据

```java
//加入ai总结

//加入总结储存功能
SharedPreferences spref = getSharedPreferences("news_summaries", MODE_PRIVATE);
String newsKey = "summary_" + titleText;
String cacheSummary = spref.getString(newsKey, "");
//如果有储存，直接显示
if(cacheSummary != null && cacheSummary != "") {
    summary_content.setText(cacheSummary);
}
//没有总结过，调用
else {
    //注意网络请求必须放在一个单独的线程里
    new Thread(() -> {
        String summary = SummarizeByGLM.summarize(titleText, contentText);
        Gson gson = new Gson();
        try {
            ChatResponse chatResponse = gson.fromJson(summary, ChatResponse.class);
            if(chatResponse.choices == null || chatResponse.choices.length == 0) {
                return;
            }
            String summaryText = chatResponse.choices[0].message.content;
            // 回到主线程更新UI
            runOnUiThread(() -> {
                summary_content.setText(summaryText);
            });
            //储存新闻总结
            SharedPreferences.Editor editor = spref.edit();
            editor.putString(newsKey, summaryText);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(() -> {
                summary_content.setText("AI 总结失败，请稍后再试");
            });
        }
    }).start();
}
```

在详情页中获取数据并且解析，然后使用一个TextView来呈现

使用了SharedPreference来储存ai总结，如果已经有了就不在生成新的，直接使用，如果没有就生成并储存



**但是SharedPreference只适合存储少量的数据，当数据多了会出现问题，考虑改造成数据库**

##### room数据库

包含3个部分（对应java类）

1. **Entity**:

   这个是数据模型，定义数据库的表结构

   
   ```java
   @Entity(tableName = "notes")
   public class Note {
   
   @PrimaryKey(autoGenerate = true)
   private int id;
       
   @ColumnInfo(name = "note_title")
   private String title;
   
   @ColumnInfo(name = "note_content")
   private String content;
   
   // 必须的构造函数（Room使用）
   public Note(String title, String content) {
       this.title = title;
       this.content = content;
   }
   
   // Getter和Setter（Room需要通过它们访问私有字段）
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }
   // 其他getter/setter...
   }
   ```
   一个Entity类相当于一张表，tableName相当于表名，列中的成员变量相当于表的一列，一个类对象相当于表的一行，主键（使用PrimaryKey表示的）则是唯一标示每一行数据的列，不允许重复或者空

2. **DAO（data access object）**:

   是room中定义了访问数据库操作的接口

   
   ```java
   @Dao
   public interface NoteDao {
   // 插入（返回插入行的ID）
   @Insert
   long insert(Note note);
       
   // 批量插入
   @Insert
   void insertAll(Note... notes);
   
   // 更新
   @Update
   void update(Note note);
   
   // 删除
   @Delete
   void delete(Note note);
   
   // 查询所有笔记（按ID降序）
   @Query("SELECT * FROM notes ORDER BY id DESC")
   List<Note> getAllNotes();
   
   // 带参数的查询
   @Query("SELECT * FROM notes WHERE id = :noteId")
   Note getNoteById(int noteId);
   
   // 复杂查询示例
   @Query("SELECT * FROM notes WHERE title LIKE :keyword OR content LIKE :keyword")
   List<Note> searchNotes(String keyword);
   }
   ```

3. **Database**:

   顶层入口类，用于整合数据库中所有组件

   ```java
   //用于标记Database
   //entities用于声明数据库中的实体类（包含的表）
   @Database(entities = {Note.class, User.class}, version = 2)
   public abstract class AppDatabase extends RoomDatabase {
       //用于获取实体类对应的Dao接口对象的抽象方法，编译时会自动补全
       public abstract NoteDao noteDao();
       public abstract UserDao userDao();
       
       //使用单例模式，只有唯一的实例INSTANCE
       private static volatile AppDatabase INSTANCE;
       
       // 预填充数据的回调
       private static final RoomDatabase.Callback roomCallback = 
           new RoomDatabase.Callback() {
               @Override
               public void onCreate(@NonNull SupportSQLiteDatabase db) {
                   super.onCreate(db);
                   // 在新线程中初始化数据
                   Executors.newSingleThreadExecutor().execute(() -> {
                       getInstance(context).noteDao()
                           .insertAll(DEFAULT_NOTES);
                   });
               }
           };
       
       //使用getInstance方法获取数据库类的对象
       //保证单例，需要判断是否有实例，如果有，直接返回，没有才创建
       public static AppDatabase getInstance(Context context) {
           if (INSTANCE == null) {
               synchronized (AppDatabase.class) {
                   if (INSTANCE == null) {
                       INSTANCE = Room.databaseBuilder(
                               context.getApplicationContext(),
                               AppDatabase.class,
                               "app_database.db"
                       )
                       .addCallback(roomCallback)
                       .addMigrations(MIGRATION_1_2)
                       .build();
                   }
               }
           }
           return INSTANCE;
       }
       
       // 数据库迁移方案（从版本1到版本2）
       static final Migration MIGRATION_1_2 = new Migration(1, 2) {
           @Override
           public void migrate(SupportSQLiteDatabase database) {
               // 添加新表
               database.execSQL(
                   "CREATE TABLE users (" +
                   "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "name TEXT NOT NULL)"
               );
               // 修改现有表
               database.execSQL(
                   "ALTER TABLE notes ADD COLUMN author_id INTEGER"
               );
           }
       };
   }
   ```

==DAO接口不需要我们自己实现，只需要打上注解，并且按照对应的格式来定义方法，编译器就会自动帮我们实现一个实现了DAO接口的类，我们只需要使用Database中的方法获取对象即可==

==在Database中注册了的实体类，当创建其对象时，会自动加入到数据库中==



使用实例：

```java
// 获取数据库实例
AppDatabase db = AppDatabase.getInstance(getApplicationContext());

// 获取DAO
NoteDao noteDao = db.noteDao();
UserDao userDao = db.userDao();

// 插入用户
User user = new User("张三");
long userId = userDao.insert(user);

// 插入关联笔记
Note note = new Note("标题", "内容");
note.setAuthorId(userId);
noteDao.insert(note);

// 查询所有笔记及其作者
List<NoteWithUser> notes = noteDao.getNotesWithUsers();
```



注意事项：

1. 导入依赖应该这样:

   ```xml
   annotationProcessor(libs.roomcompiler)
   implementation(libs.roomruntime)
   ```

2. 插入时，如果主键已经存在会报错，可以设置`@Insert(onConflict = OnConflictStrategy.REPLACE)`代表如果冲突则替换



### 实现搜索界面

主界面上面有一个搜索框，点击可以跳转到搜索界面

搜索界面应该实现的功能：

1. 按照关键词，分类，时间范围进行结果搜索
2. 记录用户的搜索记录（因为有关键词，分类，时间三种搜索属性，所以应该分别储存）
3. 当用户搜索时，给出它的搜索记录，如果没有输入，给出全部搜索记录，如果有输入，按照字符串匹配给出相似的搜索记录
4. 一键删除搜索记录，单独删除某条搜索记录
5. 一键删除搜索框的内容



考虑设置一个类用于存放搜索的信息，包括关键词，起止时间，类别

后端api支持多类别搜索



#### 创建一个SearchData类，用于存放搜索的信息

```java
package com.java.huhaoran;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;


//这是一个专门用于管理和储存搜索数据的类，方便进行传递和储存
public class SearchData {
    private String keyword;
    private HashSet<String> categories;
    private String startDate;
    private String endDate;

    // 构造方法
    public SearchData() {
        // 默认构造器
        categories = new HashSet<String>();
    }

    public SearchData(@NonNull String keyword, @Nullable LinkedList<String> categories, @Nullable String startDate, @Nullable String endDate) {
        this.keyword = keyword;
        this.categories = new HashSet<String>(categories);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter 和 Setter
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public HashSet<String> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList categories) {
        this.categories = new HashSet<String>(categories);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // 调试用输出
    @NonNull
    @Override
    public String toString() {
        return "SearchData{" +
                "keyword='" + keyword + '\'' +
                ", categories=" + categories.toString() +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

```



#### 搜索框的实现

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.LinearLayoutCompat xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/searchactivity"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".SearchActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="8dp">

        <!-- 返回按钮 -->
        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:layout_gravity="center_vertical"
            android:src="@drawable/back_in_searchactivity"
            android:contentDescription="Back" />

        <!-- 搜索输入区域 -->
        <RelativeLayout
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:layout_marginStart="8dp"
            android:layout_marginEnd="8dp">

            <!-- EditText 输入框 -->
            <EditText
                android:id="@+id/search_edittext"
                android:layout_width="match_parent"
                android:layout_height="40dp"
                android:hint="Search"
                android:textColorHint="@color/light_black"
                android:textColor="#000000"
                android:background="@drawable/searchbar"
                android:paddingStart="40dp"
                android:paddingEnd="40dp"
                android:textSize="16sp"
                android:inputType="text" />

            <!-- 搜索图标 -->
            <ImageView
                android:id="@+id/icon_search"
                android:layout_width="24dp"
                android:layout_height="24dp"
                android:layout_alignParentStart="true"
                android:layout_centerVertical="true"
                android:layout_marginStart="10dp"
                android:src="@drawable/search_icon"
                android:contentDescription="Search Icon" />

            <!-- 清除输入按钮 -->
            <ImageView
                android:id="@+id/icon_clear"
                android:layout_width="24dp"
                android:layout_height="24dp"
                android:layout_alignParentEnd="true"
                android:layout_centerVertical="true"
                android:layout_marginEnd="10dp"
                android:src="@drawable/clear"
                android:visibility="gone"
                android:contentDescription="Clear Text" />
        </RelativeLayout>

        <!-- 搜索文字按钮 -->
        <TextView
            android:id="@+id/btn_search_text"
            android:layout_width="wrap_content"
            android:layout_height="40dp"
            android:gravity="center"
            android:text="搜索"
            android:textColor="@color/dark_red"
            android:textSize="16sp"
            android:paddingHorizontal="12dp"
            android:layout_gravity="center_vertical"
            android:background="?attr/selectableItemBackground" />
    </LinearLayout>

</androidx.appcompat.widget.LinearLayoutCompat>
```

关键点：

1. 使用relativeLayout来设置搜索框及其图标
2. 搜索框的padding只会影响到搜索框的内容，也就是输入的内容显示范围，但是不会影响搜索框的背景显示，所以可以设置搜索框的padding来让图标在旁边显示
3. clear图标设置成默认为不显示，有输入的时候才设置成显示

设置EditText的监视器，当里面有文字的时候就显示删除按键：

```java
search_edittext.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    //当文字发生改变的时候
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //设置删除图标是否可见
        //如果搜索框文字不为空（s不为空），则设置为可见，否则不可见
        ImageView icon_clear = findViewById(R.id.icon_clear);
        if(!TextUtils.isEmpty(s)) {
            icon_clear.setVisibility(View.VISIBLE);
        }
        else {
            icon_clear.setVisibility(View.GONE);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {}
});
```

删除按键点击事件：

```java
//注册删除按键的点击事件
icon_clear.setOnClickListener(v -> {
   search_edittext.setText("");
});
```



#### 按照类别进行搜索

考虑设置一个网格布局，里面放上所有的类别，设置一个对应的adapter，只要点击就会改变颜色（使用之前的效果），然后设置接口回调来传递信息

**SearchCategoryAdapte**:

用于管理整个网格布局

```java
package com.java.huhaoran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashSet;

//回调接口，用于将选择信息传递到主界面
interface OnCategoryTransferListener {
    void onCategoryTransfer(String tag, boolean isChoosed);
}

public class SearchCategoryAdapter extends BaseAdapter {

    private final String[] categories = {"全部", "文化", "娱乐", "军事", "教育", "健康", "财经", "体育", "汽车", "科技", "社会"};
    //设置是否被选择

    //！！！注意，每个item的isChoose属性都是单独的，不能被其他item所共享，所以这里应该使用一个set，里面放入被选择的item的position
    HashSet<Integer> selectedPositions;
    //设置当前的context
    Context mContext;
    //接口的对象
    OnCategoryTransferListener listener;

    class ViewHolder
    {
        TextView textView;
    }

    //设置接口对象
    public void setOnCategoryTransferListener(OnCategoryTransferListener listener)
    {
        this.listener = listener;
    }

    public SearchCategoryAdapter(Context mContext)
    {
        this.mContext = mContext;
        selectedPositions = new HashSet<>();
    }

    @Override
    public int getCount()
    {
        return categories.length;
    }

    @Override
    public Object getItem(int position)
    {
        return categories[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //生成View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果没有可以复用的view，就生成一个
        if(convertView == null) {
            //生成一个ViewHolder
            holder = new ViewHolder();
            //生成view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_category, parent, false);
            //从刚刚生成的view中获取TextView
            holder.textView = (TextView) convertView.findViewById(R.id.category);
            //将holder保存在view中
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //根据position设置内容
        holder.textView.setText(categories[position]);
        //根据选中状态设置背景
        if(selectedPositions.contains(position)) {
            if(position == 0) {
                holder.textView.setBackgroundResource(R.drawable.click_effect3);
            }
            else {
                holder.textView.setBackgroundResource(R.drawable.click_effect2);
            }
        }
        else {
            holder.textView.setBackgroundResource(R.drawable.click_effect);
        }

        //设置点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = selectedPositions.contains(position);

                if (position == 0) {
                    // 点击“全部”时切换全选状态
                    if (isSelected) {
                        // 当前是选中状态，取消所有选择
                        selectedPositions.clear();
                    } else {
                        // 清除其他所有选择，只选中“全部”
                        selectedPositions.clear();
                        selectedPositions.add(0);
                    }
                    notifyDataSetChanged();

                    // 通知外部更新所有分类的选择状态
                    if (listener != null) {
                        for (int i = 1; i < categories.length; i++) {
                            listener.onCategoryTransfer(categories[i], false); // 取消其他
                        }
                        listener.onCategoryTransfer(categories[0], !isSelected); // 更新“全部”
                    }

                } else {
                    // 普通分类点击行为
                    if (isSelected) {
                        selectedPositions.remove(position);
                    } else {
                        selectedPositions.add(position);
                    }

                    // 若“全部”被选中，取消它
                    if (selectedPositions.contains(0)) {
                        selectedPositions.remove(0);
                    }

                    notifyDataSetChanged();

                    if (listener != null) {
                        listener.onCategoryTransfer(categories[position], !isSelected);
                    }
                }
            }

        });

        return convertView;
    }
}
```

要生成的item总数就是类别的个数，所以getCount返回categories的长度

维护一个HashSet，里面记录了被选择的类别的position

核心方法：getView，用于生成具体的一个item，首先先检测convertView是否为空，如果不为空进行复用，否则创建一个新的

根据当前的position来决定item上面显示的类别名，然后根据该类别是否被选择来加载标签背景

对于每个标签都要设置一个点击事件，该点击事件会将当前的标签的选择状态进行反转，同时通知ui界面进行改变（加载相反的背景），同时设置了一个回调接口，该类持有该接口的对象引用，可以通过方法进行传入，而接口内方法的定义放在activity中做，这样就可以访问activity中的数据，这里设定接口中的方法会根据传入的参数来对activity的类别集合中的项进行删除或者添加，从而实现点击事件能够操控activity中的集合



#### 按照时间进行搜索

如果直接使用DatePicker，展示效果不好，获取数据麻烦，所以考虑在页面中创建两个文本框，设置点击事件，只要点击就会弹出日期选择框提示用户进行选择

```java
private void showDatePickerDialog(boolean isStartDate) {
    //获取当前的年，月，日
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    //定义了一个DatePickerDialog控件
    //参数依次是：上下文，监听，默认年，月，日
    //监听器就是点击了日历中的确定会执行的代码
    //监听器的参数是当前的DatePicker对象，选择的年，月，日
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
        //获取日期字符串，这里将年，月，日格式化为4位，2位，2位
        //注意月是0-11，所以要加1
        String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, month1 + 1, dayOfMonth);
        //更新文本框
        if (isStartDate) {
            if(searchData.getEndDate() != null) {
                //如果结束日期不为空，则判断是否大于开始日期
                if(dateCompare(date, searchData.getEndDate()) > 0) {
                    //如果更大，则弹出提示
                    Toast.makeText(SearchActivity.this, "开始日期不能大于结束日期", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            textStartDate.setText(date);
            searchData.setStartDate(date);
        } else {
            if(searchData.getStartDate() != null) {
                //如果开始日期不为空，则判断是否小于开始日期
                if(dateCompare(date, searchData.getStartDate()) < 0) {
                    //如果更小，则弹出提示
                    Toast.makeText(SearchActivity.this, "结束日期不能小于开始日期", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            textEndDate.setText(date);
            searchData.setEndDate(date);
        }
    }, year, month, day);

    // 防止用户选未来
    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


    //展示日历
    datePickerDialog.show();
}
```



这个是显示日历并且进行选择的方法，关键在于构造了一个 DatePickerDialog类的对象，参数依次是上下文，日期选择监听器，默认的年、月、日，监听器是用户点击日历的确认之后会执行的代码，参数依次是当前日期选择器的对象，用户选择的年、月、日，这里就是将用户选择的年、月、日打在文本框里，同时进行储存即可，注意要处理一些错误选择

此时只是构造了一个DatePickerDialog的对象，需要调用show方法来展现出日历来

最后设置文本框的点击事件，设定为点击后就执行showDatePickerDialog，该方法就会生成我们刚刚设定好的DatePickerDialog对象，并且将其展示出来



#### 搜索结果界面





#### 显示历史搜索记录

在数据库中建立一个用于储存历史搜索记录的表单

因为要求实现按照关键词，类别，时间来进行搜索，所以这里应该分别储存这三个属性

储存搜索的时间

搜索记录的显示应该使用一个RecyclerLayout，一次只显示一定的条数，下方有一个更多的选项，点击可以继续显示

可以清空记录（数据库的删除功能）







或许可以加上一个无痕搜索（浏览），开了这个以后不会记录浏览和搜索的记录





### bug

1. 在刚刚打开app时点击第6个标签页就会闪退，点击其他页面就不会，先点击其他页面再点击第6个页面也不会

   解决：viewpager.setOffscreenPageLimit(titles.length);加上这一行代码，将所有页面全部预先缓存就不会闪退了

   ```java
   if (response != null && response.data != null) {
       if (!isAdded() || getActivity() == null) return; // 添加这个判断
       requireActivity().runOnUiThread(() -> {
           if (!isAdded() || getActivity() == null) return; // 再保险地判断一次
           NewsAdapter adapter = new NewsAdapter(response.data);
           recyclerView.setAdapter(adapter);
           recyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));
       });
   }
   ```

   在这里添加检查似乎也可以避免闪退，而且性能更好

2. 一些图片无法加载出来

   在manifest中加上一行

   

   ```xml
   android:usesCleartextTraffic="true"
   ```

   ![在这里插入图片描述](https://raw.githubusercontent.com/hhr2449/pictureBed/main/img/fe8eb3aee743256793e4cd1c531c13fb.png)

​	可能是由于网络协议的兼容问题

3. 标签增删界面点击item会闪退？？？

   问题在于初始化Adapter中的list的时候我使用了=，这样Adapter中的list和全局list指向同一个对象，出现重复增删的错误

4. 重用带来的错误：

   为了减少重新创建item的消耗，我们设置了重新使用废弃的View的机制

   ```java
   if(convertView == null) {
       holder = new TitlesNoUseAdapter.ViewHolder();
       convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_manager, parent, false);
       holder.title = (TextView) convertView.findViewById(R.id.title);
       holder.delButton = (ImageView) convertView.findViewById(R.id.delButton);
       convertView.setTag(holder);
   }
   else {
       holder = (TitlesNoUseAdapter.ViewHolder) convertView.getTag();
   }
   ```

​		但是当转为编辑模式时，item会带上抖动特效和点击事件，当非编辑模式重用item,如果不在getView时取消掉，item回想编辑模式一样抖动和可点击，所以在getView中除了在editorMode为真true设置特效，在为false时还要取消特效

```java
if(editMode) {
    holder.delButton.setImageResource(R.drawable.delbutton);
    holder.delButton.setVisibility(View.VISIBLE);
    Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
    holder.delButton.startAnimation(shake);
    holder.title.startAnimation(shake);
    holder.title.setOnClickListener(v -> {
        //只要点击，就调用接口中的方法来调整list
        mOnTitlesNoUseTransferListener.onTitlesNoUseTransfer(titlesNoUse.get(position));
    });
}
else {
    //取消各种效果
    holder.delButton.setVisibility(View.GONE); // 编辑模式为 false 时隐藏
    holder.title.clearAnimation();
    holder.delButton.clearAnimation();
    holder.title.setOnClickListener(null);
}
```

### 功能思考

图片展示样式(实现)

懒加载

缓存，不重复加载

#### 一个可能实现点赞，收藏，评论的方案

在FetchNews.NewsItem中增加属性记录点赞数，收藏数，评论列表，并且默认为空

在服务器中保存键值对，key是新闻标题，value是新闻相关信息，每次爬取新闻都需要去数据库中检查是否记录了相关点赞评论信息

后端服务器能否实现？？

#### 申请分享

#### 图像识别来剔除低质量图片



