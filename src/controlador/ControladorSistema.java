package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negocio.Sistema;
import vista.Ivista;
import vista.Inicio;

public class ControladorSistema implements ActionListener {

	
	private Ivista vista;
    private Sistema sistema = new Sistema();
    
    public ControladorSistema() {
        this.vista = (Ivista) new Inicio();
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
        System.out.println("Comando: " + comando);
        if (comando.equalsIgnoreCase("")) {
        } else if (comando.equalsIgnoreCase("")){
        }
}
	
}