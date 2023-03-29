package seedu.dengue.model.person.csvutil;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.model.person.Name;
import seedu.dengue.logic.parser.exceptions.ParseException;

public class NameConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Name name = ParserUtil.parseName(value);
            return name;
        } catch(ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}