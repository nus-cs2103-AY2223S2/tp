package seedu.vms.storage.keyword;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.keyword.KeywordManager;

/**
 * KeywordLoader class to load and write keywords.
 */
public class KeywordLoader {
    private static final String JSON_FILE_PATH = "/data/keywords.json";

    private final List<JsonAdaptedKeyword> keywords;

    /** Constructs a {@code KeywordLoader}. */
    @JsonCreator
    public KeywordLoader(@JsonProperty("keywords") List<JsonAdaptedKeyword> keywords) {
        this.keywords = Objects.requireNonNull(keywords);
    }

    /**
     * Converts the specified {@code KeywordManager} to a {@code KeywordLoader}.
     */
    public static KeywordLoader fromModelType(KeywordManager manager) {
        List<JsonAdaptedKeyword> types = manager
                .getKeywordMap()
                .values()
                .stream()
                .map(keyword -> JsonAdaptedKeyword.fromModelType(keyword))
                .collect(Collectors.toList());
        return new KeywordLoader(types);
    }

    /**
     * Loads all the keywords contained in the keywords JSON
     * file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static KeywordManager load(Path path) throws IllegalValueException, IOException {
        KeywordLoader loader = JsonUtil.deserializeFromFile(path, KeywordLoader.class);
        return loader.toModelType();
    }

    /**
     * Loads all the keywords contained in the keywords empty JSON
     * file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static KeywordManager load() throws IllegalValueException, IOException {
        KeywordLoader loader = JsonUtil.deserializeFromResource(JSON_FILE_PATH, KeywordLoader.class);
        return loader.toModelType();
    }

    private KeywordManager toModelType() throws IllegalValueException {
        KeywordManager manager = new KeywordManager();
        for (JsonAdaptedKeyword adapted : keywords) {
            Keyword keyword = adapted.toModelType();
            if (Keyword.isNotMainKeyword(keyword.getKeyword())
                    && Keyword.isValidMainKeyword(keyword.getMainKeyword())
                    && !keyword.getKeyword().isBlank()
                    && !keyword.getKeyword().matches(".*[\\s(--)]+.*")) {
                manager.add(keyword);
            } else {
                throw new IllegalValueException("Illegal values present.");
            }
        }
        return manager;
    }

    /**
     * Writes the data of this {@code KeywordLoader} to the specified file.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void write(Path path) throws IOException {
        JsonUtil.serializeToFile(path, this);
    }

}
