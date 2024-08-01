package kr.co.dh996.project11re.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dh996.project11re.dto.ChampDTO;
import kr.co.dh996.project11re.entity.ChampName;
import kr.co.dh996.project11re.entity.ChampTags;
import kr.co.dh996.project11re.entity.ChampVersion;
import kr.co.dh996.project11re.repository.ChampNameRepository;
import kr.co.dh996.project11re.repository.ChampTagsRepository;
import kr.co.dh996.project11re.repository.ChampVersionRepository;

@Service
public class ChampService {
	
	private final JsonService jsonService;
	private final ChampDataService champDataService;
	private final ChampVersionRepository champVersionRepository;
	private final ChampNameRepository champNameRepository;
	private final ChampTagsRepository champTagsRepository;
	
	@Autowired
    public ChampService(JsonService jsonService,
    		ChampDataService champDataService,
    		ChampVersionRepository champVersionRepository,
    		ChampNameRepository champNameRepository,
    		ChampTagsRepository champTagsRepository) {
		this.jsonService = jsonService;
		this.champDataService = champDataService;
        this.champVersionRepository = champVersionRepository;
        this.champNameRepository = champNameRepository;
        this.champTagsRepository = champTagsRepository;
    }

	@Transactional
	public void updateVersion(String version) throws IOException {
		// TODO Auto-generated method stub
		String jsonData = champDataService.fetchChampionData(version);
        List<ChampDTO> champDTOList = jsonService.parseChampionData(jsonData, version);
        ChampVersion champVersion = saveChampVersion(version); //엔티티 저장후 반환
        List<ChampName> champNameList = new ArrayList<>();
        List<ChampTags> champTagsList = new ArrayList<>();

        for (ChampDTO champDTO : champDTOList) {
            // Champ 저장
            ChampName champName = new ChampName();
            champName.setChampVersion(champVersion);
            champName.setChampId(champDTO.getChampID());
            champName.setChampName(champDTO.getChampName());
            champNameList.add(champName);

            // ChampTag 저장
            for (String tag : champDTO.getChampTags()) {
                ChampTags champTags = new ChampTags();
                champTags.setChampVersion(champVersion);
                champTags.setChampId(champName); //저장시 바뀌는 값 없기에 바로 db저장 이전 반영
                champTags.setChampTags(tag);
                champTagsList.add(champTags);
            }
        }
        champNameRepository.saveAll(champNameList);
        champTagsRepository.saveAll(champTagsList);
	}

	private ChampVersion saveChampVersion(String version) {
		// TODO Auto-generated method stub
		ChampVersion champVersion = new ChampVersion();
        champVersion.setChampVersion(version);
		return champVersionRepository.save(champVersion);
	}

	@Transactional(readOnly = true)
	public boolean checkVersion(String version) {
		// TODO Auto-generated method stub
		return champVersionRepository.existsByChampVersion(version);
	}

	@Transactional(readOnly = true)
	public List<ChampDTO> getAllChampList(String version) {
		// TODO Auto-generated method stub
		List<Object[]> results = champNameRepository.findByChampVersionWithTags(version);
        return champDataService.mapToChampDTO(results);
	}

	@Transactional(readOnly = true)
	public List<ChampDTO> getChampList(List<String> usersPickChampList) {
		// TODO Auto-generated method stub
		List<Object[]> results = champNameRepository.findByChampIdsWithTags(usersPickChampList);
        return champDataService.mapToChampDTO(results);
	}

	@Transactional(readOnly = true)
	public String getLatestVersion() {
		// TODO Auto-generated method stub
		return champDataService.findLatestVersion(champVersionRepository.getAllVersions());
	}
}
