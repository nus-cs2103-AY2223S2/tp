package seedu.internship.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.internship.commons.core.LogsCenter;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.EditCommand;
import seedu.internship.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    private static final Logger logger = LogsCenter.getLogger(EditCommandParser.class);

    /**
     * Parses arguments to construct an {@code EditCommand}
     *
     * @param args Raw user input.
     * @return an {@code EditCommand} encapsulating the user's input.
     * @throws ParseException if the user's input cannot be parsed into a valid {@code EditCommand}.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_STATUS, PREFIX_ROLE, PREFIX_DATE,
                        PREFIX_COMMENT, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            ParseException e = ParserUtil.handleIndexException(pe, EditCommand.MESSAGE_USAGE);
            throw e;
        }

        EditInternshipDescriptor editInternshipDescriptor = new EditCommand.EditInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editInternshipDescriptor.setCompanyName(ParserUtil.parseCompanyName(argMultimap.getValue(
                    PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editInternshipDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editInternshipDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editInternshipDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMENT).isPresent()) {
            String commentContent = "";
            try {
                String userInput = argMultimap.getValue(PREFIX_COMMENT).get();
                if (userInput.equals("")) {
                    logger.info("User entered empty comment string, should reset comment to NA");
                    commentContent = "NA";
                } else {
                    commentContent = userInput;
                }
            } catch (NoSuchElementException emptyComment) {
                //Should not reach here
                assert(false);
            }
            editInternshipDescriptor.setComment(ParserUtil.parseComment(commentContent));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editInternshipDescriptor::setTags);

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editInternshipDescriptor);
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
