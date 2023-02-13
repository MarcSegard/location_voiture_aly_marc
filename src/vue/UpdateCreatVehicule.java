package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.VehiculeDao;

public class UpdateCreatVehicule extends JFrame {
	private VehiculeDao vehiculeDao = new VehiculeDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int nbrFenetre = 0 ;
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
		
		lbl_vehicule_view.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vehicule_view.setIcon(new ImageIcon(UpdateCreatVehicule.class.getResource("/assets/images/petite_citadine/smart_mercedes.png")));
		lbl_vehicule_view.setBounds(286, 10, 498, 239);
		contentPane.add(lbl_vehicule_view);
		//
		ArrayList<String> categories = vehiculeDao.getCategorie();
		categories.add(0, "*");
		String[] categorieToCombo = categories.toArray(new String[categories.size()]);

		JComboBox comboBox_categorie = new JComboBox(categorieToCombo);
		comboBox_categorie.setBounds(286, 279, 208, 21);
		contentPane.add(comboBox_categorie);
		//
		ArrayList<String> modeles = vehiculeDao.getModele();
		modeles.add(0, "*");
		String[] modeleToCombo = modeles.toArray(new String[modeles.size()]);
		
		JComboBox comboBox_model = new JComboBox(modeleToCombo);
		comboBox_model.setBounds(576, 279, 208, 21);
		contentPane.add(comboBox_model);
		//
		ArrayList<String> marques = vehiculeDao.getMarque();
		marques.add(0, "*");
		String[] marqueToCombo = marques.toArray(new String[marques.size()]);
		
		JComboBox comboBox_marque = new JComboBox(marqueToCombo);
		comboBox_marque.setBounds(286, 329, 208, 21);
		contentPane.add(comboBox_marque);
		//
		ArrayList<String> carburants = vehiculeDao.getCarburant();
		carburants.add(0, "*");
		String[] carburantToCombo = carburants.toArray(new String[carburants.size()]);
		
		JComboBox comboBox_carburant = new JComboBox(carburantToCombo);
		comboBox_carburant.setBounds(576, 329, 208, 21);
		contentPane.add(comboBox_carburant);
		//
		
		JButton btn_finish = new JButton("Terminer");
		btn_finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nbrFenetre = 0;
				dispose();
			}
		});
		btn_finish.setBounds(10, 49, 208, 21);
		contentPane.add(btn_finish);
		
		JLabel Categ = new JLabel("Categorie");
		Categ.setBounds(286, 259, 97, 21);
		contentPane.add(Categ);
		
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setBounds(286, 310, 97, 21);
		contentPane.add(lblMarque);
		
		JLabel lbl_model = new JLabel("Modèle");
		lbl_model.setBounds(576, 259, 97, 21);
		contentPane.add(lbl_model);
		
		JLabel lbl_carbu = new JLabel("Carburant");
		lbl_carbu.setBounds(576, 310, 97, 21);
		contentPane.add(lbl_carbu);
		
		kilometreage_input = new JTextField();
		kilometreage_input.setBounds(286, 382, 208, 19);
		contentPane.add(kilometreage_input);
		kilometreage_input.setColumns(10);
		
		couleur_input = new JTextField();
		couleur_input.setColumns(10);
		couleur_input.setBounds(576, 382, 208, 19);
		contentPane.add(couleur_input);
		
		JLabel lbl_kilometre = new JLabel("Kilométrage");
		lbl_kilometre.setBounds(286, 361, 97, 21);
		contentPane.add(lbl_kilometre);
		
		JLabel lbl_couleur = new JLabel("Couleurs");
		lbl_couleur.setBounds(576, 360, 97, 21);
		contentPane.add(lbl_couleur);
		
		image_chemin_update_input = new JTextField();
		image_chemin_update_input.setColumns(10);
		image_chemin_update_input.setBounds(286, 483, 378, 19);
		contentPane.add(image_chemin_update_input);
		
		JLabel lbl_chemin_orgine = new JLabel("Image");
		lbl_chemin_orgine.setBounds(286, 461, 97, 21);
		contentPane.add(lbl_chemin_orgine);
		
		Prix_unitaire_input = new JTextField();
		Prix_unitaire_input.setColumns(10);
		Prix_unitaire_input.setBounds(700, 483, 84, 19);
		contentPane.add(Prix_unitaire_input);
		
		JLabel lbl_Prix = new JLabel("Prix");
		lbl_Prix.setBounds(700, 461, 84, 21);
		contentPane.add(lbl_Prix);
		
		Immatriculation_input = new JTextField();
		Immatriculation_input.setText("TW-004-AA");
		Immatriculation_input.setColumns(10);
		Immatriculation_input.setBounds(286, 432, 84, 19);
		contentPane.add(Immatriculation_input);
		
		JLabel lbl_Immatriculation = new JLabel("Immatriculation");
		lbl_Immatriculation.setBounds(286, 411, 84, 21);
		contentPane.add(lbl_Immatriculation);
		
		input_option = new JTextField();
		input_option.setColumns(10);
		input_option.setBounds(406, 432, 378, 19);
		contentPane.add(input_option);
		
		JLabel lbl_Option = new JLabel("Option");
		lbl_Option.setBounds(406, 411, 84, 21);
		contentPane.add(lbl_Option);
		
		JTextArea Descript_input = new JTextArea();
		Descript_input.setText("Description");
		Descript_input.setBounds(286, 532, 498, 90);
		contentPane.add(Descript_input);
		
		JLabel lbl_Description = new JLabel("Descriptions");
		lbl_Description.setLabelFor(Descript_input);
		lbl_Description.setBounds(286, 512, 84, 21);
		contentPane.add(lbl_Description);
		//
		lbl_vehicule_view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(file);
				//
				//RECUPERATION DU FILCHIER SELECTIONNER
				File fileSelected = file.getSelectedFile();
				String chemin = fileSelected.getAbsolutePath();
				String destination = "src/assets/images/"+comboBox_categorie.getSelectedItem().toString()+"/"+file.getSelectedFile().getName();
				File source = new File(chemin);
				File destinationFinal = new File(destination);
				String tempo= destination.substring(destination.indexOf("/")).trim();
				System.out.println(destination.substring(destination.indexOf("/")));
				lbl_vehicule_view.setIcon(new ImageIcon(UpdateCreatVehicule.class.getResource(tempo.toString())));//"/assets/images/petite_citadine/Q4e-Test.png"
				/*
				try {
					Files.copy(source.toPath(), destinationFinal.toPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});
	}
}
