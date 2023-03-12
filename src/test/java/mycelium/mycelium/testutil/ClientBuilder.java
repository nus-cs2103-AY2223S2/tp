package mycelium.mycelium.testutil;

import java.util.Optional;

import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "John Doe";
    public static final String DEFAULT_EMAIL = "John@gmail.com";
    public static final String DEFAULT_YEAR_OF_BIRTH = "2023";
    public static final String DEFAULT_SOURCE = "whited.com";
    public static final String DEFAULT_MOBILE_NUMBER = "999";

    private Name name;

    private Email email;

    private YearOfBirth yearOfBirth;

    private String source;

    private Phone mobileNumber;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        yearOfBirth = new YearOfBirth(DEFAULT_YEAR_OF_BIRTH);
        source = DEFAULT_SOURCE;
        mobileNumber = new Phone(DEFAULT_MOBILE_NUMBER);
    }

    /**
     * Initializes the ClientBuilder with the data of {@code client}.
     */
    public ClientBuilder(Client client) {
        name = client.getName();
        email = client.getEmail();
        yearOfBirth = client.getYearOfBirth().orElse(null);
        source = client.getSource().orElse(null);
        mobileNumber = client.getMobileNumber().orElse(null);
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code YearOfBirth} of the {@code Client} that we are building.
     */
    public ClientBuilder withYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = new YearOfBirth(yearOfBirth);
        return this;
    }

    /**
     * Sets the client's source.
     */
    public ClientBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withMobileNumber(String phone) {
        this.mobileNumber = new Phone(phone);
        return this;
    }

    /**
     *  Builds the {@code Client} with the given fields.
     * @return a {@code Client} with the given fields.
     */
    public Client build() {
        return new Client(name, email, Optional.ofNullable(yearOfBirth),
                Optional.ofNullable(source), Optional.ofNullable(mobileNumber));
    }

}
