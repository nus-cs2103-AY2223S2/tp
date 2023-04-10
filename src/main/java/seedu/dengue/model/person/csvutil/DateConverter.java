package seedu.dengue.model.person.csvutil;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Date;

/**
 * This class represents a converter for dates in CSV data. It extends
 * AbstractBeanField to implement the required functionality for converting
 * CSV data to Java objects.
 */
public class DateConverter extends AbstractBeanField {

    /**
     * Converts a string value representing a date to a Date object.
     * @param value the string value representing the date to convert
     * @return a Date object representing the converted date
     * @throws CsvDataTypeMismatchException if the value cannot be converted to a Date object
     * @throws CsvConstraintViolationException if the converted value violates any constraints
     */
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Date date = ParserUtil.parseDate(value);
            return date;
        } catch (ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
