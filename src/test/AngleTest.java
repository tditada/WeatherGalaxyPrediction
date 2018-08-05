package test;

import model.Geometry.Angle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AngleTest {

    @Test
    public void generateAngleNegativeGrade() {
        Angle testAngle = new Angle(-30);
        assertEquals((int)testAngle.getAngle(), 330);
    }

    @Test
    public void generateAngleWith450GradesEquals90() {
        Angle testAngle = new Angle(450);
        assertEquals((int)testAngle.getAngle(), 90);
    }


    @Test
    public void checkTwoEqualsAnglesisAlign() {
        Angle testAngle = new Angle(-30);
        assertEquals(testAngle.isAlign(testAngle), true);
    }

    @Test
    public void check0And180AreisAlign() {
        Angle testAngle = new Angle(0);
        assertEquals(testAngle.isAlign(new Angle(180)), true);
    }

    @Test
    public void check0AndNegative180AreAlign() {
        Angle testAngle = new Angle(0);
        assertEquals(testAngle.isAlign(new Angle(-180)), true);
    }

    @Test
    public void check10And190AreAlign() {
        Angle testAngle = new Angle(10);
        assertEquals(testAngle.isAlign(new Angle(190)), true);
    }

}