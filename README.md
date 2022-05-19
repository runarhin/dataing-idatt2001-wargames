# Wargames

This repository contains an application which demonstrates a simulation between two battling armies.
A random unit from each army will excessively attack each other until either of the armies are empty of units, which then leads to the opponent standing victorious.

The application was due 24th of May 2022, as a part of the semester assignment in the subject IDATT2001 Programming 2 at the Norwegian University of Science and Technology.

## Running the program

To run the program the following is needed:
- JDK-17 
- Maven 3.8.4
- JavaFX

Install the program by downloading the zip-files or by cloning via the following git links:
- git@gitlab.stud.idi.ntnu.no:runarin/wargames.git
- https://gitlab.stud.idi.ntnu.no/runarin/wargames.git


## Documentation

GitLab pages: ???


## Release 1.0

### Implemented classes:
- Unit - Abstract super class holding a general set of data concerning a battling unit, in addition to two abstract methods to be extended.
- InfantryUnit - Extends Unit. Basic melee class with simple bonuses.
- RangeUnit - Extends Unit. Attacks from afar, and takes less resistance over time.
- CavalryUnit - Extends Unit. Strong initial charge attack, and normal attack afterwards.
- CommanderUnit - Extends CavalryUnit. Has higher attack and resistance than the CavalryUnit.
- Army - Representing an army which holds multiple units. Can add and delete units as it fits.
- Battle - Contains method for simulating a battle between two armies.
- WarGamesSimpleClient - Client to test the simulation by simple methods.

In addition to respective unit tests:
- UnitTest, InfantryUnitTest, RangedUnitTest, CavalryUnitTest, CommanderUnitTest, ArmyTest, BattleTest


## Release 2.0

### Implemented methods by using streams and lambda:
- getInfantryUnits()
- getRangedUnits()
- getCavalryUnits()
- getCommanderUnits()

### Implemented classes:
- FileHandler - Static class handling reading and writing to CSV-files.
- FileHandlerTest
- CorruptedFileException


## Release 3.0

### Implemented classes:
- UnitFactory - Factory class creating multiple identical units at the same time.
- UnitFactoryTest
- UnitType - Enum class containing each type of specialized unit.
- TerrainType - Enum class containing one of the three terrain types; FOREST, HILL, and PLAINS.
- WarGamesController - MVC. Handles data flow and actions between the view and model.
- WarGamesModel - MVC. Stores data to be used during runtime.
- WarGamesApp - MVC. Initializes the view files (.fxml).
- ArmyEmptyOfUnitsException


## Final release 4.0

### Implemented classes:
- ArmySelect - Enum class to hold information about what army to add units to.
- ArmyWrapper - Wrapper class to present relevant and updated information about an army to the view.
- AddUnitsController - Handles a new stage for adding units to an army by UnitFactory.
