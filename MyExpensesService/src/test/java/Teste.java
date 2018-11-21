import java.util.List;

import com.br.myexpenses.controle.LancamentoControle;
import com.br.myexpenses.ws.rest.request.LancamentoRequest;
import com.br.myexpenses.ws.rest.response.LancamentoResponse;

public class Teste {

	public static void main(String[] args) {
		LancamentoRequest request = new LancamentoRequest();
		request.setIdUsuario(((Integer) 1).longValue());
		
		LancamentoControle lc = new LancamentoControle();
		List<LancamentoResponse> list = lc.getLancamentos(request);
	}

}
