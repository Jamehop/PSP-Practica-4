package PSPPractica4.Practica4;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
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

		System.out.println("1. Iniciar sesion\n2. Listar ficheros y directorios\n3. Subir fichero\n8. Crear directorio\n0. Salir");
		int opcion = Leer.pedirEnteroValidar();
		while(opcion!=0) {
			switch (opcion) {
			case 1:
				System.out.println("Nombre de usuario:\n");
				String usuario=Leer.pedirCadena();
				System.out.println("Contraseña:\n");
				String contraseña=Leer.pedirCadena();
				boolean isLogged;
				if(usuario.contentEquals("") && contraseña.contentEquals("")) {
					isLogged = ftpClient.login(USER, PASSWORD);
				}
				else {
					isLogged = ftpClient.login(usuario, contraseña);
				}
				

				if (isLogged) {
					System.out.println("Login correcto...");
				} else {
					System.out.println("Login incorrecto...");
				}

				break;
			case 2:
				System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());

				FTPFile[] files = ftpClient.listFiles();
				System.out.println("Ficheros en el directorio actual: " + files.length);
				for(int i=0; i<files.length;i++) {
					System.out.println(files[i]);
				}
				break;
			case 3:
				System.out.println("Indica la ruta a la que quieres subir el fichero:");
				//"C:/Users/1DAM/Desktop/Filezilla/"
				String remote_working_dir_path = Leer.pedirCadena();
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				
				System.out.println("Dime el nombre del fichero:");
				//"commons-net-3.6.jar"
				String remote_filename = Leer.pedirCadena();
				ftpClient.changeWorkingDirectory(remote_working_dir_path);
				String local_filepath = "C:\\Users\\1DAM\\Desktop\\Filezilla\\";
				FileInputStream fis = new FileInputStream(local_filepath);
			    boolean uploadFile = ftpClient.storeFile(remote_filename, fis);
			    if ( uploadFile == false ) {
			        System.out.println("Error al subir el fichero");
			    }
			    else {
			    	System.out.println("Fichero subido con exito");
			    }
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				System.out.println("Dime el nombre del directorio a crear:");
				String directorio=Leer.pedirCadena();
				boolean funciona=ftpClient.makeDirectory(directorio);
				ftpClient.getReplyString();
	            if (funciona) {
	                System.out.println("Creado correctamente directorio: " + directorio);
	            } else {
	                System.out.println("Error al crear directorio.");
	            }
				break;
			default:
				break;
			}
			
			
			opcion = Leer.pedirEnteroValidar();
		}

		ftpClient.disconnect();
	}
}