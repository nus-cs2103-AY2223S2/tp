package vimification.internal.parser;

import vimification.internal.command.ui.JumpCommand;

public class JumpCommandParser implements CommandParser<JumpCommand> {

    private static final ApplicativeParser<JumpCommand> COMMAND_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .map(JumpCommand::new)
                    .dropNext(ApplicativeParser.skipWhitespaces())
                    .dropNext(ApplicativeParser.eof());

    private static final ApplicativeParser<ApplicativeParser<JumpCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("quit")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final JumpCommandParser INSTANCE = new JumpCommandParser();

    public static JumpCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<JumpCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
