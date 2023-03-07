package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindApplicationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindApplicationCommand object
 */
public class FindApplicationCommandParser implements ApplicationParser<FindApplicationCommand> {

	/**
	 * Parses the given {@code String} of arguments in the context of the FindApplicationCommand
	 * and returns a FindApplicationCommand object for execution.
	 * @throws ParseException if the user input does not conform the expected format
	 */
	public FindApplicationCommand parse(String args) throws ParseException {
		String trimmedArgs = args.trim();
		if (trimmedArgs.isEmpty()) {
			throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
							FindApplicationCommand.MESSAGE_USAGE));
		}

		String[] nameKeywords = trimmedArgs.split("\\s+");

		return new FindApplicationCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
	}

}
