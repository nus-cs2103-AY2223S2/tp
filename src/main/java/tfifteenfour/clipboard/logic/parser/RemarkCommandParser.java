package tfifteenfour.clipboard.logic.parser;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.RemarkCommand;
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
        Index index;
        Remark remark;

        if (currentSelection.getCurrentPage() != PageType.STUDENT_PAGE) {
            throw new CommandException("Wrong page. Navigate to student page to add a remark");
        }

        remark = new Remark(parseRemarkInfo(args));
        index = ParserUtil.parseIndex(args);

        return new RemarkCommand(index, remark);
    }

    private String parseRemarkInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE));
        }

        return String.join(" ", Arrays.copyOfRange(tokens, 2, tokens.length));

    }
}
