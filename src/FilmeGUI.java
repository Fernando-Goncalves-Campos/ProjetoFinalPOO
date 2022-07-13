import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FilmeGUI extends JFrame implements ActionListener {
	Filme filme;
	JButton[] botoes;
	
	public FilmeGUI(Filme _filme) {
		super("Filme");
		filme = _filme;
		botoes = new JButton[filme.nSalas];
		
		//Cria a interface
		JPanel jp = (JPanel) getContentPane();
		jp.setLayout(new FlowLayout());
		
		//Adiciona o nome do filme
		JLabel nome = new JLabel(filme.nomeDoFilme);
		nome.setForeground(new Color(169, 183, 198));
		jp.add(nome);
		JLabel preco = new JLabel(String.format("R$%.2f", filme.preco));
		preco.setForeground(new Color(169, 183, 198));
		jp.add(preco);
		
		//Inicializa o grid para posicionar os botões
		JPanel horarios = new JPanel();
		horarios.setLayout(new GridLayout(filme.nSalas, 1));
		horarios.setBorder(null);
		horarios.setBackground(new Color(43, 43, 43));
		
		//Adiciona os horários
		for(int i = 0; i < filme.nSalas; ++i) {
			botoes[i] = new JButton(String.format("%s-%s", filme.salas[i].inicio, filme.salas[i].fim));
			horarios.add(botoes[i]);
			botoes[i].addActionListener(this);
			botoes[i].setActionCommand(String.format("%d", i));
			botoes[i].setBackground(new Color(43, 43, 43));
			botoes[i].setForeground(new Color(169, 183, 198));
			botoes[i].setBorder(null);
		}
		jp.add(horarios);
	}
	
	//Executa as ações dos botões
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < filme.nSalas; ++i) {
			String mensagem = String.format("%d", i);
			if(mensagem.equals(e.getActionCommand())) {
				SalaGUI frame = new SalaGUI(filme.salas[i]);
				frame.setSize(1920,1080);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().setBackground(new Color(43, 43, 43));
				frame.setVisible(true);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Filme filme = new Filme("Teste");
		FilmeGUI frame = new FilmeGUI(filme);
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(43, 43, 43));
		frame.setVisible(true);
	}

}
