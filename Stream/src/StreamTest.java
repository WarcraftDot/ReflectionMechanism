import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println(findFirst.get());
        System.out.println(findAny.get());
        System.out.println(anyMatch);

    }
}
