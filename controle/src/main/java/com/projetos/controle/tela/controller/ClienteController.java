package com.projetos.controle.tela.controller;


/**
 * 
 * @author Rafael
 */
@Deprecated
/*@Controller*/
/*@Lazy*/
public class ClienteController /*extends BaseController<Cliente>*/ {/*

	@Autowired
	private ClienteService clienteService;

	@FXML
	@FiltroTela(campo = "firma", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroFirma;
	
	@FXML
	@FiltroTela(campo = "logradouro.cidade", tipo = TipoFiltro.STRING, comparador = Comparador.CONTAINS_IGNORE_CASE)
	private TextField filtroCidade;
	
	@FXML
	@FiltroTela(campo = "ativo", tipo = TipoFiltro.LIST, comparador = Comparador.EQUALS)
	private ChoiceBox<ItemCombo<Boolean>> filtroAtivo;

	@FXML
	@Coluna(bean = "firma")
	private TableColumn<Cliente, String> colunaFirma;
	
	@FXML
	@Coluna(bean = "logradouro.endereco")
	private TableColumn<Cliente, String> colunaEndereco;
	
	@FXML
	@Coluna(bean = "logradouro.numero")
	private TableColumn<Cliente, Integer> colunaNumero;
	
	@FXML
	@Coluna(bean = "logradouro.bairro")
	private TableColumn<Cliente, String> colunaBairro;
	
	@FXML
	@Coluna(bean = "logradouro.cep")
	private TableColumn<Cliente, String> colunaCep;

	@FXML
	@CampoTela(bean = "firma")
	private TextField firma;
	
	@FXML
	@CampoTela(bean = "logradouro.endereco")
	private TextField endereco;
	
	@FXML
	@CampoTela(bean = "logradouro.numero")
	private TextField numero;
	
	@FXML
	@CampoTela(bean = "logradouro.bairro")
	private TextField bairro;
	
	@FXML
	@CampoTela(bean = "logradouro.cep")
	private TextField cep;
	
	@FXML
	@CampoTela(bean = "logradouro.estado")
	private TextField estado;
	
	@FXML
	@CampoTela(bean = "logradouro.cidade")
	private TextField cidade;
	
	@FXML
	@CampoTela(bean = "logradouro.ddd")
	private TextField ddd;
	
	@FXML
	@CampoTela(bean = "logradouro.telefone")
	private TextField telefone;
	
	@FXML
	@CampoTela(bean = "cnpj")
	private TextField cnpj;
	
	@FXML
	@CampoTela(bean = "inscricao")
	private TextField inscricaoEstadual;
	
	@FXML
	@CampoTela(bean = "comprador")
	private TextField comprador;
	
	@FXML
	@CampoTela(bean = "logradouro.email")
	private TextField email;
	
	@FXML
	@CampoTela(bean = "ativo")
	private RadioButton ativo;

	@FXML
	@CampoTela(bean = "inativo")
	private RadioButton inativo;

	@FXML
	private TableView<Cliente> tabela;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		super.initialize(url, resource);
		List<ItemCombo<Boolean>> lista = new ArrayList<>();
		ItemCombo<Boolean> ativo = new ItemCombo<>("ATIVO", true);
		ItemCombo<Boolean> naoAtivo = new ItemCombo<>("NÃO ATIVO", false);
		ItemCombo<Boolean> todos = new ItemCombo<>("TODOS", null);
		lista.add(ativo);
		lista.add(naoAtivo);
		lista.add(todos);
		
		ObservableList<ItemCombo<Boolean>> itens = FXCollections.observableArrayList(lista);
		filtroAtivo.setItems(itens);
	}

	@Override
	public void exibirTelaCadastro() {
		entidadeForm = new Cliente();
		telaPrincipalController.exibirTelaClienteCadastro();
	}

	@Override
	public void exibirTelaLista() {
		telaPrincipalController.exibirTelaClienteLista();
	}
	
	@Override
	protected EntidadeService<Cliente> getEntidadeService() {
		return clienteService;
	}

	public TextField getFirma() {
		return firma;
	}

	public void setFirma(TextField firma) {
		this.firma = firma;
	}

	public TextField getEndereco() {
		return endereco;
	}

	public void setEndereco(TextField endereco) {
		this.endereco = endereco;
	}

	public TextField getNumero() {
		return numero;
	}

	public void setNumero(TextField numero) {
		this.numero = numero;
	}

	public TextField getBairro() {
		return bairro;
	}

	public void setBairro(TextField bairro) {
		this.bairro = bairro;
	}

	public TextField getCep() {
		return cep;
	}

	public void setCep(TextField cep) {
		this.cep = cep;
	}

	public TextField getEstado() {
		return estado;
	}

	public void setEstado(TextField estado) {
		this.estado = estado;
	}

	public TextField getCidade() {
		return cidade;
	}

	public void setCidade(TextField cidade) {
		this.cidade = cidade;
	}

	public TextField getDdd() {
		return ddd;
	}

	public void setDdd(TextField ddd) {
		this.ddd = ddd;
	}

	public TextField getTelefone() {
		return telefone;
	}

	public void setTelefone(TextField telefone) {
		this.telefone = telefone;
	}

	public TextField getCnpj() {
		return cnpj;
	}

	public void setCnpj(TextField cnpj) {
		this.cnpj = cnpj;
	}

	public TextField getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(TextField inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public TextField getComprador() {
		return comprador;
	}

	public void setComprador(TextField comprador) {
		this.comprador = comprador;
	}

	public TextField getEmail() {
		return email;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public RadioButton getAtivo() {
		return ativo;
	}

	public void setAtivo(RadioButton ativo) {
		this.ativo = ativo;
	}

	public TableColumn<Cliente, String> getColunaFirma() {
		return colunaFirma;
	}

	public void setColunaFirma(TableColumn<Cliente, String> colunaFirma) {
		this.colunaFirma = colunaFirma;
	}

	public TableColumn<Cliente, String> getColunaEndereco() {
		return colunaEndereco;
	}

	public void setColunaEndereco(TableColumn<Cliente, String> colunaEndereco) {
		this.colunaEndereco = colunaEndereco;
	}

	public TableColumn<Cliente, Integer> getColunaNumero() {
		return colunaNumero;
	}

	public void setColunaNumero(TableColumn<Cliente, Integer> colunaNumero) {
		this.colunaNumero = colunaNumero;
	}

	public TableColumn<Cliente, String> getColunaBairro() {
		return colunaBairro;
	}

	public void setColunaBairro(TableColumn<Cliente, String> colunaBairro) {
		this.colunaBairro = colunaBairro;
	}

	public TableColumn<Cliente, String> getColunaCep() {
		return colunaCep;
	}

	public void setColunaCep(TableColumn<Cliente, String> colunaCep) {
		this.colunaCep = colunaCep;
	}

	public RadioButton getInativo() {
		return inativo;
	}

	public void setInativo(RadioButton inativo) {
		this.inativo = inativo;
	}

	@Override
	public TableView<Cliente> getTabela() {
		return tabela;
	}

*/}