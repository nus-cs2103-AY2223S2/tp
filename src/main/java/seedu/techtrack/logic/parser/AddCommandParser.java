package seedu.techtrack.logic.parser;

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

import java.util.Set;
import java.util.stream.Stream;

import seedu.techtrack.logic.commands.AddCommand;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CONTACT, PREFIX_EMAIL, PREFIX_JOBDESCRIPTION,
                        PREFIX_COMPANY, PREFIX_TAG, PREFIX_WEBSITE, PREFIX_SALARY, PREFIX_DEADLINE, PREFIX_EXPERIENCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CONTACT,
                PREFIX_EMAIL, PREFIX_COMPANY, PREFIX_WEBSITE,
                PREFIX_JOBDESCRIPTION, PREFIX_SALARY, PREFIX_DEADLINE,
                PREFIX_EXPERIENCE) || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Contact contact = ParserUtil.parseContact(argMultimap.getValue(PREFIX_CONTACT).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        JobDescription jd = ParserUtil.parseJobDescription(argMultimap.getValue(PREFIX_JOBDESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Website website = ParserUtil.parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Experience experience = ParserUtil.parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get());
        Role role = new Role(name, contact, email, company, jd, tagList, website, salary, deadline, experience);

        return new AddCommand(role);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
