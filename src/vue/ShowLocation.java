package vue;

import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;

import controller.UserDao;
import model.Vehicule;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ShowLocation extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public ShowLocation(Vehicule vehicule, int nbreJours, Date start, Date end, String montant) {
		setResizable(false);
		setBounds(100, 100, 413, 301);
		setTitle("Synthèse location " + UserDao.currentUser.getNom() + " " + UserDao.currentUser.getPrenom());
		getContentPane().setLayout(null);
		
		JLabel lblInfoVoiture = new JLabel(vehicule.getMarque() + " " + vehicule.getModele_vehicule());
		lblInfoVoiture.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoVoiture.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblInfoVoiture.setBounds(6, 19, 390, 52);
		getContentPane().add(lblInfoVoiture);
		
		DateFormat formatD= DateFormat.getDateInstance(DateFormat.FULL,
		                new Locale("fr","FR"));
		 
		 
		String startFR = formatD.format(start);
		String endFR = formatD.format(end);
		
		JLabel lblInfoDateStart = new JLabel("Du " + startFR);
		lblInfoDateStart.setBounds(16, 97, 380, 16);
		getContentPane().add(lblInfoDateStart);
		
		JLabel lblInfoDateEnd = new JLabel(" au " + endFR + " (" + String.valueOf(nbreJours) + " jours)");
		lblInfoDateEnd.setBounds(12, 118, 380, 16);
		getContentPane().add(lblInfoDateEnd);
		
		JLabel lblNewLabel = new JLabel("Montant total : " + montant + "€");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(6, 135, 390, 65);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Confirmer la location");
		btnNewButton.setBounds(104, 227, 196, 29);
		getContentPane().add(btnNewButton);
	}

}
