package com.hiya.se.ids;

import java.util.function.Supplier;

@FunctionalInterface
public interface IGame
{
     String  play(String name);

     //默认方法允许接口方法定义默认实现，子类方法不必须实现此方法而就可以拥有该方法及实现。
     /*
      * 默认方法主要优势是提供了一种扩展接口的方法，而不破坏现有代码。如果一个已经投入使用的接口需要扩展一个新的方法，在JDK8以前，我们必须再该接口的所有
      * 实现类中都添加该方法的实现，否则编译会出错。如果实现类数量很少且我们有修改的权限，可能工作量会少，但是如果实现类很多或者我们没有修改代码的权限，这
      * 样的话就很难解决了。而默认方法提供了一个实现，当没有显式提供时就默认采用这个实现，这样新添加的接口就不会破坏现有的代码。

             默认方法另一个优势是该方法是可选的，子类可以根据不同的需求而且经override或者采用默认实现。例如我们定义一个集合几口，其中有增、删、改等操作，如果我
             们的实现类90%都是以数组保存数据，那么我们可以定义针对这些方法给出默认实现，而对于其他非数组集合或者有其他类似业务，可以选择性复写接口中默认方法。
             (由于接口不允许有成员变量，所以本示例旨在说明默认方法的优势，并不具有生产可能性)具体参照如下代码
      */
     //增加默认实现
     default void addOneObj(Object object){
         System.out.println("default add");
     }
     //删除默认实现
     default void delOneObj(Object object){
         System.out.println("default del");
     }
     //更新默认实现
     default void updateOneObj(Object object){
         System.out.println("default del");
     }
     
     /*
      * java8中为接口新增了一项功能：定义一个或者更多个静态方法。类似于类中的静态方法，接口定义的静态方法可以独立于任何对象调用。所以，在调用静态方
      * 法时，不需要实现接口，也不需要接口的实例，也就是说和调用类的静态方法的方式类似。语法如：接口名字.静态方法名。
      */
     static String getName()  
     {  
         return "接口IGame。。。";  
     }  
     
     public static IGame create(final Supplier<IGame> supplier)
     {
         return supplier.get();
     }
}
