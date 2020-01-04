package br.com.tcc.puc.dao;

import java.util.ArrayList;

import br.com.tcc.puc.exception.EntidadeDuplicadaException;

public interface CrudDao<O> {
    
	ArrayList<O> obterTodos();
	
	O  obterObjeto(O o);
	
	void excluirObjeto(O o);
	
    void adicionarObjeto(O o) throws EntidadeDuplicadaException;
    
    void alterarObjeto(O o);
    
}