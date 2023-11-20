# Solar Powered Minecraft

## The mod up to now
At this stage, the mod is set up to display server data on a heads-up display (HUD). To demonstrate how the server will update the solar server data on the client's end, the server increments a value for CPU temperature and power draw. Once the modded server is up, these values will be fetched
from the command line on the server side (see how this is done [here](https://github.com/en4395/Solar_Minecraft/blob/main/SolarMinecraft/src/main/java/solarminecraft/services/DataQueryProcess.java)). 


<img src = "https://github.com/en4395/Workshop_Images/blob/main/solar_minecraft_HUD.png" width=550> 

## How to run the mod
- In the command line, enter `cd <directory where you want to keep the mod>` (e.g., `cd Desktop/`)
- Enter `git clone https://github.com/en4395/Solar_Minecraft`
- Enter `cd Solar_Minecraft/SolarMinecraft/`, this gets you inside the mod's directory
- Enter `./gradlew build`
- Wait for the build to complete. This should output a `Build Successful`
- Enter `./gradlew runClient` this will launch Minecraft with the loaded mod
