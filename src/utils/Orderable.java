package utils;

import java.util.Arrays;
import java.util.List;

// Understands the order of the objects
public interface Orderable<T extends Orderable> {
    int isBetterThen(T other);

    static <T extends Orderable> T max(T... elements) {
        return max(Arrays.asList(elements));
    }

    static <T extends Orderable> T max(List<T> elements) {
        T max = elements.get(0);
        for (T element : elements) {
            if (element.isBetterThen(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}
