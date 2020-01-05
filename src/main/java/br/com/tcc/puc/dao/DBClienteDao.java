package br.com.tcc.puc.dao;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tcc.puc.exception.EntidadeDuplicadaException;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.util.HibernateUtil;

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidades CRUD
 *         de Cliente.
 * 
 */
public class DBClienteDao implements CrudDao<Cliente> {

	private ArrayList<Cliente> clientes = null;

	/**
	 * Construtor do DAO
	 */
	public DBClienteDao() {
		clientes = new ArrayList<Cliente>();
	}

	/**
	 * M�todo que retorna a retorna todos os clientes da lista.
	 */
	public ArrayList<Cliente> obterTodos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			clientes = (ArrayList<Cliente>) session.createQuery("from Cliente", Cliente.class).list();
			// Fecha a sess�o
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientes;
	}

	/**
	 * M�todo que recupera da lista um cliente passado como par�metro de busca.
	 */
	public Cliente obterObjeto(Cliente cli) {
		Cliente clienteObj = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Cliente> cr = cb.createQuery(Cliente.class);
			Root<Cliente> root = cr.from(Cliente.class);
			cr.select(root).where(cb.equal(root.get("cpf"), cli.getCpf()));
			clienteObj = session.createQuery(cr).getSingleResult();
			// Fecha a sess�o
			session.close();
			return clienteObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * M�todo que exclui da lista um cliente passado como par�metro.
	 */
	public void excluirObjeto(Cliente clienteModificado) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// In�cia a transa��o
			transaction = session.beginTransaction();
			// Atualiza o cliente passado por par�metro
			session.delete(clienteModificado);
			// Efetua o commit no DB
			transaction.commit();
			// Fecha a sess�o
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que altera um cliente passado como par�metro.
	 */
	public void alterarObjeto(Cliente clienteModificado) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// In�cia a transa��o
			transaction = session.beginTransaction();
			// Atualiza o cliente passado por par�metro
			session.update(clienteModificado);
			// Efetua o commit no DB
			transaction.commit();
			// Fecha a sess�o
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que adicionar um cliente passado como par�metro a uma lista de
	 * clientes.
	 */
	public void adicionarObjeto(Cliente clientesParam) throws EntidadeDuplicadaException {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// In�cia a transa��o
			transaction = session.beginTransaction();
			// Salva o cliente passado por par�metro
			session.save(clientesParam);
			// Efetua o commit no DB
			transaction.commit();
			// Fecha a sess�o
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new EntidadeDuplicadaException(e.getMessage());
		}
	}

}
