package solarminecraft.domain;

import solarminecraft.SolarMinecraft;


// MSA: I think defining vanilla getters and Setters is a terrible
// idea, considering the are the same for all. 
// Might wanna refactor this to just public values, or implement validation
// within each getter. 
// I am only doing this to continue the pattern followed in the existing code. 

public class SolarServerData {
    private static float power;
    private static float cpuTemp;
    private static float pvVoltage;
    private static float pvCurrent;
    private static float pvPower;
    private static float battVoltage;
    private static float battChargeCurrent;
    private static float battChargePower;
    private static float lPower;
    private static float battRemaining;
    private static float battTemp;
    private static float battOverallCurrent;

    public SolarServerData(float power, float cpuTemp) {
        SolarServerData.power = power;
        SolarServerData.cpuTemp = cpuTemp;
        SolarServerData.pvVoltage = 0.0f;
        SolarServerData.pvCurrent = 0.0f;
        SolarServerData.pvPower = 0.0f;
        SolarServerData.battVoltage = 0.0f;
        SolarServerData.battChargeCurrent = 0.0f;
        SolarServerData.battChargePower = 0.0f;
        SolarServerData.lPower = 0.0f;
        SolarServerData.battRemaining = 0.0f;
        SolarServerData.battTemp = 0.0f;
        SolarServerData.battOverallCurrent = 0.0f;
    }

    //#region GETTERS 

    public static float getPower() {
        return power;
    }

    public static float getCpuTemp() {
        return cpuTemp;
    }

    public static float getPvVoltage() {
        return pvVoltage;
    }

    public static float getPvCurrent() {
        return pvCurrent;
    }

    public static float getPvPower() {
        return pvPower;
    }

    public static float getBattVoltage() {
        return battVoltage;
    }

    public static float getBattChargeCurrent() {
        return battChargeCurrent;
    }

    public static float getBattChargePower() {
        return battChargePower;
    }

    public static float getlPower() {
        return lPower;
    }

    public static float getBattRemaining() {
        return battRemaining;
    }

    public static float getBattTemp() {
        return battTemp;
    }

    public static float getBattOverallCurrent() {
        return battOverallCurrent;
    }
    ////#endregion

    //#region SETTERS

    public static void setPower(float pw) {
        power = pw;
    }

    public static void setCpuTemp(float temp) {
        cpuTemp = temp;
    }
    public static void setPvVoltage(float pvVoltage) {
        SolarServerData.pvVoltage = pvVoltage;
    }

    public static void setPvCurrent(float pvCurrent) {
        SolarServerData.pvCurrent = pvCurrent;
    }

    public static void setPvPower(float pvPower) {
        SolarServerData.pvPower = pvPower;
    }

    public static void setBattVoltage(float battVoltage) {
        SolarServerData.battVoltage = battVoltage;
    }

    public static void setBattChargeCurrent(float battChargeCurrent) {
        SolarServerData.battChargeCurrent = battChargeCurrent;
    }

    public static void setBattChargePower(float battChargePower) {
        SolarServerData.battChargePower = battChargePower;
    }

    public static void setlPower(float lPower) {
        SolarServerData.lPower = lPower;
    }

    public static void setBattRemaining(float battRemaining) {
        SolarServerData.battRemaining = battRemaining;
    }

    public static void setBattTemp(float battTemp) {
        SolarServerData.battTemp = battTemp;
    }

    public static void setBattOverallCurrent(float battOverallCurrent) {
        SolarServerData.battOverallCurrent = battOverallCurrent;
    }
    ////#endregion
    
}
