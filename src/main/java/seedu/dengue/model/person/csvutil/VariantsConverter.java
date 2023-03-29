package seedu.dengue.model.person.csvutil;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.variant.Variant;

public class VariantsConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value.equals("[]")) {
            return new HashSet<Variant>();
        }
        String[] v = value.replaceAll("\\[|\\]", "").split(", ");
        Collection<String> variants = Arrays.asList(v);
        if (!variants.isEmpty()) {
            try {
                Set<Variant> variantSet = ParserUtil.parseVariants(variants);
                return variantSet;
            } catch (ParseException e) {
                throw new CsvDataTypeMismatchException(e.getMessage());
            }
        } else {
            return new HashSet<Variant>();
        }
    }
}
