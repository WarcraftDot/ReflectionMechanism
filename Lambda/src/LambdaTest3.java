import java.util.Comparator;

/**
 * 方法引用与构造器引用
 */
public class LambdaTest3 {
    public static void main(String[] args) {
        Comparator<Integer> comparator =(x,y)->Integer.compare(x,y);
        Comparator<Integer> comparator1 =Integer::compare;
        int compare =comparator.compare(1,2);
        int compare1 =comparator1.compare(1,2);
        System.out.println(compare);
        System.out.println(compare1);
    }


}
