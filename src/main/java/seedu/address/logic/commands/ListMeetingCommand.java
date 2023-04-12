package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import seedu.address.model.Model;

/**
 * Lists all meetings stored in FAid to the user.
 */
public class ListMeetingCommand extends Command {

    public static final String COMMAND_WORD = "listMeeting";

    public static final String MESSAGE_SUCCESS = "Listed all meetings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
