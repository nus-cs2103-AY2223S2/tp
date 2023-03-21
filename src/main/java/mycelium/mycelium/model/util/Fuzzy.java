package mycelium.mycelium.model.util;

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

        int[] prevRow = new int[b.length() + 1];

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
}
