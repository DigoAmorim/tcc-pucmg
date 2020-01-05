package br.com.tcc.puc.dao;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;
import br.com.tcc.puc.util.HibernateUtil;

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidades de
 *         gerenciamento do pagamento dos clientes.
 * 
 */
public class DBPagamentoDao {

	private ArrayList<Pagamento> pagamentos = null;

	/**
	 * Construtor do DAO
	 */
	public DBPagamentoDao() {
		pagamentos = new ArrayList<Pagamento>();

	}

	/**
	 * Método que retorna a retorna todos os clientes da lista.
	 */
	public ArrayList<Pagamento> obterTodos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			pagamentos = (ArrayList<Pagamento>) session.createQuery("from Pagamento", Pagamento.class).list();
			// Fecha a sessão
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagamentos;
	}

	/**
	 * Método que recupera da lista de pagamentos de um cliente passado como
	 * parâmetro de busca.
	 */
	public ArrayList<Pagamento> obterObjeto(Cliente cli) {
		ArrayList<Pagamento> pagamentosDoCliente = new ArrayList<Pagamento>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Pagamento> cr = cb.createQuery(Pagamento.class);
			Root<Pagamento> root = cr.from(Pagamento.class);
			cr.select(root).where(cb.equal(root.join("cliente").get("cpf"), cli.getCpf()));
			cr.orderBy(cb.desc(root.get("dtPagamento")));
			pagamentosDoCliente = (ArrayList<Pagamento>) session.createQuery(cr).getResultList();
			// Fecha a sessão
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagamentosDoCliente;
	}

	/**
	 * Método que adicionar um cliente passado como parâmetro a uma lista de
	 * clientes.
	 */
	public void adicionarObjeto(Pagamento pagamentoParam) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inícia a transação
			transaction = session.beginTransaction();
			// Salva o cliente passado por parâmetro
			session.save(pagamentoParam);
			// Efetua o commit no DB
			transaction.commit();
			// Fecha a sessão
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

}
