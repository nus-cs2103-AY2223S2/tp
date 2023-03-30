package seedu.vms.model.keyword;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.LimitExceededException;
import seedu.vms.model.StorageModel;
import seedu.vms.model.vaccination.VaxType;

import java.security.Key;
import java.util.HashMap;

/**
 * A manager to handle {@code Keyword}.
 */
public class KeywordManager {

    private static final int KEYWORD_LIMIT = 30;
    private static final String ERROR_FORMAT_DUPLICATE_KEYWORD = "Keyword %s already exist";
    private static final String ERROR_FORMAT_NONEXISTENT_KEYWORD = "Keyword %s does not exist";
    private static final String ERROR_FORMAT_LIMIT_EXCEEDED = "Limit of %d exceeded";
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
