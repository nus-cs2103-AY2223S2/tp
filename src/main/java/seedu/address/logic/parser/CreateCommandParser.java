package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The type Create command parser.
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    private static final Prefix PREFIX_DOC = new Prefix("doc/");
    private static final Prefix PREFIX_DES = new Prefix("m/");
    private static final Prefix PREFIX_DAYS = new Prefix("d/");

    @Override
    public CreateCommand parse(String userInput) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(userInput, PREFIX_DOC, PREFIX_DES, PREFIX_DAYS);

            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            // Extract doctor name, description and number of days from userInput
            String doctorName = argMultimap.getValue(PREFIX_DOC).orElse(null);
            String description = argMultimap.getValue(PREFIX_DES).orElse(null);
            String dayStr = argMultimap.getValue(PREFIX_DAYS).orElse(null);
            if (doctorName == null || description == null || dayStr == null) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        CreateCommand.MESSAGE_USAGE));
            }
            int days = ParserUtil.parseDay(dayStr);
            return new CreateCommand(index, doctorName, description, days);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateCommand.MESSAGE_USAGE), pe);
        }
    }
}
