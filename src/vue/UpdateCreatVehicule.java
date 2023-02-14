package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.VehiculeDao;
import model.Vehicule;

public class UpdateCreatVehicule extends JFrame {
	private VehiculeDao vehiculeDao = new VehiculeDao();
	private String tempo;
	JProgressBar progress;
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int nbrFenetre = 0;
	private JTextField kilometreage_input;
	private JTextField couleur_input;
	private JTextField image_chemin_update_input;
	private JTextField Prix_unitaire_input;
	private JTextField Immatriculation_input;
	private JTextField input_option;

	/**
	 * Create the frame.
	 */
	public UpdateCreatVehicule() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_vehicule_view = new JLabel("");
		lbl_vehicule_view.setToolTipText("<html>" + "Download!" + " " + "here" + "</html>");
		getContentPane().add(lbl_vehicule_view);

		lbl_vehicule_view.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vehicule_view.setIcon(new ImageIcon(
				UpdateCreatVehicule.class.getResource("/assets/images/petite_citadine/smart_mercedes.png")));
		lbl_vehicule_view.setBounds(27, 10, 740, 239);
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

		JButton btn_finish = new JButton("Terminer");
		btn_finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nbrFenetre = 0;
				dispose();
			}
		});
		btn_finish.setBounds(542, 640, 208, 21);
		contentPane.add(btn_finish);

		JLabel Categ = new JLabel("Categorie");
		Categ.setBounds(10, 259, 97, 21);
		contentPane.add(Categ);

		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setBounds(10, 310, 97, 21);
		contentPane.add(lblMarque);

		JLabel lbl_model = new JLabel("Modèle");
		lbl_model.setBounds(552, 259, 97, 21);
		contentPane.add(lbl_model);

		JLabel lbl_carbu = new JLabel("Carburant");
		lbl_carbu.setBounds(562, 310, 97, 21);
		contentPane.add(lbl_carbu);

		kilometreage_input = new JTextField();
		kilometreage_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		kilometreage_input.setBounds(299, 280, 195, 19);
		contentPane.add(kilometreage_input);
		kilometreage_input.setColumns(10);

		couleur_input = new JTextField();
		couleur_input.setColumns(10);
		couleur_input.setBounds(299, 330, 195, 19);
		contentPane.add(couleur_input);

		JLabel lbl_kilometre = new JLabel("Kilométrage");
		lbl_kilometre.setBounds(299, 259, 97, 21);
		contentPane.add(lbl_kilometre);

		JLabel lbl_couleur = new JLabel("Couleurs");
		lbl_couleur.setBounds(299, 310, 97, 21);
		contentPane.add(lbl_couleur);

		image_chemin_update_input = new JTextField();
		image_chemin_update_input.setColumns(10);
		image_chemin_update_input.setBounds(10, 432, 484, 19);
		contentPane.add(image_chemin_update_input);

		JLabel lbl_chemin_orgine = new JLabel("Image");
		lbl_chemin_orgine.setBounds(10, 411, 97, 21);
		contentPane.add(lbl_chemin_orgine);

		Prix_unitaire_input = new JTextField();
		Prix_unitaire_input.setColumns(10);
		Prix_unitaire_input.setBounds(662, 432, 105, 19);
		contentPane.add(Prix_unitaire_input);

		JLabel lbl_Prix = new JLabel("Prix");
		lbl_Prix.setBounds(645, 411, 84, 21);
		contentPane.add(lbl_Prix);

		Immatriculation_input = new JTextField();
		Immatriculation_input.setText("TW-004-AA");
		Immatriculation_input.setColumns(10);
		Immatriculation_input.setBounds(10, 382, 105, 19);
		contentPane.add(Immatriculation_input);

		JLabel lbl_Immatriculation = new JLabel("Immatriculation");
		lbl_Immatriculation.setBounds(10, 359, 84, 21);
		contentPane.add(lbl_Immatriculation);

		input_option = new JTextField();
		input_option.setColumns(10);
		input_option.setBounds(299, 382, 468, 19);
		contentPane.add(input_option);

		JLabel lbl_Option = new JLabel("Option");
		lbl_Option.setBounds(299, 359, 84, 21);
		contentPane.add(lbl_Option);

		JTextArea Descript_input = new JTextArea();
		Descript_input.setText("Description");
		Descript_input.setBounds(10, 492, 757, 90);
		contentPane.add(Descript_input);

		JLabel lbl_Description = new JLabel("Descriptions");
		lbl_Description.setLabelFor(Descript_input);
		lbl_Description.setBounds(10, 471, 84, 21);
		contentPane.add(lbl_Description);
		//

		//
		JButton btn_create = new JButton("Create");
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
					JOptionPane.showMessageDialog(null, "Impossible! Veuillez remplire les champs ", null,
							JOptionPane.ERROR_MESSAGE);

				} else {
					Vehicule vehi = new Vehicule(couleur_input.getText(),
							Float.parseFloat(Prix_unitaire_input.getText().toString()), Immatriculation_input.getText().toString(),
							Descript_input.getText(), Double.parseDouble(kilometreage_input.getText().toString()),
							input_option.getText(), "ABIS", comboBox_categorie.getSelectedItem().toString(),
							comboBox_carburant.getSelectedItem().toString(),
							comboBox_marque.getSelectedItem().toString(), comboBox_model.getSelectedItem().toString(),
							tempo.toString());
					System.out.println(vehi.getCouleur() + " " + vehi.getPrix() + " " + vehi.getImmatriculation() + " "
							+ vehi.getDescription() + " " + vehi.getKilometrage() + " " + vehi.getOptions() + " "
							+ vehi.getCategorie() + " " + vehi.getCarburant() + " " + vehi.getMarque() + " "
							+ vehi.getModele_vehicule() + " " + vehi.getChemin_image().toString());
					if (vehiculeDao.create(vehi)) {
						JOptionPane.showMessageDialog(null, "Bravo, compte créé");
						ImprimeVhculeShow vhculeShow = new ImprimeVhculeShow();
						vhculeShow.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "OUPS, erreur lors de l'insertion");
					}

				}
			}
		});
		btn_create.setBounds(254, 640, 208, 21);
		contentPane.add(btn_create);
		//
		lbl_vehicule_view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(file);
				//
				// RECUPERATION DU FILCHIER SELECTIONNER
				File fileSelected = file.getSelectedFile();
				String chemin = fileSelected.getAbsolutePath();
				String destination = "src/assets/images/" + comboBox_categorie.getSelectedItem().toString() + "/"
						+ file.getSelectedFile().getName();
				File source = new File(chemin);
				File destinationFinal = new File(destination);
				tempo = destination.substring(destination.indexOf("/")).trim();
				System.out.println(destination.substring(destination.indexOf("/")));
				try {
					Files.copy(source.toPath(), destinationFinal.toPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
