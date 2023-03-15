package seedu.modtrek.storage;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.modtrek.commons.exceptions.IllegalValueException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String code;
    private final String credit;
    private final String grade;
    private final String semYear;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("code") String code, @JsonProperty("credit") String credit,
                             @JsonProperty("grade") String grade, @JsonProperty("semYear") String semYear,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.code = code;
        this.credit = credit;
        this.grade = grade;
        this.semYear = semYear;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        code = source.getCode().toString();
        credit = source.getCredit().toString();
        grade = source.getGrade().toString();
        semYear = source.getSemYear().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Tag> moduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            moduleTags.add(tag.toModelType());
        }

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (credit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Credit.class.getSimpleName()));
        }
        if (!Credit.isValidCredit(credit)) {
            throw new IllegalValueException(Credit.MESSAGE_CONSTRAINTS);
        }
        final Credit modelCredit = new Credit(credit);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        if (semYear == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, SemYear.class.getSimpleName()));
        }
        if (!SemYear.isValidSemYear(semYear)) {
            throw new IllegalValueException(SemYear.MESSAGE_CONSTRAINTS);
        }
        final SemYear modelSemYear = new SemYear(semYear);

        final Set<Tag> modelTags = new HashSet<>(moduleTags);
        return new Module(modelCode, modelCredit, modelSemYear, modelTags, modelGrade);
    }

}
