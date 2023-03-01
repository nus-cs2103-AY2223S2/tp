package seedu.address.model.applicant;

public class Applicant {
    private String name;

    /**
     * constructs an Applicant.
     *
     * @param name the name of the applicant.
     */
    public Applicant(String name) {
        this.name = name;
    }

    /**
     * gets the name of an applicant.
     *
     * @return the name of the applicant.
     */
    public String getName() {
        return this.name;
    }
}
