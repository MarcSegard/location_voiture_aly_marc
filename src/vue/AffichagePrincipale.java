package vue;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import myConnection.Connect;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AffichagePrincipale extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textSearch;

	private VehiculeDao vehiculeDao = new VehiculeDao();
	private ArrayList<Vehicule> vehicules = vehiculeDao.read();
	private ArrayList<Vehicule> vehiculesFiltered = vehicules;

	/* Exemple de filtrage */
	/*
	 * private ArrayList<Vehicule> vehicules= (ArrayList<Vehicule>)
	 * vehiculesIn.stream() .filter(voiture -> "noire".equals(voiture.getCouleur()))
	 * .filter(voiture -> "renault".equals(voiture.getMarque())) .filter(voiture ->
	 * "diesel".equals(voiture.getCarburant())) .collect(Collectors.toList());
	 */
	/************************/

	private int nbreJoursLocation;
	private Vehicule vehiculeSelected = vehicules.get(0);
	private Date selectedStartDate;
	private Date selectedEndDate;

	public void setVehiculeDao() {
		this.vehicules = vehiculeDao.read();
		vehiculesFiltered = vehicules;
		table.setModel(listeVehicule(vehiculesFiltered));
	}

	/**
	 * Create the panel.
	 */
	public AffichagePrincipale() {
		setBounds(10, 0, 1500, 680);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 106, 832, 422);
		add(scrollPane);

		table = new JTable();
		table.setModel(listeVehicule(vehicules));
		table.setBackground(UIManager.getColor("Button.select"));
		table.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		table.setFont(new Font("Serif", Font.TYPE1_FONT, 16));
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

		JLabel lblNewLabel_date_debut = new JLabel("Date de d??but de location :  ");
		lblNewLabel_date_debut.setLocation(16, 540);
		lblNewLabel_date_debut.setSize(200, 42);
		lblNewLabel_date_debut.setFont(new Font("Nanum Gothic", Font.PLAIN, 14));
		add(lblNewLabel_date_debut);

		UtilDateModel model = new UtilDateModel();
		LocalDate today = LocalDate.now();
		model.setDate(today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth());
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl dateDebutLoc = new JDatePickerImpl(datePanel);
		dateDebutLoc.setLocation(206, 548);
		dateDebutLoc.setSize(200, 34);
		add(dateDebutLoc);

		JLabel lblNewLabel_date_fin = new JLabel("Date de fin de location :  ");
		lblNewLabel_date_fin.setLocation(37, 572);
		lblNewLabel_date_fin.setSize(200, 42);
		lblNewLabel_date_fin.setFont(new Font("Nanum Gothic", Font.PLAIN, 14));
		add(lblNewLabel_date_fin);

		UtilDateModel model2 = new UtilDateModel();
		model2.setDate(today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth() + 1);
		model2.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl dateFinLoc = new JDatePickerImpl(datePanel2);
		dateFinLoc.setLocation(206, 580);
		dateFinLoc.setSize(200, 34);
		add(dateFinLoc);

		JLabel imageVehicule = new JLabel("");
		imageVehicule.setHorizontalAlignment(SwingConstants.CENTER);
		imageVehicule.setIcon(new ImageIcon(AffichagePrincipale.class.getResource(vehicules.get(0).getChemin_image())));
		imageVehicule.setBounds(866, 6, 600, 300);
		add(imageVehicule);

		JLabel lblNewLabel = new JLabel("Total (???) :");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(487, 548, 141, 31);
		add(lblNewLabel);

		JLabel lblPrixLocation = new JLabel(String.valueOf(vehicules.get(0).getPrix()));
		lblPrixLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrixLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblPrixLocation.setBounds(642, 540, 196, 49);
		add(lblPrixLocation);

		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Confirmer et payer la location");
		btnNewButton.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/rent.png")));

		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		btnNewButton.setBounds(1069, 610, 64, 64);
		add(btnNewButton);

		JLabel lblCardTitle = new JLabel(vehicules.get(0).getMarque() + " " + vehicules.get(0).getModele_vehicule());
		lblCardTitle.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblCardTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardTitle.setBounds(908, 287, 558, 49);
		add(lblCardTitle);

		JLabel lblCardLabelCarburant = new JLabel("");
		lblCardLabelCarburant
				.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/Carburant.png")));
		lblCardLabelCarburant.setBounds(946, 333, 51, 55);
		add(lblCardLabelCarburant);

		JLabel lblCardLabelCouleur = new JLabel("");
		lblCardLabelCouleur.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/Couleur.png")));
		lblCardLabelCouleur.setBounds(946, 400, 51, 55);
		add(lblCardLabelCouleur);

		JLabel lblCardLabelOptions = new JLabel("");
		lblCardLabelOptions.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/Options.png")));
		lblCardLabelOptions.setBounds(946, 467, 51, 55);
		add(lblCardLabelOptions);

		JLabel lblCardViewCarburant = new JLabel(vehicules.get(0).getCarburant());
		lblCardViewCarburant.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCardViewCarburant.setBounds(1031, 345, 374, 34);
		add(lblCardViewCarburant);

		JLabel lblCardViewCouleur = new JLabel(vehicules.get(0).getCouleur());
		lblCardViewCouleur.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCardViewCouleur.setBounds(1031, 410, 374, 38);
		add(lblCardViewCouleur);

		JLabel lblCardViewOptions = new JLabel(vehicules.get(0).getOptions());
		lblCardViewOptions.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblCardViewOptions.setBounds(1031, 477, 374, 37);
		add(lblCardViewOptions);

		JLabel lblCardLabelDescription = new JLabel("");
		lblCardLabelDescription
				.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/Description.png")));
		lblCardLabelDescription.setBounds(946, 545, 51, 49);
		add(lblCardLabelDescription);

		JTextArea textAreaCardViewDescription = new JTextArea(vehicules.get(0).getDescription());
		textAreaCardViewDescription.setBorder(null);
		textAreaCardViewDescription.setBackground(new Color(238, 238, 238));
		textAreaCardViewDescription.setEditable(false);
		textAreaCardViewDescription.setLineWrap(true);
		textAreaCardViewDescription.setBounds(1032, 540, 373, 70);
		add(textAreaCardViewDescription);

		JButton btnDeconnexion = new JButton("");
		btnDeconnexion.setToolTipText("D??connexion");
		btnDeconnexion.setIcon(new ImageIcon(AffichagePrincipale.class.getResource("/assets/icones/Deconnexion.png")));

		btnDeconnexion.setFont(new Font("SansSerif", Font.PLAIN, 30));
		btnDeconnexion.setBounds(1218, 610, 64, 64);
		add(btnDeconnexion);

		comboBoxCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCategorie.getSelectedIndex() != 0) {
					vehiculesFiltered = (ArrayList<Vehicule>) vehicules.stream().filter(
							voiture -> comboBoxCategorie.getSelectedItem().toString().equals(voiture.getCategorie()))
							.collect(Collectors.toList());

					table.setModel(listeVehicule(vehiculesFiltered));
				} else {
					vehiculesFiltered = vehicules;
					table.setModel(listeVehicule(vehiculesFiltered));
				}
			}
		});

		comboBoxMarques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxMarques.getSelectedIndex() != 0) {
					vehiculesFiltered = (ArrayList<Vehicule>) vehicules.stream()
							.filter(voiture -> comboBoxMarques.getSelectedItem().toString().equals(voiture.getMarque()))
							.collect(Collectors.toList());

					table.setModel(listeVehicule(vehiculesFiltered));
				} else {
					vehiculesFiltered = vehicules;
					table.setModel(listeVehicule(vehiculesFiltered));
				}
			}
		});

		comboBoxModele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxModele.getSelectedIndex() != 0) {
					vehiculesFiltered = (ArrayList<Vehicule>) vehicules.stream().filter(
							voiture -> comboBoxModele.getSelectedItem().toString().equals(voiture.getModele_vehicule()))
							.collect(Collectors.toList());

					table.setModel(listeVehicule(vehiculesFiltered));
				} else {
					vehiculesFiltered = vehicules;
					table.setModel(listeVehicule(vehiculesFiltered));
				}
			}
		});

		comboBoxCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCouleur.getSelectedIndex() != 0) {
					vehiculesFiltered = (ArrayList<Vehicule>) vehicules.stream().filter(
							voiture -> comboBoxCouleur.getSelectedItem().toString().equals(voiture.getCouleur()))
							.collect(Collectors.toList());

					table.setModel(listeVehicule(vehiculesFiltered));
				} else {
					vehiculesFiltered = vehicules;
					table.setModel(listeVehicule(vehiculesFiltered));
				}
			}
		});

		comboBoxCarburant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCarburant.getSelectedIndex() != 0) {
					vehiculesFiltered = (ArrayList<Vehicule>) vehicules.stream().filter(
							voiture -> comboBoxCarburant.getSelectedItem().toString().equals(voiture.getCarburant()))
							.collect(Collectors.toList());

					table.setModel(listeVehicule(vehiculesFiltered));
				} else {
					vehiculesFiltered = vehicules;
					table.setModel(listeVehicule(vehiculesFiltered));
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.err.println(table.getSelectedRow());
				vehiculeSelected = vehiculesFiltered.get(table.getSelectedRow());
				imageVehicule.setIcon(
						new ImageIcon(AffichagePrincipale.class.getResource(vehiculeSelected.getChemin_image())));
				lblPrixLocation.setText(String.valueOf(vehiculeSelected.getPrix()));
				lblCardTitle.setText(vehiculeSelected.getMarque() + " " + vehiculeSelected.getModele_vehicule());
				lblCardViewCarburant.setText(vehiculeSelected.getCarburant());
				lblCardViewCouleur.setText(vehiculeSelected.getCouleur());
				lblCardViewOptions.setText(vehiculeSelected.getOptions());
				textAreaCardViewDescription.setText(vehiculeSelected.getDescription());
				miseAJourPrixLocation(lblPrixLocation);
			}
		});

		// Mise ?? jour du JDatePicker de fin de location avec
		// JDatePicker de d??but de location + 1 jour
		// Mise ?? jour du total en fonction du nombre de jours
		dateDebutLoc.getJFormattedTextField().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				selectedStartDate = (Date) datePanel.getModel().getValue();
				selectedEndDate = (Date) datePanel2.getModel().getValue();
				nbreJoursLocation = (int) diffDays(selectedStartDate, selectedEndDate);

				// Si la date de d??but est apr??s la date de fin
				// La date de fin s'aligne sur la date de d??but plus un jour
				if (nbreJoursLocation < 0 || nbreJoursLocation == 0) {
					model2.setDate(datePanel.getModel().getYear(), datePanel.getModel().getMonth(),
							datePanel.getModel().getDay() + 1);
				}

				// Comparaison de la date s??lectionn??e avec la date d'aujourd'hui
				int compareDate2 = selectedStartDate.compareTo(
						Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				if (compareDate2 < 0) {
					LocalDate today = LocalDate.now();
					model.setDate(today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth());
				}

				miseAJourPrixLocation(lblPrixLocation);
			}
		});

		// Mise ?? jour du total en fonction du nombre de jours
		dateFinLoc.getJFormattedTextField().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				selectedStartDate = (Date) datePanel.getModel().getValue();
				selectedEndDate = (Date) datePanel2.getModel().getValue();
				nbreJoursLocation = (int) diffDays(selectedStartDate, selectedEndDate);

				int compareDate = selectedEndDate.compareTo(
						Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				if (compareDate < 0) {
					model2.setDate(datePanel.getModel().getYear(), datePanel.getModel().getMonth(),
							datePanel.getModel().getDay() + 1);
				}

				if (nbreJoursLocation < 0 || nbreJoursLocation == 0) {
					model2.setDate(datePanel.getModel().getYear(), datePanel.getModel().getMonth(),
							datePanel.getModel().getDay() + 1);
				}

				miseAJourPrixLocation(lblPrixLocation);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDao.currentUser == null) {
					Inscription login = new Inscription();
					login.setVisible(true);
				} else {
					ShowLocation showFacture = new ShowLocation(vehiculeSelected, nbreJoursLocation, selectedStartDate,
							selectedEndDate, lblPrixLocation.getText());
					showFacture.setVisible(true);
				}
			}
		});

		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ArrayList<Vehicule> vehiculesFilteredSearch = new ArrayList<>();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] words = textSearch.getText().split(" ");

					for (Vehicule voiture : vehicules) {
						boolean found = true;
						String params = voiture.getCategorie().concat(" " + voiture.getCouleur())
								.concat(" " + voiture.getOptions()).concat(" " + voiture.getCarburant())
								.concat(" " + voiture.getModele_vehicule()).concat(" " + voiture.getDescription())
								.concat(" " + voiture.getMarque());
						for (String word : words) {
							found &= params.contains(word);
						}

						if (found) {
							vehiculesFilteredSearch.add(voiture);
						}
					}
					vehiculesFiltered = vehiculesFilteredSearch;
					table.setModel(listeVehicule(vehiculesFiltered));
				}
			}
		});

		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDao.currentUser == null) {
					JOptionPane.showMessageDialog(null, "Pour se d??connecter il faut d'abord ??tre connecter", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (UserDao.currentUser.getEmail().contains("@agence.fr")) {
						try {
							Connect.currentConnection.close();
							Connect.currentConnection = Connect.getConnection(false);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					UserDao.currentUser = null;
					MainFrame.btnNewButton.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/icones/login.png")));
					JOptionPane.showMessageDialog(null, "Vous ??tes d??connect??(e).", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private DefaultTableModel listeVehicule(ArrayList<Vehicule> vehiculesInTable) {
		// Cr??ation de la colonne
		String col[] = { "Cat??gorie", "Marque", "mod??le", "couleur", "Carburant", "Prix (???/jour)" };
		DefaultTableModel tableau = new DefaultTableModel(null, col);
		for (Vehicule vehicule : vehiculesInTable) {
			tableau.addRow(new Object[] { vehicule.getCategorie(), vehicule.getMarque(), vehicule.getModele_vehicule(),
					vehicule.getCouleur(), vehicule.getCarburant(), vehicule.getPrix() });
		}
		return tableau;
	}

	private long diffDays(Date start, Date fin) {
		TimeUnit time = TimeUnit.DAYS;
		long difference = time.convert(fin.getTime() - start.getTime(), TimeUnit.MILLISECONDS);
		System.out.println("Diff days :  " + String.valueOf(difference));
		return difference;
	}

	private void miseAJourPrixLocation(JLabel affichagePrix) {
		affichagePrix.setText(String.format("%.2f", nbreJoursLocation * vehiculeSelected.getPrix()));
	}
}
