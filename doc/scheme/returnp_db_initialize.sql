CREATE TABLE `returnp_db`.`country` (
  `iso` CHAR(2) NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  `printableName` VARCHAR(80) NOT NULL,
  `iso3` CHAR(3) NULL,
  `numcode` INT NULL,
  `regAdminNo` INT NULL,
  `createTime` DATETIME NULL,
  `updateTime` DATETIME NULL,
  PRIMARY KEY (`iso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '국가 코드 표 ';

use returnp_db;
truncate table policy;
truncate table admin;
truncate table admin_role;
truncate table company_bank_account;
truncate table api_service;
truncate table admin_menu;
truncate table sole_dist;
truncate table affiliate;
truncate table agency;
truncate table affiliate;
truncate table sale_manager;
truncate table auth_number;
truncate table branch;
truncate table category;
truncate table green_point;
truncate table member;
truncate table member_address;
truncate table member_detail;
truncate table member_failure;
truncate table member_login_log;
truncate table membership_request;
truncate table payment_pointback_record;
truncate table payment_transaction;
truncate table point_conversion_transaction;
truncate table point_convert_request;
truncate table recommender;
truncate table red_point;
truncate table point_conversion_transaction;
truncate table point_transfer_transaction;
truncate table member_auth_token;
truncate table van_ori_data;
truncate table node_file_attach;
truncate table company_info;
truncate table code;
truncate table server_manager;

INSERT INTO `returnp_db`.`admin` (`adminEmail`, `adminPassword`) VALUES ('admin', 'admintop');
INSERT INTO `returnp_db`.`admin_role` (`adminNo`, `role`, `regAdminNo`) VALUES ('1', 'ROLE_ADMIN', '0');
INSERT INTO `returnp_db`.`company_bank_account` (`regAdminNo`, `bankName`, `bankOwnerName`, `bankAccount`) VALUES ('1', '하나 은행', '주식회사 탑해피월드', '103-910054-26304');

LOCK TABLES `policy` WRITE;
INSERT INTO `policy` VALUES (1,1,0.2,0.02,0.04,0.15,0.005,0.01,0.025,0.05,1,1,1,0.00053,50000,10000,1000000,10000,1000000,0.025,0.0125,0.005,0.0025,'2018-08-30 10:40:05','2018-08-30 10:40:05'),(2,2,0.2,0.01,0.02,0.15,0.0025,0.005,0.025,0.05,1,1,1,0.00053,50000,10000,1000000,10000,1000000,0.025,0.0125,0.0025,0.00125,'2018-09-04 12:13:46','2018-09-04 12:13:46');
UNLOCK TABLES;

LOCK TABLES `company_info` WRITE;
INSERT INTO `company_info` VALUES (1,'ko','(주) GLK','서울특별시 서초구 사임당로 32 재우빌딩 1층',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Copyrightⓒreturnp All rights reserved.',NULL),(2,'ch','(주) GLK','서울특별시 서초구 사임당로 32 재우빌딩 1층',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Copyrightⓒreturnp All rights reserved.',NULL);
UNLOCK TABLES;

INSERT INTO `returnp_db`.`server_manage` (`webServerStatus`, `adminServerStatus`, `batchServerStatus`, `pointbackServerStatus`) VALUES ('1', '1', '1', '1');

LOCK TABLES `code` WRITE;
INSERT INTO `code` VALUES (1,'CD00001','노드타입','1','1','일반 회원','Y','Y',NULL,NULL),(2,'CD00001','노드타입','2','2','정회원','Y','Y',NULL,NULL),(3,'CD00001','노드타입','3','3','지사','Y','Y',NULL,NULL),(4,'CD00001','노드타입','4','4','대리점','Y','Y',NULL,NULL),(5,'CD00001','노드타입','5','5','협력 업체','Y','Y',NULL,NULL),(6,'CD00001','노드타입','6','6','영업 관리자','Y','Y',NULL,NULL),(7,'CD00001','노드타입','7','7','총판','Y','Y',NULL,NULL),(8,'CD00002','포인트백 상태','1','1','포인트백 시작','Y','Y',NULL,NULL),(9,'CD00002','포인트백 상태','2','2','포인트백 진행중','Y','Y',NULL,NULL),(10,'CD00002','포인트백 상태','3','3','포인트백 완료','Y','Y',NULL,NULL),(11,'CD00002','포인트백 상태','4','4','포인트백 취소 시작','Y','Y',NULL,NULL),(12,'CD00002','포인트백 상태','5','5','포인트백 취소중','Y','Y',NULL,NULL),(13,'CD00002','포인트백 상태','6','6','포인트백 취소 완료','Y','Y',NULL,NULL),(14,'CD00002','포인트백 상태','7','7','포인트백 중지','Y','Y',NULL,NULL),(15,'CD00003','카테고리 상태','1','1','사용','Y','Y',NULL,NULL),(16,'CD00003','카테고리 상태','2','2','미사용','Y','Y',NULL,NULL),(17,'CD00004','전환상태','1','1','전환중','Y','Y',NULL,NULL),(18,'CD00004','전환상태','2','2','전환 완료','Y','Y',NULL,NULL),(19,'CD00004','전환상태','3','3','전환 중지','Y','Y',NULL,NULL),(20,'CD00005','인증타입','1','1','이메일','Y','Y',NULL,NULL),(21,'CD00005','인증타입','2','2','핸드폰(문자인증)','Y','Y',NULL,NULL),(22,'CD00006','키워드 타입','1','1','이메일','Y','Y',NULL,NULL),(23,'CD00006','키워드 타입','2','2','전화번호','Y','Y',NULL,NULL),(24,'CD00006','키워드 타입','3','3','이름','Y','Y',NULL,NULL),(25,'CD00007','노드 상태','1','1','정상','Y','Y',NULL,NULL),(26,'CD00007','노드 상태','2','2','등록 대기','Y','Y',NULL,NULL),(27,'CD00007','노드 상태','3','3','미 인증','Y','Y',NULL,NULL),(28,'CD00007','노드 상태','4','4','인증 완료','Y','Y',NULL,NULL),(29,'CD00007','노드 상태','5','5','사용 중지','Y','Y',NULL,NULL),(30,'CD00007','노드 상태','6','6','강제 탈퇴','Y','Y',NULL,NULL),(31,'CD00007','노드 상태','7','7','사용자 탈퇴','Y','Y',NULL,NULL),(32,'CD00007','노드 상태','8','8','사용자 삭제','Y','Y',NULL,NULL),(33,'CD00008','입금 상태','1','1','입금(결제) 확인중','Y','Y',NULL,NULL),(34,'CD00008','입금 상태','2','2','입금(결제) 확인 완료','Y','Y',NULL,NULL),(35,'CD00008','입금 상태','3','3','입금(결제) 취소','Y','Y',NULL,NULL),(36,'CD00008','입금 상태','4','4','입금(결제) 환불 처리중','Y','Y',NULL,NULL),(37,'CD00008','입금 상태','5','5','입금(결제) 환불 처리 완료','Y','Y',NULL,NULL),(38,'CD00008','입금 상태','6','6','고객 입금 완료 확인 요청','Y','Y',NULL,NULL),(39,'CD00009','밴 결제 상태','1','1','결제 승인','Y','Y',NULL,NULL),(40,'CD00009','밴 결제 상태','2','2','결제 승인 취소','Y','Y',NULL,NULL),(41,'CD00009','밴 결제 상태','3','3','결제 승인 오류','Y','Y',NULL,NULL),(42,'CD00010','결제 타입','1','1','온라인 입금','Y','Y',NULL,NULL),(43,'CD00010','결제 타입','2','2','신용카드 결제','Y','Y',NULL,NULL),(44,'CD00011','포인트 종류','1','1','GREEN 포인트','Y','Y',NULL,NULL),(45,'CD00011','포인트 종류','2','2','RED 포인트','Y','Y',NULL,NULL),(46,'CD00012','등록 타입','1','1','사용자 등록','Y','Y',NULL,NULL),(47,'CD00012','등록 타입','2','2','관리자 등록','Y','Y',NULL,NULL),(48,'CD00013','적립 가능 여부','1','1','적립 가능','Y','Y',NULL,NULL),(49,'CD00013','적립 가능 여부','2','2','적립 불가','Y','Y',NULL,NULL),(50,'CD00014','포인트 사용 가능 여부 ','1','1','사용 가능','Y','Y',NULL,NULL),(51,'CD00014','포인트 사용 가능 여부 ','2','2','사용 불가','Y','Y',NULL,NULL),(52,'CD00015','결제 트랜잭션 타입','1','1','QR Code','Y','Y',NULL,NULL),(53,'CD00015','결제 트랜잭션 타입','2','2','VAN','Y','Y',NULL,NULL),(54,'CD00015','결제 트랜잭션 타입','3','3','관리자 수동 등록','Y','Y',NULL,NULL),(55,'CD00016','정렬 타입','1','asc','오름차순','Y','Y',NULL,NULL),(56,'CD00016','정렬 타입','2','desc','내림차순','Y','Y',NULL,NULL),(57,'CD00017','결제 승인 여부 ','1','1','결제 승인','Y','Y',NULL,NULL),(58,'CD00017','결제 승인 여부 ','2','2','결제 승인 취소 ','Y','Y',NULL,NULL),(59,'CD00017','결제 승인 여부 ','3','3','결제 승인 오류','Y','Y',NULL,NULL),(69,'CD00018','API 서비스 상태','1','1','서비스 승인','Y','Y',NULL,NULL),(70,'CD00018','API 서비스 상태','2','2','서비스 중지','Y','Y',NULL,NULL),(71,'CD00018','API 서비스 상태','3','3','사용기간 만료','Y','Y',NULL,NULL),(72,'CD00019','포인트 이체 타입','1','1','포인트 선물','Y','Y',NULL,NULL),(73,'CD00019','포인트 이체 타입','2','2','포인트 이체 ','Y','Y',NULL,NULL),(74,'CD00019','포인트 이체 타입','3','3','기타','Y','Y',NULL,NULL),(75,'CD00020','포인트 이체 상태','1','1','송금 완료','Y','Y',NULL,NULL),(76,'CD00020','포인트 이체 상태','2','2','송금 실패','Y','Y',NULL,NULL),(77,'CD00020','포인트 이체 상태','3','3','송금 취소','Y','Y',NULL,NULL),(78,'CD00021','게시판 타입','1','1','공지 게시판','Y','Y',NULL,NULL),(79,'CD00021','게시판 타입','2 ','2','FAQ게시판','Y','Y',NULL,NULL),(80,'CD00021','게시판 타입','3','3','일반 상담 게시판','Y','Y',NULL,NULL),(81,'CD00021','게시판 타입','4','4','가맹 상담 게시판','Y','Y',NULL,NULL),(82,'CD00022','게시판 하부 카테고리','1','1','일반회원 관련','Y','Y',NULL,NULL),(83,'CD00022','게시판 하부 카테고리','2','2','정회원 관련','Y','Y',NULL,NULL),(84,'CD00022','게시판 하부 카테고리','3','3','포인트 관련','Y','Y',NULL,NULL),(85,'CD00022','게시판 하부 카테고리','4','4','가맹 관련','Y','Y',NULL,NULL),(86,'CD00023','게시판 검색 키워드 타입','1','1','전체','Y','Y',NULL,NULL),(87,'CD00023','게시판 검색 키워드 타입','2','2','제목','Y','Y',NULL,NULL),(88,'CD00023','게시판 검색 키워드 타입','3','3','내용','Y','Y',NULL,NULL),(89,'CD00023','게시판 검색 키워드 타입','4','4','작성자','Y','Y',NULL,NULL);
UNLOCK TABLES;
