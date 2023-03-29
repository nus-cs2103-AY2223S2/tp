package seedu.library.logic.commands;

import static seedu.library.model.bookmark.Genre.getValidGenres;

import java.util.List;

import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;

/**
 * Lists all valid genres to the user
 */
public class GenreCommand extends Command {

    public static final String COMMAND_WORD = "genre";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<String> validGenres = getValidGenres();
        String genresString = String.join(", ", validGenres);
        model.updateSelectedIndex(-1);
        return new CommandResult("Valid genres: " + genresString);
    }
}
