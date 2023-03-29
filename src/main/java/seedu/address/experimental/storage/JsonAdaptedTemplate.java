package seedu.address.experimental.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Template;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Template}
 */
public class JsonAdaptedTemplate {
    private final String name;
    private final float strWeight;
    private final float intWeight;
    private final float dexWeight;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTemplate} with the given template details.
     */
    @JsonCreator
    JsonAdaptedTemplate(@JsonProperty("name") String name, @JsonProperty("strWeight") float strWeight,
                        @JsonProperty("intWeight") float intWeight,
                        @JsonProperty("dexWeight") float dexWeight,
                        @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.strWeight = strWeight;
        this.intWeight = intWeight;
        this.dexWeight = dexWeight;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Template} into this class for Jackson use.
     */
    public JsonAdaptedTemplate(Template source) {
        name = source.getName().fullName;
        strWeight = source.getStrWeight();
        intWeight = source.getIntWeight();
        dexWeight = source.getDexWeight();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Template object into the model's {@code Template} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted template.
     */
    public Template toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(tags);
        return new Template(new Name(name), strWeight, dexWeight, intWeight, modelTags);
    }
}
