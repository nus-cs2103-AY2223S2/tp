package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnassignPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnassignPatientCommand object
 */
public class UnassignPatientCommandParser implements Parser<UnassignPatientCommand> {

    @Override
    public UnassignPatientCommand parse(String args) throws ParseException {
        try {
            List<Index> indexList = ParserUtil.parseTwoIndexes(args);
            Index patientIndex = indexList.get(0);
            Index doctorIndex = indexList.get(1);
            return new UnassignPatientCommand(patientIndex, doctorIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignPatientCommand.MESSAGE_USAGE), pe);
        }
    }
}
