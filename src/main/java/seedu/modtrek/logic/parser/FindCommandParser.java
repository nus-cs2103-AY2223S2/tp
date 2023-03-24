package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.ModuleCodePredicate;
import seedu.modtrek.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR, PREFIX_GRADE, PREFIX_TAG);

        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_CODE).isPresent();
        String codePrefixString = argMultimap.getValue(PREFIX_CODE).orElse("");
        if (isModulePrefixPresent && codePrefixString.isEmpty()) {
            throw new ParseException("Missing module code prefix after /m");
        }
        if (!codePrefixString.isEmpty()) {
            codePrefixString = ParserUtil.parseCodePrefix(codePrefixString).toString();
        }
        boolean isCreditPrefixPresent = argMultimap.getValue(PREFIX_CREDIT).isPresent();
        String creditString = argMultimap.getValue(PREFIX_CREDIT).orElse("");
        if (isCreditPrefixPresent && creditString.isEmpty()) {
            throw new ParseException("Missing credit after /c");
        }
        if (!creditString.isEmpty()) {
            creditString = ParserUtil.parseCredit(creditString).toString();
        }
        boolean isSemYearPresent = argMultimap.getValue(PREFIX_SEMYEAR).isPresent();
        String semYearString = argMultimap.getValue(PREFIX_SEMYEAR).orElse("");
        if (isSemYearPresent && semYearString.isEmpty()) {
            throw new ParseException("Missing semyear after /y");
        }
        if (!semYearString.isEmpty()) {
            semYearString = ParserUtil.parseSemYear(semYearString).toString();
        }
        boolean isGradePresent = argMultimap.getValue(PREFIX_GRADE).isPresent();
        String gradeString = argMultimap.getValue(PREFIX_GRADE).orElse("");
        if (isGradePresent && gradeString.isEmpty()) {
            throw new ParseException("Missing grade after /g");
        }
        if (!gradeString.isEmpty()) {
            gradeString = ParserUtil.parseGrade(gradeString).toString();
        }
        boolean isTagPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        if (isTagPresent && argMultimap.getAllValues(PREFIX_TAG).contains("")) {
            throw new ParseException("Missing tag after /t");
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (!argMultimap.getPreamble().isEmpty()) {
            Code code = ParserUtil.parseCode(argMultimap.getPreamble());
            ModuleCodePredicate moduleCodePredicate = new ModuleCodePredicate(code.toString(), "",
                    "", "", new HashSet<>());
            return new FindCommand(moduleCodePredicate);
        }
        ModuleCodePredicate moduleCodePredicate = new ModuleCodePredicate(codePrefixString, creditString,
                semYearString, gradeString, (HashSet<Tag>) tagList);
        return new FindCommand(moduleCodePredicate);
    }

}
