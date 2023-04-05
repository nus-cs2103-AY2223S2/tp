package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.tag.ModuleTag;

/**
 * Jackson-friendly version of {@link ModuleTag}.
 */
public class JsonAdaptedModuleTag extends JsonAdaptedTag {
    private final String moduleCode;
    private final List<JsonAdaptedLesson> lessons;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedModuleTag(
            @JsonProperty("moduleCode") String moduleCode, @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.moduleCode = moduleCode;
        this.lessons = lessons;
    }

    /**
     * Converts a given {@code GroupTag} into this class for Jackson use.
     */
    public JsonAdaptedModuleTag(ModuleTag moduleTag) {
        moduleCode = moduleTag.tagName;
        lessons = moduleTag.getImmutableLessons().stream()
                .map(JsonAdaptedLesson::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code GroupTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ModuleTag toModelType() throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException("Module code is missing!");
        }

        if (lessons == null) {
            throw new IllegalValueException("Lessons are missing!");
        }

        if (!ModuleTag.isValidTagName(moduleCode)) {
            throw new IllegalValueException(ModuleTag.MESSAGE_CONSTRAINTS);
        }

        List<Lesson> parsedLessons = new ArrayList<>();

        for (JsonAdaptedLesson lesson : lessons) {
            parsedLessons.add(lesson.toModelType().updateModuleCode(moduleCode));
        }
        return new ModuleTag(moduleCode, parsedLessons);
    }
}
