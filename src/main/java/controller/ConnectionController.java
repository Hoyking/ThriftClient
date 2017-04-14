package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.axis2.AxisFault;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import service.ClientStub;
import service.rpc.ArticleNotFoundException;
import service.rpc.ArticleService;
import service.rpc.ClientRPCStub;
import service.soap.AxisDirectoryStub;
import service.soap.ClientSoapStub;

public class ConnectionController {

	private static TTransport transport;
	private static ClientStub clientStub;
	private JTextField ipField;
	private JTextField portField;
	
	public ConnectionController() {
		introView();
		/*try {
			rpcConnection("127.0.0.1", 9090);
			new ViewController();
		} catch (TException e1) {
			JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
		}*/
		/*try {
			soapConnection("100.95.255.221", "8080");
			new ViewController();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
		}*/
	}
	
	private void introView() {
		JFrame frame = new JFrame();
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
		titlePanel.setBounds(0, 0, 300, 40);
		
		JLabel title = new JLabel("Enter server IP");
		Font font = new Font("Arial", Font.BOLD, 13);
		title.setFont(font);
		title.setBounds(100, 10, 150, 20);
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
	
	private void rpcConnection(String ip, int port) throws TException {
		transport = new TSocket(ip, port);
		transport.open();

		TProtocol protocol = new TBinaryProtocol(transport);
		ArticleService.Client client = new ArticleService.Client(protocol);
			
		ClientStub clientStub = new ClientRPCStub(client);
		setClientStub(clientStub);
	}
	
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
	
	public static void closeConnection() {
		transport.close();
	}

	private String getIP() {
		return ipField.getText();
	}
	
	private String getPort() {
		return portField.getText();
	}
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				rpcConnection(getIP(), Integer.parseInt(getPort()));
				new ViewController();
				System.out.println("RPC");
			} catch(TException tException) {
				try {
					soapConnection(getIP(), getPort());
					new ViewController();
					System.out.println("SOAP");
				} catch(RemoteException rException) {
					JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch(NumberFormatException numException) {
				JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the server", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new ConnectionController();
	}
	
}
