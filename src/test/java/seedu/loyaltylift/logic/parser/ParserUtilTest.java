package seedu.loyaltylift.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.tag.Tag;

public class ParserUtilTest {

    private static final String INVALID_QUANTITY_1 = "0";
    private static final String INVALID_QUANTITY_2 = "1000001";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CUSTOMER_TYPE = "person";
    private static final String INVALID_CUSTOMER_POINTS = "-1";
    private static final String INVALID_ADD_POINTS = "1000000";
    private static final String INVALID_SORT_OPTION = "invalid";

    private static final String VALID_QUANTITY_1 = "1";
    private static final String VALID_QUANTITY_2 = "1000000";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_CUSTOMER_TYPE_IND = "ind";
    private static final String VALID_CUSTOMER_TYPE_ENT = "ent";
    private static final String VALID_CUSTOMER_POINTS = "100";
    private static final String VALID_ADD_POINTS = "-500";
    private static final String VALID_SORT_OPTION_NAME = "name";
    private static final String VALID_SORT_OPTION_POINTS = "points";
    private static final String VALID_SORT_OPTION_CREATED_DATE = "created";
    private static final String VALID_SORT_OPTION_STATUS = "status";

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
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseQuantity_validWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity1 = new Quantity(Integer.parseInt(VALID_QUANTITY_1));
        assertEquals(expectedQuantity1, ParserUtil.parseQuantity(VALID_QUANTITY_1));

        Quantity expectedQuantity2 = new Quantity(Integer.parseInt(VALID_QUANTITY_2));
        assertEquals(expectedQuantity2, ParserUtil.parseQuantity(VALID_QUANTITY_2));
    }

    @Test
    public void parseQuantity_validWithWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity1 = new Quantity(Integer.parseInt(VALID_QUANTITY_1));
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY_1 + WHITESPACE;
        assertEquals(expectedQuantity1, ParserUtil.parseQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity((String) null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class,
                Quantity.MESSAGE_CONSTRAINTS, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_1));

        assertThrows(ParseException.class,
                Quantity.MESSAGE_CONSTRAINTS, () -> ParserUtil.parseQuantity(INVALID_QUANTITY_2));
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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseCustomerType_validCustomerType() throws Exception {
        assertEquals(ParserUtil.parseCustomerType(VALID_CUSTOMER_TYPE_IND), CustomerType.INDIVIDUAL);

        assertEquals(ParserUtil.parseCustomerType(VALID_CUSTOMER_TYPE_ENT), CustomerType.ENTERPRISE);
    }

    @Test
    public void parseCustomerType_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCustomerType(null));
    }

    @Test
    public void parseCustomerType_invalidCustomerType_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseCustomerType(INVALID_CUSTOMER_TYPE));
    }

    @Test
    public void parsePoints_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePoints((String) null));
    }

    @Test
    public void parsePoints_invalidPoints_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePoints(INVALID_CUSTOMER_POINTS));
    }

    @Test
    public void parsePoints_validPoints() throws Exception {
        Integer validPoints = Integer.valueOf(VALID_CUSTOMER_POINTS);
        Points expectedPoints = new Points(validPoints, validPoints);
        assertEquals(expectedPoints, ParserUtil.parsePoints(VALID_CUSTOMER_POINTS));
    }

    @Test
    public void parseAddPoints_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddPoints((String) null));
    }

    @Test
    public void parseAddPoints_invalidAddPoints_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePoints(INVALID_ADD_POINTS));
    }

    @Test
    public void parseAddPoints_validAddPoints() throws Exception {
        Integer validAddPoints = Integer.valueOf(VALID_ADD_POINTS);
        assertEquals(validAddPoints, ParserUtil.parseAddPoints(VALID_ADD_POINTS));
    }

    @Test
    public void parseCustomerSortOption_validSortOption() throws Exception {
        assertEquals(ParserUtil.parseCustomerSortOption(VALID_SORT_OPTION_NAME), Customer.SORT_NAME);
        assertEquals(ParserUtil.parseCustomerSortOption(VALID_SORT_OPTION_POINTS), Customer.SORT_POINTS);
    }

    @Test
    public void parseCustomerSortOption_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCustomerSortOption(null));
    }

    @Test
    public void parseCustomerSortOption_invalidSortOption_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseCustomerSortOption(INVALID_SORT_OPTION));
    }

    @Test
    public void parseOrderSortOption_validSortOption() throws Exception {
        assertEquals(ParserUtil.parseOrderSortOption(VALID_SORT_OPTION_CREATED_DATE), Order.SORT_CREATED_DATE);
        assertEquals(ParserUtil.parseOrderSortOption(VALID_SORT_OPTION_NAME), Order.SORT_NAME);
        assertEquals(ParserUtil.parseOrderSortOption(VALID_SORT_OPTION_STATUS), Order.SORT_STATUS);
    }

    @Test
    public void parseOrderSortOption_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderSortOption(null));
    }

    @Test
    public void parseOrderSortOption_invalidSortOption_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderSortOption(INVALID_SORT_OPTION));
    }
}
