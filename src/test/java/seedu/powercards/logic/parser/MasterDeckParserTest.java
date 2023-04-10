package seedu.powercards.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.ClearCommand;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.HelpCommand;
import seedu.powercards.logic.commands.cardcommands.ShowCardsCommand;
import seedu.powercards.logic.commands.deckcommands.ShowDecksCommand;
import seedu.powercards.logic.commands.deckcommands.UnselectDeckCommand;
import seedu.powercards.logic.commands.reviewcommands.EndReviewCommand;
import seedu.powercards.logic.commands.reviewcommands.FlipCardCommand;
import seedu.powercards.logic.commands.reviewcommands.NextCardCommand;
import seedu.powercards.logic.commands.reviewcommands.PreviousCardCommand;
import seedu.powercards.logic.commands.reviewcommands.TagEasyCommand;
import seedu.powercards.logic.commands.reviewcommands.TagHardCommand;
import seedu.powercards.logic.commands.reviewcommands.TagMediumCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

public class MasterDeckParserTest {

    private final MasterDeckParser parser = new MasterDeckParser();

    @Test
    public void parseCommandInMainUnselectedMode_validInput_returnsCommand() throws Exception {
        Command command = parser.parseCommandInMainUnselectedMode("addDeck Science");
        assertEquals(new AddDeckCommandParser().parse("Science"), command);

        command = parser.parseCommandInMainUnselectedMode("deleteDeck 1");
        assertEquals(new DeleteDeckCommandParser().parse("1"), command);

        command = parser.parseCommandInMainUnselectedMode("editDeck 1 NewDeckName");
        assertEquals(new EditDeckCommandParser().parse("1 NewDeckName"), command);

        command = parser.parseCommandInMainUnselectedMode("selectDeck 1");
        assertEquals(new SelectDeckCommandParser().parse("1"), command);

        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("unselectDeck"));
        assertEquals(new ShowDecksCommand(), parser.parseCommandInMainUnselectedMode("showDecks"));
        assertEquals(new FindDecksCommandParser().parse("science"),
                parser.parseCommandInMainUnselectedMode("findDecks science"));

        assertThrows(ParseException.class, ()
                -> parser.parseCommandInMainUnselectedMode("addCard q\\Question q\\Answer"));
        assertThrows(ParseException.class, ()
                -> parser.parseCommandInMainUnselectedMode("editCard 1 q\\NewQuestion"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("deleteCard 1"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("showCards"));
        assertThrows(ParseException.class, () ->
                parser.parseCommandInMainUnselectedMode("findCards Question"));

        assertEquals(new ReviewCommandParser().parse("1"),
                parser.parseCommandInMainUnselectedMode("review 1"));
        assertEquals(new SetReviewLimitCommandParser().parse("5"),
                parser.parseCommandInMainUnselectedMode("setLimit 5"));

        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("p"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("l"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("["));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("]"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode(";"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("'"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainUnselectedMode("endReview"));

        assertEquals(new HelpCommand(), parser.parseCommandInMainUnselectedMode("help"));
        assertEquals(new ClearCommand(), parser.parseCommandInMainUnselectedMode("clear"));
    }

    @Test
    public void parseCommandInMainSelectedMode_validInput_returnsCommand() throws Exception {
        Command command = parser.parseCommandInMainSelectedMode("addCard q\\Question a\\Answer");
        assertEquals(new AddCardCommandParser().parse(" q\\Question a\\Answer"), command);

        command = parser.parseCommandInMainSelectedMode("deleteCard 1");
        assertEquals(new DeleteCardCommandParser().parse("1"), command);

        command = parser.parseCommandInMainSelectedMode("editCard 1 q\\NewQuestion a\\NewAnswer");
        assertEquals(new EditCardCommandParser().parse("1 q\\NewQuestion a\\NewAnswer"), command);

        command = parser.parseCommandInMainSelectedMode("showCards");
        assertEquals(new ShowCardsCommand(), command);

        command = parser.parseCommandInMainSelectedMode("findCards Question");
        assertEquals(new FindCardsCommandParser().parse("Question"), command);

        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("addDeck Science"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("deleteDeck 1"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("editDeck 1 NewDeckName"));
        assertEquals(new SelectDeckCommandParser().parse("1"),
                parser.parseCommandInMainSelectedMode("selectDeck 1"));
        assertEquals(new UnselectDeckCommand(), parser.parseCommandInMainSelectedMode("unselectDeck"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("showDecks"));
        assertThrows(ParseException.class, ()
                -> parser.parseCommandInMainSelectedMode("findDecks science"));
        assertEquals(new ReviewCommandParser().parse("1"),
                parser.parseCommandInMainSelectedMode("review 1"));
        assertEquals(new SetReviewLimitCommandParser().parse("5"),
                parser.parseCommandInMainSelectedMode("setLimit 5"));

        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("p"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("l"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("["));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("]"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode(";"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("'"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("endReview"));

        assertEquals(new HelpCommand(), parser.parseCommandInMainSelectedMode("help"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("clear"));
    }

    @Test
    public void parseCommandInReviewMode_validInput_returnsCommand() throws Exception {
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("addDeck Science"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("deleteDeck 1"));
        assertThrows(ParseException.class, ()
                -> parser.parseCommandInReviewMode("editDeck 1 NewDeckName"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("selectDeck 1"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("unselectDeck"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("showDecks"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("findDecks science"));

        assertThrows(ParseException.class, ()
                -> parser.parseCommandInReviewMode("addCard q\\Question a\\Answer"));
        assertThrows(ParseException.class, ()
                -> parser.parseCommandInReviewMode("editCard 1 q\\NewQuestion"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("deleteCard 1"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("showCards"));
        assertThrows(ParseException.class, () -> parser.parseCommandInReviewMode("findCards Question"));

        assertEquals(new EndReviewCommand(), parser.parseCommandInReviewMode("endReview"));

        assertEquals(new FlipCardCommand(), parser.parseCommandInReviewMode("p"));
        assertEquals(new TagEasyCommand(), parser.parseCommandInReviewMode("l"));
        assertEquals(new PreviousCardCommand(), parser.parseCommandInReviewMode("["));
        assertEquals(new NextCardCommand(), parser.parseCommandInReviewMode("]"));
        assertEquals(new TagMediumCommand(), parser.parseCommandInReviewMode(";"));
        assertEquals(new TagHardCommand(), parser.parseCommandInReviewMode("'"));

        assertEquals(new HelpCommand(), parser.parseCommandInReviewMode("help"));
        assertThrows(ParseException.class, () -> parser.parseCommandInMainSelectedMode("clear"));
    }

}
