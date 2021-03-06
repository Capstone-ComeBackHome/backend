package com.comebackhome.unit.common;

import com.comebackhome.common.exception.csv.FileNotFoundException;
import com.comebackhome.common.exception.csv.FileReadException;
import com.comebackhome.common.exception.csv.NotCSVFileException;
import com.comebackhome.diagnosis.presentation.dto.request.DiseaseSaveRequest;
import com.comebackhome.diagnosis.presentation.util.CSVUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CSVUtilTest {

    @Test
    void diseaseRequestDto로_전환하기() throws Exception{
        //given
        MockMultipartFile file = createMockMultipartFile();
        //when
        List<DiseaseSaveRequest> result = CSVUtil.toDiseaseSaveRequest(file);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("부정맥");
        assertThat(result.get(0).getDefinition()).isEqualTo("정의");
        assertThat(result.get(0).getRecommendDepartment()).isEqualTo("내과");
        assertThat(result.get(0).getSymptom()).isEqualTo("증상");
        assertThat(result.get(0).getCause()).isEqualTo("원인");
        assertThat(result.get(0).getHospitalCare()).isEqualTo("치료");
    }

    private MockMultipartFile createMockMultipartFile() {
        return new MockMultipartFile("file",
                "disease.csv",
                "text/csv",
                "부정맥,정의,내과,증상,원인,치료".getBytes());
    }

    @Test
    void file이_null_경우() throws Exception{

        //when then
        assertThatThrownBy(() -> CSVUtil.toDiseaseSaveRequest(null))
                .isInstanceOf(FileNotFoundException.class);
    }

    @Test
    void file이_빈_경우() throws Exception{
        MockMultipartFile file = createEmptyMockMultipartFile();
        //when then
        assertThatThrownBy(() -> CSVUtil.toDiseaseSaveRequest(file))
                .isInstanceOf(FileNotFoundException.class);
    }

    private MockMultipartFile createEmptyMockMultipartFile() {
        return new MockMultipartFile("file",
                "disease.csv",
                "text/csv",
                "".getBytes());
    }

    @Test
    void csv_파일이_아닌_경우() throws Exception{
        MockMultipartFile file = createMockMultipartFileNotCsv();

        //when then
        assertThatThrownBy(() -> CSVUtil.toDiseaseSaveRequest(file))
                .isInstanceOf(NotCSVFileException.class);
    }

    private MockMultipartFile createMockMultipartFileNotCsv() {
        return new MockMultipartFile("file",
                "disease.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "부정맥,정의,내과,증상,원인,치료".getBytes());
    }

    @Test
    void DiseaseRequestDto_지정_형식과_다른_경우() throws Exception{
        MockMultipartFile file = createMockMultipartFilNotMatchWithDiseaseRequestDto();

        //when then
        assertThatThrownBy(() -> CSVUtil.toDiseaseSaveRequest(file))
                .isInstanceOf(FileReadException.class);
    }

    private MockMultipartFile createMockMultipartFilNotMatchWithDiseaseRequestDto() {
        return new MockMultipartFile("file",
                "disease.csv",
                "text/csv",
                "원인,치료".getBytes());
    }

}
