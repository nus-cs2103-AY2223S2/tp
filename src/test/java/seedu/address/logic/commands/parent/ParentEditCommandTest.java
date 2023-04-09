package seedu.address.logic.commands.parent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.Parents;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalParents;
import seedu.address.testutil.TypicalStudents;

class ParentEditCommandTest {
    private Parents parentList = TypicalParents.getTypicalPowerConnectParents();
    private Model model = new ModelManager(parentList, new UserPrefs());
    private Parent parent1 = TypicalParents.AMY; // new parent to add into list
    private Parent parent2 = TypicalParents.BENSON; // existing parent in the list
    private Address address = parent1.getAddress();
    private Address newAddress = new Address("Insert Address here!"); // empty new address
    private Address newAddress2 = parent2.getAddress(); // new address
    private Age age = parent1.getAge();
    private Age newAge = new Age("Insert parent age here!"); // empty new age
    private Age newAge2 = parent2.getAge(); // new age
    private Email email = parent1.getEmail();
    private Email newEmail = new Email("Insert parent email here!"); // empty new email
    private Email newEmail2 = parent2.getEmail(); // new email
    private Image image = parent1.getImage();
    private Image newImage = new Image("Insert parent image here!"); // empty new image
    private Image newImage2 = parent2.getImage(); // new image
    private final Name name = parent1.getName();
    private Name newName = new Name("Insert new name here!");
    private Name newName2 = parent2.getName();
    private Phone newPhoneNumber = new Phone("Insert parent phone here!");
    private Phone newPhoneNumber2 = parent2.getPhone();
    private final Phone phoneNumber = parent1.getPhone();
    private Set<Tag> tagList = parent1.getTags(); // AMY tags
    private Set<Tag> tagList2 = parent2.getTags(); // BENSON tags
    @Test
    public void defaultValuesTest() {
        ObservableList<Parent> parents = model.getFilteredParentList();
        assertFalse(parents.size() == 0); // parents should not be empty since it is preloaded with parentList!
        // no new name given
        assertTrue(Name.isDefaultName(newName.fullName));
        // no new phone number given
        assertTrue(Phone.isDefaultPhone(newPhoneNumber.value));
        // original parent/NOK name not empty
        assertFalse(name.equals(null));
        assertFalse(Name.isDefaultName(name.fullName));
        // original parent/NOK number not empty
        assertFalse(phoneNumber.equals(null));
        assertFalse(Phone.isDefaultPhone(phoneNumber.value));
        // new inputs given
        assertFalse(Address.isDefaultAddress(newAddress2.value));
        assertFalse(Age.isDefaultAge(newAge2.value));
        assertFalse(Email.isDefaultEmail(newEmail2.value));
        assertTrue(Image.isDefaultImage(newImage2.value)); //assert True in this case as BENSON does not have image
        assertFalse(Name.isDefaultName(newName2.fullName));
        assertFalse(Phone.isDefaultPhone(newPhoneNumber2.value));
        // no new inputs given
        assertTrue(Address.isDefaultAddress(newAddress.value));
        assertTrue(Age.isDefaultAge(newAge.value));
        assertTrue(Email.isDefaultEmail(newEmail.value));
        assertTrue(Image.isDefaultImage(newImage.value));
        assertTrue(Name.isDefaultName(newName.fullName));
        assertTrue(Phone.isDefaultPhone(newPhoneNumber.value));
    }

