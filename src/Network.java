import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Network implements ActionListener{

	private JFrame frmJavaNetworks;
	private JTextField Host;
	private JTextArea Result;
	private JButton btnIP,btnPing;
	private InetAddress ia;
	private String result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Network window = new Network();
					window.frmJavaNetworks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Network() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJavaNetworks = new JFrame();
		frmJavaNetworks.getContentPane().setBackground(Color.DARK_GRAY);
		frmJavaNetworks.setTitle("Java Networks");
		frmJavaNetworks.setResizable(false);
		frmJavaNetworks.setBounds(100, 100, 525, 341);
		frmJavaNetworks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJavaNetworks.getContentPane().setLayout(null);
		
		JLabel lblJavaNetworking = new JLabel("java networking");
		lblJavaNetworking.setForeground(Color.CYAN);
		lblJavaNetworking.setBounds(261, 12, 133, 15);
		frmJavaNetworks.getContentPane().add(lblJavaNetworking);
		
		JLabel lblHost = new JLabel("HOST");
		lblHost.setForeground(Color.GREEN);
		lblHost.setBounds(31, 65, 50, 15);
		frmJavaNetworks.getContentPane().add(lblHost);
		
		Host = new JTextField();
		Host.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 10));
		Host.setBounds(87, 63, 341, 29);
		frmJavaNetworks.getContentPane().add(Host);
		Host.setColumns(10);
		
		btnIP = new JButton("GET HOST IP ");
		btnIP.setForeground(Color.WHITE);
		btnIP.setBackground(Color.BLUE);
		btnIP.setFont(new Font("Dialog", Font.BOLD, 8));
		btnIP.setBounds(95, 118, 99, 25);
		btnIP.addActionListener(this);
		frmJavaNetworks.getContentPane().add(btnIP);
		
		btnPing = new JButton("PING HOST");
		btnPing.setForeground(Color.WHITE);
		btnPing.setFont(new Font("Dialog", Font.BOLD, 8));
		btnPing.setBackground(Color.BLUE);
		btnPing.setBounds(328, 118, 85, 25);
		btnPing.addActionListener(this);
		frmJavaNetworks.getContentPane().add(btnPing);
		
		Result = new JTextArea();
		Result.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 10));
		Result.setForeground(Color.BLACK);
		Result.setBackground(Color.ORANGE);
		Result.setEditable(false);
		Result.setBounds(48, 164, 446, 139);
		frmJavaNetworks.getContentPane().add(Result);
		
		JLabel lblResults = new JLabel("RESULTS");
		lblResults.setForeground(Color.CYAN);
		lblResults.setBounds(221, 147, 70, 15);
		frmJavaNetworks.getContentPane().add(lblResults);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		Object ob = ev.getSource();
		
		if(ob.equals(btnIP)) {
			//get and show the ip address
			if(isEmpty()) {
				String ip = GetIp(Host.getText());
				Result.setText(ip);
			}
		}
		
		else if(ob.equals(btnPing)) {
			//do ping the host
				try {
					if(isEmpty()) {
						String host = Host.getText();
						//check if host is reachable
						if(PingHost(host)) {
							Result.setText("Host "+host+" is Reachable");
				    	}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Result.setText(e.getMessage());
				}
		}
		
		}
			
	
	//a method to get the ip address.
	private String GetIp(String host) {
		try {
			ia= InetAddress.getByName(host); 
			result = "IP address of "+host+" is "+ia.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			result = e.getMessage();
		}
		return result;
	}
	
	//a method to ping host
	private boolean PingHost(String host) throws IOException {
			ia= InetAddress.getByName(host);
			if(ia.isReachable(5000)) {
				//five seconds timeout
				return true;
			}else {
				return false;
			}
	}
	
	//for empty host
	private boolean isEmpty()  {
		if(!Host.getText().isBlank()) {
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Enter a host!!");
			return false;
		}
	}
}
