package seedu.patientist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.DeletePatientToDoCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientToDoCommand object
 */
public class DeletePatientToDoCommandParser implements Parser<DeletePatientToDoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePatientToDoCommand
     * and returns an DeletePatientToDoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePatientToDoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index indexPerson;
        Index indexToDo;

        try {
            String[] indexes = argMultimap.getPreamble().strip().split("\\s+");
            if (indexes.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeletePatientToDoCommand.MESSAGE_USAGE));
            }
            indexPerson = ParserUtil.parseIndex(indexes[0]);
            indexToDo = ParserUtil.parseIndex(indexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePatientToDoCommand.MESSAGE_USAGE), pe);
        }

        return new DeletePatientToDoCommand(indexPerson, indexToDo);

    }

}
