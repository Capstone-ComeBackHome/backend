package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
import com.comebackhome.user.presentation.dto.request.UserMedicineUpdateRequest;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserMedicineUpdateRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class UserMedicineUpdateRequestTest {

    @Test
    void UserMedicineUpdateRequestDto로_전환하기() throws Exception{
        //given
        UserMedicineUpdateRequest dto = givenUserMedicineUpdateRequest();

        //when
        UserMedicineUpdateRequestDto result = dto.toUserMedicineUpdateRequestDto();

        //then
        assertThat(result.getHistory()).isEqualTo(dto.getHistory());
        assertThat(result.getSocialHistory()).isEqualTo(dto.getSocialHistory());
        assertThat(result.getDrugHistory()).isEqualTo(dto.getDrugHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(dto.getTraumaHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(dto.getFamilyHistory());
    }
}
