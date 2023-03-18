package seedu.address.storage.person;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Age;
import seedu.address.model.person.Image;
import seedu.address.model.person.Person;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;



/**
 * Jackson-friendly version of {@link Parent}.
 */
public class JsonAdaptedParent extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Parent's %s field is missing!";
    private final String age;
    private final String image;
    private final List<JsonAdaptedStudent> children = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedParent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedParent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("comment") String comment, @JsonProperty("age") String age,
                             @JsonProperty("image") String image,
                             @JsonProperty("students") List<JsonAdaptedStudent> children) {
        super(name, phone, email, address, tagged, comment);
        this.age = age;
        this.image = image;
        if (children != null) {
            this.children.addAll(children);
        }
    }


    /**
     * Converts a given {@code Parent} into this class for Jackson use.
     */
    public JsonAdaptedParent(Parent parent) {
        super(parent);
        this.age = parent.getAge().value;
        this.image = parent.getImage().value;
        for (Student student : parent.getChildren()) {
            children.add(new JsonAdaptedStudent(student));
        }
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Parent toModelType() throws IllegalValueException {
        Person person = super.toModelType();

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Index Number"));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(IndexNumber.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (image == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Index Number"));
        }
        if (!Image.isValidImage(image)) {
            throw new IllegalValueException(Image.MESSAGE_CONSTRAINTS);
        }
        final Image modelImage = new Image(image);

        final List<Student> modelChildren = new ArrayList<>();
        for (JsonAdaptedStudent child : children) {
            modelChildren.add(child.toModelType());
        }
        Parent parent = new Parent(person.getName(), modelAge, modelImage, person.getEmail(), person.getPhone(),
                person.getAddress(), person.getTags());
        parent.addStudents(modelChildren);
        return parent;
    }
}
