package ru.Irina.NauJava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.TemplateEngine;
import ru.Irina.NauJava.entity.Report;
import ru.Irina.NauJava.entity.ReportStatus;
import ru.Irina.NauJava.repository.ReportRepository;
import ru.Irina.NauJava.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReportServiceTest {


    @Mock
    private ReportRepository reportRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TemplateEngine templateEngine;


    @InjectMocks
    private ReportService reportService;


    @Test
    void createReport_ShouldReturnIdAndSetStatusCreated() {

        Report savedReport = new Report();
        savedReport.setId(1L);
        savedReport.setStatus(ReportStatus.CREATED);

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);


        Long resultId = reportService.createReport();


        assertNotNull(resultId);
        assertEquals(1L, resultId);
        verify(reportRepository, times(1)).save(any(Report.class));
    }


    @Test
    void getReportById_ShouldReturnReport_WhenExists() {
        Report report = new Report();
        report.setId(5L);


        when(reportRepository.findById(5L)).thenReturn(Optional.of(report));

        Report foundReport = reportService.getReportById(5L);

        assertNotNull(foundReport);
        assertEquals(5L, foundReport.getId());
    }


    @Test
    void getReportById_ShouldReturnNull_WhenNotExists() {

        when(reportRepository.findById(99L)).thenReturn(Optional.empty());

        Report foundReport = reportService.getReportById(99L);

        assertNull(foundReport);
    }
}