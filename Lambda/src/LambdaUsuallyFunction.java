import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaUsuallyFunction {
    public static void main(String[] args) {
        //对集合进行迭代
        List<String> list = Arrays.asList("a", "b", "c");
        for (String str :list) {
            System.out.println("before java8 ,"+str);
        };
        list.forEach(x-> System.out.println("after java8,"+x));

        //实现map
        List<Double> doubles = Arrays.asList(10.0, 20.0, 30.0);
        doubles.stream().map(x->x+x*0.05).forEach(x-> System.out.println(x));

        //实现map与reduce
        List<Double> doubles1 = Arrays.asList(10.0, 20.0, 30.0);
        double sum =0;
        for (double each : doubles1) {
            each += each * 0.05;
            sum +=each;
        }
        System.out.println(sum);
        List<Double> list1 = Arrays.asList(10.0, 20.0, 30.0);
        double sum2 =list1.stream().map(x->x*0.05).reduce((sum1,x)->sum1+x).get();
        System.out.println("after java8 "+sum2);

        //filter操作
        List<Double> doubles2 = Arrays.asList(10.0, 20.0, 30.0);
        List<Double> filteredCost =  doubles2.stream().filter(x->x>25.0).collect(Collectors.toList());
        filteredCost.forEach(x-> System.out.println(x));

        //与函数式接口Predicate配合
        List<String> languages = Arrays.asList("Java","Python","scala","Shell","R");
        filterTest(languages,x->x.startsWith("J"));//Java
        filterTest(languages,x->x.endsWith("a"));//Java,scala
        filterTest(languages,x->true);//Java,Python,scala,Shell,R
        filterTest(languages,x->false);//
        filterTest(languages,x->x.length()>4);//Python,scala,Shell


    }

    public static  void filterTest(List<String> languages , Predicate<String> condition){
        languages.stream().filter(x->condition.test(x)).forEach(x-> System.out.println(x+""));

    }
}
