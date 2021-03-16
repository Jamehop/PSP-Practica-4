package Interfaz;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			/*URL url = getClass().getClassLoader().getResource("Login.fxml");
			FXMLLoader loader = new FXMLLoader(url);*/
			
			Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
			Scene scene = new Scene(root, 700, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
			Scene scene = new Scene(root);	
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
			scene.setFill(Color.TRANSPARENT);*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
