package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.CodePrefix;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.ModuleCodePredicate;
import seedu.modtrek.model.module.SemYear;
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
        requireNonNull(args);

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_CREDIT, PREFIX_SEMYEAR, PREFIX_GRADE, PREFIX_TAG);

        List<String> filtersList = new ArrayList<>();

        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_CODE).isPresent();
        if (isModulePrefixPresent && argMultimap.getAllValues(PREFIX_CODE).contains("")) {
            throw new ParseException(CodePrefix.MESSAGE_MISSING_DETAIL);
        }
        Set<String> codePrefixSet = ParserUtil.parseCodePrefixes(argMultimap.getAllValues(PREFIX_CODE));
        for (String codePrefix : codePrefixSet) {
            filtersList.add(PREFIX_CODE + " " + codePrefix);
        }

        boolean isCreditPrefixPresent = argMultimap.getValue(PREFIX_CREDIT).isPresent();
        if (isCreditPrefixPresent && argMultimap.getAllValues(PREFIX_CREDIT).contains("")) {
            throw new ParseException(Credit.MESSAGE_MISSING_DETAIL);
        }
        Set<Credit> creditSet = ParserUtil.parseCredits(argMultimap.getAllValues(PREFIX_CREDIT));
        for (Credit credit : creditSet) {
            filtersList.add(PREFIX_CREDIT + " " + credit.toString());
        }

        boolean isSemYearPresent = argMultimap.getValue(PREFIX_SEMYEAR).isPresent();
        if (isSemYearPresent && argMultimap.getAllValues(PREFIX_SEMYEAR).contains("")) {
            throw new ParseException(SemYear.MESSAGE_MISSING_DETAIL);
        }
        Set<SemYear> semYearSet = ParserUtil.parseSemYears(argMultimap.getAllValues(PREFIX_SEMYEAR));
        for (SemYear semYear : semYearSet) {
            filtersList.add(PREFIX_SEMYEAR + " " + semYear.toString());
        }

        boolean isGradePresent = argMultimap.getValue(PREFIX_GRADE).isPresent();
        if (isGradePresent && argMultimap.getAllValues(PREFIX_GRADE).contains("")) {
            throw new ParseException(Grade.MESSAGE_MISSING_DETAIL);
        }
        Set<Grade> gradeSet = ParserUtil.parseGrades(argMultimap.getAllValues(PREFIX_GRADE));
        for (Grade grade : gradeSet) {
            filtersList.add(PREFIX_GRADE + " " + grade.toString());
        }

        boolean isTagPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        if (isTagPresent && argMultimap.getAllValues(PREFIX_TAG).contains("")) {
            throw new ParseException(Tag.MESSAGE_MISSING_DETAIL);
        }
        Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        for (Tag tag : tagSet) {
            filtersList.add(PREFIX_TAG + " " + tag.tagName);
        }

        ModuleCodePredicate moduleCodePredicate;
        if (!argMultimap.getPreamble().isEmpty()) {
            Code code;
            try {
                code = ParserUtil.parseCode(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            moduleCodePredicate = new ModuleCodePredicate(true, code.toString(), new HashSet<>(),
                    new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
            filtersList.clear();
            filtersList.add(code.toString());
        } else {
            moduleCodePredicate = new ModuleCodePredicate(false, "", codePrefixSet,
                    creditSet, semYearSet, gradeSet, tagSet);
        }

        return new FindCommand(moduleCodePredicate, filtersList);
    }

}
