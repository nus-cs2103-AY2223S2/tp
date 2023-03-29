package vimification.internal.command.logic;

import java.util.HashSet;
import java.util.Set;

public class DeleteFieldsRequest {

    public boolean deadline = false;
    public Set<String> labels = new HashSet<>();

    public DeleteFieldsRequest() {

    }


}
