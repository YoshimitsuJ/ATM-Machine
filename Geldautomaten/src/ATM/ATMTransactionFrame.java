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

public class ATMTransactionFrame extends JFrame {

	private static final long serialVersionUID = -6823567321884375313L;
	private static final int WIDTH_BUTTON = 60;
	private static final int LEFT_X_BORDER = 730;
	static final int COLUMN_MAX = 3;

	private String currentValue = "";
	private String currentAction = null;
	private JList list = new JList();
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private int balance = 5000, withdraw, deposit;

	public static void main(String args[]) {

		new ATMTransactionFrame();
	}

	public ATMTransactionFrame() {

		setTitle("ATM Transaction");
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

		JButton delButton = new JButton("DEL");
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
				if ("WithdrawMoney".equals(currentAction)) {
					
					withdraw = Integer.parseInt(currentValue);
					
					if (balance >= withdraw) {
						listModel.addElement("Output " + withdraw);
						balance = balance - withdraw;
						listModel.addElement("New balance is " + balance);
						currentAction = null;
						currentValue = "";
						listModel.addElement("Please save your Money");
						
						Timer timer = new Timer(5000, new ActionListener() {

						    @Override
						    public void actionPerformed(ActionEvent arg0) {            
						    	showMenuOption();
						    }
						});
						
						timer.setRepeats(false);
						timer.start();
						
					} else {
						System.out.println("Not enough money saved");
					}
					System.out.println("");
				}
			}
		});
		add(enterButton);
		
		Font font = new Font("Courier New", Font.PLAIN, 14);
		list.setFont(font);
		list.setBackground(new Color(30,30,50));
		list.setForeground(new Color(200,200,200));
		list.setModel(listModel);
		list.setBounds(100,WIDTH_BUTTON, 400,350);
		add(list);
		
		JButton option1 = new JButton();
		option1.setActionCommand("OPTION1");
		option1.setBounds(60,160,30,30);
		option1.addActionListener(optionActions);
		add(option1);
		
		JButton option2 = new JButton();
		option2.setActionCommand("OPTION2");
		option2.setBounds(510,160,30,30);
		option2.addActionListener(optionActions);
		add(option2);
		
		JButton option3 = new JButton();
		option3.setActionCommand("OPTION3");
		option3.setBounds(60,200,30,30);
		option3.addActionListener(optionActions);
		add(option3);
		
		JButton option4 = new JButton();
		option4.setActionCommand("OPTION4");
		option4.setBounds(510,200,30,30);
		option4.addActionListener(optionActions);
		add(option4);

		showMenuOption();

		validate();
		repaint();
	}
	
	private void showCurrentValue() {
		listModel.add(7, currentValue);
	
		while (listModel.getSize() > 8) {
			listModel.remove(8);
		}
	}
	
	private ActionListener optionActions = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand())

			{
			case "OPTION1":
				currentAction = "WithdrawMoney";
				System.out.print("Enter money to be withdraw: ");
				
				newScreen();
				listModel.addElement("Current balance: " + balance);
				listModel.addElement("-------------------------------");
				listModel.addElement("Type the amount to withdraw");
				listModel.addElement("");
				
				break;

			case "OPTION2":
				
				System.out.println("Enter money to be deposited: ");
				//deposit = s.nextInt();
				balance = balance + deposit;
				System.out.println("Your Money has been succesfully depsited");
				System.out.println("");
				break;
				
			case "OPTION3":
				System.out.println("Your balance is: " + balance);
				
				break;

			case "OPTION4":
				System.exit(0);
			}
			
		}
	};

	private void showMenuOption() {
		
		newScreen();
		
		listModel.addElement("Choose the operation you want to operate");
		listModel.addElement(" ");
		listModel.addElement("Withdraw                            Deposit");
		listModel.addElement(" ");
		listModel.addElement(" ");
		listModel.addElement("Check Balance                          Exit");
		listModel.addElement("");
	}

	private void newScreen() {
		listModel.clear();
		listModel.addElement("-------------------------------");
		listModel.addElement("Welcome to ATM Transactions LTD");
		listModel.addElement("-------------------------------");
		listModel.addElement("-------------------------------");
	}

	public void logic() {

		int balance = 5000, withdraw, deposit;
		Scanner s = new Scanner(System.in);
		while (true) {
			
			int n = s.nextInt();
			switch (n)

			{
			case 1:
				System.out.print("Enter money to be withdraw: ");
				withdraw = s.nextInt();
				if (balance >= withdraw) {
					balance = balance - withdraw;
					System.out.println("Please save your Money");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Not enough money saved");
				}
				System.out.println("");
				break;

			case 2:
				System.out.println("Enter money to be deposited: ");
				deposit = s.nextInt();
				balance = balance + deposit;
				System.out.println("Your Money has been succesfully depsited");
				System.out.println("");
				break;

			case 4:
				s.close();
				System.exit(0);
			}

		}

	}
}
