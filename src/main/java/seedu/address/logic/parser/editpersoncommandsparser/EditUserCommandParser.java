package seedu.address.logic.parser.editpersoncommandsparser;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditUserCommandParser extends EditPersonCommandParser implements Parser<EditUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditUserCommand parse(String args) throws ParseException {
        requireNonNull(args);
        EditPersonDescriptor editPersonDescriptor = this.parseForTags(args);

        return new EditUserCommand(editPersonDescriptor);
    }

    @Override
    public Optional<Index> parseIndex(String index) throws ParseException {
        return Optional.empty();
    }
}
