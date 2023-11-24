package solarminecraft.domain;

import solarminecraft.SolarMinecraft;

public class SolarServerData {
    private static float power;
    private static float cpuTemp;
    public static float batteryPercentage;

    public SolarServerData(float power, float cpuTemp, float batteryPercentage) {
        this.power = power;
        this.cpuTemp = cpuTemp;
        this.batteryPercentage = batteryPercentage;
    }

    public static float getPower() {
        return power;
    }

    public static float getCpuTemp() {
        return cpuTemp;
    }

    public static float getBatteryPercentage() { return batteryPercentage;}

    public static void setPower(float pw) {
        power = pw;
    }

    public static void setCpuTemp(float temp) {
        cpuTemp = temp;
    }

    public static void setBatteryPercentage(float battery) {batteryPercentage = battery;}
}
