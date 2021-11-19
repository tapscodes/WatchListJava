import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) {
        System.out.println("Main Method Called");
        //launches main window defined in 'start'
        launch(args);
    }

    //required 'start' method for the application
    @Override
    public void start(Stage arg0) throws Exception {
        //creates a new button that has Click here written on it and prints 'handled' when clicked
        Button btn = new Button();
        btn.setText("Click Here");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Handled");
            }

        });
        
        //sets up a stacked pane javafx window
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        //Creates the scene used
        Scene scene = new Scene(root, 300, 250);

        //Gets Icon for the window (temp one made in MSpaint for now)
        Image icon = new Image("watchtime.png");
        arg0.getIcons().add(icon);

        //Sets up the other basic window elements and shows it
        arg0.setScene(scene);
        arg0.setTitle("WatchTime Tracker");
        arg0.setWidth(420);
        arg0.setHeight(420);
        arg0.setResizable(false); //cant resize window
        arg0.show();
    }
}
