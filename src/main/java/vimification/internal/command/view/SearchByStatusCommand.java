package vimification.internal.command.view;

import vimification.model.task.Status;

public class SearchByStatusCommand extends SearchCommand {

    public SearchByStatusCommand(Status status) {
        super(task -> task.hasStatus(status));
    }

}
