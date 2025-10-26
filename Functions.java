import java.util.ArrayList;
import java.util.List;

public class Functions {

    public interface Transformer<T, R> {
        R apply(T x);
    }

    public static <T, R> List<R> applyToEach(List<T> src, Transformer<T, R> f) {
        List<R> out = new ArrayList<>(src.size());
        for (T item : src) {
            out.add(f.apply(item));
        }
        return out;
    }
}
