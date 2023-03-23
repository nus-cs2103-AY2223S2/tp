package seedu.dengue.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.variant.Variant;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_POSTAL = "+651234";
    private static final String INVALID_AGE = " ";
    private static final String INVALID_DATE = "example.com";
    private static final String INVALID_VARIANT = "#DENV1";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_POSTAL = "123456";
    private static final String VALID_AGE = "25";
    private static final String VALID_DATE = "2023-02-25";
    private static final String VALID_VARIANT_1 = "DENV1";
    private static final String VALID_VARIANT_2 = "DENV2";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePostal_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePostal((String) null));
    }

    @Test
    public void parsePostal_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePostal(INVALID_POSTAL));
    }

    @Test
    public void parsePostal_validValueWithoutWhitespace_returnsPostal() throws Exception {
        Postal expectedPostal = new Postal(VALID_POSTAL);
        assertEquals(expectedPostal, ParserUtil.parsePostal(VALID_POSTAL));
    }

    @Test
    public void parsePostal_validValueWithWhitespace_returnsTrimmedPostal() throws Exception {
        String postalWithWhitespace = WHITESPACE + VALID_POSTAL + WHITESPACE;
        Postal expectedPostal = new Postal(VALID_POSTAL);
        assertEquals(expectedPostal, ParserUtil.parsePostal(postalWithWhitespace));
    }

    @Test
    public void parseAge_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAge((String) null));
    }

    @Test
    public void parseAge_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAge(INVALID_AGE));
    }

    @Test
    public void parseAge_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, ParserUtil.parseAge(VALID_AGE));
    }

    @Test
    public void parseAge_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_AGE + WHITESPACE;
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, ParserUtil.parseAge(addressWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseVariant_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVariant(null));
    }

    @Test
    public void parseVariant_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVariant(INVALID_VARIANT));
    }

    @Test
    public void parseVariant_validValueWithoutWhitespace_returnsVariant() throws Exception {
        Variant expectedVariant = new Variant(VALID_VARIANT_1);
        assertEquals(expectedVariant, ParserUtil.parseVariant(VALID_VARIANT_1));
    }

    @Test
    public void parseVariant_validValueWithWhitespace_returnsTrimmedVariant() throws Exception {
        String variantWithWhitespace = WHITESPACE + VALID_VARIANT_1 + WHITESPACE;
        Variant expectedVariant = new Variant(VALID_VARIANT_1);
        assertEquals(expectedVariant, ParserUtil.parseVariant(variantWithWhitespace));
    }

    @Test
    public void parseVariants_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVariants(null));
    }

    @Test
    public void parseVariants_collectionWithInvalidVariants_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVariants(
                Arrays.asList(VALID_VARIANT_1, INVALID_VARIANT)));
    }

    @Test
    public void parseVariants_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseVariants(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseVariants_collectionWithValidVariants_returnsVariantSet() throws Exception {
        Set<Variant> actualVariantSet = ParserUtil.parseVariants(Arrays.asList(VALID_VARIANT_1, VALID_VARIANT_2));
        Set<Variant> expectedVariantSet = new HashSet<>(
                Arrays.asList(new Variant(VALID_VARIANT_1), new Variant(VALID_VARIANT_2)));

        assertEquals(expectedVariantSet, actualVariantSet);
    }
}
