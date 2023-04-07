package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCustomer object
 */
public class EditCustomerCommandParser implements Parser<EditCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCustomer
     * and returns an EditCustomer object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCustomerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_INTERNAL_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        // If no id present, don't know what to edit, throw error.
        if (!argMultimap.getValue(PREFIX_INTERNAL_ID).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCustomerCommand.MESSAGE_USAGE));
        }


        int customerId = ParserUtil.parseInt(argMultimap.getValue(PREFIX_INTERNAL_ID).get());
        Optional<Name> name = Optional.empty();
        Optional<Phone> phone = Optional.empty();
        Optional<Email> email = Optional.empty();
        Optional<Address> address = Optional.empty();
        Optional<Set<Tag>> tags = Optional.empty();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            name = Optional.of(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = Optional.of(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = Optional.of(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            address = Optional.of(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        tags = parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG));

        return new EditCustomerCommand(customerId, name, phone, email, address, tags);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
