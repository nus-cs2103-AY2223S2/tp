package seedu.address.testutil;

import java.util.Arrays;

import seedu.address.logic.commands.FilterCommand.FilterDescriptor;

/**
 * A utility class to help with building {@code FilterDescriptor} objects.
 */
public class FilterDescriptorBuilder {

    private final FilterDescriptor descriptor;

    public FilterDescriptorBuilder() {
        descriptor = new FilterDescriptor();
    }

    /**
     * Creates a copy of the given {@code FilterDescriptor} with identical fields
     */
    public FilterDescriptorBuilder(FilterDescriptor descriptor) {
        this.descriptor = new FilterDescriptor();
        this.descriptor.setNameValue(descriptor.getNameValue());
        this.descriptor.setPhoneValue(descriptor.getPhoneValue());
        this.descriptor.setEmailValue(descriptor.getEmailValue());
        this.descriptor.setAddressValue(descriptor.getAddressValue());
        this.descriptor.setRankValue(descriptor.getRankValue());
        this.descriptor.setUnitValue(descriptor.getUnitValue());
        this.descriptor.setCompanyValue(descriptor.getCompanyValue());
        this.descriptor.setPlatoonValue(descriptor.getPlatoonValue());
        this.descriptor.setTagValues(descriptor.getTagValues());
    }

    /**
     * Sets the name of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withName(String name) {
        descriptor.setNameValue(name);
        return this;
    }

    /**
     * Sets the phone of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withPhone(String phone) {
        descriptor.setPhoneValue(phone);
        return this;
    }

    /**
     * Sets the email of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withEmail(String email) {
        descriptor.setEmailValue(email);
        return this;
    }

    /**
     * Sets the address of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withAddress(String address) {
        descriptor.setAddressValue(address);
        return this;
    }

    /**
     * Sets the rank of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withRank(String rank) {
        descriptor.setRankValue(rank);
        return this;
    }

    /**
     * Sets the unit of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withUnit(String unit) {
        descriptor.setUnitValue(unit);
        return this;
    }

    /**
     * Sets the company of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withCompany(String company) {
        descriptor.setCompanyValue(company);
        return this;
    }

    /**
     * Sets the platoon of the {@code FilterDescriptor} that we are building.
     */
    public FilterDescriptorBuilder withPlatoon(String platoon) {
        descriptor.setPlatoonValue(platoon);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code FilterDescriptor}
     * that we are building.
     */
    public FilterDescriptorBuilder withTags(String... tags) {
        descriptor.setTagValues(Arrays.asList(tags));
        return this;
    }

    public FilterDescriptor build() {
        return descriptor;
    }
}
