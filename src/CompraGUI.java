import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompraGUI extends JDialog implements ActionListener{
	//Salva o frame que abriu a caixa de diálogo
	private JFrame seletor;
	
	//Salva o preçpo de um assento
	private double preco;
	
	//Salva os assentos desejados
	private int nComprados;
	private int[] comprados;
	
	//Salva se a compra foi efetuada
	public boolean pago = false;
	
	
	public CompraGUI(JFrame _seletor, double _preco, int _nComprados, int[] _comprados) {
		super(_seletor, true);
		seletor = _seletor;
		preco = _preco;
		nComprados = _nComprados;
		comprados = _comprados;
		
		//Inicializa o painel
		JPanel jp = (JPanel) getContentPane();
		jp.setLayout(new FlowLayout());
		
		//Adiciona o preço dos ingressos
		JLabel valor = new JLabel(String.format("R$%.2f", preco * nComprados));
		this.add(valor);
		
		//===========================Adiciona os botões================================
		//Inicializa o painel onde os botões serão colocados
		this.getContentPane().setLayout(new FlowLayout());
		
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
