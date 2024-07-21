package kr.co.dh996.project11re.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.dh996.project11re.service.ChampService;

@Controller
@RequestMapping("/version")
public class ChampController {

	private ChampService champService;
	
	@Autowired
	public ChampController(ChampService champService) {
		this.champService = champService;
	}
 
	//업데이트시험용페이지
	@GetMapping("/updateTest")
	public String showUpdatePage() {
	    return "testPage/updateTest";
	}
	 
	//업데이트에러시험용페이지
	@GetMapping("/errorTest")
	public String showErrorPage() {
	    return "testPage/updateError";
	}
	
	//업데이트성공시험용페이지
	@GetMapping("/successTest")
	public String showSuccessPage() {
	    return "testPage/updateSuccess";
	}
	
	@PostMapping("/up")
	public String updateVersion(@RequestParam("version") String version, Model model) {
		if(champService.checkVersion(version)) {
            model.addAttribute("message", "이미 저장된 버전입니다.");
			return "/version/errorTest";
		}else {
			try {
	            champService.updateVersion(version);
	            model.addAttribute("message", "버전 업데이트 성공");
		        return "/version/successTest";
	        } catch (IOException e) {
	            model.addAttribute("message", "에러발생: " + e.getMessage());
				return "/version/errorTest";
	        } 
		}
	}
}
