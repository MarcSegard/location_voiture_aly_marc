package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.VehiculeDao;
import model.Vehicule;

public class UpdateCreatVehicule extends JFrame {
	private VehiculeDao vehiculeDao = new VehiculeDao();
	private String tempo;
	private Vehicule imat;
	private String cheminOrigine;
	private JLabel lbl_vehicule_view;
	private String imgRetour = null;
	private ImprimeVhculeShow vhculeShow;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int nbrFenetre = 0;
	private JTextField kilometreage_input;
	private JTextField couleur_input;
	// private JTextField image_chemin_update_input;
	private JTextField Prix_unitaire_input;
	private JTextField Immatriculation_input;
	private JTextField input_option;

	/**
	 * Create the frame.
	 */
	public UpdateCreatVehicule(AffichagePrincipale affichagePrincipale) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Gestion des Vehicules");
		setBounds(100, 100, 798, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl_vehicule_view = new JLabel(
				new ImageIcon(new ImageIcon("src/assets/images/petite_citadine/smart_mercedes.png").getImage()
						.getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));
		lbl_vehicule_view.setToolTipText("<html>" + "Download!" + " " + "here" + "</html>");
		getContentPane().add(lbl_vehicule_view);
		lbl_vehicule_view.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vehicule_view.setBounds(249, 10, 518, 250);
		contentPane.add(lbl_vehicule_view);
		//
		ArrayList<String> categories = vehiculeDao.getCategorie();
		categories.add(0, "*");
		String[] categorieToCombo = categories.toArray(new String[categories.size()]);

		JComboBox comboBox_categorie = new JComboBox(categorieToCombo);
		comboBox_categorie.setBounds(10, 279, 195, 21);
		contentPane.add(comboBox_categorie);
		//
		ArrayList<String> modeles = vehiculeDao.getModele();
		modeles.add(0, "*");
		String[] modeleToCombo = modeles.toArray(new String[modeles.size()]);

		JComboBox comboBox_model = new JComboBox(modeleToCombo);
		comboBox_model.setBounds(559, 279, 208, 21);
		contentPane.add(comboBox_model);
		//
		ArrayList<String> marques = vehiculeDao.getMarque();
		marques.add(0, "*");
		String[] marqueToCombo = marques.toArray(new String[marques.size()]);

		JComboBox comboBox_marque = new JComboBox(marqueToCombo);
		comboBox_marque.setBounds(10, 329, 195, 21);
		contentPane.add(comboBox_marque);
		//
		ArrayList<String> carburants = vehiculeDao.getCarburant();
		carburants.add(0, "*");
		String[] carburantToCombo = carburants.toArray(new String[carburants.size()]);

		JComboBox comboBox_carburant = new JComboBox(carburantToCombo);
		comboBox_carburant.setBounds(559, 329, 208, 21);
		contentPane.add(comboBox_carburant);
		//
		ArrayList<String> matricules = vehiculeDao.getImmatriculation();
		matricules.add(0, "*********");
		String[] matriculesToCombo = matricules.toArray(new String[matricules.size()]);
		JComboBox comboBox_imat_shearch = new JComboBox(matriculesToCombo);
		comboBox_imat_shearch.setBounds(10, 142, 195, 21);
		contentPane.add(comboBox_imat_shearch);

		JLabel lblChoixImmatiriculation = new JLabel("Choix immatiriculation");
		lblChoixImmatiriculation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChoixImmatiriculation.setBounds(10, 111, 195, 21);
		contentPane.add(lblChoixImmatiriculation);
		//
		JButton btn_finish = new JButton("Terminer");
		btn_finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nbrFenetre = 0;
				dispose();
			}
		});
		btn_finish.setBounds(531, 505, 208, 40);
		contentPane.add(btn_finish);

		JLabel Categ = new JLabel("Categorie");
		Categ.setFont(new Font("Tahoma", Font.BOLD, 14));
		Categ.setBounds(10, 259, 97, 21);
		contentPane.add(Categ);

		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarque.setBounds(10, 310, 97, 21);
		contentPane.add(lblMarque);

		JLabel lbl_model = new JLabel("Modèle");
		lbl_model.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_model.setBounds(559, 259, 97, 21);
		contentPane.add(lbl_model);

		JLabel lbl_carbu = new JLabel("Carburant");
		lbl_carbu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_carbu.setBounds(562, 310, 97, 21);
		contentPane.add(lbl_carbu);

		kilometreage_input = new JTextField();
		kilometreage_input.setBounds(299, 280, 195, 19);
		contentPane.add(kilometreage_input);
		kilometreage_input.setColumns(10);

		couleur_input = new JTextField();
		couleur_input.setColumns(10);
		couleur_input.setBounds(299, 330, 195, 19);
		contentPane.add(couleur_input);

		JLabel lbl_kilometre = new JLabel("Kilométrage :");
		lbl_kilometre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_kilometre.setBounds(299, 259, 97, 21);
		contentPane.add(lbl_kilometre);

		JLabel lbl_couleur = new JLabel("Couleurs");
		lbl_couleur.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_couleur.setBounds(299, 310, 97, 21);
		contentPane.add(lbl_couleur);
		/*
		 * image_chemin_update_input = new JTextField();
		 * image_chemin_update_input.setColumns(10);
		 * image_chemin_update_input.setBounds(10, 432, 484, 19);
		 * contentPane.add(image_chemin_update_input);
		 */

		/*
		 * JLabel lbl_chemin_orgine = new JLabel("Image");
		 * lbl_chemin_orgine.setBounds(10, 411, 97, 21);
		 * contentPane.add(lbl_chemin_orgine);
		 */

		Prix_unitaire_input = new JTextField();
		Prix_unitaire_input.setColumns(10);
		Prix_unitaire_input.setBounds(662, 382, 105, 19);
		contentPane.add(Prix_unitaire_input);

		JLabel lbl_Prix = new JLabel("Prix :");
		lbl_Prix.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Prix.setBounds(662, 359, 84, 21);
		contentPane.add(lbl_Prix);

		Immatriculation_input = new JTextField();
		Immatriculation_input.setText("TW-004-AA");
		Immatriculation_input.setColumns(10);
		Immatriculation_input.setBounds(10, 382, 121, 19);
		contentPane.add(Immatriculation_input);

		JLabel lbl_Immatriculation = new JLabel("Immatriculation :");
		lbl_Immatriculation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Immatriculation.setBounds(10, 359, 127, 21);
		contentPane.add(lbl_Immatriculation);

		input_option = new JTextField();
		input_option.setColumns(10);
		input_option.setBounds(200, 382, 358, 19);
		contentPane.add(input_option);

		JLabel lbl_Option = new JLabel("Option :");
		lbl_Option.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Option.setBounds(200, 359, 84, 21);
		contentPane.add(lbl_Option);

		JTextArea Descript_input = new JTextArea();
		Descript_input.setText("Description");
		Descript_input.setBounds(10, 429, 757, 54);
		contentPane.add(Descript_input);

		JLabel lbl_Description = new JLabel("Descriptions :");
		lbl_Description.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Description.setLabelFor(Descript_input);
		lbl_Description.setBounds(10, 411, 105, 21);
		contentPane.add(lbl_Description);
		JButton btn_create = new JButton("Create");
		btn_create.setEnabled(true);
		//
		kilometreage_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		//
		Prix_unitaire_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		//
		comboBox_imat_shearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_imat_shearch.getSelectedIndex() != 0) {
					btn_create.setText("Update");
					Immatriculation_input.setEditable(false);

					String box = comboBox_imat_shearch.getSelectedItem().toString();
					imat = vehiculeDao.findByImmatriculation(box);

					comboBox_categorie.setSelectedItem(imat.getCategorie());
					System.out.println(imat.getCategorie());
					comboBox_model.setSelectedItem(imat.getModele_vehicule());
					comboBox_marque.setSelectedItem(imat.getMarque());
					comboBox_carburant.setSelectedItem(imat.getCarburant());

					lbl_vehicule_view.setIcon(
							new ImageIcon(new ImageIcon(AffichagePrincipale.class.getResource(imat.getChemin_image()))
									.getImage().getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));

					Prix_unitaire_input.setText(String.valueOf(imat.getPrix()));
					Descript_input.setText(imat.getDescription());
					input_option.setText(imat.getOptions());
					Immatriculation_input.setText(imat.getImmatriculation());
					couleur_input.setText(imat.getCouleur());
					kilometreage_input.setText(String.valueOf(imat.getKilometrage()));
					tempo = imat.getChemin_image();

					cheminOrigine = tempo;
				} else {
					btn_create.setText("Create");
					Immatriculation_input.setEditable(true);
					System.out.println(comboBox_imat_shearch.getSelectedIndex());

				}
			}

		});
		//
		btn_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CREATION DU VEHICULE UPDATE
				if (couleur_input.getText().length() == 0 || Prix_unitaire_input.getText().length() == 0
						|| Immatriculation_input.getText().length() == 0 || Descript_input.getText().length() == 0
						|| kilometreage_input.getText().length() == 0 || input_option.getText().length() == 0
						|| comboBox_categorie.getSelectedItem().toString().length() == 0
						|| comboBox_marque.getSelectedItem().toString().length() == 0
						|| comboBox_marque.getSelectedItem().toString().length() == 0
						|| comboBox_model.getSelectedItem().toString().length() == 0 || tempo.length() == 0) {
					JOptionPane.showMessageDialog(null, "Impossible!! Veuillez remplire les champs ", null,
							JOptionPane.ERROR_MESSAGE);

				} else {
					if (comboBox_imat_shearch.getSelectedIndex() == 0) {

						Vehicule vehi = new Vehicule(couleur_input.getText(),
								Float.parseFloat(Prix_unitaire_input.getText().toString()),
								Immatriculation_input.getText().toString(), Descript_input.getText(),
								Double.parseDouble(kilometreage_input.getText().toString()), input_option.getText(),
								"ABIS", comboBox_categorie.getSelectedItem().toString(),
								comboBox_carburant.getSelectedItem().toString(),
								comboBox_marque.getSelectedItem().toString(),
								comboBox_model.getSelectedItem().toString(), tempo.toString());

						/*
						 * System.out.println(vehi.getCouleur() + " " + vehi.getPrix() + " " +
						 * vehi.getImmatriculation() + " " + vehi.getDescription() + " " +
						 * vehi.getKilometrage() + " " + vehi.getOptions() + " " + vehi.getCategorie() +
						 * " " + vehi.getCarburant() + " " + vehi.getMarque() + " " +
						 * vehi.getModele_vehicule() + " " + vehi.getChemin_image().toString());
						 */

						if (vehiculeDao.create(vehi)) {
							if (vhculeShow.nbrFen == 0) {
								//JOptionPane.showMessageDialog(null, "Bravo, Vehicule a bien été créé.");
								vhculeShow = new ImprimeVhculeShow(vehi, imgRetour);
								vhculeShow.setVisible(true);
								btn_create.setEnabled(false);
								btn_finish.setEnabled(false);
								vhculeShow.nbrFen++;
								// Désactivation des boutons btn_create / finish et attente de la fermeture de
								// la fenêtre ImprimeVhculeShow pour réactiver les boutons
								vhculeShow.addWindowListener(new WindowAdapter() {
									public void windowClosed(WindowEvent e) {
										btn_create.setEnabled(true);
										btn_finish.setEnabled(true);
									}
								});
							}

						} else {
							JOptionPane.showMessageDialog(null, "Oups!! Erreur lors de l'insertion.");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Mise a jours Vehicule a bien été effectuer.");
						imat.setCouleur(couleur_input.getText());
						imat.setPrix(Float.parseFloat(Prix_unitaire_input.getText().toString()));
						imat.setImmatriculation(Immatriculation_input.getText().toString());
						imat.setDescription(Descript_input.getText());
						imat.setKilometrage(Double.parseDouble(kilometreage_input.getText().toString()));
						imat.setOptions(input_option.getText());
						imat.setCategorie(comboBox_categorie.getSelectedItem().toString());
						imat.setCarburant(comboBox_carburant.getSelectedItem().toString());
						imat.setMarque(comboBox_marque.getSelectedItem().toString());
						imat.setModele_vehicule(comboBox_model.getSelectedItem().toString());
						imat.setChemin_image(tempo.toString());
						int idImage = vehiculeDao.getIdImage(cheminOrigine);
						System.out.println(" chemin " + imat.getChemin_image());
						System.out.println(" chemin_tempo " + tempo.toString());
						if (idImage == -1) {
							JOptionPane.showMessageDialog(null, "Impossible!! Probleme id image.  ", null,
									JOptionPane.ERROR_MESSAGE);
						} else {
							vehiculeDao.update(idImage, imat);
							System.out.println("id vehicule " + imat.getId());
						}
					}
					// Mise à jour données dans le composant AffichagePrincipale
					affichagePrincipale.setVehiculeDao();
				}
			}
		});
		btn_create.setBounds(10, 505, 195, 40);
		contentPane.add(btn_create);

		lbl_vehicule_view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(file);
				//
				// RECUPERATION DU FILCHIER SELECTIONNER
				File fileSelected = file.getSelectedFile();
				String chemin = fileSelected.getAbsolutePath();
				System.out.println("chemin*****" + chemin);
				String destination = "src/assets/images/" + comboBox_categorie.getSelectedItem().toString() + "/"
						+ file.getSelectedFile().getName();
				File source = new File(chemin);
				File destinationFinal = new File(destination);
				tempo = destination.substring(destination.indexOf("/")).trim();
				System.out.println(destination.substring(destination.indexOf("/")));
				try {
					Files.copy(source.toPath(), destinationFinal.toPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgRetour = chemin.replace("/\\/g", "\\\\");
				lbl_vehicule_view.setIcon(new ImageIcon(
						new ImageIcon(imgRetour).getImage().getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));
			}
		});
	}
}
