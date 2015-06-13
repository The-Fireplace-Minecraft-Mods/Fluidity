# README #

This is the repository for the Fluidity Minecraft mod. The goal of this mod is to add content based on what mods are running alongside it. Think of it as an extreme integration layer, bridging gaps between mods and smoothing out inconsistencies.

### What is this repository for? ###

* This is a repository for the Fluidity Minecraft mod
* Fluidity is currently being developed for Minecraft 1.8 running [Forge 11.14.3.1446](http://adfoc.us/serve/sitelinks/?id=271228&url=http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.8-11.14.3.1446/forge-1.8-11.14.3.1446-src.zip)

### How do I get set up? ###

* First, clone the repo
* Second, you should just be able to run setupforge.bat, it sets it up for use with Eclipse
* Third, as usual, point Eclipse to the eclipse folder

### Contribution guidelines ###

* I have a few simple guidelines for contributing to the mod:
* I don't require much documentation, but I would like you to use `@author` documentation at the top of each class file, between the imports and the actual class
* If you do something that requires a certain Forge version, and that version is higher than the one listed in mcmod.info, update the one in mcmod.info
* You cannot add any hard dependancy(required mod) other than Forge. Any interaction with other mods must be designed with the other mods as soft-dependencies.
* When adding content, feel free to look through the extraassets folder to see if there are any textures or sounds you can use or modify for use with that content. You don't have to use something from that folder, but it may have something useful.

### Who do I talk to? ###

* [The_Fireplace](http://www.minecraftforum.net/members/The_Fireplace)

### Contributors: ###
* The_Fireplace