package seedu.internship.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internship.model.InternBuddy;
import seedu.internship.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternshipsVariableDates {
    private static LocalDate currentDate = LocalDate.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final Internship AMAZON = new InternshipBuilder().withCompanyName("Amazon")
            .withRole("Cloud Architect")
            .withStatus("assessment")
            .withDate(currentDate.plusDays(-1).format(formatter))
            .withComment("I love Amazon!")
            .withTags("aws", "back").build();
    public static final Internship FOODPANDA = new InternshipBuilder().withCompanyName("Food Panda")
            .withRole("Back end Developer")
            .withStatus("assessment")
            .withComment("I love Food Panda!")
            .withDate(currentDate.plusDays(0).format(formatter)).build();
    public static final Internship GOLDMAN = new InternshipBuilder().withCompanyName("Goldman")
            .withRole("Cyber Security Analyst")
            .withStatus("offered")
            .withComment("I love Goldman!")
            .withDate(currentDate.plusDays(1).format(formatter)).build();
    public static final Internship GRAB = new InternshipBuilder().withCompanyName("Grab")
            .withRole("Front end Engineer")
            .withStatus("rejected")
            .withComment("I love Grab!")
            .withDate(currentDate.plusDays(0).format(formatter)).build();
    public static final Internship RIOTGAMES = new InternshipBuilder().withCompanyName("Riot Games")
            .withRole("Game Client Developer")
            .withStatus("interview")
            .withDate(currentDate.plusDays(7).format(formatter))
            .withComment("I love Riot Games!")
            .withTags("game", "developer").build();

    public static final Internship SAMSUNG = new InternshipBuilder().withCompanyName("Samsung")
            .withRole("Android Developer")
            .withStatus("applied")
            .withDate(currentDate.plusDays(-100).format(formatter))
            .withComment("I love Samsung!").build();

    public static final Internship SUPERCELLGAMES = new InternshipBuilder().withCompanyName("Supercell Games")
            .withRole("Game Designer")
            .withStatus("new")
            .withDate(currentDate.plusDays(6).format(formatter))
            .withComment("I love Supercell Games!")
            .withTags("design", "game").build();


    private TypicalInternshipsVariableDates() {} // prevents instantiation

    /**
     * Returns an {@code InternBuddy} with all the typical internships.
     */
    public static InternBuddy getTypicalInternBuddyVariableDates() {
        InternBuddy ab = new InternBuddy();
        for (Internship internship : getTypicalInternships()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(AMAZON, FOODPANDA, GOLDMAN, GRAB, RIOTGAMES, SAMSUNG, SUPERCELLGAMES));
    }
}
