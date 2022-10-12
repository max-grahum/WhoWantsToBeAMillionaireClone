package WhoWantsToBeAMillionaire;

import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    public GameTest() {
    }

    /**
     * Test of main method, of class Game.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Game.main(args);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testSetMoneyPanelColour() {
        System.out.println("Test setting money panel colour:");
        CustomMoneyPanel moneyPanel = new CustomMoneyPanel(0, Color.BLUE);
        moneyPanel.setColour(Color.RED);

        Color accResult = moneyPanel.getColour();
        Color expResult = Color.RED;

        assertSame(expResult, accResult);
    }

    @Test
    public void testPoint2DConstructor() {
        Point2D point = new Point2D(32, 96);

        int expectedX = 32;
        int expectedY = 96;

        assertTrue(point.x == expectedX);
        assertTrue(point.y == expectedY);
    }

    @Test
    public void testDatabaseGetAudience() {
        Boolean actual = SaveManager.getInstance().getAudience();

        //fails if it returns null
        assertTrue(actual == true || actual == false);
    }

    @Test
    public void testDatabaseGet5050() {
        Boolean actual = SaveManager.getInstance().get5050();

        //fails if it returns null
        assertTrue(actual == true || actual == false);
    }

    @Test
    public void testDatabaseGetQuestionNumer() {
        SaveManager.getInstance().clearData();
        int actual = SaveManager.getInstance().getQuestionNumber();

        assertTrue(actual == 0);
    }

}
