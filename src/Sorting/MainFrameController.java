package Sorting;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class MainFrameController extends JFrame {
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu fileMenu1;
	private JMenu fileMenu2;
	private JMenu fileMenu3;
	private JMenu fileMenu4;
	private JMenuItem newItem;
	private JMenuItem openItem;//
	private JMenuItem saveItem;
	private JMenuItem exitItem;
	private JMenuItem selectionS;
	private JMenuItem bubbleS;//
	private JMenuItem mergeS;
	private JMenuItem quickS;
	private JMenuItem homeButton;
	private JMenuItem helpButton;
	private JFileChooser fc = new JFileChooser(); // this let u select a file
													// fron your file system
	BubbleSortingPanel bubblePanel;
	MergeSortingPanel mergePanel;
	QuickSortingPanel quickPanel;
	SelectionSortingPanel selectionPanel;
	MainFrameController homePanel;

	public MainFrameController() {
		super("Sorting Algrithms");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		
		setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		homeButton = new JMenuItem("Home");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == homeButton) {
					homePanel = new MainFrameController();
					getContentPane().setBackground(Color.PINK);
					getContentPane().removeAll();
					repaint();
					setVisible(true);
				}
			}
		});
			
		newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openFile();
			}
		});

		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveFile();
			}
		});

		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0); // this is to exit the
			}
		});

		helpButton = new JMenuItem("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.exit(0);
			}
		});
		
		
		mergeS = new JMenuItem("Merge Sorting");
		mergeS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mergeS) {
					mergePanel = new MergeSortingPanel();
					getContentPane().removeAll();
					
					setTitle("Merge Sort");
					getContentPane().add(mergePanel.toolBar, "North");
					getContentPane().add(mergePanel, "Center");
					revalidate();
					repaint();
					setVisible(true);
				}
			}
		});

		quickS = new JMenuItem("Quick Sorting");
		quickS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == quickS) {
					quickPanel = new QuickSortingPanel();
					getContentPane().removeAll();
					
					setTitle("Quick Sort");
					getContentPane().add(quickPanel.toolBar, "North");
					getContentPane().add(quickPanel, "Center");
					revalidate();
					repaint();
					setVisible(true);
				}
			}
		});

		bubbleS = new JMenuItem("Bubble Sorting");
		bubbleS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bubbleS) {
					bubblePanel = new BubbleSortingPanel();
					getContentPane().removeAll();
					
					setTitle("Bubble Sort");
					getContentPane().add(bubblePanel.toolBar, "North");
					getContentPane().add(bubblePanel, "Center");
					revalidate();
					repaint();
					setVisible(true);

				}
			}
		});

		selectionS = new JMenuItem("Selection Sorting");
		selectionS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == selectionS) {
					selectionPanel = new SelectionSortingPanel();
					getContentPane().removeAll();
				
					setTitle("Selection Sort");
					getContentPane().add(selectionPanel.toolBar, "North");
					getContentPane().add(selectionPanel, "Center");
					revalidate();
					repaint();
					setVisible(true);
				}
			}
		});

		// file menu --adding each menu item to the menu
		fileMenu3 = new JMenu("Home");
		fileMenu = new JMenu("File");
		fileMenu2 = new JMenu("Select Sorting Options");
		fileMenu4 = new JMenu("Help");
		fileMenu1 = new JMenu("Exit");
		menuBar.add(fileMenu3);
		menuBar.add(fileMenu);
		menuBar.add(fileMenu2);
		menuBar.add(fileMenu4);
		menuBar.add(fileMenu1);
		fileMenu3.add(homeButton);
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu1.add(exitItem);
		fileMenu2.add(bubbleS);
		fileMenu2.add(quickS);
		fileMenu2.add(mergeS);
		fileMenu2.add(selectionS);
		fileMenu4.add(helpButton);

		
			}
		});
		

	}

	public void newFile() {
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File myFile = fc.getSelectedFile();
			try {
				// load file windows
				super.setTitle("Open " + myFile.getAbsolutePath());
			} catch (Exception nfe) {
				super.setTitle("An error occurred during opening");
			}
			super.setTitle("Open " + myFile.getAbsolutePath());
		} else {
			super.setTitle("Cancel file open");
		}
	}

	public void openFile() {
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File myFile = fc.getSelectedFile();
			try {
				// load file windows
				super.setTitle("Open " + myFile.getAbsolutePath());
			} catch (Exception nfe) {
				super.setTitle("An error occurred during opening");
			}
			super.setTitle("Open " + myFile.getAbsolutePath());
		} else {
			super.setTitle("Cancel file open");
		}
	}

	public void saveFile() {
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File myFile = fc.getSelectedFile();
			super.setTitle("Saved " + myFile.getAbsolutePath());
		} else {
			super.setTitle("Cancel file save");
		}
	}

}
