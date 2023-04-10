package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.exceptions.DuplicateReadingException;
import seedu.address.model.tank.readings.exceptions.ReadingNotFoundException;

/**
 * A list of Ammonia Readings of one tank
 *
 * Supports a minimal set of list operations.
 */
public class UniqueIndividualReadingLevels {
    private final ObservableList<AmmoniaLevel> internalListAmmonia = FXCollections.observableArrayList();

    private final ObservableList<AmmoniaLevel> internalUnmodifiableListAmmonia =
            FXCollections.unmodifiableObservableList(internalListAmmonia);

    private final ObservableList<PH> internalListPH = FXCollections.observableArrayList();

    private final ObservableList<PH> internalUnmodifiableListPH =
            FXCollections.unmodifiableObservableList(internalListPH);

    private final ObservableList<Temperature> internalListTemp = FXCollections.observableArrayList();

    private final ObservableList<Temperature> internalUnmodifiableListTemp =
            FXCollections.unmodifiableObservableList(internalListTemp);

    private Tank tank;

    /**
     * Constructor for an ammonia level list for a single tank
     * @param t Tank this list is associated to
     */
    public UniqueIndividualReadingLevels() {

    }

    /**
     * Returns true if the list contains an equivalent reading as the given argument.
     */
    public boolean containsSameDayReading(AmmoniaLevel toCheckAmmonia, PH toCheckPH,
                                          Temperature toCheckTemp) {
        requireNonNull(toCheckAmmonia);
        requireNonNull(toCheckPH);
        requireNonNull(toCheckTemp);
        return internalListAmmonia.stream().anyMatch(toCheckAmmonia::equals)
                || internalListPH.stream().anyMatch(toCheckPH::equals)
                || internalListTemp.stream().anyMatch(toCheckTemp::equals);
    }

    /**
     * Adds a reading to the list.
     * If a reading of the same day it replaces the old reading
     */
    public void add(AmmoniaLevel toAddAmmonia, PH toAddPH, Temperature toAddTemp) {
        requireNonNull(toAddAmmonia);
        requireNonNull(toAddPH);
        requireNonNull(toAddTemp);
        if (containsSameDayReading(toAddAmmonia, toAddPH, toAddTemp)) {
            internalListAmmonia.remove(toAddAmmonia);
            internalListPH.remove(toAddPH);
            internalListTemp.remove(toAddTemp);
        }
        internalListAmmonia.add(toAddAmmonia);
        internalListPH.add(toAddPH);
        internalListTemp.add(toAddTemp);
    }

    /**
     * Replaces a {@code reading} in the list with {@code editedReadings}.
     * {@code target} must exist in the list.
     */
    public void setReadingsLevel(AmmoniaLevel targetAmmonia, AmmoniaLevel editedAmmoniaLevel,
                                 PH targetPH, PH editedPH,
                                 Temperature targetTemp, Temperature editedTemp) {
        requireAllNonNull(targetAmmonia, editedAmmoniaLevel, targetPH, editedPH, targetTemp, editedTemp);

        int indexAmmonia = internalListAmmonia.indexOf(targetAmmonia);
        int indexPH = internalListPH.indexOf(targetPH);
        int indexTemp = internalListTemp.indexOf(targetTemp);
        if (indexAmmonia == -1 || indexPH == -1 || indexTemp == -1) {
            throw new ReadingNotFoundException();
        }

        internalListAmmonia.set(indexAmmonia, editedAmmoniaLevel);
        internalListPH.set(indexPH, editedPH);
        internalListTemp.set(indexTemp, editedTemp);
    }

    /**
     * Removes the most recent readings from the list.
     * List must not be empty.
     */
    public Reading[] removeLastReadings() {
        //For now, all readings come and go as a trio
        int lastIndex = internalListAmmonia.size() - 1;
        Reading a = internalListAmmonia.remove(lastIndex);
        Reading t = internalListTemp.remove(lastIndex);
        Reading p = internalListPH.remove(lastIndex);
        Reading[] ret = new Reading[3];
        ret[0] = a;
        ret[1] = p;
        ret[2] = t;
        return ret;
    }

    public void setReadings(UniqueIndividualReadingLevels replacement) {
        requireNonNull(replacement);
        internalListAmmonia.setAll(replacement.internalListAmmonia);
        internalListPH.setAll(replacement.internalListPH);
        internalListTemp.setAll(replacement.internalListTemp);
    }

    /**
     * Replaces the contents of this list with {@code ammoniaLevels}.
     * {@code ammoniaLevels} must not contain duplicate ammoniaLevels.
     */
    public void setReadings(List<AmmoniaLevel> ammoniaLevels, List<PH> pHs, List<Temperature> temperatures) {
        requireAllNonNull(ammoniaLevels, pHs, temperatures);
        if (!ammoniaLevelsAreUnique(ammoniaLevels)) {
            throw new DuplicateReadingException();
        }
        if (!pHsAreUnique(pHs)) {
            throw new DuplicateReadingException();
        }
        if (!temperaturesAreUnique(temperatures)) {
            throw new DuplicateReadingException();
        }

        internalListAmmonia.setAll(ammoniaLevels);
        internalListPH.setAll(pHs);
        internalListTemp.setAll(temperatures);
    }

