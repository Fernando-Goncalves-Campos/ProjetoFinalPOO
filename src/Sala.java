public class Sala {
	//Inicializa as variáveis
	public int preco;
	public int horario;
	public int largura = 18;
	public int profundidade = 10;
	public boolean[][] ocupados;
	public int nComprados = 0;
	public int[] comprados = new int[this.largura * this.profundidade];
	public int nCorredores;
	public int[] corredores;
	
	public Sala() {
		this.ocupados = new boolean[this.profundidade][this.largura];
		for(int i = 0; i < this.profundidade; ++i) {
			for(int j = 0; j < this.largura; ++j) {
				this.ocupados[i][j] = false;
			}
		}
		nCorredores = 1;
		corredores =new int[] {largura};
	}
	
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
		corredores =new int[] {largura};
	}
	
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
		corredores =new int[] {largura};
	}
	
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

	public void adicionar(int acento) {
		this.comprados[this.nComprados] = acento;
		++this.nComprados;
	}
	
	public void remover(int acento) {
		int i;
		boolean achou = false;
		for(i = 0; i < this.nComprados; ++i) {
			if(this.comprados[i] == acento) {
				achou = true;
				break;
			}
		}
		
		if(achou) {
			for(++i; i < this.nComprados; ++i) {
				this.comprados[i-1] = this.comprados[i];
			}
			--this.nComprados;
		}
	}
	
	public void comprar() {
		for(int i = 0; i < this.nComprados; ++i) {
			this.ocupados[this.comprados[i]/this.largura][this.comprados[i]%this.largura] = true;
		}
		this.nComprados = 0;
	}
}