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
		setBounds(500, 90, 700, 500);
		setTitle("Synthèse");
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(168, 10, 297, 185);
		getContentPane().add(lblNewLabel);

		getContentPane().setLayout(null);
		if (img == null) {
			lblNewLabel.setIcon(new ImageIcon(AffichagePrincipale.class.getResource(vehicule.getChemin_image())));
		}else {
			lblNewLabel.setIcon(new ImageIcon(img));

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
