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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Tristan P.-S.
 * @verison 1.0 Window that tracks movies/shows/anime watched
 */
public class WatchList extends Application {

    // globally used vars
    private ArrayList<Show> showList = new ArrayList<Show>();
    private Show selectedShow;

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
        episodeTextbox.setPromptText("Enter Last Episode Seen");
        showStatusTextbox.setPromptText("Enter Watching Status");
        inputNewShowButton.setText("Add Show");
        //sets up button press
        inputNewShowButton.setOnAction(new EventHandler<ActionEvent>() {

            /**
             * Adds current show to list
             * @param arg0 the action of the button being clicked
             */
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

        //adds button to 'update' show info and a button to 'delete' show info
        Button updateShowButton = new Button();
        Button deleteShowButton = new Button();
        updateShowButton.setText("Update Show's Values");
        deleteShowButton.setText("Delete Current Show");
        //makes buttons hide until table is clicked
        updateShowButton.setVisible(false);
        deleteShowButton.setVisible(false);
        updateShowButton.setOnAction(new EventHandler<ActionEvent>() {

            /**
             * Updates the current show's info and then refreshes the list
             * @param arg0 the action of the button being clicked
             */
            @Override
            public void handle(ActionEvent arg0) {
                 //checks if all 3 text boxes are filled
                 if(!showNameTextbox.getText().isEmpty() && !episodeTextbox.getText().isEmpty() && !showStatusTextbox.getText().isEmpty()) {
                    //updates show's info + hides update and delete buttons
                    selectedShow.setShowName(showNameTextbox.getText());
                    selectedShow.setEpsWatched(episodeTextbox.getText());
                    selectedShow.setShowStatus(showStatusTextbox.getText());
                    updateShowButton.setVisible(false);
                    deleteShowButton.setVisible(false);
                    //updates the table to show that item's values have changed
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

        deleteShowButton.setOnAction(new EventHandler<ActionEvent>() {

            /**
             * Deletes the currently selected show
             * @param arg0 the action of the button being clicked
             */
            @Override
            public void handle(ActionEvent arg0) {
                //removes show + hides update and delete buttons
                 showList.remove(selectedShow);
                updateShowButton.setVisible(false);
                deleteShowButton.setVisible(false);
                //updates the table to show that item has been removed
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
            }
        });



        //Adds 3 input text field + the button to the scene
        //adds 3 texboxes to a pane going top down
        BorderPane textboxPane = new BorderPane();
        textboxPane.setTop(showNameTextbox);
        textboxPane.setLeft(episodeTextbox);
        textboxPane.setBottom(showStatusTextbox);
        //Adds 3 buttons to the scene
        //Adds 3 buttons to a pane going top down
        BorderPane buttonPane = new BorderPane();
        buttonPane.setTop(deleteShowButton);
        buttonPane.setLeft(updateShowButton);
        buttonPane.setBottom(inputNewShowButton);
        //adds 3 textboxes above the input button and sets the to the right part of the main pane (root)
        BorderPane inputPane = new BorderPane();
        inputPane.setTop(textboxPane);
        inputPane.setBottom(buttonPane);
        inputPane.setMinWidth(150);
        inputPane.setMinHeight(100);
        root.setRight(inputPane);

        //handles actions when clicking on tableview values
        table.setOnMouseClicked(new EventHandler<MouseEvent> () {

            /**
             * Handles clicking on a row on the tableview
             * @param arg0 the click event itself
             */
            @Override
            public void handle(MouseEvent arg0) {
                //sets the show to the current show clicked on
                selectedShow = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                showNameTextbox.setText(selectedShow.getShowName());
                episodeTextbox.setText(selectedShow.getEpsWatched());
                showStatusTextbox.setText(selectedShow.getShowStatus());
                updateShowButton.setVisible(true);
                deleteShowButton.setVisible(true);
            }

        });

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
                    AlertBox.display("Alert!", "Improper shows.watchlist file found (this should fix after a reboot if any values are added as the file will be overwritten)");
                    break;
                }
                showList.add(new Show(importedShow[0], importedShow[1], importedShow[2]));
            }
            br.close();
        } catch (IOException e) {
            System.out.print("No shows.watchlist file");
        }
        /*
         * debugging add statements (used prior to fileReading) <- leaving for possible
         * future tests showList.add(new Show("TestShow", "10000", "Watching"));
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
