package seedu.address.logic.parser.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.homework.UpdateHomeworkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NamePredicate;

/**
 * An UpdateHomeworkCommandParser that parses input arguments and creates a new UpdateHomeworkCommand object
 */
public class UpdateHomeworkCommandParser implements Parser<UpdateHomeworkCommand> {
    private List<String> names = new ArrayList<>();
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateHomeworkCommand
     * and returns an UpdateHomeworkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateHomeworkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX, PREFIX_HOMEWORK, PREFIX_DEADLINE);

        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_HOMEWORK)
                && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX, PREFIX_DEADLINE))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateHomeworkCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        // for all the names, trim the name and only take the first word
        for (int i = 0; i < nameKeywords.size(); i++) {
            String name = nameKeywords.get(i);
            name = name.trim();
            nameKeywords.set(i, name);
        }
        names = nameKeywords;

        if (nameKeywords.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Only one student one be updated at a time."));
        }
        // name cannot be an empty string
        if (nameKeywords.get(0).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Name cannot be empty."));
        }

        if (argMultimap.getAllValues(PREFIX_INDEX).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Only one index is allowed for update homework command."));
        }
        // index cannot be an empty string
        if (argMultimap.getValue(PREFIX_INDEX).get().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Index cannot be empty."));
        }

        // if homework name is not present, set it to null, else parse it
        Optional<String> homeworkName = Optional.empty();
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            homeworkName = Optional.of(argMultimap.getValue(PREFIX_HOMEWORK).get());
            // homework name cannot be an empty string
            if (homeworkName.get().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        "Homework name cannot be empty."));
            }
        }

        // if deadline is not present, set it to null, else parse it
        Optional<LocalDateTime> deadline = Optional.empty();
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            deadline = Optional.of(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
            // deadline cannot be an empty string
            if (argMultimap.getValue(PREFIX_DEADLINE).get().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        "Deadline cannot be empty."));
            }

            // deadline must be in the future
            if (deadline.get().isBefore(LocalDateTime.now())) {
                throw new ParseException(String.format("Deadline must be in the future."));
            }
        }

        return new UpdateHomeworkCommand(names, index, new NamePredicate(nameKeywords),
                homeworkName, deadline);
    }

    /**
     * Returns true if all prefixes are present in the given {@code ArgumentMultimap}.
     *
     * @param argumentMultimap the argument multimap to check for prefixes.
     * @param prefixes the prefixes to be checked.
     * @return true if all prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
