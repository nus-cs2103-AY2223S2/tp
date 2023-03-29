package seedu.dengue.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.storage.temporary.SpecialisedStackForMemory;


public class SpecialisedStackForMemoryTest {
    private static final int BOUND = 100;
    private SpecialisedStackForMemory<Integer> specialisedStack;
    private final Random random = new Random();

    @BeforeEach
    public void setUp() {
        this.specialisedStack = new SpecialisedStackForMemory<Integer>();
    }

    // Positive test cases for specialised stack.
    @Test
    public void push_randomIntegers_givesCorrectResults() {

        for (int i = 0; i < 20; i++) {
            int newInt = random.nextInt(BOUND);
            specialisedStack.push(newInt);
            assertTrue(specialisedStack.peek() == newInt);
        }

    }

    @Test
    public void removeOld_randomIntegers_givesCorrectResults() {
        int first = BOUND + 1;
        specialisedStack.push(first);
        for (int i = 0; i < 20; i++) {
            specialisedStack.push(random.nextInt(BOUND));
        }
        assertTrue(first == specialisedStack.removeOld());
    }

    @Test
    public void temporaryPop_randomIntegers_givesCorrectResults() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int newInt = random.nextInt(BOUND);
            integers.add(newInt);
            specialisedStack.push(newInt);
        }
        int size = integers.size();
        for (int i = size - 1; i >= 0; i--) {
            assertTrue(specialisedStack.temporaryPop() == integers.get(i));
        }
        assertTrue(specialisedStack.getStorage().size() == size);
    }

    @Test
    public void temporaryPopThenPush_randomIntegers_givesCorrectResults() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int newInt = random.nextInt(BOUND);
            integers.add(newInt);
            specialisedStack.push(newInt);
        }
        int size = integers.size();
        for (int i = size - 1; i >= 0; i--) {
            specialisedStack.temporaryPop();
        }
        for (int x : integers) {
            specialisedStack.pushOneFromTemporaryPop();
            assertTrue(specialisedStack.peek() == x);
        }
    }
}
