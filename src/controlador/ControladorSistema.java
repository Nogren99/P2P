package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextField;

import modelo.Usuario;
import negocio.Sistema;
import vista.Ivista;
import vista.SistemaDeMensajeria;
import vista.Bienvenido;
import vista.Chat;
import vista.Inicio;

public class ControladorSistema implements ActionListener, Runnable {

	
	private Ivista vista;
    private Sistema sistema = Sistema.getInstancia();
    private JTextField textField;
    private String msj;
    private static ControladorSistema instancia;
    private Thread comunicacion;
    
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
    
    public Ivista getVista()
    {
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
        //aca deberiamos tomar el ip(string) y puerto(int) y llamar a sistema.AbrirAplicacion
        System.out.println("Comando: " + comando);
        
        if (comando.equalsIgnoreCase("Crear")) {
        	this.vista.cerrar();
        	this.setVista(new Inicio());
        }else if (comando.equalsIgnoreCase("Unirse")) {
        	this.vista.cerrar();
        	this.setVista(new Bienvenido());
        	
        	//VENTANA DE CONFIGURACION
        }else if (comando.equalsIgnoreCase("Iniciar Sesión")) {
        	Inicio ventana = (Inicio) this.vista;
        	int puerto = Integer.parseInt( ventana.getTextField_1().getText() );
        	String nombre = ventana.getTextField().getText();
        	
        	Usuario.getInstance().setNombre(nombre);
        	Usuario.getInstance().setPuerto(puerto);
        	
        	//estaaba en usuario
            Thread hilo = new Thread(Sistema.getInstancia());
            hilo.start();
        	
        	System.out.println(puerto);
        	System.out.println(nombre);
        	
        	
        	this.vista.cerrar();
        	this.setVista(new Chat());
        	
        	
        	
        	//VENTANA DE BIENVENIDO
        } else if (comando.equalsIgnoreCase("Conectarse")){
        	System.out.println("a");
        	Bienvenido ventana = (Bienvenido) this.vista;
        	//Agregar nombre a la ventana
        	Usuario.getInstance().setNombre("pepe");
        	try {
				Usuario.getInstance().setIp(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
        	
        	
        	String ip = ventana.getTextField().getText();
        	int puerto = Integer.parseInt( ventana.getTextField_1().getText() );
        	
        	System.out.println("me conecto a:"+ip+ "puerto"+puerto);
        	
        	//Usuario servidor = new Usuario("",ip,puerto);
        	this.sistema.solicitarChat(ip, puerto);
        	
        	this.vista.cerrar();
        	//this.setVista(new Chat());
        	
        }else if (comando.equalsIgnoreCase("Enviar")){
        	System.out.println("envio mensajito");
        	Chat ventana = (Chat) this.vista;
        	String msj = ventana.getTextField().getText();
        	System.out.println(msj);
        	
        	try {
        		System.out.println("trai" );
                if (msj != null && !msj.isEmpty()) {
                    this.sistema.enviarMensaje(msj);
                    ventana.getTextArea().append(msj+"\n");
                    System.out.println("entro" );
                }
            } catch (IOException ex) {
            	System.out.println("catch" );
                throw new RuntimeException(ex);
            }
        	System.out.println("sigo" );
        }
    }
    
    public void ventanaChat() {
    	this.vista.cerrar();
    	this.setVista(new Chat());
    	this.comunicacion = new Thread(this);
    	this.comunicacion.start();
    }

	@Override
	public void run() {
		Chat vista = (Chat) this.vista;
		try {
			String mensaje =  this.sistema.recibirMensaje();
			vista.getTextArea().append(mensaje);
			//while(true) {
				
				//this.vista.agregarMensaje(Usuario.getInstance().getSesionActual().getRemoto().getUsername() + ": " + mensaje);
				
			//}
		}finally {
			
		}
		
	}
	
}
