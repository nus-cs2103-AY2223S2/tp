package seedu.socket.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.EditProjectCommand;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;

/**
 * Parses input arguments and creates a new EditProjectCommand object
 */
public class EditProjectCommandParser implements Parser<EditProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditProjectCommand
     * and returns an EditProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_REPO_HOST,
                        PREFIX_REPO_NAME, PREFIX_DEADLINE, PREFIX_MEETING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProjectCommand.MESSAGE_USAGE), pe);
        }

        EditProjectCommand.EditProjectDescriptor editProjectDescriptor = new EditProjectCommand.EditProjectDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editProjectDescriptor.setName(ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_REPO_HOST).isPresent()) {
            String updatedRepoHost = argMultimap.getValue(PREFIX_REPO_HOST).get();
            if (!updatedRepoHost.isEmpty()) {
                editProjectDescriptor.setRepoHost(ParserUtil.parseRepoHost(updatedRepoHost));
            } else {
                throw new ParseException(ProjectRepoHost.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_REPO_NAME).isPresent()) {
            String updatedRepoName = argMultimap.getValue(PREFIX_REPO_NAME).get();
            if (!updatedRepoName.isEmpty()) {
                editProjectDescriptor.setRepoName(ParserUtil.parseRepoName(updatedRepoName));
            } else {
                throw new ParseException(ProjectRepoName.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            String updatedDeadline = argMultimap.getValue(PREFIX_DEADLINE).get();
            if (!updatedDeadline.isEmpty()) {
                editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(updatedDeadline));
            } else {
                throw new ParseException(ProjectDeadline.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_MEETING).isPresent()) {
            String updatedMeeting = argMultimap.getValue(PREFIX_MEETING).get();
            if (!updatedMeeting.isEmpty()) {
                editProjectDescriptor.setMeeting(ParserUtil.parseMeeting(updatedMeeting));
            } else {
                throw new ParseException(ProjectMeeting.MESSAGE_CONSTRAINTS);
            }
        }

        if (!editProjectDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProjectCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProjectCommand(index, editProjectDescriptor);
    }
}

