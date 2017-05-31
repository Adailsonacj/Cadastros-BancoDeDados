package Views;
import java.sql.SQLException;
import BancoConexões.Servidor;
import BancoConexões.SocketCanais;
import net.proteanit.sql.DbUtils;

public class viewUniversidades extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	Servidor s = new Servidor();
	SocketCanais conexao = new SocketCanais();


	public viewUniversidades() throws SQLException, ClassNotFoundException {
		initComponents();
		this.setLocation(350, 100);
		listarTodosUniversidades();
		conexao.socketCanais();
	}

	public void listarTodosUniversidades() {
		tblUniversidades.setModel(DbUtils.resultSetToTableModel(s.listarTodosUniversidades()));
	}

	public void pesquisarUniversidades() {

		tblUniversidades.setModel(DbUtils.resultSetToTableModel(s.pesquisarUniversidades(txtPesquisar)));

	}

	public void mostrarSelecionado() {
		int seleciona = tblUniversidades.getSelectedRow();

		txtSigla.setText(tblUniversidades.getModel().getValueAt(seleciona, 0).toString());
		txtNome.setText(tblUniversidades.getModel().getValueAt(seleciona, 1).toString());
		cmbEstados.setSelectedItem(tblUniversidades.getModel().getValueAt(seleciona, 2).toString());
	}

	public void limparCampos() {
		txtSigla.setText("");
		txtNome.setText("");
		cmbEstados.setSelectedItem("AC");
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		tblUniversidades = new javax.swing.JTable();
		jLabel2 = new javax.swing.JLabel();
		txtSigla = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		txtNome = new javax.swing.JTextField();
		btnCadastrar = new javax.swing.JButton();
		btnExcluir = new javax.swing.JButton();
		btnLimpar = new javax.swing.JButton();
		txtPesquisar = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		cmbEstados = new javax.swing.JComboBox<>();

		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Universidades");

		tblUniversidades
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		tblUniversidades.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblUniversidadesMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tblUniversidades);

		jLabel2.setText("Sigla:");

		jLabel3.setText("Nome:");

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

		jLabel5.setText("Estado:");

		cmbEstados.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel2)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 77,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 257,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel5)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(cmbEstados, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 103,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 323,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel4).addContainerGap()));

		layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { btnCadastrar, btnExcluir, btnLimpar });

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(txtPesquisar, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
				.addGap(18, 18, 18)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(21, 21, 21)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING).addComponent(
												cmbEstados, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 20,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtSigla, javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE, 28,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 28,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 20,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
				.addGap(35, 35, 35)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnCadastrar).addComponent(btnExcluir).addComponent(btnLimpar))
				.addContainerGap(15, Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { btnCadastrar, btnExcluir, btnLimpar });

		pack();
	}// </editor-fold>

	private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			conexao.canalSaida().writeUTF("cadastrarUniversidades");
			conexao.canalSaida().writeUTF(txtSigla.getText());
			conexao.canalSaida().writeUTF(txtNome.getText());
			conexao.canalSaida().writeUTF(cmbEstados.getSelectedItem().toString());
		} catch (Exception e) {
		}
		listarTodosUniversidades();
		limparCampos();
	}

	private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {
		pesquisarUniversidades();
	}

	private void tblUniversidadesMouseClicked(java.awt.event.MouseEvent evt) {
		mostrarSelecionado();
	}

	private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {
		limparCampos();
		listarTodosUniversidades();
	}

	private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			conexao.canalSaida().writeUTF("excluirUniversidade");
			conexao.canalSaida().writeUTF(txtSigla.getText());
		} catch (Exception e) {
		}
		listarTodosUniversidades();
		limparCampos();
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnCadastrar;
	private javax.swing.JButton btnExcluir;
	private javax.swing.JButton btnLimpar;
	private javax.swing.JComboBox<String> cmbEstados;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tblUniversidades;
	private javax.swing.JTextField txtNome;
	private javax.swing.JTextField txtPesquisar;
	private javax.swing.JTextField txtSigla;
	// End of variables declaration
}
