package seedu.address.logic.parser;

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

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Location;
import seedu.address.model.person.Note;
import seedu.address.model.person.ProgrammingLanguage;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Reflection;
import seedu.address.model.person.Review;
import seedu.address.model.person.Salary;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_REVIEW, PREFIX_LOCATION,
                PREFIX_PROGRAMMING_LANGUAGE, PREFIX_QUALIFICATION, PREFIX_SALARY, PREFIX_RATING, PREFIX_NOTE,
                PREFIX_REFLECTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        CompanyName companyName = ParserUtil.parseCompanyName(
                                                    argMultimap.getValue(PREFIX_COMPANY_NAME).orElse(null));
        JobTitle jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).orElse(null));
        Set<Review> reviewList = ParserUtil.parseReviews(argMultimap.getAllValues(PREFIX_REVIEW));
        Set<ProgrammingLanguage> programmingLanguages = ParserUtil.parseProgrammingLanguages(
                                                                argMultimap.getAllValues(PREFIX_PROGRAMMING_LANGUAGE));
        Set<Qualification> qualifications = ParserUtil.parseQualifications(
                                                                argMultimap.getAllValues(PREFIX_QUALIFICATION));
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(null));
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).orElse(null));
        Set<Note> notes = ParserUtil.parseNotes(argMultimap.getAllValues(PREFIX_NOTE));
        Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse(null));
        Set<Reflection> reflections = ParserUtil.parseReflections(argMultimap.getAllValues(PREFIX_REFLECTION));

        InternshipApplication application = new InternshipApplication(companyName, jobTitle, reviewList,
            programmingLanguages, qualifications, location, salary, notes, rating, reflections);

        return new AddCommand(application);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
