package com.my.project01.service;

import org.springframework.web.servlet.ModelAndView;

import com.my.project01.dto.GuestDto;

public interface GuestService {
	public void addGuest(GuestDto dto);
	public void updateGuest(GuestDto dto);
	public void deleteGuest(GuestDto dto);
	public void getGuestInfo(ModelAndView mView, int num);
	public void getGuestList(ModelAndView mView);
}
