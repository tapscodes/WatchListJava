//javafx imports
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * @author Tristan P.-S.
 * @verison 1.0 
 * Makes an alert box when something goes wrong
 */
public class AlertBox {
    /**
     * Creates an alert box with the given title (for the window) and main message
     * @param title title of window
     * @param message message in window
     */
    public static void display(String title, String message) {
        //creates alert window
        Stage alertBox = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1200, 300);
        //adds alert message
        Text text = new Text();
        text.setText(message);
        root.setCenter(text);
        //sets alert icon
        Image icon = new Image("alert.png");
        alertBox.getIcons().add(icon);
        //adds alert button
        Button closeBtn = new Button("OK, I understand");
        root.setBottom(closeBtn);
        BorderPane.setAlignment(closeBtn, Pos.CENTER);
        closeBtn.setOnAction(e -> alertBox.close());
        //finishes setting up and then calls alert window
        alertBox.setScene(scene);
        alertBox.setTitle(title);
        alertBox.setWidth(500);
        alertBox.setHeight(100);
        alertBox.show();
    }
}
