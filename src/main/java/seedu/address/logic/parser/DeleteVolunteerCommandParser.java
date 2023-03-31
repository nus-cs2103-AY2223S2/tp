package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_NRIC;
import static seedu.address.model.person.information.Nric.MESSAGE_CONSTRAINTS;

import seedu.address.logic.commands.CommandInfo;
import seedu.address.logic.commands.DeleteVolunteerCommand;
import seedu.address.logic.commands.exceptions.RecommendationException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.information.Nric;

/**
 * Parses input arguments and creates a new DeleteVolunteerCommand object.
 */
public class DeleteVolunteerCommandParser implements Parser<DeleteVolunteerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @param args Arguments.
     * @return {@code DeleteVolunteerCommand} for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteVolunteerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVolunteerCommand.MESSAGE_USAGE));
        }
        if (!Nric.isValidNric(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_PERSON_NRIC, "volunteer", MESSAGE_CONSTRAINTS));
        }

        return new DeleteVolunteerCommand(new Nric(trimmedArgs));
    }

    /**
     * Validates the given ArgumentMultimap by checking that it fulfils certain criteria.
     *
     * @param map the ArgumentMultimap to be validated.
     * @return true if the ArgumentMultimap is valid, false otherwise.
     */
    public static boolean validate(ArgumentMultimap map) throws RecommendationException {
        if (!Nric.isValidNric(map.getPreamble())) {
            throw new RecommendationException("A valid nric should be specified");
        }
        return true;
    }

    public CommandInfo getCommandInfo() {
        return new CommandInfo(
                DeleteVolunteerCommand.COMMAND_WORD,
                DeleteVolunteerCommand.COMMAND_PROMPTS,
                DeleteVolunteerCommandParser::validate);
    }
}
