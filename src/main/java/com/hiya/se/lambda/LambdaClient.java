package com.hiya.se.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class LambdaClient
{

    public static void main(String[] args)
    {
        List<String> ss =  Arrays.asList( "a", "b", "d" );
        
        //lambda表达式
        ss.forEach(e -> System.out.println(e));
        ss.forEach(System.out::println);
        
        //指定类型
        ss.forEach((String e) -> System.out.println( e ));
        
        //引用局部变量，程序块
        String charBegin = "begin";
        String charEnd = "end";
        ss.forEach( e-> 
        {
            System.out.println( e+charBegin );
            System.out.println( e+charEnd);
        });
        
        //返回值 
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> 
        {
            int result = e1.compareTo( e2 );
            return result;
        } );
        
        /*
         * 函数接口指的是只有一个函数的接口，这样的接口可以隐式转换为Lambda表达式。
         * 1、该注解只能标记在"有且仅有一个抽象方法"的接口上。
            2、JDK8接口中的静态方法和默认方法，都不算是抽象方法。
            3、接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
            4、该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。
         * @FunctionalInterface
         * public interface Callable<V> {
         */
        FunctionInterfaceYes1 t1 = new FunctionInterfaceYes1() 
        {
            @Override
            public String msg(String info) 
            {
                return info + ",world";
            }
        };
        t1.msg("444");
        
        FunctionInterfaceYes1 t10 = info -> info + "world!";
        t10.msg("555");
        
        FunctionInterfaceYes2 t20 = (info,info2) ->  info + "world!";
        t20.msg("555","444");
        
        //FunctionInterfaceNon t30 = info -> info + "world!";
        //t30.msg("555");
        
        
     
   
        //filter ：过滤的作用 
        Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");
        Optional<String> longest = names.filter(y1 -> y1.startsWith("L")).findFirst();
        Optional<String> lNameInCaps = longest.map(String::toUpperCase);
  
        
        //orElseThrow 在当遭遇Null时，决定抛出哪个Exception时使用：
        longest.orElseThrow(ArrayIndexOutOfBoundsException::new);
        
        
        
 
        List<String> words = new ArrayList<String>();
        words.add("hello");
        words.add("word");
 
        //flatMap 将多个Stream连接成一个Stream，这时候不是用新值取代Stream的值，与map有所区别，这是重新生成一个Stream对象取而代之。
        //将words数组中的元素再按照字符拆分，然后字符去重，最终达到["h", "e", "l", "o", "w", "r", "d"]
        //如果使用map，是达不到直接转化成List<String>的结果
        List<String> stringList = words.stream().flatMap(word -> Arrays.stream(word.split(""))).distinct().collect(Collectors.toList());
        String stringLists = words.stream().flatMap(word -> Arrays.stream(word.split(""))).distinct().collect(Collectors.joining(";"));
        System.out.println("flatMap");
        stringList.forEach(e -> System.out.println(e));
        System.out.println(stringLists);
        
        //map 给定单词列表["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"],
        //将一种类型的值转换为另外一种类型的值
       // List<String> stringList2 = words.stream().map(word -> word.split("")).distinct().collect(Collectors.joining(";"));
        //String ids = words.stream().filter(s3 -> StringUtils.isNotBlank(s3)).map(s3 -> user.toString()).distinct().collect(Collectors.joining(","));
        
        System.out.println("flatMap");
        stringList.forEach(e -> System.out.println(e));
        
        
    }
}
