package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Understands the order of the objects
public interface Orderable<T extends Orderable> {
    int isBetterThen(T other);

    static <T extends Orderable> List<T> sort(T... elements) {
        return sort(Arrays.asList(elements));
    }

    static <T extends Orderable> List<T> sort(List<T> elements) {
        return elements.stream().sorted(Orderable::isBetterThen).collect(Collectors.toList());
    }
}
