# Displaying Server Stats 

* The server reads the stats from the `JSON` file produced from this python script.
* This file contains 12 readings namely:
  1. `timestamp`,
  2. `pvvoltage`,
  3. `pvcurrent`,
  4. `pvpower`,
  5. `bvoltage`,
  6. `battChargeCurrent`,
  7. `battChargePower`,
  8. `lpower`,
  9. `bremaining`,
  10. `btemp`,
  11. `battOverallCurrent`,
  12. `powerDraw`.
* These readings are read by the modpack in the file [DataQueryProcess.java](src/main/java/solarminecraft/services/DataQueryProcess.java).
* This file reads and parses the data for each reading, and each of them has a wrapper `GET` function.
* Each of these functions is called by the [ServerSetup.java](src/main/java/solarminecraft/services/setup/ServerSetup.java) file. This file refreshes these readings every second.
* After reading these values, they are used as the constructor values for a `ServerDataS2CPacket` object. (This object is defined [here](src/main/java/solarminecraft/application/network/packets/ServerDataS2CPacket.java))
* The `ServerDataS2CPacket` object is sent over to the Client via the [ModPackets.SendToClient(...)](src/main/java/solarminecraft/application/network/ModPackets.java) method.
* On the client side, the `ServerDataS2CPacket` sets the static class values in the [SolarServerData](src/main/java/solarminecraft/domain/SolarServerData.java) object.
* The `SolarServerData` Object is contained within the [ClientSetup](src/main/java/solarminecraft/services/setup/ClientSetup.java) file.
* The [ClientEventHandler](src/main/java/solarminecraft/application/client/ClientEventHandler.java) uses the `SolarServerData` object from the `ClientSetup` object, to get all these values and render them on the HUD.      
