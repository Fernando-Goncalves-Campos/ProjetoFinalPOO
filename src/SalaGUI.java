import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalaGUI extends JFrame implements ActionListener{
	
	private JCheckBox[][] opcoes;
	private Sala sala;
	
	public SalaGUI(Sala _sala) {
		super("Sala do cinema");
		sala = _sala;
		
		//Cria a interface
		JPanel jp = (JPanel) getContentPane();
		jp.setLayout(new FlowLayout());
		
		//Adiciona os acentos
		JPanel assentos = new JPanel();
		assentos.setLayout(new GridLayout(sala.profundidade + 1, sala.largura + 1 + sala.nCorredores));
		assentos.setBackground(new Color(43, 43, 43));
		//Adiciona as letras
		assentos.add(new JLabel(" "));
		int contador = 0;
		for(int i = 0; i < sala.nCorredores; ++i) {
			for(int j = 0; j < sala.corredores[i]; ++j) {
				JLabel letra = new JLabel(String.format("%5c", (char)(65 + contador)));
				letra.setForeground(new Color(169, 183, 198));
				assentos.add(letra);
				++contador;
			}
			if(i < sala.nCorredores - 1) {
				JLabel espaco = new JLabel("");
				assentos.add(espaco);
			}
		}
		assentos.add(new JLabel(" "));
		//Adicionas os assentos
		opcoes = new JCheckBox[sala.profundidade][sala.largura];
		for(int i = sala.profundidade; i > 0; --i) {
			JLabel numE = new JLabel(String.format("%9d", i));
			numE.setForeground(new Color(169, 183, 198));
			assentos.add(numE);
			contador = 0;
			for(int j = 0; j < sala.nCorredores; ++j) {
				for(int k = 0; k < sala.corredores[j]; ++k) {
					//Cria o botao
					JCheckBox botao = new JCheckBox("");
					botao.setIcon(new ImageIcon("Desocupado.png"));
					botao.setSelectedIcon(new ImageIcon("Selecionado.png"));
					botao.setRolloverIcon(new ImageIcon("Cima.png"));
					botao.setRolloverSelectedIcon(new ImageIcon("CimaSelecionado.png"));
					botao.setPressedIcon(new ImageIcon("CimaSelecionado.png"));
					botao.setDisabledIcon(new ImageIcon("Ocupado.png"));
					botao.setBorder(BorderFactory.createEmptyBorder());
					if(sala.ocupados[i-1][contador]) {
						botao.setEnabled(false);
					}
					assentos.add(botao);
					opcoes[i-1][contador] = botao;
					++contador;
				}
				if(j < sala.nCorredores - 1) {
					JLabel espaco = new JLabel("");
					assentos.add(espaco);
				}
			}
			JLabel numD = new JLabel(String.format(" %d", i));
			numD.setForeground(new Color(169, 183, 198));
			assentos.add(numD);
		}
		jp.add(assentos);
		
		//Adiciona os botôes de compra
		JPanel botoes = new JPanel(new GridLayout(2,1));
		JButton botaoRet = new JButton("Retornar");
		botaoRet.addActionListener(this);
		botaoRet.setActionCommand("retorna");
		botaoRet.setFont(new Font("Arial", Font.BOLD, 18));
		botaoRet.setBorder(null); 
		botaoRet.setBackground(new Color(43, 43 ,43));
		botaoRet.setForeground(new Color(169, 183, 198));
		JButton botaoComprar = new JButton("Comprar");
		botaoComprar.addActionListener(this);
		botaoComprar.setActionCommand("confirma");
		botaoComprar.setFont(new Font("Arial", Font.BOLD, 18));
		botaoComprar.setBorder(null); 
		botaoComprar.setBackground(new Color(43, 43 ,43));
		botaoComprar.setForeground(new Color(169, 183, 198));
		botaoComprar.setOpaque(true);
		botoes.add(botaoComprar);
		botoes.add(botaoRet);
		jp.add(botoes);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("confirma".equals(e.getActionCommand())) {
			for(int i = 0; i < sala.profundidade; ++i) {
				for(int j = 0; j < sala.largura; ++j) {
					if(opcoes[i][j].isSelected()) {
						sala.adicionar((i * sala.largura) + j);
						opcoes[i][j].setSelected(false);
						opcoes[i][j].setEnabled(false);
					}
				}
			}
			sala.comprar();
		}
		else if("retorna".equals(e.getActionCommand())) {
			this.dispose();
		}
	}

	public static void main(String[] args) {
		Sala sala = new Sala();
		sala.criaCorredores(3, new int[] {6, 6, 6});
		SalaGUI frame = new SalaGUI(sala);
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(43, 43, 43));
		frame.setVisible(true);

	}

}