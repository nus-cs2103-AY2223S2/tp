package seedu.address.model.entity.shop.garage;

import java.util.ArrayList;

import seedu.address.model.entity.person.Staff;

/**
 * This class represents a Garage which the user of this program owns.
 */
public class Garage {
    private ArrayList<Staff> staffs;
    private ArrayList<Lot> lots;

    /**
     * This method is the constructor to create a Garage of numberOfLots quantity of Lots.
     *
     * @param numberOfLots Number of lots this Garage has.
     */
    public Garage(int numberOfLots) {

        lots = new ArrayList<Lot>(numberOfLots);
        for (int i = 0; i < numberOfLots; i++) {
            lots.add(new Lot());
        }
    }

    /**
     * This method is the constructor to create a Garage of numberOfLots quantity of Lots.
     * By default, this method creates 5 lots.
     */
    public Garage() {
        this(5);
    }

    /**
     * This method returns the staff that is within this garage.
     * @return
     */
    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    /**
     * This method adds a staff to this garage.
     *
     * @param staff The staff to be added to this garage.
     */
    public void addStaff(Staff staff) {
        this.staffs.add(staff);
    }

    /**
     * This method removes a staff from this garage.
     *
     * @param staff The staff to be removed from this garage.
     */
    public void removeStaff(Staff staff) {
        this.staffs.remove(staff);
    }

    /**
     * This method returns the lots that are within this garage.
     * @return a list of lots within this garage.
     */
    public ArrayList<Lot> getLots() {
        return lots;
    }

    /**
     * This method
     * @param lots
     */
    public void setLots(ArrayList<Lot> lots) {
        this.lots = lots;
    }
}
