package arb.model.client;

import static arb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import arb.model.project.Project;
import arb.model.project.UniqueProjectList;
import arb.model.tag.Tag;
import javafx.collections.ObservableList;

/**
 * Represents a Client in the address book.
 * Guarantees: name and tags are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    private final UniqueProjectList linkedProjects;

    /**
     * Name and tags must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.tags.addAll(tags);
        this.linkedProjects = new UniqueProjectList();
    }

    public Name getName() {
        return this.name;
    }

    /**
     * Returns true if this client has a phone.
     */
    public boolean isPhonePresent() {
        return this.phone.isPresent();
    }

    public Phone getPhone() {
        return this.phone.orElse(null);
    }

    /**
     * Returns true if this client has an email.
     */
    public boolean isEmailPresent() {
        return this.email.isPresent();
    }

    public Email getEmail() {
        return this.email.orElse(null);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Links {@code project} to this client.
     */
    public void linkProject(Project project) {
        if (!linkedProjects.contains(project)) {
            linkedProjects.add(project);
        }
    }

    /**
     * Unlinks {@code project} from this client.
     */
    public void unlinkProject(Project project) {
        assert linkedProjects.contains(project) : getName() + ": " + linkedProjects.toString();
        linkedProjects.remove(project);
    }

    /**
     * Unlinks all linked projects from this client.
     */
    public void unlinkAllProjects() {
        linkedProjects.setProjects(new UniqueProjectList());
    }

    public int getNumberOfProjectsLinked() {
        return linkedProjects.asUnmodifiableObservableList().size();
    }

    public ObservableList<Project> getLinkedProjects() {
        return linkedProjects.asUnmodifiableObservableList();
    }
    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
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
                && otherClient.phone.equals(phone)
                && otherClient.email.equals(email)
                && otherClient.getTags().equals(getTags())
                && otherClient.linkedProjects.equals(linkedProjects);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags, linkedProjects);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        if (isPhonePresent()) {
            builder.append("; Phone: ").append(getPhone());
        }

        if (isEmailPresent()) {
            builder.append("; Email: ").append(getEmail());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
