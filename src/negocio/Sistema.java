package negocio;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import controlador.ControladorSistema;
import modelo.ClienteNoDisponible;
import modelo.ConexionTerminada;
import modelo.ConfirmacionSolicitud;
import modelo.Mensaje;
import modelo.SolicitudMensaje;
import modelo.Usuario;
import vista.Chat;

public class Sistema implements Runnable {

	private static Sistema instancia;
	private static ControladorSistema controlador;
	private ServerSocket servidor = null;
	private Usuario user = Usuario.getInstance();
	private ServerSocket socketServer;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private InputStreamReader inSocket;
	private ObjectOutputStream flujoSalida;
	private boolean aceptada = false;
	boolean estoyEnLlamada = false;
	boolean escucha;

	public static Sistema getInstancia() {
		if (instancia == null)
			instancia = new Sistema();
		return instancia;
	}

	public void solicitarChat(String ip, int puerto, String nombre) {
		System.out.println(this.user.getNombre() + " | meotod solicitarChat | " + "ip:" + ip + " puerto :" + puerto);
		try {
			this.socket = new Socket(ip, puerto);
			this.flujoSalida = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
			// this.inSocket = new InputStreamReader(socket.getInputStream());
			// this.in = new BufferedReader(inSocket);
			// this.out = new PrintWriter(socket.getOutputStream(), true);
			// this.out.println(Usuario.getInstance().getNombre());

			SolicitudMensaje msj = new SolicitudMensaje(nombre, nombre);
			this.flujoSalida.writeObject((SolicitudMensaje) msj);
			this.flujoSalida.flush();

			Thread userThread = new Thread(new EscucharUsuario(socket, flujoEntrada, this.flujoSalida));
			userThread.start();
		} catch (IOException e) {
		}
	}
	

	public void enviarMensaje(String mensaje, String nombre, String nombreDestinatario) {

		try {
			this.flujoSalida.writeObject(new Mensaje(mensaje, nombre, nombreDestinatario));
			this.flujoSalida.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			// ACTIVO MODO ESCUCHA
			System.out.println("Modo escucha activado.");
			this.socketServer = new ServerSocket(this.user.getPuerto());
			System.out.println(this.user.getPuerto());
			// ControladorSistema.getInstancia().ventanaEspera();
			while(true) {
				this.socket = socketServer.accept();

				ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
				this.flujoSalida = new ObjectOutputStream(socket.getOutputStream());

				Thread userThread = new Thread(new EscucharUsuario(socket, flujoEntrada, this.flujoSalida));
				userThread.start();
			}
			

		} catch (IOException e) {
		}
	}

	private class EscucharUsuario implements Runnable {

		private Socket usuario;
		private ObjectInputStream flujoEntrada;
		private ObjectOutputStream flujoSalida;
		private String nombreInterlocutor;
		private boolean aceptada;
		
		

		
		public EscucharUsuario(Socket usuario, ObjectInputStream flujoEntrada, ObjectOutputStream flujoSalida) {
			this.usuario = usuario;
			this.flujoEntrada = flujoEntrada;
			this.flujoSalida = flujoSalida;
		}

		@Override
		public void run() {
			try {

				while (true) {
					Object object = flujoEntrada.readObject();

					if (object instanceof SolicitudMensaje) {

						SolicitudMensaje solicitud = (SolicitudMensaje) object;
						System.out.println("Estoy recibiendo el mesaje " + solicitud.getNombre());
						if (estoyEnLlamada) {
	        				flujoSalida.writeObject(new ClienteNoDisponible(solicitud.getNombrePropio()));
	        			} else {
							int dialogButton = JOptionPane.showConfirmDialog(null,solicitud.getNombrePropio() + " quiere iniciar una conversación contigo. ¿Aceptar?","Solicitud entrante", 0); // 0 es si, 1 es no
							if (dialogButton == 0) { // si
								System.out.println("le acepte el chat al gil este");
								ControladorSistema.getInstancia().setSolicitante(false);
								this.nombreInterlocutor=solicitud.getNombrePropio();
								flujoSalida.writeObject(new ConfirmacionSolicitud(true, Usuario.getInstance().getNombre()));
	
								ControladorSistema.getInstancia().ventanaChatSolicitado(solicitud.getNombrePropio()); // mandar
																														// nombre
								System.out.println("==ventana de chat recibir solicitud== ");
								estoyEnLlamada=true;
							} else { // no
								flujoSalida.writeObject(new ConfirmacionSolicitud(false, solicitud.getNombrePropio()));
								System.out.println("no acepto ni en pedo");
							}
	        			}

					} else if (object instanceof ConfirmacionSolicitud) {
						ConfirmacionSolicitud confirmacion = (ConfirmacionSolicitud) object;
						if(confirmacion.isConfirmacion()) {
							ControladorSistema.getInstancia().setSolicitante(true);
							this.aceptada=true;
							estoyEnLlamada=true;
							System.out.println("Me confirmaron");
							ControladorSistema.getInstancia().ventanaChatSolicitante(confirmacion.getNombreSolicitante());
							System.out.println("==ventana de chat confirmacion== ");
						}else
							JOptionPane.showMessageDialog(null, "Tu solicitud ha sido rechazada :(");

					}  else if (object instanceof Mensaje) {
						Mensaje mensaje = (Mensaje) object;
						ControladorSistema.getInstancia().actualizaChat(mensaje.getNombreMio(), mensaje.getMensaje());
					} else if (object instanceof ClienteNoDisponible){
	        			JOptionPane.showMessageDialog(null, "El usuario no está disponible!");
	        		} else if (object instanceof ConexionTerminada){
	        			estoyEnLlamada=false;
	        			JOptionPane.showMessageDialog(null, "Conexion terminada!");
	        			ControladorSistema.getInstancia().abrirVentanaEspera();       			
	        		} else {
	        			System.out.println("objeto recibido:"+object.toString());
	        		}
				}

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
		}

	}
	
	public void cerrarConexion(String nombreDestinatario) {
		try {
			estoyEnLlamada=false;
			this.flujoSalida.writeObject(new ConexionTerminada(nombreDestinatario));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedReader getIn() {
		return in;
	}

	public Socket getSocket() {
		return socket;
	}

	public String recibirMensaje() {
		String mensaje = "";
		try {
			mensaje = this.in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(this.user.getNombre() + " |metodo recibirMensaje: | " + mensaje);
		return mensaje;
	}

	public void cerrarSockets() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.socket = null;
		this.out = null;
		this.in = null;
		this.inSocket = null;
	}

}