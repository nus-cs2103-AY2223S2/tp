package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand.AddCardDescriptor;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;

public class AddCardDescriptorBuilder {

    private AddCardDescriptor descriptor;

    public AddCardDescriptorBuilder() {
        descriptor = new AddCardDescriptor();
    }

    public AddCardDescriptorBuilder(AddCardDescriptor descriptor) {
        this.descriptor = new AddCardDescriptor(descriptor);
    }

    public AddCardDescriptorBuilder(Card card) {
        descriptor = new AddCardDescriptor();
        descriptor.setQuestion(card.getQuestion());
        descriptor.setAnswer(card.getAnswer());
        descriptor.setTags(card.getTags());
        descriptor.setDeck(card.getDeck());
    }

    public AddCardDescriptorBuilder withQuestion(String questions) {
        descriptor.setQuestion(new Question(questions));
        return this;
    }

    public AddCardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code AddCardDescriptor}
     * that we are building.
     */
    public AddCardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public AddCardDescriptor build() {
        if (descriptor.getTags().isEmpty()) {
            descriptor.setTags(new HashSet<>()); // tags cannot be empty
        };
        return descriptor;
    }

}
