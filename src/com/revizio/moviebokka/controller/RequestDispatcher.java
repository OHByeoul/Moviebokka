package com.revizio.moviebokka.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestDispatcher {
	void dispatcherRoute(String route,HttpServletRequest request, HttpServletResponse response);
}
