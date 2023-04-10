package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    // Arguments should have the format: CLASSIFICATION NAME
    // Example: char John Cena
    private static final Pattern COMMAND_FORMAT = Pattern.compile(
            "^(?<classification>\\w+)\\s+(?<name>.+)$"
    );

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(args.trim());

        // Arguments have the wrong format
        boolean isInvalidFormat = !matcher.matches();
        if (isInvalidFormat) {
            String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
            throw new ParseException(errorMessage);
        }

        final String classificationString = matcher.group("classification");
        final String nameString = matcher.group("name");

        Name name = ParserUtil.parseName(nameString);
        Classification classification = ParserUtil.parseClassification(classificationString);
        assert name != null;
        assert classification != null;

        return new DeleteCommand(name, classification);
    }

}
