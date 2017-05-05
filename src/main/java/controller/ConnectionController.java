package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import service.ClientStub;
import service.rpc.ArticleService;
import service.rpc.ClientRPCStub;
import service.soap.AxisDirectoryStub;
import service.soap.ClientSoapStub;

/** Класс служит для создания соединения с сервером
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ConnectionController {

	private static TTransport transport;
	/** Ссылка на класс-стаб для взаимодействия с web-сервисом */
	private static ClientStub clientStub;
	/** Поле для ввода ip-адреса сервера */
	private JTextField ipField;
	/** Поле для ввода порта сервера */
	private JTextField portField;
	private JFrame frame;
	
	/** Конструктор */
	public ConnectionController() {
		introView();
	}
	
	/** Метод создает стартовое окно для ввода адреса сервера */
	private void introView() {
		frame = new JFrame();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
		titlePanel.setBounds(0, 0, 300, 40);
		
		JLabel title = new JLabel("Enter server adress");
		Font font = new Font("Arial", Font.BOLD, 13);
		title.setFont(font);
		title.setBounds(85, 10, 150, 20);
		titlePanel.add(title);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(null);
		optionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		optionPanel.setBounds(0, 40, 300, 85);
		
		JLabel ip = new JLabel("IPv4 and port:");
		ip.setBounds(30, 30, 100, 20);
		
		ipField = new JTextField(50);
		ipField.setBounds(120, 30, 100, 20);
		
		portField = new JTextField(10);
		portField.setBounds(230, 30, 40, 20);
		
		optionPanel.add(ip);
		optionPanel.add(ipField);
		optionPanel.add(portField);
		
		JPanel connectPanel = new JPanel();
		connectPanel.setLayout(null);
		connectPanel.setBounds(0, 125, 300, 75);
		
		JButton connect = new JButton("CONNECT");
		connect.setBounds(100, 14, 90, 20);
		connect.addActionListener(new ButtonListener());
		connectPanel.add(connect);
		
		frame.add(titlePanel);
		frame.add(optionPanel);
		frame.add(connectPanel);
		frame.setVisible(true);
	}
	
	/** Метод для соединения с rpc web-сервисом
	 * 
	 * @param ip ip-адрес сервера
	 * @param port порт сервера
	 * @throws TException исключение в случае ошибки соединения с сервером
	 */
	private void rpcConnection(String ip, int port) throws TException {
		transport = new TSocket(ip, port);
		transport.open();

		TProtocol protocol = new TBinaryProtocol(transport);
		ArticleService.Client client = new ArticleService.Client(protocol);
			
		ClientStub clientStub = new ClientRPCStub(client);
		setClientStub(clientStub);
	}
	
	/** Метод для соединения с документно-ориентированным web-сервисом
	 * 
	 * @param ip ip-адрес 
	 * @param port порт сервера
	 * @throws RemoteException исключение в случае ошибки соединения с сервером
	 */
	private void soapConnection(String ip, String port) throws RemoteException {
		ClientStub clientStub = new ClientSoapStub(
			new AxisDirectoryStub("http://" + ip + ":" + port + "/WebServiceProject/services/AxisDirectory"));
		setClientStub(clientStub);
	}
	
	private static void setClientStub(ClientStub clientStubArg) {
		clientStub = clientStubArg;
	}
	
	public static ClientStub getClientStub() {
		return clientStub;
	}
	
	/** Метод для закрытия соединения с сервером */
	public static void closeConnection() {
		transport.close();
	}

	private String getIP() {
		return ipField.getText();
	}
	
	private String getPort() {
		return portField.getText();
	}
	
	/** Слушатель кнопки установления соединения с сервером */
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			try {
				if(Integer.parseInt(getPort()) != 9090) {
					throw new TException();
				}
				rpcConnection(getIP(), Integer.parseInt(getPort()));
				new ViewController();
				frame.setVisible(false);
			} catch(TException tException) {
				try {
					if(!getPort().equals("8080")) {
						throw new RemoteException();
					}
					soapConnection(getIP(), getPort());
					new ViewController();
					frame.setVisible(false);
				} catch(RemoteException rException) {
					JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch(NumberFormatException numException) {
				JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	/** Метод main */
	public static void main(String[] args) {
		new ConnectionController();
	}
	
}
