package seedu.address.logic.parser.drugparser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVE_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIRECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIDE_EFFECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STORAGE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRADE_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.drugcommands.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drug.ActiveIngredient;
import seedu.address.model.drug.Direction;
import seedu.address.model.drug.Drug;
import seedu.address.model.drug.Purpose;
import seedu.address.model.drug.SideEffect;
import seedu.address.model.drug.StorageCount;
import seedu.address.model.drug.TradeName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    @Override
    public AddCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput,
                        PREFIX_TRADE_NAME, PREFIX_ACTIVE_INGREDIENT,
                        PREFIX_DIRECTION, PREFIX_PURPOSE,
                        PREFIX_SIDE_EFFECT, PREFIX_STORAGE_COUNT);

        if (!arePrefixesPresent(argMultimap, PREFIX_TRADE_NAME, PREFIX_ACTIVE_INGREDIENT, PREFIX_DIRECTION,
                PREFIX_PURPOSE, PREFIX_SIDE_EFFECT, PREFIX_STORAGE_COUNT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    seedu.address.logic.commands.drugcommands.AddCommand.MESSAGE_USAGE));
        }

        TradeName tradeName = ParserUtil.parseTradeName(argMultimap.getValue(PREFIX_TRADE_NAME).get());
        ActiveIngredient activeIngredient = ParserUtil.parseActiveIngredient(
                argMultimap.getValue(PREFIX_ACTIVE_INGREDIENT).get());
        Direction direction = ParserUtil.parseDirection(argMultimap.getValue(PREFIX_DIRECTION).get());
        Purpose purpose = ParserUtil.parsePurpose(argMultimap.getValue(PREFIX_PURPOSE).get());
        SideEffect sideEffect = ParserUtil.parseSideEffect(argMultimap.getValue(PREFIX_SIDE_EFFECT).get());
        StorageCount storageCount = ParserUtil.parseStorageCount(argMultimap.getValue(PREFIX_STORAGE_COUNT).get());

        Drug drug = new Drug(tradeName, activeIngredient, direction, purpose, sideEffect, storageCount);
        return new AddCommand(drug);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
