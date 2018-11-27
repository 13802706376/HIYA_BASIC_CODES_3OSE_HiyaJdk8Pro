package com.hiya.se.function;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.hiya.se.lambda.Person;

public class FunctionReferenceClient
{
  
    
        public static void main(String[] args)
        {
               //----------------------------------------方法引用--------------------------------------//
               Person[] pArr = new Person[]{
                       new Person("003", LocalDate.of(2016,9,1)),
                       new Person("001", LocalDate.of(2016,2,1)),
                       new Person("002", LocalDate.of(2016,3,1)),
                       new Person("004", LocalDate.of(2016,12,1))};
               
                // 1 使用匿名类-----
               Arrays.sort(pArr, new Comparator<Person>() {
                   @Override
                   public int compare(Person a, Person b) {
                       return a.getBirthday().compareTo(b.getBirthday());
                   }
               });
               System.out.println(Arrays.asList(pArr));
               
               //2  Lambda表达式 -----
               Arrays.sort(pArr, (Person a,Person b)  -> 
               {
                   return a.getBirthday().compareTo(b.getBirthday());
               });
               System.out.println(Arrays.asList(pArr));
               
               //3 使用lambda表达式和类的静态方法
               Arrays.sort(pArr, ( Person a, Person b)  ->  Person.compareByAge(a,b));
               System.out.println(Arrays.asList(pArr));
               
               //4 使用方法引用，引用的是类的静态方法
               Arrays.sort(pArr,  Person::compareByAge);
               System.out.println(Arrays.asList(pArr));
               
               
               FunctionReferenceContext fr = new FunctionReferenceContext();
               String inStr = "lambda add power to Java";
               //MyStringOps::strReverse 相当于实现了接口方法func() 
               // 并在接口方法func()中作了MyStringOps.strReverse()操作
                String outStr = fr.stringOp(HiyaString::strReverse, inStr);
               System.out.println("Original string: " + inStr);
               System.out.println("String reserved: " + outStr);
               
               ArrayList<MyClass> a1 = new ArrayList<MyClass>();
               a1.add(new MyClass(1));
               a1.add(new MyClass(4));
               a1.add(new MyClass(2));
               a1.add(new MyClass(9));
               a1.add(new MyClass(3));
               a1.add(new MyClass(7));
               //FrContext::compareMC生成了抽象接口Comparator定义的compare()方法的实例。  
               MyClass maxValObj = Collections.max(a1, FunctionReferenceContext::compareMC);
               System.out.println("Maximum value is: " + maxValObj.getValue());
               
               
               //引用的方法是fr 对象的compareByName方法。
               Arrays.sort(pArr, fr::compareByName);
               
               //MyClass :: new
               MyFunc1 myClassCons = MyClass :: new;
               MyClass mc = myClassCons.func(100);
               System.out.println("val in mc is: " + mc.getValue());
               
               
               
               
               
               
        }
}
