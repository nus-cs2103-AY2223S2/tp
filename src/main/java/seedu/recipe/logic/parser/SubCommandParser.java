package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.recipe.logic.commands.SubCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.ingredient.Ingredient;


public class SubCommandParser implements Parser<SubCommand> {

    public SubCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubCommand.MESSAGE_USAGE));
        }

        if (!Ingredient.isValidIngredientName(trimmedArgs)) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }

        Ingredient queryIngredient = Ingredient.of(trimmedArgs);

        return new SubCommand(queryIngredient);
    }
}
