package PSPPractica4.Practica4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

		System.out.println("1. Iniciar sesion\n2. Cerrar sesion\n3. Listar ficheros y directorios\n4. Subir fichero"
				+ "\n5. Descargar fichero del servidor\n6. Eliminar fichero\n7. Eliminar directorio"
				+ "\n8. Crear directorio\n9. Cambiar el directorio actual\n10. Establecer conexion remotamente"
				+ "\n11. Añadir interfaz grafica\n0. Salir");
		int opcion = Leer.pedirEnteroValidar();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				System.out.println("Nombre de usuario:\n");
				String usuario = Leer.pedirCadena();
				System.out.println("Contraseña:\n");
				String contraseña = Leer.pedirCadena();
				boolean isLogged;
				if (usuario.contentEquals("") && contraseña.contentEquals("")) {
					isLogged = ftpClient.login(USER, PASSWORD);
				} else {
					isLogged = ftpClient.login(usuario, contraseña);
				}

				if (isLogged) {
					System.out.println("Login correcto...");
				} else {
					System.out.println("Login incorrecto...");
				}

				break;
			case 2:
				ftpClient.disconnect();
				System.out.println("Usuario desconectado.");
				break;
			case 3:
				System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());

				FTPFile[] files = ftpClient.listFiles();
				System.out.println("Ficheros en el directorio actual: " + files.length);
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i]);
				}

				break;
			case 4:
				//C:\Users\1DAM\Desktop\Filezilla
				System.out.println("Dime el nombre del fichero:");
				// "commons-net-3.6.jar"
				String remote_filename = "subida.txt";
				String local_filepath = "C:\\Users\\1DAM\\Desktop\\Otra carpeta";
				FileInputStream fis = new FileInputStream(local_filepath+"\\"+remote_filename);
				boolean uploadFile = ftpClient.storeFile(remote_filename, fis);
				if (uploadFile == false) {
					System.out.println("Error al subir el fichero");
				} else {
					System.out.println("Fichero subido con exito");
				}
				break;
			case 5:
				System.out.println("Introduce el nombre del fichero que se quiere descargar");
				String remoteFile1 = "descarga.txt";
	            File downloadFile1 = new File("C:\\Users\\1DAM\\Desktop\\Otra carpeta");
	            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1+"\\"+remoteFile1));
	            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
	            outputStream1.close();
	 
	            if (success) {
	                System.out.println("Descarga completada con exito.");
	            }else {
	            	System.out.println("No se pudo descargar el archivo");
	            }
				
				break;
			case 6:
				System.out.println("Introduce el nombre del fichero a eliminar");
				String aEliminar="subida.txt";
				String filename = "\\"+aEliminar;

				boolean exist = ftpClient.deleteFile(filename);
				 
				// Notify user for deletion 
				if (exist) {
				    System.out.println("Fichero '"+ filename + "' eliminado...");
				}
				else System.out.println("Fichero '"+ filename + "' no existe...");
				break;
			case 7:
				System.out.println("Introduce el nombre del directorio a eliminar");
				String aEliminar2=Leer.pedirCadena();
				String filename2 = aEliminar2;

				boolean exist2 = ftpClient.removeDirectory(filename2);
				 
				// Notify user for deletion 
				if (exist2) {
				    System.out.println("Directorio '"+ filename2 + "' eliminado...");
				}
				else System.out.println("Directorio '"+ filename2 + "' no existe...");
				break;
			case 8:
				System.out.println("Dime el nombre del directorio a crear:");
				String directorio = Leer.pedirCadena();
				boolean funciona = ftpClient.makeDirectory(directorio);
				ftpClient.getReplyString();
				if (funciona) {
					System.out.println("Creado correctamente directorio: " + directorio);
				} else {
					System.out.println("Error al crear directorio.");
				}
				break;
			case 9:
				System.out.println("Dime el nombre del directorio a cambiar:");
				String directorioC = Leer.pedirCadena();
				if (directorioC.equals("..")) {
					ftpClient.changeToParentDirectory();
				} else {
					ftpClient.changeWorkingDirectory(directorioC);
					System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
				}
				
				break;
			case 10:
				
				break;
			case 11:
				break;
			default:
				System.out.println("Valor no valido");
				break;
			}

			System.out.println("1. Iniciar sesion\n2. Cerrar sesion\n3. Listar ficheros y directorios\n4. Subir fichero"
					+ "\n5. Descargar fichero del servidor\n6. Eliminar fichero\n7. Eliminar directorio"
					+ "\n8. Crear directorio\n9. Cambiar el directorio actual\n10. Establecer conexion remotamente"
					+ "\n11. Añadir interfaz grafica\n0. Salir");
			opcion = Leer.pedirEnteroValidar();
		}

		ftpClient.disconnect();
	}
}