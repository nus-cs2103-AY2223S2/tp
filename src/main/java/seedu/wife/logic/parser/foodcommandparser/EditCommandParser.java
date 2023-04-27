package seedu.wife.logic.parser.foodcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.logic.commands.foodcommands.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_UNIT;

import seedu.wife.commons.core.index.Index;
import seedu.wife.commons.util.StringUtil;
import seedu.wife.logic.commands.foodcommands.EditCommand;
import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.logic.parser.ArgumentMultimap;
import seedu.wife.logic.parser.ArgumentTokenizer;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_UNIT, PREFIX_QUANTITY,
                        PREFIX_EXPIRY_DATE, PREFIX_TAG);

        Index index;

        index = StringUtil.getIndexFromCommand(argMultimap.getPreamble().trim(), EditCommand.MESSAGE_USAGE);

        EditFoodDescriptor editFoodDescriptor = new EditFoodDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editFoodDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_UNIT).isPresent()) {
            editFoodDescriptor.setUnit(ParserUtil.parseUnit(argMultimap.getValue(PREFIX_UNIT).get()));
        }
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editFoodDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            editFoodDescriptor.setExpiryDate(ParserUtil.parseExpiryDate(
                    argMultimap.getValue(PREFIX_EXPIRY_DATE).get()));
        }

        if (!editFoodDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_NOT_EDITED, EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, editFoodDescriptor);
    }

}
