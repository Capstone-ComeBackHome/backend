package com.comebackhome.diagnosis.presentation;

import com.comebackhome.common.CommonResponse;
import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.diagnosis.application.DiagnosisFacade;
import com.comebackhome.diagnosis.presentation.dto.request.DiagnosisSaveRequest;
import com.comebackhome.diagnosis.presentation.dto.response.DiagnosisResponseList;
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

    private final DiagnosisFacade diagnosisFacade;

    @PostMapping
    public ResponseEntity<CommonResponse> createDiagnosis(@Validated @RequestBody DiagnosisSaveRequest diagnosisSaveRequest,
                                                BindingResult errors,
                                                @LoginUser User user){
        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        diagnosisFacade.createDiagnosis(diagnosisSaveRequest.getDiseaseNameList(),user.getId());
        return ResponseEntity.ok(CommonResponse.success());
    }

    @DeleteMapping("/{diagnosisId}")
    public ResponseEntity<CommonResponse> deleteMyDiagnosis(@PathVariable Long diagnosisId,
                                                @LoginUser User user){

        diagnosisFacade.deleteMyDiagnosis(diagnosisId,user.getId());
        return ResponseEntity.ok(CommonResponse.success());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<DiagnosisResponseList>> getMyDiagnoses(
                                                  @RequestParam(required = false) Long lastDiagnosisId,
                                                  @PageableDefault(size = 20) Pageable pageable,
                                                  @LoginUser User user){

        DiagnosisResponseList diagnosisResponseList
                = DiagnosisResponseList.from(diagnosisFacade.getMyDiagnoses(lastDiagnosisId, user.getId(), pageable));
        return ResponseEntity.ok(CommonResponse.success(diagnosisResponseList));
    }

}
