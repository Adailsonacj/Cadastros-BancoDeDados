package Views;

import java.awt.Component;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import BancoConexões.Servidor;
import BancoConexões.SocketCanais;
import net.proteanit.sql.DbUtils;

public class viewProfessores extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Servidor s = new Servidor();
	SocketCanais comunicacao = new SocketCanais();

	public viewProfessores() throws SQLException, ClassNotFoundException {
		initComponents();
		this.setLocation(250, 100);
		listarTodos();
		this.populaJComboBox();

		this.setVisible(true);
		comunicacao.socketCanais();

	}

	// Lista todos os professores cadastrados
	public void listarTodos() {
		tblUsuarios.setModel(DbUtils.resultSetToTableModel(s.listarTodos()));
	}

	// Pesquisa por nome
	public void pesquisarNome() {
		tblUsuarios.setModel(DbUtils.resultSetToTableModel(s.pesquisarNome(txtPesquisar)));
	}

	// Seleciona item clicado e joga nos seus referidos campos
	public void mostrarSelecionado() {
		int seleciona = tblUsuarios.getSelectedRow();

		txtCodigo.setText(tblUsuarios.getModel().getValueAt(seleciona, 0).toString());
		txtNome.setText(tblUsuarios.getModel().getValueAt(seleciona, 1).toString());
		txtSobrenome.setText(tblUsuarios.getModel().getValueAt(seleciona, 2).toString());
		cmbUniversidades.setSelectedItem(tblUsuarios.getModel().getValueAt(seleciona, 3));
	}

	// Limpa todos os campos ao padrão default
	public void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtSobrenome.setText("");
		cmbUniversidades.setSelectedIndex(0);
		txtPesquisar.setText("");
	}

	// Popula a JComboBox com informacoes das siglas das universidades
	public void populaJComboBox() {
		s.populaJComboBox(cmbUniversidades);
	}

	private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			comunicacao.canalSaida().writeUTF("cadastrarProfessor");
			comunicacao.canalSaida().writeUTF(txtNome.getText());
			comunicacao.canalSaida().writeUTF(txtSobrenome.getText());
		} catch (Exception e) {
		}
		listarTodos();
		limparCampos();
	}

	private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {
		pesquisarNome();
	}

	private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
		mostrarSelecionado();
	}

	private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {
		limparCampos();
		listarTodos();
	}

	private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
		// excluirProfessorUniversidade();
		// excluirProfessor();

		try {
			comunicacao.canalSaida().writeUTF("excluirProfessor");
			comunicacao.canalSaida().writeInt(Integer.parseInt(txtCodigo.getText()));
		} catch (Exception e) {
		}
		listarTodos();
		limparCampos();
	}

	private void btnAdmitirActionPerformed(java.awt.event.ActionEvent evt) {
		// atribuirProfessorUniversidade();

		try {
			comunicacao.canalSaida().writeUTF("admitir");
			comunicacao.canalSaida().writeInt(Integer.parseInt(txtCodigo.getText()));
			comunicacao.canalSaida().writeUTF(cmbUniversidades.getSelectedItem().toString());
		} catch (Exception e) {
		}
		listarTodos();
		limparCampos();
	}

	private void btnDemitirActionPerformed(java.awt.event.ActionEvent evt) {
		// retirarProfessorUniversidade();

		try {
			comunicacao.canalSaida().writeUTF("demitir");
			comunicacao.canalSaida().writeInt(Integer.parseInt(txtCodigo.getText()));
			comunicacao.canalSaida().writeUTF(cmbUniversidades.getSelectedItem().toString());
		} catch (Exception e) {
		}
		listarTodos();
		limparCampos();
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		tblUsuarios = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		txtCodigo = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		txtNome = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		txtSobrenome = new javax.swing.JTextField();
		btnCadastrar = new javax.swing.JButton();
		btnExcluir = new javax.swing.JButton();
		btnLimpar = new javax.swing.JButton();
		txtPesquisar = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		btnAdmitir = new javax.swing.JButton();
		btnDemitir = new javax.swing.JButton();
		cmbUniversidades = new javax.swing.JComboBox<>();

		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Professores");

		tblUsuarios
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblUsuariosMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tblUsuarios);

		jLabel1.setText("Codigo:");

		txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
		txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtCodigo.setEnabled(false);

		jLabel2.setText("Nome:");

		jLabel3.setText("Sobrenome:");

		btnCadastrar.setText("Cadastrar");
		btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCadastrarActionPerformed(evt);
			}
		});

		btnExcluir.setText("Excluir");
		btnExcluir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExcluirActionPerformed(evt);
			}
		});

		btnLimpar.setText("Limpar");
		btnLimpar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLimparActionPerformed(evt);
			}
		});

		txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtPesquisarKeyReleased(evt);
			}
		});

		jLabel4.setText("Buscar");

		jLabel5.setText("Instituição:");

		btnAdmitir.setText("Admitir");
		btnAdmitir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdmitirActionPerformed(evt);
			}
		});

		btnDemitir.setText("Demitir");
		btnDemitir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDemitirActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(6)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 767, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(74).addGroup(layout
								.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
								.addGroup(layout.createSequentialGroup().addGap(97).addComponent(txtCodigo,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addGroup(
												layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel5)
														.addComponent(jLabel2))
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(17)
														.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 201,
																GroupLayout.PREFERRED_SIZE)
														.addGap(26).addComponent(jLabel3)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(txtSobrenome, GroupLayout.PREFERRED_SIZE, 199,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup().addGap(18).addComponent(
														cmbUniversidades, GroupLayout.PREFERRED_SIZE, 199,
														GroupLayout.PREFERRED_SIZE))))
								.addGroup(layout.createSequentialGroup()
										.addComponent(btnCadastrar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnAdmitir, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnDemitir, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))))
				.addContainerGap())
				.addGroup(layout.createSequentialGroup().addContainerGap(384, Short.MAX_VALUE)
						.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(jLabel4).addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(20)
												.addGroup(layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
												.addGap(18)
												.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 226,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE,
																28, GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 20,
																GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtSobrenome, GroupLayout.PREFERRED_SIZE, 28,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 20,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(layout.createSequentialGroup().addContainerGap(356, Short.MAX_VALUE)
												.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 28,
														GroupLayout.PREFERRED_SIZE)))
								.addGap(25)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel5)
										.addComponent(cmbUniversidades, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(26)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnCadastrar)
										.addComponent(btnExcluir).addComponent(btnLimpar).addComponent(btnAdmitir)
										.addComponent(btnDemitir))
								.addContainerGap()));
		layout.linkSize(SwingConstants.VERTICAL, new Component[] { btnCadastrar, btnExcluir, btnLimpar });
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JButton btnAdmitir;
	private javax.swing.JButton btnCadastrar;
	private javax.swing.JButton btnExcluir;
	private javax.swing.JButton btnLimpar;
	private javax.swing.JButton btnDemitir;
	private javax.swing.JComboBox<String> cmbUniversidades;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tblUsuarios;
	private javax.swing.JTextField txtCodigo;
	private javax.swing.JTextField txtNome;
	private javax.swing.JTextField txtPesquisar;
	private javax.swing.JTextField txtSobrenome;
	// End of variables declaration
}
