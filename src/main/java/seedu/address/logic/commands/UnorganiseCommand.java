package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.MeetUpIndex;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class UnorganiseCommand extends Command {

    public static final String COMMAND_WORD = "unorganise";
    private static final String MESSAGE_NO_SUCH_MEETUP = "Meet up with index %s does not exist.";
    private static final String MESSAGE_SUCCESS = "Deleted meet up.";

    private final MeetUpIndex meetUpIndex;

    public UnorganiseCommand(MeetUpIndex meetUpIndex) {
        this.meetUpIndex = meetUpIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //model to check if the index exists via get
        Optional<MeetUp> meetUp = model.getMeetUpByIndex(this.meetUpIndex);
        if (meetUp.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_MEETUP, this.meetUpIndex.getMeetUpIndex()));
        }
        model.deleteMeetUp(meetUpIndex);
        model.updateObservableMeetUpList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
