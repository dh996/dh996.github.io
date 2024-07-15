package kr.co.dh996.project11re.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.dh996.project11re.service.ChampService;
import kr.co.dh996.project11re.service.SimulService;

@Controller
@RequestMapping("/simul")
public class SimulController {
	
	private ChampService champService;
	private SimulService simulService;

	@Autowired
	public SimulController(ChampService champService,
			SimulService simulService) {
		this.champService = champService;
		this.simulService = simulService;
	}
	
	@GetMapping("/home")
	public String simulHome(Model model) {
		String version = champService.getLatestVersion();
		model.addAttribute("version", version);
		model.addAttribute("champDTOList", champService.getAllChampList(version));
		return "simul/simul_home";
	}//현재 버전값을 페이지를 접근할때마다 구하는방식
	//허나 버전 업데이트 적용시 서버에 대표버전을 설정하고
	//대표버전만 불러오는식으로 변경하게되면 작업량의 커다란 감소 기대가능
	
	@PostMapping("/simul")
	public String simulStart() {
		return simulService.simulStart();
	}
	
	@GetMapping("/simul/{sid}")
	public String simulResult(@PathVariable String sid, Model model) {
		model.addAttribute("simulDTO", simulService.getSimulDTO(sid));
		return "simul/simul_result";
	}
}
