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
//    private final ObservableMap<String, Keyword> unmodifiableTypeMap;

    /**
     * Constructs an empty {@code KeywordManager}.
     */
    public KeywordManager() {
        keywordMap = new HashMap<>();
//        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(keywordMap);
    }

    public void add(Keyword keyword) {
//        if (keywordMap.containsKey(keyword.getKeyword())) {
//            throw new IllegalValueException(String.format(ERROR_FORMAT_DUPLICATE_KEYWORD,
//                    keyword.getKeyword()));
//        }
//        if (keywordMap.size() >= KEYWORD_LIMIT) {
//            throw new LimitExceededException(String.format(ERROR_FORMAT_LIMIT_EXCEEDED,
//                    KEYWORD_LIMIT));
//        }
        keywordMap.put(keyword.getKeyword(), keyword);
        System.out.println("was i even here");
        System.out.println(keyword);
        System.out.println(keywordMap);
//        return keyword;
    }

    public Keyword remove(String keyword) {
//        if (keywordMap.containsKey(keyword.getKeyword())) {
//            throw new IllegalValueException(String.format(ERROR_FORMAT_DUPLICATE_KEYWORD,
//                    keyword.getKeyword()));
//        }
//        if (keywordMap.size() >= KEYWORD_LIMIT) {
//            throw new LimitExceededException(String.format(ERROR_FORMAT_LIMIT_EXCEEDED,
//                    KEYWORD_LIMIT));
//        }
//        if (keywordMap.containsKey(keyword)) {
        Keyword keywordKeyword = keywordMap.get(keyword);
        keywordMap.remove(keyword);
        return keywordKeyword;
    }

    public ValueChange<Keyword> set(String name, Keyword newKeyword) throws IllegalValueException {
        if (!keywordMap.containsKey(name)) {
            throw new IllegalValueException(String.format(ERROR_FORMAT_NONEXISTENT_KEYWORD,
                    name));
        }
        if (!(name.equals(newKeyword.getKeyword())) && keywordMap.containsKey(newKeyword.getKeyword())) {
            throw new IllegalValueException(String.format(ERROR_FORMAT_DUPLICATE_KEYWORD,
                    newKeyword.getKeyword()));
        }
        Keyword oldKeyword = keywordMap.remove(name);
        keywordMap.put(newKeyword.getKeyword(), newKeyword);
        return new ValueChange<>(oldKeyword, newKeyword);
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
