# Solar Powered Minecraft

The Solar Minecraft Project is a research project at Concordia University Montreal, under the supervision of Dr. Bart 
Simon & Dr. Darren Wershler. 

The project is exploring alternate minecraft game mechanics and play-styles when integrating 
Real-Time Solar and Power usage data into the minecraft world. 

This mod, running on a server, uses the server's stats to alter the gameplay for all players online.

## Getting Started 
- Clone the repository and open a terminal in the root directory of the project.
- Run `./gradlew build` to fetch and compile Gradle. This should output `Build Successful`. 
- Run `./gradlew runClient` to compile the source code and run it in Minecraft. 

## The Mod up to now
At this stage, the mod is set up to display server data on a heads-up display (HUD). 
The CPU temperature values are fetched
from the command line on the server side (see how this is done [here](https://github.com/en4395/Solar_Minecraft/blob/main/SolarMinecraft/src/main/java/solarminecraft/services/DataQueryProcess.java)). 
The server also increments a power draw variable for demonstration purposes.

<img src = "https://github.com/en4395/Workshop_Images/blob/main/solar_minecraft_HUD.png" width=550> 


