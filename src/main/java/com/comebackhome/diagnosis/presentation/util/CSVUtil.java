package com.comebackhome.diagnosis.presentation.util;

import com.comebackhome.common.exception.csv.FileNotFoundException;
import com.comebackhome.common.exception.csv.FileReadException;
import com.comebackhome.common.exception.csv.NotCSVFileException;
import com.comebackhome.diagnosis.presentation.dto.request.DiseaseSaveRequest;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CSVUtil {

    private static final String TYPE = "text/csv";

    public static List<DiseaseSaveRequest> toDiseaseSaveRequest(MultipartFile file) {
        CheckIsCSVFile(file);
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return new CsvToBeanBuilder<DiseaseSaveRequest>(reader)
                    .withType(DiseaseSaveRequest.class)
                    .build()
                    .parse();
        } catch (RuntimeException | IOException e) {
            throw new FileReadException(e);
        }
    }

    private static void CheckIsCSVFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileNotFoundException();
        }
        if (!TYPE.equals(file.getContentType())) {
            throw new NotCSVFileException();
        }
    }

}
