package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {
    public static final Transaction COFFEE_MACHINES_A = new TransactionBuilder()
            .withDesc("5 Café Lite Espresso Machines for Proseware")
            .withOwner("Limmy Lim")
            .withStatus("open")
            .withValue("5000")
            .build();
    public static final Transaction COFFEE_MACHINES_B = new TransactionBuilder()
            .withDesc("15 AwfullyHot Coffee Pots for Hotel Californication")
            .withOwner("Mac Macpherson")
            .withStatus("closed")
            .withValue("30000")
            .build();
    public static final Transaction COFFEE_BEANS = new TransactionBuilder()
            .withDesc("100kg Brazilian Grade 5 Dark Roast Arabica Beans for The Cafe Cafe")
            .withOwner("Lim Bing Seng")
            .withStatus("open")
            .withValue("1000")
            .build();
    public static final Transaction COFFEE_FILTERS = new TransactionBuilder()
            .withDesc("30 Boxes of Premium-A Filters for The Cafe Cafe")
            .withOwner("Lim Bing Seng")
            .withStatus("closed")
            .withValue("300")
            .build();
    public static final Transaction COFFEE_POUROVER_SETS = new TransactionBuilder()
            .withDesc("12 Pour-Easy Pourover Coffee Sets for Degree Robusta Cafe")
            .withOwner("Sarah Foobar")
            .withStatus("open")
            .withValue("1200")
            .build();
    public static final Transaction COFFEE_GRINDER_LEASE = new TransactionBuilder()
            .withDesc("5 LiteGrind Coffe Grinders for long term lease with The Helvetica House")
            .withOwner("Mike Lopez")
            .withStatus("open")
            .withValue("3000")
            .build();
    public static final Transaction ESPRESSO_MACHINES = new TransactionBuilder()
            .withDesc("30 VentiMaxs Espresso Machines for The Coffeemakers Cafe")
            .withOwner("Grande Michelin")
            .withStatus("closed")
            .withValue("60000")
            .build();

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Transaction txn : getTypicalTransactions()) {
            ab.addTransaction(txn);
        }
        return ab;
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(COFFEE_MACHINES_A, COFFEE_MACHINES_B, COFFEE_BEANS, ESPRESSO_MACHINES,
                COFFEE_FILTERS, COFFEE_POUROVER_SETS, COFFEE_GRINDER_LEASE));
    }
}
