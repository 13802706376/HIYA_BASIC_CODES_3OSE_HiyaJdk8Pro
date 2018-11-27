package com.hiya.se.lambda;

@FunctionalInterface
public interface FunctionInterfaceYes1
{
    // 抽象方法
    String msg(String info);
 
    // java.lang.Object中的方法不是抽象方法
    public boolean equals(Object var1);
 
    // default不是抽象方法 ,默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要。接口提供的默认方法会被接口的实现类继承或者覆写，例子代码如下
    public default void defaultMethod(){}
 
    // static不是抽象方法,Java 8带来的另一个有趣的特性是在接口中可以定义静态方法
    public static void staticMethod(){}
}
