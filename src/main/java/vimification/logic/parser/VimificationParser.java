package vimification.logic.parser;

import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.logic.commands.LogicCommand;

public class VimificationParser implements LogicCommandParser<LogicCommand> {

    private static final Logger LOGGER = LogsCenter.getLogger(VimificationParser.class);

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

    @Override
    public ApplicativeParser<ApplicativeParser<LogicCommand>> getInternalParser() {
        return INSTANCE.getInternalParser();
    }

    /**
     * Parses the user input and return its corresponding command.
     *
     * @param userInput
     * @return
     */
    @Override
    public LogicCommand parse(String userInput) {
        LOGGER.info(userInput);
        return INSTANCE.parse(userInput);
    }
}
