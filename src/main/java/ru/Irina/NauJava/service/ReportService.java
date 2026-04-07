package ru.Irina.NauJava.service;

import org.springframework.stereotype.Service;
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

    public ReportService(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
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


                StringBuilder html = new StringBuilder();
                html.append("<h2>Отчет: Статистика пользователей</h2>");
                html.append("<table border='1' cellpadding='10' style='border-collapse: collapse;'>");
                html.append("<tr style='background-color: #f2f2f2;'><th>Метрика</th><th>Значение</th><th>Время выполнения (мс)</th></tr>");
                html.append("<tr><td>Количество пользователей</td><td>").append(usersCount[0]).append("</td><td>").append(timeUsersCount[0]).append("</td></tr>");

                html.append("<tr><td>Список пользователей (логины)</td><td>");
                for (User u : usersList[0]) {
                    html.append(u.getLogin()).append("<br>");
                }
                html.append("</td><td>").append(timeUsersList[0]).append("</td></tr>");

                html.append("<tr style='background-color: #e6ffe6;'><td colspan='2'><b>Общее время формирования отчета</b></td><td><b>").append(totalTime).append("</b></td></tr>");
                html.append("</table>");

                Report report = reportRepository.findById(reportId).orElseThrow();
                report.setContent(html.toString());
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