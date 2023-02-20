package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserDao;

public class ModificationSuppressionUser extends JFrame {
	private UserDao userDao = new UserDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email_user;
	private JTextField password_user;

	/**
	 * Create the frame.
	 */
	public ModificationSuppressionUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("Modification/Suppression Utilisateur");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 10, 414, 17);
		contentPane.add(titleLabel);

		JLabel nomLabel = new JLabel("Nom :");
		nomLabel.setBounds(10, 57, 80, 14);
		contentPane.add(nomLabel);

		JLabel nomValueLabel = new JLabel( UserDao.currentUser.getNom());
		nomValueLabel.setForeground(Color.BLUE);
		nomValueLabel.setBounds(100, 57, 200, 14);
		contentPane.add(nomValueLabel);

		JLabel prenomLabel = new JLabel("Prénom :");
		prenomLabel.setBounds(10, 81, 80, 14);
		contentPane.add(prenomLabel);

		JLabel prenomValueLabel = new JLabel( UserDao.currentUser.getPrenom());
		prenomValueLabel.setForeground(Color.BLUE);
		prenomValueLabel.setBounds(100, 81, 200, 14);
		contentPane.add(prenomValueLabel);

		JLabel emailLabel = new JLabel("Email :");
		emailLabel.setBounds(10, 105, 80, 14);
		contentPane.add(emailLabel);

		email_user = new JTextField( UserDao.currentUser.getEmail());
		email_user.setToolTipText("<html>" + "A remplir obligatoirement  meme si vous  ne modifier pas l'email " + "</html>");
		email_user.setBounds(100, 103, 200, 20);
		contentPane.add(email_user);

		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setBounds(10, 141, 80, 14);
		contentPane.add(passwordLabel);

		password_user = new JPasswordField( UserDao.currentUser.getPassword());
		password_user.setBounds(100, 139, 200, 20);
		password_user.setToolTipText("<html> A  remplire obligatoirement  meme si  vous ne modifier pas le mot de passe </html>");
		contentPane.add(password_user);

		JLabel permisLabel = new JLabel("Numéro de permis :");
		permisLabel.setBounds(10, 177, 120, 14);
		contentPane.add(permisLabel);

		JLabel permisValueLabel = new JLabel( UserDao.currentUser.getPermis());
		permisValueLabel.setForeground(Color.BLUE);
		permisValueLabel.setBounds(140, 177, 200, 14);
		contentPane.add(permisValueLabel);

		JButton modifierButton = new JButton("Modifier");
		JButton supprimerButton = new JButton("Supprimer");
		modifierButton.setBounds(100, 229, 101, 20);
		supprimerButton.setBounds(228, 229, 101, 20);
		contentPane.add(modifierButton);
		contentPane.add(supprimerButton);

		modifierButton.addActionListener(new ActionListener() {
			Pattern patternEmail = Pattern.compile(
					"^[A-Za-z0-9][A-Za-z0-9.-_]+[A-Za-z0-9][@][A-Za-z0-9][A-Za-z0-9.-_]+[A-Za-z0-9]?[.][A-Za-z0-9]{2,3}$");

			String newEmail;
			String newPassword;
			Matcher matcherEmail;

			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier vos informations ?");
				newEmail = email_user.getText();
				newPassword = String.valueOf(password_user.getText());
				matcherEmail = patternEmail.matcher(newEmail);
				
				// Si l'utilisateur clique sur "oui"
				if (choice == JOptionPane.YES_OPTION) {
					if (!matcherEmail.find()) {
						JOptionPane.showMessageDialog(null, "Vérifier le format de votre email", "Oups",
								JOptionPane.ERROR_MESSAGE);
					} else if (userDao.checkEmailIsExist(newEmail) && ! UserDao.currentUser.getEmail().equals(newEmail)) {
						JOptionPane.showMessageDialog(null, "Votre mail existe déjà, veuillez en choisir un autre.",
								"Oups", JOptionPane.ERROR_MESSAGE);
					} else if (newEmail.contains("agence.fr")) {
						JOptionPane.showMessageDialog(null, "Bien tenté !!!!", "Halte aux pirates",
								JOptionPane.ERROR_MESSAGE);

					} else if (email_user.getText().length() != 0 || password_user.getText().length() != 0) {
						// Mettre à jour l'utilisateur avec les nouvelles valeurs
						System.out.println("Update OK");
						 UserDao.currentUser.setEmail(newEmail);
						 UserDao.currentUser.setPassword(newPassword);
						// Appeler la méthode update de UserDao pour mettre à jour l'utilisateur
						// userDao.update(user);
						if (userDao.update( UserDao.currentUser)) {
							JOptionPane.showMessageDialog(null, "L'utilisateur a bien été modifier", "Bravo",
									JOptionPane.INFORMATION_MESSAGE);
							// Fermer la fenêtre
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Cà c'est mal passé", "Oups",
									JOptionPane.ERROR_MESSAGE);
						}

					}

				}
			}
		});
		supprimerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Demander confirmation à l'utilisateur avant de supprimer l'utilisateur
				int confirmation = JOptionPane.showConfirmDialog(ModificationSuppressionUser.this,
						"Êtes-vous sûr de vouloir supprimer cet utilisateur ?", "Confirmation de suppression",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (confirmation == JOptionPane.YES_OPTION) {
					// Appeler la méthode delete de UserDao pour supprimer l'utilisateur courant
					userDao.delete( UserDao.currentUser);

					// Fermer la fenêtre
					dispose();
				}
			}
		});
		// principale
		getContentPane();
		setVisible(true); // ma fenêtre visible
	}

}
