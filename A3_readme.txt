README

Game: Stickman
Version: 2.0

If cannot run in Ed, please download it and run in local environment by using JAVA 11+

*** How to run ***
1. Input ‘gradle build’ in terminal
2. Input ‘gradle run’ in terminal


*** UPDATE ***
1. Modify the *** AIM *** part for extension part.
2. Add new button can be pressed for *** Controls **** part, S: Quick Save, Q: Quick Load
3. Modify *** Configuration File *** part for extension part.
4. Change the example of JSON file, - we can get target time by "timer"
                                    - we can choose what color slime we create instead of random color
5. New lable *** Features *** contains some clarifications for extension part.
6. Design Pattern Application: 
   Strategy Pattern used in Slime's behaviour distinguish by colour - in stickman.strategy package
   Memento Pattern used in quick save and quick load - in stickman.memento package


*** AIM ***

The aim of the Stickman game is to make it to touch the finish flag.
*(For easily test, the flags seted near the start position for 3 levels now)
Picking up a mushroom will enable you to shoot bullets.
If you fall off the bottom of the screen or you are touched by an enemy slime, then you 
will die and the level will start again. *The stickman has 3 lives now!*

Level transition will applied when there are still some levels behind the current level.
1. If the current level is the last of level, a "You Win" banner will come down from the top. 
2. If the stickman loses all three lives, a "Game Over" banner will come down from the top.
At these two point, you can press ESC to restart the level. --- Play as much as you want!!! ---


*** Features ***
1. Lives left will display as first line at the left top corner.
   1.1. Lives will reduce when stickman touches slime or stickman falls down
   1.2. Lives will reset when press ESC after any type of banner appears
   1.3. Lives changes like 3 - 2 - 1 - 0 (in normal case)
2. Residual Time will display as second line at the left top corner.
   2.1. Residual Time will reduce 1 per seconds 
        (counting down, even can be negative, due to punishment over target time)
   2.2. Residual Time will reset when transit to next level as its target time.
3. Target Time will display as third line at the left top corner.
   3.1. Target Time will not change except level transition to get current level's target.
4. Current Score will display as forth line at the left top corner.
   4.1. Current Score will be added 100 points after killing a slime in real time.
   4.2. Current Score will be added 50 points after eating a mushroom in real time.
   4.3. Current Score will reset as 0 for this level after one live lost 
        (If we do not reset, the mushroom & slime points will remain)
   4.4. Current Score will be initialized as 0.
        (a little bit confused about the requirement - initial is 0 & counting down to 0)
        (But I still choose to initialize it as 0, and the residual time will count down to 0 even negative)
5. Previous Score will display as fifth line at the left top corner.
   5.1. Previous Score will be added after level transition.
   5.2. Previous Score will be calculated as Residual Time + Current Score(previous level's) + Previous Score(previous level's)
        For example, for previous level, the residual time is 50, current score is 100, previous score is 100,
        the new previous score will be 50 + 100 + 100 = 250
   5.3. Previous Score will be changed to Total Score after game win, and calculate as previous score + current score.
   5.4. Total score will be 0 after all three lives lost.
6. Some easier test way here, 
   6.1. For level 1, there are four types of different colour slime with its own strategy will behave.
        SAVE: Before testing others, you can save at the first.
        In order to test easily, the mushroom is setted near to start position of hero.
        The saved state of displayed text will be printed in terminal.
        Once you eat mushroom, you could jump and shoot with movement on platform firstly. (points added)
        If you go downward before you kill all slimes, stickman may be killed by touching slime.
        LOAD: After you kill all the slimes, you can try to load the saved state. It will return to the saved state.
        The loaded state of displayed text will be printed in terminal.
        Then jumping to next platform to touch the flag, then level transited to next level 2.
        The target time for level 1 is 400 seconds.
   6.2. For level 2, there is only slime here. You can jump downward to get the mushroom.
        Then shoot the slime, and back to the platform, the flag is setted at the higher platform.
        Then jumping to higher platform to touch the flag, then level transited to level 3.
        The target time for level 2 is 300 seconds.
   6.3. For level 3, the flag is setted near to the start position of stickman.
        Just turn back to touch the flag, then the "You Win" banner will appear and nothing can move.
        The target time for level 3 is 200 seconds.
   6.4. For "Gave Over" banner function test, you can just jump downward in level 1 three times.

