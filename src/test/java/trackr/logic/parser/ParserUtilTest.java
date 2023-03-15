package trackr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;
import trackr.model.supplier.Address;
import trackr.model.supplier.Email;
import trackr.model.supplier.Name;
import trackr.model.supplier.Phone;
import trackr.model.tag.Tag;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_TASK_NAME = "S@rt Inventory";
    private static final String INVALID_TASK_DEADLINE = "35/13/2023";
    private static final String INVALID_TASK_STATUS = "done";

    private static final String INVALID_ORDER_NAME = "@&*$&*COOKIES";
    private static final String INVALID_ORDER_DEADLINE = "35/13/2023";
    private static final String INVALID_ORDER_STATUS = "done";
    private static final String INVALID_ORDER_QUANTITY = "213123";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_TASK_NAME = "Sort Inventory";
    private static final String VALID_TASK_DEADLINE = "01/01/2024";
    private static final String VALID_TASK_STATUS = "D";

    private static final String VALID_ORDER_NAME = "Chocolate Cookies";
    private static final String VALID_ORDER_DEADLINE = "01/01/2024";
    private static final String VALID_ORDER_STATUS = "D";
    private static final String VALID_ORDER_QUANTITY = "12";

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
        assertEquals(INDEX_FIRST_OBJECT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_OBJECT, ParserUtil.parseIndex("  1  "));
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

    //=====================Test parser util methods that are related to task==================
    @Test
    public void parseTaskName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskName((String) null));
    }

    @Test
    public void parseTaskName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskName(INVALID_TASK_NAME));
    }

    @Test
    public void parseTaskName_validValueWithoutWhitespace_returnsTaskName() throws Exception {
        TaskName expectedTaskName = new TaskName(VALID_TASK_NAME);
        assertEquals(expectedTaskName, ParserUtil.parseTaskName(VALID_TASK_NAME));
    }

    @Test
    public void parseTaskName_validValueWithWhitespace_returnsTrimmedTaskName() throws Exception {
        String taskNameWithWhitespace = WHITESPACE + VALID_TASK_NAME + WHITESPACE;
        TaskName expectedTaskName = new TaskName(VALID_TASK_NAME);
        assertEquals(expectedTaskName, ParserUtil.parseTaskName(taskNameWithWhitespace));
    }

    @Test
    public void parseTaskDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskDeadline((String) null));
    }

    @Test
    public void parseTaskDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskDeadline(INVALID_TASK_DEADLINE));
    }

    @Test
    public void parseTaskDeadline_validValueWithoutWhitespace_returnsTaskDeadline() throws Exception {
        TaskDeadline expectedTaskDeadline = new TaskDeadline(VALID_TASK_DEADLINE);
        assertEquals(expectedTaskDeadline, ParserUtil.parseTaskDeadline(VALID_TASK_DEADLINE));
    }

    @Test
    public void parseTaskDeadline_validValueWithWhitespace_returnsTrimmedTaskDeadline() throws Exception {
        String taskDeadlineWithWhitespace = WHITESPACE + VALID_TASK_DEADLINE + WHITESPACE;
        TaskDeadline expectedTaskDeadline = new TaskDeadline(VALID_TASK_DEADLINE);
        assertEquals(expectedTaskDeadline, ParserUtil.parseTaskDeadline(taskDeadlineWithWhitespace));
    }

    @Test
    public void parseTaskStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskStatus((Optional<String>) null));
    }

    @Test
    public void parseTaskStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskStatus(Optional.ofNullable(INVALID_TASK_STATUS)));
    }

    @Test
    public void parseTaskStatus_validValueWithoutWhitespace_returnsTaskStatus() throws Exception {
        TaskStatus expectedTaskStatus = new TaskStatus(VALID_TASK_STATUS);
        assertEquals(expectedTaskStatus, ParserUtil.parseTaskStatus(Optional.ofNullable(VALID_TASK_STATUS)));
    }

    @Test
    public void parseTaskStatus_validValueWithWhitespace_returnsTrimmedTaskStatus() throws Exception {
        Optional<String> taskStatusWithWhitespace = Optional.of(WHITESPACE + VALID_TASK_STATUS + WHITESPACE);
        TaskStatus expectedTaskStatus = new TaskStatus(VALID_TASK_STATUS);
        assertEquals(expectedTaskStatus, ParserUtil.parseTaskStatus(taskStatusWithWhitespace));
    }

    //=====================Test parser util methods that are related to order==================
    @Test
    public void parseOrderName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderName((String) null));
    }

    @Test
    public void parseOrderName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderName(INVALID_ORDER_NAME));
    }

    @Test
    public void parseOrderName_validValueWithoutWhitespace_returnsOrderName() throws Exception {
        OrderName expectedOrderName = new OrderName(VALID_ORDER_NAME);
        assertEquals(expectedOrderName, ParserUtil.parseOrderName(VALID_ORDER_NAME));
    }

    @Test
    public void parseOrderName_validValueWithWhitespace_returnsTrimmedOrderName() throws Exception {
        String orderNameWithWhitespace = WHITESPACE + VALID_ORDER_NAME + WHITESPACE;
        TaskName expectedOrderName = new TaskName(VALID_ORDER_NAME);
        assertEquals(expectedOrderName, ParserUtil.parseTaskName(orderNameWithWhitespace));
    }

    @Test
    public void parseOrderDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderDeadline((String) null));
    }

    @Test
    public void parseOrderDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderDeadline(INVALID_ORDER_DEADLINE));
    }

    @Test
    public void parseOrderDeadline_validValueWithoutWhitespace_returnsOrderDeadline() throws Exception {
        OrderDeadline expectedTaskDeadline = new OrderDeadline(VALID_ORDER_DEADLINE);
        assertEquals(expectedTaskDeadline, ParserUtil.parseOrderDeadline(VALID_ORDER_DEADLINE));
    }

    @Test
    public void parseOrderDeadline_validValueWithWhitespace_returnsTrimmedOrderDeadline() throws Exception {
        String taskDeadlineWithWhitespace = WHITESPACE + VALID_TASK_DEADLINE + WHITESPACE;
        OrderDeadline expectedTaskDeadline = new OrderDeadline(VALID_TASK_DEADLINE);
        assertEquals(expectedTaskDeadline, ParserUtil.parseOrderDeadline(taskDeadlineWithWhitespace));
    }

    @Test
    public void parseOrderStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderStatus((Optional<String>) null));
    }

    @Test
    public void parseOrderStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderStatus(Optional.ofNullable(INVALID_ORDER_STATUS)));
    }

    @Test
    public void parseOrderStatus_validValueWithoutWhitespace_returnsTaskStatus() throws Exception {
        OrderStatus expectedOrderStatus = new OrderStatus(VALID_ORDER_STATUS);
        assertEquals(expectedOrderStatus, ParserUtil.parseOrderStatus(Optional.ofNullable(VALID_ORDER_STATUS)));
    }

    @Test
    public void parseOrderStatus_validValueWithWhitespace_returnsTrimmedOrderStatus() throws Exception {
        Optional<String> orderStatusWithWhitespace = Optional.of(WHITESPACE + VALID_ORDER_STATUS + WHITESPACE);
        OrderStatus expectedTaskStatus = new OrderStatus(VALID_ORDER_STATUS);
        assertEquals(expectedTaskStatus, ParserUtil.parseOrderStatus(orderStatusWithWhitespace));
    }

    @Test
    public void parseOrderQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOrderQuantity(null));
    }

    @Test
    public void parseOrderQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOrderQuantity(INVALID_ORDER_QUANTITY));
    }

    @Test
    public void parseOrderQuantity_validValueWithoutWhitespace_returnsTaskStatus() throws Exception {
        OrderQuantity expectedOrderStatus = new OrderQuantity(VALID_ORDER_QUANTITY);
        assertEquals(expectedOrderStatus, ParserUtil.parseOrderQuantity(VALID_ORDER_QUANTITY));
    }

    @Test
    public void parseOrderQuantity_validValueWithWhitespace_returnsTrimmedOrderStatus() throws Exception {
        String orderStatusWithWhitespace = WHITESPACE + VALID_ORDER_QUANTITY + WHITESPACE;
        OrderQuantity expectedTaskStatus = new OrderQuantity(VALID_ORDER_QUANTITY);
        assertEquals(expectedTaskStatus, ParserUtil.parseOrderQuantity(orderStatusWithWhitespace));
    }

    @Test
    public void parseCustomerName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCustomerName(null));
    }

    @Test
    public void parseCustomerName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCustomerName(INVALID_NAME));
    }

    @Test
    public void parseCustomerName_validValueWithoutWhitespace_returnsCustomerName() throws Exception {
        CustomerName expectedOrderStatus = new CustomerName(VALID_NAME);
        assertEquals(expectedOrderStatus, ParserUtil.parseCustomerName(VALID_NAME));
    }

    @Test
    public void parseCustomerName_validValueWithWhitespace_returnsTrimmedOrderStatus() throws Exception {
        String orderStatusWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        CustomerName expectedTaskStatus = new CustomerName(VALID_NAME);
        assertEquals(expectedTaskStatus, ParserUtil.parseCustomerName(orderStatusWithWhitespace));
    }

    @Test
    public void parseCustomerPhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCustomerPhone(null));
    }

    @Test
    public void parseCustomerPhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCustomerPhone(INVALID_PHONE));
    }

    @Test
    public void parseCustomerPhone_validValueWithoutWhitespace_returnsCustomerPhone() throws Exception {
        CustomerPhone expectedOrderStatus = new CustomerPhone(VALID_PHONE);
        assertEquals(expectedOrderStatus, ParserUtil.parseCustomerPhone(VALID_PHONE));
    }

    @Test
    public void parseCustomerPhone_validValueWithWhitespace_returnsTrimmedOrderStatus() throws Exception {
        String orderStatusWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        CustomerPhone expectedTaskStatus = new CustomerPhone(VALID_PHONE);
        assertEquals(expectedTaskStatus, ParserUtil.parseCustomerPhone(orderStatusWithWhitespace));
    }

    @Test
    public void parseCustomerAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCustomerAddress(null));
    }

    @Test
    public void parseCustomerAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCustomerAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseCustomerAddress_validValueWithoutWhitespace_returnsCustomerAddress() throws Exception {
        CustomerAddress expectedOrderStatus = new CustomerAddress(VALID_ADDRESS);
        assertEquals(expectedOrderStatus, ParserUtil.parseCustomerAddress(VALID_ADDRESS));
    }

    @Test
    public void parseCustomerAddress_validValueWithWhitespace_returnsTrimmedOrderStatus() throws Exception {
        String orderStatusWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        CustomerAddress expectedTaskStatus = new CustomerAddress(VALID_ADDRESS);
        assertEquals(expectedTaskStatus, ParserUtil.parseCustomerAddress(orderStatusWithWhitespace));
    }
}
