# Back scrather

该工具主要为了减少单测编写中的重复工作。目前完成的版本为basic_function，实现了对常见格式的语句生成对应的PowerMock代码。

# 使用方法

1. 使用命令`java -jar back_scratcher.jar y/n` 运行jar包,其中参数n/y代表是否添加注释。![4C6Lh4.png](https://z3.ax1x.com/2021/09/13/4C6Lh4.png)

2. 将要mock的语句粘贴到输入栏

   ![4C6X9J.png](https://z3.ax1x.com/2021/09/13/4C6X9J.png)

3. 生成的代码会输出在屏幕，同时会**自动复制在剪切板上不需要手动复制**。

4. 输入`exit`退出程序。

   ![4C6qNF.png](https://z3.ax1x.com/2021/09/13/4C6qNF.png)

# 使用说明

1. 该程序生成的代码是基于PowerMock框架的，所以需要使用者熟悉PowerMock的写法。
2. 该程序目前不能直接对一个方法进行自动生成（这是未来努力的目标），只能对具有一定格式的代码进行mock。
3. 程序只负责生成一定格式的代码，具体程序执行流程还需要使用者自身控制。如对Stream流的控制，Map容器中取值的控制。

# 语句类型

## 特殊返回值方法

1. `A a, B b, C c`

   生成代码：

   ```java
   A a = mock(A.class);
   B b = mock(B.class);
   C c = mock(C.class);
   ```

   说明：

   这种格式说明是某个方法的入参，分别对每个单独的`X x`进行mock。

2. `List<ClassA> list = b.function(arg1);`

   生成代码：

   ```java
   List<ClassA> list = new ArrayList<>();
   ClassA classA = mock(ClassA.class);
   list.add(classA);
   doReturn(list).when(b).function(any());
   ```

   说明：

   返回值是`List`类型的代码，会自动往定义的list中塞入一个mock好的对象。

3. `Optional<ClassA> optional = b.function(arg1);`

   生成代码：

   ```java
   ClassA classA = mock(ClassA.class);
   Optional<ClassA> optional = Optional.of(classA);
   doReturn(optional).when(b).function(any());
   ```

   说明：

   返回值是`Optional`类型的代码，默认往容器中塞入一个mock的对象。

4. `ClassA<ClassB> a = b.function(arg1);`

   生成代码：

   ```java
   ClassB classB = mock(ClassB.class);
   ClassA<ClassB> a = mock(ClassA.class);
   doReturn(a).when(b).function(any());
   ```

   说明：

   返回值是带有泛型且不是上述的`List`或`Optional`。

## 普通方法

1. `ClassA a = b.function(arg1, arg2);`

   生成代码：

   ```java
   ClassA a = mock(ClassA.class);
   doReturn(a).when(b).function(any(), any());
   
   ```
   
2. `a = b.function(arg1);`

    生成代码：

    ```java
    A a = mock(A.class);
    doReturn(a).when(b).function(any());
    ```

    说明：

    对于语句中没有体现返回类型的代码，默认类型为返回变量名的大驼峰形式。
    
3. `b.function(arg);`

    生成代码：

    ```java
    doNothing().when(b).function(any());
    ```

    说明：

    代码中没有体现返回值，默认认为该方法返回类型为`void`


   ## 私有方法

   1. `ClassA a = function(arg1);`

      生成代码：

      ```java
      ClassA a = mock(ClassA.class);
      doReturn(a).when(spy, "function", any());
      ```

      说明：

      这种格式一般说明`function()`为这个类的private方法，所以需要利用powermock对私有方法进行mock，在生成语句之前需要使用者自己添上对该类的spy语句。

   3. `a = function(arg1);`

      生成代码：

      ```java
      A a = mock(A.class);
      doReturn(a).when(spy, "function", any());
      ```

   3. `function(arg);`

      ```java
      doNothing().when(spy, "function", any());
      ```

      ## 静态方法

1. ClassA a = ClassB.function(arg1);`

   生成代码：

   ```java
   ClassA a = mock(ClassA.class);
   doReturn(a).when(ClassB.class, "function", any());
   ```

   说明：

   这种格式说明`function()`是`ClassB`中的静态方法。

   2. `a = ClassB.function(arg1);`

      生成代码：

      ```java
      A a = mock(A.class);
      doReturn(a).when(ClassB.class, "function", any());
      ```

   3. `ClassB.function();`

      生成代码：

      ```java
      doNothing().when(ClassB.class, "function");
      ```

## 联系方式

希望能听到您宝贵的意见。:relaxed::relaxed:

QQ:1578528245

WeChat:jarforu66

Tel:18644045901

