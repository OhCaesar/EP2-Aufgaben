package AB1;

import codedraw.CodeDraw;

/**
 * This class represents vectors in a 3D vector space.
 */
public class Vector3 {

    public static Vector3 defaultVector = new Vector3(0,0,0);
    private double x;
    private double y;
    private double z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector3(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }


    /**
     * Returns the sum of this vector and vector 'v'.
     * @param v another vector, v != null.
     * @return the sum of this vector and vector 'v'.
     */
    public Vector3 plus(Vector3 v) {
        return new Vector3(x+v.x,y+v.y,z+v.z);
    }

    /**
     * Returns the product of this vector and 'd'.
     * @param d the coefficient for the product.
     * @return the product of this vector and 'd'.
     */
    public Vector3 times(double d) {
        return new Vector3(x*d,y*d,z*d);
    }

    /**
     * Returns the sum of this vector and -1*v.
     * @param v another vector, v != null.
     * @return the sum of this vector and -1*v.
     */
    public Vector3 minus(Vector3 v) {
        return new Vector3(v.x-x,v.y-y,v.z-z);
    }

    /**
     * Returns the Euclidean distance of this vector to the specified vector 'v'.
     * @param v another vector, v != null.
     * @return the Euclidean distance of this vector to the specified vector 'v'.
     */
    public double distanceTo(Vector3 v) {
        double dX = v.x-x;
        double dY = v.y - y;
        double dZ = v.z- z;

        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);

    }

    /**
     * Returns the norm of this vector.
     * @return the length (norm) of this vector.
     */
    public double length() {
        return Math.sqrt(x*x+y*y+z*z);
    }

    /**
     * Normalizes this vector: changes the length of this vector such that it becomes 1.
     * The direction of the vector is not affected.
     */
    public void normalize() {
        double vectorLenght = length();
        x /=vectorLenght;
        y /=vectorLenght;
        z /=vectorLenght;
    }

    /**
     * Draws a filled circle with a specified radius centered at the (x,y) coordinates of this vector
     * in the canvas associated with 'cd'. The z-coordinate is not used.
     * @param cd the CodeDraw object used for drawing.
     * @param radius the radius > 0.
     */
    public void drawAsFilledCircle(CodeDraw cd, double radius) {
        cd.fillCircle(cd.getWidth() * (x + Simulation.SECTION_SIZE / 2) / Simulation.SECTION_SIZE,
                cd.getWidth() * (y + Simulation.SECTION_SIZE / 2) / Simulation.SECTION_SIZE,
                radius);

    }

    /**
     * Returns the coordinates of this vector in brackets as a string
     * in the form "[x, y, z]", e.g., "[1.48E11, 0.0, 0.0]".
     * @return 'this' represented as a string.
     */
    public String toString() {

        return "[%s, %s, %s]".formatted(x,y,z);
    }
}

