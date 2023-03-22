package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

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
        List<String> names = argMultimap.getAllValues(PREFIX_NAME);
        if (!names.isEmpty()) {
            pred.withName(names);
        }
        List<String> phones = argMultimap.getAllValues(PREFIX_PHONE);
        if (!phones.isEmpty()) {
            pred.withPhone(phones);
        }
        List<String> emails = argMultimap.getAllValues(PREFIX_EMAIL);
        if (!emails.isEmpty()) {
            pred.withEmail(emails);
        }
        List<String> address = argMultimap.getAllValues(PREFIX_ADDRESS);
        if (!address.isEmpty()) {
            pred.withAddress(address);
        }
        List<String> genders = argMultimap.getAllValues(PREFIX_GENDER);
        if (!genders.isEmpty()) {
            pred.withGender(genders);
        }
        List<String> majors = argMultimap.getAllValues(PREFIX_MAJOR);
        if (!majors.isEmpty()) {
            pred.withMajor(majors);
        }
        List<String> races = argMultimap.getAllValues(PREFIX_RACE);
        if (!races.isEmpty()) {
            pred.withRace(races);
        }
        List<String> comms = argMultimap.getAllValues(PREFIX_COMMS);
        if (!comms.isEmpty()) {
            pred.withComms(comms);
        }
        List<String> mods = argMultimap.getAllValues(PREFIX_MODULES);
        if (!mods.isEmpty()) {
            pred.withModules(mods);
        }
        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (!tags.isEmpty()) {
            pred.withTag(tags);
        }

        return new FindCommand(pred);
    }

}
