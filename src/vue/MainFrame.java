package vue;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.UserDao;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 825);
		getContentPane().setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(6, 6, 1488, 72);
		getContentPane().add(header);
		header.setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/icones/login.png")));

		btnNewButton.setBounds(1389, 6, 76, 60);
		header.add(btnNewButton);

		JPanel container = new JPanel();
		container.setBounds(6, 85, 1500, 680);
		getContentPane().add(container);
		container.setLayout(null);

		container.removeAll();
		AffichagePrincipale affichagePrincipale = new AffichagePrincipale();
		affichagePrincipale.setBounds(0, 0, 1488, 737);
		container.add(affichagePrincipale);

		JPanel footer = new JPanel();
		footer.setBounds(6, 770, 1480, 17);
		getContentPane().add(footer);
		footer.setLayout(null);
		container.repaint();
		container.revalidate();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDao.currentUser == null) {
					Inscription login = new Inscription();
					login.setVisible(true);
				} else if (UserDao.currentUser.getEmail().contains("@agence.fr")) {
					if (UpdateCreatVehicule.nbrFenetre == 0) {
						UpdateCreatVehicule upVehi = new UpdateCreatVehicule(affichagePrincipale);
						upVehi.setVisible(true);
						UpdateCreatVehicule.nbrFenetre++;
					}

				} else {
					// gestion utilisateur pour la connection
					int choix = JOptionPane.showOptionDialog(null, "Que souhaitez-vous faire ?",
							"utilisateur avant modification :", JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Continuer", "Modifier/Supprimer mon compte" }, null);
					if (choix == 1) {
						ModificationSuppressionUser modSupUser = new ModificationSuppressionUser();
						modSupUser.setVisible(true);
					}
					
					
				}
			}
		});
	}
}
