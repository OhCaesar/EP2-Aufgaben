package AB1;

import codedraw.CodeDraw;

/**
 * This class represents vectors in a 3D vector space.
 */
public class Vector3 {

    //TODO: change modifiers.
    public double x;
    public double y;
    public double z;

    //TODO: define constructor.

    /**
     * Returns the sum of this vector and vector 'v'.
     * @param v another vector, v != null.
     * @return the sum of this vector and vector 'v'.
     */
    public Vector3 plus(Vector3 v) {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns the product of this vector and 'd'.
     * @param d the coefficient for the product.
     * @return the product of this vector and 'd'.
     */
    public Vector3 times(double d) {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns the sum of this vector and -1*v.
     * @param v another vector, v != null.
     * @return the sum of this vector and -1*v.
     */
    public Vector3 minus(Vector3 v) {

        //TODO: implement method.
        return null;
    }

    /**
     * Returns the Euclidean distance of this vector to the specified vector 'v'.
     * @param v another vector, v != null.
     * @return the Euclidean distance of this vector to the specified vector 'v'.
     */
    public double distanceTo(Vector3 v) {

        //TODO: implement method.
        return -1d;
    }

    /**
     * Returns the norm of this vector.
     * @return the length (norm) of this vector.
     */
    public double length() {

        //TODO: implement method.
        return 0;
    }

    /**
     * Normalizes this vector: changes the length of this vector such that it becomes 1.
     * The direction of the vector is not affected.
     */
    public void normalize() {

        //TODO: implement method.
    }

    /**
     * Draws a filled circle with a specified radius centered at the (x,y) coordinates of this vector
     * in the canvas associated with 'cd'. The z-coordinate is not used.
     * @param cd the CodeDraw object used for drawing.
     * @param radius the radius > 0.
     */
    public void drawAsFilledCircle(CodeDraw cd, double radius) {

        //TODO: implement method.
    }

    /**
     * Returns the coordinates of this vector in brackets as a string
     * in the form "[x, y, z]", e.g., "[1.48E11, 0.0, 0.0]".
     * @return 'this' represented as a string.
     */
    public String toString() {

        //TODO: implement method.
        return "";
    }
}

