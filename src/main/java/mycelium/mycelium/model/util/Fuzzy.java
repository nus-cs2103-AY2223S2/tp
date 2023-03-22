package mycelium.mycelium.model.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for fuzzy string matching.
 */
public class Fuzzy {

    /**
     * Computes a "delta-score" between two strings. The lower the score, the more similar the strings are. A score
     * of zero means the strings are completely identical.
     */
    public static int delta(String a, String b) {
        // The intuitive way to do this is through a m-by-n matrix. Here we are only
        // using one array and a temp variable.
        int m = a.length();
        int n = b.length();
        if (m > n) {
            String tmp = a;
            a = b;
            b = tmp;

            int tmp2 = m;
            m = n;
            n = tmp2;
        }

        // From here on, `b` is the longer string, and `a` is the shorter string. Accordingly, `n` is the size of the
        // longer string, and `m` is the size of the shorter string.

        if (m == 0) {
            return n;
        }
        if (m == 1) {
            char c = a.charAt(0);
            for (int i = 0; i < n; i++) {
                if (b.charAt(i) == c) {
                    return n - 1;
                }
            }
            return n;
        }

        // NOTE: if we know the max size of the strings beforehand, we can pre-allocate the array, and re-use it for
        // every call. There is no need to clear the array, since we are only ever writing to it.
        int[] prevRow = new int[n + 1];

        for (int j = 0; j < n + 1; j++) {
            prevRow[j] = j;
        }

        int grave;
        int ifDelete;
        int ifInsert;
        int tmp;
        for (int i = 0; i < m; i++) {
            grave = i; // grave refers to the upper-left tile (from the matrix POV)
            prevRow[0] = i + 1; // the first entry is always i+1

            // now fill in 1..n+1 entries, writing to index j+1 for each iteration
            for (int j = 0; j < n; j++) {
                tmp = grave;
                if (a.charAt(i) != b.charAt(j)) {
                    ifDelete = prevRow[j + 1];
                    ifInsert = prevRow[j]; // this is technically "currentRow[j]"
                    tmp = ifInsert < grave ? ifInsert : grave;
                    tmp = ifDelete < tmp ? ifDelete : tmp;
                    tmp += 1;
                }
                grave = prevRow[j + 1];
                prevRow[j + 1] = tmp;
            }
        }
        return prevRow[n];
    }

    /**
     * Computes a "delta-score" between two strings, and returns a value between 0 and 1, where 0 means the two strings
     * are completely different, and 1 means they are completely the same.
     */
    public static double deltaPercent(String a, String b) {
        return 1 - (double) delta(a, b) / Math.max(a.length(), b.length());
    }

    /**
     * Performs a fuzzy search on a list of objects against some query string. Objects which are more similar to the
     * query string will be at the front of the returned list. Objects which are completely different will not be
     * included in the returned list.
     * <p>
     * Note that this method will not modify the original list. It is a pure function and safe to call multiple times.
     */
    public static <S, T extends FuzzyComparable<S>> List<T> fuzzySearch(List<T> list, S query) {
        ArrayList<T> xs = new ArrayList<>(list);
        // This is not very efficient, but it's simple and it works
        xs.sort((a, b) -> Double.compare(b.fuzzyCompareTo(query), a.fuzzyCompareTo(query)));
        return xs.stream().filter(x -> x.fuzzyCompareTo(query) > 0.0).collect(Collectors.toList());
    }
}
