
/*-------------------------------------		회원 테이블	-----------------------------------------*/
-- 회원테이블 MEMBER
-- 회원번호용 시퀀스 MEMBERSEQ
-- 컬럼 : 회원번호, 회원아이디, 회원비밀번호, 회원이름, 회원연락처
-- 회원주소, 회원이메일 회원가입일, 회원활동여부, 회원등급, 투표권갯수

DROP TABLE MEMBER;
DROP SEQUENCE MEMBERSEQ;

CREATE SEQUENCE MEMBERSEQ;
CREATE TABLE MEMBER(
	MEMBER_SEQ NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR2(1000) NOT NULL,
	MEMBER_PW VARCHAR2(1000) NOT NULL,
	MEMBER_NAME VARCHAR2(1000) NOT NULL,
	MEMBER_PHONE VARCHAR2(100) NOT NULL,
	MEMBER_ADDR VARCHAR2(4000) NOT NULL,
	MEMBER_EMAIL VARCHAR2(1000) NOT NULL,
	MEMBER_REGDATE DATE NOT NULL,
	MEMBER_HEART NUMBER NOT NULL,
	MEMBER_ENABLED INTEGER DEFAULT 1,
	MEMBER_ROLE VARCHAR2(50) DEFAULT 'ROLE_USER' NOT NULL,
	MEMBER_VOTE NUMBER NOT NULL,
	MEMBER_IMG VARCHAR2(4000) DEFAULT '이미지' NOT NULL,
	CONSTRAINT CON_MEMBER_ID UNIQUE(MEMBER_ID)
);

SELECT * FROM MEMBER;

update member set member_role = 'ROLE_ARTIST' where member_seq = 1

-- 컬럼 값 변경
ALTER TABLE MEMBER MODIFY(MEMBER_ROLE VARCHAR2(15));

delete from member where member_id='admin';

UPDATE MEMBER SET MEMBER_ROLE='ROLE_ADMIN' WHERE MEMBER_ID='admin1234';

UPDATE MEMBER SET MEMBER_ROLE='ROLE_ARTIST' WHERE MEMBER_ID='테스트';

select member_heart, member_vote from member where member_id = 'lotus526'

--하트구매
update member set member_heart = 1000, member_vote = 0 where member_id = 'lotus526'

update member set member_vote = 
(select member_vote from member where member_id = 'lotus526') + 5
where member_id = 'lotus526'

--투표권 구매 
update member 
set member_vote = ((select member_vote from member where member_id = 'lotus526') + 10),
member_heart = ((select member_heart from member where member_id = 'lotus526') + 250)
where member_id = 'lotus526'

/*------------------------------------- 투표 테이블	-----------------------------------------*/
-- 투표 테이블 VOTE
-- 투표번호 시퀀스 VOTESEQ
-- 컬럼 : 스타이름, 투표수, 페이지번호(0~5)
-- girl-group:여자그룹/ girl-idol:여자아이돌/ new-idol:신인스타
-- star-idol:별별스타/ boy-group:남자그룹/ boy-idol:남자아이돌

DROP TABLE VOTE;

CREATE TABLE VOTE(
	VOTE_ITEM VARCHAR2(1000) NOT NULL,
	VOTE_NAME VARCHAR2(1000) NOT NULL,
	VOTE_TOTAL NUMBER DEFAULT 0 NOT NULL
);

SELECT * FROM VOTE;

UPDATE MEMBER
SET MEMBER_VOTE = 100
WHERE MEMBER_ID = 'user1'

SELECT * FROM MEMBER

/*-------------------------------------	  라이브방송 테이블	-----------------------------------------*/
-- 라이브방송 테이블 BROADCAST
-- 라이브방송 시퀀스 BROADCASTSEQ
-- 컬럼: 방송번호, 방송날짜, 방송시간, 진행자(방송인), 방송제목, 방송내용

DROP TABLE BROADCAST;
DROP SEQUENCE BROADCASTSEQ;

CREATE SEQUENCE BROADCASTSEQ;
CREATE TABLE BROADCAST(
	BROADCAST_SEQ NUMBER PRIMARY KEY, 
	BROADCAST_DATE DATE NOT NULL,
	BROADCAST_ROOM VARCHAR2(100), 
	BROADCAST_CASTER VARCHAR2(100) NOT NULL,
	BROADCAST_TITLE VARCHAR2(1000) NOT NULL,
	BROADCAST_CONTENT CLOB
);

--examples
INSERT INTO BROADCAST VALUES(BROADCASTSEQ.NEXTVAL, TO_DATE('2019-07-08 16:30','yyyy-mm-dd hh24:mi'), null, 'WeeBin', 'FirstDay' , 'index');
INSERT INTO BROADCAST VALUES(BROADCASTSEQ.NEXTVAL, TO_DATE('2019-08-16 09:00','yyyy-mm-dd hh24:mi'), null, 'Zsoo', 'AllDayAlongWithYou' , 'index');
INSERT INTO BROADCAST VALUES(BROADCASTSEQ.NEXTVAL, TO_DATE('2019-08-02 09:00','yyyy-mm-dd hh24:mi'), null, 'Zsoo', 'test' , 'index');
INSERT INTO BROADCAST VALUES(BROADCASTSEQ.NEXTVAL, TO_DATE('2019-10-15 09:00','yyyy-mm-dd hh24:mi'), null, 'Zsoo', 'test' , 'index');
INSERT INTO BROADCAST VALUES(BROADCASTSEQ.NEXTVAL, TO_DATE('2019-09-02 09:00','yyyy-mm-dd hh24:mi'), null, 'Zsoo', 'test' , null);

select * from broadcast

delete from broadcast


SELECT BROADCAST_SEQ, TO_CHAR(BROADCAST_DATE, 'yyyy-mm-dd hh24:mi') as "BROADCAST_DATE",
		BROADCAST_CASTER, BROADCAST_TITLE, BROADCAST_CONTENT
		FROM BROADCAST 
		where broadcast_content is not null

UPDATE MEMBER SET MEMBER_PW='$2a$10$WzmejPwX08xRYaKDbB1Ie.NOX5uwfHwSCahrNar1.VC0jWpx/.yiS' WHERE MEMBER_ID='1whtndus'


----------------------------------------- 실험
	UPDATE FREEBOARD SET FREEBOARD_TITLE = 11111123, FREEBOARD_CONTENT = 123 , FREEBOARD_FILE = 123
	WHERE FREEBOARD_NUM = 1
