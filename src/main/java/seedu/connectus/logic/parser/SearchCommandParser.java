package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_CCA_POSITION;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        args = " " + args;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_BIRTHDAY, PREFIX_MODULE, PREFIX_SOCMED_INSTAGRAM,
                PREFIX_SOCMED_TELEGRAM, PREFIX_SOCMED_WHATSAPP, PREFIX_CCA, PREFIX_CCA_POSITION);

        FieldsContainKeywordsPredicate predicate = new FieldsContainKeywordsPredicate();

        List<String> keywords = ParserUtil.parseKeywords(argMultimap.getPreamble());
        if (keywords != null) {
            predicate.setKeywords(keywords);
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (argMultimap.getValue(PREFIX_NAME).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            if (argMultimap.getValue(PREFIX_PHONE).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setPhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            if (argMultimap.getValue(PREFIX_EMAIL).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            if (argMultimap.getValue(PREFIX_ADDRESS).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            if (argMultimap.getValue(PREFIX_BIRTHDAY).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get());
        }
        if (argMultimap.getValue(PREFIX_SOCMED_INSTAGRAM).isPresent()) {
            if (argMultimap.getValue(PREFIX_SOCMED_INSTAGRAM).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setInstagram(argMultimap.getValue(PREFIX_SOCMED_INSTAGRAM).get());
        }
        if (argMultimap.getValue(PREFIX_SOCMED_TELEGRAM).isPresent()) {
            if (argMultimap.getValue(PREFIX_SOCMED_TELEGRAM).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setTelegram(argMultimap.getValue(PREFIX_SOCMED_TELEGRAM).get());
        }
        if (argMultimap.getValue(PREFIX_SOCMED_WHATSAPP).isPresent()) {
            if (argMultimap.getValue(PREFIX_SOCMED_WHATSAPP).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            }
            predicate.setWhatsapp(argMultimap.getValue(PREFIX_SOCMED_WHATSAPP).get());
        }
        Set<String> remarks = new HashSet<>();
        for (String s : argMultimap.getAllValues(PREFIX_REMARK)) {
            if (s.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            } else {
                remarks.add(s);
            }
        }
        if (remarks.size() > 0) {
            predicate.setRemarks(remarks);
        }
        Set<String> modules = new HashSet<>();
        for (String s : argMultimap.getAllValues(PREFIX_MODULE)) {
            if (s.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            } else {
                modules.add(s);
            }
        }
        if (modules.size() > 0) {
            predicate.setModules(modules);
        }
        Set<String> ccas = new HashSet<>();
        for (String s : argMultimap.getAllValues(PREFIX_CCA)) {
            if (s.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            } else {
                ccas.add(s);
            }
        }
        if (ccas.size() > 0) {
            predicate.setCcas(ccas);
        }
        Set<String> ccaPositions = new HashSet<>();
        for (String s : argMultimap.getAllValues(PREFIX_CCA_POSITION)) {
            if (s.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
            } else {
                ccaPositions.add(s);
            }
        }
        if (ccaPositions.size() > 0) {
            predicate.setCcaPositions(ccaPositions);
        }

        if (!predicate.isFieldKeywordPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SearchCommand.MESSAGE_NO_KEYWORDS + "\n" + SearchCommand.MESSAGE_USAGE));
        }

        return new SearchCommand(predicate);
    }

}
