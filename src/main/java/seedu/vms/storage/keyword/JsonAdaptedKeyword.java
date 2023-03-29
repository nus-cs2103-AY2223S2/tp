package seedu.vms.storage.keyword;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.vaccination.VaxType;

import java.security.Key;

/** A JSON friendly version of {@link Keyword}. */
public class JsonAdaptedKeyword {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Keyword's %s field is missing!";

    private final String keyword;
    private final String mainKeyword;

    /**
     * Constructs a {@code JsonAdaptedKeyword} with the given keyword details.
     */
    @JsonCreator
    public JsonAdaptedKeyword(@JsonProperty("keyword") String keyword,
                              @JsonProperty("mainKeyword") String mainKeyword) {
        this.keyword = keyword;
        this.mainKeyword = mainKeyword;

    }

    /**
     * Converts a given {@code Keyword} into this class for Jackson use.
     */
    public static JsonAdaptedKeyword fromModelType(Keyword source) {
        String keyword = source.getKeyword();
        String mainKeyword = source.getMainKeyword();
        return new JsonAdaptedKeyword(keyword, mainKeyword);
    }

//    public Keyword toModelType() throws IllegalValueException {
//        if (keyword == null || mainKeyword == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "keyword"));
//        }
//        return new Keyword(mainKeyword, keyword);
//    }

    /**
     * Converts this Jackson-friendly adapted keyword object into the model's
     * {@code Keyword} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted keyword.
     */
    public Keyword toModelType() throws IllegalValueException {
        return new Keyword(mainKeyword, keyword);
    }
}
