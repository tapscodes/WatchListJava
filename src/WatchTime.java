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
 * @verison 1.0
 * Window that tracks movies/shows/anime watched
 */
public class WatchTime extends Application{

    /**
     * Main Method (Launches Window)
     */
    public static void main(String[] args) {
        System.out.println("Main Method Called");
        //launches main window defined in 'start'
        launch(args);
    }

    /**
     * Sets properties of window and starts program
     * @param arg0 the 'stage' (window) you want to display with 
     */
    @Override
    public void start(Stage arg0) throws Exception {    
        //sets up a border pane javafx window
        BorderPane root = new BorderPane();



        //Creates the table view storing all teh shows watched
        TableView<Show> table = new TableView<Show>();
        //Creates the 3 columns
        TableColumn<Show, String> showNameColumn = new TableColumn<Show, String>("Show Name");
        TableColumn<Show, Integer> epsWatchedColumn = new TableColumn<Show, Integer>("Last Episode Watched");
        TableColumn<Show, String> showStatusColumn = new TableColumn<Show, String>("Watch Status");
        //Uses getter methods to try to get variables with this name
        showNameColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("showName"));
        epsWatchedColumn.setCellValueFactory(new PropertyValueFactory<Show, Integer>("epsWatched;"));
        showStatusColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("showStatus"));
        //adds colums to table
        table.getColumns().add(showNameColumn);
        table.getColumns().add(epsWatchedColumn);
        table.getColumns().add(showStatusColumn);
        root.setCenter(table);
        //makes it so every column gets equal space
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //adds all items to table
        table.getItems().add(new Show("TestShow", 10000, "Watching"));
        table.getItems().add(new Show("ZTestShow", 10, "Waiting"));        

        //Gets Icon for the window (temp one made in MSpaint for now)
        Image icon = new Image("watchtime.png");
        arg0.getIcons().add(icon);

        //Sets up the other basic window elements and shows it
        Scene scene = new Scene(root, 420, 420);
        arg0.setScene(scene);
        arg0.setTitle("WatchTime Tracker");
        arg0.setWidth(420);
        arg0.setHeight(420);
        arg0.setResizable(false); //cant resize window
        arg0.show();
    }
}
