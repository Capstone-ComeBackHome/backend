package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.DiagnosisDisease;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiagnosisDiseaseJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<DiagnosisDisease> DiagnosisDiseaseList) {
        jdbcTemplate.batchUpdate("insert into diagnosis_disease" +
                        "(disease_id, diagnosis_id, created_date,last_modified_date)" +
                        " values(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1,DiagnosisDiseaseList.get(i).getDisease().getId());
                        ps.setLong(2,DiagnosisDiseaseList.get(i).getDiagnosis().getId());
                        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    }

                    @Override
                    public int getBatchSize() {
                        return DiagnosisDiseaseList.size();
                    }
                }
        );

    }
}
