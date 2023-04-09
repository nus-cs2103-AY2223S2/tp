package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.entity.Character.CharacterBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Progression;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditValueCommand extends Command {

    public static final String MESSAGE_USAGE = "Edits the details of the entity identified "
            + "by classification and name. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: [FIELD] (must be an existing field) "
            + "[NEW_VALUE] "
            + "Example: name gilded staff";

    public static final String MESSAGE_EDIT_ENTITY_SUCCESS = "Edited Entity: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTITY = "This entity already exists in Reroll.";
    public static final String MESSAGE_INVALID_ENTITY_TYPE = "This entity type is invalid.";

    private final Entity entityToEdit;
    private final EditEntityDescriptor editEntityDescriptor;

    /**
     * @param entity               target entity to edit
     * @param editEntityDescriptor details to edit the person with
     */
    public EditValueCommand(Entity entity, EditEntityDescriptor editEntityDescriptor) {
        requireNonNull(entity);
        requireNonNull(editEntityDescriptor);

        this.entityToEdit = entity;
        this.editEntityDescriptor = editEntityDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entity editedEntity = createEditedEntity(entityToEdit, editEntityDescriptor);

        if (!entityToEdit.isSameEntity(editedEntity) && model.hasEntity(editedEntity)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTITY);
        }

        model.setEntity(entityToEdit, editedEntity);
        model.setCurrentSelectedEntity(editedEntity);

        model.updateFilteredEntityList(Model.PREDICATE_SHOW_ALL_ENTITIES);
        return new CommandResult(String.format(MESSAGE_EDIT_ENTITY_SUCCESS, editedEntity));
    }

    private static Entity createEditedEntity(Entity entityToEdit, EditEntityDescriptor editEntityDescriptor)
            throws CommandException {
        assert entityToEdit != null;

        if (entityToEdit instanceof Character) {
            return createEditedCharacter((Character) entityToEdit, (EditCharacterDescriptor) editEntityDescriptor);
        } else if (entityToEdit instanceof Mob) {
            return createEditedMob((Mob) entityToEdit, (EditMobDescriptor) editEntityDescriptor);
        } else if (entityToEdit instanceof Item) {
            return createEditedItem((Item) entityToEdit, (EditItemDescriptor) editEntityDescriptor);
        } else {
            throw new CommandException(MESSAGE_INVALID_ENTITY_TYPE);
        }
    }

    /**
     * Creates and returns a {@code Character} with the details of {@code charToEdit} edited with {@code
     * editCharDescriptor}.
     */
    private static Entity createEditedCharacter(Character charToEdit, EditCharacterDescriptor editCharacterDescriptor) {

        Name updatedName = editCharacterDescriptor.getName().orElse(charToEdit.getName());
        Stats updatedStats = editCharacterDescriptor.getStats().orElse(charToEdit.getStats());
        Progression updatedProgression = editCharacterDescriptor.getProgression().orElse(charToEdit.getProgression());
        Set<Tag> updatedTags = editCharacterDescriptor.getTags().orElse(charToEdit.getTags());
        Inventory updatedInventory = editCharacterDescriptor.getInventory().orElse(charToEdit.getInventory());

        CharacterBuilder builder = new CharacterBuilder(updatedName)
                .setStats(updatedStats)
                .setProgression(updatedProgression)
                .setInventory(updatedInventory)
                .setTags(updatedTags);

        return builder.build();
    }

    /**
     * Creates and returns a {@code Character} with the details of {@code charToEdit} edited with {@code
     * editCharDescriptor}.
     */
    private static Entity createEditedMob(Mob mobToEdit, EditMobDescriptor editMobDescriptor) {

        Name updatedName = editMobDescriptor.getName().orElse(mobToEdit.getName());
        Stats updatedStats = editMobDescriptor.getStats().orElse(mobToEdit.getStats());
        Float updatedChallengeRating = editMobDescriptor.getChallengeRating().orElse(mobToEdit.getChallengeRating());
        Boolean updatedIsLegendary = editMobDescriptor.getWeight().orElse(mobToEdit.getLegendaryStatus());
        Set<Tag> updatedTags = editMobDescriptor.getTags().orElse(mobToEdit.getTags());
        Inventory updatedInventory = editMobDescriptor.getInventory().orElse(mobToEdit.getInventory());

        return new Mob(updatedName, updatedStats, updatedChallengeRating, updatedIsLegendary,
                                                                    updatedInventory, updatedTags);
    }

    /**
     * Creates and returns a {@code Character} with the details of {@code charToEdit} edited with {@code
     * editCharDescriptor}.
     */
    private static Entity createEditedItem(Item charToEdit, EditItemDescriptor editItemDescriptor) {

        Name updatedName = editItemDescriptor.getName().orElse(charToEdit.getName());
        Integer updatedCost = editItemDescriptor.getCost().orElse(charToEdit.getCost());
        Float updatedWeight = editItemDescriptor.getWeight().orElse(charToEdit.getWeight());
        Set<Tag> updatedTags = editItemDescriptor.getTags().orElse(charToEdit.getTags());

        return new Item(updatedName, updatedCost, updatedWeight, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditValueCommand)) {
            return false;
        }

        // state check
        EditValueCommand e = (EditValueCommand) other;
        return entityToEdit.equals(e.entityToEdit)
                && editEntityDescriptor.equals(e.editEntityDescriptor);
    }

    /**
     * Stores the details to edit the entity with. Each non-empty field value will replace the corresponding field value
     * of the entity.
     */
    public static class EditEntityDescriptor {

        private Name name;
        private Set<Tag> tags;

        public EditEntityDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditEntityDescriptor(EditEntityDescriptor toCopy) {
            setName(toCopy.name);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if modification is
         * attempted. Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEntityDescriptor)) {
                return false;
            }

            // state check
            EditEntityDescriptor e = (EditEntityDescriptor) other;

            return getName().equals(e.getName())
                    && getTags().equals(e.getTags());
        }
    }

    /**
     * Stores the details to edit the character with. Each non-empty field value will replace the corresponding field
     * value of the character.
     */
    public static class EditCharacterDescriptor extends EditEntityDescriptor {

        private Stats stats;
        private Progression progression;
        private Inventory inventory;

        /**
         * Creates a character detail storage container to be edited.
         */
        public EditCharacterDescriptor() {
        }

        /**
         * Creates a character detail storage container from the provided data.
         */
        public EditCharacterDescriptor(EditCharacterDescriptor toCopy) {
            super(toCopy);
            setStats(toCopy.stats);
            setProgression(toCopy.progression);
            setInventory(toCopy.inventory);
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public Optional<Stats> getStats() {
            return Optional.ofNullable(stats);
        }

        public void setProgression(Progression progression) {
            this.progression = progression;
        }

        public Optional<Progression> getProgression() {
            return Optional.ofNullable(progression);
        }

        public Optional<Integer> getLevel() {
            return Optional.of(this.progression.getLevel());
        }

        public Optional<Integer> getXp() {
            return Optional.of(this.progression.getXP());
        }

        public Optional<Inventory> getInventory() {
            return Optional.ofNullable(inventory);
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }

        @Override
        public boolean equals(Object other) {
            // check base constraints
            if (!super.equals(other)) {
                return false;
            }

            if (!(other instanceof EditCharacterDescriptor)) {
                return false;
            }
            // character-specific state check
            EditCharacterDescriptor e = (EditCharacterDescriptor) other;

            return getXp().equals(e.getXp())
                    && getLevel().equals(e.getLevel());
        }
    }

    /**
     * Stores the details to edit the mob with. Each non-empty field value will replace the corresponding field value of
     * the mob.
     */
    public static class EditMobDescriptor extends EditEntityDescriptor {

        private Stats stats;
        private Float challengeRating;
        private Boolean isLegendary;
        private Inventory inventory;

        /**
         * Creates a Mob detail storage container to be edited.
         */
        public EditMobDescriptor() {
        }

        /**
         * Creates a Mob detail storage container from the provided data.
         */
        public EditMobDescriptor(EditMobDescriptor toCopy) {
            super(toCopy);
            setStats(toCopy.stats);
            setChallengeRating(toCopy.challengeRating);
            setIsLegendary(toCopy.isLegendary);
            setInventory(toCopy.inventory);
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public Optional<Stats> getStats() {
            return Optional.ofNullable(stats);
        }

        public void setChallengeRating(float challengeRating) {
            this.challengeRating = challengeRating;
        }

        public Optional<Float> getChallengeRating() {
            return Optional.ofNullable(challengeRating);
        }

        public void setIsLegendary(boolean isLegendary) {
            this.isLegendary = isLegendary;
        }

        public Optional<Boolean> getWeight() {
            return Optional.ofNullable(isLegendary);
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }

        public Optional<Inventory> getInventory() {
            return Optional.ofNullable(inventory);
        }

        @Override
        public boolean equals(Object other) {
            // check base constraints
            if (!super.equals(other)) {
                return false;
            }

            if (!(other instanceof EditMobDescriptor)) {
                return false;
            }
            // character-specific state check
            EditMobDescriptor e = (EditMobDescriptor) other;

            return getChallengeRating().equals(e.challengeRating)
                    && getWeight().equals(e.getWeight());
        }

    }

    /**
     * Stores the details to edit the mob with. Each non-empty field value will replace the corresponding field value of
     * the mob.
     */
    public static class EditItemDescriptor extends EditEntityDescriptor {

        private Integer cost;
        private Float weight;

        /**
         * Creates an Item detail storage container to be edited.
         */
        public EditItemDescriptor() {
        }

        /**
         * Creates an Item detail storage container from the provided data.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            super(toCopy);
            setCost(toCopy.cost);
            setWeight(toCopy.weight);
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public Optional<Integer> getCost() {
            return Optional.ofNullable(cost);
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public Optional<Float> getWeight() {
            return Optional.ofNullable(weight);
        }

        @Override
        public boolean equals(Object other) {
            // check base constraints
            if (!super.equals(other)) {
                return false;
            }

            if (!(other instanceof EditItemDescriptor)) {
                return false;
            }
            // character-specific state check
            EditItemDescriptor e = (EditItemDescriptor) other;

            return getCost().equals(e.getCost())
                    && getWeight().equals(e.getWeight());
        }
    }
}
