package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignPatientCommand object
 */
public class AssignPatientCommandParser implements Parser<AssignPatientCommand> {

    @Override
    public AssignPatientCommand parse(String args) throws ParseException {
        try {
            List<Index> indexList = ParserUtil.parseTwoIndexes(args);
            Index patientIndex = indexList.get(0);
            Index doctorIndex = indexList.get(1);
            return new AssignPatientCommand(patientIndex, doctorIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignPatientCommand.MESSAGE_USAGE), pe);
        }
    }
}
