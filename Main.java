public class Main {
    /**
     * read the radius of the simulated universe
     * @param inputFile
     * @return
     */
    public static double readRadius(String inputFile) {
        In in = new In(inputFile);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /**
     * read the planets
     */
    public static Planet[] readPlanets(String inputFile) {
        In in = new In(inputFile);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[number];
        
        System.out.println(number + "---" + radius);

        for (int i = 0; i < number; i++) {
            planets[i] = new Planet(0,0,0,0,0,"");
            planets[i].xxPos = in.readDouble();
            planets[i].yyPos = in.readDouble();
            planets[i].xxVel = in.readDouble();
            planets[i].yyVel = in.readDouble();
            planets[i].mass = in.readDouble();
            planets[i].imgFileName = in.readString();
        }
        return planets;
    }

    public static void main(String[] args) {
        /* get all needed input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int planetnumber = planets.length;

        /* Create animation */
        StdDraw.enableDoubleBuffering();
        double t_ = 0;
        double[] xForces = new double[planetnumber];
        double[] yForces = new double[planetnumber];
        while (t_ < T) {
            /* Draw the background*/
            StdDraw.setScale(-radius, radius);
            /* Clears the drawing window */
            StdDraw.clear();
            /* Draws the universe background */
            StdDraw.picture(0,0, "images/bg_space.jpg");

            /** Calculates the netforce for each planet
             *  and update its position and velocity
             */
            for (int i = 0; i < planetnumber; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /**
             * Updates each planet's position and velocity
             */
            for (int i = 0; i < planetnumber; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);

            /* Increase the time variable */
            t_ += dt;
        }
        /**
         * Print out the final status of the planets
         */

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }




    }
}