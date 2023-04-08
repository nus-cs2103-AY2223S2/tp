package taa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static taa.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import taa.commons.exceptions.IllegalValueException;
import taa.model.student.Name;
import taa.testutil.Assert;
import taa.testutil.TypicalPersons;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalPersons.BENSON.getName().toString();

    private static final String VALID_ATTENDANCE = "0;0;0;0;0;0;0;0;0;0;0;0";

    private static final String VALID_PART_POINTS = "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1";
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalPersons.BENSON.getClassTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final List<String> VALID_SUBIT_STRS = List.of();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(TypicalPersons.BENSON);
        assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_NAME, VALID_ATTENDANCE, VALID_PART_POINTS, VALID_TAGS, VALID_SUBIT_STRS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_ATTENDANCE, VALID_PART_POINTS, VALID_TAGS,
                VALID_SUBIT_STRS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_NAME, VALID_ATTENDANCE, VALID_PART_POINTS, VALID_TAGS, VALID_SUBIT_STRS);
        Assert.assertThrows(IllegalValueException.class, person::toModelType);
    }

}
