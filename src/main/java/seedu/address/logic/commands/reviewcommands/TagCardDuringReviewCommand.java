package seedu.address.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * The TagCardDuringReviewCommand class is responsible for tagging the
 * current card being reviewed with a specified tag.
 */
public class TagCardDuringReviewCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Easy/Medium/Hard";

    public static final String MESSAGE_SUCCESS = "Card is tagged with %1$s!";

    private final Tag tag;

    /**
     * Constructs a new TagCardDuringReviewCommand object with the specified tag name.
     *
     * @param tag the name of the tag to assign to the current card being reviewed
     */
    public TagCardDuringReviewCommand(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    /**
     * Executes the tagging operation by calling the tagCurrentCardInReview() method of the Model object.
     *
     * @param model the Model object containing the cards to be tagged
     * @return a CommandResult object with a success message
     * @throws CommandException if an error occurs during the execution of the command
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.tagCurrentCardInReview(this.tag);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.tag.tagName));
    }
}
