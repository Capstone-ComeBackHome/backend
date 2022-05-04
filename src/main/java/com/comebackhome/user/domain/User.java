package com.comebackhome.user.domain;

import com.comebackhome.common.domain.BaseEntity;
import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.service.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String picture;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private int age;

    private Sex sex;

    private int height;

    private int weight;

    private String history;

    private String FamilyHistory;

    private String drugHistory;

    private String socialHistory;

    private String traumaHistory;

    public String getRoleKey(){
        return this.role.getKey();
    }

    public User updateBySocialProfile(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

    public void saveInfo(UserInfoSaveRequestDto userInfoSaveRequestDto){
        this.age = userInfoSaveRequestDto.getAge();
        this.sex = userInfoSaveRequestDto.getSex();
        this.height = userInfoSaveRequestDto.getHeight();
        this.weight = userInfoSaveRequestDto.getWeight();
        this.history = userInfoSaveRequestDto.getHistory();
        this.FamilyHistory = userInfoSaveRequestDto.getFamilyHistory();
        this.drugHistory = userInfoSaveRequestDto.getDrugHistory();
        this.socialHistory = userInfoSaveRequestDto.getSocialHistory();
        this.traumaHistory = userInfoSaveRequestDto.getTraumaHistory();
    }

    public void updateEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto) {
        this.age = userEssentialUpdateRequestDto.getAge();
        this.sex = userEssentialUpdateRequestDto.getSex();
        this.height = userEssentialUpdateRequestDto.getHeight();
        this.weight = userEssentialUpdateRequestDto.getWeight();
    }

    public void updateMedicineInfo(UserMedicineUpdateRequestDto userMedicineUpdateRequestDto) {
        this.history = userMedicineUpdateRequestDto.getHistory();
        this.FamilyHistory = userMedicineUpdateRequestDto.getFamilyHistory();
        this.drugHistory = userMedicineUpdateRequestDto.getDrugHistory();
        this.socialHistory = userMedicineUpdateRequestDto.getSocialHistory();
        this.traumaHistory = userMedicineUpdateRequestDto.getTraumaHistory();
    }
}
