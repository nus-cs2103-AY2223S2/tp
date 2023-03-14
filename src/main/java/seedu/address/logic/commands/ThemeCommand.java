package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EmployeeId;
import seedu.address.model.person.Person;

/**
 * Changes the theme of ExecutivePro's UI.
 */
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the theme of ExecutivePro's UI.\n"
            + "Parameters: The name of the theme ('light' or 'dark')\n"
            + "Example: " + COMMAND_WORD + " light";

    public static final String MESSAGE_CHANGE_THEME_SUCCESS = "Theme changed!";
    public static final String MESSAGE_INVALID_THEME = "Please provide a valid theme: 'light' or 'dark'.";

    private static final Set<String> VALID_THEMES = Set.of("light", "dark");


    private final String theme;

    public ThemeCommand(String theme) {
        this.theme = theme;
    }

    public static boolean isValidTheme(String theme) {
        return VALID_THEMES.contains(theme);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isValidTheme(theme)) {
            return new CommandResult(MESSAGE_CHANGE_THEME_SUCCESS, theme);
        }
        throw new CommandException(Messages.MESSAGE_INVALID_THEME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThemeCommand // instanceof handles nulls
                && theme.equals(((ThemeCommand) other).theme)); // state check
    }
}