package com.example.OngVeterinaria.services;

import com.example.OngVeterinaria.model.AdocaoModel;
import com.example.OngVeterinaria.model.AnimalModel;
import com.example.OngVeterinaria.model.ClienteModel;
import com.example.OngVeterinaria.model.Enum.StatusGeral;
import com.example.OngVeterinaria.repository.AdocaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;

public class AdocaoServices {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @PostConstruct
    public void init() {
        // Configurações iniciais se necessário
    }


    public AdocaoModel alterarStatus(long idAdocao, StatusGeral status) {
        AdocaoModel adocao = adocaoRepository.findById(idAdocao)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada"));
        adocao.setStatus_Adocao(status);
        return adocaoRepository.save(adocao);
    }

    public void solicitarAdocao(AdocaoModel adocao) {
        adocao.setStatus_Adocao(StatusGeral.PENDENTE);
        adocaoRepository.save(adocao);
    }

    public void atualizarStatus(long id, StatusGeral novoStatus) {
        AdocaoModel adocao = adocaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
        adocao.setStatus_Adocao(novoStatus);
        adocaoRepository.save(adocao);
    }

    public byte[] gerarComprovanteAprovacao(AdocaoModel adocao) {
        // Aqui você deve criar o PDF usando uma biblioteca como iText ou Apache PDFBox
        // Aqui está um exemplo básico com iText:
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Comprovante de Adoção"));
            document.add(new Paragraph("ID da Adoção: " + adocao.getIdAdocao()));
            document.add(new Paragraph("Cliente: " + adocao.getCliente().getNome()));
            document.add(new Paragraph("Animal: " + adocao.getAnimal().getNome()));
            document.add(new Paragraph("Status: " + adocao.getStatus_Adocao()));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public void enviarEmailComprovante(String emailDestino, byte[] comprovante) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDestino);
        message.setSubject("Confirmação de Adoção");
        message.setText("Sua adoção foi aprovada! Anexamos o comprovante de adoção.");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(emailDestino);
        helper.setSubject("Confirmação de Adoção");
        helper.setText("Sua adoção foi aprovada! Anexamos o comprovante de adoção.");
        helper.addAttachment("comprovante_adoção.pdf", new ByteArrayDataSource(comprovante, "application/pdf"));
        javaMailSender.send(mimeMessage);
    }


}
