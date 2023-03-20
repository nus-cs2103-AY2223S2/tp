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
            .parseString("todo")
            .takeNext(ApplicativeParser.skipWhitespaces())
            .takeNext(ApplicativeParser.parseUntilEof().map(String::strip))
            .map(Todo::new);

    private static final ApplicativeParser<Deadline> DEADLINE_PARSER = ApplicativeParser
            .parseString("deadline")
            .takeNext(ApplicativeParser.skipWhitespaces())
            .takeNext(ApplicativeParser.parseUntil("/").map(String::strip))
            .flatMap(description -> DATE_TIME_PARSER
                    .dropNext(ApplicativeParser.skipWhitespaces())
                    .dropNext(ApplicativeParser.eof())
                    .map(deadline -> new Deadline(description, deadline)));

    private static final ApplicativeParser<CreateCommand> CREATE_COMMAND_PARSER = ApplicativeParser
            .parseString("i")
            .takeNext(ApplicativeParser.skipWhitespaces())
            .takeNext(ApplicativeParser.choice(TODO_PARSER, DEADLINE_PARSER))
            .map(CreateCommand::new);

    private static final CreateCommandParser INSTANCE = new CreateCommandParser();

    private CreateCommandParser() {}

    public static CreateCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<CreateCommand> getInternalParser() {
        return CREATE_COMMAND_PARSER;
    }
}
