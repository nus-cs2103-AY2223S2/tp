package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.UniqueLectureList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    public static final String MESSAGE_DUPLICATE_LECTURE = "Lecture list contains duplicate lecture(s).";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String code;
    private final String name;
    private final List<JsonAdaptedLecture> lectures = new ArrayList<>();

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     *
     * @param code The code of the module.
     * @param name The name of the module.
     * @param lectures The lectures of the module.
     * @param tagged The tags applied to the module.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("code") String code,
            @JsonProperty("name") String name,
            @JsonProperty("lectures") List<JsonAdaptedLecture> lectures,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.code = code;
        this.name = name;

        if (lectures != null) {
            this.lectures.addAll(lectures);
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Module} into a {@code JsonAdaptedModule} for Jackson use.
     *
     * @param source The module to be converted.
     */
    public JsonAdaptedModule(ReadOnlyModule source) {
        code = source.getCode().code;
        name = source.getName().name;
        lectures.addAll(source
                .getLectureList()
                .stream()
                .map(JsonAdaptedLecture::new)
                .collect(Collectors.toList()));
        tagged.addAll(source
                .getTags()
                .stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @return The resulting {@code Module} object.
     * @throws IllegalValueException Indicates that some data constraints were violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        final Set<Tag> moduleTags = new HashSet<>();
        for (JsonAdaptedTag adaptedTag : tagged) {
            moduleTags.add(adaptedTag.toModelType());
        }

        final UniqueLectureList moduleLectures = new UniqueLectureList();
        for (JsonAdaptedLecture adaptedLecture : lectures) {
            Lecture lecture = adaptedLecture.toModelType();

            if (moduleLectures.contains(lecture)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LECTURE);
            }

            moduleLectures.add(lecture);
        }

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "code"));
        }
        if (!ModuleCode.isValidCode(code)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode moduleCode = new ModuleCode(code);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        final ModuleName moduleName = new ModuleName(name);

        return new Module(moduleCode, moduleName, moduleTags, moduleLectures.asUnmodifiableObservableList());
    }
}
