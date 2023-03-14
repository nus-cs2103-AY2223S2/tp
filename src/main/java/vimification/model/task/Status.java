package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.AppUtil.checkArgument;


public class Status {


    public Boolean done;
    public String value;


    public Status(Boolean status) {
        requireNonNull(status);
        done = status;
        value = Integer.toString((done) ? 1 : 0);
    }

    public Status(String status) {
        requireNonNull(status);
        done = status.equals("1");
        value = status;
    }

    public void setDone() {
        done = true;
        value = Integer.toString(1);
    }

    public void setNotDone() {
        done = false;
        value = Integer.toString(0);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && done == ((Status) other).done); // state check
    }
}
