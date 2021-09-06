import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaTest {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("普通，线程启动");
            }
        };
        runnable.run();
        test2();
        test3();
        test4();
        test5();
    }

    private static void test5() {
        System.out.println(Integer.compare(100,10));
    }

    private static void test4() {
        Comparator<Integer> comparator =(x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        int compare = comparator.compare(100, 244);
        System.out.println(compare);
    }

    private static void test3() {
        Consumer<String> consumer =e-> System.out.println("3"+e);
        consumer.accept("a");
    }

    private static void test2() {
        Runnable runnable =()-> System.out.println("2");
        runnable.run();
    }

}
