/**
 * 
 */
package br.com.tcc.puc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import br.com.tcc.puc.dao.DBPagamentoDao;
import br.com.tcc.puc.exception.PagAnteriorVencException;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;
import br.com.tcc.puc.util.Utilidade;

/**
 * @author Rodrigo
 *
 *         Classe que implementar� os servi�os para manipula��o do pagamento dos
 *         clientes. As regras de neg�cio ficar�o nessa classe.
 *
 */
public class PagamentoService {

	static final String ACESSO_DADOS = "MOCK";

	static final String ACESSO_DB = "DB";
	
	private UtilidadeService utilidadeService;

	private DBPagamentoDao pagamentoDao;

	/**
	 * Construtor da Classe
	 * 
	 * @param tipoAcesso campo que indicar� se o m�todo chamar� o Mock ou o banco de
	 *                   dados
	 */
	public PagamentoService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
//			pagamentoDao = new MockPagamentoDao();
		} else if (tipoAcesso.equals(ACESSO_DB)) {
			pagamentoDao = new DBPagamentoDao();
		}
	}

	/**
	 * M�todo que criar� o pagamento
	 * 
	 * @param pagamento - Objeto que cont�m dados do pagamento que ser� criado
	 * @throws PagAnteriorVencException - Caso a data do pagamento seja anterior a
	 *                                  pr�xima data de vencimento, essa exce��o �
	 *                                  levantada
	 */
	public void criar(Pagamento pagamento) throws PagAnteriorVencException {
		Date dtVencimento;

		dtVencimento = obtemProxDataVencimento(pagamento.getCliente());
		if (dtVencimento == null) {
			pagamentoDao.adicionarObjeto(pagamento);
		} else {
			Calendar calDtVencimento = Calendar.getInstance();
			Calendar calDtPagamento = Calendar.getInstance();

			calDtVencimento.setTime(dtVencimento);
			calDtPagamento.setTime(pagamento.getDtPagamento());
			if (calDtPagamento.before(calDtVencimento)) {
				throw new PagAnteriorVencException("dtPagamentoAnteriorVencimento");
			} else {
				pagamentoDao.adicionarObjeto(pagamento);
			}
		}
	}

	/**
	 * M�todo que retorna todos os pagamentos de um determinado cliente
	 * 
	 * @param cli - Objeto que cont�m os campos que identificam o cliente para o
	 *            qual os pagamentos ser�o apresentados.
	 * @return Retorna a lista de pagamentos de um determinado cliente.
	 */
	public ArrayList<Pagamento> obterPagamentos(Cliente cli) {
		return pagamentoDao.obterObjeto(cli);
	}

	/**
	 * M�todo que analisa, a partir dos pagamentos efetuados, qual � a pr�xima data
	 * de vencimento e o status do cliente
	 * 
	 * @param Cli -> Cliente a ser analisado pelo m�todo
	 * @return Retorna o mesmo objeto com as informa��es de data de vencimento,
	 *         descri�� do tipo do plano e status atualizadas.
	 */
	public Cliente obterInfoFinanceira(Cliente cli) {
		// Inicializa��o das vari�veis
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calVencimento = Calendar.getInstance();
		Calendar calHoje = Calendar.getInstance();
		Date dtVencimento;

		if (!Objects.isNull(cli)) {
			// Preenche ainforma��o do tipo do plano
			instanciarUtilidadeService();
			cli.setDescTpPlano(utilidadeService.obterDescTpPlano(cli.getTpPlano()));
			// Obtem pr�xima data de vencimento
			dtVencimento = obtemProxDataVencimento(cli);
			// Verificar se j� houve algum pagamento. Caso n�o tenha havido, considerar o
			// cliente como: Pendente 1� parcela
			if (dtVencimento == null) {
				cli.setSitCliente(Utilidade.getMessage("Pendente1Pagamento", null));
				return cli;
			} else {
				// Ajusta calend�rio com a data de hoje
				calHoje.setTime(new Date(System.currentTimeMillis()));
				// Ajusta calend�rio com a data de vencimento
				calVencimento.setTime(dtVencimento);
				// preenche o cliente com a �ltima data de vencimento
				cli.setProxDataVencimento(dateFormat.format(dtVencimento));
				// Verifica o status financeiro do aluno. Se for hoje a data de vencimento ou
				// superior, o cliente est� adimplente
				if (mesmaData(calHoje, calVencimento)
						|| (calVencimento.getTime().after(new Date(System.currentTimeMillis())))) {
					cli.setSitCliente(Utilidade.getMessage("Adimplente", null));
					// Caso contr�rio ele est� inadimplente
				} else {
					cli.setSitCliente(Utilidade.getMessage("Inadimplente", null));
				}
			}
		}
		return cli;
	}

	/**
	 * Obtem pr�xima data de vencimento do plano do cliente
	 * 
	 * @param Cli -> Cliente a ser analisado pelo m�todo
	 * @return Retorna um objeto do tipo Date com a data de vencimento do plano
	 */
	public Date obtemProxDataVencimento(Cliente cli) {
		Calendar calUltPagamento = Calendar.getInstance();
		Pagamento ultPagamento;

		// Obtem o �ltimo pagamento do cliente
		ultPagamento = obtemUltPagamento(cli);
		if (ultPagamento != null) {
			// Seta o dia do �ltimo pagamento no calend�rio
			calUltPagamento.setTime(ultPagamento.getDtPagamento());
			// Soma � data de vencimento o c�digo do plano do cliente.
			calUltPagamento.add(Calendar.DATE, Integer.parseInt(cli.getTpPlano()));
			return calUltPagamento.getTime();
		}
		return null;
	}

	/**
	 * M�otodo que retorna o �ltimo pagamento para um determinado cliente
	 * 
	 * @param cli - Objeto que cont�m dados do cliente para qual se deja obter o
	 *            �ltimo pagamento.
	 * @return Retorna o objeto pagamento contem informa��es do �ltimo pagamento
	 *         feito pelo cliente passado como par�metro.
	 */
	public Pagamento obtemUltPagamento(Cliente cli) {
		// Inicializa��o das vari�veis
		ArrayList<Pagamento> pagamentosCliente;

		// Obtem os pagamentos efetuado pelo cliente
		pagamentosCliente = obterPagamentos(cli);
		if (!pagamentosCliente.isEmpty()) {
			// N�o ordenamos mais pois a ordena��o est� sendo feita no BD.
			//Collections.sort(pagamentosCliente);
			// Pega o �ltimo pagamento efetuado
			return pagamentosCliente.get(0);
		}
		return null;
	}

	/**
	 * M�otdo que verifica se duas datas recebidas correspondem ao mesmo dia.
	 * 
	 * @param c1 - Calend�rio 01 que est� configurado para uma determinada data.
	 * @param c2 - Calend�rio 02 que est� configurado para uma determinada data.
	 * @return Retorna true se as datas forem as mesmas e false se as datas forem
	 *         diferentes.
	 */
	private boolean mesmaData(Calendar c1, Calendar c2) {
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * M�otodo para instanciar o servi�o de utilidades da aplica��o.
	 */
	private void instanciarUtilidadeService() {
		if (utilidadeService == null) {
			utilidadeService = new UtilidadeService();
		}
	}

	public ArrayList<Pagamento> obterTodos() {
		return pagamentoDao.obterTodos();
	}

}
