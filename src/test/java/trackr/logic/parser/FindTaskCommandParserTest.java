package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_STATUS_DESC;
import static trackr.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_2024;
import static trackr.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_2100;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.TASK_STATUS_DESC_DONE;
import static trackr.logic.commands.CommandTestUtil.TASK_STATUS_DESC_NOT_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2024;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_NOT_DONE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.FindTaskCommand;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskStatus;
import trackr.testutil.TaskPredicateBuilder;

public class FindTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE);
    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid task deadline
        assertParseFailure(parser, INVALID_TASK_DEADLINE_DESC, TaskDeadline.MESSAGE_CONSTRAINTS);

        // Invalid task status
        assertParseFailure(parser, INVALID_TASK_STATUS_DESC, TaskStatus.MESSAGE_CONSTRAINTS);

        // Invalid task deadline followed by valid task status
        assertParseFailure(
                parser,
                INVALID_TASK_DEADLINE_DESC + VALID_TASK_STATUS_DONE,
                TaskDeadline.MESSAGE_CONSTRAINTS
        );

        // Invalid task status followed by valid task name
        assertParseFailure(
                parser,
                INVALID_TASK_STATUS_DESC + INVALID_TASK_NAME_DESC,
                TaskStatus.MESSAGE_CONSTRAINTS
        );

        // valid value followed by invalid value.
        // The test case for invalid value followed by valid value
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}

        assertParseFailure(parser, TASK_DEADLINE_DESC_2100 + INVALID_TASK_DEADLINE_DESC,
                TaskDeadline.MESSAGE_CONSTRAINTS); //task deadline

        assertParseFailure(parser, TASK_STATUS_DESC_NOT_DONE + INVALID_TASK_STATUS_DESC,
                TaskStatus.MESSAGE_CONSTRAINTS); //task status

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + INVALID_TASK_DEADLINE_DESC + VALID_TASK_STATUS_DONE,
                TaskDeadline.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // no leading and trailing whitespaces
        String userInput = TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE;

        TaskContainsKeywordsPredicate predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(VALID_TASK_NAME_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
                .build();
        FindTaskCommand expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple whitespaces between keywords
        String userInputSpace =
                " " + PREFIX_NAME + "\n Sort \n \t Inventory  \t" + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE;
        assertParseSuccess(parser, userInputSpace, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        // name keywords and deadline
        String userInput = TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2100;

        TaskContainsKeywordsPredicate predicate =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(VALID_TASK_NAME_SORT_INVENTORY)
                        .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                        .build();
        FindTaskCommand expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name keywords and status
        userInput = TASK_NAME_DESC_BUY_FLOUR + TASK_STATUS_DESC_NOT_DONE;
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
                .build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline and status
        userInput = TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE;
        predicate = new TaskPredicateBuilder()
                .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
                .build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // task name keywords
        String userInput = TASK_NAME_DESC_SORT_INVENTORY;
        TaskContainsKeywordsPredicate predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(VALID_TASK_NAME_SORT_INVENTORY).build();
        FindTaskCommand expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task deadline
        userInput = TASK_DEADLINE_DESC_2024;
        predicate = new TaskPredicateBuilder().withTaskDeadline(VALID_TASK_DEADLINE_2024).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task status
        userInput = TASK_STATUS_DESC_NOT_DONE;
        predicate = new TaskPredicateBuilder().withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = TASK_NAME_DESC_SORT_INVENTORY
                + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE
                + TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_NOT_DONE + TASK_NAME_DESC_BUY_FLOUR
                + TASK_DEADLINE_DESC_2100 + TASK_STATUS_DESC_DONE;

        TaskContainsKeywordsPredicate predicate =
                new TaskPredicateBuilder()
                        .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR)
                        .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                        .withTaskStatus(VALID_TASK_STATUS_DONE)
                        .build();
        FindTaskCommand expectedCommand = new FindTaskCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        // invalid task name keywords followed by valid task name keywords
        String userInput = INVALID_TASK_NAME_DESC + TASK_NAME_DESC_BUY_FLOUR;
        TaskContainsKeywordsPredicate predicate =
                new TaskPredicateBuilder().withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR).build();
        FindTaskCommand expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task deadline followed by valid task deadline
        userInput = INVALID_TASK_DEADLINE_DESC + TASK_DEADLINE_DESC_2100;
        predicate = new TaskPredicateBuilder().withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task status followed by valid task status
        userInput = INVALID_TASK_STATUS_DESC + TASK_STATUS_DESC_NOT_DONE;
        predicate = new TaskPredicateBuilder().withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        // invalid task name keywords followed by valid task name keywords
        userInput = INVALID_TASK_NAME_DESC + TASK_STATUS_DESC_NOT_DONE
                + TASK_DEADLINE_DESC_2100 + TASK_NAME_DESC_BUY_FLOUR;
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
                .build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task deadline followed by valid task deadline
        userInput = TASK_NAME_DESC_BUY_FLOUR + INVALID_TASK_DEADLINE_DESC
                + TASK_STATUS_DESC_NOT_DONE + TASK_DEADLINE_DESC_2100;
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task status followed by valid task status
        userInput = INVALID_TASK_STATUS_DESC + TASK_NAME_DESC_BUY_FLOUR
                + TASK_STATUS_DESC_NOT_DONE + TASK_DEADLINE_DESC_2100;
        predicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_differentInputOrder_success() {
        //task status, task deadline, task name
        String userInput = TASK_STATUS_DESC_DONE + TASK_DEADLINE_DESC_2100 + TASK_NAME_DESC_BUY_FLOUR;
        TaskContainsKeywordsPredicate predicate = new TaskPredicateBuilder()
                .withTaskStatus(VALID_TASK_STATUS_DONE)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR).build();
        FindTaskCommand expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        //task deadline, task status, task name
        userInput = TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE + TASK_NAME_DESC_BUY_FLOUR;
        predicate = new TaskPredicateBuilder()
                .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
                .withTaskNameKeywords(VALID_TASK_NAME_BUY_FLOUR).build();
        expectedCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
