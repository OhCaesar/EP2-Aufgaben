package AB1;

import codedraw.CodeDraw;

/**
 * This class represents celestial bodies like stars, planets, asteroids, teapots, etc..
 */
public class Body {

    public static final double G = 6.6743e-11;


    private double mass;
    private Vector3 massCenter; // position of the center of mass.
    private Vector3 currentMovement;

    public double getMass() { return mass; }
    public Vector3 getMassCenter() { return massCenter; }

    public Body(double mass,Vector3 massCenter, Vector3 currentMovement){
        this.mass = mass;
        this.massCenter = massCenter;
        this.currentMovement= currentMovement;
    }

    /**
     * Returns the distance between the centers of mass of this body and the specified body 'b'.
     * @param b the specified body, b != null.
     * @return the distance between the centers of mass of this body and the specified body 'b'.
     */
    public double distanceTo(Body b) {
        return massCenter.distanceTo(b.massCenter);
    }

    /**
     * Returns the acceleration vector of 'this' that results from the gravitational interaction
     * with the specified body 'b'. The returned vector is computed according to Newton's laws of
     * gravitation.
     * Hint: see method 'acceleration' in Simulation.java to find out how this is done.
     * @param b the specified body, b != null.
     * @return the acceleration vector.
     */
    public Vector3 acceleration(Body b) {
        Vector3 direction = massCenter.minus(b.massCenter);
        double distance = direction.length();
        direction.normalize();
        double length = G * b.mass / (distance * distance);
        return direction.times(length);
    }

    /**
     * Accelerates this body for one second according to the specified 'acceleration' vector
     * and updates the current movement accordingly.
     * Hint: see method 'accelerate' in Simulation.java to find out how this is done.
     * @param acceleration the acceleration vector, acceleration != null.
     */
    public void accelerate(Vector3 acceleration) {
        currentMovement = acceleration.plus(currentMovement);
        massCenter = currentMovement.plus(massCenter);
    }

    /**
     * Returns the approximate radius of this body.
     * (It is assumed that the radius r is related to the mass m of the body by
     * r = Math.pow(m, 0.5), where m and r measured in solar mass units.)
     * @return the radius of this body.
     */
    public double getRadius() {
        return SpaceDraw.massToRadius(mass);
    }

    /**
     * Returns a new body that is formed by the collision of this body and 'b'. The mass of the
     * returned body is the sum of the masses of this body and 'b'. The current movement of the
     * returned body is given by the law of conservation of momentum. (The momentum of the
     * returned body is the sum of the momentums of 'this' and 'b').
     * Hint: see method 'merge' in Simulation.java to find out how this is done.
     * @param b the body with which 'this' is merged, b != null.
     * @return the body being formed by the collision.
     */
    public Body merge(Body b) {
        Body result = new Body(mass + b.mass,Vector3.defaultVector,Vector3.defaultVector);
        Vector3 times1 = massCenter.times(mass);
        Vector3 times2 = b.massCenter.times(b.mass);
        Vector3 sum = times1.plus(times2);
        result.massCenter = sum.times(1 / result.mass);

        // Momentum of a body corresponds to its velocity (currentMovement) times its mass.
        // Momentum v₃m₃ of result b₃ is the sum of the momentums of b₁ and b₂:
        // v₃m₃ = v₁m₁ + v₂m₂ -> v₃ = (v₁m₁ + v₂m₂)/m₃

        Vector3 mov1 = currentMovement.times(mass);
        Vector3 mov2 = b.currentMovement.times(b.mass);
        Vector3 movSum = mov1.plus(mov2);
        result.currentMovement = movSum.times(1 / result.mass);
        return result;
    }

    /**
     * Draws this body to the specified canvas as a filled circle.
     * The radius of the circle corresponds to the radius of the body
     * (use a conversion of the real scale to the scale of the canvas as
     * in 'Simulation.java').
     * Hint: call the method 'drawAsFilledCircle' implemented in 'Vector3'.
     * @param cd the CodeDraw object used for drawing, cd != null.
     */
    public void draw(CodeDraw cd) {
        cd.setColor(SpaceDraw.massToColor(mass));

        massCenter.drawAsFilledCircle(cd,Math.max(cd.getWidth() * getRadius() / Simulation.SECTION_SIZE, 1.5));


        //massCenter.drawAsFilledCircle(cd,getRadius());
    }

    /**
     * Returns a string with the information about this body including
     * mass, position (mass center) and current movement. Example:
     * "5.972E24 kg, position: [1.48E11, 0.0, 0.0] m, movement: [0.0, 29290.0, 0.0] m/s."
     * @return 'this' represented as a string.
     */
    public String toString() {
        return "%s kg, postion: %s m, movement: %s m/s.".formatted(mass,massCenter,currentMovement);
    }
}

