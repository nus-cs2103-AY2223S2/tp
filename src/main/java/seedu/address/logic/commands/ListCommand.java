package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FISHES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TANKS;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;

/**
 * Lists all Fishes in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_FISHES = "Listed all fishes";

    public static final String MESSAGE_SUCCESS_TANKS = "Listed all tanks";


    public static final String MESSAGE_USAGE = COMMAND_WORD + " fishes: lists all fishes.\n"
            + COMMAND_WORD + " tanks: lists all tanks.";

    /* Singleton for ListFishCommand */
    public static final ListCommand LIST_FISHES = new ListFishCommand();

    /* Singleton for ListTankCommand */
    public static final ListCommand LIST_TANKS = new ListTankCommand();

    private static class ListFishCommand extends ListCommand {

        @Override
        public CommandResult execute(Model model) {
            requireNonNull(model);
            model.updateFilteredFishList(PREDICATE_SHOW_ALL_FISHES);
            model.setGuiMode(GuiSettings.GuiMode.DISPLAY_FISHES_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_FISHES, false, false, true);
        }
    }

    private static class ListTankCommand extends ListCommand {
        @Override
        public CommandResult execute(Model model) {
            requireNonNull(model);
            model.updateFilteredTankList(PREDICATE_SHOW_ALL_TANKS);
            model.setGuiMode(GuiSettings.GuiMode.DISPLAY_TANKS_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_TANKS, false, false, true);
        }
    }
}
