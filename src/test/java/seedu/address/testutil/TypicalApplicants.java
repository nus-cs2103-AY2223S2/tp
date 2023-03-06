package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.applicant.Applicant;
/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalApplicants {
    public static final Applicant DRUM = new ApplicantBuilder().withName("Drum Mer").build();

    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant BENEDICT = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_BENEDICT).build();
    public static final Applicant CHRIS = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_CHRIS).build();

    private TypicalApplicants() {
    } // prevents instantiation

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(DRUM, BENEDICT, CHRIS));
    }
}
