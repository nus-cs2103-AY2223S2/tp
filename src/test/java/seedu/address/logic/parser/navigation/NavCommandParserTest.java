package seedu.address.logic.parser.navigation;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.navigation.DirectNavCommand;
import seedu.address.logic.commands.navigation.NavCommand;
import seedu.address.logic.commands.navigation.RelativeNavCommand;
import seedu.address.logic.commands.navigation.RootNavCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.TypicalModules;

public class NavCommandParserTest {

    private NavCommandParser parser = new NavCommandParser();
    private ModuleCode modCode = TypicalModules.CS2040S.getCode();
    private LectureName lecName = new LectureName(VALID_LECTURE_NAME_L1);

    @Test
    public void parse_navRoot_success() throws ParseException {
        assertParseSuccess(parser, "", new RootNavCommand());
    }

    @Test
    public void parse_navDirectToModLec_success() throws ParseException {
        Optional<ModuleCode> modOpt = Optional.of(modCode);
        Optional<LectureName> lecOpt = Optional.of(lecName);

        Command directNavCmd = new DirectNavCommand(modOpt, lecOpt);
        assertParseSuccess(parser, MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1, directNavCmd);
    }

    @Test
    public void parse_navRelative_success() throws ParseException {
        Command relativeNavCmd = new RelativeNavCommand(VALID_LECTURE_NAME_L1);
        assertParseSuccess(parser, VALID_LECTURE_NAME_L1, relativeNavCmd);
    }

    @Test
    public void parse_bothRelativeAndDirectParamsPresent_throwsParseException() {
        assertParseFailure(parser,
                VALID_LECTURE_NAME_L1 + " " + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NavCommand.MESSAGE_USAGE));
    }
}
