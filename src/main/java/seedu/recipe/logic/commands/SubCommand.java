package seedu.recipe.logic.commands;

import seedu.recipe.model.Model;

public class SubCommand extends Command {

    public static final String COMMAND_WORD = "sub";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches across RecipeBook for substitutions for the "
            + "specified ingredient (case insensitive) and displays them in a list.\n"
            + "Parameters: INGREDIENT\n"
            + "Example: " + COMMAND_WORD + " chicken";

    public static final String MESSAGE_NO_STORED_SUBS = "No substitutions are available. Check back later or " +
            "update the RecipeBook with some suggested substitutions!";

    public static final String MESSAGE_DISPLAY_STORED_SUBS = "Here are a list of all potential substitutes for the" +
            "ingredient %s: %s";

    private final String subList;

    public SubCommand(String subList) {
        this.subList = subList;
    }

    @Override
    public CommandResult execute(Model model) {
        if (subList.isBlank()) {
            return new CommandResult((MESSAGE_NO_STORED_SUBS));
        }
        return new CommandResult(MESSAGE_DISPLAY_STORED_SUBS);
    }
}
