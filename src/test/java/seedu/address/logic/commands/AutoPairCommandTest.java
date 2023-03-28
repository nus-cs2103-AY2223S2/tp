package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.FriendlyLinkBuilder;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.VolunteerBuilder;

public class AutoPairCommandTest {
    private VolunteerBuilder volunteerBuilder1 = new VolunteerBuilder()
            .withName("volunteer1")
            .withNric("S1234567I");
    private VolunteerBuilder volunteerBuilder2 = new VolunteerBuilder()
            .withName("volunteer1")
            .withNric("S1111111I");
    private ElderlyBuilder elderlyBuilder1 = new ElderlyBuilder()
            .withName("elderly1")
            .withNric("S7654321I");

    private ElderlyBuilder elderlyBuilder2 = new ElderlyBuilder()
            .withName("elderly2")
            .withNric("S7777777I");
    @Test
    public void execute_hasPairablePersons_successWithPairs() {
        Volunteer volunteer1 = volunteerBuilder1
                .withAvailableDates("2023-03-01", "2023-03-05")
                .withRegion("NORTH")
                .build();
        Elderly elderly1 = elderlyBuilder1
                .withAvailableDates("2023-03-02", "2023-03-04")
                .withRegion("NORTH")
                .build();
        Volunteer volunteer2 = volunteerBuilder2
                .withAvailableDates("2023-05-23", "2023-05-26")
                .withRegion("WEST")
                .build();
        Elderly elderly2 = elderlyBuilder2
                .withAvailableDates("2023-05-21", "2023-05-24")
                .withRegion("WEST")
                .build();
        FriendlyLinkBuilder friendlyLinkBuilder = new FriendlyLinkBuilder()
                .withVolunteers(Arrays.asList(volunteer1, volunteer2))
                .withElderly(Arrays.asList(elderly1, elderly2));
        Model model = new ModelManagerBuilder().withFriendlyLink(friendlyLinkBuilder.build()).build();

        List<Pair> expectedPairList = Arrays.asList(new Pair(elderly1, volunteer1), new Pair(elderly2, volunteer2));
        FriendlyLink expectedFriendlyLink = friendlyLinkBuilder
                .withPairs(expectedPairList).build();
        Model expectedModel = new ModelManagerBuilder().withFriendlyLink(expectedFriendlyLink).build();
        String expectedMessage = AutoPairCommand.getSuccessMessageFromPairList(expectedPairList);

        assertCommandSuccess(new AutoPairCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personsWithUnspecifiedAvailableDates_successWithPairs() {
        Volunteer volunteer1 = volunteerBuilder1
                .withRegion("NORTH")
                .build();
        Elderly elderly1 = elderlyBuilder1
                .withRegion("NORTH")
                .build();
        Volunteer volunteer2 = volunteerBuilder2
                .withRegion("WEST")
                .build();
        Elderly elderly2 = elderlyBuilder2
                .withRegion("WEST")
                .build();
        FriendlyLinkBuilder friendlyLinkBuilder = new FriendlyLinkBuilder()
                .withVolunteers(Arrays.asList(volunteer1, volunteer2))
                .withElderly(Arrays.asList(elderly1, elderly2));
        Model model = new ModelManagerBuilder().withFriendlyLink(friendlyLinkBuilder.build()).build();

        List<Pair> expectedPairList = Arrays.asList(new Pair(elderly1, volunteer1), new Pair(elderly2, volunteer2));
        FriendlyLink expectedFriendlyLink = friendlyLinkBuilder
                .withPairs(expectedPairList).build();
        Model expectedModel = new ModelManagerBuilder().withFriendlyLink(expectedFriendlyLink).build();
        String expectedMessage = AutoPairCommand.getSuccessMessageFromPairList(expectedPairList);

        assertCommandSuccess(new AutoPairCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noUnpairedPersons_successNoPairs() {
        Volunteer volunteer1 = volunteerBuilder1
                .withAvailableDates("2023-03-01", "2023-03-05")
                .withRegion("NORTH")
                .build();
        Elderly elderly1 = elderlyBuilder1
                .withAvailableDates("2023-03-02", "2023-03-04")
                .withRegion("NORTH")
                .build();
        Volunteer volunteer2 = volunteerBuilder2
                .withAvailableDates("2023-05-23", "2023-05-26")
                .withRegion("WEST")
                .build();
        Elderly elderly2 = elderlyBuilder2
                .withAvailableDates("2023-05-21", "2023-05-24")
                .withRegion("WEST")
                .build();
        List<Pair> pairList = Arrays.asList(new Pair(elderly1, volunteer1), new Pair(elderly2, volunteer2));
        FriendlyLink friendlyLink = new FriendlyLinkBuilder()
                .withVolunteers(Arrays.asList(volunteer1, volunteer2))
                .withElderly(Arrays.asList(elderly1, elderly2))
                .withPairs(pairList).build();
        Model model = new ModelManagerBuilder().withFriendlyLink(friendlyLink).build();

        assertCommandSuccess(new AutoPairCommand(), model,
                AutoPairCommand.MESSAGE_SUCCESS_NO_PAIRS, model);
    }

    @Test
    public void execute_noCompatibleDates_successNoPairs() {
        Volunteer volunteer1 = volunteerBuilder1
                .withAvailableDates("2023-03-01", "2023-03-05")
                .build();
        Elderly elderly1 = elderlyBuilder1
                .withAvailableDates("2023-03-06", "2023-03-10")
                .build();
        Volunteer volunteer2 = volunteerBuilder2
                .withAvailableDates("2023-05-23", "2023-05-26")
                .build();
        Elderly elderly2 = elderlyBuilder2
                .withAvailableDates("2023-05-27", "2023-05-29")
                .build();

        FriendlyLinkBuilder friendlyLinkBuilder = new FriendlyLinkBuilder()
                .withVolunteers(Arrays.asList(volunteer1, volunteer2))
                .withElderly(Arrays.asList(elderly1, elderly2));
        Model model = new ModelManagerBuilder().withFriendlyLink(friendlyLinkBuilder.build()).build();

        assertCommandSuccess(new AutoPairCommand(), model, AutoPairCommand.MESSAGE_SUCCESS_NO_PAIRS, model);
    }

    @Test
    public void execute_noCompatibleRegions_successNoPairs() {
        Volunteer volunteer1 = volunteerBuilder1
                .withRegion("CENTRAL")
                .build();
        Elderly elderly1 = elderlyBuilder1
                .withRegion("EAST")
                .build();
        Volunteer volunteer2 = volunteerBuilder2
                .withRegion("NORTH")
                .build();
        Elderly elderly2 = elderlyBuilder2
                .withRegion("WEST")
                .build();

        FriendlyLinkBuilder friendlyLinkBuilder = new FriendlyLinkBuilder()
                .withVolunteers(Arrays.asList(volunteer1, volunteer2))
                .withElderly(Arrays.asList(elderly1, elderly2));
        Model model = new ModelManagerBuilder().withFriendlyLink(friendlyLinkBuilder.build()).build();

        assertCommandSuccess(new AutoPairCommand(), model, AutoPairCommand.MESSAGE_SUCCESS_NO_PAIRS, model);
    }

    @Test
    public void execute_emptyFriendlyLink_successNoPairs() {
        Model model = new ModelManagerBuilder().build();

        assertCommandSuccess(new AutoPairCommand(), model, AutoPairCommand.MESSAGE_SUCCESS_NO_PAIRS, model);
    }
}
