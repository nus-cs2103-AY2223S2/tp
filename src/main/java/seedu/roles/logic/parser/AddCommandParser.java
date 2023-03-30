package seedu.roles.logic.parser;

import static seedu.roles.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.roles.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.roles.logic.commands.AddCommand;
import seedu.roles.logic.commands.exceptions.exceptions.ParseException;
import seedu.roles.model.job.Company;
import seedu.roles.model.job.Deadline;
import seedu.roles.model.job.Email;
import seedu.roles.model.job.Experience;
import seedu.roles.model.job.JobDescription;
import seedu.roles.model.job.Name;
import seedu.roles.model.job.Phone;
import seedu.roles.model.job.Role;
import seedu.roles.model.job.Salary;
import seedu.roles.model.job.Website;
import seedu.roles.model.util.tag.Tag;


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
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CONTACT).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Company company = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_COMPANY).get());
        JobDescription jd = ParserUtil.parseJobDescription(argMultimap.getValue(PREFIX_JOBDESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Website website = ParserUtil.parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());
        Deadline deadline = ParserUtil.parseDateline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Experience experience = ParserUtil.parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get());
        Role role = new Role(name, phone, email, company, jd, tagList, website, salary, deadline, experience);

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
