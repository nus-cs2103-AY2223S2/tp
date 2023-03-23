package seedu.dengue.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.dengue.commons.core.index.Index;
import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.variant.Variant;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String Postal} into a {@code Postal}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Postal} is invalid.
     */
    public static Postal parsePostal(String postal) throws ParseException {
        requireNonNull(postal);
        String trimmedPostal = postal.trim();
        if (!Postal.isValidPostal(trimmedPostal)) {
            throw new ParseException(Postal.MESSAGE_CONSTRAINTS);
        }

        return new Postal(trimmedPostal);
    }


    /**
     * Parses a {@code String age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }

        return new Age(trimmedAge);
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String variant} into a {@code Variant}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code variant} is invalid.
     */
    public static Variant parseVariant(String variant) throws ParseException {
        requireNonNull(variant);
        String trimmedVariant = variant.trim();
        if (!Variant.isValidVariantName(trimmedVariant)) {
            throw new ParseException(Variant.MESSAGE_CONSTRAINTS);
        }
        return new Variant(trimmedVariant);
    }

    /**
     * Parses {@code Collection<String> variants} into a {@code Set<Variant>}.
     */
    public static Set<Variant> parseVariants(Collection<String> variants) throws ParseException {
        requireNonNull(variants);
        final Set<Variant> variantSet = new HashSet<>();
        for (String variantName : variants) {
            variantSet.add(parseVariant(variantName));
        }
        return variantSet;
    }
}
