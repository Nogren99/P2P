package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import negocio.Sistema;
import vista.Ivista;
import vista.SistemaDeMensajeria;
import vista.Bienvenido;
import vista.Chat;
import vista.Inicio;

public class ControladorSistema implements ActionListener {

	
	private Ivista vista;
    private Sistema sistema = new Sistema();
    private JTextField textField;
    private String msj;
    
    public void setMsj(String msj) {
		this.msj = msj;
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
        }else if (comando.equalsIgnoreCase("Iniciar Sesión")) {
        	Inicio ventana = (Inicio) this.vista;
        	int puerto = Integer.parseInt( ventana.getTextField_1().getText() );
        	System.out.println(puerto);
        	sistema.Servidor(puerto);
        	this.vista.cerrar();
        	this.setVista(new Chat());
        	
        } else if (comando.equalsIgnoreCase("Conectarse")){
        	System.out.println("a");
        	Bienvenido ventana = (Bienvenido) this.vista;
        	String ip = ventana.getTextField().getText();
        	int puerto = Integer.parseInt( ventana.getTextField_1().getText() );
        	System.out.println("me conecto a:"+ip+ "puerto"+puerto);
        	sistema.cliente(ip,puerto);
        	this.vista.cerrar();
        	this.setVista(new Chat());
        	
        }else if (comando.equalsIgnoreCase("Enviar")){
        	System.out.println("b");
        	Chat ventana = (Chat) this.vista;
        	ventana.getTextField().getText();
        	
        	String msj = sistema.getMsg();
        	System.out.println(msj);
        	
        	ventana.getModeloListaEnviados().addElement(msj);
        	ventana.repaint();
        }
}
	
}
