//package trackr.logic.parser;
//
//import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static trackr.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
//import static trackr.logic.commands.CommandTestUtil.CUSTOMER_ADDRESS_DESC;
//import static trackr.logic.commands.CommandTestUtil.CUSTOMER_NAME_DESC_NIGEL;
//import static trackr.logic.commands.CommandTestUtil.CUSTOMER_PHONE_DESC;
//import static trackr.logic.commands.CommandTestUtil.DESC_AMY;
//import static trackr.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
//import static trackr.logic.commands.CommandTestUtil.INVALID_CUSTOMER_NAME_DESC;
//import static trackr.logic.commands.CommandTestUtil.INVALID_CUSTOMER_PHONE_DESC;
//import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_DEADLINE_DESC;
//import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_NAME_DESC;
//import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_QUANTITY_DESC;
//import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_STATUS_DESC;
//import static trackr.logic.commands.CommandTestUtil.ORDER_DEADLINE_DESC_2023;
//import static trackr.logic.commands.CommandTestUtil.ORDER_DEADLINE_DESC_2024;
//import static trackr.logic.commands.CommandTestUtil.ORDER_NAME_DESC_CHOCO_COOKIE;
//import static trackr.logic.commands.CommandTestUtil.ORDER_NAME_DESC_CUPCAKES;
//import static trackr.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_ONE;
//import static trackr.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_TWO;
//import static trackr.logic.commands.CommandTestUtil.ORDER_STATUS_DESC_DONE;
//import static trackr.logic.commands.CommandTestUtil.ORDER_STATUS_DESC_NOT_DONE;
//import static trackr.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
//import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_ADDRESS;
//import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_NAME;
//import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_PHONE;
//import static trackr.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2023;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2024;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CUPCAKES;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_ONE;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_TWO;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
//import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
//import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
//import static trackr.logic.parser.CliSyntax.PREFIX_TAG;
//import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
//import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
//import static trackr.testutil.TypicalIndexes.INDEX_THIRD_OBJECT;
//
//import org.junit.jupiter.api.Test;
//
//import trackr.commons.core.index.Index;
//import trackr.logic.commands.EditOrderCommand;
//import trackr.model.order.OrderDeadline;
//import trackr.model.order.OrderDescriptor;
//import trackr.model.order.OrderName;
//import trackr.model.order.OrderQuantity;
//import trackr.model.order.OrderStatus;
//import trackr.model.order.customer.CustomerAddress;
//import trackr.model.order.customer.CustomerName;
//import trackr.model.order.customer.CustomerPhone;
//import trackr.testutil.OrderDescriptorBuilder;
//
//
//public class EditOrderCommandParserTest {
//
//    private static final String TAG_EMPTY = " " + PREFIX_TAG;
//
//    private static final String MESSAGE_INVALID_FORMAT =
//            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE);
//
//    private EditOrderCommandParser parser = new EditOrderCommandParser();
//
//    @Test
//    public void parse_missingParts_failure() {
//        // no index specified
//        assertParseFailure(parser, VALID_ORDER_NAME_CHOCOLATE_COOKIES, MESSAGE_INVALID_FORMAT);
//
//        // no field specified
//        assertParseFailure(parser, "1", EditOrderCommand.MESSAGE_NOT_EDITED);
//
//        // no index and no field specified
//        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidPreamble_failure() {
//        // negative index
//        assertParseFailure(parser, "-5" + ORDER_NAME_DESC_CHOCO_COOKIE, MESSAGE_INVALID_FORMAT);
//
//        // zero index
//        assertParseFailure(parser, "0" + ORDER_NAME_DESC_CHOCO_COOKIE, MESSAGE_INVALID_FORMAT);
//
//        // invalid arguments being parsed as preamble
//        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
//
//        // invalid prefix being parsed as preamble
//        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        assertParseFailure(parser, "1" + INVALID_ORDER_NAME_DESC,
//                OrderName.MESSAGE_CONSTRAINTS); // invalid Order name
//        assertParseFailure(parser, "1" + INVALID_ORDER_DEADLINE_DESC,
//                OrderDeadline.MESSAGE_CONSTRAINTS); // invalid Order deadline
//        assertParseFailure(parser, "1" + INVALID_ORDER_STATUS_DESC,
//                OrderStatus.MESSAGE_CONSTRAINTS); // invalid Order status
//        assertParseFailure(parser, "1" + INVALID_ORDER_QUANTITY_DESC,
//                OrderQuantity.MESSAGE_CONSTRAINTS); // invalid Order quantity
//        assertParseFailure(parser, "1" + INVALID_CUSTOMER_NAME_DESC,
//                CustomerName.MESSAGE_CONSTRAINTS); // invalid name
//        assertParseFailure(parser, "1" + INVALID_CUSTOMER_PHONE_DESC,
//                CustomerPhone.MESSAGE_CONSTRAINTS); // invalid phone
//        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC,
//                CustomerAddress.MESSAGE_CONSTRAINTS); // invalid address
//
//        // invalid Order name followed by valid Order deadline
//        assertParseFailure(parser, "1" + INVALID_ORDER_NAME_DESC
//                + VALID_ORDER_DEADLINE_2024, OrderName.MESSAGE_CONSTRAINTS);
//
//        // valid value followed by invalid value.
//        // The test case for invalid value followed by valid value
//        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
//        assertParseFailure(parser, "1" + ORDER_NAME_DESC_CHOCO_COOKIE + INVALID_ORDER_NAME_DESC,
//                OrderName.MESSAGE_CONSTRAINTS); //Order name
//
//        assertParseFailure(parser, "1" + ORDER_DEADLINE_DESC_2024 + INVALID_ORDER_DEADLINE_DESC,
//                OrderDeadline.MESSAGE_CONSTRAINTS); //Order deadline
//
//        assertParseFailure(parser, "1" + ORDER_STATUS_DESC_NOT_DONE + INVALID_ORDER_STATUS_DESC,
//                OrderStatus.MESSAGE_CONSTRAINTS); //Order status
//
//        assertParseFailure(parser, "1" + ORDER_QUANTITY_DESC_TWO + INVALID_ORDER_QUANTITY_DESC,
//                OrderQuantity.MESSAGE_CONSTRAINTS); //Order quantity
//
//        // multiple invalid values, but only the first invalid value is captured
//        assertParseFailure(parser, "1" + INVALID_ORDER_NAME_DESC
//                + INVALID_ORDER_DEADLINE_DESC + VALID_ORDER_STATUS_DONE, OrderName.MESSAGE_CONSTRAINTS);
//    }
//
//    @Test
//    public void parse_allFieldsSpecified_success() {
//        Index targetIndex = INDEX_SECOND_OBJECT;
//        String userInput = targetIndex.getOneBased() + ORDER_NAME_DESC_CHOCO_COOKIE
//                + ORDER_DEADLINE_DESC_2024 + ORDER_STATUS_DESC_NOT_DONE
//                + ORDER_QUANTITY_DESC_ONE + CUSTOMER_NAME_DESC_NIGEL
//                + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC;
//
//        OrderDescriptor descriptor =
//                new OrderDescriptorBuilder()
//                        .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
//                        .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
//                        .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
//                        .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
//                        .withCustomerName(VALID_CUSTOMER_NAME)
//                        .withCustomerPhone(VALID_CUSTOMER_PHONE)
//                        .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
//        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_someFieldsSpecified_success() {
//        //name and desc
//        Index targetIndex = INDEX_FIRST_OBJECT;
//        String userInput = targetIndex.getOneBased()
//                + ORDER_NAME_DESC_CHOCO_COOKIE + ORDER_DEADLINE_DESC_2024;
//
//        OrderDescriptor descriptor =
//                new OrderDescriptorBuilder()
//                        .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
//                        .withOrderDeadline(VALID_ORDER_DEADLINE_2024).build();
//        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        //name and status
//        userInput = targetIndex.getOneBased() + ORDER_NAME_DESC_CHOCO_COOKIE
//                + ORDER_STATUS_DESC_NOT_DONE;
//        descriptor = new OrderDescriptorBuilder().withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
//                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_oneFieldSpecified_success() {
//        // Order name
//        Index targetIndex = INDEX_THIRD_OBJECT;
//        String userInput = targetIndex.getOneBased() + ORDER_NAME_DESC_CHOCO_COOKIE;
//        OrderDescriptor descriptor = new OrderDescriptorBuilder()
//                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES).build();
//        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // Order deadline
//        userInput = targetIndex.getOneBased() + ORDER_DEADLINE_DESC_2024;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderDeadline(VALID_ORDER_DEADLINE_2024).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // Order status
//        userInput = targetIndex.getOneBased() + ORDER_STATUS_DESC_NOT_DONE;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // Order quantity
//        userInput = targetIndex.getOneBased() + ORDER_QUANTITY_DESC_TWO;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderQuantity(VALID_ORDER_QUANTITY_TWO).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // Customer Name
//        userInput = targetIndex.getOneBased() + CUSTOMER_NAME_DESC_NIGEL;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderStatus(VALID_CUSTOMER_NAME).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // Customer Phone
//        userInput = targetIndex.getOneBased() + CUSTOMER_PHONE_DESC;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderStatus(VALID_CUSTOMER_PHONE).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // Customer Address
//        userInput = targetIndex.getOneBased() + CUSTOMER_ADDRESS_DESC;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderStatus(VALID_CUSTOMER_ADDRESS).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_multipleRepeatedFields_acceptsLast() {
//        Index targetIndex = INDEX_FIRST_OBJECT;
//        String userInput = targetIndex.getOneBased() + ORDER_NAME_DESC_CHOCO_COOKIE
//                + ORDER_DEADLINE_DESC_2024 + ORDER_STATUS_DESC_NOT_DONE
//                + ORDER_QUANTITY_DESC_ONE + CUSTOMER_NAME_DESC_NIGEL
//                + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC
//                + ORDER_NAME_DESC_CHOCO_COOKIE + ORDER_DEADLINE_DESC_2024
//                + ORDER_STATUS_DESC_NOT_DONE + ORDER_QUANTITY_DESC_ONE
//                + CUSTOMER_NAME_DESC_NIGEL + CUSTOMER_PHONE_DESC
//                + CUSTOMER_ADDRESS_DESC + ORDER_NAME_DESC_CUPCAKES
//                + ORDER_DEADLINE_DESC_2023 + ORDER_STATUS_DESC_DONE
//                + ORDER_QUANTITY_DESC_TWO + DESC_AMY
//                + PHONE_DESC_AMY + ADDRESS_DESC_AMY;
//        System.out.println(userInput);
//
//        OrderDescriptor descriptor =
//                new OrderDescriptorBuilder()
//                        .withOrderName(VALID_ORDER_NAME_CUPCAKES)
//                        .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
//                        .withOrderStatus(VALID_ORDER_STATUS_DONE)
//                        .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
//                        .withCustomerName(VALID_NAME_AMY)
//                        .withCustomerPhone(VALID_PHONE_AMY)
//                        .withCustomerAddress(VALID_ADDRESS_AMY).build();
//        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        System.out.println(expectedCommand);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_invalidValueFollowedByValidValue_success() {
//        //no other valid values specified
//        // invalid Order name followed by valid Order name
//        Index targetIndex = INDEX_FIRST_OBJECT;
//        String userInput = targetIndex.getOneBased() + INVALID_ORDER_NAME_DESC + ORDER_NAME_DESC_CUPCAKES;
//        OrderDescriptor descriptor =
//                new OrderDescriptorBuilder().withOrderName(VALID_ORDER_NAME_CUPCAKES).build();
//        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // invalid Order deadline followed by valid Order deadline
//        userInput = targetIndex.getOneBased() + INVALID_ORDER_DEADLINE_DESC + VALID_ORDER_DEADLINE_2023;
//        descriptor = new OrderDescriptorBuilder().withOrderDeadline(VALID_ORDER_DEADLINE_2023).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // invalid Order status followed by valid Order status
//        userInput = targetIndex.getOneBased() + INVALID_ORDER_STATUS_DESC + ORDER_STATUS_DESC_NOT_DONE;
//        descriptor = new OrderDescriptorBuilder().withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // invalid Order quantity followed by valid Order quantity
//        userInput = targetIndex.getOneBased() + INVALID_ORDER_QUANTITY_DESC + ORDER_QUANTITY_DESC_ONE;
//        descriptor = new OrderDescriptorBuilder().withOrderQuantity(VALID_ORDER_STATUS_NOT_DONE).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // other valid values specified
//        // invalid Order name followed by valid Order name
//        userInput = targetIndex.getOneBased()
//                + INVALID_ORDER_NAME_DESC + ORDER_STATUS_DESC_NOT_DONE
//                + ORDER_DEADLINE_DESC_2023 + ORDER_NAME_DESC_CUPCAKES;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderName(VALID_ORDER_NAME_CUPCAKES)
//                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
//                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
//                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
//                .withCustomerName(VALID_CUSTOMER_NAME)
//                .withCustomerPhone(VALID_CUSTOMER_PHONE)
//                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // invalid Order deadline followed by valid Order deadline
//        userInput = targetIndex.getOneBased()
//                + ORDER_NAME_DESC_CUPCAKES + INVALID_ORDER_DEADLINE_DESC
//                + ORDER_STATUS_DESC_NOT_DONE + ORDER_DEADLINE_DESC_2023;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
//                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
//                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
//                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
//                .withCustomerName(VALID_CUSTOMER_NAME)
//                .withCustomerPhone(VALID_CUSTOMER_PHONE)
//                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
//
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // invalid Order status followed by valid Order status
//        userInput = targetIndex.getOneBased()
//                + INVALID_ORDER_STATUS_DESC + ORDER_NAME_DESC_CUPCAKES
//                + ORDER_STATUS_DESC_NOT_DONE + ORDER_DEADLINE_DESC_2023;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderName(VALID_ORDER_NAME_CUPCAKES)
//                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
//                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
//                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
//                .withCustomerName(VALID_CUSTOMER_NAME)
//                .withCustomerPhone(VALID_CUSTOMER_PHONE)
//                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_differentInputOrder_success() {
//        //Order status, Order deadline, Order name
//        Index targetIndex = INDEX_FIRST_OBJECT;
//        String userInput = targetIndex.getOneBased() + ORDER_STATUS_DESC_DONE
//                + ORDER_DEADLINE_DESC_2023 + ORDER_NAME_DESC_CUPCAKES
//                + ORDER_QUANTITY_DESC_TWO + CUSTOMER_NAME_DESC_NIGEL
//                + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC;
//        OrderDescriptor descriptor = new OrderDescriptorBuilder()
//                .withOrderStatus(VALID_ORDER_STATUS_DONE)
//                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
//                .withOrderName(VALID_ORDER_NAME_CUPCAKES)
//                .withOrderQuantity(VALID_ORDER_QUANTITY_TWO)
//                .withCustomerName(VALID_CUSTOMER_NAME)
//                .withCustomerPhone(VALID_CUSTOMER_PHONE)
//                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
//        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        //Order deadline, Order status, Order name
//        userInput = targetIndex.getOneBased() + ORDER_DEADLINE_DESC_2024
//                + ORDER_STATUS_DESC_NOT_DONE + ORDER_NAME_DESC_CUPCAKES
//                + ORDER_QUANTITY_DESC_TWO + CUSTOMER_NAME_DESC_NIGEL
//                + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC;
//        descriptor = new OrderDescriptorBuilder()
//                .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
//                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
//                .withOrderName(VALID_ORDER_NAME_CUPCAKES)
//                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
//                .withCustomerName(VALID_CUSTOMER_NAME)
//                .withCustomerPhone(VALID_CUSTOMER_PHONE)
//                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
//
//        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//}
