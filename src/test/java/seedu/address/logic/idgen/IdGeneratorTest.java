package seedu.address.logic.idgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

class IdGeneratorTest {
    @Test
    public void idGenerationTest() {
        Set<Integer> ids = new HashSet<>();
        IdGenerator idGenerator = new IdGenerator();
        Random randomEngine = new Random();
        int iterations = 1000;
        for (int i = 0; i < iterations; i++) {
            int id = idGenerator.generateCustomerId();
            assertFalse(ids.contains(id));
            ids.add(id);
            if (randomEngine.nextInt(5) == 0) {
                while (true) {
                    int idToRemove = randomEngine.nextInt(iterations);
                    if (ids.contains(idToRemove)) {
                        ids.remove(idToRemove);
                        idGenerator.setCustomerIdUnused(idToRemove);
                        int newId = idGenerator.generateCustomerId();
                        assertEquals(idToRemove, newId);
                        idGenerator.setCustomerIdUnused(newId);
                        break;
                    }
                }
            }
        }
    }
}
