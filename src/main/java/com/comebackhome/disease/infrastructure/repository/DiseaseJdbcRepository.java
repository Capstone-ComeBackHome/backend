package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Disease;
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
public class DiseaseJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<Disease> diseaseList) {
        jdbcTemplate.batchUpdate("insert into disease" +
                        "(name, definition, recommend_department, symptom, cause, hospital_care,created_date,last_modified_date)" +
                        " values(?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1,diseaseList.get(i).getName());
                        ps.setString(2,diseaseList.get(i).getDefinition());
                        ps.setString(3,diseaseList.get(i).getRecommendDepartment());
                        ps.setString(4,diseaseList.get(i).getSymptom());
                        ps.setString(5,diseaseList.get(i).getCause());
                        ps.setString(6,diseaseList.get(i).getHospitalCare());
                        ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
                        ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                    }

                    @Override
                    public int getBatchSize() {
                        return diseaseList.size();
                    }
                }
        );

    }
}
