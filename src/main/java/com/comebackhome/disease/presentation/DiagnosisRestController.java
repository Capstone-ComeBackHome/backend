package com.comebackhome.disease.presentation;

import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.disease.application.DiagnosisCommandUseCase;
import com.comebackhome.disease.presentation.dto.DiagnosisRequest;
import com.comebackhome.user.domain.User;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<Void> createDiagnosis(@Validated @RequestBody DiagnosisRequest diagnosisRequest,
                                                BindingResult errors,
                                                @LoginUser User user){
        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        diagnosisCommandUseCase.createDiagnosis(diagnosisRequest.getDiseaseNameList(),user.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{diagnosisId}")
    public ResponseEntity<Void> deleteMyDiagnosis(@PathVariable Long diagnosisId,
                                                @LoginUser User user){

        diagnosisCommandUseCase.deleteMyDiagnosis(diagnosisId,user.getId());
        return ResponseEntity.ok().build();
    }
}
