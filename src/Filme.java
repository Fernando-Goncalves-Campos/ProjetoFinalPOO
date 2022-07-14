import java.util.Random;

public class Filme {
	public String imagem;
	public String nomeDoFilme;
	public double preco;
	public int numeroDaSala;
	public int nSalas;
	public Sala[] salas;
	
	//Copia os valores entregues pelo usuário
	public Filme(String _imagem, String _nomeDoFilme, double _preco, int _nSalas, Sala[] _salas) {
		imagem = _imagem;
		preco = _preco;
		nSalas = _nSalas;
		salas = _salas;
		nomeDoFilme = _nomeDoFilme;
	}
	
	//Cria um filme com dados aleatórios (utilizado para teste)
	public Filme(String _imagem, String _nomeDoFilme) {
		imagem = _imagem;
		Random rand = new Random();
		nomeDoFilme = _nomeDoFilme;
		nSalas = rand.nextInt(1, 4);
		salas = new Sala[nSalas];
		numeroDaSala = rand.nextInt(1, 10);
		preco = rand.nextDouble(25.00, 35.00);
		int horas = 10;
		int minutos = 30;
		for(int i = 0; i < nSalas; ++i) {
			salas[i] = new Sala();
			salas[i].nomeDoFilme = _nomeDoFilme;
			salas[i].preco = preco;
			salas[i].inicio = String.format("%d:%d", horas, minutos);
			horas += 2;
			salas[i].fim = String.format("%d:%d", horas, minutos);
			salas[i].numeroDaSala = numeroDaSala;
			salas[i].criaCorredores(3, new int[] {6, 6, 6});
		}
	}
}