
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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        // adds shows from file if it is found in root folder with this program (for
        // testing it adds directly)
        addShows();
        // sets up a border pane javafx window
        BorderPane root = new BorderPane();

        // Creates the table view storing all teh shows watched
        TableView<Show> table = new TableView<Show>();
        // Creates the 3 columns
        TableColumn<Show, String> showNameColumn = new TableColumn<Show, String>("Show Name");
        TableColumn<Show, Integer> epsWatchedColumn = new TableColumn<Show, Integer>("Last Episode Watched");
        TableColumn<Show, String> showStatusColumn = new TableColumn<Show, String>("Watch Status");
        // Uses getter methods to try to get variables with this name
        showNameColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("ShowName"));
        epsWatchedColumn.setCellValueFactory(new PropertyValueFactory<Show, Integer>("EpsWatched"));
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

        // Gets Icon for the window (temp one made in MSpaint for now)
        Image icon = new Image("watchtime.png");
        arg0.getIcons().add(icon);

        // Sets up the other basic window elements and shows it
        Scene scene = new Scene(root, 420, 420);
        arg0.setScene(scene);
        arg0.setTitle("WatchTime Tracker");
        arg0.setWidth(420);
        arg0.setHeight(420);
        arg0.setResizable(false); // cant resize window
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
                for(int i = 0 ; i < importedShow.length; i ++)
                    System.out.println(importedShow[i]);
                if (importedShow.length != 3) {
                    System.out.println("Improper shows.watchlist file found"); // I should make this a popup message later so the user knows whats wrong
                    break;
                }
                showList.add(new Show(importedShow[0], Integer.parseInt(importedShow[1]), importedShow[2]));
            }
            br.close();
        } catch (IOException e) {
            System.out.print("No shows.watchlist file");
        }
        /*
         * debugging add statements (used prior to fileReading) 
         * showList.add(new Show("TestShow", 10000, "Watching")); 
         * showList.add(new Show("ZTestShow", 10,"Waiting"));
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
