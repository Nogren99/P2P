package negocio;
import java.io.*;
import java.net.*;

import controlador.ControladorSistema;
import modelo.Usuario;

public class Sistema implements Runnable {

	private static Sistema instancia;
	private static ControladorSistema controlador;
	private ServerSocket servidor = null;
	private Usuario user = Usuario.getInstance();
	boolean escucha;
	private boolean solicitud;
	
	public static Sistema getInstancia() {
	        if (instancia == null)
	            instancia = new Sistema();
	        return instancia;
	    }
	
    public void solicitarChat(String ip, int puerto) {
    	try {
    		Socket socket = new Socket(ip,puerto);
            this.solicitud = true;
            InputStreamReader  inSocket= new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(inSocket);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(Usuario.getInstance().getNombre());
            if (this.solicitud) {
                controlador.getInstancia().ventanaChat();
            }
    	} catch (IOException e) {
    	}
        
    }
    
    public void enviarMensaje(String mensaje) throws IOException {
    	//pasar a local
    	ServerSocket socketServer = new ServerSocket(this.user.getPuerto());
    	Socket socket = socketServer.accept();
    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(mensaje);
    }

    @Override
    public void run() {
        try {
        	System.out.println("Modo escucha activado.");
        	ServerSocket socketServer = new ServerSocket(this.user.getPuerto());
            escucha = true;
            Socket socket = socketServer.accept();
            socketServer.close();
            escucha = false;
            socketServer = null;
            
            
            //llamado
            
            InputStreamReader  inSocket= new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(inSocket);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(Usuario.getInstance().getNombre());
            // in.readLine();
            if (this.solicitud) {
                controlador.getInstancia().ventanaChat();
            }else{
            	//que aparezca el user en la lista de los que se quieren conectar
            }
            
        } catch (IOException e) {
        }
    }


	
	    
}






