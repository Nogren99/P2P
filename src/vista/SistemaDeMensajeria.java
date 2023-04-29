package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class SistemaDeMensajeria extends JFrame implements Ivista{

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel panel_2;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_3;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private ActionListener actionListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaDeMensajeria frame = new SistemaDeMensajeria();
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
	public SistemaDeMensajeria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.btnNewButton = new JButton("Crear Servidor");
		btnNewButton.setActionCommand("Crear");
		this.btnNewButton.setForeground(new Color(0, 0, 0));
		this.btnNewButton.setBorder(new LineBorder(new Color(95, 158, 160), 3, true));
		this.btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		this.btnNewButton.setBackground(new Color(95, 158, 160));
		this.panel.add(this.btnNewButton);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBackground(new Color(102, 205, 170));
		this.panel.add(this.panel_2, BorderLayout.NORTH);
		
		this.panel_4 = new JPanel();
		this.panel_4.setBackground(new Color(102, 205, 170));
		this.panel.add(this.panel_4, BorderLayout.SOUTH);
		
		this.panel_5 = new JPanel();
		this.panel_5.setBackground(new Color(102, 205, 170));
		this.panel.add(this.panel_5, BorderLayout.WEST);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBackground(new Color(102, 205, 170));
		this.panel.add(this.panel_3, BorderLayout.EAST);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.btnNewButton_1 = new JButton("Conectarse");
		btnNewButton_1.setActionCommand("Unirse");
		this.btnNewButton_1.setBorder(new LineBorder(new Color(95, 158, 160), 3, true));
		this.btnNewButton_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		this.btnNewButton_1.setBackground(new Color(95, 158, 160));
		this.btnNewButton_1.setForeground(new Color(0, 0, 0));
		this.panel_1.add(this.btnNewButton_1);
		
		this.panel_6 = new JPanel();
		this.panel_6.setBackground(new Color(102, 205, 170));
		this.panel_1.add(this.panel_6, BorderLayout.NORTH);
		
		this.panel_7 = new JPanel();
		this.panel_7.setBackground(new Color(102, 205, 170));
		this.panel_1.add(this.panel_7, BorderLayout.SOUTH);
		
		this.panel_8 = new JPanel();
		this.panel_8.setBackground(new Color(102, 205, 170));
		this.panel_1.add(this.panel_8, BorderLayout.WEST);
		
		this.panel_9 = new JPanel();
		this.panel_9.setBackground(new Color(102, 205, 170));
		this.panel_1.add(this.panel_9, BorderLayout.EAST);
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
		this.btnNewButton_1.addActionListener(actionListener);
		this.actionListener=actionListener;
	}
	
	

}
