package seedu.dengue.model.person.csvutil;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Name;

/**
 * This class represents a converter for names in CSV data. It extends
 * AbstractBeanField to implement the required functionality for converting
 * CSV data to Java objects.
 */
public class NameConverter extends AbstractBeanField {

    /**
     * Converts a string value representing a name to a Name object.
     * @param value the string value representing the name to convert
     * @return a Name object representing the converted name
     * @throws CsvDataTypeMismatchException if the value cannot be converted to a Name object
     * @throws CsvConstraintViolationException if the converted value violates any constraints
     */
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Name name = ParserUtil.parseName(value);
            return name;
        } catch (ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
