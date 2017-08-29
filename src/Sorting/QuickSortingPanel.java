package Sorting;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.StreamHandler;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class QuickSortingPanel extends JPanel {
	private static Random r = new Random(); // random number generator
	//private static Random r2 = new Random();// random colour generator
	private static int ARRAY_LENGTH = 1000; // the constant length of the
											// array
	private static final int LARGEST_VALUE = 100; // NULL;
	// this is the possible largest value in the array
	// ie it can be only >= 100
	private int[] array = new int[ARRAY_LENGTH];
	// declaring and initializing the array
	private boolean stopThread = false;

	JToolBar toolBar; // this is a swing tool bar
	/**
	 * stop and start button element on the GUI
	 */
	private JButton startButton;
	private JButton stopButton;
	private JLabel lbl;

	private JLabel lbl2;

	long elapsedTime = 0;

	public QuickSortingPanel() {
		/**
		 * labelling the button wit appropriate display names set the heading of
		 * the frame when it displays the event handling listener
		 */
		// super("Sorting GUI");
		setLayout(new FlowLayout());
		setBackground(Color.PINK);
		startButton = new JButton("Start Sorting");
		stopButton = new JButton("Stop Sorting");
		lbl = new JLabel(
				" The Time Performance Of A Sorting Algorithm As Array Size Increases ");

		lbl2 = new JLabel(" ");
		toolBar = new JToolBar();

		toolBar.add(startButton);
		toolBar.add(stopButton);
		add(lbl);

		add(lbl2);
		// calling event handler
		startActionHandler start = new startActionHandler();
		stopActionHandler stop = new stopActionHandler();
		startButton.addActionListener(start);
		stopButton.addActionListener(stop);

	}

	public class stopActionHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			stopThread = true;
		}

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
								sort(array);
								elapsedTime = calElapsedTime();
								for (int i = 0; i < array.length; i++) {
									System.out.print(array[i] + " ");
								}
								if (elapsedTime != 0) {
									lbl2.setText("TimeTaken to sort array : "
											+ elapsedTime + "ms");
								} else {
									lbl2.setText(" ");
								}
							} catch (Exception e) {
							}
							stopButton.setEnabled(false);
							startButton.setEnabled(true);
						}

					}).start();
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

	public void sort(int[] values) throws InterruptedException {
		// check for empty or null array
		if (values == null || values.length == 0) {
			return;
		}
		this.array = values;
		ARRAY_LENGTH = values.length;
		quicksort(0, ARRAY_LENGTH - 1);
		theDelay();// checking for a thread stop
	}

	private void quicksort(int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = array[low + (high - low) / 2];

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (array[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (array[j] > pivot) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);

	}

	private void exchange(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font = new Font("Arial", Font.BOLD, 12);
		g.setFont(font);
		int offSet = 200;
		Color c1 = new Color(156, 0, 99);
		g.setColor(c1);
		for (int i = 0; i < array.length; i++) {
			g.drawLine((i + offSet), LARGEST_VALUE + offSet, i + offSet,
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
		sort(array);
		long endTime = System.currentTimeMillis(); // gets the time the sorting
													// stops
		elapsedTime = endTime - startTime;

		return elapsedTime;

	}
}
