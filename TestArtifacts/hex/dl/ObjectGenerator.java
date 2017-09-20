package hex.dl;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ObjectGenerator extends JFrame {

	private JPanel contentPane;
	private JTextField methodname;
	private JTextField passdesc;
	private JTextField stepdesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObjectGenerator frame = new ObjectGenerator();
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
	public ObjectGenerator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox type = new JComboBox();
		type.setModel(new DefaultComboBoxModel(
				new String[] { "Editable", "WebObject", "Clickable", "ChildObjects", "CheckBox" }));
		type.setMaximumRowCount(10);
		type.setBounds(251, 26, 115, 20);
		contentPane.add(type);

		JTextArea textArea = new JTextArea();
		textArea.setSelectionColor(Color.GREEN);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 121, 535, 111);
		contentPane.add(textArea);

		methodname = new JTextField();
		methodname.setBounds(103, 26, 138, 20);
		contentPane.add(methodname);
		methodname.setColumns(10);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String s = "public static " + type.getSelectedItem() + "   " + methodname.getText() + "()\n{\n" +

				type.getSelectedItem() + " obj = new " + type.getSelectedItem() + "();" + "\nobj.setsStepName(\""
						+ stepdesc.getText() + "\");" + "\nobj.setsPassDesc(\"" + passdesc.getText() + "\");"
						+ "\n return obj;\n}";
				textArea.append(s);
			}
		});
		btnGenerate.setBounds(501, 60, 115, 50);
		contentPane.add(btnGenerate);

		stepdesc = new JTextField();
		stepdesc.setBounds(103, 61, 388, 20);
		contentPane.add(stepdesc);
		stepdesc.setColumns(10);

		passdesc = new JTextField();
		passdesc.setColumns(10);
		passdesc.setBounds(103, 92, 388, 20);
		contentPane.add(passdesc);

		JLabel lblMethodName = new JLabel("Method Name");
		lblMethodName.setBounds(10, 29, 83, 14);
		contentPane.add(lblMethodName);

		JLabel lblStepDescription = new JLabel("Step Description");
		lblStepDescription.setBounds(10, 64, 83, 14);
		contentPane.add(lblStepDescription);

		JLabel lblPassDescription = new JLabel("Pass Description");
		lblPassDescription.setBounds(10, 94, 83, 14);
		contentPane.add(lblPassDescription);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 617, 21);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAuthorArunSanmugam = new JMenuItem("Author: Arun Shanmugam");
		mnHelp.add(mntmAuthorArunSanmugam);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(550, 121, 67, 111);
		contentPane.add(btnClear);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "XPATH", "CSS", "LINKNAME", "ID", "NAME" }));
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(376, 26, 115, 20);
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Mandatory", "Optional" }));
		comboBox_1.setMaximumRowCount(10);
		comboBox_1.setBounds(501, 26, 115, 20);
		contentPane.add(comboBox_1);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(528, 121, 17, 111);
		contentPane.add(scrollBar);
	}
}
