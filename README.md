# Solar Powered Minecraft

## The mod up to now
At this stage, the mod is set up to display server data on a heads-up display (HUD). The CPU temperature values are fetched
from the command line on the server side (see how this is done [here](https://github.com/en4395/Solar_Minecraft/blob/main/SolarMinecraft/src/main/java/solarminecraft/services/DataQueryProcess.java)). The server also increments a power draw variable for demonstration purposes.


<img src = "https://github.com/en4395/Workshop_Images/blob/main/solar_minecraft_HUD.png" width=550> 

## How to run the mod
- In the command line, enter `cd <directory where you want to keep the mod>` (e.g., `cd Desktop/`)
- Enter `git clone https://github.com/en4395/Solar_Minecraft`
- Enter `cd Solar_Minecraft/SolarMinecraft/`, this gets you inside the mod's directory
- Enter `./gradlew build`
- Wait for the build to complete. This should output a `Build Successful`
- Enter `./gradlew runClient` this will launch Minecraft with the loaded mod
