import org.example.Logger;
import org.junit.Test;

import static org.example.Main.parseSettings;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void parseConfigTest() {
        int actual1 = Integer.parseInt(parseSettings("port"));
        String actual2 = parseSettings("host");
        assertEquals(5511, actual1);
        assertEquals("localhost",actual2);
        Logger.log("Settings test port,host - ok!");
    }
}
