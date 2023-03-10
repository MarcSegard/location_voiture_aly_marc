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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

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
		setTitle("Synth??se location " + UserDao.currentUser.getNom() + " " + UserDao.currentUser.getPrenom());
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

		JLabel lblNewLabel = new JLabel("Montant total : " + montant + "???");
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
					String cheminVerPDF = createPDf(vehicule, nbreJours, start, end, montant);
					sendEmail(UserDao.currentUser.getEmail(), cheminVerPDF);
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

	private String createPDf(Vehicule vehicule, int nbreJours, Date start, Date end, String montant) {
		com.itextpdf.text.Font fontHeader = FontFactory.getFont(FontFactory.COURIER, 24, BaseColor.BLACK);
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		
		String cheminVersPDF = "./Facture.pdf";
		
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(cheminVersPDF ));
			document.open();
			Paragraph p1 = new Paragraph();
			p1.add("Bonjour M. " + UserDao.currentUser.getNom());
			p1.setFont(fontHeader);
			document.add(p1);
			Paragraph p2 = new Paragraph();
			p2.add("Votre v??hicule :  " + vehicule.getMarque() + " " + vehicule.getModele_vehicule());
			p2.setFont(font);
			document.add(p2);
			Paragraph p3 = new Paragraph();
			p3.add("a ??t?? r??serv?? ");
			p3.setFont(font);
			document.add(p3);
			Paragraph p4 = new Paragraph();
			p4.add("du :  " + formatD.format(start) + " au " + formatD.format(end) + "("+nbreJours+" jours)");
			p4.setFont(font);
			document.add(p4);
			Paragraph p5 = new Paragraph();
			p5.add("pour un montant de "+ montant+ "???");
			p5.setFont(fontHeader);
			document.add(p5);
			Paragraph p6 = new Paragraph();
			p6.add("L'agence AVIS vous remercie pour votre confiance");
			p6.setFont(fontHeader);
			document.add(p6);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cheminVersPDF;

	}

	private void sendEmail(String email, String cheminVersPDF) {

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

				return new PasswordAuthentication("segardmarc", "Fabquetho1234!"); // mettre son mot de passe

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
			message.setSubject("Confirmation r??servation");
			
            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();
            
            try {

                File f =new File(cheminVersPDF);

                attachmentPart.attachFile(f);
                textPart.setText("Votre v??hicule   a bien ??t?? r??serv??\n\nL'agence AVIS vous remercie de votre confiance");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }
            
            message.setContent(multipart);

			// Now set the actual message

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
