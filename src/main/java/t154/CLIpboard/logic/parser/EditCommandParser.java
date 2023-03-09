package t154.CLIpboard.logic.parser;

import static java.util.Objects.requireNonNull;
import static t154.CLIpboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_MODULE;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_NAME;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import t154.CLIpboard.commons.core.index.Index;
import t154.CLIpboard.logic.commands.EditCommand;
import t154.CLIpboard.logic.commands.EditCommand.EditStudentDescriptor;
import t154.CLIpboard.logic.parser.exceptions.ParseException;
import t154.CLIpboard.model.student.ModuleCode;
import t154.CLIpboard.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_STUDENTID,
                        PREFIX_MODULE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            editStudentDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            parseModulesForEdit(argMultimap.getAllValues(PREFIX_MODULE)).ifPresent(editStudentDescriptor::setModules);
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<ModuleCode>} if {@code modules} is non-empty.
     * @throws ParseException if {@code modules} contain only one element which is an empty string
     */
    private Optional<Set<ModuleCode>> parseModulesForEdit(Collection<String> modules) throws ParseException {
        Collection<String> moduleSet = modules;
        return Optional.of(ParserUtil.parseModules(moduleSet));
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
