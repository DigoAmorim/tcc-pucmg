package br.com.tcc.puc.dao;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.tcc.puc.model.Instrutor;

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidades CRUD
 *         de Instrutor.
 * 
 */
public class MockInstrutorDao implements CrudDao<Instrutor> {

	private ArrayList<Instrutor> instrutores = null;

	/**
	 * Construtor do DAO
	 */
	public MockInstrutorDao() {
		instrutores = new ArrayList<Instrutor>();
		popularInstrutores();

	}

	/**
	 * M�todo que retorna a retorna todos os instrutores da lista.
	 */
	public ArrayList<Instrutor> obterTodos() {
		return instrutores;
	}

	/**
	 * M�todo que recupera da lista um instrutor passado como par�metro de busca.
	 */
	public Instrutor obterObjeto(Instrutor inst) {
		Instrutor clienteObj = null;
		for (Iterator<Instrutor> iterator = instrutores.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if (clienteObj.getCpf().equals(inst.getCpf())) {
				return clienteObj;
			}
		}
		return null;
	}

	/**
	 * M�todo que exclui da lista um instrutor passado como par�metro.
	 */
	public void excluirObjeto(Instrutor instrutorModificado) {
		Instrutor instrutorObj = null;
		for (Iterator<Instrutor> iterator = instrutores.iterator(); iterator.hasNext();) {
			instrutorObj = iterator.next();
			if (instrutorObj.getCpf().equals(instrutorModificado.getCpf())) {
				iterator.remove();
			}
		}
	}

	/**
	 * M�todo que altera um instrutor passado como par�metro.
	 */
	public void alterarObjeto(Instrutor instrutorModificado) {
		Instrutor instrutorObj;
		for (Iterator<Instrutor> iterator = instrutores.iterator(); iterator.hasNext();) {
			instrutorObj = iterator.next();
			if (instrutorObj.getCpf().equals(instrutorModificado.getCpf())) {
				instrutorObj.setNome(instrutorModificado.getNome());
				instrutorObj.setRg(instrutorModificado.getRg());
			}
		}
	}

	/**
	 * M�todo que adicionar um instrutor passado como par�metro a uma lista de
	 * instrutores.
	 */
	public void adicionarObjeto(Instrutor instrutoresParam) {
		instrutores.add(instrutoresParam);
	}

	/**
	 * M�todo que popula a lista com instrutores.
	 */
	private void popularInstrutores() {
		Instrutor i = new Instrutor();
		i.setNome("Rodrigo Amorim");
		i.setCpf("023.905.665-15");
		i.setRg("451.232.70-04");
//		i.setTpAtividade(new String[] {"01","02"});
		i.setChkAulaGrupo(true);
		i.setChkAulaMuscu(true);
		instrutores.add(i);
		i = new Instrutor();
		i.setNome("Jo�o da Silva");
		i.setCpf("443.105.365-15");
		i.setRg("582.200.40-63");
//		i.setTpAtividade(new String[] { "01" });
		i.setChkAulaGrupo(true);
		instrutores.add(i);
		i = new Instrutor();
		i.setNome("Maria do Ros�rio");
		i.setCpf("223.875.332-65");
		i.setRg("332.001.55-70");
//		i.setTpAtividade(new String[] { "02" });
		i.setChkAulaMuscu(true);
		instrutores.add(i);
	}
}
