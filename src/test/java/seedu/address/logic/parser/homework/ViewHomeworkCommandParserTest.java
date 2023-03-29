package seedu.address.logic.parser.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.homework.ViewHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.HomeworkIsCompletePredicate;
import seedu.address.model.student.NamePredicate;



class ViewHomeworkCommandParserTest {
    private ViewHomeworkCommandParser parser = new ViewHomeworkCommandParser();

    @Test
    public void parse_validArgsWithMultipleKeywords_returnsViewHomeworkCommand() throws ParseException {
        String args = " name/alice name/bob name/charlie " + PREFIX_STATUS + " completed";
        List<String> names = List.of("alice", "bob", "charlie");
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(names, new NamePredicate(
                List.of("alice", "bob", "charlie")), new HomeworkIsCompletePredicate(true), false);
        assertEquals(expectedCommand, new ViewHomeworkCommandParser().parse(args));
    }

    @Test
    public void parse_validArgsWithCompletedStatus_returnsViewHomeworkCommand() throws ParseException {
        String args = " " + PREFIX_STATUS + " completed";
        List<String> names = List.of("alice", "bob", "charlie");
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(names, PREDICATE_SHOW_ALL_STUDENTS,
                new HomeworkIsCompletePredicate(true), true);
        assertEquals(expectedCommand, new ViewHomeworkCommandParser().parse(args));
    }

    @Test
    public void parse_validArgsWithIncompleteStatus_returnsViewHomeworkCommand() throws ParseException {
        String args = " " + PREFIX_STATUS + " pending";
        List<String> names = List.of("alice", "bob", "charlie");
        ViewHomeworkCommand expectedCommand = new ViewHomeworkCommand(names, PREDICATE_SHOW_ALL_STUDENTS,
                new HomeworkIsCompletePredicate(false), true);
        assertEquals(expectedCommand, new ViewHomeworkCommandParser().parse(args));
    }
}
