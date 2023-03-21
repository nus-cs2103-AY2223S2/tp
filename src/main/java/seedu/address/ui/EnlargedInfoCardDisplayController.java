package seedu.address.ui;

/**
 * A controller to choose between displaying {@code EnlargedDoctorInfoCard}
 * and {@code EnlargedPatientInfoCard} on {@code ContactDisplay}.
 */
public class EnlargedInfoCardDisplayController {

    private boolean displayDoctorInfoCard;
    private boolean displayPatientInfoCard;
    private ContactDisplay parent;

    /**
     * Creates a {@code EnlargedInfoCardDisplayController}
     * with the given parent {@code ContactDisplay}.
     */
    public EnlargedInfoCardDisplayController(ContactDisplay parent) {
        // Show Doctor by default
        this.parent = parent;
    }

    /**
     * Updates controller state to display {@code EnlargedDoctorInfoCard}.
     */
    public void displayDoctor() {
        displayDoctorInfoCard = true;
        displayPatientInfoCard = false;
        parent.setFeedbackToUser();
    }

    /**
     * Updates controller state to display {@code EnlargedPatientInfoCard}.
     */
    public void displayPatient() {
        displayDoctorInfoCard = false;
        displayPatientInfoCard = true;
        parent.setFeedbackToUser();
    }

    public boolean getDisplayDoctorInfoCard() {
        return displayDoctorInfoCard;
    }

    public boolean getDisplayPatientInfoCard() {
        return displayPatientInfoCard;
    }

}
