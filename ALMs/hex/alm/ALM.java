package hex.alm;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
//import com.google.common.collect.Multimap;
public class ALM {

	private static  JFrame frmAlmTcExporter;
	private JTabbedPane tabbedPane_1;
	private JTabbedPane tabbedPane_2;
	private JPanel panel;
	private JButton btnNewButton;
	private static JTextField sALMURL;
	private JLabel lblUrl;
	private static JTextField sUser;
	private static JTextField sPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JComboBox projectcomboBox;
	private JMenu mnHelp;
	private JMenuItem mntmAboutAlmTc;
	private JMenuItem mntmDevauthorarunShanmugam;
	private JTextField savedir;
	private JFileChooser chooser;
	private JScrollPane scrollPane;
	private static JEditorPane dtrpnStatus ;
	private	JTree Testplantree ;
	private JScrollPane scrollPane_1;
	private JComboBox domaincomboBox ;
		 static Map<String,String> mapDomain ;
		 
		 static DefaultComboBoxModel modelDomain;
		 static DefaultComboBoxModel modelprj;
		 
	
		 //Main function --> Launch application 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ALM window = new ALM();
					window.frmAlmTcExporter.setVisible(true);
					//ALM.Authorization();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Messagebox
	 public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	        
	    }
	 
	// ALM Licence 
	public static void Authorization(){
		String name = JOptionPane.showInputDialog(frmAlmTcExporter,
                "Authorization Code", "101");
		try{
			
		
			Integer namecast = Integer.parseInt(name);
		
		if (!namecast.equals(101)){
			
			ALM.infoBox("Invalid Authorization Code", "Authorization Error");
		System.exit(0);
		}
		else{
			ALM.infoBox(" Authorization Confirmation", "Authorization Success!!");
		}
		}
		catch (NumberFormatException e){
			ALM.infoBox("Please enter a valid Authorization code to proceed", "Authorization Error");
		}
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	
	//Constructor
	public ALM() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmAlmTcExporter = new JFrame();
		frmAlmTcExporter.setTitle("ALM TC Exporter");
		frmAlmTcExporter.setBounds(100, 100, 576, 493);
		frmAlmTcExporter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlmTcExporter.getContentPane().setLayout(null);
		
		tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(10, 0, 540, 423);
		frmAlmTcExporter.getContentPane().add(tabbedPane_2);
		
		
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		tabbedPane_2.addTab("TC Exporter", null, panel, null);
		panel.setLayout(null);
		
		//Authenticate Button
		btnNewButton = new JButton("Authenticate");
		
		btnNewButton.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
				//Action Performed 
						
				
				ALM.doLogin();
				
				/*//////////////////////////////////////
				
				
				

				Testplantree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("TESTPLAN") {
							{
								
								//first parent node
								DefaultMutableTreeNode node_1;
								
								//Second parent node
								DefaultMutableTreeNode node_2;
								
								//node1 child/grandchild/leafchild
								node_1 = new DefaultMutableTreeNode("Folder1");
								
									node_1.add(new DefaultMutableTreeNode("TC1"));
									node_1.add(new DefaultMutableTreeNode("TC2"));
									node_1.add(new DefaultMutableTreeNode("TC23"));
									
									
								add(node_1);
								
								
								node_1 = new DefaultMutableTreeNode("Folder2");
									node_1.add(new DefaultMutableTreeNode("TC1"));
									
									//New folder inside 
									node_2 = new DefaultMutableTreeNode("Subfilder");
										node_2.add(new DefaultMutableTreeNode("TCsub"));
									node_1.add(node_2);
								add(node_1);
								
								node_1 = new DefaultMutableTreeNode("Folder23");
								node_1.add(new DefaultMutableTreeNode("TC1"));
								add(node_1);
							}
						}
					));
			*///////////////////////////////////////////////////////////////////////	
			}
		});
		btnNewButton.setBounds(207, 42, 95, 51);
		panel.add(btnNewButton);
		
		sALMURL = new JTextField();
		sALMURL.setText("http://ey-alm.ey.net:8080/qcbin/");
		sALMURL.setBounds(75, 11, 238, 20);
		panel.add(sALMURL);
		sALMURL.setColumns(10);
		
		lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 14, 46, 14);
		panel.add(lblUrl);
		
		
		sUser = new JTextField();
		sUser.setText("arun.shanmughamus");
		
		sUser.setBounds(75, 42, 105, 20);
		panel.add(sUser);
		sUser.setColumns(10);
		
		sPassword = new JPasswordField();
		sPassword.setBounds(75, 73, 105, 20);
		panel.add(sPassword);
		sPassword.setColumns(10);
		
		lblUsername = new JLabel("UserName");
		lblUsername.setBounds(10, 45, 65, 14);
		panel.add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 76, 46, 14);
		panel.add(lblPassword);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 33, 232, 286);
		panel.add(scrollPane);
				
		//Jtree for test plan
		 Testplantree = new JTree();
		 Testplantree.setBorder(null);
		 Testplantree.setForeground(UIManager.getColor("Button.background"));
		 Testplantree.setBackground(new Color(192, 192, 192));
		 Testplantree.setModel(new DefaultTreeModel(
		 	new DefaultMutableTreeNode("JTree") {
		 		{
		 			DefaultMutableTreeNode node_1;
		 			node_1 = new DefaultMutableTreeNode("colors");
		 				node_1.add(new DefaultMutableTreeNode("blue"));
		 				node_1.add(new DefaultMutableTreeNode("violet"));
		 				node_1.add(new DefaultMutableTreeNode("red"));
		 				node_1.add(new DefaultMutableTreeNode("yellow"));
		 			add(node_1);
		 		}
		 	}
		 ));
		scrollPane.setViewportView(Testplantree);
	
		
		
		Testplantree.setVisibleRowCount(1000);
		 modelDomain = new DefaultComboBoxModel();
		 domaincomboBox = new JComboBox(modelDomain);
		 domaincomboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		//Combo box text changed
		 		modelprj.removeAllElements();
		 		String sSelectedDomain = modelDomain.getSelectedItem().toString();
				
		 		for (Entry<String, String> entry : mapDomain.entrySet())
		 		{
//		 		    entry.getKey() + "/" + 

		 			//Add Domain
		 			if (entry.getValue().equals(sSelectedDomain)){
		 			
		 				
		 				
		 				if(modelprj.getIndexOf(entry.getKey()) == -1 ) {
		 					modelprj.addElement(entry.getKey());
		 				}
		 			
		 		    
		 			}
		 		}			
		 		//
		 			
		 		
		 	}
		 });
		domaincomboBox.setBounds(75, 104, 105, 20);
		panel.add(domaincomboBox);
		 modelprj = new DefaultComboBoxModel();
		projectcomboBox = new JComboBox(modelprj);
		projectcomboBox.setBounds(75, 135, 105, 20);
		panel.add(projectcomboBox);
		
		JLabel lblDomain = new JLabel("Domain");
		lblDomain.setBounds(10, 107, 46, 14);
		panel.add(lblDomain);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setBounds(10, 138, 46, 14);
		panel.add(lblProject);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Login to get  Test plan tree structures
				
				String domain = (String)domaincomboBox.getSelectedItem();
				
				String project = (String)projectcomboBox.getSelectedItem();
				
			}
		});
		btnNewButton_1.setBounds(207, 104, 95, 51);
		panel.add(btnNewButton_1);
		
		savedir = new JTextField();
		savedir.setBounds(10, 166, 170, 20);
		panel.add(savedir);
		savedir.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Jfilechooser
				 int result;
				 
				    chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle("Choose Dir");
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    //    
				    if (chooser.showOpenDialog(btnBrowse) == JFileChooser.DIRECTORIES_ONLY) { 
				    	savedir.setText( chooser.getCurrentDirectory().toString());		        
				      
				      }
				    else {
				    //	savedir.setText( chooser.getCurrentDirectory().toString());	
				    	savedir.setText( chooser.getCurrentDirectory().toString());		
				      }
			}
		});
		btnBrowse.setBounds(213, 166, 89, 23);
		panel.add(btnBrowse);
		 
		 scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(10, 270, 292, 38);
		 panel.add(scrollPane_1);
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(10, 221, 292, 38);
		panel.add(btnExport);
		
		JButton btnlogout = new JButton("Logout");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ALM.display();
				
			}
		});
		btnlogout.setBounds(436, 11, 89, 20);
		panel.add(btnlogout);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 364, 544, 20);
		panel.add(progressBar);
		
		 dtrpnStatus = new JEditorPane();
		 dtrpnStatus.setBounds(10, 317, 292, 36);
		 panel.add(dtrpnStatus);
		 dtrpnStatus.setText("System Status:");
		 
		 
		dtrpnStatus.setToolTipText("Statusupdate");
		dtrpnStatus.setBackground(UIManager.getColor("Button.background"));
		
		JMenuBar menuBar = new JMenuBar();
		frmAlmTcExporter.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAboutAlmTc = new JMenuItem("About ALM TC Exporter");
		mnHelp.add(mntmAboutAlmTc);
		
		mntmDevauthorarunShanmugam = new JMenuItem("Dev/Author:Arun Shanmugam");
		mnHelp.add(mntmDevauthorarunShanmugam);
		
		
	}
	
	
	//Custom Methods**************************************************
	
	public static void doLogin(){
		
		
		String User = sUser.getText().toString();
		String Password = sPassword.getText().toString();
		String sURL = sALMURL.getText().toString();
					
		dtrpnStatus.setText(OTAWrapper.login(User, Password, sURL));
		
	 mapDomain = OTAWrapper.getDomainsprojects();
		

		
for (Entry<String, String> entry : mapDomain.entrySet())
{
//entry.getKey() + "/" + 

//Add Domain


if(modelDomain.getIndexOf(entry.getValue()) == -1 ) {
modelDomain.addElement(entry.getValue());
}


}



///Same code hasbeen copied to combobox event********************************************************************************************************************************
String sSelectedDomain = modelDomain.getSelectedItem().toString();
		
for (Entry<String, String> entry : mapDomain.entrySet())
{
//entry.getKey() + "/" + 

//Add Domain
if (entry.getValue().equals(sSelectedDomain)){



if(modelprj.getIndexOf(entry.getKey()) == -1 ) {
	modelprj.addElement(entry.getKey());
}


}
}			
//


	}
	//Displays a popup on clicking login button
	private static void display() {
       // String[] items = {"One", "Two", "Three", "Four", "Five"};
        //JComboBox combo = new JComboBox(items);
        JTextField field1 = new JTextField("ALM USERNAME");
        JTextField field2 = new JTextField("PASSWORD");
        JPanel panel = new JPanel(new GridLayout(0, 1));
      //  panel.add(combo);
        panel.add(new JLabel("Username"));
        panel.add(field1);
        panel.add(new JLabel("Password"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Authentication",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
           
        } else {
            //System.out.println("Cancelled");
        }
    }
}
