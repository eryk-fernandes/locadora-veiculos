package org.example.model.service;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.example.controller.LocacaoController;
import org.example.controller.PagamentoController;

public class GeradorPDF {

	private static final LocacaoController locacaoController = new LocacaoController();
	private static final PagamentoController pagamentoController = new PagamentoController();

	public void gerarRelatorioVeiculosLocados() throws Exception {

		Document pdf = new Document();

		PdfWriter.getInstance(pdf, new FileOutputStream("dados/relatorios/veiculos.pdf"));

		pdf.open();
		
		String[] veiculos = locacaoController.veiculosLocadosPorMes();
		
		PdfPTable tabelaNome = new PdfPTable(1);

		tabelaNome.addCell(new Paragraph("VEÍCULOS LOCADOS"));
		
		pdf.add(tabelaNome);
		
		PdfPTable tabela = new PdfPTable(4);
		
		tabela.addCell(new Paragraph("MODELO"));
		tabela.addCell(new Paragraph("ANO"));
		tabela.addCell(new Paragraph("PLACA"));
		tabela.addCell(new Paragraph("DATA DE RETIRADA"));
		
		for (String veiculo : veiculos) {
			
			String modelo = veiculo.split(" ")[0];
			String ano = veiculo.split(" ")[1];
			String placa = veiculo.split(" ")[2];
			String mes = veiculo.split(" ")[3];
			
			tabela.addCell(new Paragraph(modelo));
			tabela.addCell(new Paragraph(ano));
			tabela.addCell(new Paragraph(placa));
			tabela.addCell(new Paragraph(mes));
		}
		
		pdf.add(tabela);
		
		pdf.close();

	}

	public void gerarRelatorioFaturamentos() throws Exception {

		Document pdf = new Document();

		PdfWriter.getInstance(pdf, new FileOutputStream("dados/relatorios/faturamentos.pdf"));

		pdf.open();
		
		String[] pagamentos = pagamentoController.faturamentosPorMes();
		
		PdfPTable tabelaNome = new PdfPTable(1);

		tabelaNome.addCell(new Paragraph("PAGAMENTOS"));

		pdf.add(tabelaNome);
		
		PdfPTable tabela = new PdfPTable(6);
		
		tabela.addCell(new Paragraph("ID"));
		tabela.addCell(new Paragraph("ID DA LOCAÇÃO"));
		tabela.addCell(new Paragraph("VALOR"));
		tabela.addCell(new Paragraph("MÉTODO DE PAGAMENTO"));
		tabela.addCell(new Paragraph("DATA DE PAGAMENTO"));
		tabela.addCell(new Paragraph("FATURAMENTO MENSAL"));

		for (String pagamento : pagamentos) {
			
			String id = pagamento.split(" ")[0];
			String idLocacao = pagamento.split(" ")[1];
			String valor = pagamento.split(" ")[2];
			String metodo = pagamento.split(" ")[3];
			String dataPagamento = pagamento.split(" ")[4];
			String faturamento = pagamento.split(" ")[5];
			
			tabela.addCell(new Paragraph(id));
			tabela.addCell(new Paragraph(idLocacao));
			tabela.addCell(new Paragraph(valor));
			tabela.addCell(new Paragraph(metodo));
			tabela.addCell(new Paragraph(dataPagamento));
			tabela.addCell(new Paragraph(faturamento));
		}
		
		pdf.add(tabela);
		
		pdf.close();

	}

	public void gerarRelatorioLocacoes() throws Exception {

		Document pdf = new Document();

		PdfWriter.getInstance(pdf, new FileOutputStream("dados/relatorios/locacoes.pdf"));

		pdf.open();
		
		String[] locacoes = locacaoController.locacoesPorMes();
		
		PdfPTable tabelaNome = new PdfPTable(1);

		tabelaNome.addCell(new Paragraph("LOCAÇÕES"));

		pdf.add(tabelaNome);
		
		PdfPTable tabela = new PdfPTable(4);
		
		tabela.addCell(new Paragraph("ID"));
		tabela.addCell(new Paragraph("CPF"));
		tabela.addCell(new Paragraph("PLACA"));
		tabela.addCell(new Paragraph("DATA DE RETIRADA"));

		for (String locacao : locacoes) {
			
			String id = locacao.split(" ")[0];
			String cpf = locacao.split(" ")[1];
			String placa = locacao.split(" ")[2];
			String retirada = locacao.split(" ")[3];
			
			tabela.addCell(new Paragraph(id));
			tabela.addCell(new Paragraph(cpf));
			tabela.addCell(new Paragraph(placa));
			tabela.addCell(new Paragraph(retirada));
		}
		
		pdf.add(tabela);
		
		pdf.close();

	}
}
