package seedu.dengue.model.person.csvutils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.model.person.Date;
import seedu.dengue.logic.parser.exceptions.ParseException;

public class dateConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            Date date = ParserUtil.parseDate(value);
            return date;
        } catch(ParseException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}

