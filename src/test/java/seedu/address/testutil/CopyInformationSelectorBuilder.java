package seedu.address.testutil;

import seedu.address.logic.commands.CopyCommand.CopyInformationSelector;

/**
 * A utility class to help with building CopyInformationSelector objects.
 */
public class CopyInformationSelectorBuilder {

    private final CopyInformationSelector copyInformationSelector;

    /**
     * Creates a new CopyInformationSelector with no fields selected to copy.
     */
    public CopyInformationSelectorBuilder() {
        this.copyInformationSelector = new CopyInformationSelector();
        this.copyInformationSelector.copyAllInformation(false);
    }

    /**
     * Select all fields of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectAll() {
        this.copyInformationSelector.copyAllInformation(true);
        return this;
    }

    /**
     * Selects {@code Name} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectName() {
        this.copyInformationSelector.copyName(true);
        return this;
    }

    /**
     * Selects {@code Phone} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectPhone() {
        this.copyInformationSelector.copyPhone(true);
        return this;
    }

    /**
     * Selects {@code Email} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectEmail() {
        this.copyInformationSelector.copyEmail(true);
        return this;
    }

    /**
     * Selects {@code Address} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectAddress() {
        this.copyInformationSelector.copyAddress(true);
        return this;
    }

    /**
     * Selects {@code Rank} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectRank() {
        this.copyInformationSelector.copyRank(true);
        return this;
    }

    /**
     * Selects {@code Unit} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectUnit() {
        this.copyInformationSelector.copyUnit(true);
        return this;
    }

    /**
     * Selects {@code Company} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectCompany() {
        this.copyInformationSelector.copyCompany(true);
        return this;
    }

    /**
     * Selects {@code Platoon} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectPlatoon() {
        this.copyInformationSelector.copyPlatoon(true);
        return this;
    }

    /**
     * Selects {@code Tags} of the {@code CopyInformationSelector} that we are building.
     */
    public CopyInformationSelectorBuilder selectTags() {
        this.copyInformationSelector.copyTags(true);
        return this;
    }

    public CopyInformationSelector build() {
        return copyInformationSelector;
    }
}
