package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.MeetUpIndex;
import seedu.address.model.meetup.Participants;
import seedu.address.model.meetup.exceptions.DuplicateMeetUpException;
import seedu.address.model.meetup.exceptions.MeetUpClashException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.TimePeriod;

/**
 * Adds a meeting to the address book
 */
public class OrganiseCommand extends Command {

    public static final String COMMAND_WORD = "organise";
    public static final String MESSAGE_ORGANISE_NEW_MEETING_SUCCESS = "Organised a new meeting";
    public static final String MESSAGE_SCHEDULE_RECOMMENDATION_SUCCESS = "Added a new meeting from recommendations";
    public static final String MESSAGE_NO_SUCH_RECOMMENDATION = "No recommendation with this index";
    public static final String MESSAGE_DUPLICATE_MEETING = "Duplicate meetings are not allowed";
    public static final String MESSAGE_NO_SUCH_PERSON = "%s no longer exists in your contacts.";
    public static final String MESSAGE_NO_SUCH_PERSON_ID = "Person with index %s does not exist";
    public static final String MESSAGE_MEET_UP_CLASH = "Meet up clashes with scheduled meet ups or "
            + "participant timetables";

    private final ContactIndex index;
    private final TimePeriod timePeriod;
    private final Location location;
    private final Participants participants;

    /**
     * Constructor for a {@code OrganiseCommand}.
     * @param index A recommendation's index.
     */
    public OrganiseCommand(ContactIndex index) {
        this.index = index;
        this.timePeriod = null;
        this.location = null;
        this.participants = null;
    }

    /**
     * Constructor for a {@code OrganiseCommand}.
     * @param timePeriod The day and time of the meeting.
     * @param location The location of the meeting.
     * @param participants The participants of the meeting.
     */
    public OrganiseCommand(TimePeriod timePeriod, Location location, Participants participants) {
        this.index = null;
        this.timePeriod = timePeriod;
        this.location = location;
        this.participants = participants;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //for recommendations
        if (this.index != null) {
            return organiseRecommendation(model);
        }
        //for customised new meetings
        return organiseCustomisedMeeting(model);
    }

    /**
     * Organises a new meeting based on customised inputs.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException CommandException If an error occurs during command execution.
     */
    private CommandResult organiseCustomisedMeeting(Model model) throws CommandException {

        MeetUpIndex newIndex = model.getMeetUpIndex();
        List<Person> people = new ArrayList<>();
        assert participants != null;

        for (ContactIndex contactIndex : participants.getContactIndices()) {
            Optional<Person> person = model.getPersonByIndex(contactIndex);
            if (person.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_NO_SUCH_PERSON_ID, contactIndex.toString()));
            }
            people.add(person.get());
        }
        participants.setParticipants(people);
        MeetUp meetUp = new MeetUp(timePeriod, location, participants, newIndex);
        addMeetUp(meetUp, model);
        return new CommandResult(MESSAGE_ORGANISE_NEW_MEETING_SUCCESS);
    }

    /**
     * Adds a meet up to storage.
     * @throws CommandException if a duplicate meet is to be added.
     */
    private void addMeetUp(MeetUp meetUp, Model model) throws CommandException {
        try {
            //check if current meetup up has class with other meetups
            model.addMeetUp(meetUp);
        } catch (DuplicateMeetUpException dme) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        } catch (MeetUpClashException muc) {
            throw new CommandException(MESSAGE_MEET_UP_CLASH);
        }
        model.updateObservableMeetUpList();
    }

    /**
     * Organises a new meeting based on meet recommendations.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException CommandException If an error occurs during command execution.
     */
    private CommandResult organiseRecommendation(Model model) throws CommandException {
        if (model.getRecommendationByIndex(this.index).isEmpty()) {
            throw new CommandException(MESSAGE_NO_SUCH_RECOMMENDATION);
        }
        Recommendation recommendation = model.getRecommendationByIndex(this.index).get();
        Participants participants = model.getParticipants();

        for (Person person : participants.getParticipants()) {
            if (!model.hasPerson(person)) {
                throw new CommandException(String.format(MESSAGE_NO_SUCH_PERSON, person.getName()));
            }
        }

        MeetUpIndex newIndex = model.getMeetUpIndex();
        MeetUp meetUp = new MeetUp(recommendation, participants, newIndex);
        addMeetUp(meetUp, model);
        return new CommandResult(MESSAGE_SCHEDULE_RECOMMENDATION_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof OrganiseCommand
                && participants.equals(((OrganiseCommand) other).participants)
                && location.equals(((OrganiseCommand) other).location)
                && timePeriod == ((OrganiseCommand) other).timePeriod);
    }
}
