package seedu.address.logic.parser;

import seedu.address.logic.commands.AddEntityCommand;
import seedu.address.logic.commands.AddItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddEntityCommandParser implements Parser<AddEntityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEntityCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CLASSIFICATION, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASSIFICATION, PREFIX_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEntityCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Classification classification = ParserUtil.parseClassification(
            argMultimap.getValue(PREFIX_CLASSIFICATION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (classification.isCharacter()) {
            //Call AddCharacterCommmand
            return null;
        } else if (classification.isItem()) {
            Item item = new Item(name, Item.DEFAULT_COST,Item.DEFAULT_WEIGHT, tagList);
            return new AddItemCommand(item);
        } else if (classification.isMob()) {
            // Call AddMobCommand
            return null;
        } else {
            return null;
        }


    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
