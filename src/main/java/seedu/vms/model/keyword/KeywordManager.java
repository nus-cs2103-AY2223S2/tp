package seedu.vms.model.keyword;

import java.util.HashMap;

/**
 * A manager to handle {@code Keyword}.
 */
public class KeywordManager {

    private static HashMap<String, Keyword> keywordMap;

    /**
     * Constructs an empty {@code KeywordManager}.
     */
    public KeywordManager() {
        keywordMap = new HashMap<>();
    }

    /**
     * Adds a keyword mapping to the keyword map.
     * @param keyword Keyword object to be added.
     */
    public void add(Keyword keyword) {
        keywordMap.put(keyword.getKeyword(), keyword);
    }

    /**
     * Removes a keyword mapping in the keyword map.
     * @param keyword String of keyword to be removed.
     */
    public Keyword remove(String keyword) {
        Keyword keywordKeyword = keywordMap.get(keyword);
        keywordMap.remove(keyword);
        return keywordKeyword;
    }

    public String getKeyword(String keywordString) {
        if (!keywordMap.containsKey(keywordString)) {
            return keywordString;
        }
        return keywordMap.get(keywordString).getMainKeyword();
    }

    public HashMap<String, Keyword> getKeywordMap() {
        return keywordMap;
    }

    /**
     * Check if existing mapping exists for given keyword.
     * @param keyword Keyword string to be checked.
     * @return True if existing mapping exists else false.
     */
    public static boolean existingMappingExists(String keyword) {
        if (keywordMap.containsKey(keyword)) {
            return true;
        } else {
            return false;
        }
    }

}
