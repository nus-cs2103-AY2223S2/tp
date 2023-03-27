package vimification.internal.parser;

import java.time.LocalDateTime;

import vimification.internal.command.logic.CreateCommand;
import vimification.model.task.Deadline;
import vimification.model.task.Task;
import vimification.model.task.Todo;

public class CreateCommandParser implements CommandParser<CreateCommand> {

    /**
     * todo <description>
     */
    private static final ApplicativeParser<Todo> TODO_PARSER = ApplicativeParser
            .string("todo")
            .takeNext(ApplicativeParser.skipWhitespaces1())
            .takeNext(CommandParserUtil.STRING_PARSER)
            .map(Todo::new);

    private static final ApplicativeParser<LocalDateTime> DATE_TIME_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .map(ignore -> LocalDateTime.now());

    /**
     * deadline <description> --by <datetime>
     */
    private static final ApplicativeParser<Deadline> DEADLINE_PARSER = ApplicativeParser
            .string("deadline")
            .takeNext(ApplicativeParser.skipWhitespaces1())
            .takeNext(CommandParserUtil.STRING_PARSER)
            .dropNext(ApplicativeParser.skipWhitespaces1())
            .dropNext(ApplicativeParser.string("--by"))
            .dropNext(ApplicativeParser.skipWhitespaces1())
            .combine(DATE_TIME_PARSER, Deadline::new);

    private static final ApplicativeParser<Task> TASK_PARSER = ApplicativeParser
            .choice(TODO_PARSER, DEADLINE_PARSER)
            .dropNext(ApplicativeParser.skipWhitespaces1());
            // .combine(CommandParserUtil.ARG_MAP_PARSER, CreateCommandParser::setTaskArgs);

    private static final ApplicativeParser<CreateCommand> COMMAND_PARSER = TASK_PARSER
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(CreateCommand::new);

    private static final ApplicativeParser<ApplicativeParser<CreateCommand>> INTERNAL_PARSER =
            ApplicativeParser.string("i")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final CreateCommandParser INSTANCE = new CreateCommandParser();

    private CreateCommandParser() {}

    private static Task setTaskArgs(Task task, ArgumentMultimap map) {
        // set tags

        // set priority

        return task;
    }

    public static CreateCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<CreateCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
