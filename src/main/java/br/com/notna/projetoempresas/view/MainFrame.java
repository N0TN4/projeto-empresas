package br.com.notna.projetoempresas.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.notna.projetoempresas.model.Company;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */

	public ArrayList<Company> companyList = new ArrayList<Company>();
	DefaultListModel modelo_da_lista = new DefaultListModel();
	JList list;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.list = new JList();
		contentPane.setLayout(null);
		for(Company company : companyList) {
			modelo_da_lista.addElement(company.getCorporateName());
		}
		
		
		
		
		list.setBounds(42, 147, 173, 155);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Company company = new Company();
				
				company.setCorporateName("teste 1");
			
//				adicionarUmaEmpresa(this.list);
				adicionarUmaEmpresa(company);
			}
		});
		btnNewButton.setBounds(240, 193, 89, 23);
		contentPane.add(btnNewButton);
		
		
//		lblNewLabel.setBounds(311, 38, 146, 14);
//		contentPane.add(lblNewLabel);
//		
		
		
		
	}
	
	
	void adicionarUmaEmpresa( Company empresa) {
		empresa.setCorporateName("testoNildo");
		
		
		modelo_da_lista.addElement(empresa.getCorporateName());

		list.setModel(modelo_da_lista);
	}
}
