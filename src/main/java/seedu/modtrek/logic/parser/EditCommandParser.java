package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR, PREFIX_GRADE, PREFIX_TAG);

        String preamble = argMultimap.getPreamble();
        if (preamble.isEmpty() || preamble.contains("/")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        Code code = ParserUtil.parseCode(preamble);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR, PREFIX_GRADE, PREFIX_TAG)) {
            throw new ParseException(String.format(EditCommand.MESSAGE_NOT_EDITED, EditCommand.MESSAGE_USAGE));
        }

        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CODE, EditCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CREDIT, EditCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_SEMYEAR, EditCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_GRADE, EditCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_TAG, EditCommand.MESSAGE_USAGE);

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();

        boolean isCodePrefixPresent = argMultimap.getValue(PREFIX_CODE).isPresent();
        String codeString = argMultimap.getValue(PREFIX_CODE).orElse("");
        if (isCodePrefixPresent && codeString.isEmpty()) {
            throw new ParseException(Code.MESSAGE_MISSING_DETAIL);
        }
        if (!codeString.isEmpty()) {
            editModuleDescriptor.setCode(ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get()));
        }

        boolean isCreditPrefixPresent = argMultimap.getValue(PREFIX_CREDIT).isPresent();
        String creditString = argMultimap.getValue(PREFIX_CREDIT).orElse("");
        if (isCreditPrefixPresent && creditString.isEmpty()) {
            throw new ParseException(Credit.MESSAGE_MISSING_DETAIL);
        }
        if (!creditString.isEmpty()) {
            editModuleDescriptor.setCredit(ParserUtil.parseCredit(argMultimap.getValue(PREFIX_CREDIT).get()));
        }

        boolean isSemYearPresent = argMultimap.getValue(PREFIX_SEMYEAR).isPresent();
        String semYearString = argMultimap.getValue(PREFIX_SEMYEAR).orElse("");
        if (isSemYearPresent && semYearString.isEmpty()) {
            throw new ParseException(SemYear.MESSAGE_MISSING_DETAIL);
        }
        if (!semYearString.isEmpty()) {
            editModuleDescriptor.setSemYear(ParserUtil.parseSemYear(argMultimap.getValue(PREFIX_SEMYEAR).get()));
        }

        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editModuleDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editModuleDescriptor::setTags);

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(code, editModuleDescriptor);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
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
