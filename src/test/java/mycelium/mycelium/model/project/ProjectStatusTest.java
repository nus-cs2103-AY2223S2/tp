package mycelium.mycelium.model.project;

import static mycelium.mycelium.model.project.ProjectStatus.DONE;
import static mycelium.mycelium.model.project.ProjectStatus.IN_PROGRESS;
import static mycelium.mycelium.model.project.ProjectStatus.NOT_STARTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.testutil.Pair;

public class ProjectStatusTest {
    private static final Map<String, Pair<String, ProjectStatus>> okInput = Map.of(
        "not started", Pair.of("not_started", NOT_STARTED),
        "in progress", Pair.of("in_progress", IN_PROGRESS),
        "done", Pair.of("done", DONE),
        "not started (uppercase)", Pair.of("NOT_STARTED", NOT_STARTED),
        "in progress (uppercase)", Pair.of("IN_PROGRESS", IN_PROGRESS),
        "done (uppercase)", Pair.of("DONE", DONE),
        "not started (mixed case)", Pair.of("NoT_sTaRtEd", NOT_STARTED),
        "in progress (mixed case)", Pair.of("In_PrOgReSs", IN_PROGRESS),
        "done (mixed case)", Pair.of("dOnE", DONE)
    );
    private static final Map<String, String> badInput = Map.of(
        "empty string", "",
        "only whitespace", " ",
        "invalid status", "foobar"
    );

    @Test
    public void isValidProjectStatus_validStatus_returnsTrue() {
        okInput.forEach((desc, tt) -> {
            assertTrue(ProjectStatus.isValidProjectStatus(tt.first), "While testing case: " + desc);
        });
    }

    @Test
    public void isValidProjectStatus_invalidStatus_returnsFalse() {

        // We reject the status if it is not one of the three valid statuses.
        badInput.forEach((desc, tt) -> {
            assertFalse(ProjectStatus.isValidProjectStatus(tt), "While testing case: " + desc);
        });
    }

    @Test
    public void fromString_validString_returnsStatus() {
        okInput.forEach((desc, tt) -> {
            ProjectStatus status = ProjectStatus.fromString(tt.first);
            assertEquals(tt.second, status, "While testing case: " + desc);
        });
    }

    @Test
    public void fromString_invalidString_throwsException() {
        badInput.forEach((desc, tt) -> {
            Assertions.assertThrows(IllegalArgumentException.class, (
                ) -> ProjectStatus.fromString(tt), "While testing case: " + desc);
        });
    }
}
