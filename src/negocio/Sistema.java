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
    		this.socket = new Socket(ip,puerto);
            this.solicitud = true;
            this.inSocket= new InputStreamReader(socket.getInputStream());
            this.in = new BufferedReader(inSocket);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.out.println(Usuario.getInstance().getNombre());
            if (this.solicitud) {
                ControladorSistema.getInstancia().ventanaChat();
            }
    	} catch (IOException e) {
    	}
        
    }
    
    public void enviarMensaje(String mensaje) throws IOException {
        this.out.println(mensaje);
        System.out.println("metodo enviarMensaje: "+mensaje);
    }

    
    
    @Override
    public void run() {
        try {
        	
        	//ACTIVO MODO ESCUCHA
        	System.out.println("Modo escucha activado.");
        	this.socketServer = new ServerSocket(this.user.getPuerto());
        	System.out.println(this.user.getPuerto());
            this.socket = socketServer.accept();
            socketServer.close();
            socketServer = null;
            
            
            //INICIO ENTRADAS Y SALIDAS
            this.inSocket= new InputStreamReader(socket.getInputStream());
            this.in = new BufferedReader(inSocket);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.out.println(Usuario.getInstance().getNombre());
            System.out.println("mi nombre es "+this.user.getNombre()+" y me conecte con mi amiguito "+ this.in.readLine());
            if (this.solicitud) {
                ControladorSistema.getInstancia().ventanaChat();
            }
            
        } catch (IOException e) {
        }
    }

	public BufferedReader getIn() {
		return in;
	}

	public Socket getSocket() {
		return socket;
	}

	public String recibirMensaje() {
		String mensaje="";
		System.out.println("metodo recibirMensaje: "+mensaje);
		try {
			mensaje = this.in.readLine();
			System.out.println("metodo recibirMensaje: "+mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}


	    
}






