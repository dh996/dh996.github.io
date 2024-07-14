package kr.co.dh996.project11re.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.dh996.project11re.service.ChampService;

@Controller
public class ChampController {

	@Autowired
	private ChampService champService;
	
	@PostMapping("/version/up")
	public String updateVersion(@RequestParam("version") String version, Model model) {
		if(champService.checkVersion(version)) {
			try {
	            champService.updateVersion(version);
	            model.addAttribute("message", "버전 업데이트 성공");
		        return "/version/success";
	        } catch (IOException e) {
	            model.addAttribute("message", "에러발생: " + e.getMessage());
				return "/version/error";
	        } 
		}else {
            model.addAttribute("message", "이미 저장된 버전입니다.");
			return "/version/error";
		}
	}
}
