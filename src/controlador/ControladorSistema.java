package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Usuario;
import negocio.Sistema;
import vista.Ivista;
import vista.SistemaDeMensajeria;
import vista.Bienvenido;
import vista.Chat;
import vista.Inicio;
import vista.SalaDeEspera;

public class ControladorSistema implements ActionListener {

	private Ivista vista;
	private WindowListener escuchaVentana;
    private Sistema sistema = Sistema.getInstancia();
    private JTextField textField;
    private String msj;
    private static ControladorSistema instancia;
    private Thread comunicacion;
    private boolean isSolicitante;
    
    public void setMsj(String msj) {
		this.msj = msj;
	}
    
    public static ControladorSistema getInstancia() {
        if (instancia == null)
            instancia = new ControladorSistema();
        return instancia;
    }

	public ControladorSistema() {
        this.vista = new SistemaDeMensajeria();
        this.vista.setActionListener(this);
        this.vista.mostrar();
    }
    
    public Ivista getVista(){
        return vista;
    }

    private void setVista(Ivista vista) {
        this.vista=vista;
        this.vista.setActionListener(this);
        this.vista.mostrar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        //System.out.println("Comando: " + comando);
       
        if (comando.equalsIgnoreCase("Crear")) {
        	this.vista.cerrar();
        	this.setVista(new Inicio());
        }else if (comando.equalsIgnoreCase("Unirse")) {
        	this.vista.cerrar();
        	this.setVista(new Bienvenido());
        	
        //====VENTANA DE CONFIGURACION=====
        }else if (comando.equalsIgnoreCase("Iniciar Sesión")) {
        	Inicio ventana = (Inicio) this.vista;
        	
        	int puerto = Integer.parseInt( ventana.getTextField_1().getText() );
        	String nombre = ventana.getTextField().getText();
        	Usuario.getInstance().setNombre(nombre);
        	Usuario.getInstance().setPuerto(puerto);
        	
        	System.out.println("Inicio en puerto: "+ puerto+" username: "+nombre);
        	try {
        		Usuario.getInstance().setIp(InetAddress.getLocalHost().getHostAddress());
				System.out.println("Inicio en ip: "+ Usuario.getInstance().getIp());
			}catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally { } 

            Thread hilo = new Thread(Sistema.getInstancia());
            hilo.start();

        	this.vista.cerrar();
        	//this.setVista(new Chat());        	

        //=====VENTANA DE BIENVENIDO====
        } else if (comando.equalsIgnoreCase("Conectarse")){
        	Bienvenido ventana = (Bienvenido) this.vista;
        	
        	//MIS DATOS:
        	Usuario.getInstance().setNombre(ventana.getTextField_2().getText());
        	try {
				Usuario.getInstance().setIp(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
        	
        	//DATOS DEL USUARIO AL QUE ME CONECTO
        	String ip = ventana.getTextField().getText();
        	int puerto = Integer.parseInt( ventana.getTextField_1().getText() );
        	System.out.println("me conecto a:"+ip+ "puerto"+puerto);
        	
        	//SOLICITO INICIAR CHAT
        	this.sistema.solicitarChat(ip, puerto,Usuario.getInstance().getNombre());
        	
        	
        //====VENTANA DE CHAT====
        }else if (comando.equalsIgnoreCase("Enviar")){
        	Chat ventana = (Chat) this.vista;
			String msj = ventana.getTextField().getText();
			System.out.println("mensaje: "+msj);
			ventana.getTextField().setText("");

			if (msj != null && !msj.isEmpty()) {
				ventana.getTextArea().append(Usuario.getInstance().getNombre() + " (Tu) : " + msj + "\n");
				if (this.isSolicitante) {
					Sistema.getInstancia().enviarMensaje(msj, Usuario.getInstance().getNombre(),"destinatario");
				} else if (this.isSolicitante == false) {
					Sistema.getInstancia().enviarMensaje(msj, Usuario.getInstance().getNombre(),"interlocutor");
				}

			}
        }
    }
    
    
	public boolean isSolicitante() {
		return isSolicitante;
	}

	public void setSolicitante(boolean isSolicitante) {
		this.isSolicitante = isSolicitante;
	}
	
	
	public void ventanaChatSolicitante() {
		this.vista.cerrar();
		this.setVista(new Chat());
		Chat chat = (Chat) this.vista;
		//chat.getLblChatCon().setText("Chat con: " + this.nombreDestinatario);
	}

	public void ventanaChatSolicitado(String nombre) {
		this.vista.cerrar();
		this.setVista(new Chat());
		//this.nombreSolicitante = nombre;
		Chat chat = (Chat) this.vista;
		//chat.getLblChatCon().setText("Chat con: " + nombre);
	}
    
    public void ventanaEspera() {
    	this.vista.cerrar();
    	this.setVista(new SalaDeEspera());
    }
    
    public void ventanaChat() {
    	this.vista.cerrar();
    	this.setVista(new Chat());
    	//this.comunicacion = new Thread(this);
    	this.comunicacion.start();
    }
    
    public void cerrarVentana() {
    	System.out.println("cierro ventana");
    	Sistema.getInstancia().cerrarSockets();
    	this.comunicacion.interrupt();
    }
    
	public void actualizaChat(String nombre, String mensaje) {
		Chat chat = (Chat) this.vista;
		chat.getTextArea().append(nombre + " : " + mensaje + "\n");
	}


	
	
}
