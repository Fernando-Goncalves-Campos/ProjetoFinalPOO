import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
		jp.setLayout(new FlowLayout());
		
		//Possibilita a seleção dos filmes caso eles não caibam na tela
		JPanel opcoes = new JPanel(new GridLayout(nFilmes, 1));
		JPanel info = new JPanel(new FlowLayout());
		info.setBackground(new Color(43, 43, 43));
		info.add(opcoes);
		JScrollPane rolar = new JScrollPane(info);
		rolar.setPreferredSize(new Dimension(1538,798));
		rolar.setBorder(null);
		jp.add(rolar);
		
		for(int i = 0; i < nFilmes; ++i) {
			//Inicializa o painel do filme
			JPanel opcao = new JPanel(new FlowLayout());
			
			//Adiciona o nome do filme
			JLabel nome = new JLabel(filmes[i].nomeDoFilme);
			nome.setForeground(new Color(169, 183, 198));
			nome.setBackground(new Color(43,43,43));
			opcao.add(nome);
			JLabel preco = new JLabel(String.format("R$%.2f", filmes[i].preco));
			preco.setForeground(new Color(169, 183, 198));
			opcao.add(preco);
			
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
			opcao.add(horarios);
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
		Filme filme1 = new Filme("Teste1");
		Filme filme2 = new Filme("Teste2");
		FilmeGUI frame = new FilmeGUI(2, new Filme[] {filme1, filme2});
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(43, 43, 43));
		frame.setVisible(true);
	}

}
