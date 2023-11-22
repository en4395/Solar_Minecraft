# Solar Powered Minecraft

## The mod up to now
At this stage, the mod is set up to display server data on a heads-up display (HUD). The CPU temperature is fetched
from the command line on the server side (see how this is done [here](https://github.com/en4395/Solar_Minecraft/blob/main/SolarMinecraft/src/main/java/solarminecraft/services/DataQueryProcess.java)) and updated on the client side every second. Server power draw data will be fetched and updated in a similar manner.

<img src = "https://github.com/en4395/Workshop_Images/blob/main/solar_minecraft_HUD.png" width=550> 

## How to play on the server
Instructions on joining the server are available [here](https://github.com/en4395/Solar_Minecraft/wiki/How-to-join-the-Solar-Minecraft-server)

## How to run the mod
- In the command line, enter `cd <directory where you want to keep the mod>` (e.g., `cd Desktop/`)
- Enter `git clone https://github.com/en4395/Solar_Minecraft`
- Enter `cd Solar_Minecraft/SolarMinecraft/`, this gets you inside the mod's directory
- Enter `./gradlew build`
- Wait for the build to complete. This should output a `Build Successful`
- Enter `./gradlew runClient` this will launch Minecraft with the loaded mod