*** Controls ***

Left: Left Arrow

Right: Right Arrow

Jump: Up arrow
		NOTE: You can only jump a maximum time of two jumps.
			After this the Stickman will fall back to the 
			ground, when you can now jump again.

Shoot: [Left Arrow | Right Arrow] + Spacebar.
		NOTE: Shoot will not work with just the spacebar or before a mushroom is gathered.

Restart: ESCAPE
        NOTE: You can restart the whole game when you win the game or lose all three lives.

Quick Save: S
        NOTE: 1. Save all the states of level, times, lives, scores of whole game in memento.
              2. Save all the entities of current level in memento.
              3. You can only save one state in memento not in disk,
                 if you press two times, it will overwrite exsiting saved state.

Quick Load: Q 
        NOTE: Reload all the things in memento, 
              including ll the states of level, times, lives, scores of whole game,
              and all the entities of saved state with the same position.
              (some issues here, but if refer to the way I provided, it can be load correctly)


*** Configuration File ***

The configuration file can be found at "src/main/resources/config.json".
The levels are generated by data specified within the configuration file. 
The file is loaded by the App class and fed into the GameEngineImp class as a JSONOject.
All level data is caputured in the "levels" section of the configuration file.

An example of the config file can be seen below.
The one attribute that is handy to play with is the "small" attribute of the hero. Setting this
to false will give a hero that is 50% bigger.


**** EXAMPLE Configuration file *****
Note: This example will not generate a playable game, it is only a small collection from the actual file.
Comments to the right of the JSON denoted by '****' give brief explanations of the JSON.

