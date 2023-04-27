package negocio;
import java.io.*;
import java.net.*;

public class Sistema {

	private static Sistema instancia;
	public static Sistema getInstancia() {
	        if (instancia == null)
	            instancia = new Sistema();
	        return instancia;
	    }
	
	//REFERENTE A LA VENTANA 1. EL USUARIO ABRE SU SERVIDOR Y QUEDA EN MODO ESCUCHA
	
	//no se si es muy representativo pero asi se llama en el CU lol
	public void AbrirAplicacion(String ip, int puerto) {
		
		try {

            //Creamos el socket de servidor y esperamos a que un usuario se conecte
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Esperando conexión en el puerto " + puerto + "...");
            Socket socket = servidor.accept();
            System.out.println("Conexión establecida con el usuario " + socket.getInetAddress().getHostName());

            
            
            //====VENTANA 3 - CHAT=======================
            
            //Creamos los flujos de entrada y salida para enviar y recibir mensajes
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Recibimos el mensaje del usuario remoto
            String mensaje = entrada.readLine();
            System.out.println("Mensaje del usuario remoto: " + mensaje);

            // Enviamos una respuesta al usuario remoto
            salida.println("Todo joya");

            //cerramos flujos
            salida.close();
            entrada.close();
          //==============================================
            
            
            // Cerramos socket
            
            socket.close();
            servidor.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
		
	}
	
	//REFERENTE A LA VENTANA 2. EL USUARIO SE CONECTA A OTRO SERVIDOR(ACTUA COMO CLIENTE)
	
	public void cliente() {
		String host = "127.0.0.1"; // Dirección IP del usuario con el que queremos establecer la conexión
        int puerto = 5000; // Puerto en el que el usuario está escuchando

        try {

            // Creamos el socket y nos conectamos al usuario
            Socket socket = new Socket(host, puerto);
            
            
            //====VENTANA 3 - CHAT=======================

            // Creamos los flujos de entrada y salida para enviar y recibir mensajes
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviamos un mensaje al usuario remoto
            salida.println("Todo joya?");

            // Recibimos la respuesta del usuario remoto
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del usuario remoto: " + respuesta);
            
            //Cerramos flujos
            salida.close();
            entrada.close();
            
          //==============================================
            

            // Cerramos socket
            
            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
	
	    
}
