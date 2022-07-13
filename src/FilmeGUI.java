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
		jp.add(new JLabel(filme.nomeDoFilme));
		jp.add(new JLabel(String.format("R$%.2f", filme.preco)));
		
		//Inicializa o grid para posicionar os botões
		JPanel horarios = new JPanel();
		horarios.setLayout(new GridLayout(filme.nSalas, 2));
		
		//Adiciona os horários
		for(int i = 0; i < filme.nSalas; ++i) {
			botoes[i] = new JButton();
			horarios.add(botoes[i]);
			botoes[i].addActionListener(this);
			botoes[i].setActionCommand(String.format("%d", i));
			horarios.add(new JLabel(String.format("%s-%s", filme.salas[i].inicio, filme.salas[i].fim)));
		}
		jp.add(horarios);
		
		//Adiciona o botão voltar
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(this);
		voltar.setActionCommand("voltar");
	}
	
	//Executa as ações dos botões
	public void actionPerformed(ActionEvent e) {
		if("voltar".equals(e.getActionCommand())) {
			this.dispose();
		}
		else {
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
