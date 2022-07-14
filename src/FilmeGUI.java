import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class FilmeGUI extends JFrame implements ActionListener {
	int nFilmes;
	Filme[] filmes;
	JButton[][] botoes;
	
	public FilmeGUI(int _nFilmes, Filme[] _filmes) {
		super("Filme");
		nFilmes = _nFilmes;
		filmes = _filmes;
		botoes = new JButton[nFilmes][];
		
		//Cria a interface
		JPanel jp = (JPanel) getContentPane();
		jp.setLayout(new BorderLayout());
		
		//Instrui o usuário a como utilizar o programa
		JLabel instrucoes = new JLabel("Clique nos horários para comprar os ingressos para o filme");
		instrucoes.setForeground(new Color(169, 183, 198));
		instrucoes.setFont(new Font("Arial", Font.BOLD, 16));
		jp.add(instrucoes, BorderLayout.NORTH);
		
		//Inicialiaza os paineis onde serão colocados os dados
		JPanel opcoes = new JPanel(new GridLayout(nFilmes, 1));
		JPanel info = new JPanel(new FlowLayout());
		info.setBackground(new Color(43, 43, 43));
		info.add(opcoes);
		
		//Possibilita a seleção dos filmes caso eles não caibam na tela
		JScrollPane rolar = new JScrollPane(info);
		rolar.setPreferredSize(new Dimension(1538,799));
		rolar.getHorizontalScrollBar().setBackground(new Color(43, 43, 43));
		rolar.getVerticalScrollBar().setBackground(new Color(43, 43, 43));
		rolar.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		    	this.thumbColor = new Color(166, 166, 166);
		    }
		});
		rolar.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(166, 166, 166);
		    }
		});
		rolar.setBorder(null);
		jp.add(rolar, BorderLayout.CENTER);
		
		//Adiciona os filmes
		for(int i = 0; i < nFilmes; ++i) {
			//Inicializa o painel do filme alinhado à esquerda
			JPanel opcao = new JPanel();
			opcao.setLayout(new BoxLayout(opcao, BoxLayout.X_AXIS));
			opcao.setAlignmentX(LEFT_ALIGNMENT);
			opcao.setBackground(new Color(43, 43, 43));
			opcao.setPreferredSize(new Dimension(300, 250));
			
			//Adiciona a imagem
			JLabel imagem = new JLabel();
			imagem.setIcon(new ImageIcon(filmes[i].imagem));
			opcao.add(imagem);
			
			//Adiciona os dados do filme
			JPanel dados = new JPanel(new GridLayout(2, 1));
			dados.setBackground(new Color(43, 43, 43));
			
			//Adiciona o nome do filme
			JLabel nome = new JLabel(filmes[i].nomeDoFilme);
			nome.setForeground(new Color(169, 183, 198));
			nome.setFont(new Font("Arial", Font.BOLD, 20));
			dados.add(nome);
			
			//Adiciona as sessões do filme
			JPanel sessoes = new JPanel(new GridLayout(1, 2));
			sessoes.setBackground(new Color(43, 43, 43));
			
			//Adiciona a sala e o preco
			JPanel valor = new JPanel(new GridLayout(2, 1));
			valor.setBackground(new Color(43, 43, 43));
			
			//Adiciona a sala
			JLabel sala = new JLabel(String.format("Sala %d", filmes[i].numeroDaSala));
			sala.setForeground(new Color(169, 183, 198));
			valor.add(sala);
			
			//Adiciona o preco
			JLabel preco = new JLabel(String.format("R$%.2f", filmes[i].preco));
			preco.setForeground(new Color(169, 183, 198));
			valor.add(preco);
			sessoes.add(valor);
			
			//Inicializa o grid para posicionar os botões
			JPanel horarios = new JPanel();
			horarios.setLayout(new GridLayout(filmes[i].nSalas, 1));
			horarios.setBorder(null);
			horarios.setBackground(new Color(43, 43, 43));
			
			//Adiciona os horários
			botoes[i] = new JButton[filmes[i].nSalas];
			for(int j = 0; j < filmes[i].nSalas; ++j) {
				botoes[i][j] = new JButton(String.format("%s-%s", filmes[i].salas[j].inicio, filmes[i].salas[j].fim));
				horarios.add(botoes[i][j]);
				botoes[i][j].addActionListener(this);
				botoes[i][j].setActionCommand(String.format("%d-%d", i, j));
				botoes[i][j].setBackground(new Color(43, 43, 43));
				botoes[i][j].setForeground(new Color(169, 183, 198));
				botoes[i][j].setBorder(null);
			}
			sessoes.add(horarios);
			dados.add(sessoes);
			opcao.add(dados);
			opcoes.add(opcao);
		}
	}
	
	//Executa as ações dos botões
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < nFilmes; ++i) {
			for(int j = 0; j < filmes[i].nSalas; ++j) {
				String mensagem = String.format("%d-%d", i, j);
				if(mensagem.equals(e.getActionCommand())) {
					SalaGUI frame = new SalaGUI(filmes[i].salas[j]);
					frame.setSize(1550,830);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setBackground(new Color(43, 43, 43));
					frame.setVisible(true);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Filme filme1 = new Filme("PosterVingadores.png", "Vingadores");
		Filme filme2 = new Filme("PosterMatrix.png", "Matrix");
		Filme filme3 = new Filme("PosterMiranha.png", "Homem-Aranha");
		Filme filme4 = new Filme("PosterThor.png", "Thor");
		FilmeGUI frame = new FilmeGUI(4, new Filme[] {filme1, filme2, filme3, filme4});
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(43, 43, 43));
		frame.setVisible(true);
	}

}
