package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DELIVERY_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DELIVERY_SLOT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EARNING;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERY_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERY_SLOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EARNING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPIENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SENDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;

public class AddDeliveryJobCommandParserTest {
    private AddDeliveryJobCommandParser parser = new AddDeliveryJobCommandParser();

    private static String buildCommand(String... arg) {
        StringBuilder sb = new StringBuilder();
        for (String string : arg) {
            sb.append(string + " ");
        }
        return sb.toString();
    }

    @Test
    public void parse_allFieldsPresent_success() {
        DeliveryJob expectedJob = new DeliveryJob(VALID_RECIPIENT, VALID_SENDER, VALID_DELIVERY_DATE,
                VALID_DELIVERY_SLOT, VALID_EARNING, "");

        // whitespace only preamble
        assertParseSuccess(parser,
                buildCommand(PREAMBLE_WHITESPACE, "si/", VALID_SENDER, "ri/", VALID_RECIPIENT, "earn/", VALID_EARNING),
                new AddDeliveryJobCommand(expectedJob));

        // all fields valid
        assertParseSuccess(parser,
                buildCommand(PREAMBLE_WHITESPACE, "si/", VALID_SENDER, "ri/", VALID_RECIPIENT, "earn/", VALID_EARNING,
                        "date/", VALID_DELIVERY_DATE, "slot/", VALID_DELIVERY_SLOT),
                new AddDeliveryJobCommand(expectedJob));

        // multiple slot - last slot taken
        assertParseSuccess(parser,
                buildCommand(PREAMBLE_WHITESPACE, "si/", VALID_SENDER, "ri/", VALID_RECIPIENT, "earn/", VALID_EARNING,
                        "date/", VALID_DELIVERY_DATE, "slot/", VALID_DELIVERY_SLOT, "slot/4"),
                new AddDeliveryJobCommand(expectedJob));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        DeliveryJob expectedJob = new DeliveryJob(VALID_RECIPIENT, VALID_SENDER, VALID_EARNING, "");

        // no date and slot
        assertParseSuccess(parser,
                buildCommand(" si/", VALID_SENDER, "ri/", VALID_RECIPIENT, "earn/", VALID_EARNING),
                new AddDeliveryJobCommand(expectedJob));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeliveryJobCommand.MESSAGE_USAGE);

        // missing sender
        assertParseFailure(parser,
                buildCommand(" ri/", VALID_RECIPIENT, "earn/", VALID_EARNING),
                expectedMessage);

        // missing recipient
        assertParseFailure(parser,
                buildCommand(" si/", VALID_SENDER, "earn/", VALID_EARNING),
                expectedMessage);

        // missing earning
        assertParseFailure(parser,
                buildCommand(" si/", VALID_SENDER, "ri/", VALID_RECIPIENT),
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, buildCommand(" "), expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid earning
        assertParseFailure(parser,
                buildCommand(" si/", VALID_RECIPIENT, "ri/", VALID_RECIPIENT, "earn/", INVALID_EARNING),
                Earning.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser,
                        buildCommand(PREAMBLE_WHITESPACE, "si/", VALID_SENDER, "ri/", VALID_RECIPIENT, "earn/",
                                        VALID_EARNING,
                                        "date/", INVALID_DELIVERY_DATE, "slot/", VALID_DELIVERY_SLOT),
                        DeliveryDate.MESSAGE_CONSTRAINTS);

        // invalid slot
        assertParseFailure(parser,
                        buildCommand(PREAMBLE_WHITESPACE, "si/", VALID_SENDER, "ri/", VALID_RECIPIENT, "earn/",
                                        VALID_EARNING,
                                        "date/", VALID_DELIVERY_DATE, "slot/", INVALID_DELIVERY_SLOT),
                        DeliverySlot.MESSAGE_CONSTRAINTS);
    }
}
