package com.example.boot07.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot07.cafe.dao.CafeCommentDao;
import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeCommentDto;
import com.example.boot07.cafe.dto.CafeDto;
import com.example.boot07.exception.NotDeleteException;

@Service
public class CafeServiceImpl implements CafeService{

	@Autowired
	private CafeDao cafeDao;
	
	@Autowired
	private CafeCommentDao cafeCommentDao;
	
	//페이징 처리, 검색어 기능을 고려한 비즈니스 로직처리를 하는 메소드 
	@Override
	public void getList(HttpServletRequest request, Model model) {
	      //한 페이지에 몇개씩 표시할 것인지
	      final int PAGE_ROW_COUNT=5;
	      //하단 페이지를 몇개씩 표시할 것인지
	      final int PAGE_DISPLAY_COUNT=5;

	      //보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	      int pageNum=1;
	      //페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	      String strPageNum=request.getParameter("pageNum");
	      //만일 페이지 번호가 파라미터로 넘어 온다면
	      if(strPageNum != null){
	         //숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
	         pageNum=Integer.parseInt(strPageNum);
	      }   
	      
	      //보여줄 페이지의 시작 ROWNUM
	      int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	      //보여줄 페이지의 끝 ROWNUM
	      int endRowNum=pageNum*PAGE_ROW_COUNT;
	      
	      /*
	         [ 검색 키워드에 관련된 처리 ]
	         -검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.      
	      */
	      String keyword=request.getParameter("keyword");
	      String condition=request.getParameter("condition");
	      //만일 키워드가 넘어오지 않는다면 
	      if(keyword==null){
	         //키워드와 검색 조건에 빈 문자열을 넣어준다. 
	         //클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
	         keyword="";
	         condition=""; 
	      }

	      //특수기호를 인코딩한 키워드를 미리 준비한다. 
	      String encodedK=URLEncoder.encode(keyword);
	         
	      //FileDto 객체에 startRowNum 과 endRowNum 을 담는다.
	      CafeDto dto=new CafeDto();
	      dto.setStartRowNum(startRowNum);
	      dto.setEndRowNum(endRowNum);
	   
	      //만일 검색 키워드가 넘어온다면 
	      if(!keyword.equals("")){
	         //검색 조건이 무엇이냐에 따라 분기 하기
	         if(condition.equals("title_content")){//제목 + 내용 검색인 경우
	            dto.setTitle(keyword);
	            dto.setContent(keyword);
	         }else if(condition.equals("title")){ //제목 검색인 경우
	            dto.setTitle(keyword);
	         }else if(condition.equals("writer")){ //작성자 검색인 경우
	            dto.setWriter(keyword);
	         } // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
	      }
	      
	      //파일 목록을 select 해 온다.(검색 키워드가 있는경우 키워드에 부합하는 전체 글) 
	      List<CafeDto> list=cafeDao.getList(dto);
	      
	      //전체 글의 갯수(검색 키워드가 있는경우 키워드에 부합하는 전체 글의 갯수)
	      int totalRow=cafeDao.getCount(dto);
	      
	      //하단 시작 페이지 번호 
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	      //하단 끝 페이지 번호
	      int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	      
	      //전체 페이지의 갯수 구하기
	      int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum=totalPageCount; //보정해 준다. 
	      }
	      //view page에 필요한 값을 model에 담음 
	      model.addAttribute("list", list);
	      model.addAttribute("pageNum", pageNum);
	      model.addAttribute("startPageNum", startPageNum);
	      model.addAttribute("endPageNum", endPageNum);
	      model.addAttribute("totalPageCount", totalPageCount);
	      model.addAttribute("keyword", keyword);
	      model.addAttribute("encodedK", encodedK);
	      model.addAttribute("totalRow", totalRow);
	      model.addAttribute("condition", condition);
	}

	@Override
	public void getDetail(HttpServletRequest request, Model model) {
	      //자세히 보여줄 글번호를 읽어온다. 
	      int num=Integer.parseInt(request.getParameter("num"));
	      //조회수 올리기
	      cafeDao.addViewCount(num);
	      
	      /*
	         [ 검색 키워드에 관련된 처리 ]
	         -검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.      
	      */
	      String keyword=request.getParameter("keyword");
	      String condition=request.getParameter("condition");
	      //만일 키워드가 넘어오지 않는다면 
	      if(keyword==null){
	         //키워드와 검색 조건에 빈 문자열을 넣어준다. 
	         //클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
	         keyword="";
	         condition=""; 
	      }
	      //CafeDto 객체를 생성해서 
	      CafeDto dto=new CafeDto();
	      //자세히 보여줄 글번호를 넣어준다. 
	      dto.setNum(num);
	      //만일 검색 키워드가 넘어온다면 
	      if(!keyword.equals("")){
	         //검색 조건이 무엇이냐에 따라 분기 하기
	         if(condition.equals("title_content")){//제목 + 내용 검색인 경우
	            //검색 키워드를 CafeDto 에 담아서 전달한다.
	            dto.setTitle(keyword);
	            dto.setContent(keyword);         
	         }else if(condition.equals("title")){ //제목 검색인 경우
	            dto.setTitle(keyword);   
	         }else if(condition.equals("writer")){ //작성자 검색인 경우
	            dto.setWriter(keyword);   
	         } // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
	      }
	      
	      //글하나의 정보를 얻어온다.
	      CafeDto resultDto=cafeDao.getData(dto);
	      
	      //특수기호를 인코딩한 키워드를 미리 준비한다. 
	      String encodedK=URLEncoder.encode(keyword);
	      
	      /*
	         [ 댓글 페이징 처리에 관련된 로직 ]
	       */
	      //한 페이지에 몇개씩 표시할 것인지
	      final int PAGE_ROW_COUNT=10;
	      //detail.jsp 페이지에서는 항상 1페이지의 댓글 내용만 출력한다. 
	      int pageNum=1;
	      //보여줄 페이지의 시작 ROWNUM
	      int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	      //보여줄 페이지의 끝 ROWNUM
	      int endRowNum=pageNum*PAGE_ROW_COUNT;
	      //원글의 글번호를 이용해서 해당글에 달린 댓글 목록을 얻어온다.
	      CafeCommentDto commentDto=new CafeCommentDto();
	      commentDto.setRef_group(num);
	      //1페이지에 해당하는 startRowNum 과 endRowNum 을 dto 에 담아서  
	      commentDto.setStartRowNum(startRowNum);
	      commentDto.setEndRowNum(endRowNum);
	      //1페이지에 해당하는 댓글 목록만 select 되도록 한다. 
	      List<CafeCommentDto> commentList=cafeCommentDao.getList(commentDto);   
	      
	      //원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
	      int totalRow=cafeCommentDao.getCount(num);
	      //댓글 전체 페이지의 갯수
	      int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	      
	    // Model에 글 하나의 정보 담기
	      model.addAttribute("dto", resultDto);
	      model.addAttribute("condition", condition);
	      model.addAttribute("keyword", keyword);
	      model.addAttribute("encodedK", encodedK);
	      model.addAttribute("totalRow", totalRow);
	      model.addAttribute("commentList", commentList);
	      model.addAttribute("totalPageCount", totalPageCount);
	}

	@Override
	public void saveContent(CafeDto dto) {
		//title, content, writer 정보가 들어있는 CafeDto를 dao에 전달해서 DB에 저장되도록 함 
		cafeDao.insert(dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		cafeDao.update(dto);
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		//세션에서 로그인된 아이디를 읽어와서
		String id = (String)request.getSession().getAttribute("id");
		//글 작성자와 로그인된 아이디가 다르다면
		CafeDto dto = cafeDao.getData(num);
		//예외를 발생시켜 삭제가 안되도록 함 
		if(!id.equals(dto.getWriter())) {
			throw new NotDeleteException("작성자 외 삭제 불가");
		}
		//일치하면 글 삭제하기 
		cafeDao.delete(num);
	}

	@Override
	public void getData(HttpServletRequest request) {
		//수정할 글번호
		int num = Integer.parseInt(request.getParameter("num"));
		//수정할 글의 정보를 얻어와서
		CafeDto dto = cafeDao.getData(num);
		//request에 담아줌
		request.setAttribute("dto", dto);
	}

	@Override
	public void saveComment(HttpServletRequest request) {
		//폼 전송되는 파라미터 추출
		int ref_group = Integer.parseInt(request.getParameter("ref_group")); //원글의 글번호
		String target_id = request.getParameter("target_id"); //댓글 대상자의 아이디
		String content = request.getParameter("content"); //댓글의 내용
		/*
		 *	원글의 댓글은 comment_group 번호가 전송이 안되고,
		 *	댓글의 댓글은 commemt_group 번호가 전송이 됨
		 *	따라서 null 여부를 조사하면 원글의 댓글인지 댓글의 댓글인지 판단할 수 있음 
		 */
		String comment_group = request.getParameter("comment_group");
		
		//댓글 작성자는 session영역에서 얻어냄
		String writer = (String)request.getSession().getAttribute("id");
		//댓글의 시퀀스 번호 미리 얻어내기
		int seq = cafeCommentDao.getSequence();
		
		//저장할 댓글의 정보를 dto에 담기
		CafeCommentDto dto = new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		//원글의 댓글인 경우 
		if(comment_group == null) {
			//댓글의 글번호를 comment_group 번호로 사용
			dto.setComment_group(seq);
		}else {
			//전송된comment_group번호를 숫자로 바꿔서 dto에 넣어줌
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		//댓글 정보를 DB에 저장
		cafeCommentDao.insert(dto);
	}

	@Override
	public void deleteComment(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		//삭제할 댓글정보를 읽어옴
		CafeCommentDto dto = cafeCommentDao.getData(num);
		String id = (String)request.getSession().getAttribute("id");
		//글 작성자와 로그인된 아이디가 일치하지 않을 경우
		if(!dto.getWriter().equals(id)) {
			throw new NotDeleteException("작성자 외 삭제 불가");
		}
		// dao를 이용해서 DB에서 삭제 
		cafeCommentDao.delete(num);
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		cafeCommentDao.update(dto);
	}

	@Override
	public void moreCommentList(HttpServletRequest request) {
		//로그인된 아이디
		String id = (String)request.getSession().getAttribute("id");
		//ajax요청 파라미터로 넘어오는 댓글의 페이지 번호를 읽어냄
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		//ajax요청 파라미터로 넘어오는 원글의 글번호를 읽어냄
		int num = Integer.parseInt(request.getParameter("num"));
		
		
	/*[ 댓글 페이징 처리에 관련된 로직 ]*/
		
     //한 페이지에 몇개씩 표시할 것인지
     final int PAGE_ROW_COUNT=10;

     //보여줄 페이지의 시작 ROWNUM
     int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
     //보여줄 페이지의 끝 ROWNUM
     int endRowNum=pageNum*PAGE_ROW_COUNT;

     //원글의 글번호를 이용해서 해당글에 달린 댓글 목록을 얻어온다.
     CafeCommentDto commentDto=new CafeCommentDto();
     commentDto.setRef_group(num);
     //1페이지에 해당하는 startRowNum 과 endRowNum 을 dto 에 담아서  
     commentDto.setStartRowNum(startRowNum);
     commentDto.setEndRowNum(endRowNum);

     //pageNum에 해당하는 댓글 목록만 select 되도록 한다. 
     List<CafeCommentDto> commentList=cafeCommentDao.getList(commentDto);
     //원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
     int totalRow=cafeCommentDao.getCount(num);
     //댓글 전체 페이지의 갯수
     int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);

     //view page 에 필요한 값 request 에 담아주기
     request.setAttribute("commentList", commentList);
     request.setAttribute("num", num); //원글의 글번호
     request.setAttribute("pageNum", pageNum); //댓글의 페이지 번호
	}
}
