package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.TimerTask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CronTest {
    @Test
    public void singleton_test() {
        assertEquals(Cron.getInstance().hashCode(), Cron.getInstance().hashCode());
    }

    @Test
    public void size() {
        int size = 5;
        int delay = 5;

        TimerTask t = new TimerTask() {
            @Override
            public void run() {
            }
        };

        assertTimeoutPreemptively(Duration.ofSeconds(delay - 1), () -> {
            Cron cron = Cron.getInstance();
            for (int i = 0; i < size; ++i) {
                cron.addTask(t, 1, delay);
            }
            assertEquals(cron.size(), size);
        });
    }

    @Test
    public void addTask_print() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String p = "Task 1";
        int frequency = 10;

        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                System.out.println(p);
            }
        };

        Cron cron = Cron.getInstance();
        cron.addTask(t, frequency);
        assertTimeoutPreemptively(Duration.ofSeconds(frequency - 5), () ->
                assertEquals(p, outputStreamCaptor.toString().trim()));
        System.setOut(System.out);
    }

    @Test
    public void isRunning() {
        Cron cron = Cron.getInstance();
        assertTrue(cron.isRunning());
        cron.stop();
        assertFalse(cron.isRunning());
    }

    @Test
    public void stop() {
        Cron cron = Cron.getInstance();
        cron.stop();
        assertFalse(cron.isRunning());
    }

    @BeforeEach
    public void setUp() {
        stop();
    }
}
