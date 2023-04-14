package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.powercards.logic.commands.cardcommands.AddCardCommand;
import seedu.powercards.logic.commands.cardcommands.AddCardCommand.AddCardDescriptor;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Question;

/**
 * Parses input arguments and creates a new AddCardCommand object
 */
public class AddCardCommandParser implements Parser<AddCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCardCommand
     * and returns an AddCardCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCardCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }

        AddCardDescriptor cardDescriptor = new AddCardDescriptor();
        Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        cardDescriptor.setQuestion(question);

        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());
        cardDescriptor.setAnswer(answer);

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            cardDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        return new AddCardCommand(cardDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
