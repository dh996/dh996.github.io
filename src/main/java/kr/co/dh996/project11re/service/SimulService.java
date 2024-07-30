package kr.co.dh996.project11re.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dh996.project11re.simul.data.RecordSimulProcess;
import kr.co.dh996.project11re.simul.data.RecordSimulData;
import kr.co.dh996.project11re.simul.data.SimulLog;
import kr.co.dh996.project11re.simul.data.SimulMainObject;
import kr.co.dh996.project11re.simul.program.Simulation;
import kr.co.dh996.project11re.dto.SimulDTO;
import kr.co.dh996.project11re.entity.ProcessEmbedded;
import kr.co.dh996.project11re.entity.SimulData;
import kr.co.dh996.project11re.entity.SimulList;
import kr.co.dh996.project11re.entity.SimulLogs;
import kr.co.dh996.project11re.entity.SimulProcessA;
import kr.co.dh996.project11re.entity.SimulProcessD;
import kr.co.dh996.project11re.entity.SimulProcessT;
import kr.co.dh996.project11re.repository.SimulDataRepository;
import kr.co.dh996.project11re.repository.SimulListRepository;
import kr.co.dh996.project11re.repository.SimulLogsRepository;
import kr.co.dh996.project11re.repository.SimulProcessARepository;
import kr.co.dh996.project11re.repository.SimulProcessDRepository;
import kr.co.dh996.project11re.repository.SimulProcessTRepository;

@Service
public class SimulService {
	//데이터베이스와 연결된 서비스 클래스입니다.
	
	private Simulation simulation;
	private final SimulDataService simulDataService;
	private final SimulDataRepository simulDataRepository;
	private final SimulListRepository simulListRepository;
	private final SimulLogsRepository simulLogsRepository;
	private final SimulProcessARepository simulProcessARepository;
	private final SimulProcessDRepository simulProcessDRepository;
	private final SimulProcessTRepository simulProcessTRepository;
	
	@Autowired
	public SimulService(Simulation simulation,
			SimulDataService simulDataService,
			SimulDataRepository simulDataRepository,
			SimulListRepository simulListRepository,
			SimulLogsRepository simulLogsRepository,
			SimulProcessARepository simulProcessARepository,
			SimulProcessDRepository simulProcessDRepository,
			SimulProcessTRepository simulProcessTRepository) {
		this.simulDataService = simulDataService;
		this.simulDataRepository = simulDataRepository;
		this.simulListRepository = simulListRepository;
		this.simulLogsRepository = simulLogsRepository;
		this.simulProcessARepository = simulProcessARepository;
		this.simulProcessDRepository = simulProcessDRepository;
		this.simulProcessTRepository = simulProcessTRepository;
	}

	public String simulStart(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		SimulMainObject simulMO = simulation.simulation(simulDataService.getUserpick(requestData));
		saveSimul(simulMO);
		return simulMO.getSid();
	}
	
	@Transactional
	public void saveSimul(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		SimulList simulList = saveList(simulMO);
		List<RecordSimulData> recordDataList = simulDataService.generateRecordData(simulMO.getSimulDataList(), simulMO.getSid());
		List<RecordSimulProcess> recordProcessList = simulMO.getSimulProcessList();
		List<SimulLog> recordLogList = simulMO.getSimulLogList();
		saveData(recordDataList, simulList);
		saveProcess(recordProcessList, simulList);
		saveLog(recordLogList, simulList);
	}

	private SimulList saveList(SimulMainObject simulMO) {
		// TODO Auto-generated method stub
		SimulList simulList = new SimulList(simulMO);
		return simulListRepository.save(simulList);
	}

	private void saveData(List<RecordSimulData> recordDataList, SimulList simulList) {
		// TODO Auto-generated method stub
		List<SimulData> simulDataList = new ArrayList<>();
	    for (RecordSimulData recordData : recordDataList) {
	        SimulData simulData = new SimulData(simulList, recordData);
	        simulDataList.add(simulData);
	    }
	    simulDataRepository.saveAll(simulDataList);
	}
	
	private void saveProcess(List<RecordSimulProcess> recordProcessList, SimulList simulList) {
		// TODO Auto-generated method stub
		List<SimulProcessA> simulProcessAList = new ArrayList<>();
		List<SimulProcessD> simulProcessDList = new ArrayList<>();
		List<SimulProcessT> simulProcessTList = new ArrayList<>();
		for (RecordSimulProcess recordProcess : recordProcessList) {
			//나중에 레코드시뮬프로세스 구조분할하던지 해야할듯
			ProcessEmbedded processEmbedded0 = new ProcessEmbedded(recordProcess.getRound(), 0);
			ProcessEmbedded processEmbedded1 = new ProcessEmbedded(recordProcess.getRound(), 1);
			SimulProcessA simulProcessA = new SimulProcessA(simulList, recordProcess);
			SimulProcessT simulProcessT0 = new SimulProcessT(simulList, recordProcess, processEmbedded0);
			SimulProcessT simulProcessT1 = new SimulProcessT(simulList, recordProcess, processEmbedded1);
			for (String dragon : recordProcess.getDragonU()) {
				SimulProcessD simulProcessD = new SimulProcessD(simulList, dragon, processEmbedded0);
				simulProcessDList.add(simulProcessD);
			}
			for (String dragon : recordProcess.getDragonE()) {
				SimulProcessD simulProcessD = new SimulProcessD(simulList, dragon, processEmbedded1);
				simulProcessDList.add(simulProcessD);
			}
			simulProcessAList.add(simulProcessA);
			simulProcessTList.add(simulProcessT0);
			simulProcessTList.add(simulProcessT1);
		}
		simulProcessARepository.saveAll(simulProcessAList);
		simulProcessDRepository.saveAll(simulProcessDList);
		simulProcessTRepository.saveAll(simulProcessTList);
	}

	private void saveLog(List<SimulLog> recordLogList, SimulList simulList) {
		// TODO Auto-generated method stub
		List<SimulLogs> simulLogsList = new ArrayList<>();
		for(SimulLog recordLog : recordLogList) {
			ProcessEmbedded processEmbedded = new ProcessEmbedded(recordLog);
			SimulLogs simulLogs = new SimulLogs(simulList, recordLog, processEmbedded);
			simulLogsList.add(simulLogs);
		}
		simulLogsRepository.saveAll(simulLogsList);
	}

	@Transactional(readOnly = true)
	public SimulDTO getSimulDTO(String sid) {
		// TODO Auto-generated method stub
		return simulListRepository.findSimulInfo(sid);
	}
}
