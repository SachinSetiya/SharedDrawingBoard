package server;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerFrame {

	private JFrame frame;
	private JTextField textField;
	private JComboBox comboSessions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame window = new ServerFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerFrame() {
		initialize();
		fillData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 962, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSelectSession = new JLabel("Select Session");
		lblSelectSession.setBounds(79, 34, 133, 15);
		frame.getContentPane().add(lblSelectSession);

		JButton btnConnect = new JButton("Connect Session");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectSession();
			}
		});
		btnConnect.setBounds(643, 69, 155, 25);
		frame.getContentPane().add(btnConnect);

		JButton btnNewSession = new JButton("New Session");
		btnNewSession.setBounds(605, 311, 155, 30);
		frame.getContentPane().add(btnNewSession);

		textField = new JTextField();
		textField.setBounds(398, 317, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnDeleteSession = new JButton("Delete Session");
		btnDeleteSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSession();
			}
		});
		btnDeleteSession.setBounds(643, 178, 155, 25);
		frame.getContentPane().add(btnDeleteSession);

		JLabel lblOrCreateNew = new JLabel("Or Create new Session");
		lblOrCreateNew.setBounds(79, 258, 211, 25);
		frame.getContentPane().add(lblOrCreateNew);

		JLabel lblActiveSessions = new JLabel("Avaliable Sessions");
		lblActiveSessions.setBounds(187, 71, 146, 20);
		frame.getContentPane().add(lblActiveSessions);

		JLabel lblEnterSessionName = new JLabel("Enter Session Name");
		lblEnterSessionName.setBounds(129, 319, 204, 15);
		frame.getContentPane().add(lblEnterSessionName);

		comboSessions = new JComboBox();
		// comboSessions.setModel(new DefaultComboBoxModel(new String[] {"ss", "dd"}));
		comboSessions.setBounds(398, 73, 139, 24);
		frame.getContentPane().add(comboSessions);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnSave = new JMenu("Save All Sessions");
		mnFile.add(mnSave);

		JMenu mnReset = new JMenu("Reset");
		mnFile.add(mnReset);
	}

	/**
	 * Function used for filling data
	 */
	void fillData() {
		// Fill comboSessions
		ArrayList<String> list = FileHelper.fileNames(FileHelper.SESSION_FOLDER);
		for (String str : list) {
			comboSessions.addItem(str);
		}
	}

	void connectSession() {
		System.out.println(comboSessions.getSelectedItem());
	}

	void deleteSession() {
		FileHelper.deleteFile(FileHelper.SESSION_FOLDER +"/"+comboSessions.getSelectedItem().toString());
	}
}
