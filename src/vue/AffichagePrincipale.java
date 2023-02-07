package vue;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class AffichagePrincipale extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textSearch;

	/**
	 * Create the panel.
	 */
	public AffichagePrincipale() {
		setBounds(10, 0, 1488, 785);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 142, 832, 562);
		add(scrollPane);

		String headerTable[] = { "Catégorie", "Marque", "modèle", "couleur", "Carburant", "Prix (€/jour)" };
		Object dataTable[][] = { { "citadine", "Renault", "clio", "rouge", "diesel", "45,5" },
				{ "monospace", "Peugeot", "3008", "noire", "Super", "24,5" },
				{ "berline", "Citroën", "C3", "jaune", "électrique", "56" },
				{ "utilitaire", "Renault", "Kango", "marron", "Hybride", "34,6" } };

		table = new JTable(dataTable, headerTable);
		table.setBackground(UIManager.getColor("Button.select"));
		table.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));

		scrollPane.setViewportView(table);

		String[] categories = { "*", "petite citadine", "citadine", "SUV", "berline", "monospace", "utilitaire" };
		JComboBox comboBoxCategorie = new JComboBox(categories);
		comboBoxCategorie.setBounds(6, 122, 141, 25);
		add(comboBoxCategorie);

		String[] marques = { "*", "renault", "peugeot", "citroën", "fiat", "BMW" };
		JComboBox comboBoxMarques = new JComboBox(marques);
		comboBoxMarques.setBounds(143, 122, 141, 25);
		add(comboBoxMarques);

		String[] carburant = { "*", "diesel", "super98", "super95", "électrique", "hybride" };
		JComboBox comboBoxCarburant = new JComboBox(carburant);
		comboBoxCarburant.setBounds(557, 122, 141, 25);
		add(comboBoxCarburant);

		String[] modele = { "*", "kangoo", "clio", "capture" };
		JComboBox comboBoxModele = new JComboBox(modele);
		comboBoxModele.setBounds(281, 122, 141, 25);
		add(comboBoxModele);

		String[] couleur = { "*", "rouge", "noire", "jaune" };
		JComboBox comboBoxCouleur = new JComboBox(couleur);
		comboBoxCouleur.setBounds(421, 122, 141, 25);
		add(comboBoxCouleur);

		textSearch = new JTextField();
		textSearch.setBounds(48, 83, 650, 27);
		add(textSearch);
		textSearch.setColumns(10);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(
				"/Users/marcsegard/eclipse-workspace/location_voiture_aly_marc/src/assets/icones/search.png"));
		lblSearch.setBounds(6, 75, 51, 42);
		add(lblSearch);

		JLabel imageVehicule = new JLabel("");
		imageVehicule.setHorizontalAlignment(SwingConstants.CENTER);
		imageVehicule.setIcon(new ImageIcon(
				"/Users/marcsegard/eclipse-workspace/location_voiture_aly_marc/src/assets/images/petite_citadine/citroen-am_test.png"));
		imageVehicule.setBounds(866, 42, 600, 300);
		add(imageVehicule);

	}

}
