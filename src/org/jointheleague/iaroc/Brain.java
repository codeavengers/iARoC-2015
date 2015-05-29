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
        int ir = getInfraredByte();
        if(isBumpRight()){
            driveDirect(-500, 0);
            SystemClock.sleep(2000);
            driveDirect(500, 500);
        }
        dashboard.log("." + ir);
      if (ir == 248){
          driveDirect(475,500);

      }
        if (ir == 244){
            driveDirect(500,475);
        }
        if (ir == 255){
            driveDirect(-500,500);

        }




}}