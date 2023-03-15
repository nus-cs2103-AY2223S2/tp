package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_POLICY_NAME = " ";
    private static final String INVALID_POLICY_DATE = "05-10-2023";
    private static final String INVALID_POLICY_PREMIUM = "$100";
    private static final String INVALID_POLICY_PREMIUM_WITH_4_DECIMAL = "1000.00000";
    private static final String INVALID_POLICY_FREQUENCY = "everyday";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_POLICY_NAME = "Life Insurance";
    private static final String VALID_POLICY_DATE = "05.10.2023";
    private static final String VALID_POLICY_PREMIUM = "1000.00";
    private static final String VALID_POLICY_PREMIUM_WITHOUT_DECIMAL = "1000";
    private static final String VALID_POLICY_FREQUENCY = "monthly";

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
        assertEquals(INDEX_FIRST_CLIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CLIENT, ParserUtil.parseIndex("  1  "));
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
    void parsePolicyName_validValueWithoutWhitespace_returnsPolicyName() throws Exception {
        PolicyName expectedPolicyName = new PolicyName(VALID_POLICY_NAME);
        assertEquals(expectedPolicyName, ParserUtil.parsePolicyName(VALID_POLICY_NAME));
    }

    @Test
    void parsePolicyName_validValueWithWhitespace_returnsTrimmedPolicyName() throws Exception {
        PolicyName expectedPolicyName = new PolicyName(VALID_POLICY_NAME);
        assertEquals(expectedPolicyName, ParserUtil.parsePolicyName(WHITESPACE + VALID_POLICY_NAME + WHITESPACE));
    }

    @Test
    void parseCustomDate_validValueWithoutWhitespace_returnsCustomDate() throws Exception {
        CustomDate expectedCustomDate = new CustomDate(VALID_POLICY_DATE);
        assertEquals(expectedCustomDate, ParserUtil.parseCustomDate(VALID_POLICY_DATE));
    }
    @Test
    void parseCustomDate_validValueWithWhitespace_returnsTrimmedCustomDate() throws Exception {
        CustomDate expectedCustomDate = new CustomDate(VALID_POLICY_DATE);
        assertEquals(expectedCustomDate, ParserUtil.parseCustomDate(WHITESPACE + VALID_POLICY_DATE + WHITESPACE));
    }
    @Test
    void parseCustomDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCustomDate(INVALID_POLICY_DATE));
    }
    @Test
    void parsePremium_validValueWithoutWhitespace_returnsPremium() throws Exception {
        Premium expectedPremium = new Premium(VALID_POLICY_PREMIUM);
        assertEquals(expectedPremium, ParserUtil.parsePremium(VALID_POLICY_PREMIUM));
    }
    @Test
    void parsePremium_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePremium(INVALID_POLICY_PREMIUM));
    }
    @Test
    void parsePremium_validValueWithWhitespace_returnsTrimmedPremium() throws Exception {
        Premium expectedPremium = new Premium(VALID_POLICY_PREMIUM);
        assertEquals(expectedPremium, ParserUtil.parsePremium(WHITESPACE + VALID_POLICY_PREMIUM + WHITESPACE));
    }

    @Test
    void parseFrequency_validValueWithoutWhitespace_returnsFrequency() throws Exception {
        Frequency expectedFrequency = new Frequency(VALID_POLICY_FREQUENCY);
        assertEquals(expectedFrequency, ParserUtil.parseFrequency(VALID_POLICY_FREQUENCY));
    }
    @Test
    void parseFrequency_validValueWithWhitespace_returnsTrimmedFrequency() throws Exception {
        Frequency expectedFrequency = new Frequency(VALID_POLICY_FREQUENCY);
        assertEquals(expectedFrequency, ParserUtil.parseFrequency(WHITESPACE + VALID_POLICY_FREQUENCY + WHITESPACE));
    }
    @Test
    void parseFrequency_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFrequency(INVALID_POLICY_FREQUENCY));
    }
}
