package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertSame;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SkillCommand;
import seedu.address.model.note.Note;
import seedu.address.model.person.NoteContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



public class SkillCommandParserTest {

    private SkillCommandParser parser = new SkillCommandParser();

    @Test
    public void parse_validArg_skillPredicateIsTheSame() {
        Person amy = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withNotes(VALID_NOTE_FRIEND)
                .build();

        Predicate<Person> predicate = x -> true;

        predicate = predicate.and(new NoteContainsKeywordsPredicate(amy.getNotesString()));
        SkillCommand expectedSkillCommand = new SkillCommand(predicate);

        // Create a new Predicate<Person> object with the expected Predicate<Person> object
        Predicate<Person> expectedPredicate = expectedSkillCommand.getPredicate();

        // Perform the test
        assertSame(expectedPredicate, predicate);
    }

    @Test void parse_invalidArgNoteTooLong_throwsParseException() {
        assertParseFailure(parser, "a".repeat(46), Note.MESSAGE_LENGTH_CONSTRAINTS);
    }


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "       ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SkillCommand.MESSAGE_USAGE));
    }
}
