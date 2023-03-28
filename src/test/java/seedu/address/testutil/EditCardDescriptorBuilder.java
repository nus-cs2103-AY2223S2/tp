package seedu.address.testutil;

import seedu.address.logic.commands.cardcommands.EditCardCommand;
import seedu.address.logic.commands.cardcommands.EditCardCommand.EditCardDescriptor;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.Tag.TagName;


/**
 * A utility class to help with building EditCardDescriptor objects.
 */
public class EditCardDescriptorBuilder {

    private EditCardCommand.EditCardDescriptor descriptor;

    public EditCardDescriptorBuilder() {
        descriptor = new EditCardCommand.EditCardDescriptor();
    }

    public EditCardDescriptorBuilder(EditCardCommand.EditCardDescriptor descriptor) {
        this.descriptor = new EditCardCommand.EditCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCardDescriptor} with fields containing {@code card}'s details
     */
    public EditCardDescriptorBuilder(Card card) {
        descriptor = new EditCardDescriptor();
        descriptor.setQuestion(card.getQuestion());
        descriptor.setAnswer(card.getAnswer());
        descriptor.setTag(card.getTag());
    }

    /**
     * Sets the {@code Question} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new Question(question));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withTag(String tagName) {
        descriptor.setTag(new Tag(TagName.valueOf(tagName.toUpperCase())));
        return this;
    }

    /**
     * Sets the {@code Deck} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withDeck(Deck deck) {
        descriptor.setDeck(deck);
        return this;
    }

    public EditCardCommand.EditCardDescriptor build() {
        return descriptor;
    }
}
