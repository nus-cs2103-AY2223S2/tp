package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;


/**
 * Parses input arguments and creates a new {@code AddPolicyCommand} object
 */
public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddPolicyCommand}
     * @return an {@code AddPolicyCommand} object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POLICY_NAME, PREFIX_POLICY_START_DATE,
                PREFIX_POLICY_PREMIUM, PREFIX_POLICY_FREQUENCY);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            logger.info("Missing Index: " + args);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE), ive);
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_NAME, PREFIX_POLICY_START_DATE,
                PREFIX_POLICY_PREMIUM, PREFIX_POLICY_FREQUENCY) || argMultimap.getPreamble().isEmpty()) {
            logger.info("Missing parameters: " + args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }
        PolicyName policyName = ParserUtil.parsePolicyName(argMultimap.getValue(PREFIX_POLICY_NAME).get());
        CustomDate startDate = ParserUtil.parseCustomDate(argMultimap.getValue(PREFIX_POLICY_START_DATE).get());
        Premium premium = ParserUtil.parsePremium(argMultimap.getValue(PREFIX_POLICY_PREMIUM).get());
        Frequency frequency = ParserUtil.parseFrequency(argMultimap.getValue(PREFIX_POLICY_FREQUENCY).get());

        Policy policy = new Policy(policyName, startDate, premium, frequency);

        logger.info("Parsed: " + args);
        return new AddPolicyCommand(index, policy);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
