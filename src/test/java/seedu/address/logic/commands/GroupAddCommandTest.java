package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.GroupAddCommand.GROUP_ADD_PERSON_SUCCESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GroupAddCommand.
 */
public class GroupAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private AddressBook addressBook = getTypicalAddressBook();
    private List<Tag> tagList = Arrays.asList(new Tag("friends"));
    private Set<Tag> tagSet = new HashSet<>(tagList);

    @Test
    public void execute_invalidIndex_failure() throws ParseException, CommandException {
        Index index = ParserUtil.parseIndex("100");
        Set<Tag> tagSet = new HashSet<>(tagList);
        assertThrows(CommandException.class, () -> new GroupAddCommand(index, tagSet).execute(model));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        GroupAddCommand command = new GroupAddCommand(outOfBoundIndex, tagSet);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GroupAddCommand command = new GroupAddCommand(outOfBoundIndex, tagSet);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void addToModifiedGroup_groupNotFound_failure() {
        GroupAddCommand groupAddCommand = new GroupAddCommand(INDEX_FIRST_PERSON, tagSet);

        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        String results = model.getAddressBook()
                .getTagList().toString();
        assertCommandFailure(groupAddCommand, model,
                String.format(GroupAddCommand.GROUP_NOT_FOUND_FAILURE,
                        "[friends]", results));
    }

    @Test
    public void addToModifiedGroup_studentAlreadyAdded_failure() throws CommandException {
        GroupAddCommand groupAddCommand = new GroupAddCommand(INDEX_FIRST_PERSON, tagSet);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);

        model.setAddressBook(new AddressBookBuilder()
                .withPerson(ALICE).withTag(new Tag("friends"))
                .build());
        assertCommandFailure(groupAddCommand, model,
                String.format(GroupAddCommand.STUDENT_ALREADY_ADDED_FAILURE,
                        "[friends]"));
    }

    @Test
    public void execute_groupAddCommand_success() throws CommandException {
        List<Tag> tagListTest = Arrays.asList(new Tag("private"));
        Set<Tag> tagSetTest = new HashSet<>(tagListTest);

        GroupAddCommand groupAddCommand = new GroupAddCommand(INDEX_FIRST_PERSON, tagSetTest);

        Set<Tag> groupsToAdd = new HashSet<>(tagListTest);
        CommandResult commandResult = new CommandResult(String.format(GROUP_ADD_PERSON_SUCCESS,
                ALICE.getName(), groupsToAdd));

        //expectedModel
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person expectedPerson = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withPayRate("10")
                .withPhone("94351253")
                .withTags("private", "friends")
                .build();
        expectedModel.setAddressBook(new AddressBookBuilder().withPerson(expectedPerson)
                .withTag(new Tag("friends")).withTag(new Tag("private"))
                .build());


        //create a test model
        model.setAddressBook(new AddressBookBuilder().withPerson(ALICE)
                .withTag(new Tag("friends")).withTag(new Tag("private"))
                .build());

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(groupAddCommand, model, commandResult, expectedModel);
    }

    @Test
    public void equals() {
        final GroupAddCommand standardCommand = new GroupAddCommand(INDEX_FIRST_PERSON, tagSet);

        final Set<Tag> diffTagSet = new HashSet<>(Arrays.asList(new Tag("Poo")));

        // same values -> returns true
        GroupAddCommand commandWithSameValues = new GroupAddCommand(INDEX_FIRST_PERSON, tagSet);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new GroupAddCommand(INDEX_SECOND_PERSON, tagSet)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new GroupAddCommand(INDEX_FIRST_PERSON, diffTagSet)));
    }
}
