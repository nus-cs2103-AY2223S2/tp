package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Exam;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedHomework> homeworkList = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessonList = new ArrayList<>();
    private final List<JsonAdaptedExam> examList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("homeworks") List<JsonAdaptedHomework> homeworkList,
                              @JsonProperty("lessons") List<JsonAdaptedLesson> lessonList,
                              @JsonProperty("exams") List<JsonAdaptedExam> examList) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (homeworkList != null) {
            this.homeworkList.addAll(homeworkList);
        }
        if (lessonList != null) {
            this.lessonList.addAll(lessonList);
        }
        if (examList != null) {
            this.examList.addAll(examList);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        homeworkList.addAll(source.getHomeworkList().stream()
                .map(JsonAdaptedHomework::new)
                .collect(Collectors.toList()));
        lessonList.addAll(source.getLessonsList().stream()
            .map(JsonAdaptedLesson::new)
            .collect(Collectors.toList()));
        examList.addAll(source.getExamsList().stream()
            .map(JsonAdaptedExam::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }

        // Convert homeworkList to modelHomeworkList
        final List<Homework> modelHomeworkList = new ArrayList<>();
        for (JsonAdaptedHomework homework : homeworkList) {
            modelHomeworkList.add(homework.toModelType());
        }

        // Convert lessonList to modelLessonList
        final List<Lesson> modelLessonList = new ArrayList<>();
        for (JsonAdaptedLesson lesson : lessonList) {
            modelLessonList.add(lesson.toModelType());
        }

        // Convert examList to modelExamList
        final List<Exam> modelExamList = new ArrayList<>();
        for (JsonAdaptedExam exam : examList) {
            modelExamList.add(exam.toModelType());
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

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        return new Student(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelHomeworkList,
            modelLessonList, modelExamList);
    }
}
