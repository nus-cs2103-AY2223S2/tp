package seedu.address.logic.parser.navigation;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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
    private ModuleCode modCode = TypicalModules.getCs2040s().getCode();
    private LectureName lecName = new LectureName(VALID_LECTURE_NAME_L1);

    @Test
    public void parse_navRoot_success() throws ParseException {
        assertParseSuccess(parser, "", new RootNavCommand());
    }

    @Test
    public void parse_navRootWithRootPrefix_success() throws ParseException {
        assertParseSuccess(parser, PREFIX_ROOT.toString(), new RootNavCommand());
    }

    @Test
    public void parse_navRootWithRootPrefixExtraSpace_success() throws ParseException {
        assertParseSuccess(parser, " " + PREFIX_ROOT.toString() + " ", new RootNavCommand());
    }

    @Test
    public void parse_navDirectToMod_success() throws ParseException {
        Optional<ModuleCode> modOpt = Optional.of(modCode);
        Command expectedCmd = new DirectNavCommand(modOpt, Optional.empty());

        assertParseSuccess(parser, MODULE_CODE_DESC_2040, expectedCmd);
    }

    public void parse_navDirectToModWithInvalidMod_failure() throws ParseException {
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_navDirectToLec_success() throws ParseException {
        Optional<ModuleCode> modOpt = Optional.of(modCode);
        Optional<LectureName> lecOpt = Optional.of(lecName);
        Command expectedCmd = new DirectNavCommand(modOpt, lecOpt);

        assertParseSuccess(parser, MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1, expectedCmd);
    }

    @Test
    public void parse_navDirecttoLecWithInvalidLec_failure() throws ParseException {
        assertParseFailure(parser, MODULE_CODE_DESC_2040 + " " + INVALID_LECTURE_NAME_DESC,
                LectureName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_navDirecttoLecWithMissingMod_failure() throws ParseException {
        assertParseFailure(parser, LECTURE_NAME_DESC_L1, getInvalidCommandMessage());
    }

    @Test
    public void parse_navRelative_success() throws ParseException {
        Command expectedCmd = new RelativeNavCommand(VALID_LECTURE_NAME_L1);
        assertParseSuccess(parser, VALID_LECTURE_NAME_L1, expectedCmd);
    }

    @Test
    public void parse_bothRelativeAndDirect_failure() {
        assertParseFailure(parser, VALID_LECTURE_NAME_L1 + " " + MODULE_CODE_DESC_2040 + LECTURE_NAME_DESC_L1,
                getInvalidCommandMessage());
    }

    private String getInvalidCommandMessage() {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, NavCommand.MESSAGE_USAGE);
    }
}
