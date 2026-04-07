package ru.Irina.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Irina.NauJava.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}