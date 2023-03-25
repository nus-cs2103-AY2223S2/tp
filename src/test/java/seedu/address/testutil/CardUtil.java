package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.cardcommands.AddCommand;
import seedu.address.logic.commands.cardcommands.EditCommand.EditCardDescriptor;
import seedu.address.model.card.Card;

/**
 * A utility class for Card.
 */
public class CardUtil {

    /**
     * Returns an add command string for adding the {@code card}.
     */
    public static String getAddCommand(Card card) {
        return AddCommand.COMMAND_WORD + " " + getCardDetails(card);
    }

    /**
     * Returns the part of command string for the given {@code card}'s details.
     */
    public static String getCardDetails(Card card) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + card.getQuestion().question + " ");
        sb.append(PREFIX_ANSWER + card.getAnswer().answer + " ");
        sb.append(PREFIX_TAG + card.getTagName() + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCardDescriptor}'s details.
     */
    public static String getEditCardDescriptorDetails(EditCardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getQuestion().ifPresent(question -> sb.append(PREFIX_QUESTION)
                .append(question.question).append(" "));
        descriptor.getAnswer().ifPresent(answer -> sb.append(PREFIX_ANSWER).append(answer.answer).append(" "));
        descriptor.getTag().ifPresent(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
        return sb.toString();
    }
}
