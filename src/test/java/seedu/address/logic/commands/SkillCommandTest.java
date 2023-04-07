package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.NoteContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

public class SkillCommandTest {

    private Model model = new ModelManager((getTypicalAddressBook()), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NoteContainsKeywordsPredicate firstPredicate =
                new NoteContainsKeywordsPredicate("first");
        NoteContainsKeywordsPredicate secondPredicate =
                new NoteContainsKeywordsPredicate("second");

        SkillCommand skillFirstCommand = new SkillCommand(firstPredicate);
        SkillCommand skillSecondCommand = new SkillCommand(secondPredicate);

        // Same object -> returns true
        assertTrue(skillFirstCommand.equals(skillFirstCommand));

        // Same values -> returns true
        SkillCommand skillFirstCommandCopy = new SkillCommand(firstPredicate);
        assertTrue(skillFirstCommand.equals(skillFirstCommandCopy));

        // Different types -> return false
        assertFalse(skillFirstCommand.equals(1));
        assertFalse(skillSecondCommand.equals(2));

        // Null -> return false
        assertFalse(skillFirstCommand.equals(null));
        assertFalse(skillSecondCommand.equals(null));

        // Different person -> return false
        assertFalse(skillFirstCommand.equals(skillSecondCommand));
    }

    @Test
    public void execute_zero_keywords_errorThrown() {
        Person personToSkill = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder personInList = new PersonBuilder(personToSkill);

        Person skillPerson = personInList
                .withNotes("Java")
                .build();

        NoteContainsKeywordsPredicate emptyNotePredicate = new NoteContainsKeywordsPredicate(" ");
        SkillCommand skillCommand = new SkillCommand(emptyNotePredicate);

        CommandException exceptionThrown = assertThrows(CommandException.class, () -> {
            skillCommand.execute(model);
        });

        System.out.println(exceptionThrown.getMessage());

//        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SkillCommand.MESSAGE_USAGE),
//                exceptionThrown.getMessage());
        System.out.println(exceptionThrown.getMessage());
    }
}
