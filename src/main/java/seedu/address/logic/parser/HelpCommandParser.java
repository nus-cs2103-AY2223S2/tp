package seedu.address.logic.parser;

import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) throws RecommendationException {
        throw new RecommendationException("Too many arguments.");
    }

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput String from user.
     * @return A {@code Command}.
     * @throws ParseException If {@code userInput} does not conform the expected format.
     */
    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        assert false : "This method should not be invoked";
        return null;
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                HelpCommand.COMMAND_WORD,
                HelpCommand.COMMAND_PROMPTS,
                HelpCommandParser::validate);
    }
}
