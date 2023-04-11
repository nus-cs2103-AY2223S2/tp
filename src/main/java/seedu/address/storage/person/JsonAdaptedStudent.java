package seedu.address.storage.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Age;
import seedu.address.model.person.Class;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.Cca;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.Test;
import seedu.address.storage.academics.JsonAdaptedAttendance;
import seedu.address.storage.academics.JsonAdaptedHomework;
import seedu.address.storage.academics.JsonAdaptedTest;



/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";
    private final String indexNumber;
    private final String sex;
    private final String age;
    private final String image;
    private final String cca;
    private final String sc;
    private final Set<JsonAdaptedAttendance> attendance = new HashSet<>();
    private final Set<JsonAdaptedHomework> homework = new HashSet<>();
    private final Set<JsonAdaptedTest> test = new HashSet<>();
    private final String parentNumber;
    private final String parentName;
    private final String rls;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("comment") String comment,
                              @JsonProperty("sex") String sex, @JsonProperty("age") String age,
                              @JsonProperty("image") String image, @JsonProperty("cca") String cca,
                              @JsonProperty("attendance") Set<JsonAdaptedAttendance> attendance,
                              @JsonProperty("homework") Set<JsonAdaptedHomework> homework,
                              @JsonProperty("test") Set<JsonAdaptedTest> test,
                              @JsonProperty("parentNumber") String parentNumber,
                              @JsonProperty("parentName") String parentName,
                              @JsonProperty("rls") String rls,
                              @JsonProperty("class") String sc,
                              @JsonProperty("id") String indexNumber) {
        super(name, phone, email, address, tagged, comment);
        this.indexNumber = indexNumber;
        this.sex = sex;
        this.age = age;
        this.image = image;
        this.cca = cca;
        this.sc = sc;
        this.parentNumber = parentNumber;
        this.parentName = parentName;
        this.rls = rls;
        if (attendance != null) {
            this.attendance.addAll(attendance);
        }
        if (homework != null) {
            this.homework.addAll(homework);
        }
        if (test != null) {
            this.test.addAll(test);
        }
    }


    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student student) {
        super(student);
        this.indexNumber = student.getIndexNumber().value;
        this.sex = student.getSex().value;
        this.age = student.getAge().value;
        this.image = student.getImage().value;
        this.cca = student.getCca().value;
        this.sc = student.getSc().getClassName();
        this.parentNumber = student.getParentNumber().value;
        this.parentName = student.getParentName().fullName;
        this.rls = student.getRls().rls;
        attendance.addAll(student.getAttendance().stream()
                .filter(attendance1 -> !attendance1.isAbsent())
                .map(JsonAdaptedAttendance::new)
                .collect(Collectors.toList()));
        homework.addAll(student.getHomework().stream()
                .map(JsonAdaptedHomework::new)
                .collect(Collectors.toList()));
        test.addAll(student.getTest().stream()
                .map(JsonAdaptedTest::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Student toModelType() throws IllegalValueException {
        Person person = super.toModelType();

        if (indexNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Index Number"));
        }
        if (!IndexNumber.isValidIndexNumber(indexNumber)) {
            throw new IllegalValueException(IndexNumber.MESSAGE_CONSTRAINTS);
        }
        final IndexNumber modelIndexNumber = new IndexNumber(indexNumber);

        if (sex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Sex"));
        }
        if (!Sex.isValidSex(sex)) {
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }

        final Sex modelSex = new Sex(sex);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Index Number"));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (image == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Image"));
        }
        if (!Image.isValidImage(image)) {
            throw new IllegalValueException(Image.MESSAGE_CONSTRAINTS);
        }
        final Image modelImage = new Image(image);

        if (cca == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "CCA"));
        }
        if (!Cca.isValidCca(cca)) {
            throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
        }
        final Cca modelCca = new Cca(cca);

        if (sc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "SC"));
        }
        if (!Class.isValidClass(sc)) {
            throw new IllegalValueException(Class.MESSAGE_CONSTRAINTS);
        }

        if (parentNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Parent Number"));
        }
        if (!Phone.isValidPhone(parentNumber)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelParentNumber = new Phone(parentNumber);

        if (parentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Parent Name"));
        }
        if (!Name.isValidName(parentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelParentName = new Name(parentName);

        if (rls == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "RLS"));
        }
        if (!Relationship.isValidRelationship(rls)) {
            throw new IllegalValueException(Relationship.MESSAGE_CONSTRAINTS);
        }
        final Relationship modelRls = new Relationship(rls);
        final List<Test> studentTest = new ArrayList<>();
        for (JsonAdaptedTest jsonAdaptedTest : test) {
            studentTest.add(jsonAdaptedTest.toModelType());
        }
        final Set<Test> modelTest = new HashSet<>(studentTest);
        final List<Homework> studentHomework = new ArrayList<>();
        for (JsonAdaptedHomework jsonAdaptedHomework : homework) {
            studentHomework.add(jsonAdaptedHomework.toModelType());
        }
        final Set<Homework> modelHomework = new HashSet<>(studentHomework);

        final List<Attendance> studentAttendance = new ArrayList<>();
        for (JsonAdaptedAttendance jsonAdaptedAttendance : attendance) {
            if (!(jsonAdaptedAttendance.getAttendance().equals("F"))) {
                studentAttendance.add(jsonAdaptedAttendance.toModelType());
            }
        }
        final Set<Attendance> modelAttendance = new HashSet<>(studentAttendance);

        return new Student(person.getName(), Class.of(sc), modelIndexNumber, modelSex, modelParentName,
                modelParentNumber, modelRls, modelAge, modelImage, person.getEmail(), person.getPhone(), modelCca,
                person.getAddress(), modelAttendance, modelHomework, modelTest, person.getTags(), person.getComment());
    }

}
