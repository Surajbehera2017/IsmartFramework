/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework;

import hex.framework.broadcast.DisplayServer;
import hex.framework.common.PreventSleep;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class Execute {

	private JFrame frmRrrSeleniumTest;
	static Thread tFramework;
	private static boolean boolpause = false;
	static Thread iProgress;
	static JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Execute window = new Execute();
					window.frmRrrSeleniumTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				ProgressBarUpdator ju = new ProgressBarUpdator(progressBar);
				iProgress = new java.lang.Thread(ju);
				iProgress.start();

			}
		});
	}

	/**
	 * Create the application.
	 */
	public Execute() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public static void startFrameworkThread() {
		
		PreventSleep.Prevent();

		Driver d = new Driver();

		tFramework = new Thread(d);
		tFramework.start();

		Runnable monitor = () -> {
			try {
				hex.framework.broadcast.Server.main(null);
				DisplayServer.main(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		};

		new Thread(monitor).start();

	}

	public static void pauseFrameworkThread() {
		tFramework.suspend();
	}

	public static void resumeFrameworkThread() {

		tFramework.resume();
	}

	private void initialize() {
		frmRrrSeleniumTest = new JFrame();
		frmRrrSeleniumTest.setTitle("Stockland Selenium Test Suite Runner");
		frmRrrSeleniumTest.setBounds(100, 100, 450, 145);
		frmRrrSeleniumTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRrrSeleniumTest.getContentPane().setLayout(null);

		JButton btnStartExecution = new JButton("Start Execution");
		btnStartExecution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Execute.startFrameworkThread();

			}
		});
		btnStartExecution.setBounds(125, 11, 139, 42);
		frmRrrSeleniumTest.getContentPane().add(btnStartExecution);

		JButton btnPause = new JButton("Pause ");
		btnPause.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {

				if (!boolpause) {
					Execute.pauseFrameworkThread();
					btnPause.setText("Resume");
					boolpause = true;
				} else {

					Execute.resumeFrameworkThread();
					btnPause.setText("Pause");
					boolpause = false;
				}
			}
		});
		btnPause.setBounds(285, 11, 139, 42);
		frmRrrSeleniumTest.getContentPane().add(btnPause);

		JCheckBox chckbxDebugMode = new JCheckBox("Debug Mode");
		chckbxDebugMode.setBounds(6, 21, 97, 23);
		frmRrrSeleniumTest.getContentPane().add(chckbxDebugMode);

		progressBar = new JProgressBar();
		progressBar.setBounds(6, 64, 418, 31);
		frmRrrSeleniumTest.getContentPane().add(progressBar);
	}
}

class ProgressBarUpdator implements java.lang.Runnable {

	/**
	 * Progress bar that shows the current status
	 */
	private javax.swing.JProgressBar jpb = null;
	/**
	 * Progress bar value
	 */
	private java.lang.Integer value = null;

	/**
	 * Constructor
	 * 
	 * @param jpb
	 *            The progress bar this has to update
	 */
	public ProgressBarUpdator(javax.swing.JProgressBar jpb) {
		this.jpb = jpb;
		this.jpb.setMinimum(0);
		this.jpb.setMaximum(20);
	}

	/**
	 * Sets the value to the progress bar
	 * 
	 * @param value
	 *            Value to set
	 */

	/**
	 * Action of the thread will be executed here. The value of the progress bar
	 * will be set here.
	 */
	public void run() {
		for (int x = 0; x < 10000000; x++) {

			// this.jpb.setValue(2);

			for (int i = 2; i < 22; i++) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.jpb.setValue(i);
			}

		}
	}

}