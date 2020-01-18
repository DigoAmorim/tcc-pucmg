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
 *         Classe que implementará os serviços para manipulação do pagamento dos
 *         clientes. As regras de negócio ficarão nessa classe.
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
	 * @param tipoAcesso campo que indicará se o método chamará o Mock ou o banco de
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
	 * Método que criará o pagamento
	 * 
	 * @param pagamento - Objeto que contém dados do pagamento que será criado
	 * @throws PagAnteriorVencException - Caso a data do pagamento seja anterior a
	 *                                  próxima data de vencimento, essa exceção é
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
	 * Método que retorna todos os pagamentos de um determinado cliente
	 * 
	 * @param cli - Objeto que contém os campos que identificam o cliente para o
	 *            qual os pagamentos serão apresentados.
	 * @return Retorna a lista de pagamentos de um determinado cliente.
	 */
	public ArrayList<Pagamento> obterPagamentos(Cliente cli) {
		return pagamentoDao.obterObjeto(cli);
	}

	/**
	 * Método que analisa, a partir dos pagamentos efetuados, qual é a próxima data
	 * de vencimento e o status do cliente
	 * 
	 * @param Cli -> Cliente a ser analisado pelo método
	 * @return Retorna o mesmo objeto com as informações de data de vencimento,
	 *         descriçã do tipo do plano e status atualizadas.
	 */
	public Cliente obterInfoFinanceira(Cliente cli) {
		// Inicialização das variáveis
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calVencimento = Calendar.getInstance();
		Calendar calHoje = Calendar.getInstance();
		Date dtVencimento;

		if (!Objects.isNull(cli)) {
			// Preenche ainformação do tipo do plano
			instanciarUtilidadeService();
			cli.setDescTpPlano(utilidadeService.obterDescTpPlano(cli.getTpPlano()));
			// Obtem próxima data de vencimento
			dtVencimento = obtemProxDataVencimento(cli);
			// Verificar se já houve algum pagamento. Caso não tenha havido, considerar o
			// cliente como: Pendente 1° parcela
			if (dtVencimento == null) {
				cli.setSitCliente(Utilidade.getMessage("Pendente1Pagamento", null));
				return cli;
			} else {
				// Ajusta calendário com a data de hoje
				calHoje.setTime(new Date(System.currentTimeMillis()));
				// Ajusta calendário com a data de vencimento
				calVencimento.setTime(dtVencimento);
				// preenche o cliente com a última data de vencimento
				cli.setProxDataVencimento(dateFormat.format(dtVencimento));
				// Verifica o status financeiro do aluno. Se for hoje a data de vencimento ou
				// superior, o cliente está adimplente
				if (mesmaData(calHoje, calVencimento)
						|| (calVencimento.getTime().after(new Date(System.currentTimeMillis())))) {
					cli.setSitCliente(Utilidade.getMessage("Adimplente", null));
					// Caso contrário ele está inadimplente
				} else {
					cli.setSitCliente(Utilidade.getMessage("Inadimplente", null));
				}
			}
		}
		return cli;
	}

	/**
	 * Obtem próxima data de vencimento do plano do cliente
	 * 
	 * @param Cli -> Cliente a ser analisado pelo método
	 * @return Retorna um objeto do tipo Date com a data de vencimento do plano
	 */
	public Date obtemProxDataVencimento(Cliente cli) {
		Calendar calUltPagamento = Calendar.getInstance();
		Pagamento ultPagamento;

		// Obtem o último pagamento do cliente
		ultPagamento = obtemUltPagamento(cli);
		if (ultPagamento != null) {
			// Seta o dia do último pagamento no calendário
			calUltPagamento.setTime(ultPagamento.getDtPagamento());
			// Soma à data de vencimento o código do plano do cliente.
			calUltPagamento.add(Calendar.DATE, Integer.parseInt(cli.getTpPlano()));
			return calUltPagamento.getTime();
		}
		return null;
	}

	/**
	 * Méotodo que retorna o último pagamento para um determinado cliente
	 * 
	 * @param cli - Objeto que contém dados do cliente para qual se deja obter o
	 *            último pagamento.
	 * @return Retorna o objeto pagamento contem informações do último pagamento
	 *         feito pelo cliente passado como parâmetro.
	 */
	public Pagamento obtemUltPagamento(Cliente cli) {
		// Inicialização das variáveis
		ArrayList<Pagamento> pagamentosCliente;

		// Obtem os pagamentos efetuado pelo cliente
		pagamentosCliente = obterPagamentos(cli);
		if (!pagamentosCliente.isEmpty()) {
			// Não ordenamos mais pois a ordenação está sendo feita no BD.
			//Collections.sort(pagamentosCliente);
			// Pega o último pagamento efetuado
			return pagamentosCliente.get(0);
		}
		return null;
	}

	/**
	 * Méotdo que verifica se duas datas recebidas correspondem ao mesmo dia.
	 * 
	 * @param c1 - Calendário 01 que está configurado para uma determinada data.
	 * @param c2 - Calendário 02 que está configurado para uma determinada data.
	 * @return Retorna true se as datas forem as mesmas e false se as datas forem
	 *         diferentes.
	 */
	private boolean mesmaData(Calendar c1, Calendar c2) {
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * Méotodo para instanciar o serviço de utilidades da aplicação.
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
