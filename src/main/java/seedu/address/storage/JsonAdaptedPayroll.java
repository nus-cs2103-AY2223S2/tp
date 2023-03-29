package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Payroll;

/**
 * Jackson-friendly version of {@link Payroll}.
 */
public class JsonAdaptedPayroll {
    private final String payroll;

    /**
     * Constructs a {@code JsonAdaptedPayroll} with the given {@code payroll}.
     */
    @JsonCreator
    public JsonAdaptedPayroll(String payroll) {
        this.payroll = payroll;
    }

    /**
     * Converts a given {@code Payroll} into this class for Jackson use.
     */
    public JsonAdaptedPayroll(Payroll source) {
        this.payroll = String.valueOf(source.getSalary()) + " " + String.valueOf(source.getDayOfPayment());
    }

    @JsonValue
    public String getPayroll() {
        return payroll;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Payroll} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Payroll toModelType() throws IllegalValueException {
        if (!Payroll.isValidPayroll(payroll)) {
            throw new IllegalValueException(Payroll.MESSAGE_CONSTRAINTS);
        }
        return new Payroll(payroll);
    }
}
