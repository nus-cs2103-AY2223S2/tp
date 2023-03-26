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
        String codePrefixString = argMultimap.getValue(PREFIX_CODE).orElse("");
        if (isModulePrefixPresent && codePrefixString.isEmpty()) {
            throw new ParseException(CodePrefix.MESSAGE_MISSING_DETAIL);
        }
        if (!codePrefixString.isEmpty()) {
            codePrefixString = ParserUtil.parseCodePrefix(codePrefixString).toString();
            filtersList.add(PREFIX_CODE + " " + codePrefixString);
        }

        boolean isCreditPrefixPresent = argMultimap.getValue(PREFIX_CREDIT).isPresent();
        String creditString = argMultimap.getValue(PREFIX_CREDIT).orElse("");
        if (isCreditPrefixPresent && creditString.isEmpty()) {
            throw new ParseException(Credit.MESSAGE_MISSING_DETAIL);
        }
        if (!creditString.isEmpty()) {
            creditString = ParserUtil.parseCredit(creditString).toString();
            filtersList.add(PREFIX_CREDIT + " " + creditString);
        }

        boolean isSemYearPresent = argMultimap.getValue(PREFIX_SEMYEAR).isPresent();
        String semYearString = argMultimap.getValue(PREFIX_SEMYEAR).orElse("");
        if (isSemYearPresent && semYearString.isEmpty()) {
            throw new ParseException(SemYear.MESSAGE_MISSING_DETAIL);
        }
        if (!semYearString.isEmpty()) {
            semYearString = ParserUtil.parseSemYear(semYearString).toString();
            filtersList.add(PREFIX_SEMYEAR + " " + semYearString);
        }

        boolean isGradePresent = argMultimap.getValue(PREFIX_GRADE).isPresent();
        String gradeString = argMultimap.getValue(PREFIX_GRADE).orElse("");
        if (isGradePresent && gradeString.isEmpty()) {
            throw new ParseException(Grade.MESSAGE_MISSING_DETAIL);
        }
        if (!gradeString.isEmpty()) {
            gradeString = ParserUtil.parseGrade(gradeString).toString();
            filtersList.add(PREFIX_GRADE + " " + gradeString);
        }

        boolean isTagPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        if (isTagPresent && argMultimap.getAllValues(PREFIX_TAG).contains("")) {
            throw new ParseException(Tag.MESSAGE_MISSING_DETAIL);
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        for (Tag tag : tagList) {
            filtersList.add(PREFIX_TAG + " " + tag.tagName);
        }

        ModuleCodePredicate moduleCodePredicate;
        if (!argMultimap.getPreamble().isEmpty()) {
            Code code = ParserUtil.parseCode(argMultimap.getPreamble());
            moduleCodePredicate = new ModuleCodePredicate(code.toString(), "",
                    "", "", new HashSet<>());
            filtersList.clear();
            filtersList.add(code.toString());
        } else {
            moduleCodePredicate = new ModuleCodePredicate(codePrefixString, creditString,
                    semYearString, gradeString, (HashSet<Tag>) tagList);
        }

        return new FindCommand(moduleCodePredicate, filtersList);
    }

}
