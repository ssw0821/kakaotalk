Feature: KakaoTalk App

Scenario: 카카오톡 프로필 이름 및 상태메시지 변경
		Given 프로필 관리 화면으로 이동
		When 프로필 이름 변경
		And 프로필 상태메시지 변경
		Then 홈 화면에서 프로필 이름 및 상태메시지 변경 확인
        
		
        
