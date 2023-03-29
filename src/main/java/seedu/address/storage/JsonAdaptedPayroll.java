package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Payroll;

/**
 * Jackson-friendly version of {@link Payroll}.
 */
public class JsonAdaptedPayroll {
    private final String salaryStr;
    private final String dateOfPaymentStr;

    /**
     * Constructs a {@code JsonAdaptedPayroll} with the given {@code payroll}.
     */
    @JsonCreator
    public JsonAdaptedPayroll(String salaryStr, String dateOfPaymentStr) {
        this.salaryStr = salaryStr;
        this.dateOfPaymentStr = dateOfPaymentStr;
    }

    /**
     * Converts a given {@code Payroll} into this class for Jackson use.
     */
    public JsonAdaptedPayroll(Payroll source) {
        this.salaryStr = String.valueOf(source.getSalary());
        this.dateOfPaymentStr = String.valueOf(source.getDayOfPayment());
    }

    @JsonValue
    public String getSalaryStr() {
        return salaryStr;
    }

    @JsonValue
    public String getDateOfPaymentStr() {
        return dateOfPaymentStr;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Payroll} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Payroll toModelType() throws IllegalValueException {
        int salary = Integer.parseInt(this.salaryStr);
        int dateOfPayment = Integer.parseInt(this.dateOfPaymentStr);
        if (!Payroll.isValidPayroll(salaryStr + " " + dateOfPaymentStr)) {
            throw new IllegalValueException(Payroll.MESSAGE_CONSTRAINTS);
        }
        return new Payroll(salary, dateOfPayment);
    }
}