{
    "general" : {
        "application" : "Stickman",
        "subject" : "SOFT2201",
        "purpose" : "Assignment_2",
        "boilerplatecodeauthor" : "TheUniversityOfSydney",  		**** General information
        "applicationcreator" : {
        }
    },

    "game" : {
        "levelcount" : 3,			                **** Would be useful when there is more than one level
        "screensize" : [640, 400]		            **** To know then there are no more levels to load.
    },

    "levels" : {
        "1" : {                                                                                         **** Level Number: 1/2/3 used to transit level
            "floor" : {
                "dirt" : [
                    {"x_axis": true, "from" : 0.0, "to" : 750.0, "qty" : 16, "pos" : 350.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 700.0},
                    {"x_axis": true, "from" : 950.0, "to" : 3450.0, "qty" : 63, "pos" : 350.0}			**** The floor is made up of tiles.
                ],												                                        **** x_axis is true for when the pos refes to an x cordinate
                "top" : [											                                    **** and build a row of the tiles.
                    {"x_axis" : true, "from" : 0.0, "to" : 800.0, "qty" : 10, "pos" : 300.0},			**** x_axis is false for when a column of tiles needs to be
                    {"x_axis" : true, "from" : 700.0, "to" : 750.0, "qty" : 1, "pos" : 150.0}			**** built.
                ],
                "left" : [
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 650.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 450.0, "qty" : 2, "pos" : 900.0}
                ],
                "right" : [
                    {"x_axis" : false, "from" : 200.0, "to" : 450.0, "qty" : 2, "pos" : 750.0},
                    {"x_axis" : false, "from" : 200.0, "to" : 300.0, "qty" : 2, "pos" : 1000.0}
                ],
                "left_cnr" : [
                    {"x_axis" : true, "from" : 650.0, "to" : 700.0, "qty" : 1, "pos" : 150.0},
                    {"x_axis" : true, "from" : 900.0, "to" : 950.0, "qty" : 1, "pos" : 150.0}
                ],
                "right_cnr" : [
                    {"x_axis" : true, "from" : 750.0, "to" : 800.0, "qty" : 1, "pos" : 150.0},
                    {"x_axis" : true, "from" : 1000.0, "to" : 1050.0, "qty" : 1, "pos" : 150.0}
                ]
            },
            "platforms" : [
                {"x_from" : 200.0, "x_to" : 300.0, "qty" : 1, "ypos" : 200.0},
                {"x_from" : 400.0, "x_to" : 650.0, "qty" : 4, "ypos" : 225.0},
                {"x_from" : 1050.0, "x_to" : 1150.0, "qty" : 1, "ypos" : 225.0},		**** Platforms are built by knowing where you want one or more
                {"x_from" : 1150.0, "x_to" : 1350.0, "qty" : 3, "ypos" : 100.0},		**** platforms to start and finish. The width is variable
                {"x_from" : 1350.0, "x_to" : 1500.0, "qty" : 2, "ypos" : 25.0},			**** and is determined by the equal division of the length
                {"x_from" : 1550.0, "x_to" : 1600.0, "qty" : 1, "ypos" : -25.0},		**** by the amount of platforms (qty). All platforms are 
                										                                **** horizontal and so the 'pos' attribute is always for the
												                                        **** y-axis.
                {"x_from" : 651.0, "x_to" : 651.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 799.0, "x_to" : 799.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 901.0, "x_to" : 901.1, "qty" : 1, "ypos" : 149.5},
                {"x_from" : 1049.0, "x_to" : 1049.1, "qty" : 1, "ypos" : 149.5},		**** These platforms are micro platforms.
                {"x_from" : 1151.0, "x_to" : 1151.2, "qty" : 1, "ypos" : 98.0},			**** The micro platforms create small unoticable objects
                {"x_from" : 1349.0, "x_to" : 1349.1, "qty" : 1, "ypos" : 99.5},			**** that the Slimes use to know when to change direction.
                {"x_from" : 1251.0, "x_to" : 1251.1, "qty" : 1, "ypos" : -100.5},		**** A Slime will chnage its direction when it hits a stationary
                {"x_from" : 1449.0, "x_to" : 1449.1, "qty" : 1, "ypos" : -100.5},		**** object. So micro platfors are used to create their boundary.
                {"x_from" : 1401.0, "x_to" : 1401.1, "qty" : 1, "ypos" : -250.5}
               
            ],
            "trees" : [
                {"x" : 25.0, "y" : 103.0, "w" : 128.0, "h" : 197.0}
            ],
            "flags" : [
                {"x" : 3165.0, "y" : -285.0, "w" : 127.0, "h" : 200.0}				    **** Other object being Trees, Flags, Mushrooms, Slimes and the
            ],											                                **** Hero are created here.
            "mushrooms" : [
                {"x" : 515.0, "y" : 265.0, "w" : 31.2, "h" : 41.4},
                {"x" : 2625.0, "y" : 65.0, "w" : 31.2, "h" : 41.4}
            ],
            "slimes" : [
                {"x" : 250.0, "y" : 240.0, "color":  "GREEN"},                          **** Slime will generate with color instead of random colour selection
                {"x" : 450.0, "y" : 240.0, "color":  "YELLOW"},                         **** Each color slime will use different strategy to behave
                {"x" : 600.0, "y" : 240.0, "color":  "PURPLE"},                         **** Red: keep static, Green: random speed changed once per second
                {"x" : 700.0, "y" : 140.0, "color":  "RED"}                             **** Yellow: track hero's position, Blue: go right, Purple: go left
            ],
            "hero" : {"x" :  250.0, "y" :  50.0, "small" : true},				        **** The Hero can be 'true' or 'false' for the "small" attribute.
                                                                                        **** Changing this to false will generate a hero 50% bigger.

            "timer" : {"target" : 400.0}                                                **** Timer will set the target time for each level
        }											                                    
    }
}