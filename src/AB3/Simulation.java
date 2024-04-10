package AB3;

import AB1.SpaceDraw;
import AB1.Vector3;
import AB2.Body;
import codedraw.CodeDraw;

import java.awt.*;
import java.util.Random;

/**
 * Simulates a cluster of bodies.
 */
public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance between earth and sun.
    public static final double AU = 150e9; // meters

    // some further constants needed in the simulation
    public static final double SUN_MASS = 1.989e30; // kilograms
    public static final double SUN_RADIUS = 696340e3; // meters

    // set some system parameters
    public static final int NUMBER_OF_BODIES = 50;
    public static final double OVERALL_SYSTEM_MASS = 30 * SUN_MASS; // kilograms

    // all quantities are based on units of kilogram respectively second and meter.

    // TODO: implement according to 'Aufgabenblatt3.md'.


    /**
     * The main simulation method using instances of other classes.
     *
     * @param args not used.
     */
    public static void main(String[] args) {

        CodeDraw cd = new CodeDraw();
        //
        BodyQueue bodies = new BodyQueue();
        //BodyAccMap - accelaration
        BodyAccelerationTreeMap accelerationOfBody = new BodyAccelerationTreeMap();

        Random random = new Random(2024);

        for (int i = 0; i < NUMBER_OF_BODIES; i++) {
            bodies.add(new Body(Math.abs(random.nextGaussian()) * OVERALL_SYSTEM_MASS / NUMBER_OF_BODIES,
                    new Vector3(0.2 * random.nextGaussian() * AU,
                            0.2 * random.nextGaussian() * AU,
                            0.2 * random.nextGaussian() * AU),
                    new Vector3(random.nextGaussian() * 5e3,random.nextGaussian() * 5e3,random.nextGaussian() * 5e3)));

        }

        double seconds = 0;

        // simulation loop
        while (true) {
            seconds++; // each iteration computes the movement of the celestial bodies within one second.


            // merge bodies that have collided

            for (int i = 0; i < bodies.size(); i++) {
                Body currentBody = bodies.poll();
                for (int j = i + 1; j < bodies.size(); j++) {
                    Body currentBody2 = bodies.poll();
                    if (currentBody.getMassCenter().distanceTo(currentBody2.getMassCenter()) <
                            SpaceDraw.massToRadius(currentBody2.getMass()) + SpaceDraw.massToRadius(currentBody.getMass())) {
                        // collision of bodies i and j
                        currentBody = currentBody2.merge(currentBody);

                        // since the body index i changed size there might be new collisions
                        // at all positions of bodies, so start all over again
                        i = -1;
                        j = bodies.size();
                    } else {
                        bodies.add(currentBody2);
                    }
                }
                bodies.add(currentBody);
            }


            // for each body (with index i): compute its total acceleration.
            for (int i = 0; i < bodies.size(); i++) {
                Body currentBody = bodies.poll();
                accelerationOfBody.put(currentBody,Vector3.defaultVector); // begin with zero
                for (int j = 0; j < bodies.size(); j++) {
                    Body changingBody = bodies.poll();
                    Vector3 toAdd = currentBody.acceleration(changingBody);
                    accelerationOfBody.put(currentBody,accelerationOfBody.get(currentBody).plus(toAdd));
                    bodies.add(changingBody);
                }
                bodies.add(currentBody);
            }
            // now accelerationOfBody[i] holds the total acceleration vector for bodies[i].

            // for each body (with index i): accelerate it for one second.
            for (int i = 0; i < bodies.size(); i++) {
                Body currentBody = bodies.poll();
                currentBody.accelerate(accelerationOfBody.get(currentBody));
                bodies.add(currentBody);
            }

            // show all movements in the canvas only every hour (to speed up the simulation)
            if (seconds % (3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                cd.clear(Color.BLACK);

                // draw new positions
                for (int i = 0; i < bodies.size(); i++) {
                    Body body = bodies.poll();
                    body.draw(cd);
                    bodies.add(body);
                }
                // show new positions
                cd.show();
            }

        }

    }
}