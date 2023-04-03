package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.logic.commands.project.FindProjectCommand;
import arb.model.project.Project;
import arb.model.project.predicates.IsOfStatusPredicate;
import arb.model.project.predicates.LinkedClientNameContainsKeywordsPredicate;
import arb.model.project.predicates.ProjectContainsTagsPredicate;
import arb.model.project.predicates.ProjectWithinTimeframePredicate;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;
import arb.testutil.PredicateUtil;

public class FindProjectCommandParserTest {

    private FindProjectCommandParser parser = new FindProjectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void parse_validArgs_returnsFindProjectCommand() {
        ProjectContainsTagsPredicate expectedTagsPredicate = PredicateUtil.getProjectContainsTagsPredicate("painting");
        TitleContainsKeywordsPredicate expectedTitlesPredicate =
                PredicateUtil.getTitleContainsKeywordsPredicate("Sky", "Painting");
        IsOfStatusPredicate expectedStatusPredicate = PredicateUtil.getIsOfStatusPredicate(false);
        ProjectWithinTimeframePredicate expectedTimeframePredicate =
                PredicateUtil.getProjectWithinTimeframePredicate(Optional.of("3pm 2023-01-01"), Optional.empty());
        LinkedClientNameContainsKeywordsPredicate expectedClientPredicate =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("Alice", "Wheeler");
        CombinedPredicate<Project> expectedCombinedPredicate =
                PredicateUtil.getCombinedPredicate(expectedTagsPredicate, expectedTitlesPredicate,
                expectedStatusPredicate, expectedTimeframePredicate, expectedClientPredicate);

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
