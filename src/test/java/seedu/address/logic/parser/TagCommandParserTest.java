package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_tagVideoCommand_success() {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        //System.out.println(NAME_DESC_AMY);

        //assertParseSuccess(parser, VALID_MODULE_CODE + TAG_DESC_MODULE, new TagCommand(VALID_TAG_SET, expectedModuleCode));


    }
}