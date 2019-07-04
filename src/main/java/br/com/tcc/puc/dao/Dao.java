package br.com.tcc.puc.dao;

import java.util.ArrayList;

public interface Dao<O> {
    
	ArrayList<O> obterTodos();
	
	O  obterObjeto(O o);
	
	void excluirObjeto(O o);
	
    void adicionarObjeto(O o);
    
    void alterarObjeto(O o);
    
}