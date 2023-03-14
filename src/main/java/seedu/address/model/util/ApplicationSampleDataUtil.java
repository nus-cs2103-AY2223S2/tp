package seedu.address.model.util;

import seedu.address.model.InternshipBook;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class ApplicationSampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] {
            new Application(new Role("Software Engineer"),
                    new CompanyName("Google"),
                    new CompanyEmail("googlehires@gmail.com"),
                    new Status("interested")),
            new Application(new Role("Cloud Engineer Intern"),
                    new CompanyName("Amazon"),
                    new CompanyEmail("amazonhires@amazon.com"),
                    new Status("applied")),
            new Application(new Role("Design and Innovation Intern"),
                    new CompanyName("Apple"),
                    new CompanyEmail("applehires@apple.com"),
                    new Status("applied")),
            new Application(new Role("Software Testing Intern"),
                    new CompanyName("Microsoft"),
                    new CompanyEmail("microsofthires@microsoft.com"),
                    new Status("interested")),
            new Application(new Role("Junior Trader Analyst"),
                    new CompanyName("Hudson River Trading"),
                    new CompanyEmail("hrthires@hrt.com"),
                    new Status("accepted")),
            new Application(new Role("Data Analyst Intern"),
                    new CompanyName("Bloomberg"),
                    new CompanyEmail("bloomberghires@bloomberg.com"),
                    new Status("rejected"))
        };
    }

    public static ReadOnlyInternshipBook getSampleInternshipBook() {
        InternshipBook sampleIb = new InternshipBook();
        for (Application sampleApplication : getSampleApplications()) {
            sampleIb.addApplication(sampleApplication);
        }
        return sampleIb;
    }
}
