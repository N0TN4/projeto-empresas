package br.com.notna.projetoempresas.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.notna.projetoempresas.model.Company;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public ArrayList<Company> companyList = new ArrayList<Company>();
	DefaultListModel<String> modelo_da_lista = new DefaultListModel<String>();
	JList list;
	private JTextField textField;

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
		setTitle("Empresas");
		this.list = new JList();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setBounds(42, 147, 173, 155);
		list.setLayoutOrientation(JList.VERTICAL);
		scrollPane.setBounds(42, 147, 173, 155);
		contentPane.add(scrollPane);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CadastroFrame frame = new CadastroFrame();
				frame.setVisible(true);
//				
			}
		});
		btnNewButton.setBounds(225, 202, 89, 41);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Atualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					get("http://localhost:8080/company");
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(225, 115, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Cadastro de empresas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(42, 25, 298, 50);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(42, 116, 99, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton("Ir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarEmpresas(textField.getText());
			}
		});
		btnNewButton_2.setBounds(151, 115, 60, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar");
		lblNewLabel_1.setBounds(42, 98, 46, 14);
		contentPane.add(lblNewLabel_1);

		try {
			get("http://localhost:8080/company");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	void adicionarUmaEmpresa(Company empresa) {
		empresa.setCorporateName(empresa.getCorporateName());
		
		modelo_da_lista.addElement("#" + empresa.getId()+ " " + empresa.getCorporateName());

		list.setModel(modelo_da_lista);
	}

	public void get(String uri) throws Exception {
		modelo_da_lista.removeAllElements();
		list.setModel(modelo_da_lista);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpUriRequest httpUriRequest = new HttpGet(uri);

		HttpResponse response = client.execute(httpUriRequest);
		InputStream body = response.getEntity().getContent();
		String responseContent = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
		Gson g = new Gson();
		ArrayList<Company> listaDeCompanys = new ArrayList<Company>();

		ResponseObjectModel<Company> p = g.fromJson(responseContent, ResponseObjectModel.class);
		java.lang.reflect.Type collectionType = new TypeToken<ResponseObjectModel<Company>>() {
		}.getType();
		ResponseObjectModel<Company> model = g.fromJson(responseContent, collectionType);

		for (Company company : model.data) {
			adicionarUmaEmpresa(company);
		}

	}


	
	void buscarEmpresas(String nomeDaEmpresa) {
		DefaultListModel<String> newList = new DefaultListModel<String>();

		for (int i = 0; i < modelo_da_lista.getSize(); i++) {
			if (modelo_da_lista.elementAt(i).toString().contains(nomeDaEmpresa)) {
				newList.addElement(modelo_da_lista.elementAt(i).toString());
			}
		}
		list.setModel(newList);

	}
}
