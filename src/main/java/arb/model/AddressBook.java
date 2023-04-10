package arb.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import arb.commons.core.LogsCenter;
import arb.model.client.Client;
import arb.model.client.Name;
import arb.model.client.UniqueClientList;
import arb.model.project.Project;
import arb.model.project.UniqueProjectList;
import arb.model.tag.TagMapping;
import arb.model.tag.UniqueTagMappingList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient, .isSameProject, .isSameTagMapping
 * comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private static final Logger logger = LogsCenter.getLogger(AddressBook.class);

    private final UniqueClientList clients;
    private final UniqueProjectList projects;
    private final UniqueTagMappingList tagMappings;

    private Optional<Project> projectToLink;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
        projects = new UniqueProjectList();
        tagMappings = new UniqueTagMappingList();
        projectToLink = Optional.empty();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Clients and Projects in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setProjects(projects);
    }

    /**
     * Replaces the contents of the tag mapping list with the client and project tags
     * of {@code clients} and {@code projects}.
     */
    public void setTagMappings(List<Client> clients, List<Project> projects) {
        this.tagMappings.setTagMappings(clients, projects);
    }

    /**
     * Removes all client tag mappings in the tag mapping list.
     */
    public void resetClientTagMappings() {
        this.tagMappings.resetClientTagMappings();
    }

    /**
     * Removes all project tag mappings in the tag mapping list.
     */
    public void resetProjectTagMappings() {
        this.tagMappings.resetProjectTagMappings();
    }

    /**
     * Unlinks all linked projects from clients in the client list.
     */
    public void resetClientLinkings() {
        logger.info("Resetting client links");
        this.clients.resetProjectLinkings();
    }

    /**
     * Unlinks all linked clients from projects in the project list.
     */
    public void resetProjectLinkings() {
        logger.info("Resetting project links");
        this.projects.resetClientLinkings();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setProjects(newData.getProjectList());
        setTagMappings(newData.getClientList(), newData.getProjectList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Returns true if a client with {@code clientName} exists in the client list.
     */
    public boolean hasClient(Name clientName) {
        requireNonNull(clientName);
        return clients.contains(clientName);
    }

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addClient(Client c) {
        clients.add(c);
        tagMappings.addClientTags(c);
    }

    /**
     * Adds a project to the address book.
     * The project must not already exist in the address book.
     */
    public void addProject(Project p) {
        projects.add(p);
        tagMappings.addProjectTags(p);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);
        projects.transferLinkedProjects(target, editedClient);
        tagMappings.editClientTags(target, editedClient);
        clients.setClient(target, editedClient);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the address book.
     * The project identity of {@code editedProject} must not be the same as another existing project
     * in the address book.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);
        tagMappings.editProjectTags(target, editedProject);
        clients.transferLinkedClients(target, editedProject);
        projects.setProject(target, editedProject);
    }

    /**
     * Removes {@code key} from the client list in this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
        projects.removeAllLinks(key);
        tagMappings.deleteClientTags(key);
    }

    /**
     * Removes {@code key} from the project list in this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeProject(Project key) {
        projects.remove(key);
        clients.unlinkClientFromProject(key);
        tagMappings.deleteProjectTags(key);
    }

    /**
     * Sets the {@code project} that should be linked to a client.
     */
    public void setProjectToLink(Project project) {
        requireNonNull(project);
        this.projectToLink = Optional.of(project);
    }

    /**
     * Resets {@code projectToLink}.
     */
    public void resetProjectToLink() {
        this.projectToLink = Optional.empty();
    }

    /**
     * Links {@code projectToLink} to {@code client}.
     */
    public void linkProjectToClient(Client client) {
        assert projectToLink.isPresent();
        clients.unlinkClientFromProject(projectToLink.get());
        projects.linkProjectToClient(projectToLink.get(), client);
        clients.linkClientToProject(client, projectToLink.get());
        logger.info("Linking project " + projectToLink.get() + " to client " + client);
        this.projectToLink = Optional.empty();
    }

    /**
     * Links the project {@code toLink} to the client with {@code clientName}.
     */
    public void linkProjectToClient(Name clientName, Project toLink) {
        clients.linkClientToProject(clientName, toLink);
    }

    /**
     * Unlinks the client linked to {@code project}.
     */
    public void unlinkClientFromProject(Project project) {
        clients.unlinkClientFromProject(project);
        projects.unlinkProjectFromClient(project);
    }

    /**
     * Returns the number of clients in the addressbook that fulfill {@code predicate}.
     */
    public int numberOfClientsMatchingPredicate(Predicate<Client> predicate) {
        return clients.numberOfClientsMatchingPredicate(predicate);
    }

    /**
     * Marks {@code project} as done.
     */
    public void markProjectAsDone(Project project) {
        projects.markProjectAsDone(project);
    }

    /**
     * Marks {@code project} as not done.
     */
    public void markProjectAsNotDone(Project project) {
        projects.markProjectAsNotDone(project);
    }

    public int noProjectSize() {
        return projects.noProjectSize();
    }

    public int noOverdueProjects() {
        return projects.noOverdueProjects();
    }

    public int noDoneProjects() {
        return projects.noDoneProjects();
    }

    public int noNotDoneProjects() {
        return projects.noNotDoneProjects();
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients, "
                + projects.asUnmodifiableObservableList().size() + " projects, "
                + tagMappings.asUnmodifiableObservableList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TagMapping> getTagMappingList() {
        return tagMappings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients)
                && projects.equals(((AddressBook) other).projects)
                && tagMappings.equals(((AddressBook) other).tagMappings)
                && projectToLink.equals(((AddressBook) other).projectToLink));
    }

    @Override
    public int hashCode() {
        return Objects.hash(clients, projects, tagMappings, projectToLink);
    }
}
