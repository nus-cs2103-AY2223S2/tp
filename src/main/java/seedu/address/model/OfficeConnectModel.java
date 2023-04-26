package seedu.address.model;

import static seedu.address.logic.commands.UnassignCommand.MESSAGE_NON_EXIST_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


/**
 * The API of the OfficeConnectModel component.
 */
public class OfficeConnectModel {
    public static final Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    private final RepositoryModelManager<Task> taskModelManager;
    private final RepositoryModelManager<AssignTask> assignTaskModelManager;
    private final Model personModelManger;

    /**
     * Initializes a OfficeConnectModel empty data.
     */
    public OfficeConnectModel() {
        taskModelManager = new RepositoryModelManager<>(new Repository<Task>());
        assignTaskModelManager = new RepositoryModelManager<>(new Repository<AssignTask>());
        personModelManger = new ModelManager();
    }

    /**
     * Initializes a OfficeConnectModel given data.
     */
    public OfficeConnectModel(RepositoryModelManager<Task> taskModelManager,
                              RepositoryModelManager<AssignTask> assignTaskModelManager, Model personModelManger) {
        this.taskModelManager = taskModelManager;
        this.assignTaskModelManager = assignTaskModelManager;
        this.personModelManger = personModelManger;
        init();
    }

    /**
     * Initializes a OfficeConnectModel given old AddressBook model.
     */
    public OfficeConnectModel(Model model) {
        taskModelManager = new RepositoryModelManager<>(new Repository<Task>());
        assignTaskModelManager = new RepositoryModelManager<>(new Repository<AssignTask>());
        personModelManger = model;
    }

    private void init() {
        updateTaskToPersonsMapping();
        updatePersonToTasksMapping();
    }


    public RepositoryModelManager<Task> getTaskModelManager() {
        return taskModelManager;
    }

    public RepositoryModelManager<AssignTask> getAssignTaskModelManager() {
        return assignTaskModelManager;
    }

    private void updateTaskToPersonsMapping() {
        for (Task task : taskModelManager.getReadOnlyRepository().getData()) {
            setTaskToPersons(task);
        }
    }

