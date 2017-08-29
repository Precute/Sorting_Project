package Sorting;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class SelectionSortingPanel extends JPanel {// this is the constructor
													// for the sort
	// Panel

	private static Random r = new Random(); // random generator
	private static final int ARRAY_LENGTH = 400; // the constant length of the
	// array
	private static final int LARGEST_VALUE = 100; // NULL;
	// this is the possible largest value in the array
	// ie it can be only >= 100
	private final int[] array = new int[ARRAY_LENGTH];
	// declaring and initializing the array
	private boolean stopThread = false;

	JToolBar toolBar; // this is a swing tool bar
	/**
	 * stop and start button element on the GUI
	 */
	private JButton startButton;
	private JButton stopButton;
	private JLabel lbl;
	private JLabel lbl1;

	long elapsedTime = 0;

	public SelectionSortingPanel() {
		/**
		 * labelling the button wit appropriate display names set the heading of
		 * the frame when it displays the event handling listener
		 */
		// super("Sorting GUI");
		setLayout(new FlowLayout());
		setBackground(Color.PINK);
		// lbl = new JLabel ("Select your favourite Rainbow Colour");

		startButton = new JButton("Start Sorting");
		stopButton = new JButton("Stop Sorting");
		lbl = new JLabel(
				" The Time Performance Of A Sorting Algorithm As Array Size Increases ");
		lbl1 = new JLabel("");
		toolBar = new JToolBar();

		toolBar.add(startButton);
		toolBar.add(stopButton);
		add(lbl);
		add(lbl1);
		// calling event handler

		startActionHandler start = new startActionHandler();
		stopActionHandler stop = new stopActionHandler();
		startButton.addActionListener(start);
		stopButton.addActionListener(stop);

	}

	public class startActionHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Thread(new Runnable() {// a thread is a unit of executions
										// running in an assigned time
						public void run() {
							startButton.setEnabled(false);
							stopButton.setEnabled(true);
							fillArray(array);
							try {
								// long startTime =System.currentTimeMillis();
								// // get the time to sorting starts
								selectionSort(array);
								elapsedTime = calElapsedTime();
								// long endTime = System.currentTimeMillis();
								// //gets the time the sorting stops

							} catch (Exception e) {
							}
							if (elapsedTime != 0) {
								lbl1.setText("TimeTaken to sort array : "
										+ elapsedTime + "ms");
							} else {
								lbl1.setText(" ");
							}
							stopButton.setEnabled(false);
							startButton.setEnabled(true);
						}

					}).start();
		}
	}

	public class stopActionHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			stopThread = true;
		}

	}

	/**
	 * this fill array method basically generate random value in the array a
	 * ranging from 0 to the 100.
	 * 
	 * @param a
	 */
	private static void fillArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextInt(LARGEST_VALUE);
		}
	}

	public void selectionSort(int[] a) throws InterruptedException {
		// TODO Auto-generated method stub
		for (int i = 0; i < ARRAY_LENGTH - 1; i++) {

			int min = i;
			for (int j = i + 1; j < ARRAY_LENGTH; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			exchange(a, min, i);
			theDelay();
		}

	}

	private void exchange(int[] a, int i, int j) {
		// TODO Auto-generated method stub
		// a exchange method
		// it checks how the array ellement are arrange
		int tempStore = a[i];
		a[i] = a[j];
		a[j] = tempStore;
	}

	/**
	 * this is the paint component method to paint on the JPAnEL Using a draw
	 * line method it
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font = new Font("Arial", Font.BOLD, 12);
		g.setFont(font);
		int offSet = 200;
		Color c1 = new Color(156, 0, 99);
		g.setColor(c1);
		for (int i = 0; i < array.length; i++) {
			g.drawLine(i + offSet, LARGEST_VALUE + offSet, i + offSet,
					LARGEST_VALUE + offSet - array[i]);
		}
		// g.drawLine(PROPERTIES, arg1, arg2, arg3);

	}

	private void theDelay() throws InterruptedException {
		// TODO Auto-generated method stub
		repaint();
		Thread.sleep(1);
		if (stopThread) {
			stopThread = false;
			throw new RuntimeException();
		}
	}

	private long calElapsedTime() throws InterruptedException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		selectionSort(array);
		long endTime = System.currentTimeMillis(); // gets the time the sorting
													// stops
		elapsedTime = endTime - startTime;

		return elapsedTime;

	}

}
