package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static bookopedia.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static bookopedia.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static bookopedia.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_PARCEL_DESC;
import static bookopedia.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static bookopedia.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static bookopedia.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static bookopedia.logic.commands.CommandTestUtil.PARCEL_DESC_LAZADA;
import static bookopedia.logic.commands.CommandTestUtil.PARCEL_DESC_SHOPEE;
import static bookopedia.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static bookopedia.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static bookopedia.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static bookopedia.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static bookopedia.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static bookopedia.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static bookopedia.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PARCEL_LAZADA;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PARCEL_SHOPEE;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookopedia.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static bookopedia.testutil.TypicalPersons.AMY;
import static bookopedia.testutil.TypicalPersons.BOB;
import static bookopedia.testutil.TypicalPersons.OPTIONAL_AMY;

import org.junit.jupiter.api.Test;

import bookopedia.logic.commands.AddCommand;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Person;
import bookopedia.model.person.Phone;
import bookopedia.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withParcels(VALID_PARCEL_SHOPEE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PARCEL_DESC_SHOPEE, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PARCEL_DESC_SHOPEE, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PARCEL_DESC_SHOPEE, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PARCEL_DESC_SHOPEE, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + PARCEL_DESC_SHOPEE, new AddCommand(expectedPerson));

        // multiple parcels - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withParcels(VALID_PARCEL_SHOPEE, VALID_PARCEL_LAZADA)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PARCEL_DESC_LAZADA + PARCEL_DESC_SHOPEE, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero parcels
        Person expectedPerson = new PersonBuilder(AMY).withParcels().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_emptyEmailAndPhone_success() {
        // empty Email and Phone
        Person expectedPerson = new PersonBuilder(OPTIONAL_AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + ADDRESS_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PARCEL_DESC_LAZADA + PARCEL_DESC_SHOPEE, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + PARCEL_DESC_LAZADA + PARCEL_DESC_SHOPEE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + PARCEL_DESC_LAZADA + PARCEL_DESC_SHOPEE, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + PARCEL_DESC_LAZADA + PARCEL_DESC_SHOPEE, Address.MESSAGE_CONSTRAINTS);

        // invalid parcel
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_PARCEL_DESC + VALID_PARCEL_SHOPEE, Parcel.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + PARCEL_DESC_LAZADA + PARCEL_DESC_SHOPEE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
