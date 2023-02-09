package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.UserDao;
import controller.VehiculeDao;
import model.Vehicule;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 1500, 1050);
		getContentPane().setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(6, 6, 1488, 95);
		getContentPane().add(header);
		header.setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/icones/login.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDao.currentUser == null) {
					Inscription login = new Inscription();
					login.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(1389, 18, 76, 60);
		header.add(btnNewButton);

		JPanel container = new JPanel();
		container.setBounds(6, 103, 1488, 785);
		getContentPane().add(container);
		container.setLayout(null);

		container.removeAll();
		AffichagePrincipale affichagePrincipale = new AffichagePrincipale();
		affichagePrincipale.setBounds(0, 0, 1488, 791);
		container.add(affichagePrincipale);

		JPanel footer = new JPanel();
		footer.setBounds(6, 900, 1480, 52);
		getContentPane().add(footer);
		footer.setLayout(null);
		container.repaint();
		container.revalidate();
	}
}
