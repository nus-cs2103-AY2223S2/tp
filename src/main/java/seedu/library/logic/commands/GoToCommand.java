package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;
import seedu.library.model.bookmark.Bookmark;



/**
 * open up a browser and goes to specified index bookmark's url if present.
 */
public class GoToCommand extends Command {
    public static final String COMMAND_WORD = "goto";
    public static final String URI_OPS_ERROR = "could not go to url";
    public static final String NOT_SUPPORTED = "this function is not supported on your OS ";
    public static final String EMPTY_URL_ERROR = "url field is not present cannot goto site";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": opens up specified index bookmark's url if present\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_GOTO_BOOKMARK_SUCCESS = "Goto url of Bookmark: %1$s";
    private final Index targetIndex;
    public GoToCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bookmark> lastShownList = model.getFilteredBookmarkList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
        }

        Bookmark bookmarkToGoto = lastShownList.get(targetIndex.getZeroBased());
        String url = bookmarkToGoto.getUrl().toString();
        try {
            if (url.isEmpty()) {
                throw new CommandException(EMPTY_URL_ERROR);
            }
            openUrl(url);
        } catch (IOException ioe) {
            throw new CommandException(URI_OPS_ERROR + ioe, ioe);

        }
        model.updateSelectedIndex(-1);
        return new CommandResult(String.format(MESSAGE_GOTO_BOOKMARK_SUCCESS, bookmarkToGoto), false, false, false);
    }

    /**
     * Opens url in users default browser
     * @param url
     * @throws IOException
     */
    public static void openUrl(String url) throws IOException, CommandException {
        URI targetUrl = URI.create(url);
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win") || os.contains("mac")) {
            Desktop.getDesktop().browse(targetUrl);
        } else { //Desktop package not supported in this system
            if (os.contains("nix") || os.contains("nux")) {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(String.format("xdg-open %s", url));
            } else {
                throw new CommandException(NOT_SUPPORTED);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GoToCommand)) {
            return false;
        }

        // state check
        GoToCommand e = (GoToCommand) other;
        return other == this
                || (other instanceof GoToCommand
                && targetIndex.equals(e.targetIndex));
    }
}
