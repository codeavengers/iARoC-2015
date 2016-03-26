package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
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

        readSensors(SENSORS_INFRARED_BYTE);
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
       // int ir = getInfraredByte();
        iARoc2015GoldRush();
        SystemClock.sleep(1000);
        //iARoc2015DragRace();
//        driveDirect(500,500);
//        if(isBumpLeft());
//        {
//        driveDirect(-300,-500);
//            driveDirect(500,500);
//        }
//        if(isBumpRight());
//        {
//            driveDirect(-500,-300);
//            driveDirect(500,500);
//        }








    }

    public void iARoc2015GoldRush() throws ConnectionLostException{
        int ir = getInfraredByte();
        driveDirect(50, 50);
        SystemClock.sleep(1000);
        driveDirect(50, -50);
        while(ir == 255) {//seeing nothing

          driveDirect(50, -50);
            readSensors(SENSORS_INFRARED_BYTE);
            ir = getInfraredByte();
            dashboard.log(ir + "");
        }
        driveDirect(500, 500);
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
}
