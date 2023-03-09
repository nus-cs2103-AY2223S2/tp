package t154.CLIpboard.logic.parser;

import static java.util.Objects.requireNonNull;
import static t154.CLIpboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_REMARK;

import t154.CLIpboard.commons.core.index.Index;
import t154.CLIpboard.commons.exceptions.IllegalValueException;
import t154.CLIpboard.logic.commands.RemarkCommand;
import t154.CLIpboard.logic.parser.exceptions.ParseException;
import t154.CLIpboard.model.student.Remark;

/**
 * Parses input arguments and creates a new RemarkCommand object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }
        Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        return new RemarkCommand(index, remark);
    }
}
