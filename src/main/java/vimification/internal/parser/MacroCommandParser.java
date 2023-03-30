package vimification.internal.parser;

import vimification.internal.command.macro.AddMacroCommand;
import vimification.internal.command.macro.DeleteMacroCommand;
import vimification.internal.command.macro.MacroCommand;

public class MacroCommandParser implements CommandParser<MacroCommand> {

    private static final ApplicativeParser<MacroCommand> COMMAND_PARSER =
            CommandParserUtil.MACRO_FLAG_PARSER
                    .dropNext(ApplicativeParser.skipWhitespaces1())
                    .map(ComposedArgumentFlag::getActualFlag)
                    .flatMap(flag -> {
                        if (flag.equals(CommandParserUtil.ADD_MACRO_FLAG)) {
                            return CommandParserUtil.STRING_PARSER
                                    .dropNext(ApplicativeParser.skipWhitespaces1())
                                    .combine(CommandParserUtil.STRING_PARSER, AddMacroCommand::new);
                        } else {
                            return CommandParserUtil.STRING_PARSER
                                    .map(DeleteMacroCommand::new);
                        }
                    })
                    .dropNext(ApplicativeParser.skipWhitespaces())
                    .dropNext(ApplicativeParser.eof());

    private static final ApplicativeParser<ApplicativeParser<MacroCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("macro")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final MacroCommandParser INSTANCE = new MacroCommandParser();

    private MacroCommandParser() {}

    public static MacroCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<MacroCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }

}
