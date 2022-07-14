import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompraGUI extends JDialog implements ActionListener{
	//Salva o frame que abriu a caixa de diálogo
	private JFrame seletor;
	
	//Salva as informações da sessão
	private Sala sala;
	
	//Salva os assentos desejados
	private int nComprados;
	private int[] comprados;
	
	//Salva se a compra foi efetuada
	public boolean pago = false;
	
	
	public CompraGUI(JFrame _seletor, Sala _sala, int _nComprados, int[] _comprados) {
		super(_seletor, true);
		seletor = _seletor;
		sala = _sala;
		nComprados = _nComprados;
		comprados = _comprados;
		
		//Inicializa o painel
		JPanel jp = (JPanel) getContentPane();
		jp.setLayout(new FlowLayout());
		
		//===========================Adiciona as formas de pagamento===========================
		//---------Inicializa o painel---------
		JPanel formas = new JPanel(new FlowLayout());
		
		//---------Adiciona o título---------
		formas.add(new JLabel("Formas de pagamento:"));
		formas.add(new JLabel("Não há formas de pagamento disponíveis, mas você pode imaginar que elas existem"));
		jp.add(formas);
		
		//Possibilita a leitura dos dados caso muitos ingressos sejam selecionados
		JPanel info = new JPanel(new FlowLayout());
		JScrollPane rolar = new JScrollPane(info);
		rolar.setPreferredSize(new Dimension(170, 157));
		rolar.setBorder(null);
		
		//===========================Adiciona os dados dos ingressos===========================
		//---------Inicializa o grid---------
		JPanel dados = new JPanel(new GridLayout(5, 1));
		dados.add(new JLabel(String.format("Filme: %s", sala.nomeDoFilme)));
		dados.add(new JLabel(String.format("Sala: %d", sala.numeroDaSala)));
		dados.add(new JLabel(String.format("Horário: %s-%s", sala.inicio, sala.fim)));
		
		//---------Adiciona os assentos---------
		JPanel ingressos = new JPanel(new FlowLayout());
		ingressos.add(new JLabel("Assentos:"));
		for(int i = 0; i < nComprados; ++i) {
			ingressos.add(new JLabel(String.format(" %d%c", (comprados[i]/sala.largura)+1, (char)(65 + (comprados[i]%sala.largura)))));
		}
		dados.add(ingressos);
		
		//---------Adiciona o preco---------
		dados.add(new JLabel(String.format("R$%.2f", nComprados * sala.preco)));
		
		//---------Adiciona os dados no painel---------
		
		info.add(dados);
		jp.add(rolar);
		
		//===========================Adiciona os botões================================
		//Inicializa o painel onde os botões serão colocados
		this.getContentPane().setLayout(new FlowLayout());
		
		//---------Adiciona o botão de retorno---------
		//Inicializa o botão
		JButton botaoRet = new JButton("Retornar");
		botaoRet.addActionListener(this);
		botaoRet.setActionCommand("retorna");
				
		//Altera a aparência
		botaoRet.setFont(new Font("Arial", Font.BOLD, 18));
		botaoRet.setBorder(null); 
		botaoRet.setBackground(new Color(43, 43 ,43));
		botaoRet.setForeground(new Color(169, 183, 198));
		
		//Adiciona o botão
		this.add(botaoRet);
		
		//---------Adiciona o botão de compra---------
		//Inicializa o botão
		JButton botaoComprar = new JButton("Comprar");
		botaoComprar.addActionListener(this);
		botaoComprar.setActionCommand("confirma");
		
		//Altera a aparência
		botaoComprar.setFont(new Font("Arial", Font.BOLD, 18));
		botaoComprar.setBorder(null); 
		botaoComprar.setBackground(new Color(43, 43 ,43));
		botaoComprar.setForeground(new Color(169, 183, 198));
		
		//Adiciona o botão
		this.add(botaoComprar);
	}
	
	//Executa as ações dos botões e caixas de checagem
	@Override
	public void actionPerformed(ActionEvent e) {
		//Caso seja pressionado o botão "Comprar"
		if("confirma".equals(e.getActionCommand())) {
			//Marca que os ingresso foram pagos
			pago = true;
			this.dispose();
		}
		
		//Caso seja pressionado o botão "Retornar"
		else if("retorna".equals(e.getActionCommand())) {
			//Fecha a janela
			this.dispose();
		}
	}
}
