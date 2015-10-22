package com.fsj.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.Constants;

/*
 * 涓嶉渶瑕佸疄鐜颁换浣曟帴鍙ｏ紝涔熶笉闇�缁ф壙浠讳綍鐨勭被锛屼篃涓嶉渶瑕佷换浣�Servlet API
 */
@Controller
@RequestMapping("/welcome")
//灏哅odel涓睘鎬у悕涓篊onstants.USER_INFO_SESSION鐨勫睘鎬ф斁鍒癝ession灞炴�鍒楄〃涓紝浠ヤ究杩欎釜灞炴�鍙互璺ㄨ姹傝闂�
@SessionAttributes(Constants.USER_INFO_SESSION)
public class WelcomeController {
	
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String helloWorld(TUser user,Model model) throws Exception {
		TUser user1 = userService.getUserByName(user.getName());
		if(user1 == null) {
			model.addAttribute("message", "");
			return "relogin";
		}else if(user.getPassword() == null || !user.getPassword().equals(user1.getPassword()) ){
			model.addAttribute("message", "瀵嗙爜閿欒");
			return "relogin";
		}else {
			model.addAttribute(Constants.USER_INFO_SESSION, user1); //鍚嶄负Constants.USER_INFO_SESSION鐨勫睘鎬ф斁鍒癝ession灞炴�鍒楄〃涓�
			return "welcome";
		}
	}
}
