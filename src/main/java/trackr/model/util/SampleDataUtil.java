package trackr.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import trackr.model.OrderList;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
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
import trackr.model.supplier.Address;
import trackr.model.supplier.Email;
import trackr.model.supplier.Name;
import trackr.model.supplier.Phone;
import trackr.model.supplier.Supplier;
import trackr.model.tag.Tag;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * Contains utility methods for populating {@code Trackr} with sample data.
 */
public class SampleDataUtil {
    public static Supplier[] getSampleSuppliers() {
        return new Supplier[] {
            new Supplier(new Name("Prima Flour"), new Phone("87438807"), new Email("sales.primaflour@prima.com.sg"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("flour")),
            new Supplier(new Name("Kim Guan Guan Coffee"), new Phone("99272758"), new Email("info@kimguanguan.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("coffee")),
            new Supplier(new Name("N Supplies"), new Phone("93210283"), new Email("sales@nsupplies.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("halal")),
            new Supplier(new Name("Teck Leong Pte Ltd"), new Phone("91031282"), new Email("teckleong@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("confectionery")),
            new Supplier(new Name("Chip Seng"), new Phone("92492021"), new Email("chipseng@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("sugar")),
            new Supplier(new Name("Roy Selva"), new Phone("92624417"), new Email("roys@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("driver"))
        };
    }

    public static ReadOnlySupplierList getSampleSupplierList() {
        SupplierList sampleSupplierList = new SupplierList();
        for (Supplier sampleSupplier : getSampleSuppliers()) {
            sampleSupplierList.addSupplier(sampleSupplier);
        }
        return sampleSupplierList;
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
