package solarminecraft.domain;

import solarminecraft.SolarMinecraft;

public class SolarServerData {
    private static float power;
    private static float cpuTemp;
    public SolarServerData(float power, float cpuTemp) {
        this.power = power;
        this.cpuTemp = cpuTemp;
    }

    public static float getPower() {
        return power;
    }

    public static float getCpuTemp() {
        return cpuTemp;
    }

    public static void setPower(float pw) {
        power = pw;
    }

    public static void setCpuTemp(float temp) {
        cpuTemp = temp;
    }
}
