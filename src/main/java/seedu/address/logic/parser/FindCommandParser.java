package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULES;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        PersonContainsKeywordsPredicate pred = new PersonContainsKeywordsPredicate();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_GENDER, PREFIX_MAJOR, PREFIX_MODULES, PREFIX_RACE, PREFIX_COMMS);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
            );
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            pred.withName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            pred.withPhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            pred.withEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            pred.withAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            pred.withGender(argMultimap.getValue(PREFIX_GENDER).get());
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            pred.withMajor(argMultimap.getValue(PREFIX_MAJOR).get());
        }
        if (argMultimap.getValue(PREFIX_RACE).isPresent()) {
            pred.withRace(argMultimap.getValue(PREFIX_RACE).get());
        }
        if (argMultimap.getValue(PREFIX_COMMS).isPresent()) {
            pred.withComms(argMultimap.getValue(PREFIX_COMMS).get());
        }
        if (argMultimap.getValue(PREFIX_MODULES).isPresent()) {
            pred.withModules(argMultimap.getValue(PREFIX_MODULES).get());
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            pred.withTag(argMultimap.getValue(PREFIX_TAG).get());
        }

        // parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        // parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULES)).ifPresent(editPersonDescriptor::setModules);

        return new FindCommand(pred);
    }

}
