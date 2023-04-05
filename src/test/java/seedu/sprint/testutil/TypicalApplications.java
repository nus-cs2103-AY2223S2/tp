package seedu.sprint.testutil;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_GRAB;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_TAG_HIGHSALARY;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_TAG_SCHOOL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalApplications {

    public static final Application GOOGLE = new ApplicationBuilder().withRole("Software Engineer")
            .withCompanyName("Google").withCompanyEmail("googlehires@gmail.com")
            .withStatus("interested")
            .withTags("highSalary").withTask("31-12-2023", "Online Assessment").build();
    public static final Application AMAZON = new ApplicationBuilder().withRole("Cloud Engineer Intern")
            .withCompanyName("Amazon").withCompanyEmail("amazonhires@amazon.com")
            .withStatus("applied")
            .withTags("cloudTechnology").build();
    public static final Application APPLE = new ApplicationBuilder().withRole("Design and Innovation Intern")
            .withCompanyName("Apple").withCompanyEmail("applehires@apple.com")
            .withStatus("applied")
            .withTags("employeeDiscounts").build();
    public static final Application MICROSOFT = new ApplicationBuilder().withRole("Software Testing Intern")
            .withCompanyName("Microsoft").withCompanyEmail("microsofthires@microsoft.com")
            .withStatus("offered")
            .withTags("windowsSupremacy").build();
    public static final Application META = new ApplicationBuilder().withRole("UX Design Intern")
            .withCompanyName("Meta").withCompanyEmail("metahires@meta.com")
            .withStatus("applied").withTask("01-11-2023", "Online Assessment")
            .build();
    public static final Application GOVTECH = new ApplicationBuilder().withRole("CyberSecurity Intern")
            .withCompanyName("Govtech").withCompanyEmail("govtechhires@govtech.sg")
            .withStatus("applied").withTask("31-10-2023", "Interview")
            .build();

    public static final Application ANT_GROUP = new ApplicationBuilder().withRole("Backend Developer Intern")
            .withCompanyName("Ant Group").withCompanyEmail("antgrouphires@antgroup.com")
            .withStatus("offered").withTags("china")
            .withTask("31-10-2023", "Accept Offer").build();

    // Manually added - without tags
    public static final Application HUDSON_RIVER = new ApplicationBuilder().withRole("Junior Trader Analyst")
            .withCompanyName("Hudson River Trading").withCompanyEmail("hrthires@hrt.com")
            .withStatus("offered").build();
    public static final Application BLOOMBERG = new ApplicationBuilder().withRole("Data Analyst Intern")
            .withCompanyName("Bloomberg").withCompanyEmail("bloomberghires@bloomberg.com")
            .withStatus("rejected").build();

    // Manually added - Application's details found in {@code ApplicationCommandTestUtil}
    public static final Application BYTEDANCE = new ApplicationBuilder().withRole(VALID_ROLE_BYTEDANCE)
            .withCompanyName(VALID_COMPANY_NAME_BYTEDANCE).withCompanyEmail(VALID_COMPANY_EMAIL_BYTEDANCE)
            .withStatus(VALID_STATUS_BYTEDANCE).withTags(VALID_TAG_HIGHSALARY).build();
    public static final Application GRAB = new ApplicationBuilder().withRole(VALID_ROLE_GRAB)
            .withCompanyName(VALID_COMPANY_NAME_GRAB).withCompanyEmail(VALID_COMPANY_EMAIL_GRAB)
            .withStatus(VALID_STATUS_GRAB).withTags(VALID_TAG_HIGHSALARY, VALID_TAG_SCHOOL).build();


    public static final String KEYWORD_MATCHING_GOOGLE = "Google"; // A keyword that matches Google

    private TypicalApplications() {} // prevents instantiation

    /**
     * Returns an {@code InternshipBook} with all the typical applications.
     */
    public static InternshipBook getTypicalInternshipBook() {
        InternshipBook ib = new InternshipBook();
        for (Application application : getTypicalApplications()) {
            ib.addApplication(application);
        }
        return ib;
    }

    public static List<Application> getTypicalApplications() {
        return new ArrayList<>(Arrays.asList(GOOGLE, AMAZON, APPLE, MICROSOFT, META, GOVTECH));
    }
}
