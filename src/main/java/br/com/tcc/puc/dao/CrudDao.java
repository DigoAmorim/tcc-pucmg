package br.com.tcc.puc.dao;

import java.util.ArrayList;

public interface CrudDao<O> {
    
	ArrayList<O> obterTodos();
	
	O  obterObjeto(O o);
	
	void excluirObjeto(O o);
	
    void adicionarObjeto(O o);
    
    void alterarObjeto(O o);
    
}