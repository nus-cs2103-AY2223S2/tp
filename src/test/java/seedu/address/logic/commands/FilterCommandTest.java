package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FieldContainsPartialKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.FilterDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        final FilterCommand standardCommand = new FilterCommand(FILTER_DESC_AMY);

        // same values -> returns true
        FilterDescriptor copyDescriptor = new FilterDescriptorBuilder(FILTER_DESC_AMY).build();
        FilterCommand commandWithSameValues = new FilterCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new FilterCommand(FILTER_DESC_BOB));
    }

    @Test
    public void execute_zeroKeywords_noPersonFiltered() {
        FilterDescriptor emptyDescriptor = new FilterDescriptor();
        FilterCommand command = new FilterCommand(emptyDescriptor);

        assertCommandSuccess(command, model, FilterCommand.MESSAGE_FILTER_SUCCESS, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleTag_multiplePersonsFound() {
        FilterDescriptor filterDescriptor = new FilterDescriptor();
        filterDescriptor.setTagValues(List.of("friend")); // intentional 's' missing
        Predicate<Person> predicate = preparePredicate(filterDescriptor);

        FilterCommand command = new FilterCommand(filterDescriptor);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, FilterCommand.MESSAGE_FILTER_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        FilterDescriptor personDescriptor = new FilterDescriptor();
        personDescriptor.setNameValue("l");
        personDescriptor.setAddressValue("street");
        personDescriptor.setEmailValue("example.co");
        Predicate<Person> predicate = preparePredicate(personDescriptor);

        FilterCommand command = new FilterCommand(personDescriptor);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, FilterCommand.MESSAGE_FILTER_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code PersonDescriptor} into a {@code FieldContainsPartialKeywordsPredicate}.
     */
    private FieldContainsPartialKeywordsPredicate preparePredicate(FilterDescriptor filterDescriptor) {
        return new FieldContainsPartialKeywordsPredicate(filterDescriptor.getNameValue(),
                filterDescriptor.getPhoneValue(), filterDescriptor.getEmailValue(),
                filterDescriptor.getAddressValue(), filterDescriptor.getRankValue(),
                filterDescriptor.getUnitValue(), filterDescriptor.getCompanyValue(),
                filterDescriptor.getPlatoonValue(), filterDescriptor.getTagValues());
    }
}
