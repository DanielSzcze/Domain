package site.danielszczesny.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.danielszczesny.backend.model.timofinance.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
