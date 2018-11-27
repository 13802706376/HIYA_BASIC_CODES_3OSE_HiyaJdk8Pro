package com.hiya.se.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamClient
{

    static List<Student> stuList = new ArrayList<Student>();
    static Stream<Student> stuList2 = stuList.stream();

    public static void init()
    {
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            stuList.add(new Student("student" + i, random.nextInt(50) + 50));
        }
    }
    
    private static <T> void printList(List<T> stuList )
    {
        stuList.forEach(stu -> 
        {
            System.out.print(stu.toString());
            System.out.println();
        });
    }
    

    public static void main(String[] args)
    {
        StreamClient sc = new StreamClient();
        init();
        printList(stuList);
        System.out.println("-----------------------------------------");

/*        
         * 基础知识
         * Predicate：断定 
         * Consumer：消费者
         * Supplier：供应商
         

        // 1列出班上超过85分的学生姓名，并按照分数降序输出用户名字
        //在collect方法执行之前，filter、sorted、map方法还未执行，只有当collect方法执行时才会触发之前转换操作
        List<String> nameList =
                stuList.stream()  //转化成stream 
                .filter(x-> x.getScore()>85 && x.getScore()<99 )  //filter主要是过滤，满足条件的 
                .sorted(Comparator.comparing(Student::getScore).reversed()) //按照分数降序  
                .map(Student::getName) //输出用户名字
                .collect(Collectors.toList()); //输出  List<String> nameList
        printList(stuList);
        System.out.println("-----------------------------------------");
        printList(nameList);
        
        
         * 1.stream不存储数据
            2.stream不改变源数据
            3.stream的延迟执行特性
            4.通常我们在数组或集合的基础上创建stream，stream不会专门存储数据，对stream的操作也不会影响到创建它的数组和集合,
                对于stream的聚合、消费或收集操作只能进行一次，再次操作会报错
         
        Stream<String> stream = Stream.generate(()->"user").limit(20);
        stream.forEach(System.out::println);
        //stream.forEach(System.out::println);  //报错 
        
        
        //创建流
        int[] arr = new int[]{1,2,34,5};
        IntStream intStream = Arrays.stream(arr);
        
        Student[] studentArr = new Student[]{new Student("s1",29),new Student("s2",27)};
        Stream<Student> studentStream = Arrays.stream(studentArr);
        
        Stream<Integer> stream1 = Stream.of(1,2,34,5,65);
        stream1.forEach(System.out::println);
        
        List<String> strs = Arrays.asList("11212","dfd","2323","dfhgf");
        //创建普通流
        Stream<String> stream21  = strs.stream();
        //创建并行流
        Stream<String> stream22 = strs.parallelStream();
        
        //创建一个空的stream
        Stream<Integer> streamEmpty  = Stream.empty();
        
        //创建无限流，通过limit提取指定大小
        Stream.generate(()->"number"+new Random().nextInt()).limit(100).forEach(System.out::println);
        Stream.generate(()->new Student("name",10)).limit(20).forEach(System.out::println);
        
        
         * map：转换流，将一种类型的流转换为另外一种流
         * 将String数组中字母转换为大写
         
        String[] arr31 = new String[]{"yes", "YES", "no", "NO"};
        Arrays.stream(arr31).map(x -> x.toLowerCase()).forEach(System.out::println);
        
        
         *  filter：过滤流，过滤流中的元素
         
        Integer[] arr41 = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        Arrays.stream(arr41).filter(x->x>3&&x<8).forEach(System.out::println);
        
        
         *   flapMap：拆解流，将流中每一个元素拆解成一个流
         
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        Stream.of(arr1, arr2, arr3).map(Arrays::stream).forEach(System.out::println);
        
        
         * * max、min
                  最大最小值
         
        Stream.of(arr1).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        Stream.of(arr1).min(Comparator.comparing(String::length)).ifPresent(System.out::println);
        
        
        
         * sorted：对流进行排序
         *  * reversed(),java8泛型推导的问题，所以如果comparing里面是非方法引用的lambda表达式就没办法直接使用reversed()
             * Comparator.reverseOrder():也是用于翻转顺序，用于比较对象（Stream里面的类型必须是可比较的）
             * Comparator. naturalOrder()：返回一个自然排序比较器，用于比较对象（Stream里面的类型必须是可比较的）
         
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
        Arrays.stream(arr1).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
        
        //limit，限制从流中获得前n个数据
        //skip，跳过前n个数据
        Stream.iterate(1,x->x+2).limit(10).forEach(System.out::println);
        Stream.iterate(1,x->x+2).skip(2).limit(5).forEach(System.out::println);
        
        //可以把两个stream合并成一个stream（合并的stream类型必须相同）只能两两合并
        Stream<String> stream51 = Stream.of(arr1);
        Stream<String> stream52 = Stream.of(arr2);
        Stream.concat(stream51,stream52).distinct().forEach(System.out::println);
        
        //count 计算数量
        long count = Stream.of(arr1).count();
        
        
        // findFirst查找第一个
        String str =  Stream.of(arr1).parallel().filter(x->x.length()>3).findFirst().orElse("noghing");
        
        
         *  findAny
         *找到所有匹配的元素
        * 对并行流十分有效
        * 只要在任何片段发现了第一个匹配元素就会结束整个运算
         
        Optional<String> optional = Stream.of(arr1).parallel().filter(x->x.length()>3).findAny();
        
        //使用Optional可以在没有值时指定一个返回值
        Integer[] arr61 = new Integer[]{4,5,6,7,8,9};
        Integer result = Stream.of(arr61).filter(x->x>9).max(Comparator.naturalOrder()).orElse(-1);
        System.out.println(result);
        Integer result1 = Stream.of(arr61).filter(x->x>9).max(Comparator.naturalOrder()).orElseGet(()->-1);
        System.out.println(result1);
        Integer result2 = Stream.of(arr61).filter(x->x>9).max(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        System.out.println(result2);
        
        //收集结果(生成List，生成Set，生成Map  ，生成数组，生成制定类型 )
        List<Student> list = stuList.stream().collect(Collectors.toList());
        Set<Student> set = stuList.stream().collect(Collectors.toSet());
        Map<String,Integer> map = stuList.stream().collect(Collectors.toMap(Student::getName,Student::getScore,(s,a)->s+a));
        Student[] s = stuList.stream().toArray(Student[]::new);
        HashSet<Student> s55 = stuList.stream().collect(Collectors.toCollection(HashSet::new));
        */
        //统计
        IntSummaryStatistics summaryStatistics = stuList.stream().collect(Collectors.summarizingInt(Student::getScore));
        System.out.println("getAverage->"+summaryStatistics.getAverage());
        System.out.println("getMax->"+summaryStatistics.getMax());
        System.out.println("getMin->"+summaryStatistics.getMin());
        System.out.println("getCount->"+summaryStatistics.getCount());
        System.out.println("getSum->"+summaryStatistics.getSum());
        
        
        //分组和分片
        Student [] students  = new Student[100];
        for (int i=0;i<30;i++){
            Student student = new Student("user1",i);
            students [i] = student;
        }
        for (int i=30;i<60;i++){
            Student student = new Student("user2",i);
            students [i] = student;
        }
        for (int i=60;i<100;i++){
            Student student = new Student("user3",i);
            students [i] = student;
        }
        Map<String,List<Student>> map101 =  Arrays.stream(students).collect(Collectors.groupingBy(Student::getName));
        map101.forEach((x,y)-> System.out.println(x+"->"+y));
        
        //如果只有两类，使用partitioningBy会比groupingBy更有效率 ,大于50的分片，小于50的分片 （只有2类）
        Map<Boolean,List<Student>> map102 = Arrays.stream(students).collect(Collectors.partitioningBy(x->x.getScore()>50));
        map102.forEach((x,y)-> System.out.println(x+"->"+y));
        
        //toSet指定类型
        Map<String,Set<Student>> map103 = Arrays.stream(students).collect(Collectors.groupingBy(Student::getName,Collectors.toSet()));
        map103.forEach((x,y)-> System.out.println(x+"->"+y));
        
        //List<Student>做聚合操作 （总数量）
        Map<String,Long> map1 = Arrays.stream(students).collect(Collectors.groupingBy(Student::getName,Collectors.counting()));
        map1.forEach((x,y)-> System.out.println(x+"->"+y));
        
        //List<Student>做聚合操作 （总数相加）
        Map<String,Integer> map2 = Arrays.stream(students).collect(Collectors.groupingBy(Student::getName,Collectors.summingInt(Student::getScore)));
        map2.forEach((x,y)-> System.out.println(x+"->"+y));
        
        //List<Student>做聚合操作 （最大值）
        Map<String,Optional<Student>> map3 = Arrays.stream(students).collect(Collectors.groupingBy(Student::getName,Collectors.maxBy(Comparator.comparing(Student::getScore))));
        map3.forEach((x,y)-> System.out.println(x+"->"+y));
        
        
        //原始类型流 和 Stream的相互转换  
        //在数据量比较大的情况下，将基本数据类型（int,double...）包装成相应对象流的做法是低效的，因此，我们也可以直接将数据初始化为原始类型流，在原始类型流上的操作与对象流类似
        DoubleStream doubleStream = DoubleStream.of(0.1,0.2,0.3,0.8);
        IntStream intStream = IntStream.of(1,3,5,7,9);
        IntStream stream1 = IntStream.rangeClosed(0,100);
        IntStream stream2 = IntStream.range(0,100);
        Stream<Double> stream = doubleStream.boxed();
        doubleStream = stream.mapToDouble(Double::new);
        
        //peek，监控方法
       // 串行流和并行流的执行顺序
        Stream<Integer> stream701 = Stream.iterate(1, x -> x + 1).limit(10);
        stream701.peek(sc::peek1).filter(x -> x > 5)
                .peek(sc::peek2).filter(x -> x < 8)
                .peek(sc::peek3)
                .forEach(System.out::println);
        
        Stream<Integer> stream702 = Stream.iterate(1, x -> x + 1).limit(10).parallel();
        stream702.peek(sc::peek1).filter(x -> x > 5)
                .peek(sc::peek2).filter(x -> x < 8)
                .peek(sc::peek3)
                .forEach(System.out::println);
        
        
        
        
        
        
        
        
        
    }
    
    
    public  void peek1(int x) {
        System.out.println(Thread.currentThread().getName() + ":->peek1->" + x);
    }
 
    public void peek2(int x) {
        System.out.println(Thread.currentThread().getName() + ":->peek2->" + x);
    }
 
    public void peek3(int x) {
        System.out.println(Thread.currentThread().getName() + ":->final result->" + x);
    }
    
    

}
