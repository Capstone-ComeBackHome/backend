INSERT INTO disease (id, name, definition, recommend_department, symptom, cause, hospital_care)
values (1,
        '질병1',
        '발작성 민백 중 하나인 부정맥은 심장 전체로 전기 신호를 전달하는 전기 전달체계 변화나 기능부전 등에 의해 초래되는 불규칙한 심박동을 말해요. 휴식 시의 성인의 정상 심박수는 분달 60~80회이며 분당 60~100회까지를 정상맥박이라고 해요. 부정맥은 심장 박동수의 이상 혹은 율동의 이상을 의미하며, 심방과 심실 어디에서나 발생할 수 있어요.',
        '내과',
        '부정맥은 종류에 따라 빠르거나 느린 심박동을 본인이 느끼게 되고, 부정맥이 갑자기 짧게 나타날 경우 환자들은 맥박이 한 두번 갑자기 건너뛰는 느낌이 들 수 있어요. 부정맥이 발생하면 정상적인 심장박동에 비해 혈액공금이 원활하지 못하여 어지럼증이나 실신을 일으킬 수 있어오ㅛ. 지속적으로 반복될 경우 피로감을 느끼기도 해요. 그외 가슴통증이나 호흡곤란의 증상이 나타날 수 있어요.',
        '심장은 신체의 전기전달체계에 의해 작동해요. 이런 전기전달체계가 영향을 받게 되면 부정맥이 발생할 수 있어요.',
        '부정맥이 유발되는 상황을 알면 그런 상황을 피하게 하는 것이 가장 좋아요.'
       );

INSERT INTO disease (id, name, definition, recommend_department, symptom, cause, hospital_care)
values (2,
        '질병2',
        '후두염은 바이러스나 세균이 후두 점막에 침투하면서 염증을 일으켜 발생하는 질병으로 1~3세의 유하에서 흔히 나타나요. 어떤 원인에 의해 후두점막의 부종이 심해져 기도가 좁아지면서 증상이 나타나요',
        '내과',
        '초기에는 발열을 동반한 산기도 감영증의 증상이 나타나요. 수일 후 특징적인 개 기침소리가 나요.',
        '폐쇄성 후두염은 파라인플루엔자 바이러스의 감염에 의한 경우가 대부분인데, 세균 감염에 의한 경우도 많이 있어요.',
        '습하고 찬 공기를 쐬는 것이 좋아요.'
       );

INSERT INTO disease (id, name, definition, recommend_department, symptom, cause, hospital_care)
values (3,
        '질병3',
        '편도염이란 편도를 구성하는 허편도, 인두편도, 구개편도 중 주로 구개편도에 발생하는 급성 염증을 말해요.',
        '내과',
        '급선 편도염은 갑작스러운 고열과 오한이 나타나요.',
        '급성 편도염의 가장 흔한 원인균은 B용혈성 연쇄상구균이며, 이외에도 포도상구균 등 다양한 균들이 원인이 될 수 있어요.',
        '평소 구강위생을 유지하고 자주 손을 씻어요.'
       );

INSERT INTO disease_tag (id, disease_type, name)
values (1, 'HEAD', '강박증');
INSERT INTO disease_tag (id, disease_type, name)
values (2, 'HEAD', '건망증');
INSERT INTO disease_tag (id, disease_type, name)
values (3, 'HEAD', '두통');
INSERT INTO disease_tag (id, disease_type, name)
values (4, 'HEAD', '두피 건조');
INSERT INTO disease_tag (id, disease_type, name)
values (5, 'HEAD', '두피 열상');
INSERT INTO disease_tag (id, disease_type, name)
values (6, 'HEAD', '어지러움');
INSERT INTO disease_tag (id, disease_type, name)
values (7, 'HEAD', '탈모');
INSERT INTO disease_tag (id, disease_type, name)
values (8, 'HEAD', '편두통');
INSERT INTO disease_tag (id, disease_type, name)
values (9, 'HEAD', '기민상태');
INSERT INTO disease_tag (id, disease_type, name)
values (10, 'HEAD', '인지장애');

