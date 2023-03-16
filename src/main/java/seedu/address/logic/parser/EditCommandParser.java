package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditListingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;

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
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_APPLICANT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditListingDescriptor editListingDescriptor = new EditListingDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editListingDescriptor.setJobTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editListingDescriptor.setJobDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        parseApplicantsForEdit(argMultimap.getAllValues(PREFIX_APPLICANT)).ifPresent(editListingDescriptor::setApplicants);

        if (!editListingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editListingDescriptor);
    }

    /**
     * Parses {@code ArrayList<String> applicants} into a {@code ArrayList<Applicant>} if {@code applicants} is non-empty.
     * If {@code applicants} contain only one element which is an empty string, it will be parsed into a
     * {@code ArrayList<Applicant>} containing zero applicants.
     */
    private Optional<ArrayList<Applicant>> parseApplicantsForEdit(ArrayList<String> applicants) throws ParseException {
        assert applicants != null;

        if (applicants.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<String> applicantList = applicants.size() == 1 && applicants.contains("") ?
                                          new ArrayList<>() : applicants;
        return Optional.of(ParserUtil.parseApplicants(applicantList));
    }

}
