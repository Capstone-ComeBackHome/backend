package com.comebackhome.user.domain;

import com.comebackhome.common.domain.BaseEntity;
import com.comebackhome.user.domain.dto.UserEssentialUpdateDto;
import com.comebackhome.user.domain.dto.UserInfoDto;
import com.comebackhome.user.domain.dto.UserMedicineUpdateDto;
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

    public void saveInfo(UserInfoDto userInfoDto){
        this.age = userInfoDto.getAge();
        this.sex = userInfoDto.getSex();
        this.height = userInfoDto.getHeight();
        this.weight = userInfoDto.getWeight();
        this.history = userInfoDto.getHistory();
        this.FamilyHistory = userInfoDto.getFamilyHistory();
        this.drugHistory = userInfoDto.getDrugHistory();
        this.socialHistory = userInfoDto.getSocialHistory();
        this.traumaHistory = userInfoDto.getTraumaHistory();
    }

    public void updateEssentialInfo(UserEssentialUpdateDto userEssentialUpdateDto) {
        this.age = userEssentialUpdateDto.getAge();
        this.sex = userEssentialUpdateDto.getSex();
        this.height = userEssentialUpdateDto.getHeight();
        this.weight = userEssentialUpdateDto.getWeight();
    }

    public void updateMedicineInfo(UserMedicineUpdateDto userMedicineUpdateDto) {
        this.history = userMedicineUpdateDto.getHistory();
        this.FamilyHistory = userMedicineUpdateDto.getFamilyHistory();
        this.drugHistory = userMedicineUpdateDto.getDrugHistory();
        this.socialHistory = userMedicineUpdateDto.getSocialHistory();
        this.traumaHistory = userMedicineUpdateDto.getTraumaHistory();
    }
}
