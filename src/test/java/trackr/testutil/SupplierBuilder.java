package trackr.testutil;

import java.util.HashSet;
import java.util.Set;

import trackr.model.commons.Tag;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;
import trackr.model.util.SampleDataUtil;

/**
 * A utility class to help with building Supplier objects.
 */
//@@author liumc-sg-reused
public class SupplierBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private PersonName personName;
    private PersonPhone personPhone;
    private PersonEmail personEmail;
    private PersonAddress personAddress;
    private Set<Tag> tags;

    /**
     * Creates a {@code SupplierBuilder} with the default details.
     */
    public SupplierBuilder() {
        personName = new PersonName(DEFAULT_NAME);
        personPhone = new PersonPhone(DEFAULT_PHONE);
        personEmail = new PersonEmail(DEFAULT_EMAIL);
        personAddress = new PersonAddress(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the SupplierBuilder with the data of {@code supplierToCopy}.
     */
    public SupplierBuilder(Supplier supplierToCopy) {
        personName = supplierToCopy.getPersonName();
        personPhone = supplierToCopy.getPersonPhone();
        personEmail = supplierToCopy.getPersonEmail();
        personAddress = supplierToCopy.getPersonAddress();
        tags = new HashSet<>(supplierToCopy.getPersonTags());
    }

    /**
     * Sets the {@code PersonName} of the {@code supplier} that we are building.
     */
    public SupplierBuilder withName(String personName) {
        this.personName = new PersonName(personName);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code supplier} that we are building.
     */
    public SupplierBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code supplier} that we are building.
     */
    public SupplierBuilder withAddress(String address) {
        this.personAddress = new PersonAddress(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code supplier} that we are building.
     */
    public SupplierBuilder withPhone(String phone) {
        this.personPhone = new PersonPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code supplier} that we are building.
     */
    public SupplierBuilder withEmail(String email) {
        this.personEmail = new PersonEmail(email);
        return this;
    }

    public Supplier build() {
        return new Supplier(personName, personPhone, personEmail, personAddress, tags);
    }

}
