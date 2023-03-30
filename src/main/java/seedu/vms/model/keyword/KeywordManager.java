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

    public void add(Keyword keyword) {
        keywordMap.put(keyword.getKeyword(), keyword);
        System.out.println(keyword);
        System.out.println(keywordMap);
    }

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

}
