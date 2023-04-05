package seedu.address.model.parent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParents.ALICE;
import static seedu.address.testutil.TypicalParents.BOB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ParentBuilder;
import seedu.address.testutil.TypicalStudents;

public class ParentTest {
    private Name nameTest = new Name("test");
    private Age ageTest = new Age("15");
    private Image imageTest = new Image("defaultParent.png");
    private Email emailTest = new Email("testing123@gmail.com");
    private Phone phoneTest = new Phone("91234567");
    private Address addrTest = new Address("21 Lower Kent Ridge Rd");
    private Set<Tag> tags = new HashSet<>();

    private Parent parent = new Parent(nameTest, ageTest, imageTest, emailTest, phoneTest, addrTest, tags);

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new ParentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name and phone number, different age -> returns true
        Person editedAliceAge = new ParentBuilder(ALICE).withAge(VALID_AGE_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceAge));

        // same name and phone number, different email -> returns true
        Person editedAliceEmail = new ParentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceEmail));

        // same name and phone number, different Address -> returns true
        Person editedAliceAddress = new ParentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAliceAddress));

        // same name and phone number, different tags -> returns true
        Person editedAliceTags = new ParentBuilder(ALICE).withTags(VALID_TAG_QUIET).build();
        assertTrue(ALICE.isSamePerson(editedAliceTags));

        // same name and phone number, different email -> returns true
        Person editedAlice = new ParentBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ParentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different phone number, all other attributes same -> returns false
        editedAlice = new ParentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new ParentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ParentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new ParentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new ParentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different age -> returns false
        editedAlice = new ParentBuilder(ALICE).withAge(VALID_AGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ParentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ParentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ParentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ParentBuilder(ALICE).withTags(VALID_TAG_QUIET).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void constructorTest() {
        // Throws error due to empty input for COMPULSORY inputs
        assertThrows(IllegalArgumentException.class, () -> new Parent(new Name(""), ageTest, imageTest, emailTest,
                phoneTest, addrTest, tags));
        // No error thrown as empty input is for OPTIONAL inputs
        assertDoesNotThrow(() -> new Parent(nameTest, new Age("Insert parent age here!"), imageTest, emailTest,
                phoneTest, addrTest, tags));
        // No Error thrown due to correct inputs
        assertDoesNotThrow(() -> new Parent(nameTest, ageTest, imageTest, emailTest, phoneTest, addrTest, tags));
    }

    @Test
    public void setPhoneTest() {
        Phone newPhone = new Phone("95557333");
        Phone currPhone = phoneTest;
        // currPhone == currPhone
        assertTrue(parent.getPhone().equals(currPhone));
        // currPhone == newPhone
        assertFalse(parent.getPhone().equals(newPhone));
        parent.setPhone(newPhone);
        // newPhone == currPhone
        assertFalse(parent.getPhone().equals(currPhone));
        // newPhone == newPhone
        assertTrue(parent.getPhone().equals(newPhone));
    }

    @Test
    public void isValidParentNumberTest() {
        // invalid Parent Number
        assertFalse(Parent.isValidParentNumber("")); // empty string
        assertFalse(Parent.isValidParentNumber(" ")); // spaces ONLY
        assertFalse(Parent.isValidParentNumber("^")); // only non-numeric characters
        assertFalse(Parent.isValidParentNumber("1*")); // contains non-numeric characters
        assertFalse(Parent.isValidParentNumber("22")); // insufficient values given (minimum 3)
        assertFalse(Parent.isValidParentNumber("twenty")); // alphabets
        assertFalse(Parent.isValidParentNumber("999s")); // contains non-numeric characters
        assertFalse(Parent.isValidParentNumber("-11111")); // contains non-numeric characters (NEGATIVE VALUES)

        // valid Parent Number

        // 3 digits phone number (Minimum)
        assertTrue(Parent.isValidParentNumber("999"));
        // 8 digits phone number
        assertTrue(Parent.isValidParentNumber("91234567"));
        // LONG phone number
        assertTrue(Parent.isValidParentNumber("123456789012345678901234567890123456789012345678901234567890"));
        // Phone number with only 0s
        assertTrue(Parent.isValidParentNumber("000000"));
    }

    @Test
    public void getParticularsTest() {
        Parent parent = new Parent(nameTest, ageTest, imageTest, emailTest, phoneTest, addrTest, tags);
        // Invalid particulars retrieval
        assertFalse(parent.getName() == null);
        assertFalse(parent.getPhone() == null);
        assertFalse(parent.getAge() == null);
        assertFalse(parent.getStudents() == null);
        assertFalse(parent.getImage() == null);
        assertFalse(parent.getEmail() == null);
        assertFalse(parent.getAddress() == null);
        assertFalse(parent.getTags() == null);
        assertFalse(parent.getComment() == null);

        // Valid particulars retrieval
        assertTrue(parent.getComment().equals(new Comment("No comment"))); // Does not have args for Parent
        assertTrue(parent.getStudentClass() == null); // Does not have args for Parent
        assertTrue(parent.getIndexNumber() == null); // Does not have args for Parent
        assertTrue(parent.getName().equals(nameTest));
        assertTrue(parent.getPhone().equals(phoneTest));
        assertTrue(parent.getAge().equals(ageTest));
        assertTrue(parent.getStudents().equals(new ArrayList<Student>()));
        assertTrue(parent.getImage().equals(imageTest));
        assertTrue(parent.getEmail().equals(emailTest));
        assertTrue(parent.getAddress().equals(addrTest));
        assertTrue(parent.getTags().equals(tags));
    }

    @Test
    public void addRemoveStudentTest() {
        List<Student> sampleStudentList = TypicalStudents.getTypicalStudents();
        Student student = sampleStudentList.get(1);
        // Initial state with 0 student
        List<Student> studentList = parent.getStudents();
        assertTrue(studentList.size() == 0);

        // Add 1 student to list
        parent.addStudent(student);
        studentList = parent.getStudents();
        assertTrue(studentList.size() > 0);

        // Remove 1 student from list
        parent.removeStudent(student);
        studentList = parent.getStudents();
        assertTrue(studentList.size() == 0);
        assertFalse(studentList.contains(student));

        // Add students from sampleStudentList to parent's student list
        parent.addStudents(sampleStudentList);
        studentList = parent.getStudents();
        assertTrue(studentList.equals(sampleStudentList));

        // Remove particular student from sampleStudentList from parent's student list
        Student studentToRemove = sampleStudentList.get(3);
        parent.removeStudent(studentToRemove);
        studentList = parent.getStudents();
        assertFalse(studentList.contains(studentToRemove));
        assertTrue(sampleStudentList.contains(studentToRemove));
    }
}
