package seedu.dengue.model.person.csvutil;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Postal;

/**
 * This class represents a converter for postal codes in CSV data. It extends
 * AbstractBeanField to implement the required functionality for converting
 * CSV data to Java objects.
 */
public class PostalConverter extends AbstractBeanField {

    /**
     * Converts a string value representing a postal code to a Postal object.
     * @param value the string value representing the postal code to convert
     * @return a Postal object representing the converted postal code
     * @throws CsvDataTypeMismatchException if the value cannot be converted to a Postal object
     * @throws CsvConstraintViolationException if the converted value violates any constraints
     */
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Postal postal = ParserUtil.parsePostal(value);
            return postal;
        } catch (ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