INSERT INTO disease_tag (id, disease_type, name)
values (11, 'BRONCHUS', '코막힘');
INSERT INTO disease_tag (id, disease_type, name)
values (12, 'BRONCHUS', '재채기');
INSERT INTO disease_tag (id, disease_type, name)
values (13, 'BRONCHUS', '콧물');
INSERT INTO disease_tag (id, disease_type, name)
values (14, 'BRONCHUS', '기침');
INSERT INTO disease_tag (id, disease_type, name)
values (15, 'BRONCHUS', '편도선 비대');
INSERT INTO disease_tag (id, disease_type, name)
values (16, 'BRONCHUS', '가래');
INSERT INTO disease_tag (id, disease_type, name)
values (17, 'BRONCHUS', '인후염');
INSERT INTO disease_tag (id, disease_type, name)
values (18, 'BRONCHUS', '후두염');
INSERT INTO disease_tag (id, disease_type, name)
values (19, 'BRONCHUS', '구내염');
INSERT INTO disease_tag (id, disease_type, name)
values (20, 'BRONCHUS', '잇몸염증');

INSERT INTO disease_tag (id, disease_type, name)
values (21, 'CHEST', '가슴 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (22, 'CHEST', '가슴 답답');
INSERT INTO disease_tag (id, disease_type, name)
values (23, 'CHEST', '호흡 곤란');
INSERT INTO disease_tag (id, disease_type, name)
values (24, 'CHEST', '천식');
INSERT INTO disease_tag (id, disease_type, name)
values (25, 'CHEST', '부정맥');
INSERT INTO disease_tag (id, disease_type, name)
values (26, 'CHEST', '폐렴');
INSERT INTO disease_tag (id, disease_type, name)
values (27, 'CHEST', '잦은 딸꾹질');
INSERT INTO disease_tag (id, disease_type, name)
values (28, 'CHEST', '과호흡');
INSERT INTO disease_tag (id, disease_type, name)
values (29, 'CHEST', '빈맥');
INSERT INTO disease_tag (id, disease_type, name)
values (30, 'CHEST', '객혈');

INSERT INTO disease_tag (id, disease_type, name)
values (31, 'STOMACH', '공복감');
INSERT INTO disease_tag (id, disease_type, name)
values (32, 'STOMACH', '구토');
INSERT INTO disease_tag (id, disease_type, name)
values (33, 'STOMACH', '담석');
INSERT INTO disease_tag (id, disease_type, name)
values (34, 'STOMACH', '복부 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (35, 'STOMACH', '소화불량');
INSERT INTO disease_tag (id, disease_type, name)
values (36, 'STOMACH', '옆구리 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (37, 'STOMACH', '위염');
INSERT INTO disease_tag (id, disease_type, name)
values (38, 'STOMACH', '장염');
INSERT INTO disease_tag (id, disease_type, name)
values (39, 'STOMACH', '잦은 트림');
INSERT INTO disease_tag (id, disease_type, name)
values (40, 'STOMACH', '복부 비만');

INSERT INTO disease_tag (id, disease_type, name)
values (41, 'LIMB', '관절통');
INSERT INTO disease_tag (id, disease_type, name)
values (42, 'LIMB', '관절 경직');
INSERT INTO disease_tag (id, disease_type, name)
values (43, 'LIMB', '어깨 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (44, 'LIMB', '팔 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (45, 'LIMB', '다리 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (46, 'LIMB', '무릎 통증');
INSERT INTO disease_tag (id, disease_type, name)
values (47, 'LIMB', '다리 외상');
INSERT INTO disease_tag (id, disease_type, name)
values (48, 'LIMB', '팔 외상');
INSERT INTO disease_tag (id, disease_type, name)
values (49, 'LIMB', '저림');
INSERT INTO disease_tag (id, disease_type, name)
values (50, 'LIMB', '뻣뻣함');

INSERT INTO disease_tag (id, disease_type, name)
values (51, 'SKIN', '여드름');
INSERT INTO disease_tag (id, disease_type, name)
values (52, 'SKIN', '면포');
INSERT INTO disease_tag (id, disease_type, name)
values (53, 'SKIN', '각질');
INSERT INTO disease_tag (id, disease_type, name)
values (54, 'SKIN', '거친 피부');
INSERT INTO disease_tag (id, disease_type, name)
values (55, 'SKIN', '홍조');
INSERT INTO disease_tag (id, disease_type, name)
values (56, 'SKIN', '흉터');
INSERT INTO disease_tag (id, disease_type, name)
values (57, 'SKIN', '반점');
INSERT INTO disease_tag (id, disease_type, name)
values (58, 'SKIN', '색소 침착');
INSERT INTO disease_tag (id, disease_type, name)
values (59, 'SKIN', '습진');
INSERT INTO disease_tag (id, disease_type, name)
values (60, 'SKIN', '발진');

