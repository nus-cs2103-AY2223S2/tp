package trackr.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import trackr.model.Menu;
import trackr.model.OrderList;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.commons.Tag;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.Customer;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;
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
            new Supplier(new PersonName("Prima Flour"), new PersonPhone("87438807"),
                new PersonEmail("sales.primaflour@prima.com.sg"), new PersonAddress("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("flour")),
            new Supplier(new PersonName("Kim Guan Guan Coffee"), new PersonPhone("99272758"),
                new PersonEmail("info@kimguanguan.com"), new PersonAddress("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("coffee")),
            new Supplier(new PersonName("N Supplies"), new PersonPhone("93210283"),
                new PersonEmail("sales@nsupplies.com"), new PersonAddress("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("halal")),
            new Supplier(new PersonName("Teck Leong Pte Ltd"), new PersonPhone("91031282"),
                new PersonEmail("teckleong@example.com"),
                new PersonAddress("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("confectionery")),
            new Supplier(new PersonName("Chip Seng"), new PersonPhone("92492021"),
                    new PersonEmail("chipseng@example.com"), new PersonAddress("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("sugar")),
            new Supplier(new PersonName("Roy Selva"), new PersonPhone("92624417"), new PersonEmail("roys@example.com"),
                new PersonAddress("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("driver"))
        };
    }

    public static ReadOnlySupplierList getSampleSupplierList() {
        SupplierList sampleSupplierList = new SupplierList();
        for (Supplier sampleSupplier : getSampleSuppliers()) {
            sampleSupplierList.addItem(sampleSupplier);
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
            new Task(new TaskName("Buy flour"), new TaskDeadline("01/01/2024"),
                    new TaskStatus()),
            new Task(new TaskName("Sort inventory"), new TaskDeadline("03/03/2024"),
                    new TaskStatus("D")),
            new Task(new TaskName("Check status of orders"), new TaskDeadline("02/01/2024"),
                    new TaskStatus("N"))
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addItem(sampleTask);
        }
        return sampleTl;
    }

    public static MenuItem[] getSampleMenuItems() {
        return new MenuItem[] {
            new MenuItem(new ItemName("Chocolate Cookies"), new ItemSellingPrice("5.00"), new ItemCost("1.20")),
            new MenuItem(new ItemName("Cupcake"), new ItemSellingPrice("6.20"), new ItemCost("1.10")),
            new MenuItem(new ItemName("Bread"), new ItemSellingPrice("10"), new ItemCost("0.5"))
        };
    }

    public static ReadOnlyMenu getSampleMenu() {
        Menu sampleM = new Menu();
        for (MenuItem sampleMenuItem : getSampleMenuItems()) {
            sampleM.addItem(sampleMenuItem);
        }
        return sampleM;
    }

    public static Order[] getSampleOrders() {
        Customer amy = new Customer(new PersonName("Amy"),
                new PersonPhone("12345678"),
                new PersonAddress("123 Smith Street"));
        Customer bob = new Customer(new PersonName("Bob"),
                new PersonPhone("87654321"),
                new PersonAddress("321 Hoover Street"));
        Customer charlie = new Customer(new PersonName("Charlie"),
                new PersonPhone("71396482"),
                new PersonAddress("789 Bonder Street"));

        MenuItem cookie = new MenuItem(new ItemName("Chocolate Cookies"),
                                        new ItemSellingPrice("5.00"),
                                        new ItemCost("1.20"));
        MenuItem cupcake = new MenuItem(new ItemName("Cupcake"),
                                        new ItemSellingPrice("6.20"),
                                        new ItemCost("1.10"));
        MenuItem bread = new MenuItem(new ItemName("Bread"),
                                        new ItemSellingPrice("10"),
                                        new ItemCost("0.5"));

        return new Order[] {
            new Order(cookie,
                    new OrderDeadline("01/01/2024"),
                    new OrderStatus(), new OrderQuantity("2"),
                    amy),
            new Order(cupcake,
                    new OrderDeadline("03/03/2024"),
                    new OrderStatus("I"),
                    new OrderQuantity("10"),
                    bob),
            new Order(bread,
                    new OrderDeadline("02/01/2024"),
                    new OrderStatus("D"),
                    new OrderQuantity("100"),
                    charlie)
        };
    }

    public static ReadOnlyOrderList getSampleOrderList() {
        OrderList sampleOl = new OrderList();
        for (Order sampleOrder : getSampleOrders()) {
            sampleOl.addItem(sampleOrder);
        }
        return sampleOl;
    }

}
