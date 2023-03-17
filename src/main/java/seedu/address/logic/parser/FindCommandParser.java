package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.person.*;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Prefix[] ALLOWED_PREFIXES = {PREFIX_NAME, PREFIX_PHONE, PREFIX_NOTE};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALLOWED_PREFIXES);
        Predicate<Person> findPredicate = x -> true; //always false predicate as default
        boolean isPrefixInput = false;

        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            isPrefixInput = true;
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            String[] nameKeywords = name.fullName.split("\\s+");
            findPredicate = findPredicate.and(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            isPrefixInput = true;
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            String[] phoneKeywords = phone.value.split("\\s+");
            findPredicate = findPredicate.and(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NOTE)) {
            isPrefixInput = true;
            Set<Note> noteList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_NOTE));
            for (Note n: noteList) {
                findPredicate = findPredicate.and(new NoteContainsKeywordsPredicate(n.noteName));
            }
        }

        if (!isPrefixInput) { //if user did not input any prefix -> name or phone searching
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");

            if (keywords.length == 1) {
                String nameOrPhone = keywords[0];
                char firstChar = nameOrPhone.charAt(0);
                if (Character.isDigit(firstChar)) {
                    return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        return new FindCommand(findPredicate);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
