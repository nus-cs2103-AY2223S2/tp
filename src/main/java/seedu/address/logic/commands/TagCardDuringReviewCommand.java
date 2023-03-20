package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class TagCardDuringReviewCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Easy/Medium/Hard";

    public static final String MESSAGE_SUCCESS = "Card is tagged with %1$s!";

    private final String tagName;

    public TagCardDuringReviewCommand(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.tagCurrentCardInReview(this.tagName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.tagName));
    }
}
