package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

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
        iARoc2016Maze();








    }



    private void iARoc2016GoldRush() throws ConnectionLostException {
        readSensors(SENSORS_INFRARED_BYTE);
        int ir = getInfraredByte();
        driveDirect(-100, 100);
        dashboard.log(ir + "");
        while(ir != 255) {//seeing nothing
            driveDirect(50, 50);
            SystemClock.sleep(2000);
            driveDirect(-100, 100);

        }
        if(ir == 245){
            driveDirect(50, 50);
        }
        if(ir == 246){
            driveDirect(50, 50);
        }
        if(ir == 247){
            driveDirect(50, 50);
        }

    }


    public void iARoc2015DragRace() throws ConnectionLostException {
        driveDirect(498, 499);
        if(isBumpRight()){
            driveDirect(0, -500);
            SystemClock.sleep(500);
            driveDirect(500, 500);
        }
        if(isBumpLeft()){
            driveDirect(-500, 0);
            SystemClock.sleep(500);
            driveDirect(500, 500);
        }
    }
    private void iARoc2016Maze() throws ConnectionLostException{
        int wall = getWallSignal();
        dashboard.log(wall + "");
       driveDirect(150, 500);
        if (wall > 3){
            driveDirect(400, 200);
            SystemClock.sleep(300);

        }
       readSensors(6);
        if (isBumpRight()) {
            driveDirect(100, -400);
            SystemClock.sleep(600);
            drive(300, 500);
            dashboard.log("BumpedRight!");

        }
//        if (isBumpLeft()){
//            driveDirect(-400, 0);
//            SystemClock.sleep(700);
//            drive(400, 500);
//
//        }
    }
}
