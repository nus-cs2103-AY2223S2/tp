package seedu.address.logic.parser.editpersoncommandsparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_NO_PREAMBLE_REQUIRED;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditUserCommandParser extends PersonCommandParser implements Parser<EditUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditUserCommand parse(String args) throws ParseException {
        requireNonNull(args);
        PersonDescriptor editPersonDescriptor = this.parseForTags(args);

        return new EditUserCommand(editPersonDescriptor);
    }

    @Override
    public Optional<Index> parseIndex(String index) throws ParseException {
        if (!index.isEmpty()) {
            throw new ParseException(MESSAGE_NO_PREAMBLE_REQUIRED);
        }
        return Optional.empty();
    }

    @Override
    public String getMessageUsage() {
        return EditUserCommand.MESSAGE_USAGE;
    }
}
