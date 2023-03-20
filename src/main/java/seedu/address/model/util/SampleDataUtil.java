package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyRepository;
import seedu.address.model.Repository;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {


    private final Repository<Task> sampleTasksRepo;
    private final Repository<AssignTask> sampleAssignTaskRepo;

    /**
     * Constructor to create sample data.
     */
    public SampleDataUtil() {
        AddressBook samplePersonRepo = new AddressBook();
        sampleTasksRepo = new Repository<>();
        sampleAssignTaskRepo = new Repository<>();
        for (Task sampleTask : TypicalTasks.getTypicalTasks()) {
            sampleTasksRepo.addItem(sampleTask);
        }
        for (Person samplePerson : TypicalPersons.getTypicalPersons()) {
            samplePersonRepo.addPerson(samplePerson);
        }
        assert samplePersonRepo.getPersonList().size() > 3;
        assert sampleTasksRepo.getData().size() > 3;

        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(0).getId(),
            sampleTasksRepo.getData().get(0).getId()));
        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(0).getId(),
            sampleTasksRepo.getData().get(1).getId()));
        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(1).getId(),
            sampleTasksRepo.getData().get(1).getId()));
        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(1).getId(),
            sampleTasksRepo.getData().get(3).getId()));
        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(2).getId(),
            sampleTasksRepo.getData().get(0).getId()));
        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(2).getId(),
            sampleTasksRepo.getData().get(2).getId()));
        sampleAssignTaskRepo.addItem(new AssignTask(samplePersonRepo.getPersonList().get(2).getId(),
            sampleTasksRepo.getData().get(3).getId()));
    }

    public ReadOnlyRepository<Task> getSampleTasksRepo() {

        return sampleTasksRepo;
    }

    public ReadOnlyRepository<AssignTask> getSampleAssignTaskRepo() {

        return sampleAssignTaskRepo;
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : TypicalPersons.getTypicalPersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
