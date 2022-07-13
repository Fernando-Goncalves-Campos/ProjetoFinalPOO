public class Sala {
	//Dados da sessão
	public double preco = 10.00;
	public int horario;
	
	//Organização dos assentos no cinema
	public int largura = 18;
	public int profundidade = 13;
	public boolean[][] ocupados;

	//Determina os corredores
	public int nCorredores; //Nº de corredores + 1
	public int[] corredores; //Quantidade de cadeiras entre os corredores e as paredes do cinema
	
	//Cria a sala com valores pré-determinados
	public Sala() {
		// comentario de teste
		this.ocupados = new boolean[this.profundidade][this.largura];
		for(int i = 0; i < this.profundidade; ++i) {
			for(int j = 0; j < this.largura; ++j) {
				this.ocupados[i][j] = false;
			}
		}
		nCorredores = 1;
		corredores = new int[] {largura};
	}
	
	//Cria a sala com dimensões desejadas
	public Sala(int largura, int profundidade) {
		this.largura = largura;
		this.profundidade = profundidade;
		
		this.ocupados = new boolean[profundidade][largura];
		for(int i = 0; i < profundidade; ++i) {
			for(int j = 0; j < largura; ++j) {
				this.ocupados[i][j] = false;
			}
		}
		nCorredores = 1;
		corredores = new int[] {largura};
	}
	
	//Cria a sala com assentos já ocupados
	public Sala(int nOcupados, int[] ocupados) {
		this.ocupados = new boolean[this.profundidade][this.largura];
		for(int i = 0; i < this.profundidade; ++i) {
			for(int j = 0; j < this.largura; ++j) {
				this.ocupados[i][j] = false;
			}
		}
		
		for(int i = 0; i < nOcupados; ++i) {
			this.ocupados[ocupados[i]/this.largura][ocupados[i]%this.largura] = true;
		}
		nCorredores = 1;
		corredores = new int[] {largura};
	}
	
	//Cria a sala com assentos já ocupados e dimensões desejadas
	public Sala(int largura, int profundidade, int nOcupados, int[] ocupados) {
		this.largura = largura;
		this.profundidade = profundidade;
		
		this.ocupados = new boolean[profundidade][largura];
		for(int i = 0; i < profundidade; ++i) {
			for(int j = 0; j < largura; ++j) {
				this.ocupados[i][j] = false;
			}
		}
		
		for(int i = 0; i < nOcupados; ++i) {
			this.ocupados[ocupados[i]/largura][ocupados[i]%largura] = true;
		}
		nCorredores = 1;
		corredores = new int[] {largura};
	}
	
	//Determina as posições dos corredores
	public void criaCorredores(int _nCorredores, int[] _corredores) {
		if(_nCorredores <= largura+1) {
			int total = 0;
			for(int i = 0; i < _nCorredores; ++i) {
				total += _corredores[i];
			}
			if(total == largura) {
				nCorredores = _nCorredores;
				corredores = _corredores;
			}
		}
	}
	
	//Finaliza a compra e atualiza os assentos ocupados da sala
	public void comprar(int nComprados, int[] comprados) {
		for(int i = 0; i < nComprados; ++i) {
			this.ocupados[comprados[i]/this.largura][comprados[i]%this.largura] = true;
		}
	}
}