    private void setTaskToPersons(Task task) {
        if (!taskModelManager.hasItem(task)) {
            return;
        }

        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getTaskId().equals(task.getId()));
        List<Person> persons = personModelManger.getAddressBook().getPersonList()
            .filtered(person -> assignTasks.stream().anyMatch(a -> a.getPersonId().equals(person.getId())));
        taskModelManager.setItem(task, Task.ofUpdatePeoples(task, persons));
    }

    private void setPersonToTasks(Person person) {
        if (!personModelManger.hasPerson(person)) {
            return;
        }
        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getPersonId().equals(person.getId()));

        List<Task> tasks = taskModelManager.getReadOnlyRepository().getData()
            .filtered(task -> assignTasks.stream().anyMatch(a -> a.getTaskId().equals(task.getId())));
        personModelManger.setPerson(person, Person.ofUpdateTasks(person, tasks));

    }

    private void updatePersonToTasksMapping() {
        for (Person person : personModelManger.getAddressBook().getPersonList()) {
            setPersonToTasks(person);
        }
    }

    /**
     * Checks if task model manager contains task.
     *
     * @param task task to be checked
     * @return true if task model manager contains the item
     */
    public boolean hasTaskModelManagerItem(Task task) {
        return taskModelManager.hasItem(task);
    }

    /**
     * Adds task to task model manager.
     *
     * @param task task to be added
     */
    public void addTaskModelManagerItem(Task task) {
        taskModelManager.addItem(task);
    }

    /**
     * Checks if assigntask model manager contains assignment.
     *
     * @param assignTask assignment to be checked
     * @return true if assigntask model manager contains the item
     */
    public boolean hasAssignTaskModelManagerItem(AssignTask assignTask) {
        return assignTaskModelManager.hasItem(assignTask);
    }

    /**
     * Adds a new item to the AssignTaskModelManager and updates the mappings between Persons and Tasks.
     *
     * @param assignTask the AssignTask to be added to the AssignTaskModelManager
     * @param person the Person associated with the AssignTask
     * @param task the Task associated with the AssignTask
     */
    public void addAssignTaskModelManagerItem(AssignTask assignTask, Person person, Task task) {
        assignTaskModelManager.addItem(assignTask);
        setPersonToTasks(person);
        setTaskToPersons(task);

    }

    /**
     * Updates filtered item list in task model manager.
     *
     * @param predicate predicate that determines if item should stay in the filtered item list
     */
    public void updateTaskModelManagerFilteredItemList(Predicate<Task> predicate) {
        taskModelManager.updateFilteredItemList(predicate);
    }

    public ObservableList<Task> getTaskModelManagerFilteredItemList() {
        return taskModelManager.getFilteredItemList();
    }

    /**
     * Edits the given task in task model manager.
     *
     * @param target     target task to be edited
     * @param editedTask edited task
     */
    public void setTask(Task target, Task editedTask) {
        taskModelManager.setItem(target, editedTask);
        updatePersonsOnTaskChanged(editedTask);
    }

    private void updatePersonsOnTaskChanged(Task task) {
        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getTaskId().equals(task.getId()));

        List<Person> persons = personModelManger.getAddressBook().getPersonList()
            .filtered(person -> assignTasks.stream().anyMatch(a -> a.getPersonId().equals(person.getId())));

        for (Person person : persons) {
            setPersonToTasks(person);
        }

    }

    private void updateTaskOnPersonChanged(Person person) {
        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getPersonId().equals(person.getId()));

        List<Task> tasks = taskModelManager.getReadOnlyRepository().getData()
            .filtered(task -> assignTasks.stream().anyMatch(a -> a.getTaskId().equals(task.getId())));

        for (Task task : tasks) {
            setTaskToPersons(task);
        }

    }


    /**
     * Deletes task from task model manager.
     *
     * @param task task to be deleted
     */
    public void deleteTask(Task task) throws CommandException {
        taskModelManager.deleteItem(task);
        List<Person> personList = getPersonList(task);
        for (Person person : personList) {
            deleteAssignment(person, task);
        }

    }

    //@@author cyiting
    private List<Person> getPersonList(Task task) {
        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getTaskId().equals(task.getId()));
        return new ArrayList<>(getAddressBook().getPersonList()
            .filtered(p -> assignTasks.stream().anyMatch(a -> a.getPersonId().equals(p.getId()))));
    }

    public ReadOnlyRepository<Task> getTaskModelManagerReadOnlyRepository() {
        return taskModelManager.getReadOnlyRepository();
    }

    /**
     * Focus onto a specific task.
     */
    public void focusTask(Task taskToFocus) {
        updateTaskModelManagerFilteredItemList(task -> task.getId().equals(taskToFocus.getId()));
        List<AssignTask> assignTasks = new ArrayList<>(getAssignTaskModelManager()
            .filter(assign -> assign.getTaskId().equals(taskToFocus.getId())));
        personModelManger.updateFilteredPersonList(person -> assignTasks.stream()
            .anyMatch(assign -> assign.getPersonId().equals(person.getId())));
    }

    /**
     * Deletes assignment from assign task model manager.
     *
     * @param assignTask assignment to be deleted
     */
    @Deprecated
    public void deleteAssignTaskModelManagerItem(AssignTask assignTask) {
        assignTaskModelManager.deleteItem(assignTask);
    }


    /**
     * Checks the given index is within the filter task list range
     *
     * @param index to check
     * @return true if within the range else false
     */
    public boolean isValidFilterTaskListIndexRange(int index) {
        return index >= 0 && index < taskModelManager.getFilteredItemList().size();
    }

    /**
     * Delete the task assignment from a person
     *
     * @param person person to unassign
     * @param task   task to unassign
     * @return task that has been deleted
     */
    public Task deleteAssignment(Person person, Task task) throws CommandException {
        AssignTask toDelete = new AssignTask(person, task);

        if (!assignTaskModelManager.hasItem(toDelete)) {
            throw new CommandException(MESSAGE_NON_EXIST_ASSIGNMENT);
        }
        assignTaskModelManager.deleteItem(toDelete);

        setTaskToPersons(task);
        setPersonToTasks(person);
        return task;
    }

    public boolean hasPerson(Person toAdd) {
        return personModelManger.hasPerson(toAdd);
    }


    public void addPerson(Person toAdd) {
        personModelManger.addPerson(toAdd);
    }

    public List<Person> getFilteredPersonList() {
        return personModelManger.getFilteredPersonList();
    }

    public ReadOnlyAddressBook getAddressBook() {
        return personModelManger.getAddressBook();
    }

    public void updateFilteredPersonList(Predicate<Person> predicate) {
        personModelManger.updateFilteredPersonList(predicate);
    }

    /**
     * Delete the person from personModelManger
     *
     * @param personToDelete person to remove
     */
    public void deletePerson(Person personToDelete) throws CommandException {
        personModelManger.deletePerson(personToDelete);
        List<Task> taskList = getTaskList(personToDelete);
        for (Task task : taskList) {
            deleteAssignment(personToDelete, task);
        }
    }

    public Model getPersonModelManager() {
        return personModelManger;
    }

    //@@author cyiting
    private List<Task> getTaskList(Person person) {
        List<AssignTask> assignTasks = assignTaskModelManager.filter(a -> a.getPersonId().equals(person.getId()));
        return new ArrayList<>(getTaskModelManager()
            .filterItemList(task -> assignTasks.stream().anyMatch(a -> a.getTaskId().equals(task.getId()))));
    }

    public void setPerson(Person personToEdit, Person editedPerson) {
        personModelManger.setPerson(personToEdit, editedPerson);
        updateTaskOnPersonChanged(editedPerson);
    }
}
