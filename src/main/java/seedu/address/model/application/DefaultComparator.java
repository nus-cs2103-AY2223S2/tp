package seedu.address.model.application;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Compares two applications based on their time of creation.
 * More recently created applications will be ranked higher.
 */
public class DefaultComparator implements Comparator<Application> {

    private List<Application> applicationList;

    /**
     * Constructs a {@code DefaultComparator} for the given application list.
     *
     * @param applicationList A valid list of applications.
     */
    public DefaultComparator(List<Application> applicationList) {
        requireNonNull(applicationList);
        this.applicationList = applicationList;
    }

    public DefaultComparator() {
        this.applicationList = new ArrayList<Application>();
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public int compare(Application appOne, Application appTwo) {
        int appOneIndex = applicationList.indexOf(appOne);
        int appTwoIndex = applicationList.indexOf(appTwo);
        if (appOneIndex < appTwoIndex) {
            // Application One was created earlier and should be placed lower
            return -1;
        } else if (appOneIndex > appTwoIndex) {
            // Application Two was created earlier and should be placed lower
            return 1;
        }
        // should throw error here as it is not possible for two applications to share an index
        return 0;
    }
}
