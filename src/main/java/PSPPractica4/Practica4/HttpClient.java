package PSPPractica4.Practica4;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class HttpClient {

	static final int PORT = 14147;
	static final String HOST = "localhost";
	static final String USER = "admin";
	static final String PASSWORD = "admin";

	public static void main(String[] args) throws IOException {
		FTPClient ftpClient = new FTPClient();

		ftpClient.connect(HOST);
		if (ftpClient.getReplyCode() == 220) {
			System.out.println(ftpClient.getReplyString());
			System.out.println("El servidor FTP está preparado");
		}

		System.out.println("1. Iniciar sesion\n0. Salir");
		int opcion = Leer.pedirEnteroValidar();
		while(opcion!=0) {
			switch (opcion) {
			case 1:
				System.out.println("Nombre de usuario:\n");
				String usuario=Leer.pedirCadena();
				System.out.println("Contraseña:\n");
				String contraseña=Leer.pedirCadena();
				
				boolean isLogged = ftpClient.login(usuario, contraseña);

				if (isLogged) {
					System.out.println("Login correcto...");
				} else {
					System.out.println("Login incorrecto...");
				}

				break;
			case 2:
				break;
			case 3:
				break;
			default:
				break;
			}
			
			
			opcion = Leer.pedirEnteroValidar();
		}

		
		System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());

		FTPFile[] files = ftpClient.listFiles();
		System.out.println("Ficheros en el directorio actual: " + files.length);

		ftpClient.disconnect();
	}
}