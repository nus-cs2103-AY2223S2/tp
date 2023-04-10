package wingman.logic.flight.linklocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.commons.fp.Lazy;
import wingman.model.Model;

@ExtendWith(MockitoExtension.class)
public class FlightLocationLinkCommandFactoryTest {

    @Mock
    private Model model;
    private FlightLocationLinkCommandFactory<LinkFlightToLocationCommand> linkFactory;
    private FlightLocationLinkCommandFactory<UnlinkFlightToLocationCommand> unlinkFactory;
    private Lazy<Model> modelLazy;



    @BeforeEach
    public void setUp() {
        modelLazy = Lazy.of(model);
        linkFactory = new FlightLocationLinkCommandFactory<>(
                modelLazy,
                LinkFlightToLocationCommand::new,
                "linklocation"
        );
        unlinkFactory = new FlightLocationLinkCommandFactory<>(
                modelLazy,
                UnlinkFlightToLocationCommand::new,
                "unlinklocation"
        );
    }

    @Test
    public void testGetPrefixes() {
        assertEquals(
                Optional.of(Set.of("/fl", "/from", "/to")),
                linkFactory.getPrefixes()
        );
        assertEquals(
                Optional.of(Set.of("/fl", "/from", "/to")),
                unlinkFactory.getPrefixes()
        );
    }

    @Test
    public void testGetCommandWord() {
        assertEquals("linklocation", linkFactory.getCommandWord());
        assertEquals("unlinklocation", unlinkFactory.getCommandWord());
    }

}
