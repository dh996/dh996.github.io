package kr.co.dh996.project11re.dto;

import java.util.List;

import kr.co.dh996.project11re.entity.SimulData;
import kr.co.dh996.project11re.entity.SimulList;
import kr.co.dh996.project11re.entity.SimulLogs;
import kr.co.dh996.project11re.entity.SimulProcessA;
import kr.co.dh996.project11re.entity.SimulProcessD;
import kr.co.dh996.project11re.entity.SimulProcessT;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimulDTO {

	 private SimulList simulList;
	 private List<SimulData> simulDataList;
	 private List<SimulLogs> simulLogsList;
	 private List<SimulProcessA> simulProcessAList;
	 private List<SimulProcessD> simulProcessDList;
	 private List<SimulProcessT> simulProcessTList;

	 public SimulDTO(SimulList simulList,
			 List<SimulData> simulDataList,
			 List<SimulLogs> simulLogsList,
			 List<SimulProcessA> simulProcessAList,
			 List<SimulProcessD> simulProcessDList,
			 List<SimulProcessT> simulProcessTList) {
	     this.simulList = simulList;
	     this.simulDataList = simulDataList;
	     this.simulLogsList = simulLogsList;
	     this.simulProcessAList = simulProcessAList;
	     this.simulProcessDList = simulProcessDList;
	     this.simulProcessTList = simulProcessTList;
	 }
}
