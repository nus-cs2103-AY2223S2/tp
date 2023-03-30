package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.project.FindProjectCommand;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.predicates.IsOfStatusPredicate;
import arb.model.project.predicates.LinkedClientNameContainsKeywordsPredicate;
import arb.model.project.predicates.ProjectContainsTagsPredicate;
import arb.model.project.predicates.ProjectWithinTimeframePredicate;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;

public class FindProjectCommandParserTest {

    private FindProjectCommandParser parser = new FindProjectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindProjectCommand() {
        List<String> expectedTags = Arrays.asList("painting");
        List<String> expectedTitles = Arrays.asList("Sky", "Painting");
        List<String> expectedClientNames = Arrays.asList("Alice", "Wheeler");
        ProjectContainsTagsPredicate expectedTagsPredicate = new ProjectContainsTagsPredicate(expectedTags);
        TitleContainsKeywordsPredicate expectedTitlesPredicate = new TitleContainsKeywordsPredicate(expectedTitles);
        IsOfStatusPredicate expectedStatusPredicate = new IsOfStatusPredicate(new Status(false));
        ProjectWithinTimeframePredicate expectedTimeframePredicate =
                new ProjectWithinTimeframePredicate(new Deadline("3pm 2023-01-01"), null);
        LinkedClientNameContainsKeywordsPredicate expectedClientPredicate =
                new LinkedClientNameContainsKeywordsPredicate(expectedClientNames);
        CombinedPredicate<Project> expectedCombinedPredicate =
                new CombinedPredicate<>(Arrays.asList(
                expectedTagsPredicate, expectedTitlesPredicate, expectedStatusPredicate, expectedTimeframePredicate,
                expectedClientPredicate));

        FindProjectCommand expectedFindProjectCommand =
                new FindProjectCommand(expectedCombinedPredicate);
        assertParseSuccess(parser, " n/Sky Painting st/not done t/painting s/3pm 2023-01-01 c/Alice Wheeler",
                expectedFindProjectCommand);
        // With whitespace
        assertParseSuccess(parser, " \n n/Sky Painting \n \t st/not done \t t/painting  "
                        + "\t s/3pm 2023-01-01 \n c/Alice Wheeler \n",
                expectedFindProjectCommand);
    }

}
