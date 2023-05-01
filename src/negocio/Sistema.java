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
	private ServerSocket socketServer;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private InputStreamReader  inSocket;
	boolean escucha;
	private boolean solicitud;
	
	public static Sistema getInstancia() {
	        if (instancia == null)
	            instancia = new Sistema();
	        return instancia;
	    }
	
    public void solicitarChat(String ip, int puerto) {
    	System.out.println("meotod solicitarChat / "+ "ip:"+ip+" puerto :"+puerto);
    	try {
    		socket = new Socket(ip,puerto);
            this.solicitud = true;
            this.inSocket= new InputStreamReader(socket.getInputStream());
            this.in = new BufferedReader(inSocket);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.out.println(Usuario.getInstance().getNombre());
            if (this.solicitud) {
                controlador.getInstancia().ventanaChat();
            }
    	} catch (IOException e) {
    	}
        
    }
    
    public void enviarMensaje(String mensaje) throws IOException {
        out.println(mensaje);
        System.out.println("metodo enviarMensaje"+mensaje);
    }

    
    
    @Override
    public void run() {
        try {
        	System.out.println("Modo escucha activado.");
        	this.socketServer = new ServerSocket(this.user.getPuerto());
        	System.out.println(this.user.getPuerto());
            this.socket = socketServer.accept();
            socketServer.close();
            socketServer = null;
            
            
            //llamado
            
            this.inSocket= new InputStreamReader(socket.getInputStream());
            this.in = new BufferedReader(inSocket);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.out.println(Usuario.getInstance().getNombre());
            System.out.println("mi nombre es "+this.user.getNombre()+" y me conecte con mi amiguito "+ this.in.readLine());
            // in.readLine();
            if (this.solicitud) {
                controlador.getInstancia().ventanaChat();
            }else{
            	//que aparezca el user en la lista de los que se quieren conectar
            }
            
        } catch (IOException e) {
        }
    }

	public String recibirMensaje() {
		String mensaje="";
		System.out.println("metodo recibirMensaje"+mensaje);
		try {
			mensaje = this.in.readLine();
			System.out.println("metodo recibirMensaje"+mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}


	
	    
}






