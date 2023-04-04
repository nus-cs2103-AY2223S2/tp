package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses {@code args} into a {@code ListCommand} and returns it.
     *
     * @param args Arguments.
     * @return {@code ListCommand} for execution.
     * @throws ParseException If {@code args} does not conform the expected format.
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim().toLowerCase();
        switch (trimmedArgs) {
        case "":
        case "unpaired":
        case "paired":
            return new ListCommand(trimmedArgs);
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                ListCommand.COMMAND_WORD,
                ListCommand.COMMAND_PROMPTS,
                ListCommandParser::validate, "<[UNPAIRED \\ PAIRED]>");
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) throws RecommendationException {
        if (map.getPreamble().contains(" ")) {
            throw new RecommendationException("Too many arguments.");
        }
        return true;
    }
}
