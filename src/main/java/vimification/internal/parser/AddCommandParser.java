package vimification.internal.parser;

import java.time.LocalDateTime;

import vimification.internal.command.logic.AddCommand;
import vimification.model.task.Priority;
import vimification.model.task.Task;


public class AddCommandParser implements CommandParser<AddCommand> {

    private static final ArgumentFlag LABEL_FLAG =
            new ArgumentFlag("-l", "--label", Integer.MAX_VALUE);
    private static final ArgumentFlag PRIORITY_FLAG = new ArgumentFlag("-p", "--priority");
    private static final ArgumentFlag DEADLINE_FLAG = new ArgumentFlag("-d", "--deadline");

    private static final ApplicativeParser<LocalDateTime> DEADLINE_PARSER = ApplicativeParser
            .nonWhitespaces1()
            .map(ignore -> LocalDateTime.now());

    private static final ApplicativeParser<Priority> PRIORITY_PARSER =
            CommandParserUtil.PRIORITY_PARSER.throwIfFail("Invalid priority");

    private static final ApplicativeParser<Task> TASK_PARSER = CommandParserUtil.STRING_PARSER
            .map(Task::new) // description
            .flatMap(task -> ApplicativeParser // optional flags
                    .skipWhitespaces1()
                    .takeNext(CommandParserUtil.arguments(LABEL_FLAG, PRIORITY_FLAG, DEADLINE_FLAG))
                    .map(args -> modifyTask(task, args))
                    .orElse(task));

    private static final ApplicativeParser<AddCommand> COMMAND_PARSER = TASK_PARSER
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(AddCommand::new);

    private static final ApplicativeParser<ApplicativeParser<AddCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("a")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final AddCommandParser INSTANCE = new AddCommandParser();

    private AddCommandParser() {}

    private static Task modifyTask(Task task, ArgumentMultimap args) {
        args.get(LABEL_FLAG).forEach(task::addLabel);
        args.getFirst(PRIORITY_FLAG).ifPresent(s -> task.setPriority(PRIORITY_PARSER.parse(s)));
        args.getFirst(DEADLINE_FLAG).ifPresent(s -> task.setDeadline(DEADLINE_PARSER.parse(s)));
        return task;
    }

    public static AddCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<AddCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
