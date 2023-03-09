package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Customer in the address book.
 */
public class Customer extends Person {
    private final String notes;
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param notes
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags, String notes) {
        super(name, phone, email, address, tags);
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

    /**
     * Allows delivery man to edit notes on customer.
     *
     * @param newNotes edited notes.
     * @return Customer new customer with edited notes.
     */
    public Customer editNotes(String newNotes) {
        return new Customer(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(),
                newNotes);
    }
}
