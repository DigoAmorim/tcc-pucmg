package br.com.tcc.puc.service;

import java.util.ArrayList;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pessoa;


/**
 * @author Rodrigo
 *
 *Interface que servirá para montar os serviços de Pessoa (Cliente, Instrutor etc.)
 */
public interface IPessoaService {
	
	void criar(Pessoa pessoa);
	
	void editar(Pessoa pessoa);
	
	void remover(Pessoa pessoa);
	
	Pessoa obter(Object id);
	
	ArrayList<Cliente> obterTodos();
	
	ArrayList<Pessoa> obterPessoas(Pessoa pessoa);

}
