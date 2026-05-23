package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.entity.Camera;
import com.control_activos.sks.control_activos.repository.BranchRepository;
import com.control_activos.sks.control_activos.repository.CameraRepository;
import com.control_activos.sks.control_activos.services.CameraService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


@RestController
public class MemoriaController {

    @Autowired
    CameraRepository cameraRepository;
    @Autowired
    CameraService cameraService;
    @Autowired
    SpringTemplateEngine templateEngine;

    @GetMapping("{branchId}/photoReport")
    public ResponseEntity<byte[]> photoReport (@PathVariable Long branchId) {


        byte[] pdf = generatePdfTempService(branchId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"report.pdf\"");
        return ResponseEntity.ok().headers(headers).body(pdf);



    };

    public byte[] generatePdfTempService(Long branchId) {

        List<Camera> cameras = cameraRepository.findByBranchId(branchId);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",
                Locale.of("es", "MX"));

        String actualDate = now.format(formatter);

        Context context = new Context();
        context.setVariable("cameras", cameras);
        context.setVariable("actualDate", actualDate);

        String html = templateEngine.process("PhotoReport", context);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();

            String baseUrl = new File(".")
                    .toURI()
                    .toString();
            builder.withHtmlContent(html, baseUrl);

            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

}
