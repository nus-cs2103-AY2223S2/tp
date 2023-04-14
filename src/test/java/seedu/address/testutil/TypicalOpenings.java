package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_FEB_OA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_MARCH_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SHOPEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ultron.model.Ultron;
import seedu.ultron.model.opening.Opening;

/**
 * A utility class containing a list of {@code Opening} objects to be used in tests.
 */
public class TypicalOpenings {

    public static final Opening MICROSOFT = new OpeningBuilder().withPosition("Software Engineer")
            .withStatus("APPLIED").withEmail("microsoft@outlook.com")
            .withCompany("Microsoft").withRemark("Dream job")
            .withKeydates(new ArrayList<String>(Arrays.asList("OA", "2022-03-01"))).build();
    public static final Opening APPLE = new OpeningBuilder().withPosition("Frontend Engineer")
            .withStatus("INTERVIEWING")
            .withEmail("apple@apple.com").withCompany("Apple")
            .withRemark("I love apple")
            .withKeydates(new ArrayList<String>(Arrays.asList("OA", "2022-03-01")),
                        new ArrayList<String>(Arrays.asList("Interview", "2022-02-01"))).build();
    public static final Opening GRAB = new OpeningBuilder().withPosition("Backend Engineer").withCompany("Grab")
            .withEmail("grab@grab.com").withStatus("REJECTED")
            .withRemark("I didn't like Grab anyways").build();
    public static final Opening BYTEDANCE = new OpeningBuilder()
            .withPosition("Fullstack engineer").withCompany("Bytedance")
            .withEmail("bytedance@bytedance.com").withStatus("INTERVIEWING")
            .withRemark("I want to meet tiktok influencers")
            .withKeydates(new ArrayList<String>(Arrays.asList("Interview", "2022-03-05"))).build();
    public static final Opening META = new OpeningBuilder().withPosition("Game Developer").withCompany("Meta")
            .withEmail("meta@meta.com").withStatus("FOUND")
            .withRemark("Is the metaverse bad?").build();
    public static final Opening AMAZON = new OpeningBuilder().withPosition("Cloud Engineer").withCompany("Amazon")
            .withEmail("amazon@amazon.com").withStatus("OFFERED")
            .withRemark("I don't really like Jeff Bezos though").build();
    public static final Opening NETFLIX = new OpeningBuilder().withPosition("Backend Engineer").withCompany("Netflix")
            .withEmail("netflix@netflix.com").withStatus("ACCEPTED")
            .withRemark("Bing chilling").build();

    // Manually added - Opening's details found in {@code CommandTestUtil}
    public static final Opening GOOGLE = new OpeningBuilder().withPosition(VALID_POSITION_GOOGLE)
            .withCompany(VALID_COMPANY_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
            .withRemark(VALID_REMARK_GOOGLE).withKeydates(VALID_KEYDATE_FEB_OA).build();
    public static final Opening SHOPEE = new OpeningBuilder().withPosition(VALID_POSITION_SHOPEE)
            .withCompany(VALID_COMPANY_SHOPEE)
            .withEmail(VALID_EMAIL_SHOPEE).withStatus(VALID_STATUS_SHOPEE)
            .withRemark(VALID_REMARK_SHOPEE).withKeydates(VALID_KEYDATE_MARCH_INTERVIEW, VALID_KEYDATE_FEB_OA)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalOpenings() {} // prevents instantiation

    /**
     * Returns an {@code Ultron} with all the typical openings.
     */
    public static Ultron getTypicalUltron() {
        Ultron ultron = new Ultron();
        for (Opening opening : getTypicalOpenings()) {
            ultron.addOpening(opening);
        }
        return ultron;
    }

    public static List<Opening> getTypicalOpenings() {
        return new ArrayList<>(Arrays.asList(MICROSOFT, BYTEDANCE, AMAZON, APPLE, GRAB, META, NETFLIX));
    }
}
