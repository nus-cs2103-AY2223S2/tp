package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ListCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Language;
import seedu.address.model.tag.LanguageContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }


    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        Set<Language> languages = new HashSet<>(); // no language keywords are given
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
        Set<Tag> tags = new HashSet<>();
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, false);
        assertCommandSuccess(command,
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Set<Language> languages = new HashSet<>(); // no language keywords are given
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
        Set<Tag> tags = new HashSet<>();
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
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
        TagContainsKeywordsPredicate firstPredicateTag =
                new TagContainsKeywordsPredicate(firstTags);
        LanguageContainsKeywordsPredicate firstPredicateLang =
                new LanguageContainsKeywordsPredicate(firstLanguages);

        Set<Tag> secondTags = new HashSet<>();
        firstTags.add(new Tag("friend"));
        Set<Language> secondLanguages = new HashSet<>();
        secondLanguages.add(new Language("java"));
        TagContainsKeywordsPredicate secondPredicateTag =
                new TagContainsKeywordsPredicate(secondTags);
        LanguageContainsKeywordsPredicate secondPredicateLang =
                new LanguageContainsKeywordsPredicate(secondLanguages);


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
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("unknownTag"));
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
    @Test
    public void execute_zeroLanguagesKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Set<Tag> tags = new HashSet<>(); // no language keywords are given
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
        Set<Language> languages = new HashSet<>();
        languages.add(new Language("unknownLanguage"));
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
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
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
        Set<Language> languages = new HashSet<>();
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_languageKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Set<Tag> tags = new HashSet<>();
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
        Set<Language> languages = new HashSet<>();
        languages.add(new Language("Java"));
        languages.add(new Language("JavaScript"));
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Set<Tag> tags = new HashSet<>();
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tags);
        tags.add(new Tag("friends"));
        Set<Language> languages = new HashSet<>();
        languages.add(new Language("Java"));
        languages.add(new Language("JavaScript"));
        LanguageContainsKeywordsPredicate langPredicate = new LanguageContainsKeywordsPredicate(languages);
        ListCommand command = new ListCommand(tagPredicate, langPredicate, true);
        expectedModel.updateFilteredPersonList(tagPredicate.and(langPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }
}
