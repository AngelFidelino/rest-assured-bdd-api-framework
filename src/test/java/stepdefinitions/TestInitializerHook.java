package stepdefinitions;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.aflr.utils.RestAssuredExtension;

public class TestInitializerHook {
    RestAssuredExtension restAssuredExtension;

    @Before
    public void setUp() {
        restAssuredExtension = new RestAssuredExtension();
    }

    @After
    public void after() {
        restAssuredExtension.stopWireMockServer();
    }
}
