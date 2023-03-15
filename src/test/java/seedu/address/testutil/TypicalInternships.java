package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InternBuddy;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship AMAZON = new InternshipBuilder().withCompanyName("Amazon")
            .withRole("Cloud Architect")
            .withStatus("assessment")
            .withDate("2023-02-01")
            .withTags("aws", "back").build();
    public static final Internship FOODPANDA = new InternshipBuilder().withCompanyName("Food Panda")
            .withRole("Back end Developer")
            .withStatus("assessment")
            .withDate("2023-02-02").build();
    public static final Internship GOLDMAN = new InternshipBuilder().withCompanyName("Goldman")
            .withRole("Cyber Security Analyst")
            .withStatus("offered")
            .withDate("2023-02-03").build();
    public static final Internship GRAB = new InternshipBuilder().withCompanyName("Grab")
            .withRole("Front end Engineer")
            .withStatus("rejected")
            .withDate("2023-02-04").build();
    public static final Internship RIOTGAMES = new InternshipBuilder().withCompanyName("Riot Games")
            .withRole("Game Client Developer")
            .withStatus("interview")
            .withDate("2023-02-05")
            .withTags("game", "developer").build();

    public static final Internship SAMSUNG = new InternshipBuilder().withCompanyName("Samsung")
            .withRole("Android Developer")
            .withStatus("applied")
            .withDate("2023-02-06").build();
    public static final Internship SUPERCELLGAMES = new InternshipBuilder().withCompanyName("Supercell Games")
            .withRole("Game Designer")
            .withStatus("new")
            .withDate("2023-02-07")
            .withTags("design", "game").build();


    // Manually added
    public static final Internship TESLA = new InternshipBuilder().withCompanyName("Tesla")
            .withRole("App Developer")
            .withStatus("offered")
            .withDate("2023-02-08").build();
    public static final Internship NITENDOGAMES = new InternshipBuilder().withCompanyName("Nitendo Games")
            .withRole("Game Developer")
            .withStatus("interview")
            .withDate("2023-02-09").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Internship APPLE = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_APPLE)
            .withRole(VALID_ROLE_APPLE)
            .withStatus(VALID_STATUS_APPLE)
            .withDate(VALID_DATE_APPLE)
            .withTags(VALID_TAG_FRONT).build();
    public static final Internship GOOGLE = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_GOOGLE)
            .withRole(VALID_ROLE_GOOGLE)
            .withStatus(VALID_STATUS_GOOGLE)
            .withDate(VALID_DATE_GOOGLE)
            .withTags(VALID_TAG_FRONT, VALID_TAG_BACK)
            .build();

    public static final String KEYWORD_MATCHING_GAMES = "Games"; // A keyword that matches GAMES

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code InternBuddy} with all the typical internships.
     */
    public static InternBuddy getTypicalInternBuddy() {
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
