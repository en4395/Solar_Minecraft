# Solar Powered Minecraft
`Version 1.0.1`

The Solar Minecraft Project is a research project at Concordia University Montreal, under the supervision of Dr. Bart 
Simon & Dr. Darren Wershler. 

The project is exploring alternate minecraft game mechanics and play-styles when integrating 
Real-Time Solar and Power usage data into the minecraft world. 

This forge mod, running on a server, uses the server's stats to alter the gameplay for all players online. 

Please see the [docs folder](./docs/) for more documentation.
## Getting Started 
- Clone the repository and open a terminal in the root directory of the project.
- Run `./gradlew build` to fetch and compile Gradle. This should output `Build Successful`. 
- Run `./gradlew runClient` to compile the source code and run it in Minecraft.

## Running The Mod: 
- For the JAR file, please see [releases](https://github.com/estineali/Solar_Minecraft/releases).
- For instructions on how to run this with forge, see [this wiki link](https://github.com/en4395/Solar_Minecraft/wiki/How-to-join-the-Solar-Minecraft-server).  

## The Mod up to now
At this stage, the mod is set up to display server data on a heads-up display (HUD). 

The CPU temperature values are fetched from the command line on the server side (see how this is done [here](https://github.com/en4395/Solar_Minecraft/blob/main/src/main/java/solarminecraft/services/DataQueryProcess.java)). 

The server also increments a power draw variable. These are test values for demonstration purpose and DO NOT CORRESPOND TO REAL SYSTEM POWER DRAW.

<img src = "https://github.com/en4395/Workshop_Images/blob/main/solar_minecraft_HUD.png" width=550> 

## Contributors: 
1. [Ella Noyes](https://github.com/en4395)
2. [Shahrom Ali](https://github.com/estineali)

## Technologies Used 
1. Java 17 - Eclipse Temurin Version
2. Minecraft Forge `1.20.2`
3. Gradle
