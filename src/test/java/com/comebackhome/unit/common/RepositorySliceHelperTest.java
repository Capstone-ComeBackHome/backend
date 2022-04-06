package com.comebackhome.unit.common;

import com.comebackhome.common.RepositorySliceHelper;
import com.comebackhome.disease.domain.Diagnosis;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosis;
import static org.assertj.core.api.Assertions.assertThat;

public class RepositorySliceHelperTest {

    @Test
    void pageSize초과한_content로_toSlice로_Slice객체_만들기() throws Exception{
        //given
        List<Diagnosis> diagnosisList = List.of(
                givenDiagnosis(),
                givenDiagnosis(),
                givenDiagnosis()
        );
        PageRequest pageRequest = PageRequest.of(0, 2);

        //when
        Slice<Diagnosis> result = RepositorySliceHelper.toSlice(diagnosisList, pageRequest);

        //then
        assertThat(result.getContent().size()).isEqualTo(2);
        assertThat(result.hasNext()).isTrue();
    }

    @Test
    void pageSize초과하지_않은_content로_toSlice로_Slice객체_만들기() throws Exception{
        //given
        List<Diagnosis> diagnosisList = List.of(
                givenDiagnosis(),
                givenDiagnosis(),
                givenDiagnosis()
        );
        PageRequest pageRequest = PageRequest.of(0, 20);

        //when
        Slice<Diagnosis> result = RepositorySliceHelper.toSlice(diagnosisList, pageRequest);

        //then
        assertThat(result.getContent().size()).isEqualTo(3);
        assertThat(result.hasNext()).isFalse();
    }

}
