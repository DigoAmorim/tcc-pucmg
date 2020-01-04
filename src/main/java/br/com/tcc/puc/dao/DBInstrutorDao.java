package br.com.tcc.puc.dao;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.tcc.puc.exception.EntidadeDuplicadaException;
import br.com.tcc.puc.model.Instrutor;
import br.com.tcc.puc.util.HibernateUtil;

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidades CRUD
 *         de Instrutor.
 * 
 */
public class DBInstrutorDao implements CrudDao<Instrutor> {

	private ArrayList<Instrutor> instrutores = null;

	/**
	 * Construtor do DAO
	 */
	public DBInstrutorDao() {
		instrutores = new ArrayList<Instrutor>();
	}

	/**
	 * Método que retorna a retorna todos os instrutores da lista.
	 */
	public ArrayList<Instrutor> obterTodos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			instrutores = (ArrayList<Instrutor>) session.createQuery("from Instrutor", Instrutor.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return instrutores;
	}

	/**
	 * Método que recupera da lista um instrutor passado como parâmetro de busca.
	 */
	public Instrutor obterObjeto(Instrutor inst) {
		Instrutor instrutorObj = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Instrutor> cr = cb.createQuery(Instrutor.class);
			Root<Instrutor> root = cr.from(Instrutor.class);
			cr.select(root).where(cb.equal(root.get("cpf"), inst.getCpf()));
			instrutorObj = session.createQuery(cr).getSingleResult();
			return instrutorObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que exclui da lista um instrutor passado como parâmetro.
	 */
	public void excluirObjeto(Instrutor instrutorModificado) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inícia a transação
			transaction = session.beginTransaction();
			// Atualiza o cliente passado por parâmetro
			session.delete(instrutorModificado);
			// Efetua o commit no DB
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que altera um instrutor passado como parâmetro.
	 */
	public void alterarObjeto(Instrutor instrutorModificado) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inícia a transação
			transaction = session.beginTransaction();
			// Atualiza o cliente passado por parâmetro
			session.update(instrutorModificado);
			// Efetua o commit no DB
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que adicionar um instrutor passado como parâmetro a uma lista de
	 * instrutores.
	 */
	public void adicionarObjeto(Instrutor instrutoresParam) throws EntidadeDuplicadaException {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Inícia a transação
			transaction = session.beginTransaction();
			// Salva o cliente passado por parâmetro
			session.save(instrutoresParam);
			// Efetua o commit no DB
			transaction.commit();
		} catch (Exception e) {
			throw new EntidadeDuplicadaException(e.getMessage());
		} finally {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

}
