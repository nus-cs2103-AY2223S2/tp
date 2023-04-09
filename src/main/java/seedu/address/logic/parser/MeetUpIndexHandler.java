package seedu.address.logic.parser;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import seedu.address.model.Model;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.MeetUpIndex;

/**
 * Assigns a valid meet up index for a newly-added meet up to EduMate.
 */
public class MeetUpIndexHandler {

    private Model model;

    public MeetUpIndexHandler(Model model) {
        this.model = model;
    }

    /**
     * Assigns a meet up index for a meet up.
     * @return new meet up index for new meet up.
     */
    public MeetUpIndex assignMeetUpIndex() {
        List<MeetUp> meetUpList = model.getObservableMeetUpList();
        if (meetUpList.isEmpty() || meetUpList.stream()
                .noneMatch(meetUp -> meetUp.getMeetUpIndex().equals(new MeetUpIndex(1)))) {
            return new MeetUpIndex(1);
        }

        OptionalInt takenIndices = IntStream.iterate(1, x -> x + 1)
                .takeWhile(integer -> meetUpList.stream()
                        .anyMatch(meetUp -> meetUp
                                .getMeetUpIndex().equals(new MeetUpIndex(integer)))).max();
        Integer availableIndex = takenIndices.getAsInt() + 1;
        return new MeetUpIndex(availableIndex);
    }

    /**
     * Returns the meet up at the given index.
     */
    public Optional<MeetUp> getMeetUpByIndex(MeetUpIndex index) {
        List<MeetUp> meetUpList = model.getObservableMeetUpList();
        return meetUpList.stream()
                .filter(meetUp -> meetUp.getMeetUpIndex().equals(index)).findFirst();
    }

}
