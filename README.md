<h1> Treasure Trail </h1>

<p> This is a game where the user runs around Bishop's
university campus looking for clues that will lead the user
to the hidden Bishop's treasure.</p>
<br/>
<p>We are using <a href="http://slick.ninjacave.com/">Slick2D</a> as a
Java game library.</p>

# How to Setup & Run #

1. Clone the repository into a folder on your local desktop with GitBash.
2. Open Eclipse and create a new Java project with JSE 1.7.
3. Import the repository to the new project by using File -> Import -> File System, then choosing the folder that the repository was cloned into.
4. Set up the Slick2D library by following the instructions [here](http://slick.ninjacave.com/wiki/index.php?title=Setting_up_Slick2D_with_Eclipse).
	- All the needed JAR files are or should be in the 'dependencies' folder. The *.dll files for Windows are in the folder 'dependencies/windows/'. It is suggested to follow the instructions at the given link though to get fresh JARs for Slick2d. You will still need to follow the instructions to set up LWJGLs native path (by native, in version [3.1.1](https://github.com/LWJGL/lwjgl3/releases/tag/3.1.1) of LWJGL, they mean the folder '/lwjgl/'). 
5. Copy the files in the foler 'dependencies/windows/' to the folder '/lwjgl/' that was created in (4) and used as the native library location for Slick2D.
6. Now, it should run without any problems.

# How to Release #
1. Go in File-->export
2. Select "Runnable Jar file" and click next
3. In launch configuration, I had to select Main (1) - softengineeringproject instead of Main - softengineeringproject.
4. Select "Package required libraries into generated JAR", don't check the save as ANT script checkbox.
5. Click finish
6. Go to the folder where the jar was exported, and add all the dependencies to that folder the same way it is done in older releases.
7. Use command java -Djava.library.path="PATH_TO_WINDOWS_FOLDER\dependencies\windows" -jar treasuretrail.jar to debug

&copy; TreasureTrail 2017
