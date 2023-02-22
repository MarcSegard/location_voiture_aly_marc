package vue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;

import controller.UserDao;
import controller.VehiculeDao;
import model.Vehicule;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.DateFormat;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ShowLocation extends JDialog {
	private VehiculeDao vehiculeDao = new VehiculeDao();

	private static final long serialVersionUID = 1L;
	private JTextField inputTitulaire;
	private JTextField inputNumero;
	private JTextField textField;
	private JTextField textField_1;
	DateFormat formatD = DateFormat.getDateInstance(DateFormat.FULL, new Locale("fr", "FR"));

	/**
	 * Create the dialog.
	 */
	public ShowLocation(Vehicule vehicule, int nbreJours, Date start, Date end, String montant) {
		setResizable(false);
		setBounds(100, 100, 519, 307);
		setTitle("Synthèse location " + UserDao.currentUser.getNom() + " " + UserDao.currentUser.getPrenom());
		getContentPane().setLayout(null);

		JLabel lblInfoVoiture = new JLabel(vehicule.getMarque() + " " + vehicule.getModele_vehicule());
		lblInfoVoiture.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoVoiture.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblInfoVoiture.setBounds(6, 19, 390, 52);
		getContentPane().add(lblInfoVoiture);

		String startFR = formatD.format(start);
		String endFR = formatD.format(end);

		JLabel lblInfoDateStart = new JLabel("Du " + startFR);
		lblInfoDateStart.setBounds(16, 97, 266, 16);
		getContentPane().add(lblInfoDateStart);

		JLabel lblInfoDateEnd = new JLabel(" au " + endFR + " (" + String.valueOf(nbreJours) + " jours)");
		lblInfoDateEnd.setBounds(12, 118, 281, 16);
		getContentPane().add(lblInfoDateEnd);

		JLabel lblNewLabel = new JLabel("Montant total : " + montant + "€");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(6, 135, 390, 65);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Confirmer la location");

		btnNewButton.setBounds(177, 247, 196, 29);
		getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon(new ImageIcon("src/" + vehicule.getChemin_image()).getImage()
				.getScaledInstance(170, 100, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel_1.setBounds(313, 52, 200, 100);
		getContentPane().add(lblNewLabel_1);

		ArrayList<String> paiements = vehiculeDao.getMoyenPaiement();
		String[] paiementsToCombo = paiements.toArray(new String[paiements.size()]);
		JComboBox comboBoxTypePaiement = new JComboBox(paiementsToCombo);

		comboBoxTypePaiement.setBounds(167, 212, 97, 27);
		getContentPane().add(comboBoxTypePaiement);

		JLabel lblNewLabel_2 = new JLabel("Moyen de paiement :");
		lblNewLabel_2.setBounds(16, 216, 162, 16);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Titulaire : ");
		lblNewLabel_3.setBounds(32, 283, 64, 16);
		getContentPane().add(lblNewLabel_3);

		inputTitulaire = new JTextField();
		inputTitulaire.setBounds(131, 278, 369, 26);
		getContentPane().add(inputTitulaire);
		inputTitulaire.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Numero carte :");
		lblNewLabel_4.setBounds(32, 311, 97, 16);
		getContentPane().add(lblNewLabel_4);

		inputNumero = new JTextField();
		inputNumero.setBounds(131, 306, 369, 26);
		getContentPane().add(inputNumero);
		inputNumero.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Date Exp. :");
		lblNewLabel_5.setBounds(32, 339, 76, 16);
		getContentPane().add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(131, 334, 58, 26);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("CVV:");
		lblNewLabel_6.setBounds(238, 339, 42, 16);
		getContentPane().add(lblNewLabel_6);

		textField_1 = new JTextField();
		textField_1.setBounds(282, 334, 69, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		comboBoxTypePaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxTypePaiement.getSelectedItem().toString().equals("VISA")
						|| comboBoxTypePaiement.getSelectedItem().toString().equals("MASTER CARD")) {
					btnNewButton.setBounds(167, 372, 196, 29);
					setBounds(100, 100, 519, 450);
				} else if (comboBoxTypePaiement.getSelectedItem().toString().equals("paypal")) {
					btnNewButton.setBounds(156, 243, 196, 29);
					setBounds(100, 100, 519, 303);
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vehiculeDao.createLocation(vehicule, UserDao.currentUser.getId(),
						comboBoxTypePaiement.getSelectedItem().toString(), Float.parseFloat(montant.replace(",", ".")),
						start, end)) {
					System.out.println("Insertion facture Ok");
					sendEmail(UserDao.currentUser.getEmail(), vehicule, nbreJours, start, end, montant);
					dispose();
				} else {
					System.out.println("Insertion Nok !!!");
					JOptionPane.showMessageDialog(null, "Oups!! Erreur la location n'est pas effective.");
				}

			}
		});

		System.out.println("id_client : " + UserDao.currentUser.getId());
		System.out.println("id_vehicule : " + vehicule.getId());
	}

	private void sendEmail(String email, Vehicule vehicule, int nbreJours, Date start, Date end, String montant) {

		System.out.println("email : " + email);

		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		String from = "segardmarc@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.free.fr";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("segardmarc", "oscjava"); // mettre son mot de passe

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Confirmation réservation");

			// Now set the actual message
			message.setText("Votre véhicule  " + vehicule.getMarque() + " " + vehicule.getModele_vehicule() + " a bien été réservé du " + formatD.format(start) + " au " + formatD.format(end) +
					" ("+nbreJours+ " jours) pour un montant de " + montant + "€\n\nL'agence AVIS vous remercie de votre confiance");


			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