    @Test
    public void executeTest() {
        // Parent/NOK not found as Parent/NOK name and phone number are not found in existing parents/NOKs
        assertThrows(CommandException.class, () -> new ParentEditCommand(name, newName2, newAge2, newImage2, newEmail2,
                phoneNumber, newPhoneNumber2, newAddress2, tagList2).execute(model));
        // Parent/NOK number found in existing parents/NOKs but name does not match
        assertThrows(CommandException.class, () -> new ParentEditCommand(newName2, newName2, newAge2, newImage2,
                newEmail2, phoneNumber, newPhoneNumber2, newAddress2, tagList2).execute(model));
        // Parent/NOK name found in existing parents/NOKs but number does not match
        assertThrows(CommandException.class, () -> new ParentEditCommand(name, newName2, newAge2, newImage2,
                newEmail2, newPhoneNumber2, newPhoneNumber2, newAddress2, tagList2).execute(model));
        // Parent/NOK number found with matching name in existing parents/NOKs with no change in parent/NOK info
        assertDoesNotThrow(() -> new ParentEditCommand(newName2, newName2, newAge2, newImage2, newEmail2,
                newPhoneNumber2, newPhoneNumber2, newAddress2, tagList2).execute(model));
        // Edit parent/NOK in parent list
        ParentEditCommand testCommand = new ParentEditCommand(newName2, name, newAge2, newImage2, newEmail2,
                newPhoneNumber2, phoneNumber, newAddress2, tagList2);
        ModelManager testModel = new ModelManager(model.getParents(), new UserPrefs());
        assertDoesNotThrow(() -> testCommand.execute(testModel));
        Parent newParent = new Parent(name, newAge2, newImage2, newEmail2, phoneNumber, newAddress2, tagList2);
        ObservableList<Parent> parents = testModel.getFilteredParentList();
        assertFalse(parents.contains(parent2));
        assertTrue(parents.contains(newParent));
        String messageSuccessTest = String.format(ParentEditCommand.MESSAGE_EDIT_PARENT_SUCCESS, name, phoneNumber);
        assertCommandSuccess(testCommand, model, messageSuccessTest, testModel);
    }

    @Test
    public void editParentTest() {
        //Set up of parent and students
        Parent originalParent = parent2;
        Student carl = TypicalStudents.CARL;
        Student daniel = TypicalStudents.DANIEL;
        Student originalCarl = carl;
        Student originalDaniel = daniel;
        carl.setParent(parent2); // Add parent2 to Carl
        daniel.setParent(parent2); // Add parent2 to Daniel
        parent2.addStudent(carl); // Add Carl to parent2
        parent2.addStudent(daniel); // Add Daniel to parent2
        PcClass studentList = TypicalStudents.getTypicalPowerConnectStudents();
        ModelManager testModel = new ModelManager(studentList, parentList, new UserPrefs());
        testModel.setParent(originalParent, parent2); // update parent2 info
        testModel.setStudent(originalCarl, carl); // update Carl info
        testModel.setStudent(originalDaniel, daniel); // update Daniel info
        // Testing editParent()
        ParentEditCommand testCommand = new ParentEditCommand(newName2, name, age, image, email,
                newPhoneNumber2, phoneNumber, address, tagList);
        assertDoesNotThrow(() -> testCommand.execute(testModel));
        ObservableList<Student> students = testModel.getFilteredStudentList();
        ObservableList<Parent> parents = testModel.getFilteredParentList();
        assertTrue(students.contains(carl));
        assertTrue(students.contains(daniel));
        for (Student student : students) { // Get Updated info of Carl and Daniel
            if (student.getIndexNumber().equals(carl.getIndexNumber())
                    && student.getStudentClass().equals(carl.getStudentClass())) {
                carl = student;
            }
            if (student.getIndexNumber().equals(daniel.getIndexNumber())
                    && student.getStudentClass().equals(daniel.getStudentClass())) {
                daniel = student;
            }
        }
        for (Parent parent : parents) { // Get Updated info of parent1 and parent2
            if (parent.getPhone().equals(parent1.getPhone())) {
                parent1 = parent;
            }
            if (parent.getPhone().equals(parent2.getPhone())) {
                parent2 = parent;
            }
        }
        // Check Carl and Daniel parent info for edits to their parent/NOK info updated to the new parent's/NOK's info
        assertTrue(carl.getParent().equals(parent1));
        assertTrue(daniel.getParent().equals(parent1));
        assertFalse(carl.getParent().equals(parent2));
        assertFalse(daniel.getParent().equals(parent2));
    }
}
