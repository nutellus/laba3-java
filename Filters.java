import java.util.ArrayList;
import java.util.List;

public class Filters {

    public interface Tester<T> {
        boolean test(T x);
    }

    public static <T> List<T> filter(List<T> src, Tester<T> tester) {
        List<T> out = new ArrayList<>();
        for (T item : src) {
            if (tester.test(item)) {
                out.add(item);
            }
        }
        return out;
    }
}
