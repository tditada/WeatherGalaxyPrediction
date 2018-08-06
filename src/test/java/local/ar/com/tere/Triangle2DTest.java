package local.ar.com.tere;

import local.ar.com.tere.model.Geometry.Triangle2D;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Triangle2DTest {

    private Triangle2D littleCenteredTriangle;

    @Before
    public void setUp() {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(0,1));
        points.add(new Point2D.Double(-1,-1));
        points.add(new Point2D.Double(1, -1));
        this.littleCenteredTriangle = new Triangle2D(points);
    }

    @Test
    public void isX0Y0InTriangleCentered() {
        assertTrue(littleCenteredTriangle.isPointInTriangle(new Point2D.Double(0,0)));
    }

    @Test
    public void isX9Y9InLittleTriangleCenteredShouldBeFalse() {
        assertFalse(littleCenteredTriangle.isPointInTriangle(new Point2D.Double(9,9)));
    }

    @Test
    public void perimeterFromTestTriangleIsNear6() {
        assertEquals(littleCenteredTriangle.getTrianglePerimeter(), 6.472, 0.001);
    }

}
