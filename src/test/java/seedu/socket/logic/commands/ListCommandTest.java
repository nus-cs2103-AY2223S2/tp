package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BENSON;
import static seedu.socket.testutil.TypicalPersons.DANIEL;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.socket.logic.parser.ListCommandParser;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.person.predicate.ListCommandLanguagePredicate;
import seedu.socket.model.person.predicate.ListCommandTagPredicate;
import seedu.socket.model.person.tag.Language;
import seedu.socket.model.person.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSocket(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSocket(), new UserPrefs());
    }


    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        Set<Language> languages = new HashSet<>(); // no language keywords are given
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        Set<Tag> tags = new HashSet<>();
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, false);
        assertCommandSuccess(command,
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Set<Language> languages = new HashSet<>(); // no language keywords are given
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        Set<Tag> tags = new HashSet<>();
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, false);
        assertCommandSuccess(command, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void execute_listIsFiltered_showsEverythingWithRandomArgs() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        ListCommand command = null;
        try {
            command = new ListCommandParser().parse("random arg");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertCommandSuccess(command, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        Set<Tag> firstTags = new HashSet<>();
        firstTags.add(new Tag("friends"));
        Set<Language> firstLanguages = new HashSet<>();
        firstLanguages.add(new Language("python"));
        ListCommandTagPredicate firstPredicateTag =
                new ListCommandTagPredicate(firstTags);
        ListCommandLanguagePredicate firstPredicateLang =
                new ListCommandLanguagePredicate(firstLanguages);

        Set<Tag> secondTags = new HashSet<>();
        firstTags.add(new Tag("friend"));
        Set<Language> secondLanguages = new HashSet<>();
        secondLanguages.add(new Language("java"));
        ListCommandTagPredicate secondPredicateTag =
                new ListCommandTagPredicate(secondTags);
        ListCommandLanguagePredicate secondPredicateLang =
                new ListCommandLanguagePredicate(secondLanguages);


        ListCommand listFirstCommand = new ListCommand(firstPredicateTag, firstPredicateLang, true);
        ListCommand listSecondCommand = new ListCommand(secondPredicateTag, secondPredicateLang, true);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommand));

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(firstPredicateTag, firstPredicateLang, true);
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listFirstCommand.equals(listSecondCommand));
    }

    @Test
    public void execute_zeroTagKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Set<Language> languages = new HashSet<>(); // no language keywords are given
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("unknownTag"));
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
    @Test
    public void execute_zeroLanguagesKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Set<Tag> tags = new HashSet<>(); // no language keywords are given
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        Set<Language> languages = new HashSet<>();
        languages.add(new Language("unknownLanguage"));
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_tagKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("friends"));
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        Set<Language> languages = new HashSet<>();
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_languageKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Set<Tag> tags = new HashSet<>();
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        Set<Language> languages = new HashSet<>();
        languages.add(new Language("Java"));
        languages.add(new Language("JavaScript"));
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Set<Tag> tags = new HashSet<>();
        ListCommandTagPredicate tagPredicate = new ListCommandTagPredicate(tags);
        tags.add(new Tag("friends"));
        Set<Language> languages = new HashSet<>();
        languages.add(new Language("Java"));
        languages.add(new Language("JavaScript"));
        ListCommandLanguagePredicate langPredicate = new ListCommandLanguagePredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }
}
