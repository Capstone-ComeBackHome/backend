INSERT INTO disease (id, name, definition, recommend_department, symptom, hospital_care, complications)
values (1,
        '부정맥',
        '발작성 민백 중 하나인 부정맥은 심장 전체로 전기 신호를 전달하는 전기 전달체계 변화나 기능부전 등에 의해 초래되는 불규칙한 심박동을 말해요. 휴식 시의 성인의 정상 심박수는 분달 60~80회이며 분당 60~100회까지를 정상맥박이라고 해요. 부정맥은 심장 박동수의 이상 혹은 율동의 이상을 의미하며, 심방과 심실 어디에서나 발생할 수 있어요.',
        '내과',
        '부정맥은 종류에 따라 빠르거나 느린 심박동을 본인이 느끼게 되고, 부정맥이 갑자기 짧게 나타날 경우 환자들은 맥박이 한 두번 갑자기 건너뛰는 느낌이 들 수 있어요. 부정맥이 발생하면 정상적인 심장박동에 비해 혈액공금이 원활하지 못하여 어지럼증이나 실신을 일으킬 수 있어오ㅛ. 지속적으로 반복될 경우 피로감을 느끼기도 해요. 그외 가슴통증이나 호흡곤란의 증상이 나타날 수 있어요.',
        '부정맥의 치료 방법은 다양해요. 항부정맥과 같은 약물치료, 페이스메이커와 같은 인공심박조율기를 통한 치료, 전기충격을 통한 치료, 부정맥 부위 제거수술 등이 있어요.',
        '합병증으로는 뇌졸증, 실신, 심정지 등 치명적인 합병증이 있을 수 있지만 드물어요. 치료가 늦어지면 심부전이 악화될 수 있어요.'
       );

INSERT INTO cause(disease_id,reason)
values (1, '심장은 신체의 전기전달체계에 의해 작동해요. 이런 전기전달체계가 영향을 받게 되면 부정맥이 발생할 수 있어요.');
INSERT INTO cause(disease_id,reason)
values (1, '혹은 심장 자체의 변화가 원인이 될 수 있는데, 심근경색 등의 허혈성 심질환, 선전성 심질환, 심근증, 심장판막질환, 유전적 질환 등이 원인이 될 수 있어요.');
INSERT INTO cause(disease_id,reason)
values (1, '이 외에 고도의 스트레스, 카페인, 술, 흡연, 불충분한 수면 등 환경의 변화로 발생할 수 있어요.');


INSERT INTO home_care(disease_id,solution)
values (1, '부정맥이 유발되는 상황을 알면 그런 상황을 피하게 하는 것이 가장 좋아요.');
INSERT INTO home_care(disease_id,solution)
values (1, '걷기, 달리기, 자전거, 수영과 같은 유산소 운동을 추천해요.');
INSERT INTO home_care(disease_id,solution)
values (1, '온몸의 근육을 풀어줄 수 있는 체조나 요가등도 권장해요.');
INSERT INTO home_care(disease_id,solution)
values (1, '수면부족, 스트레스, 피로와 같은 자극을 줄 수 있는 요인들은 피하는 것이 좋아요.');

INSERT INTO disease (id, name, definition, recommend_department, symptom, hospital_care)
values (2,
        '후두염',
        '후두염은 바이러스나 세균이 후두 점막에 침투하면서 염증을 일으켜 발생하는 질병으로 1~3세의 유하에서 흔히 나타나요. 어떤 원인에 의해 후두점막의 부종이 심해져 기도가 좁아지면서 증상이 나타나요',
        '내과',
        '초기에는 발열을 동반한 산기도 감영증의 증상이 나타나요. 수일 후 특징적인 개 기침소리가 나요.',
        '습기가 많은 침실에 앉히면 도움이 돼요. 기도 염증을 줄이기 위해 약을 복용해야 하고, 증상이 아주 심하면 입원해야 해요.'
       );

INSERT INTO cause(disease_id,reason)
values (2, '폐쇄성 후두염은 파라인플루엔자 바이러스의 감염에 의한 경우가 대부분인데, 세균 감염에 의한 경우도 많이 있어요.');

INSERT INTO home_care(disease_id,solution)
values (2, '습하고 찬 공기를 쐬는 것이 좋아요.');
INSERT INTO home_care(disease_id,solution)
values (2, '적절한 습도와 온도를 유지하는 것이 중요해요.');
INSERT INTO home_care(disease_id,solution)
values (2, '가습기를 틀어 습도를 조절해요.');
INSERT INTO home_care(disease_id,solution)
values (2, '밤에 갑자기 호흡곤란이 오면 위험할 수 있으므로 부모가 아기 곁에서 잠을 자야해요.');

INSERT INTO disease (id, name, definition, recommend_department, symptom, hospital_care, complications)
values (3,
        '편도염',
        '편도염이란 편도를 구성하는 허편도, 인두편도, 구개편도 중 주로 구개편도에 발생하는 급성 염증을 말해요.',
        '내과',
        '급선 편도염은 갑작스러운 고열과 오한이 나타나요.',
        '비수술적 치료로 1일 정도는 항생제를 사용해서 치료할 수 있어요.',
        '편도염으로 인한 흔한 합병증은 편도주위농양이에요.'
       );

INSERT INTO cause(disease_id,reason)
values (3, '급성 편도염의 가장 흔한 원인균은 B용혈성 연쇄상구균이며, 이외에도 포도상구균 등 다양한 균들이 원인이 될 수 있어요.');
INSERT INTO cause(disease_id,reason)
values (3, '인플루엔자 바이러스, 파라인플루엔자 바이러스 등의 바이러스도 역시 원인이 될 수 있어요.');

INSERT INTO home_care(disease_id,solution)
values (3, '평소 구강위생을 유지하고 자주 손을 씻어요.');
INSERT INTO home_care(disease_id,solution)
values (3, '수분 공급을 충분히 하고 휴식을 취해요.');
INSERT INTO home_care(disease_id,solution)
values (3, '목구멍의 자극성과 기침을 줄이기 위해 가습기를 이용해요.');