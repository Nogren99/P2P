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

            Thread hilo = new Thread(Sistema.getInstancia());
            hilo.start();

        	this.vista.cerrar();
        	this.setVista(new Chat());        	
        	
        	
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
        	this.sistema.solicitarChat(ip, puerto);
        	this.vista.cerrar();
        	
        	
        //====VENTANA DE CHAT====
        }else if (comando.equalsIgnoreCase("Enviar")){
        	Chat ventana = (Chat) this.vista;
        	String msj = ventana.getTextField().getText();
        	System.out.println("mensaje: "+msj);
        	
        	try {
                if (msj != null && !msj.isEmpty()) {
                    this.sistema.enviarMensaje(msj);
                    ventana.getTextArea().append(Usuario.getInstance().getNombre()+" : " +msj+"\n");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
			vista.getTextArea().setEditable(true);
			vista.getTextArea().append(mensaje);
			vista.getTextArea().setEditable(false);
			//while(true) {
				
				//this.vista.agregarMensaje(Usuario.getInstance().getSesionActual().getRemoto().getUsername() + ": " + mensaje);
				
			//}
			// Lee el primer caracter para checkear que siga establecida la conexion
            
		}/*catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		finally {
			
		}
		
	}
	
}
