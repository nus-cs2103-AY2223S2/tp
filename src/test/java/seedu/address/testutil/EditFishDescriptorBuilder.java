package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditFishDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

/**
 * A utility class to help with building EditFishDescriptor objects.
 */
public class EditFishDescriptorBuilder {

    private EditFishDescriptor descriptor;

    public EditFishDescriptorBuilder() {
        descriptor = new EditFishDescriptor();
    }

    public EditFishDescriptorBuilder(EditFishDescriptor descriptor) {
        this.descriptor = new EditFishDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFishDescriptor} with fields containing {@code fish}'s details
     */
    public EditFishDescriptorBuilder(Fish fish) {
        descriptor = new EditFishDescriptor();
        descriptor.setName(fish.getName());
        descriptor.setLastFedDate(fish.getLastFedDate());
        descriptor.setSpecies(fish.getSpecies());
        descriptor.setFeedingInterval(fish.getFeedingInterval());
        descriptor.setTank(fish.getTank());
        descriptor.setTags(fish.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditFishDescriptor} that we are building.
     */
    public EditFishDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code LastFedDate} of the {@code EditFishDescriptor} that we are building.
     */
    public EditFishDescriptorBuilder withLastFedDate(String lastFedDate) {
        descriptor.setLastFedDate(new LastFedDate(lastFedDate));
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code EditFishDescriptor} that we are building.
     */
    public EditFishDescriptorBuilder withSpecies(String species) {
        descriptor.setSpecies(new Species(species));
        return this;
    }

    /**
     * Sets the {@code FeedingInterval} of the {@code EditFishDescriptor} that we are building.
     */
    public EditFishDescriptorBuilder withFeedingInterval(String feedingInterval) {
        descriptor.setFeedingInterval(new FeedingInterval(feedingInterval));
        return this;
    }

    /**
     * Sets the {@code Tank} of the {@code EditFishDescriptor} that we are building.
     */
    public EditFishDescriptorBuilder withTank(String tank) {
        descriptor.setTank(new Tank(new TankName(tank), new AddressBook()));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFishDescriptor}
     * that we are building.
     */
    public EditFishDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditFishDescriptor build() {
        return descriptor;
    }
}
