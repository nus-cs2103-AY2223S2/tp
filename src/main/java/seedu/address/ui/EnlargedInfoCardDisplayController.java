package seedu.address.ui;

/**
 * A controller to choose between displaying Doctor and Patient Info.
 */
public class EnlargedInfoCardDisplayController {

    private boolean displayDoctorInfoCard;
    private boolean displayPatientInfoCard;
    private ContactDisplay parent;

    public EnlargedInfoCardDisplayController(ContactDisplay parent) {
        // Show Doctor by default
        this.parent = parent;
    }

    public void displayDoctor() {
        displayDoctorInfoCard = true;
        displayPatientInfoCard = false;
        parent.setFeedbackToUser();
    }

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
