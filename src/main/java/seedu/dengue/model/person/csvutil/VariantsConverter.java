package seedu.dengue.model.person.csvutil;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import seedu.dengue.logic.parser.ParserUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.variant.Variant;

/**
 * This class represents a converter for dengue variants in CSV data. It extends
 * AbstractBeanField to implement the required functionality for converting
 * CSV data to Java objects.
 */
public class VariantsConverter extends AbstractBeanField {

    /**
     * Converts a string value representing the variants to a Set of Variant objects.
     * @param value the string value representing the variants to convert
     * @return a Set of Variants object representing the converted variants
     * @throws CsvDataTypeMismatchException if the value cannot be converted to a Set of Variants
     * @throws CsvConstraintViolationException if the converted value violates any constraints
     */
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
