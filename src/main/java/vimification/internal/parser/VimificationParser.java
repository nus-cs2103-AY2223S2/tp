package vimification.internal.parser;

import java.util.function.Function;
import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.internal.command.logic.LogicCommand;
import vimification.model.MacroMap;

public class VimificationParser implements LogicCommandParser<LogicCommand> {

    private static final Logger LOGGER = LogsCenter.getLogger(VimificationParser.class);

    private MacroMap macroMap;

    private final ApplicativeParser<ApplicativeParser<LogicCommand>> internalParser =
            ApplicativeParser
                    .string(":")
                    .optional()
                    .takeNext(ApplicativeParser.untilEof().map(String::strip))
                    .map(input -> macroMap.get(input).orElse(input))
                    .takeNext(ApplicativeParser.choice(
                            CreateCommandParser.getInstance().cast().getInternalParser(),
                            DeleteCommandParser.getInstance().cast().getInternalParser()))
                    .throwIfFail("Unknown command");

    private VimificationParser(MacroMap macroMap) {
        this.macroMap = macroMap;
    }

    // public static VimificationParser getInstance() {
    // return new VimificationParser(new MacroMap());
    // }

    public static VimificationParser getInstance(MacroMap macroMap) {
        if (macroMap == null) {
            macroMap = new MacroMap();
        }
        return new VimificationParser(macroMap);
    }

    @Override
    public ApplicativeParser<ApplicativeParser<LogicCommand>> getInternalParser() {
        return internalParser;
    }

    /**
     * Parses the user input and return its corresponding command.
     *
     * @param userInput
     * @return
     */
    @Override
    public LogicCommand parse(String input) {
        LOGGER.info(input);
        return getInternalParser().flatMap(Function.identity()).parse(input);
    }
}
