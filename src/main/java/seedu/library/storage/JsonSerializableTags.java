package seedu.library.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.ReadOnlyTags;
import seedu.library.model.Tags;
import seedu.library.model.tag.Tag;

/**
 * An Immutable tag list that is serializable to JSON format.
 */
@JsonRootName(value = "tags")
public class JsonSerializableTags {

    public static final String MESSAGE_DUPLICATE_TAGS = "Tags list contains duplicate tag(s).";

    private final List<JsonAdaptedTag> tagList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTags} with the given tags.
     */
    @JsonCreator
    public JsonSerializableTags(@JsonProperty("tagList") List<JsonAdaptedTag> tagList) {
        this.tagList.addAll(tagList);
    }

    /**
     * Converts a given {@code ReadOnlyTags} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTag}.
     */
    public JsonSerializableTags(ReadOnlyTags source) {
        tagList.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts tags into the model's {@code Tags} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tags toModelType() throws IllegalValueException {
        Tags tags = new Tags();
        for (JsonAdaptedTag jsonAdaptedTag : tagList) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (tags.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAGS);
            }
            tags.addTag(tag);
        }
        return tags;
    }
}
