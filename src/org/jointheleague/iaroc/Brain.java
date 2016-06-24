package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

import java.util.Random;

public class
        Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;
    int number = 0;
    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?
        //driveDirect(500, 500);
    }

    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {
        readSensors(SENSORS_WALL_SIGNAL);
        readSensors(SENSORS_INFRARED_BYTE);
       readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        //iARoc2016Maze();  //  (We finished this one)
        iARoc2016GoldRush();
      // iARoc2016DragRace();
        //simpleTurn();
    }




//    int angl = 0;
//    private void simpleTurn()throws ConnectionLostException {
//        readSensors(6);
//        angl += getAngle();
//
//       // driveDirect(200, -200);
//        dashboard.log("angle: " + angl);
//
//        if(angl >= 354){
//            driveDirect(500, 500);
//        }else{
//            driveDirect(200, -200);
//        }
//
//    if(isBumpLeft() && isBumpRight()){
//        dashboard.log("Bumped");
//    }
//    }
           int angle = 0;
    int distance = 0;
    private void iARoc2016GoldRush() throws ConnectionLostException {
        readSensors(6);
        Random rand = new Random();
        int value = rand.nextInt(2);
        int ir = getInfraredByte();
        //angle += getAngle();
        dashboard.log(ir + "");
        dashboard.log("angle: " + angle);
       distance += getDistance();
        angle += getAngle();

        if(angle >= 253){
            driveDirect(500, 500);
            readSensors(6);
            dashboard.log(distance + "");
            if(distance >= 400){
                dashboard.log("Inside if statement");
                driveDirect(200, -200);
                readSensors(6);
            }
        }else{
            driveDirect(200, -200);
            readSensors(6);
        }

        if(ir != 255) {//seeing nothing
            driveDirect(50, 50);
        }

        if(isBumpLeft() && isBumpRight()){
            dashboard.log("BumpedFront");
            driveDirect(-500, -500);
            SystemClock.sleep(500);
            if(value == 0) {
                driveDirect(250, 500); // Turn Left
                SystemClock.sleep(1000);
            }else{
                driveDirect(500, 250); // Turn Right
                SystemClock.sleep(1000);
            }
        }
        else if(isBumpRight()){
            dashboard.log("BumpedRight");
            driveDirect(-500, -500);
            SystemClock.sleep(500);
            driveDirect(500, 250);
            SystemClock.sleep(1000);
        }
        else if(isBumpLeft()){
            dashboard.log("BumpedLeft");
            driveDirect(-500, -500);
            SystemClock.sleep(500);
            driveDirect(250, 500);
            SystemClock.sleep(1000);
        }
        }



    public void iARoc2016DragRace() throws ConnectionLostException {
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        readSensors(SENSORS_ANGLE);
        driveDirect(500, 500);
        SystemClock.sleep(1500);
        readSensors(SENSORS_ANGLE);
        int angle = getAngle();
        dashboard.log(angle + "");

        if (angle >= 8) {
            readSensors(SENSORS_ANGLE);
            driveDirect(500, 480);
            SystemClock.sleep(400);
            readSensors(SENSORS_ANGLE);
            int angleTurning = getAngle();
            if (angleTurning > angle) {
                driveDirect(480, 500);
                SystemClock.sleep(500);
            }
        }

        if (isBumpRight() && isBumpLeft()) {
            dashboard.log("Bumped front!!!!");
            driveDirect(-500, -500);
            SystemClock.sleep(500);
            driveDirect(500, -500);
            SystemClock.sleep(813);

        }else if (isBumpRight()) {
            dashboard.log("Bumped right!!!!");
            driveDirect(-500, -500);
            SystemClock.sleep(500);
            driveDirect(500, -500);
            SystemClock.sleep(813);

        }
        else if (isBumpLeft()){
            dashboard.log("Bumped left!!!!");
            driveDirect(-500, -500);
            SystemClock.sleep(500);
            driveDirect(500, -500);
            SystemClock.sleep(813);

        }

    }

    private void iARoc2016Maze() throws ConnectionLostException {
        readSensors(6);
       // driveDirect(200, 500); // Right
        driveDirect(500, 200); // Left

//        if (isBumpRight()) {
//            driveDirect(300, -400);
//            SystemClock.sleep(10);
//        // driveDirect(25, 500);
//            dashboard.log("BumpedRight!");
//      }
        if (isBumpLeft()) {
            driveDirect(-400, 300);
            SystemClock.sleep(10);
            // driveDirect(25, 500);
            dashboard.log("BumpedLeft!");

        }
    }
}
