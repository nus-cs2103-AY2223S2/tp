package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeletePolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeletePolicyCommand object
 */
public class DeletePolicyCommandParser implements Parser<DeletePolicyCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code userInput} in the context of the DeletePolicyCommand and returns
     * a DeletePolicyCommand object for execution.
     * @param userInput The input that is given by the user to conduct a command.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    public DeletePolicyCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_POLICY_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_INDEX)
                || argMultimap.getPreamble().isEmpty()) {
            logger.info("Missing parameters: " + userInput);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePolicyCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            logger.info("Missing Index: " + userInput);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE), ive);
        }

        Index policyIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_POLICY_INDEX).get());

        logger.info("Parsed: "
                + index + " "
                + PREFIX_POLICY_INDEX + policyIndex + " ");
        return new DeletePolicyCommand(index, policyIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultiMap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argMultiMap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMultiMap.getValue(prefix).isPresent());
    }
}
