package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.lesson.DeleteLessonCommand;
import seedu.address.model.student.NamePredicate;

public class DeleteLessonCommandParserTest {

    private DeleteLessonCommandParser parser = new DeleteLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        List<String> inputNames = new ArrayList<>(List.of("Alice"));
        NamePredicate namePredicate = new NamePredicate(inputNames);
        Index targetIndex = Index.fromOneBased(1);
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_INDEX + "1";
        DeleteLessonCommand expectedCommand = new DeleteLessonCommand(inputNames, namePredicate, targetIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingName_throwsParseException() {
        String userInput = " " + PREFIX_INDEX + "1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        String userInput = " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteLessonCommand.MESSAGE_USAGE));
    }
}
