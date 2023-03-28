package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.comparator.CompanyNameComparator;
import seedu.address.model.person.comparator.InterviewDateComparator;
import seedu.address.model.person.comparator.JobTitleComparator;
import seedu.address.model.person.comparator.StatusComparator;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Checks if the prefix p specified is valid.
     *
     * @param p The prefix to be verified
     * @throws ParseException if none of the prefixes is matched
     */
    private void verifySortCommandOptions(Prefix p) throws ParseException {
        for (Prefix i : SortCommand.PREFIXES_SUPPORTED) {
            if (p.equals(i)) {
                return;
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String[] prefixes = trimmedArgs.split("\\s+");
        if (prefixes.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        Prefix prefix = new Prefix(prefixes[0]);
        verifySortCommandOptions(prefix);
        if (PREFIX_COMPANY_NAME.equals(prefix)) {
            return new SortCommand(new CompanyNameComparator());
        } else if (PREFIX_JOB_TITLE.equals(prefix)) {
            return new SortCommand(new JobTitleComparator());
        } else if (PREFIX_STATUS.equals(prefix)) {
            return new SortCommand(new StatusComparator());
        } else if (PREFIX_DATE.equals(prefix)) {
            return new SortCommand(new InterviewDateComparator());
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}

