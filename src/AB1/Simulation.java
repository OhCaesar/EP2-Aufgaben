package AB1;

import codedraw.*;

import java.awt.*;
import java.util.Random;
/*
1.  Was versteht man unter Datenkapselung? Geben Sie ein Beispiel, wo dieses Konzept in dieser
    Aufgabenstellung angewendet wird.

    Die Datenkapselung befasst sich damit einzelne Variablen und Methoden nur im Funktionalen zusammenhang für äußeren Zugriff anzubieten.
    In diesem Beispiel wird die Datenkapselung dazu verwendet um Beispielsweise die Variablen X,Y und Z der Klasse Vektor vor direktem zugriff zu schützen indem keine möglichkeit gegeben wird
    die einzelnen Werte seperat zu verändern sondern nur im Raume des Programmablaufs die mit den Methoden
    - Plus - Minus - Normalize - Times zu Klassenexternen access zur verfügung gestellt werden.

2.  Was versteht man unter Data Hiding? Geben Sie ein Beispiel, wo dieses Konzept in dieser
    Aufgabenstellung angewendet wird.

    Bei Data hiding geht es um den Grundsatz Variablen oder Methoden vor äußerem Zugriff zu bewahren.
    Dies wird in Java per modifier geregelt (public - private)
    Beispiel :
        - Klasse (Vector3)
            Variablen :
                - private x,y,z (regulierter Access)
                - Body.x -> nicht möglich
        - Klasse (Body)
            Variablen :
                - private mass , massCenter , currentMovement
                - Body.mass -> nicht möglich

3.  Was steht bei einem Methodenaufruf links vom . (z.B. bei SpaceDraw.massToColor(1e30) oder
    body.radius())? Woran erkennt man dabei Objektmethoden?

    Aufbau eines Aufrufs :

    [Klassenname].[Methode()/Variablenname]

    Beispiel :

    Vector3 vector = new Vector3(0,0,0);
    vector.length();              -----> Aufruf

    Objektmethode :
    Eine Objektmethode kann nur bei instanzierung eines Objekts (benützung des Konstruktors) aufgerufen werden und exisitiert nur im Objektkontext.
    Die Funktionalität bezieht sich dabei auf Objektinterne Variablen und ist abhängig vom status des instanzierten Objekts

    Klassenmethoden :
    Eine Klassenmethode ist eine statisch definierte methode welche keinen Objektstatus verfolgt.
    Dabei muss kein Objekt existieren und sie bezieht sich NICHT auf gespeicherte Variablenwerte.
    Dementsprechend gibt es nur eine variante dieser Methode.

    Erkennung von Objektmethoden :
    Wenn die Namenssignatur der Klasse auf der linken Seite des Punkts steht handelt es sich um eine Klassenmethode
    Wenn andererseits ein instanziertes Objekt am Aufrufstart steht, erkennt man dies nicht nur an der konventionellen kleinschreibung des
    Variablennamens sondern auch an der zuvor aufgerufenen instanzierung des Objekts der gegebenen Klasse.
 */

/**
 * Simulates the formation of a massive solar system.
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
    public static final double SECTION_SIZE = 2 * AU; // the size of the square region in space
    public static final int NUMBER_OF_BODIES = 100;
    public static final double OVERALL_SYSTEM_MASS = 30 * SUN_MASS; // kilograms

    // all quantities are based on units of kilogram respectively second and meter.

    /**
     * The main simulation method using instances of other classes.
     *
     * @param args not used.
     */
    public static void main(String[] args) {

        CodeDraw cd = new CodeDraw();
        Body[] bodies = new Body[NUMBER_OF_BODIES];
        Vector3[] accelerationOfBody = new Vector3[bodies.length];

        Random random = new Random(2024);

        for (int i = 0; i < bodies.length; i++) {
            bodies[i] = new Body(Math.abs(random.nextGaussian()) * OVERALL_SYSTEM_MASS / bodies.length,
                    new Vector3(0.2 * random.nextGaussian() * AU,
                            0.2 * random.nextGaussian() * AU,
                            0.2 * random.nextGaussian() * AU),
                    new Vector3(random.nextGaussian() * 5e3,random.nextGaussian() * 5e3,random.nextGaussian() * 5e3));

        }

        double seconds = 0;

        // simulation loop
        while (true) {
            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // merge bodies that have collided
            for (int i = 0; i < bodies.length; i++) {
                for (int j = i + 1; j < bodies.length; j++) {
                    if (bodies[i].getMassCenter().distanceTo(bodies[j].getMassCenter()) <
                            SpaceDraw.massToRadius(bodies[j].getMass()) + SpaceDraw.massToRadius(bodies[i].getMass())) {
                        // collision of bodies i and j
                        bodies[i] = bodies[j].merge(bodies[i]);

                        // generate a duplicate of the array with body j removed.
                        Body[] bodiesOneRemoved = new Body[bodies.length - 1];
                        for (int k = 0; k < bodiesOneRemoved.length; k++) {
                            bodiesOneRemoved[k] = bodies[k < j ? k : k + 1];
                        }
                        bodies = bodiesOneRemoved;

                        // since the body index i changed size there might be new collisions
                        // at all positions of bodies, so start all over again
                        i = -1;
                        j = bodies.length;
                    }
                }
            }

            // for each body (with index i): compute its total acceleration.
            for (int i = 0; i < bodies.length; i++) {
                accelerationOfBody[i] = Vector3.defaultVector; // begin with zero
                for (int j = 0; j < bodies.length; j++) {
                    if (i != j) {
                        Vector3 toAdd = bodies[i].acceleration(bodies[j]);
                        accelerationOfBody[i] = accelerationOfBody[i].plus(toAdd);
                    }
                }
            }
            // now accelerationOfBody[i] holds the total acceleration vector for bodies[i].

            // for each body (with index i): accelerate it for one second.
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].accelerate(accelerationOfBody[i]);
            }

            // show all movements in the canvas only every hour (to speed up the simulation)
            if (seconds % (3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                cd.clear(Color.BLACK);

                // draw new positions
                for (Body body : bodies) {
                    body.draw(cd);
                }
                // show new positions
                cd.show();
            }

        }

    }
}