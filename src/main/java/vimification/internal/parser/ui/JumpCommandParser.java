package vimification.internal.parser.ui;

import vimification.common.core.Index;
import vimification.internal.command.ui.JumpCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;

/**
 * The parser that can parses and creates new {@link JumpCommand}.
 */
public class JumpCommandParser implements CommandParser<JumpCommand> {

    private static final ApplicativeParser<ApplicativeParser<JumpCommand>> INTERNAL_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .map(JumpCommandParser::parseIndex);

    private static final JumpCommandParser INSTANCE = new JumpCommandParser();

    private JumpCommandParser() {}

    private static ApplicativeParser<JumpCommand> parseIndex(Index index) {
        JumpCommand command = new JumpCommand(index);
        return ApplicativeParser
                .of(command)
                .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);
    }

    public static JumpCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<JumpCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
