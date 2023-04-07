package vimification.internal.parser.logic;

import vimification.internal.command.logic.UndoCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;

/**
 * The parser that can parses and creates new {@link UndoCommand}.
 */
public class UndoCommandParser implements CommandParser<UndoCommand> {

    private static final ApplicativeParser<UndoCommand> COMMAND_PARSER =
            ApplicativeParser
                    .of(new UndoCommand())
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<UndoCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("undo")
                    .constMap(COMMAND_PARSER);

    private static final UndoCommandParser INSTANCE = new UndoCommandParser();

    public static UndoCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<UndoCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
