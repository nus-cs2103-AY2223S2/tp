package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.score.Score;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MESSAGE_DUPLICATE_SCORE = "Score list contains duplicate score(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String parentPhone;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedTask> taskList = new ArrayList<>();

    private final List<JsonAdaptedScore> scores = new ArrayList<>();
    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("parentPhone") String parentPhone,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("taskList") List<JsonAdaptedTask> taskList,
                              @JsonProperty("scores") List<JsonAdaptedScore> scores) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.parentPhone = parentPhone;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (taskList != null) {
            this.taskList.addAll(taskList);
        }
        if (scores != null) {
            this.scores.addAll(scores);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(seedu.address.model.student.Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        parentPhone = source.getParentPhone().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        taskList.addAll(source.getTaskListAsObservableList().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        scores.addAll(source.getScoreListAsObservableList().stream()
                .map(JsonAdaptedScore::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public seedu.address.model.student.Student toModelType() throws IllegalValueException {
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
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        if (!Phone.isMoreThanMaxDigits(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_EXCEED_MAX_DIGITS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (parentPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }

        if (!Phone.isValidPhone(parentPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        if (!Phone.isMoreThanMaxDigits(parentPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_EXCEED_MAX_DIGITS);
        }
        final Phone modelParentPhone = new Phone(parentPhone);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        Student student = new Student(modelName, modelPhone, modelEmail, modelAddress, modelParentPhone, modelTags);

        for (JsonAdaptedTask jsonAdaptedTask : taskList) {
            Task task = jsonAdaptedTask.toModelType();
            if (student.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            student.addTask(task);
        }

        for (JsonAdaptedScore jsonAdaptedScore : scores) {
            Score score = jsonAdaptedScore.toModelType();
            if (student.hasScore(score)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCORE);
            }
            student.addScore(score);
        }

        return student;
    }
}
