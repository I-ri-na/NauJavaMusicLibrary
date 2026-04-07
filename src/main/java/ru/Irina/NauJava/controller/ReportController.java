package ru.Irina.NauJava.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Irina.NauJava.entity.Report;
import ru.Irina.NauJava.entity.ReportStatus;
import ru.Irina.NauJava.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> startReportGeneration() {
        Long reportId = reportService.createReport();
        reportService.generateReportAsync(reportId);
        return ResponseEntity.ok("Процесс формирования отчета запущен! ID вашего отчета: " + reportId);
    }


    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    public ResponseEntity<String> getReport(@PathVariable Long id) {
        Report report = reportService.getReportById(id);

        if (report == null) {
            return ResponseEntity.badRequest().body("<h3>Отчет с таким ID не найден.</h3>");
        }

        if (report.getStatus() == ReportStatus.CREATED) {
            return ResponseEntity.ok("<h3>Отчет еще формируется... Пожалуйста, обновите страницу чуть позже.</h3>");
        } else if (report.getStatus() == ReportStatus.ERROR) {
            return ResponseEntity.ok("<h3>Ошибка:</h3><p>" + report.getContent() + "</p>");
        } else {
            return ResponseEntity.ok(report.getContent());
        }
    }
}