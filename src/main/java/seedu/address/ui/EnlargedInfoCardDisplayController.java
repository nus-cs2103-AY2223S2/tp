package seedu.address.ui;

/**
 * A controller to choose between displaying {@code EnlargedDoctorInfoCard}
 * and {@code EnlargedPatientInfoCard} on {@code ContactDisplay}.
 */
public class EnlargedInfoCardDisplayController {

    private boolean shouldDisplayDoctorInfoCard = true;
    private boolean shouldDisplayPatientInfoCard = false;
    private ContactDisplay parent;

    /**
     * Creates a {@code EnlargedInfoCardDisplayController}
     * with the given parent {@code ContactDisplay}.
     */
    public EnlargedInfoCardDisplayController(ContactDisplay parent) {
        this.parent = parent;
    }

    /**
     * Updates controller state to display {@code EnlargedDoctorInfoCard}.
     */
    public void displayDoctor() {
        shouldDisplayDoctorInfoCard = true;
        shouldDisplayPatientInfoCard = false;
        parent.setFeedbackToUser(true);
    }

    /**
     * Updates controller state to display {@code EnlargedPatientInfoCard}.
     */
    public void displayPatient() {
        shouldDisplayDoctorInfoCard = false;
        shouldDisplayPatientInfoCard = true;
        parent.setFeedbackToUser(true);
    }

    public boolean shouldDisplayDoctorInfoCard() {
        return shouldDisplayDoctorInfoCard;
    }

    public boolean shouldDisplayPatientInfoCard() {
        return shouldDisplayPatientInfoCard;
    }

}
