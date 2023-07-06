-- 사용자(회원) 정보를 저장할 테이블
CREATE TABLE fit_users(
    id VARCHAR2(100) PRIMARY KEY,
    pwd VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    profile VARCHAR2(100), --프로필 이미지 경로를 저장할 칼럼
    regdate DATE
);
CREATE SEQUENCE fit_users_seq; 

--방문자 정보(방명록)를 저장할 테이블 
CREATE TABLE fit_guest(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	content CLOB,
	pwd VARCHAR2(100) NOT NULL,
	regdate DATE
);
CREATE SEQUENCE fit_guest_seq; 

-- 게시글을 저장할 테이블 
CREATE TABLE fit_cafe(
    num NUMBER PRIMARY KEY, --글번호
    writer VARCHAR2(100) NOT NULL, --작성자 (로그인된 아이디)
    title VARCHAR2(100) NOT NULL, --제목
    content CLOB, --글 내용
    viewCount NUMBER, -- 조회수
    regdate DATE --글 작성일
);

-- 게시글의 번호를 얻어낼 시퀀스
CREATE SEQUENCE fit_cafe_seq; 

-- 댓글을 저장할 테이블
CREATE TABLE fit_cafe_comment(
    num NUMBER PRIMARY KEY, --댓글의 글번호
    writer VARCHAR2(100), --댓글 작성자의 아이디
    content VARCHAR2(500), --댓글 내용
    target_id VARCHAR2(100), --댓글의 대상자 아이디
    ref_group NUMBER, -- 원글의 그룹번호
    comment_group NUMBER, -- 댓글의 그룹번호 
    deleted CHAR(3) DEFAULT 'no', -- 삭제된 댓글인지 여부 'yes' or 'no'
    regdate DATE -- 작성일
);
-- 댓글의 글번호를 얻어낼 시퀀스
CREATE SEQUENCE fit_cafe_comment_seq;

-- 이미지 갤러리를 만들기 위한 테이블 
CREATE TABLE fit_gallery(
   num NUMBER PRIMARY KEY,
   writer VARCHAR2(100),
   caption VARCHAR2(100),   -- 이미지에 대한 설명
   imagePath VARCHAR2(100), -- 업로드된 이미지의 경로  ex) /resources/upload/xxx.jpg
   viewCount NUMBER, -- 조회수
   regdate DATE -- 이미지 업로드 날짜 
);

CREATE SEQUENCE fit_gallery_seq;

-- 댓글을 저장할 테이블
CREATE TABLE fit_gallery_comment(
    num NUMBER PRIMARY KEY, --댓글의 글번호
    writer VARCHAR2(100), --댓글 작성자의 아이디
    content VARCHAR2(500), --댓글 내용
    target_id VARCHAR2(100), --댓글의 대상자 아이디
    ref_group NUMBER, -- 원글의 그룹번호
    comment_group NUMBER, -- 댓글의 그룹번호 
    deleted CHAR(3) DEFAULT 'no', -- 삭제된 댓글인지 여부 'yes' or 'no'
    regdate DATE -- 작성일
);
CREATE SEQUENCE fit_gallery_comment_seq;
