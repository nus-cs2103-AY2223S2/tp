package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATE_OF_BIRTH_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATE_OF_JOINING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.LEAVE_COUNTER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PAYROLL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SOFTWARE_ENGINEER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEmployees.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EmployeeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Employee expectedEmployee = new EmployeeBuilder(BOB).withTags(VALID_TAG_MANAGER).build();

        // whitespace only preamble
        EmployeeBuilder.setEmployeeId(BOB.getEmployeeId().value); // set employee id to Bob's id
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DEPARTMENT_DESC_BOB + PAYROLL_DESC_BOB + LEAVE_COUNTER_DESC_BOB
                + DATE_OF_BIRTH_DESC_BOB + DATE_OF_JOINING_DESC_BOB + TAG_DESC_MANAGER,
                new AddCommand(expectedEmployee));

        // multiple names - last name accepted
        EmployeeBuilder.setEmployeeId(BOB.getEmployeeId().value); // set employee id to Bob's id
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DEPARTMENT_DESC_BOB + PAYROLL_DESC_BOB + LEAVE_COUNTER_DESC_BOB
                + DATE_OF_BIRTH_DESC_BOB + DATE_OF_JOINING_DESC_BOB + TAG_DESC_MANAGER,
                new AddCommand(expectedEmployee));

        // multiple phones - last phone accepted
        EmployeeBuilder.setEmployeeId(BOB.getEmployeeId().value); // set employee id to Bob's id
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DEPARTMENT_DESC_BOB + PAYROLL_DESC_BOB + LEAVE_COUNTER_DESC_BOB
                + DATE_OF_BIRTH_DESC_BOB + DATE_OF_JOINING_DESC_BOB + TAG_DESC_MANAGER,
                new AddCommand(expectedEmployee));

        // multiple emails - last email accepted
        EmployeeBuilder.setEmployeeId(BOB.getEmployeeId().value); // set employee id to Bob's id
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DEPARTMENT_DESC_BOB + PAYROLL_DESC_BOB + LEAVE_COUNTER_DESC_BOB
                + DATE_OF_BIRTH_DESC_BOB + DATE_OF_JOINING_DESC_BOB + TAG_DESC_MANAGER,
                new AddCommand(expectedEmployee));

        // multiple addresses - last address accepted
        EmployeeBuilder.setEmployeeId(BOB.getEmployeeId().value); // set employee id to Bob's id
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + DEPARTMENT_DESC_BOB + PAYROLL_DESC_BOB + LEAVE_COUNTER_DESC_BOB
                + DATE_OF_BIRTH_DESC_BOB + DATE_OF_JOINING_DESC_BOB + TAG_DESC_MANAGER,
                new AddCommand(expectedEmployee));

        // multiple tags - all accepted
        EmployeeBuilder.setEmployeeId(BOB.getEmployeeId().value); // set employee id to Bob's id
        Employee expectedEmployeeMultipleTags = new EmployeeBuilder(BOB)
                .withTags(VALID_TAG_MANAGER, VALID_TAG_SOFTWARE_ENGINEER)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DEPARTMENT_DESC_BOB + PAYROLL_DESC_BOB + PAYROLL_DESC_BOB + LEAVE_COUNTER_DESC_BOB
                        + DATE_OF_BIRTH_DESC_BOB + DATE_OF_JOINING_DESC_BOB + TAG_DESC_SOFTWARE_ENGINEER
                        + TAG_DESC_MANAGER,
                new AddCommand(expectedEmployeeMultipleTags));
    }



    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }


}
