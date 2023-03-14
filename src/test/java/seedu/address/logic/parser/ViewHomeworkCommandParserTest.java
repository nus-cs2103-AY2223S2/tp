package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.HomeworkIsCompletePredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;

class ViewHomeworkCommandParserTest {
    private ViewHomeworkCommandParser parser = new ViewHomeworkCommandParser();

    @Test
    public void parse_validArgsWithMultipleKeywords_returnsViewHomeworkCommand() throws ParseException {
        String args = " n/alice n/bob n/charlie " + PREFIX_STATUS + " completed";
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(new NameContainsKeywordsPredicate(
                List.of("alice", "bob", "charlie")), new HomeworkIsCompletePredicate(true), false);
        assertEquals(expectedCommand, new ViewHomeworkCommandParser().parse(args));
    }

    @Test
    public void parse_validArgsWithCompletedStatus_returnsViewHomeworkCommand() throws ParseException {
        String args = " " + PREFIX_STATUS + " completed";
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(PREDICATE_SHOW_ALL_STUDENTS,
                new HomeworkIsCompletePredicate(true), true);
        assertEquals(expectedCommand, new ViewHomeworkCommandParser().parse(args));
    }

    @Test
    public void parse_validArgsWithIncompleteStatus_returnsViewHomeworkCommand() throws ParseException {
        String args = " " + PREFIX_STATUS + " pending";
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(PREDICATE_SHOW_ALL_STUDENTS,
                new HomeworkIsCompletePredicate(false), true);
        assertEquals(expectedCommand, new ViewHomeworkCommandParser().parse(args));
    }

    @Test
    public void parse_validArgs_returnsViewHomeworkCommand() throws ParseException {
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(
                new NameContainsKeywordsPredicate(List.of("Alice", "Bob")),
                new HomeworkIsCompletePredicate(false), false);
        assertEquals(expectedCommand, parser.parse("n/Alice n/Bob"));
    }
}
