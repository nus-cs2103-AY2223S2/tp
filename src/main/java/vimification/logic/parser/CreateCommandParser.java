package vimification.logic.parser;

import java.time.LocalDateTime;

import vimification.logic.commands.CreateCommand;
import vimification.model.task.Deadline;
import vimification.model.task.Todo;

public class CreateCommandParser implements LogicCommandParser<CreateCommand> {

    private static final ApplicativeParser<LocalDateTime> DATE_TIME_PARSER = ApplicativeParser
            .parseNonWhitespaces()
            .map(ignore -> LocalDateTime.now()); // TODO: Fix after we agree on the format.

    private static final ApplicativeParser<Todo> TODO_PARSER = ApplicativeParser
            .skipWhitespaces()
            .takeNext(ApplicativeParser.parseString("todo"))
            .takeNext(ApplicativeParser.skipWhitespaces())
            .takeNext(ApplicativeParser.parseUntilEof().map(String::strip))
            .map(Todo::new);

    private static final ApplicativeParser<Deadline> DEADLINE_PARSER = ApplicativeParser
            .skipWhitespaces()
            .takeNext(ApplicativeParser.parseString("deadline"))
            .takeNext(ApplicativeParser.skipWhitespaces())
            .takeNext(ApplicativeParser.parseUntil("/").map(String::strip))
            .combine(DATE_TIME_PARSER, Deadline::new);

    private static final ApplicativeParser<CreateCommand> COMMAND_PARSER = ApplicativeParser
            .skipWhitespaces()
            .takeNext(ApplicativeParser.choice(TODO_PARSER, DEADLINE_PARSER))
            .dropNext(ApplicativeParser.skipWhitespaces())
            .dropNext(ApplicativeParser.eof())
            .map(CreateCommand::new);

    private static final ApplicativeParser<ApplicativeParser<CreateCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .skipWhitespaces()
                    .takeNext(ApplicativeParser.parseString("i"))
                    .constMap(COMMAND_PARSER);

    private static final CreateCommandParser INSTANCE = new CreateCommandParser();

    private CreateCommandParser() {}

    public static CreateCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<CreateCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
