package br.com.notna.projetoempresas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.notna.projetoempresas.model.Company;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFrame frame = new CadastroFrame();
					frame.setTitle("Cadastrar uma empresa");
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
	public CadastroFrame() {
		setTitle("Cadastrar uma empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Trade Name");
		lblNewLabel.setBounds(10, 11, 58, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Corporate Name");
		lblNewLabel_1.setBounds(10, 36, 104, 14);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("Country");
		label.setBounds(10, 70, 24, -2);
		contentPane.add(label);

		JLabel lblNewLabel_2 = new JLabel("state");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("city");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("neighbourhood");
		lblNewLabel_4.setBounds(10, 111, 86, 14);
		contentPane.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setBounds(124, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(124, 33, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(124, 58, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(124, 83, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(124, 108, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("address");
		lblNewLabel_5.setBounds(10, 136, 46, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("phone");
		lblNewLabel_6.setBounds(10, 161, 46, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("federalTaxNumber");
		lblNewLabel_7.setBounds(10, 186, 104, 14);
		contentPane.add(lblNewLabel_7);

		textField_5 = new JTextField();
		textField_5.setBounds(124, 133, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(124, 158, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(124, 183, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				try {
					Company company = new Company();
				company.setTradeName(textField.getText());
				company.setCorporateName(textField_1.getText());
				company.setState(textField_2.getText());
				company.setCity(textField_3.getText());
				company.setNeighbourhood(textField_4.getText());
				company.setAddress(textField_5.getText());
				company.setPhone(textField_6.getText());
				company.setFederalTaxNumber(textField_7.getText());
					post("http://localhost:8080/company", company);
					JOptionPane.showMessageDialog(null, "Sua empresa foi cadastrada com sucesso.", "Cadastrado ", JOptionPane.DEFAULT_OPTION);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ocorreu um error ao cadastrar sua empresa: \n\n" + e.getMessage(), "Não cadastrado", JOptionPane.ERROR_MESSAGE);
					
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton);
	}

	public void post(String uri, Company company) throws Exception {
		boolean cadastrado = false;
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(uri);
			Gson g = new Gson();
			StringEntity params = new StringEntity(g.toJson(company));
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			InputStream body = response.getEntity().getContent();
			String responseContent = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
			ResponseObjectModel<Company> p = g.fromJson(responseContent, ResponseObjectModel.class);

			java.lang.reflect.Type collectionType = new TypeToken<ResponseObjectModel<Company>>() {
			}.getType();
			ResponseObjectModel<Company> model = g.fromJson(responseContent, collectionType);

			
			
			
		} catch (Exception ex) {
			
		} 
		
		dispose();
		
		
	}
}
