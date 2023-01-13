import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmHangman extends JFrame {

	private JPanel contentPane;
	private JTextField txtLetter;
	private JTextField txtGuessed;
	private static String[] words = {"hat","great","primary","wound","field","as","hangman","worm","nervous","word","letter","game","bring","pride","credit","add","union","poetry","wrong","thanks"};
	private static String word = words[(int)(Math.random() * words.length)];
	private static String asterisk = new String(new char[word.length()]).replace("\0", "*");
	private static int count = 0;
	public String guess = " ";
	private JTextField txtCount;
	private JTextField txtAsterisk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHangman frame = new FrmHangman();
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
	public FrmHangman() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstruction = new JLabel("Please enter a letter:");
		lblInstruction.setBounds(10, 0, 424, 31);
		lblInstruction.setFont(new Font("Vivaldi", Font.BOLD, 25));
		contentPane.add(lblInstruction);
		
		txtLetter = new JTextField();
		txtLetter.setBounds(238, 9, 66, 20);
		contentPane.add(txtLetter);
		txtLetter.setColumns(10);
		
		
		
		txtGuessed = new JTextField();
		txtGuessed.setBounds(238, 230, 153, 20);
		contentPane.add(txtGuessed);
		txtGuessed.setColumns(10);
		
	
		
		JLabel lblGuessed = new JLabel("Guessed Letters");
		lblGuessed.setFont(new Font("Vivaldi", Font.BOLD, 25));
		lblGuessed.setBounds(10, 230, 163, 20);
		contentPane.add(lblGuessed);

		PictureBox picHangman = new PictureBox("images","hangman1.jpg");
		picHangman.setBounds(122, 42, 168, 102);
		contentPane.add(picHangman);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String letter;
				letter = txtLetter.getText();
				Check(letter);
				guess = txtGuessed.getText();
				guess = guess +" " + letter.charAt(0);
				txtGuessed.setText(guess);
				String time = Integer.toString(count);
				txtCount.setText(time);
				txtAsterisk.setText(asterisk);
				if (count == 1) {
					picHangman.setImage("images","hangman2.jpg");
				}
				else if (count == 2) {
					picHangman.setImage("images","hangman3.jpg");
				}
				else if(count == 3) {
					picHangman.setImage("images","hangman4.jpg");
				}
				else if (count == 4) {
					picHangman.setImage("images","hangman5.jpg");
				}
				else if (count == 5) {
					picHangman.setImage("images","hangman6.jpg");
				}
				else if (count == 6) {
					picHangman.setImage("images","hangman7.jpg");
					JOptionPane.showMessageDialog(null, "Game Over! The Word is "+word, "Game Over" , JOptionPane.CLOSED_OPTION);
					System.exit(0);
				}
				if(asterisk.equals(word)) {
					JOptionPane.showMessageDialog(null, "Great! The word is "+ word, "Win", JOptionPane.DEFAULT_OPTION);
					System.exit(0);
				}
			}
		});
		
		
		
		btnCheck.setFont(new Font("Vivaldi", Font.BOLD, 20));
		btnCheck.setBounds(335, 7, 89, 23);
		contentPane.add(btnCheck);
		
		JLabel lblCount = new JLabel("Wrong Guess:");
		lblCount.setFont(new Font("Vivaldi", Font.BOLD, 25));
		lblCount.setBounds(10, 177, 198, 59);
		contentPane.add(lblCount);
		
		txtCount = new JTextField();
		txtCount.setBounds(238, 199, 66, 20);
		contentPane.add(txtCount);
		txtCount.setColumns(10);
		txtCount.setText("0");
		
		txtAsterisk = new JTextField();
		txtAsterisk.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 25));
		txtAsterisk.setBounds(43, 150, 331, 31);
		contentPane.add(txtAsterisk);
		txtAsterisk.setColumns(10);
		txtAsterisk.setText(asterisk);
		
	}
	
	public void Check(String letter) {
		String newasterisk = "";
		for(int i = 0; i < word.length(); i++) {
		if(word.charAt(i) == letter.toLowerCase().charAt(0)) {
		newasterisk += letter.toLowerCase().charAt(0);
		
	}
		else if(asterisk.charAt(i) != '*') {
			newasterisk += word.charAt(i);
			
		}
		else{
			newasterisk += "*";
		}
	}
		
		if (asterisk.equals(newasterisk)){
			count ++;
			JOptionPane.showMessageDialog(null, "woops " + letter.toLowerCase().charAt(0) + " is not in this word","Wrong guess", JOptionPane.ERROR_MESSAGE);
			
		}
		else {
			asterisk = newasterisk;
			JOptionPane.showMessageDialog(null, "Nice " + letter.toLowerCase().charAt(0) + " is in this word","Right guess" , JOptionPane.INFORMATION_MESSAGE);
		}
		txtLetter.setText("");
		}
}