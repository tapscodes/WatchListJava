
//normal java imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//javafx imports (requires referenced libraries)
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Tristan P.-S.
 * @verison 1.0 Window that tracks movies/shows/anime watched
 */
public class WatchList extends Application {

    // globally used vars
    private ArrayList<Show> showList = new ArrayList<Show>();

    /**
     * Main Method (Launches Window)
     */
    public static void main(String[] args) {
        System.out.println("Main Method Called");
        // launches main window defined in 'start'
        launch(args);
    }

    /**
     * Sets properties of window and starts program
     * 
     * @param arg0 the 'stage' (window) you want to display with
     */
    @Override
    public void start(Stage arg0) throws Exception {
        // adds shows from file if it is found in root folder with this program 
        addShows();
        // sets up a border pane javafx window
        BorderPane root = new BorderPane();

        // Creates the table view storing all teh shows watched
        TableView<Show> table = new TableView<Show>();
        // Creates the 3 columns
        TableColumn<Show, String> showNameColumn = new TableColumn<Show, String>("Show Name");
        TableColumn<Show, String> epsWatchedColumn = new TableColumn<Show, String>("Last Episode Watched");
        TableColumn<Show, String> showStatusColumn = new TableColumn<Show, String>("Watch Status");
        // Uses getter methods to try to get variables with this name
        showNameColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("ShowName"));
        epsWatchedColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("EpsWatched"));
        showStatusColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("ShowStatus"));
        // adds colums to table
        table.getColumns().add(showNameColumn);
        table.getColumns().add(epsWatchedColumn);
        table.getColumns().add(showStatusColumn);
        root.setCenter(table);
        // makes it so every column gets equal space
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // adds all items to table
        for (Show show : showList) {
            table.getItems().add(show);
        }

        //Sets up the 3 input text fields + the button
        TextField showNameTextbox = new TextField();
        TextField episodeTextbox = new TextField();
        TextField showStatusTextbox = new TextField();
        Button inputNewShowButton = new Button();
        showNameTextbox.setPromptText("Enter Show Name Here");
        episodeTextbox.setPromptText("Enter Last Episode Watched");
        showStatusTextbox.setPromptText("Enter Watching Status");
        inputNewShowButton.setText("Add Show");
        //sets up button press
        inputNewShowButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                //checks if all 3 text boxes are filled
                if(!showNameTextbox.getText().isEmpty() && !episodeTextbox.getText().isEmpty() && !showStatusTextbox.getText().isEmpty()) {
                    showList.add(new Show(showNameTextbox.getText(), episodeTextbox.getText(), showStatusTextbox.getText()));
                    //updates the table to show the new show + the old ones
                    table.getItems().clear();
                    for (Show show : showList) {
                        table.getItems().add(show);
                    }
                    //saves changes to the shows.watchlist file
                    try {
                        writeToFile();
                    } catch (IOException e) {
                        AlertBox.display("Alert!", "Something went seriously wrong, please contact me on git if you see this");
                    }
                    showNameTextbox.clear();
                    episodeTextbox.clear();
                    showStatusTextbox.clear();
                } else { //if all 3 text boxes aren't filled
                    AlertBox.display("Alert!", "You need 3 inputs");
                }
            }
            
        });

        //Adds 3 input text field + the button to the scene
        //adds 3 texboxes to a pane going top down
        BorderPane textboxPane = new BorderPane();
        textboxPane.setTop(showNameTextbox);
        textboxPane.setCenter(episodeTextbox);
        textboxPane.setBottom(showStatusTextbox);
        BorderPane inputPane = new BorderPane();
        //adds 3 textboxes above the input button and sets the to the right part of the main pane (root)
        inputPane.setTop(textboxPane);
        inputPane.setCenter(inputNewShowButton);
        inputPane.setMaxWidth(250);
        inputPane.setMinWidth(100);
        root.setRight(inputPane);

        // Gets Icon for the window (temp one made in MSpaint for now)
        Image icon = new Image("watchtime.png");
        arg0.getIcons().add(icon);

        // Sets up the other basic window elements and shows it
        Scene scene = new Scene(root, 624, 352);
        arg0.setScene(scene);
        arg0.setTitle("WatchTime Tracker");
        arg0.setWidth(624);
        arg0.setHeight(352);
        arg0.setResizable(true); // can resize window
        writeToFile();
        arg0.show();
    }

    /**
     * Adds shows to the showList from any file found called "shows.watchlist" in
     * the stored files
     */
    public void addShows() {
        // writes file to parent directery
        File file = new File("./src/shows.watchlist");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;

            while ((s = br.readLine()) != null) {
                String[] importedShow = s.split(":::");
                if (importedShow.length != 3) {
                    System.out.println("Improper shows.watchlist file found"); // I should make this a popup message later so the user knows whats wrong
                    break;
                }
                showList.add(new Show(importedShow[0], importedShow[1], importedShow[2]));
            }
            br.close();
        } catch (IOException e) {
            System.out.print("No shows.watchlist file");
        }
        /*
         * debugging add statements (used prior to fileReading) <- leaving for possible future tests
         * showList.add(new Show("TestShow", "10000", "Watching")); 
         * showList.add(new Show("ZTestShow", "10","Waiting"));
         */
    }

    /**
     * Writes all currently stored movies to a file
     * 
     * @throws IOException Throws exception if the file we just created doesn't
     *                     exist (not sure why that would be the case)
     */
    public void writeToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/shows.watchlist"));
        for (Show show : showList) {
            bw.write(show.getShowName() + ":::" + show.getEpsWatched() + ":::" + show.getShowStatus());
            bw.newLine();
        }
        bw.close();
    }
}
