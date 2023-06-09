# 호텔 예약 시스템

## 소개

이 프로젝트는 호텔 예약 시스템을 구현한 것으로, 원하는 날짜에 남은 객실을 검색하고 예약할 수 있는 시스템입니다.

## 시연 영상

[![호텔 프로젝트](http://img.youtube.com/vi/iDZ5_dUCReo/0.jpg)](https://youtu.be/iDZ5_dUCReo?t=0s) 

## PPT 파일

[여기](https://github.com/joyoungwook-korean/SpringBootSt_0706/files/7835296/Track2_4.1.pdf)에서 PPT 파일을 확인할 수 있습니다.

## 기능

1. 결제 시스템을 구현했습니다. 현재 결제가 가능하며 서버에 저장되지만 자동으로 취소됩니다.

2. 결제 완료 시 예약 정보와 예약 정보가 담긴 QR 코드를 생성하여 휴대폰 MMS로 발송하는 서비스를 구현했습니다.

3. AWS를 사용하여 실제로 운영 가능한 EC2 서비스를 구축했습니다.

4. AWS S3 서버를 이용하여 파일 및 이미지를 실제 서버에서 가져올 수 있습니다.

5. AWS RDS(MariaDB)를 이용하여 DB 서버를 구축했습니다.

6. Spring Security의 OAuth2를 이용하여 소셜 자동 로그인 및 회원가입(Kakao, Naver, Google) 및 권한 설정을 구현했습니다.

7. Spring JPA를 이용하여 Entity를 생성하고 연관 관계를 매핑했습니다.

8. 관리자 페이지를 구현하여 유저와의 권한을 설정했습니다.

9. 관리자와 문의 가능한 메일 기능을 구현했습니다.

10. 관리자 페이지에서 객실 정보, 예약 시스템, 유저 정보의 CRUD(Create, Read, Update, Delete)가 가능한 페이지를 구현했습니다.

11. Google Map API를 이용하여 호텔의 위치 정보와 자신의 위치 정보를 찾을 수 있도록 구현했습니다.

12. 일정과 인원 수를 지정하여 남은 방을 검색하여 예약할 수 있는 로직을 구현했습니다.

13. AJAX를 이용하여 비동기적으로 CRUD를 수행할 수 있도록 만들었습니다.

## 개발 언어

프론트엔드: Thymeleaf, HTML, JavaScript, jQuery, Bootstrap, Ajax

데이터베이스: AWS RDS(MariaDB)

백엔드: Java, Spring Boot, Spring JPA, Spring Security

기타: AWS S3, AWS EC2, OAuth2(Social Login)-[Kakao, Naver, Google], Google Map, 메일(google SMTP), Naver Sens(MMS), QR 코드
