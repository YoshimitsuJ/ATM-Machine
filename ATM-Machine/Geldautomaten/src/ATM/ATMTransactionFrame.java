package ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.Timer;

/**
 * ATMTransactionFrame
 */
public class ATMTransactionFrame extends JFrame implements ActionListener {

	private static final int WAIT_DURATION = 5;
	private static final String ACTION_DEPOSIT_MONEY = "DepositMoney";
	private static final String ACTION_WITHDRAW_MONEY = "WithdrawMoney";
	private static final String TEXT_DEL = "DEL";
	private static final String ATM_TRANSACTION_TITLE = "ATM Transaction";
	private static final long serialVersionUID = -6823567321884375313L;
	private static final int WIDTH_BUTTON = 60;
	private static final int LEFT_X_BORDER = 730;
	static final int COLUMN_MAX = 3;

	private String currentValue = "";
	private String currentAction = null;
	private JList<String> displayList = new JList<>();
	private DefaultListModel<String> displayListModel = new DefaultListModel<>();
	private int balance = 5000, withdraw, deposit;

	public static void main(String args[]) {

		new ATMTransactionFrame();
	}

	public ATMTransactionFrame() {

		setTitle(ATM_TRANSACTION_TITLE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		setLayout(null);
		setVisible(true);

		int breakCount = 1;
		int x = LEFT_X_BORDER;
		int y = WIDTH_BUTTON;

		for (int i = 9; i > -1; i--) {

			JButton button = new JButton(String.valueOf(i));
			button.setBounds(x, y, WIDTH_BUTTON, WIDTH_BUTTON);

			x -= WIDTH_BUTTON + 5;
			if (breakCount >= 3) {
				breakCount = 0;
				x = LEFT_X_BORDER;
				y += WIDTH_BUTTON + 5;
			}

			breakCount++;

			add(button);

			final int value = i;
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					currentValue += value;
					showCurrentValue();
					System.out.println(currentValue);
				}

			});
		}

		JButton delButton = new JButton(TEXT_DEL);
		delButton.setBounds(x, y, WIDTH_BUTTON, WIDTH_BUTTON);
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentValue.length() > 0) {
					currentValue = currentValue.substring(0, currentValue.length() - 1);
					System.out.println(currentValue);
					showCurrentValue();
				}
			}
		});
		add(delButton);

		JButton enterButton = new JButton("<html><body>E<br>N<br>T<br>E<br>R");
		enterButton.setBounds(LEFT_X_BORDER + WIDTH_BUTTON + 5, WIDTH_BUTTON, WIDTH_BUTTON, (WIDTH_BUTTON + 5) * 4 - 5);
		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (ACTION_WITHDRAW_MONEY.equals(currentAction)) {

					withdraw = Integer.parseInt(currentValue);

					if (balance >= withdraw) {
						displayText("Output " + withdraw);
						balance = balance - withdraw;
						displayText("New balance is " + balance);
						currentAction = null;
						currentValue = "";
						displayText("Please save your Money");

						waitSeconds(WAIT_DURATION);

					} else {
						System.out.println("Not enough money saved");
					}
					System.out.println("");
				}

				if (ACTION_DEPOSIT_MONEY.equals(currentAction)) {

					deposit = Integer.parseInt(currentValue);

					if (balance >= deposit) {
						displayText("Input " + deposit);
						balance = balance + deposit;
						displayText("New balance is " + balance);
						currentAction = null;
						currentValue = "";
						displayText("Thank you for depositing");

						waitSeconds(WAIT_DURATION);
					}
					System.out.println("");
				}
			}

		});
		add(enterButton);

		Font font = new Font("Courier New", Font.PLAIN, 14);
		displayList.setFont(font);
		displayList.setBackground(new Color(30, 30, 50));
		displayList.setForeground(new Color(200, 200, 200));
		displayList.setModel(displayListModel);
		displayList.setBounds(100, WIDTH_BUTTON, 400, 350);
		add(displayList);

		addOptionButton("OPTION1", 60, 160, 30, 30);
		addOptionButton("OPTION2", 510, 160, 30, 30);
		addOptionButton("OPTION3", 60, 200, 30, 30);
		addOptionButton("OPTION4", 510, 200, 30, 30);

		showMenuOption();

		validate();
		repaint();
	}

	/**
	 * Add an option button to the surface
	 * 
	 * @param actionCommand
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	private void addOptionButton(String actionCommand, int x, int y, int width, int height) {
		JButton option = new JButton();
		option.setActionCommand(actionCommand);
		option.setBounds(x, y, width, height);
		option.addActionListener(this);
		add(option);
	}

	private void showCurrentValue() {
		displayListModel.add(7, currentValue);

		while (displayListModel.getSize() > 8) {
			displayListModel.remove(8);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

			case "OPTION1":
				showWithdrawScreen();
				break;

			case "OPTION2":
				showDepositScreen();
				break;

			case "OPTION3":
				showBalanceScreen();
				break;

			case "OPTION4":
				System.exit(0);

			default:
				System.err.println("not assigned command");
				break;
		}
	}

	private void showBalanceScreen() {
		newScreen();
		displayText("Your balance is: ");
		displayText("-------------------------------");
		displayText(" " + balance);

		waitSeconds(WAIT_DURATION);
	}

	private void showDepositScreen() {
		currentAction = ACTION_DEPOSIT_MONEY;

		newScreen();
		displayText("Current balance: " + balance);
		displayText("-------------------------------");
		displayText("");
		displayText("Enter money to be deposited: ");
		displayText("-------------------------------");
		displayText("");
	}

	private void showWithdrawScreen() {
		currentAction = ACTION_WITHDRAW_MONEY;
		System.out.print("Enter money to be withdraw: ");

		newScreen();
		displayText("Current balance: " + balance);
		displayText("-------------------------------");
		displayText("Type the amount to withdraw");
		displayText("");
	}

	private void showMenuOption() {

		newScreen();

		displayText("Choose the operation you want to operate");
		displayText(" ");
		displayText("Withdraw                            Deposit");
		displayText(" ");
		displayText(" ");
		displayText("Check Balance                          Exit");
		displayText("");
	}

	private void newScreen() {
		clearDisplay();
		displayText("-------------------------------");
		displayText("Welcome to ATM Transactions LTD");
		displayText("-------------------------------");
		displayText("-------------------------------");
	}

	/**
	 * Display text on console screen
	 * 
	 * @param text
	 */
	private void displayText(String text) {
		displayListModel.addElement(text);
	}

	/**
	 * Clear console display
	 */
	private void clearDisplay() {
		displayListModel.clear();
	}

	private void waitSeconds(int delaySeconds) {

		Timer timer = new Timer(delaySeconds * 1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showMenuOption();
			}
		});

		timer.setRepeats(false);
		timer.start();
	}
}
