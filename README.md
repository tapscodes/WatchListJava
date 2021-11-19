# WatchList
 Keeps track of shows watched + status of watching (complete, watching, waiting, etc.)
## shows.watchlist file
All of your shows watched, the last episode you watched of it, and its current status will be stored in a file called "shows.watchlist" alongside the main files in SRC.
The file must be formatted like

(Show Name [this is a string/word(s)]):::(Episode Number [this is a number/int]):::(Watch Status [this is a string/word(s)])

on every single line (1 show per line). An example file can be found in the 'src' folder on github (and probably the releases).
## Setup (in VScode)
Your launch.json must have the "vmArgs" javafx portion added below 'request' in launch.json (which is found on their website at: https://openjfx.io/openjfx-docs/#install-javafx )

^As of 11/19/21, this is ""vmArgs": "--module-path C:/Users/(Insert User Here)/Downloads/openjfx-17.0.1_windows-x64_bin-sdk/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml",

Your settings.json will contain your reference libraries (which are the jsons downloaded from java fx sdk at: https://gluonhq.com/products/javafx/ ) <- this can be added manually via the 'Java Projects' tab once you have the proper extension in VScode (being the many java extensions from microsoft).