package mycelium.mycelium.model.util;

/**
 * A utility class for fuzzy string matching.
 */
public class Fuzzy {
    /**
     * Returns a value between 0 and 1, where 0 means the two strings are "completely different", and 1 means they are
     * completely the same. Every character in the query must be present in the target, and the characters must be in
     * the
     * same order.
     */
    public static double delta(String query, String target) {
        // An empty query would match everything, so we disallow it and return 0. Since we expect every character of
        // the query to be present in the target, we also disallow the case where the query is longer than the target.
        if (target.length() < query.length() || query.isEmpty()) {
            return 0;
        }

        double score = 0;
        int targetIdx = 0;

        // Skip over characters in the target until we find the first character of the query.
        targetIdx += target.chars().takeWhile(x -> x != query.charAt(0)).count(); // fancy stream
        if (targetIdx == target.length()) {
            return 0;
        }
        score += targetIdx;
        targetIdx++;

        for (int i = 1; i < query.length(); i++) {
            int prevTargetIdx = targetIdx;

            while (targetIdx < target.length() && target.charAt(targetIdx) != query.charAt(i)) {
                targetIdx++;
            }
            if (targetIdx == target.length()) {
                return 0;
            }

            score += 3 * (targetIdx - prevTargetIdx); // note the multiplier
            targetIdx++;
        }

        score += target.length() - targetIdx;
        return 1 / (score + 1); // prevent division by 0
    }

    /**
     * Computes a "delta-score" between two strings. The lower the score, the more similar the strings are. A score
     * of zero means the strings are completely identical. This is just levenshtein distance.
     */
    private static int distance(String a, String b) {
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
     * Computes distance between two strings as a ratio. The higher the ratio, the more similar the strings are.
     */
    public static double levenshtein(String a, String b) {
        return 1 - (double) distance(a, b) / Math.max(a.length(), b.length());
    }
}
