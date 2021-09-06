import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置函数式接口
 */
public class LambdaTest2 {
    public static void main(String[] args) {
        ArrayList<Integer> res = getNumList(10,()->(int)(Math.random()*100));
        System.out.println(res);

        String newStr = StrHandler("abc",(str)->str.toUpperCase());
        System.out.println(newStr);
        newStr =StrHandler("  abc  ",(str)->str.trim());

        List<String> list = Arrays.asList("a","b","c");

        List<String> ret = filterStr(list,(str)->str.length()>2);
        System.out.println(ret);
    }
    public static List<String> filterStr(List<String> list, Predicate<String> pre){
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str:list
             ) {
            if (pre.test(str)){
                arrayList.add(str);
            }
        }
        return arrayList;
    }
    //函数式接口
    public static String  StrHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    private static ArrayList<Integer> getNumList(int num, Supplier<Integer> sup) {
        ArrayList<Integer> list=new ArrayList<>();
        for (int j = 0; j <num ; j++) {
            Integer e=sup.get();
            list.add(e);
        }
        return list;
    }

    //消费性接口
    //有一个参数，并且无返回值
    public static void test3() {
        //这个e就代表所实现的接口的方法的参数，
        Consumer<String> consumer = e->System.out.println("Lambda 表达式方式，"+e);
        consumer.accept("传入参数");
    }


}
