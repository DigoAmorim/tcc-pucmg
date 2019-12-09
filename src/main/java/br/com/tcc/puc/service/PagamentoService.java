/**
 * 
 */
package br.com.tcc.puc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import br.com.tcc.puc.dao.MockPagamentoDao;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;

/**
 * @author Rodrigo
 *
 *         Classe que implementará os serviços para manipulação do pagamento dos
 *         clientes. As regras de negócio ficarão nessa classe.
 *
 */
public class PagamentoService {

	static final String ACESSO_DADOS = "MOCK";

	private MockPagamentoDao pagamentoDao;

	public PagamentoService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			pagamentoDao = new MockPagamentoDao();
		}
	}

	public void criar(Pagamento pagamento) {
		pagamentoDao.adicionarObjeto(pagamento);
	}

	public ArrayList<Pagamento> obterPagamentos(Cliente cli) {
		return pagamentoDao.obterObjeto(cli);
	}

	public Cliente obterInfoPagamento(Cliente cli) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calPagamento = Calendar.getInstance();
		Pagamento pagEfetuado;
		ArrayList<Pagamento> pagamentosCliente = obterPagamentos(cli);
		Date dataVerificacao = cli.getDtMatricula();
		Calendar calVerificacao = Calendar.getInstance();
		calVerificacao.setTime(dataVerificacao);
		cli.setProxDataVencimento(dateFormat.format(dataVerificacao));

		// Enquanto o dia de verificação for inferior ao dia atual, temos que procurar
		// pelo respectivo pagamento.
		while (!calVerificacao.getTime().after(new Date(System.currentTimeMillis()))) {
			cli.setSitCliente("Inadimplente");
			// Ajusta Calendário coma data de verificação (Inicialmente a data da matrícula)
			// Pega todos os pagamentos já realizados pelo cliente
			if (!pagamentosCliente.isEmpty()) {
				for (Iterator<Pagamento> iterator = pagamentosCliente.iterator(); iterator.hasNext();) {
					pagEfetuado = (Pagamento) iterator.next();
					calPagamento.setTime(pagEfetuado.getDtPagamento());
					// Verifica se houve pagamento para cada uma dos períodos, conforme plano
					// contratado
					if (calVerificacao.get(Calendar.MONTH) == calPagamento.get(Calendar.MONTH)) {
						if (calVerificacao.get(Calendar.YEAR) == calPagamento.get(Calendar.YEAR)) {
							if (cli.getTpPlano().equals("Mensal")) {
								calVerificacao.add(Calendar.DATE, 30);
								cli.setSitCliente("Adimplente");
								cli.setProxDataVencimento(dateFormat.format(calVerificacao.getTime()));
								break;
							} else if (cli.getTpPlano().equals("Anual")) {
								calVerificacao.add(Calendar.DATE, 365);
								cli.setSitCliente("Adimplente");
								cli.setProxDataVencimento(dateFormat.format(calVerificacao.getTime()));
								break;
							}
						}
					}
				}
			} else {
				// Não foi feito até o momento nenhum pagamento
				// Verifica se a data de pagamento é hoje.
				Calendar calHoje = Calendar.getInstance();
				calHoje.setTime(new Date(System.currentTimeMillis()));
				if (mesmaData(calHoje, calVerificacao)) {
					cli.setSitCliente("Adimplente");
				}
				break;
			}
		}
		return cli;
	}
	
	private boolean mesmaData(Calendar c1, Calendar c2) {
	    return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && 
	            c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
	            c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
	}

	public ArrayList<Pagamento> obterTodos() {
		return pagamentoDao.obterTodos();
	}

}
