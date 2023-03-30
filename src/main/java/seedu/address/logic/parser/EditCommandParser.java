package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Note;
import seedu.address.model.person.ProgrammingLanguage;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.Reflection;
import seedu.address.model.person.Review;

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
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_LOCATION, PREFIX_SALARY,
                        PREFIX_RATING, PREFIX_QUALIFICATION, PREFIX_PROGRAMMING_LANGUAGE, PREFIX_REVIEW, PREFIX_NOTE,
                        PREFIX_REFLECTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editInternshipDescriptor.setCompanyName(ParserUtil
                                                    .parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editInternshipDescriptor.setJobTitle(ParserUtil
                                                .parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editInternshipDescriptor.setLocation(ParserUtil
                                                .parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editInternshipDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editInternshipDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }
        parseQualificationsForEdit(argMultimap.getAllValues(PREFIX_QUALIFICATION))
                                                            .ifPresent(editInternshipDescriptor::setQualifications);
        parseProgrammingLanguageForEdit(argMultimap.getAllValues(PREFIX_PROGRAMMING_LANGUAGE))
                                                        .ifPresent(editInternshipDescriptor::setProgrammingLanguages);
        parseReviewsForEdit(argMultimap.getAllValues(PREFIX_REVIEW)).ifPresent(editInternshipDescriptor::setReviews);
        parseNotesForEdit(argMultimap.getAllValues(PREFIX_NOTE)).ifPresent(editInternshipDescriptor::setNotes);
        parseReflectionsForEdit(argMultimap.getAllValues(PREFIX_REFLECTION))
                                                            .ifPresent(editInternshipDescriptor::setReflections);

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editInternshipDescriptor);
    }

    /**
     * Parses {@code Collection<String> qualifications} into a {@code Set<Qualification>} if {@code qualifications}
     * is non-empty. If {@code qualifications} contain only one element which is an empty string, it will be parsed into
     * a {@code Set<Qualification>} containing zero qualifications.
     */
    private Optional<Set<Qualification>> parseQualificationsForEdit(Collection<String> qualifications)
                                                                                            throws ParseException {
        assert qualifications != null;

        if (qualifications.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> qualificationSet = qualifications.size() == 1 && qualifications.contains("")
                                                                            ? Collections.emptySet() : qualifications;
        return Optional.of(ParserUtil.parseQualifications(qualificationSet));
    }

    /**
     * Parses {@code Collection<String> programmingLanguages} into a {@code Set<ProgrammingLanguage>} if
     * {@code programmingLanguages} is non-empty. If {@code programmingLanguages} contain only one element which is an
     * empty string, it will be parsed into a {@code Set<ProgrammingLanguage>} containing zero programmingLanguages.
     */
    private Optional<Set<ProgrammingLanguage>> parseProgrammingLanguageForEdit(Collection<String> programmingLanguages)
                                                                                                throws ParseException {
        assert programmingLanguages != null;

        if (programmingLanguages.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> programmingLanguageSet = programmingLanguages.size() == 1 && programmingLanguages
                                                        .contains("") ? Collections.emptySet() : programmingLanguages;
        return Optional.of(ParserUtil.parseProgrammingLanguages(programmingLanguageSet));
    }

    /**
     * Parses {@code Collection<String> reviews} into a {@code Set<Review>} if {@code reviews}
     * is non-empty. If {@code reviews} contain only one element which is an empty string, it will be parsed into
     * a {@code Set<Review>} containing zero reviews.
     */
    private Optional<Set<Review>> parseReviewsForEdit(Collection<String> reviews)
            throws ParseException {
        assert reviews != null;

        if (reviews.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> reviewSet = reviews.size() == 1 && reviews.contains("")
                                                                            ? Collections.emptySet() : reviews;
        return Optional.of(ParserUtil.parseReviews(reviewSet));
    }

    /**
     * Parses {@code Collection<String> notes} into a {@code Set<Note>} if {@code notes}
     * is non-empty. If {@code notes} contain only one element which is an empty string, it will be parsed into
     * a {@code Set<Note>} containing zero notes.
     */
    private Optional<Set<Note>> parseNotesForEdit(Collection<String> notes)
            throws ParseException {
        assert notes != null;

        if (notes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> noteSet = notes.size() == 1 && notes.contains("")
                ? Collections.emptySet() : notes;
        return Optional.of(ParserUtil.parseNotes(noteSet));
    }

    /**
     * Parses {@code Collection<String> reflections} into a {@code Set<Reflection>} if {@code reflections}
     * is non-empty. If {@code reflections} contain only one element which is an empty string, it will be parsed into
     * a {@code Set<Reflection>} containing zero reflections.
     */
    private Optional<Set<Reflection>> parseReflectionsForEdit(Collection<String> reflections)
            throws ParseException {
        assert reflections != null;

        if (reflections.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> reflectionSet = reflections.size() == 1 && reflections.contains("")
                ? Collections.emptySet() : reflections;
        return Optional.of(ParserUtil.parseReflections(reflectionSet));
    }

}
