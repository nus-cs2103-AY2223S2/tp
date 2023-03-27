package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;

/**
 * Represent's a patient's current prescription. Which is the encapsulation of medication and cost
 */
public class Prescription {

    public final Medication medication;
    public final Cost cost;

    /**
     * Constructs a {@code Prescription}.
     *
     * @param medication of the prescription for the patient
     * @param cost of the prescription
     */
    public Prescription(Medication medication, Cost cost) {
        requireNonNull(medication);
        this.medication = medication;
        this.cost = cost;
    }

    public static final Prescription EMPTY_PRESCRIPTION = new Prescription(Medication.EMPTY_MEDICATION,
            Cost.EMPTY_COST);

    /**
     * @return true if its an empty prescription
     */
    public boolean isEmpty() {
        return this.medication.isEmpty() && this.cost.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("[$%s], %s", cost.toString(), medication.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Prescription)) {
            return false;
        }

        Prescription otherPrescription = (Prescription) other;
        return medication.equals(otherPrescription.medication)
                && cost.equals(otherPrescription.cost);
    }

}
