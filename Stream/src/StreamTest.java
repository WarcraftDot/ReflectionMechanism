import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println(findFirst.get());
        System.out.println(findAny.get());
        System.out.println(anyMatch);
    }

    //筛选员工中未满18周岁的人，并形成新的集合
    private static void filter01() {
        initPerson();
        List<Person> collect = personList.stream().filter(x -> x.getAge() > 18).collect(Collectors.toList());
        System.out.println(collect);
    }

    static List<Person> personList = new ArrayList<Person>();

    private static void initPerson() {
        personList.add(new Person("张三", 8, 3000));
        personList.add(new Person("李四", 18, 5000));
        personList.add(new Person("王五", 28, 7000));
        personList.add(new Person("孙六", 38, 9000));
    }

    //获取String集合中最长的元素
    private static void test02() {
        List<String> list = Arrays.asList("ZhangSan", "LiSi", "WangWu", "SunLiu");
        Comparator<? super String> comparator = Comparator.comparing(String::length);
        Optional<String> max = list.stream().max(comparator);
        System.out.println(max);

    }

    //获取Integer集合中的最大值
    private static void test05() {
        List<Integer> list = Arrays.asList(1, 17, 27, 7);
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(max2);
    }

    //获取员工中年龄最大的人
    private static void test06() {
        initPerson();
        Comparator<? super Person> comparator = Comparator.comparingInt(Person::getAge);
        Optional<Person> max = personList.stream().max(comparator);
        System.out.println(max);

    }

    //计算integer集合中大于10的元素的个数
    private static void test07() {
        List<Integer> list = Arrays.asList(1, 17, 27, 7);
        long max = list.stream().filter(x -> x > 10).count();
        System.out.println(max);
    }

    //字符串大写
    private static void test08() {
        List<String> list = Arrays.asList("ZhangSan", "LiSi", "WangWu", "SunLiu");
        list.forEach(t -> t.toUpperCase());
        List<String> collect = list.stream().map(t -> t.toUpperCase()).collect(Collectors.toList());
        List<String> collect2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect2);

    }

    //整数数组每个元素+3
    private static void test09() {
        List<Integer> list = Arrays.asList(1, 17, 27, 7);
        List<Integer> collect = list.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println(collect);
    }

    //工资加2000
    private static void test10() {
        initPerson();
        List<Person> collect = personList.stream().map(x -> {
            x.setAge(x.getSalary() + 2000);
            return x;
        }).collect(Collectors.toList());
        System.out.println(collect);

    }

    //将两个字符数组合并成一个新的字符数组
    private static void test11() {
        String[] arr = {"z, h, a, n, g", "s, a, n"};
        List<String> list = Arrays.asList(arr);
        List<String> collect = list.stream().flatMap(x -> {
            String[] array = x.split(",");
            Stream<String> stream = Arrays.stream(array);
            return stream;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    //求Integer集合的元素之和、乘积和最大值
    private static void test13() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> reduce = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> reduce2 = list.stream().reduce((x, y) -> x * y);
        Optional<Integer> reduce3 = list.stream().reduce((x, y) -> x > y ? x : y);
        System.out.println(reduce);
        System.out.println(reduce2);
        System.out.println(reduce3);
    }

    //求所有员工的工资之和和最高工资
    private static void test14() {
        initPerson();
        Optional<Integer> reduce = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        Optional<Integer> reduce1 = personList.stream().map(Person::getSalary).reduce(Integer::max);
        System.out.println(reduce);
        System.out.println(reduce1);
    }

    //取出大于18岁的员工转为map
    private static void test15() {
        initPerson();
        Map<String, Person> person = personList.stream().filter(x -> x.getAge() > 18).collect(Collectors.toMap(Person::getName, y -> y));
        System.out.println(person);
    }
//统计员工人数、平均工资、工资总额、最高工资
    private static void test1() {
        Long count = personList.stream().collect(Collectors.counting());
        double AVGSalary = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        Optional<Integer> MaxSalary = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        DoubleSummaryStatistics collect =personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("统计员工人数:"+count);
        System.out.println("求平均工资:"+AVGSalary);
        System.out.println("求最高工资:"+MaxSalary);
        System.out.println("求工资之和:"+sum);
        System.out.println("一次性统计所有信息:"+collect);
    }
//将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
    private static void test16(){
        personList.add(new Person("zhangsan",25, 3000, "male", "tieling"));
        personList.add(new Person("lisi",27, 5000, "male", "tieling"));
        personList.add(new Person("wangwu",29, 7000, "female", "tieling"));
        personList.add(new Person("sunliu",26, 3000, "female", "dalian"));
        personList.add(new Person("yinqi",27, 5000, "male", "dalian"));
        personList.add(new Person("guba",21, 7000, "female", "dalian"));
        Map<Boolean,List<Person>> part =personList.stream().collect(Collectors.partitioningBy(x->x.getSalary()>8000));
        Map<String,List<Person>> group =personList.stream().collect(Collectors.groupingBy(Person::getSex));
        Map<String,Map<String,List<Person>>>group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex,Collectors.groupingBy(Person::getArea)));
        System.out.println(part);
        System.out.println(group);
        System.out.println(group2);
    }
    //连接成一个字符串
    private static void test17(){
        String names = personList.stream().map(x->x.getName()).collect(Collectors.joining(","));
        System.out.println();
    }
    //将员工按工资由高到低（工资一样则按年龄由大到小）排序
    private static void test18(){
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        List<String> newList2 =personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());
        List<String> newList3 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName).collect(Collectors.toList());
        List<String> newList4 =personList.stream().sorted((x,y)->{
            if (x.getSalary() ==y.getSalary()){
                return y.getAge()-x.getAge();
            }else {
                return y.getSalary()-x.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println(newList);
        System.out.println(newList2);
        System.out.println(newList3);
        System.out.println(newList4);
    }
    //流合并、去重、限制、跳过
    private static void test19(){
        String[] arr1 ={ "a", "b", "c", "d"};
        String[] arr2 ={"d", "e", "f", "g" };
        Stream<String> stream1 =Stream.of(arr1);
        Stream<String> stream2 =Stream.of(arr2);
        List<String> list = Stream.concat(stream1,stream2).distinct().collect(Collectors.toList());
        List<Integer> collect = Stream.iterate(1,x->x+2).limit(10).collect(Collectors.toList());
        List<Integer> collect2 = Stream.iterate(1,x->x+2).skip(5).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(collect);
        System.out.println(collect2);
    }
    //流读取文件
    private static void test20(){
        String file ="";
        Path path =new File(file).toPath();
        try (Stream<String> lines =Files.lines(path, StandardCharsets.UTF_8)){
            lines.onClose(() ->System.out.println("Done")).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private  static void test21(){
        //计算差集
        String[] arr1 ={ "a", "b", "c", "d"};
        String[] arr2 ={"d", "e", "f", "g" };
        List<String> allList = Stream.of(arr1).collect(Collectors.toList());
        List<String> wList =Stream.of(arr2).collect(Collectors.toList());
        List<String> reduce1 =allList.stream().filter(item->!wList.contains(item)).collect(Collectors.toList());

    }
  }