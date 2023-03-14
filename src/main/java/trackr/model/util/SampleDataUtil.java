package trackr.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import trackr.model.AddressBook;
import trackr.model.OrderList;
import trackr.model.ReadOnlyAddressBook;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.TaskList;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.Customer;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;
import trackr.model.person.Address;
import trackr.model.person.Email;
import trackr.model.person.Name;
import trackr.model.person.Person;
import trackr.model.person.Phone;
import trackr.model.tag.Tag;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * Contains utility methods for populating {@code Trackr} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
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

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("Buy flour"), new TaskDeadline("01/01/2024"), new TaskStatus()),
            new Task(new TaskName("Sort inventory"), new TaskDeadline("03/03/2024"), new TaskStatus("D")),
            new Task(new TaskName("Check status of orders"), new TaskDeadline("02/01/2024"), new TaskStatus("N")),
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;
    }

    public static Order[] getSampleOrders() {
        Customer amy = new Customer(new CustomerName("Amy"),
                new CustomerPhone("12345678"),
                new CustomerAddress("123 Smith Street"));
        Customer bob = new Customer(new CustomerName("Bob"),
                new CustomerPhone("87654321"),
                new CustomerAddress("321 Hoover Street"));
        Customer charlie = new Customer(new CustomerName("Charlie"),
                new CustomerPhone("71396482"),
                new CustomerAddress("789 Bonder Street"));

        return new Order[] {
            new Order(new OrderName("Chocolate Cookies"),
                    new OrderDeadline("01/01/2024"),
                    new OrderStatus(), new OrderQuantity("2"),
                    amy),
            new Order(new OrderName("Cheese Cake"),
                    new OrderDeadline("03/03/2024"),
                    new OrderStatus(),
                    new OrderQuantity("10"),
                    bob),
            new Order(new OrderName("Vanilla Ice Cream"),
                    new OrderDeadline("02/01/2024"),
                    new OrderStatus(),
                    new OrderQuantity("100"),
                    charlie),
        };
    }

    public static ReadOnlyOrderList getSampleOrderList() {
        OrderList sampleOl = new OrderList();
        for (Order sampleOrder : getSampleOrders()) {
            sampleOl.addOrder(sampleOrder);
        }
        return sampleOl;
    }

}
