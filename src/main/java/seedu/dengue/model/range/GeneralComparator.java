package seedu.dengue.model.range;

import java.util.Comparator;

abstract class GeneralComparator<T> implements Comparator<T> {
    public abstract int compare(T t1, T t2);

    public boolean firstIsSmaller(T t1, T t2) {
        return compare(t1, t2) < 0;
    }
}
