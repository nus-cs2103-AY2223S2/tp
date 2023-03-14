package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.module.ModuleLectureContainsKeywordsPredicate;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // Empty args
        assertParseSuccess(parser, "     ", new ListCommand());

        // Module code is present
        assertParseSuccess(parser, MODULE_CODE_DESC_2103, new ListCommand(
            new ModuleContainsKeywordsPredicate(VALID_MODULE_CODE_2103)));

        // Module code and lecture name is present
        assertParseSuccess(parser, MODULE_CODE_DESC_2103 + LECTURE_NAME_DESC_L1,
            new ListCommand(
                new ModuleLectureContainsKeywordsPredicate(VALID_MODULE_CODE_2103, VALID_LECTURE_NAME_L1)));
    }

}
