package br.com.tcc.puc.service;

import java.util.ArrayList;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pessoa;


/**
 * @author Rodrigo
 *
 *Interface que servir� para montar os servi�os de Pessoa (Cliente, Instrutor etc.)
 */
public interface IPessoaService {
	
	void criar(Pessoa pessoa);
	
	void editar(Pessoa pessoa);
	
	void remover(Pessoa pessoa);
	
	Pessoa obter(Object id);
	
	ArrayList<Cliente> obterTodos();
	
	ArrayList<Pessoa> obterPessoas(Pessoa pessoa);

}
