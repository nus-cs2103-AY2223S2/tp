package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static bookopedia.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_NAME;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PARCEL;
import static bookopedia.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.EditCommand;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.parcel.Parcel;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_PARCEL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseParcelsForEdit(argMultimap.getAllValues(PREFIX_PARCEL)).ifPresent(editPersonDescriptor::setParcels);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> parcels} into a {@code Set<Parcel>} if {@code parcels} is non-empty.
     * If {@code parcels} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Parcel>} containing zero parcels.
     */
    private Optional<Set<Parcel>> parseParcelsForEdit(Collection<String> parcels) throws ParseException {
        assert parcels != null;

        if (parcels.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> parcelSet = parcels.size() == 1 && parcels.contains("") ? Collections.emptySet() : parcels;
        return Optional.of(ParserUtil.parseParcels(parcelSet));
    }

}
