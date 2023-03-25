package vimification.logic.parser;

import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.logic.commands.LogicCommand;

public class VimificationParser {

    private static final Logger LOGGER = LogsCenter.getLogger(VimificationParser.class);

    private static final LogicCommandParser<LogicCommand> INTERNAL_PARSER =
            CreateCommandParser.getInstance()
                    .cast()
                    .or(DeleteCommandParser.getInstance())
                    .updateInternalParser(parser -> parser.throwIfFail("Unknown command"));

    public static VimificationParser getInstance() {
        return new VimificationParser();
    }

    /**
     * Parses the user input and return its corresponding command.
     *
     * @param userInput
     * @return
     */
    public LogicCommand parse(String userInput) {
        LOGGER.info(userInput);
        return INTERNAL_PARSER.parse(userInput);
    }
}
