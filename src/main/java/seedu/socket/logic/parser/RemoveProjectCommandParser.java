package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.RemoveProjectCommand;
import seedu.socket.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveProjectCommand object
 */
public class RemoveProjectCommandParser implements Parser<RemoveProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveProjectCommand
     * and returns an RemoveProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REPO_HOST, PREFIX_REPO_NAME, PREFIX_DEADLINE, PREFIX_MEETING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveProjectCommand.MESSAGE_USAGE), pe);
        }

        RemoveProjectCommand.RemoveProjectDescriptor removeProjectDescriptor =
                new RemoveProjectCommand.RemoveProjectDescriptor();
        if (argMultimap.getValue(PREFIX_REPO_HOST).isPresent()) {
            removeProjectDescriptor.setRepoHost(ParserUtil.parseRepoHost(argMultimap.getValue(PREFIX_REPO_HOST).get()));
        }
        if (argMultimap.getValue(PREFIX_REPO_NAME).isPresent()) {
            removeProjectDescriptor.setRepoName(ParserUtil.parseRepoName(argMultimap.getValue(PREFIX_REPO_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            removeProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING).isPresent()) {
            removeProjectDescriptor.setMeeting(ParserUtil.parseMeeting(argMultimap.getValue(PREFIX_MEETING).get()));
        }

        if (!removeProjectDescriptor.isAnyFieldRemoved()) {
            throw new ParseException(RemoveProjectCommand.MESSAGE_NOT_REMOVE);
        }

        return new RemoveProjectCommand(index, removeProjectDescriptor);
    }
}
