/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbesimserver;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author jmccartney
 */
public class MicrobeSimServer {

    private Simulation simulation = new Simulation();
    private boolean running = true;
    private boolean paused = false;
    private int tps = 60;
    private int tickCount = 0;

    public static void SimulationLoop() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MicrobeSimServer simLoop = new MicrobeSimServer();

        if (simLoop.running) {
            simLoop.runSimulationLoop();
        }
    }

    //Starts a new thread and runs the simulation loop in it.
    public void runSimulationLoop() {
        Thread loop = new Thread() {
            public void run() {
                simulationLoop();
            }
        };
        loop.start();
    }

    //Only run this in another Thread!
    private void simulationLoop() {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each tick should take for our target simulation hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the simulation this many times before a new broadcast.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_BROADCAST = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we broadcasted.
        double lastBroadcastTime = System.nanoTime();

        //If we are able to get as high as this TPS, don't broadcast again.
        final double TARGET_TPS = 60;
        final double TARGET_TIME_BETWEEN_BROADCASTS = 1000000000 / TARGET_TPS;

        //Simple way of finding TPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {
                //Do as many simulation updates as we need to, potentially playing catchup.
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_BROADCAST) {
                    updateSimulation();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of simulation that needed to keep EXACT time, you would get rid of this.
                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Broadcast. To do so, we need to calculate interpolation for a smooth broadcast.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
                broadcastSimulation(interpolation);
                lastBroadcastTime = now;

                //Update the ticks we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime) {
                    System.out.println("NEW SECOND " + thisSecond + " " + tickCount);
                    tps = tickCount;
                    tickCount = 0;
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between broadcasts. This saves the CPU from hogging.
                while (now - lastBroadcastTime < TARGET_TIME_BETWEEN_BROADCASTS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }

                    now = System.nanoTime();
                }
            }
        }
    }

    private void updateSimulation() {
        simulation.update();
    }

    private void broadcastSimulation(float interpolation) {
        simulation.setInterpolation(interpolation);
        simulation.broadcast();
    }

    private class Simulation {

        float interpolation;
        LinkedTransferQueue goCUpdateQueue;
        LinkedBlockingQueue goInSim;

        // float ballX, ballY, lastBallX, lastBallY;
        // int ballWidth, ballHeight;
        // float ballXVel, ballYVel;
        // float ballSpeed;
        // int lastDrawX, lastDrawY;
        public Simulation() {

            goCUpdateQueue = new LinkedTransferQueue();
            goInSim = new LinkedBlockingQueue();

            int count = 0;
            while (count < 100) {
                GOComponent physics_circle = new GOComponent_Physics_Circle("physics_circle");
                GOComponent physics = new GOComponent_Physics("physics");
                physics.attach(physics_circle);

                GOComponent script_entity = new GOComponent_Script("script_entity");
                GOComponent script_prop = new GOComponent_Script("script_prop");
                GOComponent script_decoration = new GOComponent_Script("script_decoration");
                GOComponent script = new GOComponent_Script("script");
                script.attach(script_entity);
                script.attach(script_prop);
                script.attach(script_decoration);

                GOComponent objectManager = new GOComponentManager("entity");
                objectManager.attach(script);
                objectManager.attach(physics);

                //add to list
                goInSim.add(objectManager);

                count++;

            }
            System.out.println("Initialied and put: " + goInSim.size() + " game objects into goInSim");

        }

        public void setInterpolation(float interp) {
            interpolation = interp;
        }

        public void update() {
            // lastBallX = ballX;
            // lastBallY = ballY;

        }

        public void broadcast() {

            // int drawX = (int) ((ballX - lastBallX) * interpolation + lastBallX - ballWidth/2);
            // int drawY = (int) ((ballY - lastBallY) * interpolation + lastBallY - ballHeight/2);
            tickCount++;
        }
    }

}
