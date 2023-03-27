package vimification.internal.parser;

import java.time.LocalDateTime;
import java.util.Set;

import vimification.internal.command.logic.CreateCommand;
import vimification.model.task.Deadline;
import vimification.model.task.Priority;
import vimification.model.task.Task;
import vimification.model.task.Todo;

public class CreateCommandParser implements CommandParser<CreateCommand> {

    private static final ArgumentFlag TAG_FLAG = new ArgumentFlag("-t", "--tag", Integer.MAX_VALUE);
    private static final ArgumentFlag PRIORITY_FLAG = new ArgumentFlag("-p", "--priority");

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

    private static final ApplicativeParser<Priority> PRIORITY_PARSER =
            CommandParserUtil.PRIORITY_PARSER
                    .throwIfFail("Invalid priority");

    private static final ApplicativeParser<Task> TASK_PARSER = ApplicativeParser
            .choice(TODO_PARSER, DEADLINE_PARSER)
            .flatMap(task -> ApplicativeParser
                    .skipWhitespaces1()
                    .takeNext(CommandParserUtil.arguments(TAG_FLAG, PRIORITY_FLAG))
                    .map(args -> modifyTask(task, args))
                    .orElse(task));

    private static final ApplicativeParser<CreateCommand> COMMAND_PARSER = TASK_PARSER
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(CreateCommand::new);

    private static final ApplicativeParser<ApplicativeParser<CreateCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("i")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final CreateCommandParser INSTANCE = new CreateCommandParser();

    private CreateCommandParser() {}

    private static Task modifyTask(Task task, ArgumentMultimap args) {
        // set tags
        Set<String> tags = args.get(TAG_FLAG);
        // set priority
        args.getFirst(PRIORITY_FLAG).ifPresent(str -> {
            Priority priority = PRIORITY_PARSER.parse(str);
            task.setPriority(priority);
        });
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
