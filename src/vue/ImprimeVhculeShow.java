package vue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Vehicule;

public class ImprimeVhculeShow extends JDialog {
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ImprimeVhculeShow(Vehicule vehicule, String img) {
		setResizable(false);
		setBounds(500, 90, 700, 400);
		setTitle("Recapitulatif de Synthèse");
		JLabel lbl_img_Vehicule = new JLabel();
		lbl_img_Vehicule.setIcon(new ImageIcon(img));
		lbl_img_Vehicule.setBounds(249, 10, 518, 250);
		getContentPane().add(lbl_img_Vehicule);

		getContentPane().setLayout(null);
		
		JLabel lbl_imp_categ = new JLabel("Categorie : "+vehicule.getCarburant().toString());
		lbl_imp_categ.setBounds(10, 107, 120, 30);
		getContentPane().add(lbl_imp_categ);
		
		JLabel lbl_imp_carbu = new JLabel("Carburant : "+vehicule.getCarburant().toString());
		lbl_imp_carbu.setBounds(340, 192, 158, 30);
		getContentPane().add(lbl_imp_carbu);
		
		JLabel lbl_imp_couleur = new JLabel("Couleur : "+vehicule.getCouleur().toString());
		lbl_imp_couleur.setBounds(188, 148, 120, 30);
		getContentPane().add(lbl_imp_couleur);
		
		JLabel lbl_imp_model = new JLabel("Modele : "+vehicule.getModele_vehicule().toString());
		lbl_imp_model.setBounds(550, 192, 120, 30);
		getContentPane().add(lbl_imp_model);
		
		JLabel lbl_imp_marque = new JLabel("Marque : "+vehicule.getMarque().toString());
		lbl_imp_marque.setBounds(10, 148, 120, 30);
		getContentPane().add(lbl_imp_marque);
		
		JLabel lbl_imp_matricule = new JLabel("New label"+vehicule.getImmatriculation().toString());
		lbl_imp_matricule.setBounds(10, 232, 120, 30);
		getContentPane().add(lbl_imp_matricule);
		
		JLabel lbl_imp_kilometage = new JLabel("Kilometrage : "+vehicule.getKilometrage());
		lbl_imp_kilometage.setBounds(188, 107, 142, 30);
		getContentPane().add(lbl_imp_kilometage);
		
		JLabel lbl_imp_description = new JLabel("Description ;"+vehicule.getDescription().toString());
		lbl_imp_description.setBounds(10, 272, 395, 56);
		getContentPane().add(lbl_imp_description);
		
		JLabel lbl_imp_option = new JLabel("Options : "+vehicule.getOptions().toString());
		lbl_imp_option.setBounds(220, 232, 293, 30);
		getContentPane().add(lbl_imp_option);
		
		JLabel lbl_imp_prix = new JLabel("Prix : "+vehicule.getPrix());
		lbl_imp_prix.setBounds(550, 232, 120, 30);
		getContentPane().add(lbl_imp_prix);
		if (img == null) {
			lbl_img_Vehicule.setIcon(
					new ImageIcon(new ImageIcon(AffichagePrincipale.class.getResource(vehicule.getChemin_image()))
							.getImage().getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));
			
		}else {
			lbl_img_Vehicule.setIcon(
					new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));
		}
		
		/*
		 * getContentPane().setLayout(new BorderLayout()); contentPanel.setBorder(new
		 * EmptyBorder(5, 5, 5, 5)); getContentPane().add(contentPanel,
		 * BorderLayout.CENTER); contentPanel.setLayout(null); { JPanel buttonPane = new
		 * JPanel(); buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 * getContentPane().add(buttonPane, BorderLayout.SOUTH); { JButton okButton =
		 * new JButton("OK"); okButton.setActionCommand("OK"); buttonPane.add(okButton);
		 * getRootPane().setDefaultButton(okButton); } { JButton cancelButton = new
		 * JButton("Cancel"); cancelButton.setActionCommand("Cancel");
		 * buttonPane.add(cancelButton); } }
		 */
	}
}
