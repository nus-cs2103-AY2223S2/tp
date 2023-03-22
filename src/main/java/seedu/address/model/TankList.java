package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.UniqueTankList;

/**
 * Wraps all data at the product level
 * Duplicates are not allowed (by .isSameTank comparison)
 */
public class TankList implements ReadOnlyTankList {
    private final UniqueTankList tanks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tanks = new UniqueTankList();
    }

    public TankList() {}

    /**
     * Creates a Tank List using the tanks in the {@code toBeCopied}
     */
    public TankList(ReadOnlyTankList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the tank list with {@code tank}.
     * {@code tank} must not contain duplicate tanks.
     */
    public void setTanks(List<Tank> tanks) {
        this.tanks.setTanks(tanks);
    }

    /**
     * Resets the existing data of this {@code TankList} with {@code newData}.
     */
    public void resetData(ReadOnlyTankList newData) {
        requireNonNull(newData);

        setTanks(newData.getTankList());
    }

    //// tank-level operations

    /**
     * Returns true if a tank with the same identity as {@code tank} exists in the tank list.
     */
    public boolean hasTank(Tank tank) {
        requireNonNull(tank);
        return tanks.contains(tank);
    }

    /**
     * Adds a tank to the {@code TankList}.
     * The tank must not already exist in the {@code TankList}.
     */
    public void addTank(Tank p) {
        tanks.add(p);
    }

    /**
     * Replaces the given tank {@code target} in the list with {@code editedTank}.
     * {@code target} must exist in the {@code TankList}.
     * The tank identity of {@code editedTank} must not be the same as another existing tank in the {@code TankList}.
     */
    public void setTank(Tank target, Tank editedTank) {
        requireNonNull(editedTank);

        tanks.setTank(target, editedTank);
    }

    /**
     * Removes {@code key} from this {@code TankList}.
     * {@code key} must exist in the {@code TankList}.
     */
    public void removeTank(Tank key) {
        tanks.remove(key);
    }

    public ArrayList<Tank> getTanksWithUnfedFish() {
        ArrayList<Tank> ret = new ArrayList<>();
        for (Tank t : tanks) {
            if (t.hasUnfedFish()) {
                ret.add(t);
            }
        }
        return ret;
    }

    //// util methods

    @Override
    public String toString() {
        return tanks.asUnmodifiableObservableList().size() + " tanks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Tank> getTankList() {
        return tanks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TankList // instanceof handles nulls
                && tanks.equals(((TankList) other).tanks));
    }

    @Override
    public int hashCode() {
        return tanks.hashCode();
    }
}
