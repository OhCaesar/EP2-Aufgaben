package AB1;

import codedraw.CodeDraw;

// This class represents celestial bodies like stars, planets, asteroids, teapots, etc..
public class Body {

    //TODO: change modifiers.
    public double mass;
    public Vector3 massCenter; // position of the center of mass.
    public Vector3 currentMovement;

    //TODO: define constructor.

    // Returns the distance between the centers of mass of this body and the specified body 'b'.
    public double distanceTo(Body b) {

        //TODO: implement method.
        return 0;
    }

    // Returns the acceleration vector of 'this' that results from the gravitational interaction
    // with body 'b2'. This returned vector is computed according to Newton's laws of gravitation.
    // Hint: see method 'acceleration' in Simulation.java to find out how this is done.
    public Vector3 acceleration(Body b) {

        //TODO: implement method.
        return null;
    }

    // Accelerates this body for one second according to the specified 'acceleration' vector
    // and updates the current movement accordingly.
    // Hint: see method 'accelerate' in Simulation.java to find out how this is done.
    public void accelerate(Vector3 acceleration) {

        //TODO: implement method.
    }

    // Returns the approximate radius of this body.
    // (It is assumed that the radius r is related to the mass m of the body by
    // r = Math.pow(m, 0.5), where m and r measured in solar mass units.)
    public double getRadius() {

        //TODO: implement method.
        return 0d;
    }

    // Returns a new body that is formed by the collision of this body and 'b'. The mass of the
    // returned body is the sum of the masses of this body and 'b'. The current movement of the
    // returned body is given by the law of conservation of momentum. (The momentum of the
    // returned body is the sum of the momentums of 'this' and 'b').
    // Hint: see method 'merge' in Simulation.java to find out how this is done.
    public Body merge(Body b) {

        //TODO: implement method.
        return null;
    }

    // Draws this body to the specified canvas as a filled circle.
    // The radius of the circle corresponds to the radius of the body
    // (use a conversion of the real scale to the scale of the canvas as
    // in 'Simulation.java').
    // Hint: call the method 'drawAsFilledCircle' implemented in 'Vector3'.
    public void draw(CodeDraw cd) {

        //TODO: implement method.
    }

    // Returns a string with the information about this body including
    // mass, position (mass center) and current movement. Example:
    // "5.972E24 kg, position: [1.48E11, 0.0, 0.0] m, movement: [0.0, 29290.0, 0.0] m/s."
    public String toString() {

        //TODO: implement method.
        return "";
    }
}

