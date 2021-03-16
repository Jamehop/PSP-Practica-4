package Controllers;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import Interfaz.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller1 {

	@FXML
	Button btnConfirm;

	@FXML
	TextField textUser;

	@FXML
	PasswordField textPassw;

	int contador = 0;
	static final int PORT = 14147;
	static final String HOST = "localhost";
	static final String USER = "admin";
	static final String PASSWORD = "admin";
		@FXML
		public void confirm(ActionEvent event) {
			String user = textUser.getText();
			String pass = this.textPassw.getText();
			if (!(contador == 3)) {

				if (!user.equals("admin") && !pass.equals("admin")) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle(" ERROR ");
					alert.setContentText("Usuario o Password INCORRECTO");
					alert.showAndWait();
					contador++;
				} else {
					try {
						FTPClient ftpClient = new FTPClient();

						ftpClient.connect(HOST);
						if (ftpClient.getReplyCode() == 220) {
							System.out.println(ftpClient.getReplyString());
							System.out.println("El servidor FTP está preparado");
						}
						Stage mystage = (Stage) this.btnConfirm.getScene().getWindow();
						mystage.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("SEGURIDAD");
				alert.setContentText(" Intentos maximos alcanzados. Cerrando aplicacion.");
				alert.showAndWait();
				Platform.exit();
			}

		}
}
