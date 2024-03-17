package solarminecraft.services;

import solarminecraft.domain.SolarServerData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.lang.Process;



public class DataQueryProcess {
    
    enum SOLAR_DATA {
        TIMESTAMP, 
        PVVOLTAGE,
        PVCURRENT,
        PVPOWER,
        BATTVOLTAGE,
        BATTCHARGECURRENT,
        BATTCHARGEPOWER,
        LPOWER,
        BATTREMAINING,
        BATTTEMP,
        BATTOVERALLCURRENT,
        SYSTEMPOWERDRAW
    }

    public static float GetCPUTemp() {
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

    public static float GetSysPower() {
        return GetServerData(SOLAR_DATA.SYSTEMPOWERDRAW);
    }

    public static float GetServerData(SOLAR_DATA property) { 
        // dont use this function to get the timestamp. This function only returns a float. 

        if (property == SOLAR_DATA.TIMESTAMP) { 
            return -1f; 
        }

        String path = "/home/pc/serialread/solar_data.json";
        int count_lines = 14;

        try {
            ProcessBuilder pb = new ProcessBuilder("cat", path);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            for (int i = 0; i < count_lines; i++) {
                String data = reader.readLine().strip();

                if (data != null) { 
                    if (property == SOLAR_DATA.PVVOLTAGE && data.contains("pvvoltage")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.PVCURRENT && data.contains("pvcurrent")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.PVPOWER && data.contains("pvpower")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.BATTVOLTAGE && data.contains("bvoltage")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.BATTCHARGECURRENT && data.contains("battChargeCurrent")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.BATTCHARGEPOWER && data.contains("battChargePower")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.LPOWER && data.contains("lpower")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.BATTREMAINING && data.contains("bremaining")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.BATTTEMP && data.contains("btemp")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.BATTOVERALLCURRENT && data.contains("battOverallCurrent")){ 
                        return GetValue(data);  
                    } else if (property == SOLAR_DATA.SYSTEMPOWERDRAW && data.contains("powerdraw")){
                        return GetValue(data);
                    }
                }
            } 
            return 7.7f;
        } catch (Exception e) { 
            System.out.println("There was an error running this function");
            return -1f;
        } 
    }

    public static float GetValue(String data) { 
        // Extract the float value from the string entry from the JSON file
        
        float ret_val = 0.0f;
        String[] split_data = data.split(":", 2); 
        ret_val = Float.valueOf(split_data[1].replace('"', '\0'));

        return ret_val;
         
    }

    public static String GetTimestamp() { 
        String path = "/home/pc/serialread/solar_data.json";
        int count_lines = 2; // only need to read until the 2nd line 

        try {
            ProcessBuilder pb = new ProcessBuilder("cat", path);
            pb.redirectErrorStream(true);

            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String data = "";
            for (int i = 0; i < count_lines; i++) 
                data = reader.readLine().strip();

            if (data != null) { 
                data = data.split(":", 2)[1];
            }
            return data; 
        } catch (Exception e) { 
            System.out.println("ERROR: There was an error calling GetTimestamp: " + e.getMessage() );
            return "";
        }    
    }

}
