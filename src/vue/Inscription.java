package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserDao;
import model.User;

//import controller.UserDao;
//import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Inscription extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputName;
	private JTextField inputPrenom;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	private JTextField inputEmailConnection;
	private JPasswordField inputPasswordConnection;
	private JTextField inputPermis;

	/**
	 * Create the frame.
	 */
	public Inscription() {
		// UserDao uc1 = new UserDao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 394, 544);
		contentPane.add(panel);
		panel.setLayout(null);

		UserDao uc1 = new UserDao();

		JLabel lblNewLabel = new JLabel("Connection");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(6, 6, 382, 34);
		panel.add(lblNewLabel);

		JLabel lblEmail_1 = new JLabel("Email :");
		lblEmail_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail_1.setBounds(36, 113, 89, 16);
		panel.add(lblEmail_1);

		JLabel lblPassword_1 = new JLabel("Password :");
		lblPassword_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword_1.setBounds(36, 158, 89, 16);
		panel.add(lblPassword_1);

		inputEmailConnection = new JTextField();
		inputEmailConnection.setColumns(10);
		inputEmailConnection.setBounds(137, 108, 243, 26);
		panel.add(inputEmailConnection);

		inputPasswordConnection = new JPasswordField();
		inputPasswordConnection.setBounds(137, 153, 239, 26);
		panel.add(inputPasswordConnection);

		JButton btnConnection = new JButton("Connection");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userlogin = uc1.userLogin(inputEmailConnection.getText(),
						String.valueOf(inputPasswordConnection.getPassword()));
				if (userlogin != null) {
					JOptionPane.showMessageDialog(null, "Bonjour " + userlogin.getPrenom() + ", vous êtes connecté",
							"Super", JOptionPane.INFORMATION_MESSAGE);
					UserDao.currentUser = userlogin;
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Nous n'avez pas de compte", "Oups", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnConnection.setBounds(147, 214, 117, 29);
		panel.add(btnConnection);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(412, 6, 400, 544);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(22, 94, 89, 16);
		panel_1.add(lblNom);

		JLabel lblInscription = new JLabel("Inscription");
		lblInscription.setBounds(0, 6, 400, 42);
		lblInscription.setHorizontalAlignment(SwingConstants.CENTER);
		lblInscription.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 35));
		panel_1.add(lblInscription);

		JLabel lblPrenom = new JLabel("Prénom :");
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrenom.setBounds(22, 134, 89, 16);
		panel_1.add(lblPrenom);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(22, 173, 89, 16);
		panel_1.add(lblEmail);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(22, 218, 89, 16);
		panel_1.add(lblPassword);

		inputName = new JTextField();
		inputName.setBounds(119, 89, 243, 26);
		panel_1.add(inputName);
		inputName.setColumns(10);

		inputPrenom = new JTextField();
		inputPrenom.setColumns(10);
		inputPrenom.setBounds(119, 129, 243, 26);
		panel_1.add(inputPrenom);

		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(119, 168, 243, 26);
		panel_1.add(inputEmail);

		inputPassword = new JPasswordField();
		inputPassword.setBounds(123, 213, 239, 26);
		panel_1.add(inputPassword);

		JButton btnCreateUser = new JButton("Inscription");
		btnCreateUser.addActionListener(new ActionListener() {
			Pattern patternEmail = Pattern.compile(
					"^[A-Za-z0-9][A-Za-z0-9.-_]+[A-Za-z0-9][@][A-Za-z0-9][A-Za-z0-9.-_]+[A-Za-z0-9]?[.][A-Za-z0-9]{2,3}$");
			Pattern patternMdP = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");

			String name;
			String firstName;
			String email;
			String password;
			String permis;
			Matcher matcherEmail;
			Matcher matcherMdP;

			public void actionPerformed(ActionEvent e) {
				name = inputName.getText();
				firstName = inputPrenom.getText();
				email = inputEmail.getText();
				password = String.valueOf(inputPassword.getPassword());
				permis = inputPermis.getText();
				matcherEmail = patternEmail.matcher(email);
				matcherMdP = patternMdP.matcher(password);

				if (!matcherEmail.find()) {
					JOptionPane.showMessageDialog(null, "Vérifier le format de votre email", "Oups",
							JOptionPane.ERROR_MESSAGE);
				} else if (uc1.checkEmailIsExist(email)) {
					JOptionPane.showMessageDialog(null, "Votre mail existe déjà, veuillez en choisir un autre.", "Oups",
							JOptionPane.ERROR_MESSAGE);
				} else if (!matcherMdP.find()) {
					JOptionPane.showMessageDialog(null,
							"Votre mot de passe est trop faible. \n Il doit contenir au moins :\n\t - une lettre minuscule\n\t - une lettre majuscule\n\t - un chiffre\n\t - un caractère spécial (#?!@$%^&*-)",
							"Oups", JOptionPane.ERROR_MESSAGE);
				} else if (name.length() == 0 || firstName.length() == 0 || email.length() == 0
						|| inputPassword.getPassword().length == 0 || permis.length() == 0) {
					JOptionPane.showMessageDialog(null, "Aucun champ ne doit pas être vide", "Oups",
							JOptionPane.ERROR_MESSAGE);
				} else {
					User user1 = new User(name, firstName, email, permis, password);
					if (uc1.create(user1)) {
						JOptionPane.showMessageDialog(null, "L'utilisateur a bien été créé", "Bravo",
								JOptionPane.INFORMATION_MESSAGE);
						UserDao.currentUser = user1;
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Cà c'est mal passé", "Oups", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnCreateUser.setBounds(141, 295, 117, 29);
		panel_1.add(btnCreateUser);

		inputPermis = new JTextField();
		inputPermis.setColumns(10);
		inputPermis.setBounds(119, 257, 243, 26);
		panel_1.add(inputPermis);

		JLabel lblNPermis = new JLabel("N° permis :");
		lblNPermis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNPermis.setBounds(22, 262, 89, 16);
		panel_1.add(lblNPermis);
	}
}
