package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Volunteer;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Volunteer objects.
 */
public class VolunteerBuilder extends PersonBuilderScaffold<VolunteerBuilder> {

    private Set<MedicalQualificationTag> medicalTags;

    /**
     * Creates a {@code VolunteerBuilder} with the default details.
     */
    public VolunteerBuilder() {
        super();
        medicalTags = new HashSet<>();
    }

    /**
     * Initializes the VolunteerBuilder with the data of {@code volunteerToCopy}.
     */
    public VolunteerBuilder(Volunteer volunteerToCopy) {
        super(volunteerToCopy);
        medicalTags = new HashSet<>(volunteerToCopy.getMedicalTags());
    }

    public VolunteerBuilder withMedicalTags(String ... medicalTags) {
        this.medicalTags = SampleDataUtil.getMedicalTagSet(medicalTags);
        return this;
    }

    public Volunteer build() {
        return new Volunteer(name, phone, email, address, nric,
                age, region, tags, medicalTags);
    }
}
