package wingman.model.crew;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static wingman.model.crew.CrewRank.CREW_MEMBER;
import static wingman.model.crew.CrewRank.JUNIOR_CREW_MEMBER;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CrewTest {
    private final String name1 = "name";
    private final String name2 = " ";

    private final CrewRank rank1 = CREW_MEMBER;
    private final CrewRank rank2 = JUNIOR_CREW_MEMBER;

    private final Crew crew1 = new Crew(name1, rank1);
    private final Crew crew2 = new Crew(name2, rank1);
    private final Crew crew3 = new Crew(name1, rank2);
    private final Crew crew4 = new Crew(name2, rank2);

    @Test
    public void testCrew() {
        // positive test case
        assertDoesNotThrow(() -> new Crew(name1, rank1));
        assertDoesNotThrow(() -> new Crew(name2, rank1));
        assertDoesNotThrow(() -> new Crew(name1, rank2));
    }

    @Test
    public void testGetName() {
        assertEquals(name1, crew1.getName());
    }

    @Test
    public void testGetRank() {
        assertEquals(rank1, crew1.getRank());
    }

    @Test
    public void testId() {
        assertNotNull(crew1.getId());
    }

    @Test
    public void testGetDisplayList() {
        assertEquals(List.of(name1, String.format("Rank: %s", rank1)), crew1.getDisplayList());
    }

    @Test
    public void testEquals() {
        assertEquals(crew1, crew1);
        assertEquals(crew2, crew2);
        assertEquals(crew3, crew3);
        assertEquals(crew4, crew4);
        assertEquals(crew1, crew4);
        assertEquals(crew4, crew1);

        assertNotEquals(crew1, crew2);
        assertNotEquals(crew2, crew1);
        assertNotEquals(crew1, crew3);
        assertNotEquals(crew3, crew1);
        assertNotEquals(crew2, crew3);
        assertNotEquals(crew3, crew2);
    }
}
