package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;
import seedu.library.model.bookmark.Bookmark;

/**
 * Views a bookmark identified using it's displayed index from the library.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the bookmark identified by the index number used in the displayed bookmark list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_VIEWED_SUCCESS = "Viewed Bookmark: %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bookmark> lastShownList = model.getFilteredBookmarkList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
        }
        Bookmark bookmarkToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateSelectedBookmark(bookmarkToView);
        model.updateSelectedIndex(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_VIEW_VIEWED_SUCCESS, bookmarkToView), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
