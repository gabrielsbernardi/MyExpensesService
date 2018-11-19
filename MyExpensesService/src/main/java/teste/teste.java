package teste;

import com.br.myexpenses.controle.CategoriaControle;
import com.br.myexpenses.model.Categoria;

public class teste {

	public static void main(String[] args) throws Exception {
		
		CategoriaControle c = new CategoriaControle();
		
		Categoria categoria = new Categoria(1,7,"teste", "teste");
		
		//c.inserir(categoria);
		
		Categoria categoria2 = new Categoria(1,7,"teste alterado2", "teste alterado2");
		
		c.atualizar(categoria2, 7);

	}
}
