import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) {
        System.out.println("Main Method Called");
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

        //Creates a new scene
        Scene scene = new Scene(root, 300, 250);
        arg0.setTitle("Title");
        arg0.setScene(scene);
        arg0.show();
    }
}
