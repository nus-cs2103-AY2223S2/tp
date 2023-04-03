package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.location.util.TypicalLocation.SERANGOON;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EduMate;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.location.Location;
import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.location.util.LocationDataUtil;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.testutil.PersonBuilder;

class MeetCommandTest {

    // EMPTY TIMETABLE standard message
    private static final String FULL_DAY_PERIOD = "Start: 8 AM  End: 10 PM";
    private static final String EMPTY_TIMETABLE_MESSAGE = "MONDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "TUESDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "WEDNESDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "THURSDAY"
        + "\n" + FULL_DAY_PERIOD
        + "\n\n" + "FRIDAY"
        + "\n" + FULL_DAY_PERIOD;

    @Test
    public void execute_serangoonParticipants_serangoonSuggested() throws CommandException {
        Model model = new ModelManager(new EduMate(), new UserPrefs(), new EduMateHistory());

        User serangoonUser = new PersonBuilder(model.getUser())
                .withStation(SERANGOON).buildAsUser();
        model.setUser(serangoonUser);

        Person serangoonPerson = new PersonBuilder(serangoonUser).build();
        model.addPerson(serangoonPerson);

        MeetCommand meetCommand = new MeetCommand(
                Set.of(new ContactIndex(0), new ContactIndex(1)),
                LocationDataUtil.MEET_LOCATIONS, 10);

        meetCommand.execute(model);

        List<Recommendation> recommendations = model.getObservableRecommendationList();
        List<Location> locationsNearSerangoon = recommendations.stream()
                .map(Recommendation::getLocation)
                .filter(location -> DistanceUtil.getDistance(location, SERANGOON) < 1)
                .collect(Collectors.toList());

        assertFalse(locationsNearSerangoon.isEmpty());
    }

    @Test
    public void execute_invalidContactIndices_throwsCommandException() {
        Model model = new ModelManager(new EduMate(), new UserPrefs(), new EduMateHistory());

        MeetCommand meetCommand = new MeetCommand(
                Set.of(new ContactIndex(0), new ContactIndex(1)),
                LocationDataUtil.MEET_LOCATIONS, 10);

        assertThrows(CommandException.class, () -> meetCommand.execute(model));
    }
}
