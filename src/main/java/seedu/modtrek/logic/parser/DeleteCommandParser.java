package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.HashSet;
import java.util.Set;

import seedu.modtrek.logic.commands.DeleteCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CODE);

        boolean isAll;
        Set<Code> codes;
        String flag = argMultimap.getPreamble();
        boolean isCodePrefixPresent = argMultimap.getValue(PREFIX_CODE).isPresent();
        String codeString = argMultimap.getValue(PREFIX_CODE).orElse("");
        if (isCodePrefixPresent && codeString.isEmpty()) {
            throw new ParseException(Code.MESSAGE_MISSING_DETAIL);
        }
        if (flag.equals("all")) {
            isAll = true;
            codes = new HashSet<>();
        } else if (flag.isEmpty() && !codeString.isEmpty()) {
            ParserUtil.checkIfSlashIsPresent(argMultimap, PREFIX_CODE, DeleteCommand.MESSAGE_USAGE);
            isAll = false;
            codes = ParserUtil.parseCodes(argMultimap.getAllValues(PREFIX_CODE));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        return new DeleteCommand(isAll, codes);
    }

}
