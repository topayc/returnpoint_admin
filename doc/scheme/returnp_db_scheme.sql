CREATE DATABASE  IF NOT EXISTS `returnp_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `returnp_db`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 211.254.212.90    Database: returnp_db
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminNo` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(45) NOT NULL,
  `adminEmail` varchar(100) NOT NULL,
  `adminPassword` varchar(100) NOT NULL,
  `regAdminNo` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`adminNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_file`
--

DROP TABLE IF EXISTS `admin_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_file` (
  `adminFileNo` int(11) NOT NULL AUTO_INCREMENT,
  `uploadType` varchar(10) DEFAULT NULL COMMENT '해당 파일 업로드 타입 - 코드 등록, 노드 등록 등',
  `fileNodeType` varchar(15) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `orginalFileName` varchar(100) DEFAULT NULL,
  `fileLocalPath` varchar(200) DEFAULT NULL,
  `fileWebPath` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `regAdminEmail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`adminFileNo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='관리자에 의해서 업로드된 파일 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_menu`
--

DROP TABLE IF EXISTS `admin_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_menu` (
  `adminMenuNo` int(11) NOT NULL AUTO_INCREMENT,
  `menuNameKr` varchar(100) DEFAULT NULL,
  `menuNameEn` varchar(100) DEFAULT NULL,
  `menuDeps` int(11) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  `viewReqCode` varchar(100) DEFAULT NULL,
  `menuDesKr` varchar(100) DEFAULT NULL,
  `menuDesEn` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `regAdminNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminMenuNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role` (
  `adminRoleNo` int(11) NOT NULL AUTO_INCREMENT,
  `adminNo` int(11) NOT NULL,
  `role` varchar(30) NOT NULL,
  `regAdminNo` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`adminRoleNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `affiliate`
--

DROP TABLE IF EXISTS `affiliate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliate` (
  `affiliateNo` int(11) NOT NULL AUTO_INCREMENT,
  `affiliateSerial` varchar(100) DEFAULT NULL,
  `affiliateCode` varchar(100) DEFAULT NULL,
  `affiliateName` varchar(100) DEFAULT NULL,
  `affiliateAddress` varchar(200) DEFAULT NULL,
  `affiliateTel` varchar(45) DEFAULT NULL,
  `affiliatePhone` varchar(45) DEFAULT NULL,
  `memberNo` int(11) NOT NULL,
  `agencyNo` int(11) DEFAULT NULL,
  `recommenderNo` int(11) DEFAULT NULL,
  `affiliateStatus` varchar(3) DEFAULT NULL COMMENT '1 : 미 인증\n2 : 인증 완료 \n3:  정상 사용\n4. 포인트 배분 중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 자발 탈퇴 ',
  `regType` enum('U','A') DEFAULT 'U',
  `regAdminNo` int(11) DEFAULT NULL,
  `affiliateEmail` varchar(100) DEFAULT NULL,
  `greenPointAccStatus` enum('Y','N') DEFAULT 'Y',
  `redPointAccStatus` enum('Y','N') DEFAULT 'Y',
  `greenPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `redPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`affiliateNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agency` (
  `agencyNo` int(11) NOT NULL AUTO_INCREMENT,
  `agencyCode` varchar(100) DEFAULT NULL,
  `agencyName` varchar(100) DEFAULT NULL,
  `agencyAddress` varchar(200) DEFAULT NULL,
  `agencyTel` varchar(45) DEFAULT NULL,
  `agencyPhone` varchar(45) DEFAULT NULL,
  `memberNo` int(11) NOT NULL,
  `branchNo` int(11) DEFAULT NULL,
  `recommenderNo` int(11) DEFAULT 0,
  `agencyStatus` varchar(3) DEFAULT NULL COMMENT '1 : 미 인증\n2 : 인증 완료 \n3:  정상 사용\n4. 포인트 배분 중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 자발 탈퇴 ',
  `regType` enum('U','A') DEFAULT 'A',
  `regAdminNo` int(11) DEFAULT NULL,
  `agencyEmail` varchar(100) DEFAULT NULL,
  `greenPointAccStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `redPointAccStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `greenPointUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `redPointUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`agencyNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `api_service`
--

DROP TABLE IF EXISTS `api_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_service` (
  `apiServiceNo` int(11) NOT NULL AUTO_INCREMENT,
  `apiService` varchar(100) NOT NULL,
  `apiServiceName` varchar(100) NOT NULL,
  `company` varchar(100) NOT NULL,
  `project` varchar(100) NOT NULL,
  `domain` varchar(100) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `apiKey` varchar(100) NOT NULL,
  `apiServiceStatus` varchar(3) NOT NULL,
  `expireTime` datetime DEFAULT NULL,
  `regAdminNo` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`apiServiceNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_number`
--

DROP TABLE IF EXISTS `auth_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_number` (
  `memberNo` int(11) NOT NULL,
  `authNumber` varchar(100) DEFAULT NULL COMMENT '인증번호',
  `lastTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `boardNo` int(11) NOT NULL AUTO_INCREMENT,
  `boardType` varchar(3) NOT NULL,
  `boardName` varchar(45) NOT NULL,
  `boardTitle` varchar(200) NOT NULL,
  `boardContent` text NOT NULL,
  `boardWriterNo` int(11) NOT NULL,
  `boardWriterName` varchar(100) NOT NULL,
  `boardWriterEmail` varchar(100) NOT NULL,
  `boardWriterType` varchar(2) NOT NULL COMMENT '작성자 타입  \nU : 회원 작성\nA : 어드민 작성\n',
  `viewHitCount` int(11) NOT NULL COMMENT '조회수',
  `isPublic` enum('Y','N') NOT NULL COMMENT '공개 글 여부',
  `boardPid` int(11) NOT NULL DEFAULT 0,
  `boardRef` int(11) NOT NULL COMMENT '답글 , 댓글의 원래 게시글 참조 번호',
  `boardLevel` int(11) NOT NULL COMMENT '1 : 원래 게시글 \n2 : 답글 혹은 댓글',
  `boardCate` varchar(3) DEFAULT NULL COMMENT '보드타입의 하부 세부 카테고리 ',
  `replyStatus` varchar(2) DEFAULT NULL,
  `rerv1` varchar(100) DEFAULT NULL,
  `rerv2` varchar(100) DEFAULT NULL,
  `rerv3` varchar(100) DEFAULT NULL,
  `rerv4` varchar(100) DEFAULT NULL,
  `rerv5` varchar(100) DEFAULT NULL,
  `rerv6` varchar(100) DEFAULT NULL,
  `rerv7` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`boardNo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `branchNo` int(11) NOT NULL AUTO_INCREMENT,
  `soleDistNo` int(11) DEFAULT NULL,
  `branchCode` varchar(100) DEFAULT NULL,
  `branchName` varchar(100) DEFAULT NULL,
  `branchAddress` varchar(100) DEFAULT NULL,
  `branchTel` varchar(45) DEFAULT NULL,
  `branchPhone` varchar(45) DEFAULT NULL,
  `branchStatus` varchar(3) DEFAULT NULL COMMENT '1 : 미 인증\n2 : 인증 완료 \n3:  정상 사용\n4. 포인트 배분 중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 자발 탈퇴 ',
  `memberNo` int(11) NOT NULL,
  `recommenderNo` int(11) DEFAULT 0,
  `regType` enum('U','A') DEFAULT 'U',
  `regAdminNo` int(11) DEFAULT NULL,
  `branchEmail` varchar(100) DEFAULT NULL,
  `greenPointAccStatus` enum('Y','N') DEFAULT 'Y',
  `redPointAccStatus` enum('Y','N') DEFAULT 'Y',
  `greenPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `redPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`branchNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryNo` int(11) NOT NULL AUTO_INCREMENT,
  `categoryCode` varchar(45) DEFAULT NULL,
  `parentCategoryNo` int(11) DEFAULT NULL,
  `categoryLevel` varchar(1) DEFAULT NULL,
  `categoryName` varchar(100) DEFAULT NULL,
  `categoryNameEn` varchar(100) DEFAULT NULL,
  `categoryStatus` varchar(3) DEFAULT NULL COMMENT '카테고리 상태\n1 : 사용\n2: 미사용',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`categoryNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='가맹점 카테고리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `chart_test`
--

DROP TABLE IF EXISTS `chart_test`;
/*!50001 DROP VIEW IF EXISTS `chart_test`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `chart_test` AS SELECT 
 1 AS `date`,
 1 AS `greenPoint`,
 1 AS `conversionPoint`,
 1 AS `redPoint`,
 1 AS `accRedPoint`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `chart_test1`
--

DROP TABLE IF EXISTS `chart_test1`;
/*!50001 DROP VIEW IF EXISTS `chart_test1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `chart_test1` AS SELECT 
 1 AS `date`,
 1 AS `greenPoint`,
 1 AS `conversionPoint`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `chart_test2`
--

DROP TABLE IF EXISTS `chart_test2`;
/*!50001 DROP VIEW IF EXISTS `chart_test2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `chart_test2` AS SELECT 
 1 AS `date`,
 1 AS `redPoint`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `codeNo` int(11) NOT NULL AUTO_INCREMENT,
  `groupCode` varchar(20) NOT NULL COMMENT '상위 그룹 코드',
  `groupCodeName` varchar(20) NOT NULL COMMENT '상위 그룹 코드 이름',
  `code` varchar(20) NOT NULL COMMENT '코드 값',
  `codeValue` varchar(10) NOT NULL,
  `codeName` varchar(20) NOT NULL COMMENT '코드 이름',
  `adminUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `webUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`codeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_bank_account`
--

DROP TABLE IF EXISTS `company_bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_bank_account` (
  `companyBankAccountNo` int(11) NOT NULL AUTO_INCREMENT,
  `regAdminNo` int(11) NOT NULL,
  `bankName` varchar(100) NOT NULL,
  `bankOwnerName` varchar(100) DEFAULT NULL,
  `bankAccount` varchar(100) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`companyBankAccountNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_info`
--

DROP TABLE IF EXISTS `company_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_info` (
  `companyInfo` int(11) NOT NULL AUTO_INCREMENT,
  `langLocale` varchar(10) DEFAULT NULL,
  `companyName` varchar(50) DEFAULT NULL COMMENT '회사명',
  `companyAddress` varchar(200) DEFAULT NULL,
  `companyTel` varchar(50) DEFAULT NULL COMMENT '회사 전화번호',
  `companyeEmail` varchar(50) DEFAULT NULL COMMENT '회사 이메일',
  `corporateRegistNumber` varchar(50) DEFAULT NULL COMMENT '사업자 등록번호',
  `corporateType` varchar(50) DEFAULT NULL COMMENT '등록 사업',
  `operatingHours` varchar(50) DEFAULT NULL COMMENT '고객 센터 운영 시간',
  `customerCenterTel` varchar(50) DEFAULT NULL COMMENT '고객 센터 전화번호',
  `privacyOfficer` varchar(50) DEFAULT NULL COMMENT '개인 정보 보호 책임자',
  `copyright` varchar(200) DEFAULT NULL,
  `faxNumber` varchar(45) DEFAULT NULL,
  `ceo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`companyInfo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `dash_board`
--

DROP TABLE IF EXISTS `dash_board`;
/*!50001 DROP VIEW IF EXISTS `dash_board`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `dash_board` AS SELECT 
 1 AS `totalMemberCount`,
 1 AS `totalRecommendCount`,
 1 AS `totalSaleManagerCount`,
 1 AS `totalBranchCount`,
 1 AS `totalAffiliateCount`,
 1 AS `totalAgenctCount`,
 1 AS `totalSoleDistCount`,
 1 AS `todayMemberCount`,
 1 AS `todayRecommendCount`,
 1 AS `todaySaleManagerCount`,
 1 AS `todayBranchCount`,
 1 AS `todayAffiliateCount`,
 1 AS `todayAgenctCount`,
 1 AS `todaySoleDistCount`,
 1 AS `readyMemberCount`,
 1 AS `readyRecommendCount`,
 1 AS `readySaleManagerCount`,
 1 AS `readyBranchCount`,
 1 AS `readyAffiliateCount`,
 1 AS `readyAgenctCount`,
 1 AS `readySoleDistCount`,
 1 AS `sumMemberRedPoint`,
 1 AS `sumMemberGreenPoint`,
 1 AS `sumRecommendGreenPoint`,
 1 AS `sumSaleManagerGreenPoint`,
 1 AS `sumBranchGreenPoint`,
 1 AS `sumAffiliateGreenPoint`,
 1 AS `sumAgenctGreenPoint`,
 1 AS `sumSoleDistGreenPoint`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `dates`
--

DROP TABLE IF EXISTS `dates`;
/*!50001 DROP VIEW IF EXISTS `dates`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `dates` AS SELECT 
 1 AS `date`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `digits`
--

DROP TABLE IF EXISTS `digits`;
/*!50001 DROP VIEW IF EXISTS `digits`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `digits` AS SELECT 
 1 AS `digit`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `exec_point`
--

DROP TABLE IF EXISTS `exec_point`;
/*!50001 DROP VIEW IF EXISTS `exec_point`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `exec_point` AS SELECT 
 1 AS `pointConversionTansactionNo`,
 1 AS `pointConvertRequestNo`,
 1 AS `memberNo`,
 1 AS `eachPoint`,
 1 AS `accPoint`,
 1 AS `remainPoint`,
 1 AS `accRate`,
 1 AS `conversionAccPoint`,
 1 AS `conversionEachPoint`,
 1 AS `conversionAccRate`,
 1 AS `conversionStatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `green_point`
--

DROP TABLE IF EXISTS `green_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `green_point` (
  `greenPointNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `nodeNo` int(11) NOT NULL,
  `nodeType` varchar(3) NOT NULL,
  `nodeTypeName` varchar(45) DEFAULT NULL,
  `pointAmount` float NOT NULL,
  `greenPointCreateTime` datetime DEFAULT NULL,
  `greenPointUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`greenPointNo`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `memberNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberName` varchar(100) NOT NULL,
  `memberEmail` varchar(100) NOT NULL,
  `memberPassword` varchar(100) NOT NULL,
  `memberPassword2` varchar(100) DEFAULT NULL,
  `memberType` varchar(10) DEFAULT NULL COMMENT '1 : 일반 회원\n2 : 정회원 (추천인)\n3 : 지사\n4 : 대리점 \n5 : 협력업체(가맹점_ \n6 : 영업 관리자\n& : 총판\n\n위의 모든 값을 가질 수 있음 ',
  `memberStatus` varchar(3) DEFAULT '1' COMMENT '1 : 정상\n2 : 등록대기 \n3:  미인증\n4. 사용중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 탈퇴\n8: 사죵자 탈퇴 ',
  `memberAuthType` varchar(3) DEFAULT '1' COMMENT '1 : 이메일 인증\n2 : 문자 인증',
  `memberPhone` varchar(45) NOT NULL,
  `recommenderNo` int(11) DEFAULT 0,
  `joinRoute` varchar(3) DEFAULT NULL COMMENT '가입 경로 ',
  `statusReason` varchar(200) DEFAULT NULL,
  `isSoleDist` enum('Y','N') DEFAULT 'N',
  `isRecommender` enum('Y','N') DEFAULT 'N',
  `isSaleManager` enum('Y','N') DEFAULT 'N',
  `isBranch` enum('Y','N') DEFAULT 'N',
  `isAffiliate` enum('Y','N') DEFAULT 'N',
  `isAgency` enum('Y','N') DEFAULT 'N',
  `regType` enum('U','A') DEFAULT 'U' COMMENT '1: 본인 등록\n2 : 관리자에 의한 등록',
  `regAdminNo` int(11) DEFAULT 0,
  `greenPointAccStatus` enum('Y','N') DEFAULT 'Y' COMMENT '그린포인트 적립 가능 여부',
  `redPointAccStatus` enum('Y','N') DEFAULT 'Y' COMMENT '레드 포인트 적립 가능 여부',
  `greenPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `redPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberNo`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_address`
--

DROP TABLE IF EXISTS `member_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_address` (
  `memberAddressNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `nodeNo` int(11) DEFAULT NULL,
  `nodeType` varchar(3) DEFAULT NULL,
  `roadFullAddr` varchar(200) DEFAULT NULL,
  `roadAddrPart1` varchar(200) DEFAULT NULL,
  `roadAddrPart2` varchar(200) DEFAULT NULL,
  `addrDetail` varchar(200) DEFAULT NULL,
  `engAddr` varchar(300) DEFAULT NULL,
  `jibunAddr` varchar(200) DEFAULT NULL,
  `zipNo` varchar(5) DEFAULT NULL,
  `admCd` varchar(30) DEFAULT NULL,
  `rnMgtSn` varchar(30) DEFAULT NULL,
  `bdMgtSn` varchar(30) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  `regAdminNo` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberAddressNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_auth_token`
--

DROP TABLE IF EXISTS `member_auth_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_auth_token` (
  `memberAuthTokenNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) DEFAULT NULL,
  `memberEmail` varchar(100) DEFAULT NULL,
  `userAuthToken` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `memberPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`memberAuthTokenNo`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='회원별 token 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_card_info`
--

DROP TABLE IF EXISTS `member_card_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_card_info` (
  `memberCardInfoNo` int(11) NOT NULL AUTO_INCREMENT,
  `cardName` varchar(100) NOT NULL,
  `cardAccount` varchar(100) NOT NULL,
  `cardExpiration` varchar(45) NOT NULL,
  `memberNo` int(11) NOT NULL,
  `regAdminNo` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberCardInfoNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_detail`
--

DROP TABLE IF EXISTS `member_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_detail` (
  `memberDetailNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `regAdminNo` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberDetailNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_failure`
--

DROP TABLE IF EXISTS `member_failure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_failure` (
  `memberNo` int(11) NOT NULL,
  `failureCnt` int(11) NOT NULL DEFAULT 0 COMMENT '로그인 실패 휫수',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `member_green_point`
--

DROP TABLE IF EXISTS `member_green_point`;
/*!50001 DROP VIEW IF EXISTS `member_green_point`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `member_green_point` AS SELECT 
 1 AS `memberNo`,
 1 AS `sumMemberGreenPoint`,
 1 AS `sumRecommendGreenPoint`,
 1 AS `sumBranchGreenPoint`,
 1 AS `sumAgenctGreenPoint`,
 1 AS `sumAffiliateGreenPoint`,
 1 AS `sumSaleManagerGreenPoint`,
 1 AS `sumSoleDistGreenPoint`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `member_login_log`
--

DROP TABLE IF EXISTS `member_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_login_log` (
  `memberLoginLogNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `memberEmail` varchar(100) NOT NULL,
  `loginTryCount` int(11) NOT NULL,
  `loginResut` enum('Y','N') DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`memberLoginLogNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `member_red_point`
--

DROP TABLE IF EXISTS `member_red_point`;
/*!50001 DROP VIEW IF EXISTS `member_red_point`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `member_red_point` AS SELECT 
 1 AS `memberNo`,
 1 AS `sumMemberRedPoint`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `membership_request`
--

DROP TABLE IF EXISTS `membership_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membership_request` (
  `membershipRequestNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `depositor` varchar(100) NOT NULL,
  `companyBankAccountNo` int(11) NOT NULL,
  `recommenderNo` int(11) DEFAULT NULL,
  `paymentAmount` int(11) NOT NULL,
  `paymentStatus` enum('1','2','3','4','5','6') NOT NULL DEFAULT '3' COMMENT '1 : 입금(결제) 확인중\n2 : 입금(결제) 확인 완료\n3 : 입금(결제) 취소\n4 : 입금(결제) 환불 처리중\n5 : 입금(결제) 환불 완료\n6: 고객 입금 완료 확인 요청\n',
  `gradeType` varchar(3) DEFAULT NULL COMMENT '1 : 일반 회원\n2 : 정회원(추천인)\n3 : 영업 관리자\n4 : 협력 업체 \n4 : 대리점 \n5 : 지사 ',
  `paymentType` varchar(10) NOT NULL COMMENT '1 : 온라인 입금\n2 : 신용 카드 ',
  `regType` enum('U','A') DEFAULT 'U',
  `regAdminNo` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`membershipRequestNo`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `node_file_attach`
--

DROP TABLE IF EXISTS `node_file_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `node_file_attach` (
  `nodeFileAttachNo` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) NOT NULL,
  `originalFileName` varchar(255) NOT NULL,
  `fileType` varchar(2) NOT NULL COMMENT '업로드 파일 종류\n1 : 일반 회원\n2 : 정회원 (추천인)\n3 : 지사\n4 : 대리점 \n5 : 협력업체(가맹점_ \n6 : 영업 관리자\n& : 총판',
  `fileSize` varchar(45) NOT NULL,
  `fileExt` varchar(10) NOT NULL,
  `regType` enum('U','A') NOT NULL DEFAULT 'U',
  `regAdminNo` int(11) NOT NULL,
  `fileLocalPath` varchar(255) NOT NULL,
  `fileWebPath` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`nodeFileAttachNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `node_tree`
--

DROP TABLE IF EXISTS `node_tree`;
/*!50001 DROP VIEW IF EXISTS `node_tree`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `node_tree` AS SELECT 
 1 AS `node_type`,
 1 AS `node_id`,
 1 AS `up_node_id`,
 1 AS `node_no`,
 1 AS `up_node_no`,
 1 AS `name`,
 1 AS `recommenderNo`,
 1 AS `saleManagerNo`,
 1 AS `memberNo`,
 1 AS `recommenderMemberNo`,
 1 AS `saleManagerMemberNo`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `numbers`
--

DROP TABLE IF EXISTS `numbers`;
/*!50001 DROP VIEW IF EXISTS `numbers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `numbers` AS SELECT 
 1 AS `number`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `payment_pointback_record`
--

DROP TABLE IF EXISTS `payment_pointback_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_pointback_record` (
  `paymentPointbackRecordNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL COMMENT '참조 회원 번호',
  `paymentTransactionNo` int(11) NOT NULL COMMENT '해당 포인트 백이 발생한 vanPaymentTransaction테이블의 row  번호',
  `nodeNo` int(11) DEFAULT NULL,
  `nodeType` varchar(3) NOT NULL,
  `accRate` float DEFAULT NULL,
  `pointbackAmount` float NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`paymentPointbackRecordNo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='소비자 결제(결제 내역 직접 등록, 큐알코드에 의한 결제 등록, Van 연동에 의한 결제 등록)에 대하여 결제금액에 따른\n포인트가 적립된 세부 레코드 내역 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_transaction`
--

DROP TABLE IF EXISTS `payment_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_transaction` (
  `paymentTransactionNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `memberName` varchar(100) NOT NULL,
  `memberPhone` varchar(45) DEFAULT NULL,
  `memberEmail` varchar(100) NOT NULL,
  `nodeType` varchar(2) DEFAULT NULL,
  `nodeNo` int(11) DEFAULT NULL,
  `nodeEmail` varchar(100) DEFAULT NULL,
  `nodeName` varchar(100) DEFAULT NULL,
  `nodePhone` varchar(45) DEFAULT NULL,
  `affiliateNo` int(11) DEFAULT NULL,
  `affiliateSerial` varchar(100) DEFAULT NULL,
  `paymentApprovalAmount` int(11) NOT NULL,
  `paymentApprovalNumber` varchar(100) NOT NULL,
  `paymentApprovalStatus` enum('1','2','3','4') NOT NULL COMMENT '1 : 결제 승인 \n2 : 결제 승인 취소  \n3 : 결제 승인 오류',
  `pointBackStatus` varchar(3) DEFAULT '2' COMMENT '해당 거래 내역의 포인트백 상태\n1 : 적립 시작\n2 : 적립 완료\n3: 적립 취소 시작\n4 : 적립 취소 완료\n5 : 중지',
  `paymentTransactionType` varchar(3) NOT NULL COMMENT '1 : 모바일 QR 코드에 의한 등록\n2 : 밴사 연동에 의한 등록\n2 : 관리자에 의한 등록',
  `paymentApprovalDateTime` datetime NOT NULL,
  `orgPaymentData` varchar(300) DEFAULT NULL COMMENT '결제 정보 원문 데이타 ',
  `regAdminNo` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`paymentTransactionNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='큐알코드 및 관리자, 포스에 의한 결제 내역 저장 테이블\n이 테이블을 참조로 각 노드의 Green Point 를 적립 시킴';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `plan_point`
--

DROP TABLE IF EXISTS `plan_point`;
/*!50001 DROP VIEW IF EXISTS `plan_point`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `plan_point` AS SELECT 
 1 AS `pointConversionTansactionNo`,
 1 AS `pointConvertRequestNo`,
 1 AS `memberNo`,
 1 AS `eachPoint`,
 1 AS `accPoint`,
 1 AS `remainPoint`,
 1 AS `accRate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `point_conversion_plan`
--

DROP TABLE IF EXISTS `point_conversion_plan`;
/*!50001 DROP VIEW IF EXISTS `point_conversion_plan`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `point_conversion_plan` AS SELECT 
 1 AS `memberNo`,
 1 AS `nodeType`,
 1 AS `pointAmount`,
 1 AS `conversionAccPoint`,
 1 AS `pointTransRate`,
 1 AS `conversionStatus`,
 1 AS `createTime`,
 1 AS `updateTime`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `point_conversion_transaction`
--

DROP TABLE IF EXISTS `point_conversion_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `point_conversion_transaction` (
  `pointConversionTansactionNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `nodeType` varchar(3) DEFAULT NULL,
  `conversionTotalPoint` float DEFAULT 0,
  `conversionAccPoint` float DEFAULT 0,
  `conversionAccRate` float DEFAULT 0,
  `conversionStatus` varchar(3) DEFAULT '1' COMMENT '1 : 전환중\n2 : 전환 중지\n',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `pointConvertRequestNo` int(11) DEFAULT 0 COMMENT '일단 돌아가게 하기 위해 임시적으로 추가함.. 재동씨 작업 완료되면 제거해야함',
  `pointTransRate` float DEFAULT 0 COMMENT '일단 돌아가게 하기 위해 임시적으로 추가함.. 재동씨 작업 완료되면 제거해야함',
  PRIMARY KEY (`pointConversionTansactionNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2385 DEFAULT CHARSET=utf8 COMMENT='R POINT 적립 세부 내역 테이블\nR Point 적립 후 해당 테이블에 이력 인서트';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `point_convert_request`
--

DROP TABLE IF EXISTS `point_convert_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `point_convert_request` (
  `pointConvertRequestNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `greenPointNo` int(11) DEFAULT NULL,
  `nodeType` varchar(3) NOT NULL,
  `requestNodeTypeName` varchar(30) NOT NULL,
  `convertPointAmount` float NOT NULL,
  `regType` enum('U','A') DEFAULT NULL,
  `regAdminNo` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pointConvertRequestNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='그린 포인트 전환 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `point_deposit_transaction`
--

DROP TABLE IF EXISTS `point_deposit_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `point_deposit_transaction` (
  `pointDepositTransactionNo` int(11) NOT NULL AUTO_INCREMENT,
  `depositAmount` int(11) NOT NULL,
  `depositMemberNo` int(11) NOT NULL,
  `depositMemberType` varchar(45) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pointDepositTransactionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `point_transfer_transaction`
--

DROP TABLE IF EXISTS `point_transfer_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `point_transfer_transaction` (
  `pointTransferTransactionNo` int(11) NOT NULL AUTO_INCREMENT,
  `pointTransferer` int(11) NOT NULL COMMENT '포인트를 송금한 멤버 번호',
  `pointReceiver` int(11) NOT NULL COMMENT '포인트를 송금할 대상자 멤버 번호',
  `pointTransferAmount` float NOT NULL COMMENT '송금 금액',
  `pointNode` varchar(3) DEFAULT NULL COMMENT 'Green Point 전환의 경우 아래의 값중에 하나가 필요. Red Point 의 경우 아래 값 불필요\n1 : 일반 회원\n2 : 정회원 (추천인)\n3 : 지사\n4 : 대리점 \n5 : 협력업체(가맹점_ \n6 : 영업 관리자\n& : 총판',
  `pointType` varchar(20) NOT NULL COMMENT '송금할 포인트 타입\n1 : Green Point\n2 : Red Point',
  `pointTransferType` varchar(3) NOT NULL COMMENT '송금 타입\n1 : 선물\n2 : 이체\n3 : 기타 ',
  `pointTransferStatus` varchar(3) NOT NULL COMMENT '송금 상태\n1 : 송금 완료\n2 : 송금 실패\n3 : 송금 강제 취소 (관리자만 가능) ',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`pointTransferTransactionNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='포인트 이체(선물, 송금)  내역 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy` (
  `policyNo` int(11) NOT NULL AUTO_INCREMENT,
  `regAdminNo` int(11) NOT NULL,
  `branchComm` float NOT NULL COMMENT '지사 수수료',
  `soleDistComm` float NOT NULL COMMENT '총판 수수료',
  `agancyComm` float NOT NULL COMMENT '대리점 수수료',
  `affiliateComm` float NOT NULL COMMENT '협력 업체 수수료',
  `branchRecComm` float NOT NULL COMMENT '지점 추천인 수수료',
  `agancyRecComm` float NOT NULL COMMENT '대리점 추천인 수수료',
  `affiliateRecComm` float NOT NULL COMMENT '협력 업체 추천인 수수료',
  `customerRecCom` float NOT NULL COMMENT '고객 추천인 수수료',
  `customerComm` float NOT NULL COMMENT '고객 수수료',
  `pointTransRate` float NOT NULL COMMENT '포인트 실제 현금 전환 비율',
  `pointTransLimit` float NOT NULL COMMENT '포인트 전환 최소 금액',
  `redPointAccRate` float NOT NULL COMMENT '레드 포인트 일별 적립율',
  `membershipTransLimit` int(11) NOT NULL COMMENT '멤버쉽 (추천인, 정회원) 전환 최소 금액',
  `gPointMovingMinLimit` int(11) NOT NULL COMMENT 'G point 이체 최저 금액 한도',
  `gPointMovingMaxLimit` int(11) NOT NULL COMMENT 'G point 이체 최고 금액 한도',
  `rPointMovingMinLimit` int(11) NOT NULL COMMENT 'R  point 이체 최저 금액 한도',
  `rPointMovingMaxLimit` int(11) NOT NULL COMMENT 'R point 이체 최고 금액 한도',
  `customerRecManagerComm` float DEFAULT NULL,
  `affiliateRecManagerComm` float DEFAULT NULL,
  `agancyRecManagerComm` float DEFAULT NULL,
  `branchRecManagerComm` float DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`policyNo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recommender`
--

DROP TABLE IF EXISTS `recommender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommender` (
  `recommenderNo` int(11) NOT NULL AUTO_INCREMENT,
  `recommenderCode` varchar(100) DEFAULT NULL,
  `recommenderName` varchar(100) DEFAULT NULL,
  `recommenderAddress` varchar(200) DEFAULT NULL,
  `recommenderTel` varchar(45) DEFAULT NULL,
  `recommenderPhone` varchar(45) DEFAULT NULL,
  `memberNo` int(11) NOT NULL,
  `saleManagerNo` int(11) DEFAULT 0,
  `recommenderStatus` varchar(3) NOT NULL DEFAULT '3' COMMENT '1 : 미 인증\n2 : 인증 완료 \n3:  정상 사용\n4. 포인트 배분 중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 자발 탈퇴 ',
  `regType` enum('U','A') DEFAULT 'A',
  `regAdminNo` int(11) DEFAULT NULL,
  `recommenderEmail` varchar(100) DEFAULT NULL,
  `greenPointAccStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `redPointAccStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `greenPointUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `redPointUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`recommenderNo`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `red_point`
--

DROP TABLE IF EXISTS `red_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `red_point` (
  `redPointNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `pointAmount` float NOT NULL DEFAULT 0,
  `redPointCreateTime` datetime DEFAULT NULL,
  `redPointUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`redPointNo`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sale_manager`
--

DROP TABLE IF EXISTS `sale_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_manager` (
  `saleManagerNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `saleManagerCode` varchar(100) NOT NULL,
  `saleManagerName` varchar(100) DEFAULT NULL,
  `saleManagerAddress` varchar(200) DEFAULT NULL,
  `saleManagerTel` varchar(45) DEFAULT NULL,
  `saleManagerPhone` varchar(45) DEFAULT NULL,
  `saleManagerStatus` varchar(3) NOT NULL COMMENT '1 : 미 인증\n2 : 인증 완료 \n3:  정상 사용\n4. 포인트 배분 중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 자발 탈퇴 ',
  `regType` enum('U','A') DEFAULT 'A',
  `regAdminNo` int(11) DEFAULT NULL,
  `saleManagerEmail` varchar(100) NOT NULL,
  `greenPointAccStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `redPointAccStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `greenPointUseStatus` enum('Y','N') NOT NULL DEFAULT 'Y',
  `redPointUseStatus` enum('Y','N') DEFAULT 'Y',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`saleManagerNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server_manage`
--

DROP TABLE IF EXISTS `server_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_manage` (
  `serverManageNo` int(11) NOT NULL AUTO_INCREMENT,
  `webServerStatus` varchar(1) NOT NULL DEFAULT '1' COMMENT '1 : 정상 운영\n2 : 점검중',
  `adminServerStatus` varchar(2) NOT NULL DEFAULT '1',
  `batchServerStatus` varchar(3) NOT NULL DEFAULT '1',
  `pointbackServerStatus` varchar(4) NOT NULL DEFAULT '1',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`serverManageNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sole_dist`
--

DROP TABLE IF EXISTS `sole_dist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sole_dist` (
  `soleDistNo` int(11) NOT NULL AUTO_INCREMENT,
  `memberNo` int(11) NOT NULL,
  `soleDistCode` varchar(100) NOT NULL,
  `soleDistEmail` varchar(100) DEFAULT NULL,
  `soleDistName` varchar(100) DEFAULT NULL,
  `soleDistAddress` varchar(200) DEFAULT NULL,
  `soleDistTel` varchar(45) DEFAULT NULL,
  `soleDistPhone` varchar(45) DEFAULT NULL,
  `soleDistStatus` varchar(3) NOT NULL COMMENT '1 : 미 인증\n2 : 인증 완료 \n3:  정상 사용\n4. 포인트 배분 중지 \n5 : 사용 정지 \n6 : 강제 탈퇴\n7 : 사용자 자발 탈퇴 ',
  `regType` enum('U','A') DEFAULT NULL,
  `regAdminNo` int(11) DEFAULT NULL,
  `greenPointAccStatus` enum('Y','N') NOT NULL,
  `redPointAccStatus` enum('Y','N') NOT NULL,
  `greenPointUseStatus` enum('Y','N') NOT NULL,
  `redPointUseStatus` enum('Y','N') NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`soleDistNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `van_ori_data`
--

DROP TABLE IF EXISTS `van_ori_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `van_ori_data` (
  `vanOriDataNo` int(11) NOT NULL AUTO_INCREMENT,
  `vanData` varchar(500) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`vanOriDataNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `chart_test`
--

/*!50001 DROP VIEW IF EXISTS `chart_test`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `chart_test` AS select `tb1`.`date` AS `date`,`tb1`.`greenPoint` AS `greenPoint`,`tb1`.`conversionPoint` AS `conversionPoint`,`tb2`.`redPoint` AS `redPoint`,sum(`tb2`.`redPoint`) AS `accRedPoint` from (`chart_test1` `tb1` join `chart_test2` `tb2` on(`tb2`.`date` < `tb1`.`date`)) group by `tb1`.`date` order by `tb1`.`date` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `chart_test1`
--

/*!50001 DROP VIEW IF EXISTS `chart_test1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `chart_test1` AS select `dates`.`date` AS `date`,floor(rand() * 100) AS `greenPoint`,floor(rand() * 100) AS `conversionPoint` from `dates` where `dates`.`date` between curdate() + interval -30 day and curdate() */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `chart_test2`
--

/*!50001 DROP VIEW IF EXISTS `chart_test2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `chart_test2` AS select `dates`.`date` AS `date`,floor(rand() * 100) AS `redPoint` from `dates` where `dates`.`date` between curdate() + interval -30 day and curdate() */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `dash_board`
--

/*!50001 DROP VIEW IF EXISTS `dash_board`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `dash_board` AS select count(`mb`.`memberNo`) AS `totalMemberCount`,sum(`mb`.`isRecommender` = 'Y') AS `totalRecommendCount`,sum(`mb`.`isSaleManager` = 'Y') AS `totalSaleManagerCount`,sum(`mb`.`isBranch` = 'Y') AS `totalBranchCount`,sum(`mb`.`isAffiliate` = 'Y') AS `totalAffiliateCount`,sum(`mb`.`isAgency` = 'Y') AS `totalAgenctCount`,sum(`mb`.`isSoleDist` = 'Y') AS `totalSoleDistCount`,sum(`mb`.`createTime` > curdate()) AS `todayMemberCount`,sum(`mb`.`isRecommender` = 'Y' and `rc`.`createTime` > curdate()) AS `todayRecommendCount`,sum(`mb`.`isSaleManager` = 'Y' and `sm`.`createTime` > curdate()) AS `todaySaleManagerCount`,sum(`mb`.`isBranch` = 'Y' and `br`.`createTime` > curdate()) AS `todayBranchCount`,sum(`mb`.`isAffiliate` = 'Y' and `af`.`createTime` > curdate()) AS `todayAffiliateCount`,sum(`mb`.`isAgency` = 'Y' and `ag`.`createTime` > curdate()) AS `todayAgenctCount`,sum(`mb`.`isSoleDist` = 'Y' and `sd`.`createTime` > curdate()) AS `todaySoleDistCount`,sum(`mb`.`memberStatus` = '3') AS `readyMemberCount`,sum(`mb`.`isRecommender` = 'Y' and `rc`.`recommenderStatus` = '1') AS `readyRecommendCount`,sum(`mb`.`isSaleManager` = 'Y' and `sm`.`saleManagerStatus` = '1') AS `readySaleManagerCount`,sum(`mb`.`isBranch` = 'Y' and `br`.`branchStatus` = '1') AS `readyBranchCount`,sum(`mb`.`isAffiliate` = 'Y' and `af`.`affiliateStatus` = '1') AS `readyAffiliateCount`,sum(`mb`.`isAgency` = 'Y' and `ag`.`agencyStatus` = '1') AS `readyAgenctCount`,sum(`mb`.`isSoleDist` = 'Y' and `sd`.`soleDistStatus` = '1') AS `readySoleDistCount`,round(sum(`red`.`sumMemberRedPoint`),0) AS `sumMemberRedPoint`,round(`green`.`sumMemberGreenPoint`,0) AS `sumMemberGreenPoint`,round(`green`.`sumRecommendGreenPoint`,0) AS `sumRecommendGreenPoint`,round(`green`.`sumSaleManagerGreenPoint`,0) AS `sumSaleManagerGreenPoint`,round(`green`.`sumBranchGreenPoint`,0) AS `sumBranchGreenPoint`,round(`green`.`sumAffiliateGreenPoint`,0) AS `sumAffiliateGreenPoint`,round(`green`.`sumAgenctGreenPoint`,0) AS `sumAgenctGreenPoint`,round(`green`.`sumSoleDistGreenPoint`,0) AS `sumSoleDistGreenPoint` from ((((((((`member` `mb` left join `recommender` `rc` on(`mb`.`memberNo` = `rc`.`memberNo`)) left join `sale_manager` `sm` on(`mb`.`memberNo` = `sm`.`memberNo`)) left join `branch` `br` on(`mb`.`memberNo` = `br`.`memberNo`)) left join `affiliate` `af` on(`mb`.`memberNo` = `af`.`memberNo`)) left join `agency` `ag` on(`mb`.`memberNo` = `ag`.`memberNo`)) left join `sole_dist` `sd` on(`mb`.`memberNo` = `sd`.`memberNo`)) left join `member_red_point` `red` on(`mb`.`memberNo` = `red`.`memberNo`)) left join `member_green_point` `green` on(`mb`.`memberNo` = `green`.`memberNo`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `dates`
--

/*!50001 DROP VIEW IF EXISTS `dates`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `dates` AS select curdate() - interval `numbers`.`number` day AS `date` from `numbers` union all select curdate() + interval (`numbers`.`number` + 1) day AS `date` from `numbers` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `digits`
--

/*!50001 DROP VIEW IF EXISTS `digits`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `digits` AS select 0 AS `digit` union all select 1 AS `1` union all select 2 AS `2` union all select 3 AS `3` union all select 4 AS `4` union all select 5 AS `5` union all select 6 AS `6` union all select 7 AS `7` union all select 8 AS `8` union all select 9 AS `9` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `exec_point`
--

/*!50001 DROP VIEW IF EXISTS `exec_point`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `exec_point` AS select `b`.`pointConversionTansactionNo` AS `pointConversionTansactionNo`,`b`.`pointConvertRequestNo` AS `pointConvertRequestNo`,`b`.`memberNo` AS `memberNo`,`b`.`eachPoint` AS `eachPoint`,`b`.`accPoint` AS `accPoint`,`b`.`remainPoint` AS `remainPoint`,`b`.`accRate` AS `accRate`,if(`b`.`accPoint` > `a`.`conversionTotalPoint` or `a`.`conversionTotalPoint` = 0,`a`.`conversionTotalPoint`,`b`.`accPoint`) AS `conversionAccPoint`,if(`b`.`accPoint` > `a`.`conversionTotalPoint` or `a`.`conversionTotalPoint` = 0,`a`.`conversionTotalPoint` - `b`.`accPoint`,`b`.`eachPoint`) AS `conversionEachPoint`,if(`b`.`accRate` > 1 or `a`.`conversionTotalPoint` = 0,1,`b`.`accRate`) AS `conversionAccRate`,if(1 > `b`.`accRate` and `a`.`conversionTotalPoint` > 0,1,3) AS `conversionStatus` from (`plan_point` `b` join `point_conversion_transaction` `a` on(`a`.`pointConversionTansactionNo` = `b`.`pointConversionTansactionNo` and `a`.`pointConvertRequestNo` = `b`.`pointConvertRequestNo` and `a`.`memberNo` = `b`.`memberNo`)) where `a`.`conversionStatus` = 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `member_green_point`
--

/*!50001 DROP VIEW IF EXISTS `member_green_point`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `member_green_point` AS select `green_point`.`memberNo` AS `memberNo`,sum(if(`green_point`.`nodeType` = '1',`green_point`.`pointAmount`,0)) AS `sumMemberGreenPoint`,sum(if(`green_point`.`nodeType` = '2',`green_point`.`pointAmount`,0)) AS `sumRecommendGreenPoint`,sum(if(`green_point`.`nodeType` = '3',`green_point`.`pointAmount`,0)) AS `sumBranchGreenPoint`,sum(if(`green_point`.`nodeType` = '4',`green_point`.`pointAmount`,0)) AS `sumAgenctGreenPoint`,sum(if(`green_point`.`nodeType` = '5',`green_point`.`pointAmount`,0)) AS `sumAffiliateGreenPoint`,sum(if(`green_point`.`nodeType` = '6',`green_point`.`pointAmount`,0)) AS `sumSaleManagerGreenPoint`,sum(if(`green_point`.`nodeType` = '7',`green_point`.`pointAmount`,0)) AS `sumSoleDistGreenPoint` from `green_point` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `member_red_point`
--

/*!50001 DROP VIEW IF EXISTS `member_red_point`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `member_red_point` AS select `red_point`.`memberNo` AS `memberNo`,sum(`red_point`.`pointAmount`) AS `sumMemberRedPoint` from `red_point` group by `red_point`.`memberNo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `node_tree`
--

/*!50001 DROP VIEW IF EXISTS `node_tree`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `node_tree` AS select '7' AS `node_type`,concat('7','-',`sole_dist`.`soleDistNo`) AS `node_id`,NULL AS `up_node_id`,`sole_dist`.`soleDistNo` AS `node_no`,NULL AS `up_node_no`,`sole_dist`.`soleDistName` AS `name`,NULL AS `recommenderNo`,NULL AS `saleManagerNo`,`sole_dist`.`memberNo` AS `memberNo`,NULL AS `recommenderMemberNo`,NULL AS `saleManagerMemberNo` from `sole_dist` union all select '3' AS `node_type`,concat('3','-',`a`.`branchNo`) AS `node_id`,concat('7','-',`a`.`soleDistNo`) AS `up_node_id`,`a`.`branchNo` AS `node_no`,`a`.`soleDistNo` AS `up_node_no`,`a`.`branchName` AS `name`,`a`.`recommenderNo` AS `recommenderNo`,`b`.`saleManagerNo` AS `saleManagerNo`,`a`.`memberNo` AS `memberNo`,`b`.`memberNo` AS `recommenderMemberNo`,`c`.`memberNo` AS `saleManagerMemberNo` from ((`branch` `a` left join `recommender` `b` on(`a`.`recommenderNo` = `b`.`recommenderNo`)) left join `sale_manager` `c` on(`b`.`saleManagerNo` = `c`.`saleManagerNo`)) union all select '4' AS `node_type`,concat('4','-',`a`.`agencyNo`) AS `node_id`,concat('3','-',`a`.`branchNo`) AS `up_node_id`,`a`.`agencyNo` AS `node_no`,`a`.`branchNo` AS `up_node_no`,`a`.`agencyName` AS `name`,`a`.`recommenderNo` AS `recommenderNo`,`b`.`saleManagerNo` AS `saleManagerNo`,`a`.`memberNo` AS `memberNo`,`b`.`memberNo` AS `recommenderMemberNo`,`c`.`memberNo` AS `saleManagerMemberNo` from ((`agency` `a` left join `recommender` `b` on(`a`.`recommenderNo` = `b`.`recommenderNo`)) left join `sale_manager` `c` on(`b`.`saleManagerNo` = `c`.`saleManagerNo`)) union all select '5' AS `node_type`,concat('5','-',`a`.`affiliateNo`) AS `node_id`,concat('4','-',`a`.`agencyNo`) AS `up_node_id`,`a`.`affiliateNo` AS `node_no`,`a`.`agencyNo` AS `up_node_no`,`a`.`affiliateName` AS `name`,`a`.`recommenderNo` AS `recommenderNo`,`b`.`saleManagerNo` AS `saleManagerNo`,`a`.`memberNo` AS `memberNo`,`b`.`memberNo` AS `recommenderMemberNo`,`c`.`memberNo` AS `saleManagerMemberNo` from ((`affiliate` `a` left join `recommender` `b` on(`a`.`recommenderNo` = `b`.`recommenderNo`)) left join `sale_manager` `c` on(`b`.`saleManagerNo` = `c`.`saleManagerNo`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `numbers`
--

/*!50001 DROP VIEW IF EXISTS `numbers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `numbers` AS select `ones`.`digit` + `tens`.`digit` * 10 + `hundreds`.`digit` * 100 + `thousands`.`digit` * 1000 AS `number` from (((`digits` `ones` join `digits` `tens`) join `digits` `hundreds`) join `digits` `thousands`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `plan_point`
--

/*!50001 DROP VIEW IF EXISTS `plan_point`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `plan_point` AS select `point_conversion_transaction`.`pointConversionTansactionNo` AS `pointConversionTansactionNo`,`point_conversion_transaction`.`pointConvertRequestNo` AS `pointConvertRequestNo`,`point_conversion_transaction`.`memberNo` AS `memberNo`,round(`point_conversion_transaction`.`conversionTotalPoint` * `point_conversion_transaction`.`pointTransRate`,2) AS `eachPoint`,round(`point_conversion_transaction`.`conversionAccPoint` + `point_conversion_transaction`.`conversionTotalPoint` * `point_conversion_transaction`.`pointTransRate`,0) AS `accPoint`,if(`point_conversion_transaction`.`conversionTotalPoint` - `point_conversion_transaction`.`conversionAccPoint` > 0,`point_conversion_transaction`.`conversionTotalPoint` - `point_conversion_transaction`.`conversionAccPoint`,0) AS `remainPoint`,round(`point_conversion_transaction`.`conversionAccRate` + `point_conversion_transaction`.`pointTransRate`,2) AS `accRate` from `point_conversion_transaction` where `point_conversion_transaction`.`conversionStatus` = 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `point_conversion_plan`
--

/*!50001 DROP VIEW IF EXISTS `point_conversion_plan`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `point_conversion_plan` AS select `gp`.`memberNo` AS `memberNo`,`gp`.`nodeType` AS `nodeType`,`gp`.`pointAmount` AS `pointAmount`,round(`gp`.`pointAmount` * `p`.`redPointAccRate`,2) AS `conversionAccPoint`,`p`.`redPointAccRate` AS `pointTransRate`,'3' AS `conversionStatus`,current_timestamp() AS `createTime`,current_timestamp() AS `updateTime` from (`returnp_db`.`green_point` `gp` join (`returnp_db`.`policy` `p` join `information_schema`.`tables` `t` on(`p`.`policyNo` = `t`.`AUTO_INCREMENT` - 1))) where `t`.`TABLE_NAME` = 'policy' and `t`.`TABLE_SCHEMA` = 'returnp_db' and `gp`.`pointAmount` > `p`.`pointTransLimit` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-24 14:23:04
