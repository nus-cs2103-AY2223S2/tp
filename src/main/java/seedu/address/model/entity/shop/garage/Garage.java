package seedu.address.model.entity.shop.garage;

import seedu.address.model.entity.person.Staff;
import seedu.address.model.entity.shop.Shop;

import java.util.ArrayList;

public class Garage extends Shop {
    private ArrayList<Lot> lots;
    public ArrayList<Staff> staffs;

    /**
     * This method is the constructor to create a Garage of numberOfLots quantity of Lots.
     *
     * @param numberOfLots Number of lots this Garage has.
     */
    public Garage(int numberOfLots) {
        lots = new ArrayList<Lot>(numberOfLots);
    }

    /**
     * This method is the constructor to create a Garage of numberOfLots quantity of Lots.
     * By default, this method creates 5 lots.
     */
    public Garage() {
        this(5);
    }

}
