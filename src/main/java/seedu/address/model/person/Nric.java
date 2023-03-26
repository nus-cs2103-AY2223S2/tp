package seedu.address.model.person;

/**
 * To represents the patient's identity
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS =
            "Person's age should be in this format SXXXXXXXA";
    public final String number;
    public Nric(String number) {
        this.number = number;
    }

    /**
     * @param obj the NRIC number
     * @return boolean, true if is a valid NRIC number
     */
    public boolean isValidNumber(Nric obj) {
        if (obj == this) { // short circuit if same object
            return true;
        }
        if (!(obj instanceof Nric)) { // instanceof handles nulls and other classes
            return false;
        }

        if (((Nric) obj).number.equals(this.number)) {
            return true;
        } else if (!(obj.number.indexOf(0) == 'S' || obj.number.indexOf(0) == 'T')) {
            if (obj.number.length() == 9) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public String getNumber() {
        return this.number;
    }
    @Override
    public String toString() {
        return "NRIC: " + number;
    }
}
