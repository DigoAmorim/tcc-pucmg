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
	 * M�todo que retorna a retorna todos os instrutores da lista.
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
	 * M�todo que recupera da lista um instrutor passado como par�metro de busca.
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
	 * M�todo que exclui da lista um instrutor passado como par�metro.
	 */
	public void excluirObjeto(Instrutor instrutorModificado) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// In�cia a transa��o
			transaction = session.beginTransaction();
			// Atualiza o cliente passado por par�metro
			session.delete(instrutorModificado);
			// Efetua o commit no DB
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que altera um instrutor passado como par�metro.
	 */
	public void alterarObjeto(Instrutor instrutorModificado) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// In�cia a transa��o
			transaction = session.beginTransaction();
			// Atualiza o cliente passado por par�metro
			session.update(instrutorModificado);
			// Efetua o commit no DB
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que adicionar um instrutor passado como par�metro a uma lista de
	 * instrutores.
	 */
	public void adicionarObjeto(Instrutor instrutoresParam) throws EntidadeDuplicadaException {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// In�cia a transa��o
			transaction = session.beginTransaction();
			// Salva o cliente passado por par�metro
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
