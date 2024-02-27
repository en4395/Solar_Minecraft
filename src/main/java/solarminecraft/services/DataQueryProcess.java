package solarminecraft.services;

import solarminecraft.domain.SolarServerData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.lang.Process;

public class DataQueryProcess {

    public static float cpuTempProcess() {
//
//        GO FIND THE PATH TO CPU TEMP!
//
        String path = "/sys/class/thermal/thermal_zone2/temp";

        try {
            ProcessBuilder pb = new ProcessBuilder("cat", path);

            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String data = reader.readLine();
            float temp = (float)Integer.parseInt(data);
            temp /= 1000;

            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
            return temp;
        }

        catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
    For testing purposes. This will likely be similar to cpuTempProcess, but will
    read serial output from arduino
    */
    public static float powerProcess() {
        if(power < 50)
            power += 5;
        else
            power = 0;
        return power;
    }

    /*
    These variables are for testing purposes! The thread running on the server will
    ultimately just call cpuTempProcess() and powerProcess()
    */
    private static float temp = 40;
    private static float power = 30;

    public static float lazyTempProcess() {
       if(temp < 100)
           temp += 5;
       else temp = 0;
       return temp;
    }
}
