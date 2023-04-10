package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class AddTagCommandTest {
    private Model model;
    private final Set<Tag> tagsToAdd = new HashSet<>(Arrays.asList(new Tag("family")));
    private final Set<Tag> tagsToAddDup = new HashSet<>(Arrays.asList(new Tag("friends")));


    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_addTagUnfilteredList_success() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddTag).withTags("friends", "family").build();
        Set<Tag> tags = tagsToAdd;
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tags);
        String expectedMessage = String.format(AddTagCommand.ADD_TAG_PERSON_SUCCESS, personToAddTag.getName(), tags);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToAddTag, expectedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTagUnfilteredList_throwsCommandException() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddTag).withTags("friends").build();
        Set<Tag> tags = new HashSet<>(tagsToAddDup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToAddTag, expectedPerson);
        expectedModel.commitAddressBook();

        AddTagCommand duplicateAddTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tags);
        assertCommandFailure(duplicateAddTagCommand, model, String.format(AddTagCommand.STUDENT_ALREADY_ADDED_FAILURE,
                personToAddTag.getName().formattedName, "[friends]"));
    }
}
