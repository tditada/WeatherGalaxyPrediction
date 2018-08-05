package model.Geometry;

public class Angle {

    private static final int FULL_CIRCLE = 360;
    private static final int ALIGNMENT_GRADE = FULL_CIRCLE / 2;
    private Integer angle;


    /**
     * Angle in degrees
     * @param angle degrees
     */
    public Angle(Integer angle) {
        angle = angle % FULL_CIRCLE;
        if (angle < 0) {
            this.angle = FULL_CIRCLE + angle;
        } else {
            this.angle = angle;
        }
    }

    /**
     * Check if angles has a 180 degrees difference
     * @param angle {@link Angle} to compare
     * @return true if the are align, false otherwise
     */
    public boolean isAlign(Angle angle) {
        return this.angle % ALIGNMENT_GRADE == angle.getAngle() % ALIGNMENT_GRADE ;
    }

    public Integer getAngle() {
        return this.angle;
    }

}
