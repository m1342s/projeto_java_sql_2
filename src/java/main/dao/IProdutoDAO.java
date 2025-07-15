package dao;

import java.util.List;

import domain.Produto;

public interface IProdutoDAO {
	
	public Integer cadastrar(Produto produto) throws Exception;
	
	public Integer atualizar(Produto produto) throws Exception;
	
	public Integer excluir(Produto produto) throws Exception;
	
	public Produto buscar(String produto) throws Exception;
	
	public List<Produto> buscarTodos() throws Exception;

}
