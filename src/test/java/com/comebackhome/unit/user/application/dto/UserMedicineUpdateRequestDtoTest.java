package com.comebackhome.unit.user.application.dto;

import com.comebackhome.user.application.dto.UserMedicineUpdateRequestDto;
import com.comebackhome.user.domain.dto.UserMedicineUpdateDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserMedicineUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

public class UserMedicineUpdateRequestDtoTest {

    @Test
    void UserMedicineUpdateDto로_전환하기() throws Exception{
        //given
        UserMedicineUpdateRequestDto dto = givenUserMedicineUpdateRequestDto();

        //when
        UserMedicineUpdateDto result = dto.toUserMedicineUpdateDto();

        //then
        assertThat(result.getHistory()).isEqualTo(dto.getHistory());
        assertThat(result.getSocialHistory()).isEqualTo(dto.getSocialHistory());
        assertThat(result.getDrugHistory()).isEqualTo(dto.getDrugHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(dto.getTraumaHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(dto.getFamilyHistory());

    }
}
