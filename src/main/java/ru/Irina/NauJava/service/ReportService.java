package ru.Irina.NauJava.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.Irina.NauJava.entity.Report;
import ru.Irina.NauJava.entity.ReportStatus;
import ru.Irina.NauJava.entity.User;
import ru.Irina.NauJava.repository.ReportRepository;
import ru.Irina.NauJava.repository.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;


    private final TemplateEngine templateEngine;


    public ReportService(ReportRepository reportRepository, UserRepository userRepository, TemplateEngine templateEngine) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.templateEngine = templateEngine;
    }

    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        report = reportRepository.save(report);
        return report.getId();
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    public void generateReportAsync(Long reportId) {
        CompletableFuture.runAsync(() -> {
            long totalStartTime = System.currentTimeMillis();

            try {
                final long[] usersCount = {0};
                final List<User>[] usersList = new List[]{null};
                final long[] timeUsersCount = {0};
                final long[] timeUsersList = {0};

                Thread thread1 = new Thread(() -> {
                    long startTime = System.currentTimeMillis();
                    usersCount[0] = userRepository.count();
                    timeUsersCount[0] = System.currentTimeMillis() - startTime;
                });

                Thread thread2 = new Thread(() -> {
                    long startTime = System.currentTimeMillis();
                    usersList[0] = userRepository.findAll();
                    timeUsersList[0] = System.currentTimeMillis() - startTime;
                });

                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();

                long totalTime = System.currentTimeMillis() - totalStartTime;


                Context context = new Context();
                context.setVariable("usersCount", usersCount[0]);
                context.setVariable("timeUsersCount", timeUsersCount[0]);
                context.setVariable("usersList", usersList[0]);
                context.setVariable("timeUsersList", timeUsersList[0]);
                context.setVariable("totalTime", totalTime);


                String htmlContent = templateEngine.process("report", context);

                Report report = reportRepository.findById(reportId).orElseThrow();
                report.setContent(htmlContent);
                report.setStatus(ReportStatus.COMPLETED);
                reportRepository.save(report);

            } catch (Exception e) {
                Report report = reportRepository.findById(reportId).orElse(null);
                if (report != null) {
                    report.setStatus(ReportStatus.ERROR);
                    report.setContent("Произошла ошибка при формировании отчета: " + e.getMessage());
                    reportRepository.save(report);
                }
            }
        });
    }
}