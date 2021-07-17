package site.danielszczesny.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.danielszczesny.backend.model.timofinance.Record;

import java.util.Set;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    public Set<Record> getAllByUserId(long userId);
}
