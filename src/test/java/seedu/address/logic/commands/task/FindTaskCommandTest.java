package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.NOTE_5;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.GOOGLE_T;
import static seedu.address.testutil.TypicalTodos.META_T;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ContentContainsKeywordsPredicate;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(Messages.MESSAGE_RESULT_LISTED_OVERVIEW, 0);

        TitleContainsKeywordsPredicate predicateT = prepareTitlePredicate(" ");
        ContentContainsKeywordsPredicate predicateC = prepareContentPredicate(" ");

        FindTaskCommand command = new FindTaskCommand(predicateT, predicateC);

        expectedModel.updateFilteredTodoList(predicateT);
        expectedModel.updateFilteredNoteList(predicateC);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTodoList());
        assertEquals(Collections.emptyList(), model.getFilteredNoteList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(Messages.MESSAGE_RESULT_LISTED_OVERVIEW, 3);

        TitleContainsKeywordsPredicate predicateT = prepareTitlePredicate("META GOOGLE Day");
        ContentContainsKeywordsPredicate predicateC = prepareContentPredicate("META GOOGLE Day");

        FindTaskCommand command = new FindTaskCommand(predicateT, predicateC);

        expectedModel.updateFilteredTodoList(predicateT);
        expectedModel.updateFilteredNoteList(predicateC);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(META_T, GOOGLE_T), model.getFilteredTodoList());
        assertEquals(List.of(NOTE_5), model.getFilteredNoteList());
    }

    private TitleContainsKeywordsPredicate prepareTitlePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private ContentContainsKeywordsPredicate prepareContentPredicate(String userInput) {
        return new ContentContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
