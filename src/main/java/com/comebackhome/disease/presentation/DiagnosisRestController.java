package com.comebackhome.disease.presentation;

import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.disease.application.DiagnosisCommandUseCase;
import com.comebackhome.disease.application.DiagnosisQueryUseCase;
import com.comebackhome.disease.application.dto.DiagnosisResponseDtoList;
import com.comebackhome.disease.presentation.dto.DiagnosisResponseList;
import com.comebackhome.disease.presentation.dto.DiagnosisSaveRequest;
import com.comebackhome.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/diagnoses")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class DiagnosisRestController {

    private final DiagnosisCommandUseCase diagnosisCommandUseCase;
    private final DiagnosisQueryUseCase diagnosisQueryUseCase;

    @PostMapping
    public ResponseEntity<Void> createDiagnosis(@Validated @RequestBody DiagnosisSaveRequest diagnosisSaveRequest,
                                                BindingResult errors,
                                                @LoginUser User user){
        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        diagnosisCommandUseCase.createDiagnosis(diagnosisSaveRequest.getDiseaseNameList(),user.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{diagnosisId}")
    public ResponseEntity<Void> deleteMyDiagnosis(@PathVariable Long diagnosisId,
                                                @LoginUser User user){

        diagnosisCommandUseCase.deleteMyDiagnosis(diagnosisId,user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<DiagnosisResponseList> getMyDiagnoses(
                                                  @RequestParam(required = false) Long lastDiagnosisId,
                                                  @PageableDefault(size = 20) Pageable pageable,
                                                  @LoginUser User user){

        DiagnosisResponseDtoList diagnosisResponseDtoList
                = diagnosisQueryUseCase.getMyDiagnoses(lastDiagnosisId, user.getId(), pageable);
        return ResponseEntity.ok(DiagnosisResponseList.from(diagnosisResponseDtoList));
    }

}
