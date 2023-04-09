package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.logic.commands.ClashCommand.MESSAGE_CLASH_INTERNSHIP_SUCCESS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.event.Event;
import seedu.internship.testutil.EventBuilder;
import seedu.internship.testutil.EventCatalogueBuilder;

public class ClashCommandTest {

    @Test
    public void execute_noEvents_noClash() throws CommandException {
        Model model = new ModelManager(new InternshipCatalogue(), new EventCatalogue(), new UserPrefs());
        CommandResult actual = new ClashCommand().execute(model);

        HashMap<LocalDate, List<Event>> expectedHash = new HashMap<LocalDate, List<Event>>();
        CommandResult expected = new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS,
                expectedHash.keySet().size()), ResultType.CLASH, expectedHash);

        assertEquals(actual, expected);

    }

    @Test
    public void execute_similarDeadline_noClash() throws CommandException {
        EventCatalogue eventCatalogue = new EventCatalogueBuilder().withEvent(new Event[] {
                new EventBuilder().withName("d1").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                        .withDescription("").build(),
                new EventBuilder().withName("d2").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                        .withDescription("").build(),
                new EventBuilder().withName("d3").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                        .withDescription("").build(),
                new EventBuilder().withName("d4").withStart("13/04/2023 2359").withEnd("13/04/2023 2359")
                        .withDescription("").build()
        }).build();
        Model model = new ModelManager(new InternshipCatalogue(), eventCatalogue, new UserPrefs());
        CommandResult actual = new ClashCommand().execute(model);

        HashMap<LocalDate, List<Event>> expectedHash = new HashMap<LocalDate, List<Event>>();
        CommandResult expected = new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS,
                expectedHash.keySet().size()), ResultType.CLASH, expectedHash);

        assertEquals(actual, expected);

    }

    @Test
    public void execute_eventsWithNoClash_noClash() throws CommandException {
        EventCatalogue eventCatalogue = new EventCatalogueBuilder().withEvent(new Event[] {
                new EventBuilder().withName("e1").withStart("14/04/2023 1200").withEnd("14/04/2023 1300")
                        .withDescription("").build(),
                new EventBuilder().withName("e2").withStart("14/04/2023 1300").withEnd("14/04/2023 1400")
                        .withDescription("").build(),
                new EventBuilder().withName("d1").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                        .withDescription("").build(),
                new EventBuilder().withName("d2").withStart("14/04/2023 2359").withEnd("14/04/2023 2359")
                        .withDescription("").build()
        }).build();
        Model model = new ModelManager(new InternshipCatalogue(), eventCatalogue, new UserPrefs());
        CommandResult actual = new ClashCommand().execute(model);

        HashMap<LocalDate, List<Event>> expectedHash = new HashMap<LocalDate, List<Event>>();
        CommandResult expected = new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS,
                expectedHash.keySet().size()), ResultType.CLASH, expectedHash);

        assertEquals(actual, expected);
    }

    @Test
    public void execute_eventsWithNoClashAndDeadline_noClash() throws CommandException {
        EventCatalogue eventCatalogue = new EventCatalogueBuilder().withEvent(new Event[] {
                new EventBuilder().withName("e1").withStart("14/04/2023 1200").withEnd("14/04/2023 1300")
                        .withDescription("").build(),
                new EventBuilder().withName("e2").withStart("14/04/2023 1300").withEnd("14/04/2023 1400")
                        .withDescription("").build(),
                new EventBuilder().withName("e3").withStart("14/04/2023 1400").withEnd("14/04/2023 1500")
                        .withDescription("").build(),
                new EventBuilder().withName("e4").withStart("13/04/2023 2300").withEnd("14/04/2023 0300")
                        .withDescription("").build()
        }).build();
        Model model = new ModelManager(new InternshipCatalogue(), eventCatalogue, new UserPrefs());
        CommandResult actual = new ClashCommand().execute(model);

        HashMap<LocalDate, List<Event>> expectedHash = new HashMap<LocalDate, List<Event>>();
        CommandResult expected = new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS,
                expectedHash.keySet().size()), ResultType.CLASH, expectedHash);

        assertEquals(actual, expected);

    }

    @Test
    public void execute_clashingInterviewOnSingleDay_clash() throws CommandException {
        EventCatalogue eventCatalogue = new EventCatalogueBuilder().withEvent(new Event[] {
                new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("14/04/2023 0400")
                        .withDescription("").build(),
                new EventBuilder().withName("e2").withStart("14/04/2023 1500").withEnd("14/04/2023 1800")
                        .withDescription("").build(),
                new EventBuilder().withName("e3").withStart("14/04/2023 1600").withEnd("14/04/2023 1900")
                        .withDescription("").build(),
                new EventBuilder().withName("e4").withStart("13/04/2023 2300").withEnd("14/04/2023 0400")
                        .withDescription("").build()
        }).build();
        Model model = new ModelManager(new InternshipCatalogue(), eventCatalogue, new UserPrefs());
        CommandResult actual = new ClashCommand().execute(model);

        HashMap<LocalDate, List<Event>> expectedHash = new HashMap<LocalDate, List<Event>>();
        ArrayList<Event> lst = new ArrayList<>();
        lst.add(new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("14/04/2023 0400")
                .withDescription("").build());
        lst.add(new EventBuilder().withName("e2").withStart("14/04/2023 1500").withEnd("14/04/2023 1800")
                .withDescription("").build());
        lst.add(new EventBuilder().withName("e3").withStart("14/04/2023 1600").withEnd("14/04/2023 1900")
                .withDescription("").build());
        lst.add(new EventBuilder().withName("e4").withStart("13/04/2023 2300").withEnd("14/04/2023 0400")
                .withDescription("").build());
        expectedHash.put(LocalDate.of(2023, 4, 14), lst);
        CommandResult expected = new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS,
                expectedHash.keySet().size()), ResultType.CLASH, expectedHash);

        assertEquals(actual, expected);
    }

    @Test
    public void execute_clashingOverMultipleDays_clash() throws CommandException {
        // If interview clashes over multiple days, all the days which the event clashes on should be displayed.
        // Only days with events of clashing timings should be listed.
        EventCatalogue eventCatalogue = new EventCatalogueBuilder().withEvent(new Event[] {
                new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("17/04/2023 2359")
                        .withDescription("").build(),
                new EventBuilder().withName("e2").withStart("14/04/2023 1500").withEnd("16/04/2023 1800")
                        .withDescription("").build(),
                new EventBuilder().withName("e3").withStart("15/04/2023 1600").withEnd("18/04/2023 1900")
                        .withDescription("").build(),
                new EventBuilder().withName("e4").withStart("18/04/2023 0100").withEnd("18/04/2023 0400")
                        .withDescription("").build()
        }).build();
        Model model = new ModelManager(new InternshipCatalogue(), eventCatalogue, new UserPrefs());
        CommandResult actual = new ClashCommand().execute(model);

        HashMap<LocalDate, List<Event>> expectedHash = new HashMap<LocalDate, List<Event>>();
        ArrayList<Event> day14 = new ArrayList<>();
        day14.add(new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("17/04/2023 2359")
                .withDescription("").build());
        day14.add(new EventBuilder().withName("e2").withStart("14/04/2023 1500").withEnd("16/04/2023 1800")
                .withDescription("").build());

        ArrayList<Event> day15 = new ArrayList<>();
        day15.add(new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("17/04/2023 2359")
                .withDescription("").build());
        day15.add(new EventBuilder().withName("e2").withStart("14/04/2023 1500").withEnd("16/04/2023 1800")
                .withDescription("").build());
        day15.add(new EventBuilder().withName("e3").withStart("15/04/2023 1600").withEnd("18/04/2023 1900")
                .withDescription("").build());

        ArrayList<Event> day16 = new ArrayList<>();
        day16.add(new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("17/04/2023 2359")
                .withDescription("").build());
        day16.add(new EventBuilder().withName("e2").withStart("14/04/2023 1500").withEnd("18/04/2023 1800")
                .withDescription("").build());
        day15.add(new EventBuilder().withName("e3").withStart("15/04/2023 1600").withEnd("18/04/2023 1900")
                .withDescription("").build());

        ArrayList<Event> day17 = new ArrayList<>();
        day17.add(new EventBuilder().withName("e1").withStart("14/04/2023 0200").withEnd("17/04/2023 2359")
                .withDescription("").build());
        day17.add(new EventBuilder().withName("e3").withStart("15/04/2023 1600").withEnd("18/04/2023 1900")
                .withDescription("").build());

        ArrayList<Event> day18 = new ArrayList<>();
        day18.add(new EventBuilder().withName("e3").withStart("15/04/2023 1600").withEnd("18/04/2023 1900")
                        .withDescription("").build());
        day18.add(new EventBuilder().withName("e4").withStart("18/04/2023 0100").withEnd("18/04/2023 0400")
                        .withDescription("").build());

        expectedHash.put(LocalDate.of(2023, 4, 14), day14);
        expectedHash.put(LocalDate.of(2023, 4, 15), day15);
        expectedHash.put(LocalDate.of(2023, 4, 16), day16);
        expectedHash.put(LocalDate.of(2023, 4, 17), day17);
        expectedHash.put(LocalDate.of(2023, 4, 18), day18);
        CommandResult expected = new CommandResult(String.format(MESSAGE_CLASH_INTERNSHIP_SUCCESS,
                expectedHash.keySet().size()), ResultType.CLASH, expectedHash);

        assertEquals(actual, expected);
    }


}


