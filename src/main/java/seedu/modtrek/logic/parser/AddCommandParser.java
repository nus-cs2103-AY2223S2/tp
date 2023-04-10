package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

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
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR, PREFIX_GRADE, PREFIX_TAG);

        if (args.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CODE, AddCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CREDIT, AddCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_SEMYEAR, AddCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_GRADE, AddCommand.MESSAGE_USAGE);
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_TAG, AddCommand.MESSAGE_USAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR)) {
            throw new ParseException(AddCommand.MESSAGE_MISSING_PREFIXES);
        }

        String codeString = argMultimap.getValue(PREFIX_CODE).get();
        if (codeString.isEmpty()) {
            throw new ParseException(Code.MESSAGE_MISSING_DETAIL);
        }
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CODE, AddCommand.MESSAGE_USAGE);
        Code code = ParserUtil.parseCode(codeString);

        String creditString = argMultimap.getValue(PREFIX_CREDIT).get();
        if (creditString.isEmpty()) {
            throw new ParseException(Credit.MESSAGE_MISSING_DETAIL);
        }
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CREDIT, AddCommand.MESSAGE_USAGE);
        Credit credit = ParserUtil.parseCredit(creditString);

        String semYearString = argMultimap.getValue(PREFIX_SEMYEAR).get();
        if (semYearString.isEmpty()) {
            throw new ParseException(SemYear.MESSAGE_MISSING_DETAIL);
        }
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_SEMYEAR, AddCommand.MESSAGE_USAGE);
        SemYear semYear = ParserUtil.parseSemYear(semYearString);

        String gradeString = argMultimap.getValue(PREFIX_GRADE).orElse("");
        ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_GRADE, AddCommand.MESSAGE_USAGE);
        Grade grade = ParserUtil.parseGrade(gradeString);

        boolean isTagPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        if (isTagPresent && argMultimap.getAllValues(PREFIX_TAG).contains("")) {
            throw new ParseException(Tag.MESSAGE_MISSING_DETAIL);
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Module module = new Module(code, credit, semYear, tagList, grade);

        return new AddCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
