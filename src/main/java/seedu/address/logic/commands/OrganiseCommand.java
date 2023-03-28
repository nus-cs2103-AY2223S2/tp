package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import org.joda.time.LocalTime;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Participants;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.exceptions.DuplicateMeetUpException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;
import java.util.List;

/**
 * Adds a meeting to the address book
 */
public class OrganiseCommand extends Command {

    public static final String COMMAND_WORD = "organise";
    public static final String MESSAGE_ORGANISE_NEW_MEETING_SUCCESS = "Organised a new meeting";
    public static final String MESSAGE_SCHEDULE_RECOMMENDATION_SUCCESS = "Added a new meeting from recommendations";
    public static final String MESSAGE_NO_SUCH_RECOMMENDATION = "No recommendation with this index";
    public static final String MESSAGE_DUPLICATE_MEETING = "This is a duplicate meeting";

    private final ContactIndex index;
    private final TimePeriod timePeriod;
    private final Location location;
    private final Participants indices;


    public OrganiseCommand(ContactIndex index) {
        this.index = index;
        this.timePeriod = null;
        this.location = null;
        this.indices = null;
    }

    public OrganiseCommand(Day day, LocalTime startTime, LocalTime endTime, Location location, Participants indices) {
        this.index = null;
        this.timePeriod = new TimeBlock(startTime, endTime, day);
        this.location = location;
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //for recommendations
        if (this.index != null) {
            if (model.getRecommendationByIndex(this.index).isEmpty()) {
                throw new CommandException(MESSAGE_NO_SUCH_RECOMMENDATION);
            }
            Recommendation recommendation = model.getRecommendationByIndex(this.index).get();
            Participants participants = model.getParticipants();
            ContactIndex newIndex = model.getMeetUpIndex();
            MeetUp meetUp = new MeetUp(recommendation, participants, newIndex);
            model.addMeetUp(meetUp);
            model.updateObservableMeetUpList();
            return new CommandResult(MESSAGE_SCHEDULE_RECOMMENDATION_SUCCESS);
        }

        //for customised new meetings
        ContactIndex newIndex = model.getMeetUpIndex();
        MeetUp meetUp = new MeetUp(timePeriod, location, indices, newIndex);
        try {
            model.addMeetUp(meetUp);
        } catch (DuplicateMeetUpException dm) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.updateObservableMeetUpList();
        return new CommandResult(MESSAGE_ORGANISE_NEW_MEETING_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrganiseCommand
                && indices.equals(((OrganiseCommand) other).indices)
                && location.equals(((OrganiseCommand) other).location)
                && timePeriod == ((OrganiseCommand) other).timePeriod);
    }
}
