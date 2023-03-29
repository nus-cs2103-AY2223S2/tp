package vimification.storage.oldcode;

import java.time.LocalDateTime;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.oldcode.Event;
import vimification.model.task.Task;
import vimification.storage.JsonAdaptedTask;


public class JsonAdaptedEvent {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;


    public JsonAdaptedEvent(Event event) {
        //super(event.getDescription(), event.getStatus(), event.getPriority());
        startDateTime = event.getStartDateTime();
        endDateTime = event.getEndDateTime();
    }


}
