package seedu.techtrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.logic.commands.EditCommand;
import seedu.techtrack.logic.commands.EditCommand.EditRoleDescriptor;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.model.util.tag.Tag;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CONTACT, PREFIX_EMAIL,
                PREFIX_WEBSITE, PREFIX_COMPANY, PREFIX_TAG, PREFIX_JOBDESCRIPTION, PREFIX_SALARY, PREFIX_DEADLINE,
                PREFIX_EXPERIENCE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditRoleDescriptor editRoleDescriptor = new EditRoleDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editRoleDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            editRoleDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editRoleDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editRoleDescriptor.setCompany(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_WEBSITE).isPresent()) {
            editRoleDescriptor.setWebsite(ParserUtil.parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get()));
        }
        if (argMultimap.getValue(PREFIX_JOBDESCRIPTION).isPresent()) {
            editRoleDescriptor.setJobDescription(ParserUtil.parseJobDescription(argMultimap
                    .getValue(PREFIX_JOBDESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editRoleDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editRoleDescriptor.setDeadline(ParserUtil.parseDateline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()) {
            editRoleDescriptor.setExperience(ParserUtil.parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editRoleDescriptor::setTags);

        if (!editRoleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editRoleDescriptor);
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
