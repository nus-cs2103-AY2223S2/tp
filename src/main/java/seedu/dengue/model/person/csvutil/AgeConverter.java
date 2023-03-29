package seedu.dengue.model.person.csvutil;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;

/**
 * This class represents a converter for ages in CSV data. It extends
 * AbstractBeanField to implement the required functionality for converting
 * CSV data to Java objects.
 */
public class AgeConverter extends AbstractBeanField {

    /**
     * Converts a string value representing an age to an Age object.
     * @param value the string value representing the age to convert
     * @return an Age object representing the converted age
     * @throws CsvDataTypeMismatchException if the value cannot be converted to an Age object
     * @throws CsvConstraintViolationException if the converted value violates any constraints
     */
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Age age = ParserUtil.parseAge(value);
            return age;
        } catch (ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
