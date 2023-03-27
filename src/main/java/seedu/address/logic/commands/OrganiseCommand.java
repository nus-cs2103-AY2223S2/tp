package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import org.joda.time.LocalTime;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.exceptions.DuplicateMeetUpException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.TimeBlock;
import seedu.address.model.scheduler.time.TimePeriod;

import java.util.List;
import java.util.Set;

public class OrganiseCommand extends Command {

    public static final String COMMAND_WORD = "organise";
    public static final String MESSAGE_SUCCESS = "Organised a new meeting";

    //need error messages for wrong formats


    private final ContactIndex index;
    private final TimePeriod timePeriod;
    private final Location location;
    private final Set<ContactIndex> indices;


    public OrganiseCommand(ContactIndex index) {
        //TODO grab from saved suggestions the recommendation by index
        this.index = index;
        this.timePeriod = null;
        this.location = null;
        this.indices = null;
    }

    public OrganiseCommand(Day day, LocalTime startTime, LocalTime endTime, Location location, Set<ContactIndex> indices) {
        this.index = null;
        this.timePeriod = new TimeBlock(startTime, endTime, day);
        this.location = location;
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.index != null) {
            return new CommandResult("NOT IMPLEMENTED YET");
//            SortedList<Recommendation> recommendations = model.getObservableRecommendationList(); //todo shouldnt be red after integration merge
//
//            Recommendation recommendation = model.getRecommendationByIndex(this.index);
//            if (recommendation == null) {
//                throw new CommandException("NO RECOMMENDATION WITH THIS INDEX"); //todo make this a constant
//            }
//            Set<ContactIndex> participants = model.getParticipants(); //todo model should save this
//            //todo check if participants is null, tho technically if recc isnt null, this shld not be either, maybe an assert here
//            ContactIndex newIndex = model.getMeetUpIndex();
//            MeetUp meetUp = new MeetUp(recommendation, participants, newIndex);
//            model.addMeetUp(meetUp);
//            //model.updateObservableMeetUpList();
//            //todo update the meetup observable list
        }

        //
        ContactIndex newIndex = model.getMeetUpIndex();
        MeetUp meetUp = new MeetUp(timePeriod, location, indices, newIndex);
        try {
            model.addMeetUp(meetUp);
        } catch (DuplicateMeetUpException dm) {
            throw new CommandException("DUPLICATE MEET");
        }

        model.addMeetUp(meetUp);
        List<MeetUp> l = model.getObservableMeetUpList();
        l.forEach(System.out::println);


        return new CommandResult("successfully added new meeting");
    }
}
