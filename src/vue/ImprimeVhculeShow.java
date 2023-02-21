package vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Vehicule;

public class ImprimeVhculeShow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static int nbrFen = 0;

	/**
	 * Create the dialog.
	 */
	public ImprimeVhculeShow(Vehicule vehicule, String img) {
		setResizable(false);
		setBounds(500, 90, 771, 450);
		setTitle("Récapitulatif de Synthèse");
		getContentPane().setLayout(null);

		JLabel lbl_img_Vehicule = new JLabel();
		lbl_img_Vehicule.setIcon(new ImageIcon(img));
		lbl_img_Vehicule.setBounds(230, 10, 518, 250);
		getContentPane().add(lbl_img_Vehicule);

		JLabel lbl_imp_carbu = new JLabel("Carburant : " + vehicule.getCarburant().toString());
		lbl_imp_carbu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_carbu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_imp_carbu.setBounds(10, 310, 188, 30);
		getContentPane().add(lbl_imp_carbu);

		JLabel lbl_imp_description = new JLabel("Description ;" + vehicule.getDescription().toString());
		lbl_imp_description.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_description.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_imp_description.setBounds(20, 350, 518, 56);
		getContentPane().add(lbl_imp_description);

		JLabel lbl_imp_option = new JLabel("Options : " + vehicule.getOptions().toString());
		lbl_imp_option.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_option.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_imp_option.setBounds(230, 310, 398, 30);
		getContentPane().add(lbl_imp_option);

		JLabel lbl_imp_prix = new JLabel("Prix : " + vehicule.getPrix()+" €");
		lbl_imp_prix.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_prix.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_imp_prix.setBounds(664, 310, 84, 30);
		getContentPane().add(lbl_imp_prix);
		
		JLabel lbl_icone = new JLabel("");
		lbl_icone
				.setIcon(new ImageIcon(ImprimeVhculeShow.class.getResource("/assets/icones/transferserviceblack.png")));
		lbl_icone.setBounds(20, 21, 152, 100);
		contentPanel.add(lbl_icone);

		JLabel lbl_imp_model = new JLabel("Modèle : " + vehicule.getModele_vehicule().toString());
		lbl_imp_model.setBounds(10, 225, 168, 30);
		contentPanel.add(lbl_imp_model);
		lbl_imp_model.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_model.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lbl_imp_matricule = new JLabel(" Numéro Immatricule : " + vehicule.getImmatriculation().toString());
		lbl_imp_matricule.setBounds(10, 265, 188, 30);
		contentPanel.add(lbl_imp_matricule);
		lbl_imp_matricule.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_matricule.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lbl_imp_kilometage = new JLabel("Kilométrage : " + vehicule.getKilometrage());
		lbl_imp_kilometage.setBounds(230, 265, 168, 30);
		contentPanel.add(lbl_imp_kilometage);
		lbl_imp_kilometage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_kilometage.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lbl_imp_couleur = new JLabel("Couleur : " + vehicule.getCouleur().toString());
		lbl_imp_couleur.setBounds(443, 265, 188, 30);
		contentPanel.add(lbl_imp_couleur);
		lbl_imp_couleur.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_couleur.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lbl_imp_marque = new JLabel("Marque : " + vehicule.getMarque().toString());
		lbl_imp_marque.setBounds(10, 185, 168, 30);
		contentPanel.add(lbl_imp_marque);
		lbl_imp_marque.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_marque.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lbl_imp_categ = new JLabel("Catégorie : " + vehicule.getCarburant().toString());
		lbl_imp_categ.setBounds(10, 145, 168, 30);
		contentPanel.add(lbl_imp_categ);
		lbl_imp_categ.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imp_categ.setFont(new Font("Tahoma", Font.BOLD, 14));


		if (img == null) {
			lbl_img_Vehicule.setIcon(
					new ImageIcon(new ImageIcon(AffichagePrincipale.class.getResource(vehicule.getChemin_image()))
							.getImage().getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));

		} else {
			lbl_img_Vehicule.setIcon(new ImageIcon(
					new ImageIcon(img).getImage().getScaledInstance(400, 250, java.awt.Image.SCALE_SMOOTH)));
		}

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btn_confirmation = new JButton(" Je Valide la modification");
		btn_confirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nbrFen = 0;
				dispose();
			}
		});
		btn_confirmation.setBounds(629, 357, 97, 39);
		contentPanel.add(btn_confirmation);

		
	}
}
