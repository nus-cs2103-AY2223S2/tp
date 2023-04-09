package taa.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import taa.commons.exceptions.IllegalValueException;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;

/** Jackson-friendly version of {@link Student}. */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String pp;
    private final String attendance;
    private final ArrayList<String> submissionStrArr = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /** Constructs a {@link JsonAdaptedStudent} with the given student details. Called when reading from JSON. */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("attendance") String attendance,
                              @JsonProperty("pp") String pp, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("submissionStrArr") List<String> submissionStrArr) {
        this.name = name;
        this.pp = pp;
        this.attendance = attendance;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (submissionStrArr != null) {
            this.submissionStrArr.addAll(submissionStrArr);
        }
    }

    /** Converts a given {@link Student} into this class for Jackson use. Called when saving to JSON. */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        attendance = source.getAtd().atdStrorageStr();
        pp = source.getAtd().partPointsStorageStr();
        submissionStrArr.addAll(source.getSubmissionStorageStrings());
        tagged.addAll(source.getClassTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@link Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (pp == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Participation Points"));
        }
        if (!Attendance.isValidPpStorageString(pp)) {
            throw new IllegalValueException("Invalid participation value \"" + pp + "\" in JSON file");
        }
        if (attendance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Attendance"));
        }
        if (!Attendance.isValidAttendanceStorageString(attendance)) {
            throw new IllegalValueException("Invalid attendance value \"" + attendance + "\" in JSON file");
        }

        final Name modelName = new Name(name);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Student(modelName, attendance, pp, submissionStrArr, modelTags);
    }

}
