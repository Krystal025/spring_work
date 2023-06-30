package com.my.spring04.exception;

/*
 *	예외 클래스 만드는 방법
 *
 * 	1. RuntimeException 클래스 상속
 *  2. Strimg type을 전달받는 생성자를 정의한 후 해당 생성자에 전달되는 문자열을 부모 생성자에 전달 
 *    - 이 클래스로 생성된 객체는 getMessage()라는 메소드를 갖고 있는데
 *    	해당 메소드는 생성자에 전달받았던 예외 메세지를 리턴해줌 
 *    
 *    ex)
 *    NotDeleteException nde = new NotDeleteException("oh!no!");
 *    String msg = nde.getMessage(); //"oh!no!"
 *    
 *     - 어디에선가 이 예외를 발생시키고 싶으면 throw 예약어를 활용하면 됨
 *     
 *    ex)
 *    throw new NotDeleteException("남의 파일을 지우지 맙시다");
 */

public class NotDeleteException extends RuntimeException{
	//생성자
	public NotDeleteException(String message) {
		super(message);
	}

}
