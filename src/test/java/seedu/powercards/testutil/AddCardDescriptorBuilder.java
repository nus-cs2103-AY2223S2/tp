package seedu.powercards.testutil;

import seedu.powercards.logic.commands.cardcommands.AddCardCommand.AddCardDescriptor;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.model.tag.Tag.TagName;

/**
 * A utility class to help with building AddCardDescriptor objects.
 */
public class AddCardDescriptorBuilder {

    private AddCardDescriptor descriptor;

    public AddCardDescriptorBuilder() {
        descriptor = new AddCardDescriptor();
    }

    public AddCardDescriptorBuilder(AddCardDescriptor descriptor) {
        this.descriptor = new AddCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddCardDescriptor} with fields containing {@code card}'s details
     */
    public AddCardDescriptorBuilder(Card card) {
        descriptor = new AddCardDescriptor();
        descriptor.setQuestion(card.getQuestion());
        descriptor.setAnswer(card.getAnswer());
        descriptor.setTag(card.getTag());
        descriptor.setDeck(card.getDeck());
    }

    /**
     * Sets the {@code Question} of the {@code AddCardDescriptor} that we are building.
     */
    public AddCardDescriptorBuilder withQuestion(String questions) {
        descriptor.setQuestion(new Question(questions));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code AddCardDescriptor} that we are building.
     */
    public AddCardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Parses the {@code tag} into a {@code Tag} and set it to the {@code AddCardDescriptor}
     * that we are building.
     */
    public AddCardDescriptorBuilder withTag(String tag) {
        descriptor.setTag(new Tag(TagName.valueOf(tag.toUpperCase())));
        return this;
    }

    /**
     * Returns a AddCardDescriptor instance
     *
     * @return the AddCardDescriptor instance
     */
    public AddCardDescriptor build() {
        return descriptor;
    }

}
