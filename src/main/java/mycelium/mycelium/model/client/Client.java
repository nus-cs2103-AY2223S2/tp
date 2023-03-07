package mycelium.mycelium.model.client;

import java.util.Date;
import java.util.Objects;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;

/**
 * Represents a client with their personal information, including their name, email address,
 * year of birth, source of information, and mobile phone number.
 * A client can be created with just a name and email or with all available information.
 * The name and email are required fields and cannot be null.
 */
public class Client {
    private final Name name;

    private final Email email;

    private final Date yearOfBirth;

    private final String source;

    private final Phone mobileNumber;

    /**
     * Creates a new Client object with the given name and email address.
     * @param name the name of the client.
     * @param email the email address of the client.
     */
    public Client(Name name, Email email) {
        this.name = name;
        this.email = email;
        this.yearOfBirth = null;
        this.source = null;
        this.mobileNumber = null;
    }

    /**
     * Creates a new Client object with the given personal information.
     * @param name the name of the client.
     * @param email the email address of the client.
     * @param yearOfBirth the year of birth of the client.
     * @param source the source of information about the client.
     */
    public Client(Name name, Email email, Date yearOfBirth, String source, Phone mobileNumber) {
        this.name = name;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.source = source;
        this.mobileNumber = mobileNumber;
    }

    /**
     * Returns the name of the client.
     * @return the name of the client.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the email address of the client.
     * @return the email address of the client.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the year of birth of the client.
     * @return the year of birth of the client.
     */
    public Date getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Returns the source where the developer found out about the client.
     * @return the source where the developer found out about the client.
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the mobile number of the client.
     * @return the mobile number of the client.
     */
    public Phone getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Compares this client to another client to check if they are the same client.
     * Two clients are considered the same if they have the same name.
     * @param otherClient  The client to compare with this client.
     * @return true if the two clients are the same client, false otherwise.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }
        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getYearOfBirth().equals(getYearOfBirth())
                && otherClient.getSource().equals(getSource())
                && otherClient.getMobileNumber().equals(getMobileNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, yearOfBirth, source, mobileNumber);
    }
}
