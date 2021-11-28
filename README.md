# WatchList
This is a program that helps you store, edit, and view a list of shows, the episodes watched, and what your current watching status is. 

## Will this program work on my system?
As long as your system can install java, it will. This program will work on linux, windows, and macOS, see below for instructions.

## How to use/install
To use this program simply download the .jar file and install java on your computer ([CLICK HERE](https://www.java.com/en/) to go to java's official website to download it if you don't already have it installed). Once java is installed, simply drag the program (WatchList.jar) where you want your database to be stored and then you can just double click it or run it normally and a database called 'shows.watchlist' will be made in the same folder which automatically updates as you use the program! (There is also a sample shows.watchlist file attached here if you want that for some reason).

## Importing the shows you've watch from another version of this application
In order to import shows from another version of this app, simply drag your shows.watchlist file into the same folder as this jar file and it will attempt to import whatever is there. (PLEASE BE CAUTIOUS WHILE DOING THIS AND BACK UP YOUR SHOWS.WATCHLIST FILE, ANY BAD FILES WILL BE OVERWRITTEN IF THIS PROGRAM IS UNABLE TO PROPERLY READ THEM)

## I found a bug! What do I do?
Please go under the 'issues' tab of this github page and post about any issues you're having with this program there, and I'll try to respond to issues when I can.

## Creating your own shows.watchlist backing file
All of your shows watched, the last episode you watched of it, and its current status will be stored in a file called "shows.watchlist" alongside the main files in SRC.
The file must be formatted like what can be seen below

(Show Name):::(Episode Number [can be a word or a number]):::(Watch Status)

on every single line (1 show per line). An example file can be found in the 'src' folder on github (and probably the releases). 
Although I'm not sure why you would want to do this, since this program automatically creates and saves a shows.watchlist file as you use it.
