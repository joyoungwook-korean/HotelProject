# SpringBootSt_0706
HOTEL　予約システムです。

## 紹介
ホテル予約システムを利用して希望日の残っているルームを検索して選択ができるようにするシステムでする。

## 機能
１．決済システムを具現しました。

２．AWSを利用して実在に接続するようにEC2サーバーを作りました。

３．AWS S3サーバーを利用して、ルームの写真をCRUDするシステムを開発しました。

４．AWS RDS（MariaDB）を利用して、DBを構築しました。

５．Spring SecurityのOAuth2利用してソーシャルログイン機能と権限を設定しました。

６．SpringJPAを利用して、DB連関関係を具現しました。

７．管理者のページを具現して権限を設定しました。

８．管理者とCONTACTできるメール機能を具現しました。

９．管理者のページにROOM情報と予約情報とユーザー情報のCRUDができます。

１０．GoogleMAPを利用して、hotelの位置情報と自分の位置情報を具現しました。

１１.　日付と人数を指定して、残っている部屋を検索し予約することができます。

１２．AJAXを利用して、非同期CRUDと検索ができます。


### 開発言語
frontEnd : Thymeleaf, HTML, JavaScript, Jquery, bootstrap, Ajax

DB : AWS RDS( MariaDB )

Backend : Java, Springboot, SpringJPA, Spring Security

その他 : AWS S3, AWS EC2, OAuth2(Social Login)-[Kakao, naver, google], Google Map, Mail(google SMTP)
