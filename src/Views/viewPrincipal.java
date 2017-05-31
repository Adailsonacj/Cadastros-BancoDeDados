package Views;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import BancoConexões.SocketCanais;

public class viewPrincipal extends javax.swing.JFrame {

	SocketCanais conexao = new SocketCanais();
	private static final long serialVersionUID = 1L;

	public viewPrincipal() {
		initComponents();
		this.setExtendedState(MAXIMIZED_BOTH);
		conexao.socketCanais();
}

	private void initComponents() {

		jDesktopPane1 = new javax.swing.JDesktopPane();
		jLabel1 = new javax.swing.JLabel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jmiProfessores = new javax.swing.JMenuItem();
		jmiUniversidades = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem3 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Principal");

		jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(51, 51, 255));
		jLabel1.setText("ACADÊMICA 2.0");

		jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
		jDesktopPane1.setLayout(jDesktopPane1Layout);
		jDesktopPane1Layout.setHorizontalGroup(
				jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
								.addContainerGap(181, Short.MAX_VALUE).addComponent(jLabel1).addContainerGap()));
		jDesktopPane1Layout.setVerticalGroup(
				jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
								.addContainerGap(244, Short.MAX_VALUE).addComponent(jLabel1).addContainerGap()));

		jMenu1.setText("Cadastros");

		jmiProfessores.setAccelerator(
				javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_MASK));
		jmiProfessores.setText("Professores");
		jmiProfessores.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiProfessoresActionPerformed(evt);
			}
		});
		jMenu1.add(jmiProfessores);

		jmiUniversidades.setAccelerator(
				javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_9, java.awt.event.InputEvent.CTRL_MASK));
		jmiUniversidades.setText("Universidades");
		jmiUniversidades.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiUniversidadesActionPerformed(evt);
			}
		});
		jMenu1.add(jmiUniversidades);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Opções");

		jMenuItem3.setAccelerator(
				javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem3.setText("Sair");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem3);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jDesktopPane1));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jDesktopPane1));

		pack();
	}// </editor-fold>

	private void jmiProfessoresActionPerformed(java.awt.event.ActionEvent evt) {

		viewProfessores form = null;
		try {
			form = new viewProfessores();
		} catch (SQLException ex) {
			Logger.getLogger(viewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(viewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}
		form.setVisible(true);
		jDesktopPane1.add(form);
	}

	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void jmiUniversidadesActionPerformed(java.awt.event.ActionEvent evt) {
		viewUniversidades form = null;
		try {
			form = new viewUniversidades();
		} catch (SQLException ex) {
			Logger.getLogger(viewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(viewPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}
		form.setVisible(true);
		jDesktopPane1.add(form);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new viewPrincipal().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JDesktopPane jDesktopPane1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jmiProfessores;
	private javax.swing.JMenuItem jmiUniversidades;
	// End of variables declaration
}
