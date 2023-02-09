package vue;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.UserDao;
import controller.VehiculeDao;
import model.Vehicule;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AffichagePrincipale extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textSearch;

	private VehiculeDao vehiculeDao = new VehiculeDao();

	/**
	 * Create the panel.
	 */
	public AffichagePrincipale() {
		setBounds(10, 0, 1488, 785);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 106, 832, 562);
		add(scrollPane);


		table = new JTable();
		table.setModel(listeVehicule());
		table.setBackground(UIManager.getColor("Button.select"));
		table.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		scrollPane.setViewportView(table);

		/******************************************************/
		/* Remarque JComboBox */
		// Le JComboBox demande un type, on pourrait mettre
		// JComboBox<String> mais windows builder refuse
		// Donc nous ne pouvons pas mettre de <String>
		// Ou alors juste avant la liovraison
		/*****************************************************/

		ArrayList<String> categories = vehiculeDao.getCategorie();
		categories.add(0, "*");
		String[] categorieToCombo = categories.toArray(new String[categories.size()]);
		JComboBox comboBoxCategorie = new JComboBox(categorieToCombo);
		comboBoxCategorie.setBounds(6, 86, 141, 25);
		add(comboBoxCategorie);

		ArrayList<String> marques = vehiculeDao.getMarque();
		marques.add(0, "*");
		String[] marqueToCombo = marques.toArray(new String[marques.size()]);
		JComboBox comboBoxMarques = new JComboBox(marqueToCombo);
		comboBoxMarques.setBounds(143, 86, 141, 25);
		add(comboBoxMarques);

		ArrayList<String> carburants = vehiculeDao.getCarburant();
		carburants.add(0, "*");
		String[] carburantToCombo = carburants.toArray(new String[carburants.size()]);
		JComboBox comboBoxCarburant = new JComboBox(carburantToCombo);
		comboBoxCarburant.setBounds(557, 86, 141, 25);
		add(comboBoxCarburant);

		ArrayList<String> modeles = vehiculeDao.getModele();
		modeles.add(0, "*");
		String[] modeleToCombo = modeles.toArray(new String[modeles.size()]);
		JComboBox comboBoxModele = new JComboBox(modeleToCombo);
		comboBoxModele.setBounds(281, 86, 141, 25);
		add(comboBoxModele);

		ArrayList<String> couleurs = vehiculeDao.getCouleur();
		couleurs.add(0, "*");
		String[] couleurToCombo = couleurs.toArray(new String[couleurs.size()]);
		JComboBox comboBoxCouleur = new JComboBox(couleurToCombo);
		comboBoxCouleur.setBounds(421, 86, 141, 25);
		add(comboBoxCouleur);

		textSearch = new JTextField();
		textSearch.setBounds(48, 47, 650, 27);
		add(textSearch);
		textSearch.setColumns(10);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/search.png")));
		lblSearch.setBounds(6, 39, 51, 42);
		add(lblSearch);

		JLabel lblNewLabel_date_debut = new JLabel("Date de début de location :  ");
		lblNewLabel_date_debut.setLocation(16, 669);
		lblNewLabel_date_debut.setSize(200, 42);
		lblNewLabel_date_debut.setFont(new Font("Nanum Gothic", Font.PLAIN, 14));
		add(lblNewLabel_date_debut);
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl dateDebutLoc = new JDatePickerImpl(datePanel);
		dateDebutLoc.setLocation(206, 677);
		dateDebutLoc.setSize(200, 34);
		add(dateDebutLoc);

		JLabel lblNewLabel_date_fin = new JLabel("Date de fin de location :  ");
		lblNewLabel_date_fin.setLocation(37, 701);
		lblNewLabel_date_fin.setSize(200, 42);
		lblNewLabel_date_fin.setFont(new Font("Nanum Gothic", Font.PLAIN, 14));
		add(lblNewLabel_date_fin);
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl dateFinLoc = new JDatePickerImpl(datePanel2);
		dateFinLoc.setLocation(206, 709);
		dateFinLoc.setSize(200, 34);
		add(dateFinLoc);

		JLabel imageVehicule = new JLabel("");
		imageVehicule.setHorizontalAlignment(SwingConstants.CENTER);
		imageVehicule.setIcon(
				new ImageIcon(AffichagePrincipale.class.getResource("/assets/images/SUV/BMWiX_M60_electrique.png")));
		imageVehicule.setBounds(866, 6, 600, 300);
		add(imageVehicule);

		JLabel lblNewLabel = new JLabel("Total (€) :");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(571, 689, 141, 31);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("45.8");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(682, 680, 156, 49);
		add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Louer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDao.currentUser == null) {
					Inscription login = new Inscription();
					login.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		btnNewButton.setBounds(1130, 687, 129, 34);
		add(btnNewButton);

	}
	
	public DefaultTableModel listeVehicule() {
		//Création de la colonne
		String col [] =  {"Catégorie", "Marque", "modèle", "couleur", "Carburant", "Prix (€/jour)"};
		DefaultTableModel tableau = new DefaultTableModel(null, col);
		for (Vehicule vehicule : vehiculeDao.read()) {
			tableau.addRow(new Object[] {
					vehicule.getCategorie(),
					vehicule.getMarque(),
					vehicule.getModele_vehicule(),
					vehicule.getCouleur(),
					vehicule.getCarburant(),
					vehicule.getPrix()
			});
		}
		return tableau;
		
	}
}
