package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NoteContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class SkillCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
    public void execute_oneKeyword_noPersonFound() {
        Person personToSkill = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder personInList = new PersonBuilder(personToSkill);

        Person skillPerson = personInList
                .withNotes("Java")
                .build();

        Predicate<Person> predicate = x -> true;
        predicate = predicate.and(new NoteContainsKeywordsPredicate("Python"));

        SkillCommand skillCommand = new SkillCommand(predicate);

        model.setPerson(personToSkill, skillPerson);
        model.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size());

        CommandResult result = skillCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(expectedResult, result);

    }

    @Test
    public void execute_oneKeyword_showOneApplicant() {
        Person personToSkill = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder personInList = new PersonBuilder(personToSkill);

        Person skillPerson = personInList
                .withNotes("Java")
                .build();

        Predicate<Person> predicate = x -> true;
        predicate = predicate.and(new NoteContainsKeywordsPredicate(skillPerson.getNotesString()));

        SkillCommand skillCommand = new SkillCommand(predicate);

        model.setPerson(personToSkill, skillPerson);
        model.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size());

        CommandResult result = skillCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(expectedResult, result);

    }

    @Test
    public void execute_oneKeyword_showMultipleApplicants() {
        Person personToSkill1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder person1InList = new PersonBuilder(personToSkill1);

        Person skillPerson1 = person1InList
                .withNotes("Python")
                .build();

        Person personToSkill2 = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getOneBased());
        PersonBuilder person2InList = new PersonBuilder(personToSkill2);

        Person skillPerson2 = person2InList
                .withNotes("Python")
                .build();

        Predicate<Person> predicate = x -> true;
        predicate = predicate.and(new NoteContainsKeywordsPredicate(skillPerson1.getNotesString()));

        SkillCommand skillCommand = new SkillCommand(predicate);

        model.setPerson(personToSkill1, skillPerson1);
        model.setPerson(personToSkill2, skillPerson2);
        model.updateFilteredPersonList(predicate);

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size());

        CommandResult result = skillCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(expectedResult, result);

    }

    @Test
    public void execute_multipleKeywords_noPersonFound() {
        Person personToSkill1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder person1InList = new PersonBuilder(personToSkill1);

        Person skillPerson1 = person1InList
                .withNotes("Python", "Java")
                .build();

        Person personToSkill2 = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getOneBased());
        PersonBuilder person2InList = new PersonBuilder(personToSkill2);

        Person skillPerson2 = person2InList
                .withNotes("Python")
                .build();

        Predicate<Person> predicate = x -> true;
        predicate = predicate.and(new NoteContainsKeywordsPredicate("Swift"));
        SkillCommand skillCommand = new SkillCommand(predicate);

        model.setPerson(personToSkill1, skillPerson1);
        model.setPerson(personToSkill2, skillPerson2);
        model.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size());

        CommandResult result = skillCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(expectedResult, result);

    }

    @Test
    public void execute_multipleKeywords_showOneApplicant() {
        Person personToSkill1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder person1InList = new PersonBuilder(personToSkill1);

        Person skillPerson1 = person1InList
                .withNotes("Python", "Java")
                .build();

        Person personToSkill2 = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getOneBased());
        PersonBuilder person2InList = new PersonBuilder(personToSkill2);

        Person skillPerson2 = person2InList
                .withNotes("Python")
                .build();

        Predicate<Person> predicate = x -> true;
        String keywords = skillPerson1.getNotesString();
        String[] keywords_list = keywords.split(" ");
        for (int i = 0; i < keywords_list.length; i++) {
            String keyword = keywords_list[i];
            predicate = predicate.and(new NoteContainsKeywordsPredicate(keyword));
        }

        SkillCommand skillCommand = new SkillCommand(predicate);

        model.setPerson(personToSkill1, skillPerson1);
        model.setPerson(personToSkill2, skillPerson2);
        model.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size());

        CommandResult result = skillCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(expectedResult, result);

    }

    @Test
    public void execute_multipleKeywords_showMultipleApplicants() {
        Person personToSkill1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
        PersonBuilder person1InList = new PersonBuilder(personToSkill1);

        Person skillPerson1 = person1InList
                .withNotes("ML", "AI")
                .build();

        Person personToSkill2 = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getOneBased());
        PersonBuilder person2InList = new PersonBuilder(personToSkill2);

        Person skillPerson2 = person2InList
                .withNotes("ML", "AI")
                .build();

        Predicate<Person> predicate = x -> true;
        String keywords = skillPerson1.getNotesString();
        String[] keywords_list = keywords.split(" ");
        for (int i = 0; i < keywords_list.length; i++) {
            String keyword = keywords_list[i];
            predicate = predicate.and(new NoteContainsKeywordsPredicate(keyword));
        }

        SkillCommand skillCommand = new SkillCommand(predicate);

        model.setPerson(personToSkill1, skillPerson1);
        model.setPerson(personToSkill2, skillPerson2);
        model.updateFilteredPersonList(predicate);

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size());

        CommandResult result = skillCommand.execute(model);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(expectedResult, result);

    }
}
