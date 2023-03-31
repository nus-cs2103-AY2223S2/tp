package tfifteenfour.clipboard.logic.parser;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.Arrays;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.studentcommands.RemarkCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.student.Remark;

/**
 * Parses input arguments and creates a new RemarkCommand object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {

    private final CurrentSelection currentSelection;

    public RemarkCommandParser(CurrentSelection currentSelection) {
        this.currentSelection = currentSelection;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException, CommandException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizePrefixes(args, PREFIX_REMARK);
        Index index;

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to add a remark");
        } else if (argMultimap.size() <= 1) {
            throw new CommandException("Wrong command format. " + RemarkCommand.MESSAGE_USAGE);
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }
        Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        return new RemarkCommand(index, remark);
    }

    private String parseRemarkInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        System.out.println(tokens.length);
        //if (tokens.length < 3) {
        //    throw new ParseException("Remark not entered");
        //}

        return String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));

    }
}