    /**
     * Returns true if {@code Readings} contains only unique Readings.
     */
    private boolean ammoniaLevelsAreUnique(List<AmmoniaLevel> ammoniaLevels) {
        for (int i = 0; i < ammoniaLevels.size() - 1; i++) {
            for (int j = i + 1; j < ammoniaLevels.size(); j++) {
                if (ammoniaLevels.get(i).equals(ammoniaLevels.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code Readings} contains only unique Readings.
     */
    private boolean pHsAreUnique(List<PH> pHs) {
        for (int i = 0; i < pHs.size() - 1; i++) {
            for (int j = i + 1; j < pHs.size(); j++) {
                if (pHs.get(i).equals(pHs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code Readings} contains only unique Readings.
     */
    private boolean temperaturesAreUnique(List<Temperature> temperaturess) {
        for (int i = 0; i < temperaturess.size() - 1; i++) {
            for (int j = i + 1; j < temperaturess.size(); j++) {
                if (temperaturess.get(i).equals(temperaturess.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AmmoniaLevel> asUnmodifiableObservableListAmmonia() {
        return internalUnmodifiableListAmmonia;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PH> asUnmodifiableObservableListPH() {
        return internalUnmodifiableListPH;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Temperature> asUnmodifiableObservableListTemp() {
        return internalUnmodifiableListTemp;
    }

    /**
     * Setter for tank
     * @param t Tank to be set to
     */
    public void setTank(Tank t) {
        this.tank = t;
    }

    /**
     * Getter for tank
     * @return tank of this list
     */
    public Tank getTank() {
        return this.tank;
    }

    /**
     * Returns string for Json individual ammonia levels
     * @return string for Json individual ammonia levels
     */
    public String getCommaSeperatedValuesStringAmmonia() {
        String ret = "";
        int ind = 0;
        for (AmmoniaLevel a : internalListAmmonia) {
            String val = Double.toString(a.getValue());
            ret += val;
            if (ind != size() - 1) {
                ret += ",";
            }
            ind++;
        }
        return ret;
    }

    /**
     * Returns string for Json individual ammonia levels
     * @return string for Json individual ammonia levels
     */
    public String getCommaSeperatedDatesStringAmmonia() {
        String ret = "";
        int ind = 0;
        for (AmmoniaLevel a : internalListAmmonia) {
            String date = a.dateString;
            ret += date;
            if (ind != size() - 1) {
                ret += ",";
            }
            ind++;
        }
        return ret;
    }

    /**
     * Returns string for Json individual PH levels
     * @return string for Json individual PH levels
     */
    public String getCommaSeperatedValuesStringPH() {
        String ret = "";
        int ind = 0;
        for (PH a : internalListPH) {
            String val = Double.toString(a.getValue());
            ret += val;
            if (ind != size() - 1) {
                ret += ",";
            }
            ind++;
        }
        return ret;
    }

    /**
     * Returns string for Json individual PH levels
     * @return string for Json individual PH levels
     */
    public String getCommaSeperatedDatesStringPH() {
        String ret = "";
        int ind = 0;
        for (PH a : internalListPH) {
            String date = a.dateString;
            ret += date;
            if (ind != size() - 1) {
                ret += ",";
            }
            ind++;
        }
        return ret;
    }

    /**
     * Returns string for Json individual Temp levels
     * @return string for Json individual Temp levels
     */
    public String getCommaSeperatedValuesStringTemp() {
        String ret = "";
        int ind = 0;
        for (Temperature a : internalListTemp) {
            String val = Double.toString(a.getValue());
            ret += val;
            if (ind != size() - 1) {
                ret += ",";
            }
            ind++;
        }
        return ret;
    }

    /**
     * Returns string for Json individual Temp levels
     * @return string for Json individual Temp levels
     */
    public String getCommaSeperatedDatesStringTemp() {
        String ret = "";
        int ind = 0;
        for (Temperature a : internalListTemp) {
            String date = a.dateString;
            ret += date;
            if (ind != size() - 1) {
                ret += ",";
            }
            ind++;
        }
        return ret;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueIndividualReadingLevels // instanceof handles nulls
                && tank.equals(((UniqueIndividualReadingLevels) other).tank));
    }

    /**
     * Returns size of each of the readings. The 3 types have the same number of total inputs
     * @return size integer
     */
    public int size() {
        //for now all 3 readings come and go together, have same size
        return internalListAmmonia.size();
    }
}
