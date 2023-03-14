package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedCategory {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Category's %s field is missing!";

    private final String categoryName;

    private final String summary;

    /**
     * Constructs a {@code JsonAdaptedCategory} with the given category details.
     */
    @JsonCreator
    public JsonAdaptedCategory(@JsonProperty("categoryName") String categoryName,
                               @JsonProperty("summary") String summary) {
        this.categoryName = categoryName;
        this.summary = summary;
    }

    /**
     * Converts a given {@code Category} into this class for Jackson use.
     */
    public JsonAdaptedCategory(Category source) {
        this.categoryName = source.getCategoryName();
        this.summary = source.getSummary();
    }

    /**
     * Converts this Jackson-friendly adapted category object into the model's {@code Category} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted category.
     */
    public Category toModelType() throws IllegalValueException {

        if (categoryName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final String modelCategoryName = categoryName;

        if (summary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final String modelDescription = summary;

        return new UserDefinedCategory(modelCategoryName, modelDescription);
    }

}
