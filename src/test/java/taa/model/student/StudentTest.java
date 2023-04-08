package taa.model.student;

import static taa.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taa.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_LAB02;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_TUT_15;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taa.model.tag.Tag;
import taa.testutil.Assert;
import taa.testutil.PersonBuilder;
import taa.testutil.TypicalPersons;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> student.getClassTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.isSameStudent(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withTags(VALID_TAG_TUT_15).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new PersonBuilder(TypicalPersons.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertFalse(TypicalPersons.BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalPersons.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(TypicalPersons.BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(5));

        // different student -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different name -> returns false
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_TUT_15).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }

    @Test
    public void getName() {
        // Test pre-built Students
        Assertions.assertTrue(TypicalPersons.AMY.getName().equals(new Name(VALID_NAME_AMY)));
        Assertions.assertTrue(TypicalPersons.BOB.getName().equals(new Name(VALID_NAME_BOB)));

        // Test fresh copies of Students
        Student amyCopy = new PersonBuilder(TypicalPersons.AMY).build();
        Assertions.assertTrue(amyCopy.getName().equals(new Name(VALID_NAME_AMY)));

        Student bobCopy = new PersonBuilder(TypicalPersons.BOB).build();
        Assertions.assertTrue(bobCopy.getName().equals(new Name(VALID_NAME_BOB)));
    }

    @Test
    public void getClassTags() {
        // no tags provided --> size of set returned should be 0
        Assertions.assertEquals(TypicalPersons.CARL.getClassTags().size(), 0);

        // 1 tag provided --> returned set should contain one and only one tag with the given name
        Set<Tag> amyClassTags = TypicalPersons.AMY.getClassTags();
        Assertions.assertEquals(amyClassTags.size(), 1);
        Assertions.assertTrue(amyClassTags.contains(new Tag(VALID_TAG_LAB02)));

        // 2 tags provided --> both tags should be present in returned set, and nothing else
        Set<Tag> bobClassTags = TypicalPersons.BOB.getClassTags();
        Assertions.assertEquals(bobClassTags.size(), 2);
        Assertions.assertTrue(bobClassTags.contains(new Tag(VALID_TAG_LAB02)));
        Assertions.assertTrue(bobClassTags.contains(new Tag(VALID_TAG_TUT_15)));
    }
}
