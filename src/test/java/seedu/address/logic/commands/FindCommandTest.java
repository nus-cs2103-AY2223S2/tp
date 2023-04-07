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
import seedu.address.logic.parser.predicates.FullMatchKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

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
        FullMatchKeywordsPredicate firstPredicate =
                new FullMatchKeywordsPredicate(firstMultimap);
        FullMatchKeywordsPredicate secondPredicate =
                new FullMatchKeywordsPredicate(secondMultimap);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    // TODO
    // Change behavior to disallow field-less find.
    @Test
    public void execute_noArgs_allPersonFound() {
        int totalNumOfPersons = model.getAddressBook().getPersonList().size();
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, totalNumOfPersons);
        FullMatchKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getAddressBook().getPersonList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FullMatchKeywordsPredicate preparePredicate(String userInput) {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EDUCATION, PREFIX_REMARK, PREFIX_MODULE, PREFIX_TAG);
        return new FullMatchKeywordsPredicate(argMultimap);
    }

    @Test
    public void execute_singleNameArg_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FullMatchKeywordsPredicate predicate = preparePredicate(" n/meier");
        FindCommand command = new FindCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TypicalPersons.BENSON, TypicalPersons.DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameArgs_onePersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FullMatchKeywordsPredicate predicate = preparePredicate(" n/meier n/ben");
        FindCommand command = new FindCommand(predicate);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(predicate);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(TypicalPersons.BENSON), model.getFilteredPersonList());
    }

}
