package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.predicates.ContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        String first = "first";
        String second = "second";
        ArgumentMultimap firstMultimap = ArgumentTokenizer.tokenize(first);
        ArgumentMultimap secondMultimap = ArgumentTokenizer.tokenize(second);
        ContainsKeywordsPredicate firstPredicate =
                new ContainsKeywordsPredicate(firstMultimap);
        ContainsKeywordsPredicate secondPredicate =
                new ContainsKeywordsPredicate(secondMultimap);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    // TODO
    // Change behavior to disallow field-less filter.
    @Test
    public void execute_noArgs_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ContainsKeywordsPredicate preparePredicate(String userInput) {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EDUCATION, PREFIX_REMARK, PREFIX_MODULE, PREFIX_TAG);
        return new ContainsKeywordsPredicate(argMultimap);
    }

    @Test
    public void execute_singleNameArg_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ContainsKeywordsPredicate predicate = preparePredicate(" n/meier");
        FilterCommand command = new FilterCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TypicalPersons.BENSON, TypicalPersons.DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameArgs_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ContainsKeywordsPredicate predicate = preparePredicate(" n/meier n/car");
        FilterCommand command = new FilterCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TypicalPersons.BENSON, TypicalPersons.CARL, TypicalPersons.DANIEL),
                model.getFilteredPersonList());
    }

}
