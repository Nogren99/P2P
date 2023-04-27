package vista;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Inicio extends JFrame implements Ivista {

	private JPanel contentPane;
	private ActionListener actionListener;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JButton btnNewButton;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_9;
	private JLabel lblNewLabel_3;
	private JPanel panel_19;
	private JPanel panel_20;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(1, 3, 0, 0));
		
		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(10, 0, 0, 0));
		
		this.panel_10 = new JPanel();
		this.panel_10.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_10);
		
		this.panel_11 = new JPanel();
		this.panel_11.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_11);
		
		this.panel_12 = new JPanel();
		this.panel_12.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_12);
		
		this.panel_13 = new JPanel();
		this.panel_13.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_13);
		
		this.panel_14 = new JPanel();
		this.panel_14.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_14);
		
		this.panel_15 = new JPanel();
		this.panel_15.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_15);
		
		this.panel_16 = new JPanel();
		this.panel_16.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_16);
		
		this.panel_17 = new JPanel();
		this.panel_17.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_17);
		
		this.panel_18 = new JPanel();
		this.panel_18.setBackground(new Color(195, 222, 214));
		this.panel_1.add(this.panel_18);
		
		this.panel_9 = new JPanel();
		this.panel_9.setBackground(new Color(156, 191, 181));
		this.panel_1.add(this.panel_9);
		
		this.lblNewLabel_3 = new JLabel("Escucha: OFF");
		this.lblNewLabel_3.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		this.panel_9.add(this.lblNewLabel_3);
		
		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(5, 1, 0, 0));
		
		this.panel_4 = new JPanel();
		this.panel_4.setBackground(new Color(195, 222, 214));
		this.panel_2.add(this.panel_4);
		
		this.panel_5 = new JPanel();
		this.panel_5.setBackground(new Color(195, 222, 214));
		this.panel_2.add(this.panel_5);
		this.panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.lblNewLabel = new JLabel("Configuración");
		this.lblNewLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		this.panel_5.add(this.lblNewLabel);
		
		this.panel_6 = new JPanel();
		this.panel_6.setBackground(new Color(195, 222, 214));
		this.panel_2.add(this.panel_6);
		
		this.lblNewLabel_1 = new JLabel("Dirección IP");
		this.lblNewLabel_1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		this.panel_6.add(this.lblNewLabel_1);
		
		this.textField = new JTextField();
		this.panel_6.add(this.textField);
		this.textField.setColumns(10);
		
		this.panel_7 = new JPanel();
		this.panel_2.add(this.panel_7);
		this.panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.panel_19 = new JPanel();
		this.panel_19.setBackground(new Color(195, 222, 214));
		this.panel_7.add(this.panel_19);
		
		this.lblNewLabel_2 = new JLabel("Puerto");
		this.lblNewLabel_2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		this.panel_19.add(this.lblNewLabel_2);
		
		this.panel_20 = new JPanel();
		this.panel_20.setBackground(new Color(195, 222, 214));
		this.panel_7.add(this.panel_20);
		
		this.textField_1 = new JTextField();
		this.panel_20.add(this.textField_1);
		this.textField_1.setColumns(10);
		
		this.panel_8 = new JPanel();
		this.panel_8.setBackground(new Color(195, 222, 214));
		this.panel_2.add(this.panel_8);
		
		this.btnNewButton = new JButton("Iniciar Sesión");
		this.btnNewButton.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		this.panel_8.add(this.btnNewButton);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBackground(new Color(195, 222, 214));
		this.panel_3.setForeground(new Color(102, 205, 170));
		this.panel.add(this.panel_3);
	}

	
	@Override
	public void cerrar() {
		this.dispose();
	}

	@Override
	public void mostrar() {
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnNewButton.addActionListener(actionListener);
		this.actionListener=actionListener;
		
	}

}
