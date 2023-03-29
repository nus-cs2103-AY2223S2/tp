package seedu.dengue.model.person.csvutil;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.model.person.Postal;
import seedu.dengue.logic.parser.exceptions.ParseException;

public class PostalConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Postal postal = ParserUtil.parsePostal(value);
            return postal;
        } catch(ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}

