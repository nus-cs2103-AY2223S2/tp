package vimification.internal.parser;

import vimification.commons.core.Index;
import vimification.internal.command.ui.JumpCommand;

public class JumpCommandParser implements CommandParser<JumpCommand> {

    private static final ApplicativeParser<ApplicativeParser<JumpCommand>> INTERNAL_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER.map(JumpCommandParser::parseIndex);

    private static final JumpCommandParser INSTANCE = new JumpCommandParser();

    private JumpCommandParser() {}

    private static ApplicativeParser<JumpCommand> parseIndex(Index index) {
        JumpCommand command = new JumpCommand(index);
        return ApplicativeParser.of(command)
                .dropNext(ApplicativeParser.skipWhitespaces())
                .dropNext(ApplicativeParser.eof());
    }

    public static JumpCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<JumpCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
