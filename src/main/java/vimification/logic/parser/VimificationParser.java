package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

public class VimificationParser implements LogicCommandParser<LogicCommand> {

    private static final LogicCommandParser<LogicCommand> INSTANCE =
            CreateCommandParser.getInstance()
                    .cast()
                    .or(DeleteCommandParser.getInstance())
                    .updateInternalParser(parser -> ApplicativeParser
                            .string(":").optional()
                            .takeNext(ApplicativeParser.skipWhitespaces())
                            .takeNext(parser)
                            .throwIfFail("Unknown command"));

    private static final VimificationParser PROXY_INSTANCE = new VimificationParser();

    private VimificationParser() {}

    public static VimificationParser getInstance() {
        return PROXY_INSTANCE;
    }

    public ApplicativeParser<ApplicativeParser<LogicCommand>> getInternalParser() {
        return INSTANCE.getInternalParser();
    }
}
