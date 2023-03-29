package seedu.connectus.logic.parser;

import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import seedu.connectus.logic.commands.SearchCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.person.FieldsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_BIRTHDAY, PREFIX_MODULE, PREFIX_SOCMED_INSTAGRAM,
                PREFIX_SOCMED_TELEGRAM, PREFIX_SOCMED_WHATSAPP, PREFIX_CCA, PREFIX_CCA_POSITION);

        FieldsContainKeywordsPredicate predicate = new FieldsContainKeywordsPredicate();

        List<String> keywords = ParserUtil.parseKeywords(argMultimap.getPreamble());
        if (keywords != null) {
            predicate.setKeywords(keywords);
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicate.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate.setPhone(ParserUtil.parsePhoneForFilter(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            predicate.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            predicate.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            predicate.setBirthday(ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()));
        }
        {
            predicate.setSocialMedia(ParserUtil.parseSocialMedia(argMultimap));
        }
        ParserUtil.parseRemarksOptional(argMultimap.getAllValues(PREFIX_REMARK)).ifPresent(predicate::setRemarks);
        ParserUtil.parseModulesOptional(argMultimap.getAllValues(PREFIX_MODULE)).ifPresent(predicate::setModules);
        ParserUtil.parseCcasOptional(argMultimap.getAllValues(PREFIX_CCA)).ifPresent(predicate::setCcas);
        ParserUtil.parseCcaPositionsOptional(argMultimap.getAllValues(PREFIX_CCA_POSITION))
                .ifPresent(predicate::setCcaPositions);

        return new SearchCommand(predicate);
    }

}
