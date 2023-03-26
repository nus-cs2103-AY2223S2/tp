package seedu.address.logic.parser.txncommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.TxnCommandTestUtil.DESCRIPTION_DESC_LIMMY;
import static seedu.address.logic.commands.TxnCommandTestUtil.DESCRIPTION_DESC_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.TxnCommandTestUtil.INVALID_OWNER_DESC;
import static seedu.address.logic.commands.TxnCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.TxnCommandTestUtil.INVALID_VALUE_DESC;
import static seedu.address.logic.commands.TxnCommandTestUtil.OWNER_DESC_LIMMY;
import static seedu.address.logic.commands.TxnCommandTestUtil.OWNER_DESC_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.TxnCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.TxnCommandTestUtil.STATUS_DESC_LIMMY;
import static seedu.address.logic.commands.TxnCommandTestUtil.STATUS_DESC_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.VALID_DESCRIPTION_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.VALID_OWNER_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.VALID_STATUS_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.VALID_VALUE_MAC;
import static seedu.address.logic.commands.TxnCommandTestUtil.VALUE_DESC_LIMMY;
import static seedu.address.logic.commands.TxnCommandTestUtil.VALUE_DESC_MAC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTransactions.COFFEE_MACHINES_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.txncommands.AddTxnCommand;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Value;
import seedu.address.model.transaction.status.TxnStatus;
import seedu.address.testutil.TransactionBuilder;

class AddTxnCommandParserTest {
    private AddTxnCommandParser parser = new AddTxnCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        Transaction expectedTxn = new TransactionBuilder(COFFEE_MACHINES_B).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_MAC + VALUE_DESC_MAC
                + STATUS_DESC_MAC + OWNER_DESC_MAC, new AddTxnCommand(expectedTxn));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_LIMMY + DESCRIPTION_DESC_MAC
                + VALUE_DESC_MAC + STATUS_DESC_MAC + OWNER_DESC_MAC, new AddTxnCommand(expectedTxn));

        // multiple values - last value accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_MAC + STATUS_DESC_MAC
                + VALUE_DESC_LIMMY + VALUE_DESC_MAC, new AddTxnCommand(expectedTxn));

        // multiple owners - last owner accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_LIMMY + OWNER_DESC_MAC
                + STATUS_DESC_MAC + VALUE_DESC_MAC, new AddTxnCommand(expectedTxn));

        // multiple status - last status accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_MAC
                + STATUS_DESC_LIMMY + STATUS_DESC_MAC + VALUE_DESC_MAC, new AddTxnCommand(expectedTxn));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTxnCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_MAC + OWNER_DESC_MAC + VALUE_DESC_MAC
                + STATUS_DESC_MAC, expectedMessage);

        // missing owner prefix
        assertParseFailure(parser, DESCRIPTION_DESC_MAC + VALID_OWNER_MAC + VALUE_DESC_MAC
                + STATUS_DESC_MAC, expectedMessage);

        // missing value prefix
        assertParseFailure(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_MAC + VALID_VALUE_MAC
                + STATUS_DESC_MAC, expectedMessage);

        // missing status prefix
        assertParseFailure(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_MAC + VALUE_DESC_MAC
                + VALID_STATUS_MAC, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_MAC + VALID_OWNER_MAC + VALID_VALUE_MAC
                + VALID_STATUS_MAC, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + VALUE_DESC_MAC
                + STATUS_DESC_MAC + OWNER_DESC_MAC, Description.MESSAGE_CONSTRAINTS);

        // invalid owner
        assertParseFailure(parser, DESCRIPTION_DESC_MAC + VALUE_DESC_MAC
                + STATUS_DESC_MAC + INVALID_OWNER_DESC, Owner.MESSAGE_CONSTRAINTS);

        // invalid value
        assertParseFailure(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_MAC + INVALID_VALUE_DESC
                + STATUS_DESC_MAC, Value.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, DESCRIPTION_DESC_MAC + OWNER_DESC_MAC + VALUE_DESC_MAC
                + INVALID_STATUS_DESC, TxnStatus.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + INVALID_OWNER_DESC
                + VALUE_DESC_MAC + STATUS_DESC_MAC, Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_MAC + OWNER_DESC_MAC
                        + VALUE_DESC_MAC + STATUS_DESC_MAC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTxnCommand.MESSAGE_USAGE));
    }



}
