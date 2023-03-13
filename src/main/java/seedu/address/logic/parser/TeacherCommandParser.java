package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TeacherCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Teacher;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHER;

public class TeacherCommandParser implements Parser<TeacherCommand> {

    public TeacherCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TEACHER);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TeacherCommand.MESSAGE_USAGE), ive);
        }

        String teacherValue = argMultimap.getValue(PREFIX_TEACHER).orElse("");
        Teacher teacher = new Teacher(teacherValue);

        return new TeacherCommand(index, teacher);
    }

}
