package vimification.internal.parser;

import java.util.logging.Logger;

import vimification.common.core.LogsCenter;
import vimification.internal.command.Command;
import vimification.internal.command.logic.LogicCommand;
import vimification.internal.command.macro.MacroCommand;
import vimification.internal.command.ui.UiCommand;
import vimification.internal.parser.logic.AddCommandParser;
import vimification.internal.parser.logic.DeleteCommandParser;
import vimification.internal.parser.logic.EditCommandParser;
import vimification.internal.parser.logic.InsertCommandParser;
import vimification.internal.parser.logic.UndoCommandParser;
import vimification.internal.parser.macro.MacroCommandParser;
import vimification.internal.parser.ui.FilterCommandParser;
import vimification.internal.parser.ui.HelpCommandParser;
import vimification.internal.parser.ui.JumpCommandParser;
import vimification.internal.parser.ui.QuitCommandParser;
import vimification.internal.parser.ui.RefreshCommandParser;
import vimification.internal.parser.ui.SortCommandParser;
import vimification.model.MacroMap;

/**
 * The application's parser, used to parses user input and creates different commands.
 */
public class VimificationParser {

    private static final Logger LOGGER = LogsCenter.getLogger(VimificationParser.class);

    private static final ApplicativeParser<Void> PREPROCESSOR = ApplicativeParser
            .string(":")
            .optional();

    private static final CommandParser<LogicCommand> LOGIC_COMMAND_PARSER =
            AddCommandParser.getInstance()
                    .<LogicCommand>cast()
                    .or(DeleteCommandParser.getInstance())
                    .or(InsertCommandParser.getInstance())
                    .or(EditCommandParser.getInstance())
                    .or(UndoCommandParser.getInstance());

    private static final CommandParser<UiCommand> UI_COMMAND_PARSER =
            FilterCommandParser.getInstance()
                    .<UiCommand>cast()
                    .or(SortCommandParser.getInstance())
                    .or(RefreshCommandParser.getInstance())
                    .or(HelpCommandParser.getInstance())
                    .or(JumpCommandParser.getInstance())
                    .or(QuitCommandParser.getInstance());

    private static final CommandParser<MacroCommand> MACRO_COMMAND_PARSER =
            MacroCommandParser.getInstance();

    private static final CommandParser<Command> COMMAND_PARSER =
            LOGIC_COMMAND_PARSER
                    .<Command>cast()
                    .or(UI_COMMAND_PARSER)
                    .or(MACRO_COMMAND_PARSER)
                    .updateInternalParser(parser -> parser.throwIfFail("Unknown command"));

    private MacroMap macroMap;

    private final ApplicativeParser<String> macroPreprocessor =
            PREPROCESSOR
                    .takeNext(CommandParserUtil.STRING_PARSER)
                    .map(input -> macroMap.get(input).orElse(input))
                    .combine(ApplicativeParser.untilEof(), String::concat);

    private VimificationParser(MacroMap macroMap) {
        this.macroMap = macroMap;
    }

    public static VimificationParser getInstance(MacroMap macroMap) {
        if (macroMap == null) {
            macroMap = new MacroMap();
        }
        return new VimificationParser(macroMap);
    }

    /**
     * Parses the user input and return its corresponding command.
     *
     * @param input the user input
     * @return the command represented by the user input
     */
    public Command parse(String input) {
        LOGGER.info("Original input: " + input);
        String preprocessedInput = macroPreprocessor.parse(input);
        LOGGER.info("Input expanded to: " + preprocessedInput);
        return COMMAND_PARSER.parse(preprocessedInput);
    }
}
