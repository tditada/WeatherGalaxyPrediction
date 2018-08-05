package model.Geometry;

import java.awt.geom.Point2D;
import java.util.List;

public class Triangle2D {
    private List<Point2D> points;

    public Triangle2D(List<Point2D> points){
        this.points = points;
    }

    /**
     * Checks if point p is inside triangle using barycentric coordinates.
     *
     * @param p Point2D to check
     * @return true if point is inside triangle, false otherwise
     */
    public boolean isPointInTriangle(Point2D p) {
        Point2D p0 = points.get(0), p1 = points.get(1), p2 = points.get(2);
        double A = 0.5 * (-p1.getY() * p2.getX() + p0.getY() * (-p1.getX() + p2.getX()) + p0.getX() * (p1.getY() - p2.getY()) + p1.getX() * p2.getY());
        int sign = A < 0 ? -1 : 1;
        double s = (p0.getY() * p2.getX() - p0.getX() * p2.getY() + (p2.getY() - p0.getY()) * p.getX() + (p0.getX() - p2.getX()) * p.getY()) * sign;
        double t = (p0.getX() * p1.getY() - p0.getY() * p1.getX() + (p0.getY() - p1.getY()) * p.getX() + (p1.getX() - p0.getX()) * p.getY()) * sign;

        return s > 0 && t > 0 && (s + t) < 2 * A * sign;
    }

    /**
     * Get triangle perimeter, adding the length of all sides.
     * @return triangle perimeter
     */
    public double getTrianglePerimeter() {
        Point2D p0 = points.get(0), p1 = points.get(1), p2 = points.get(2);
        return (p0.distance(p1) + p0.distance(p2) + p1.distance(p2));
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for(Point2D p: points) {
            s.append("(" + p.getX() + "," + p.getY() + ") \n");
        }
        return s.toString();
    }
}
