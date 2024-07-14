package kr.co.dh996.project11re.service;

import java.io.IOException;
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
	
	private JsonService jsonService;
	private DataService dataService;
	private final ChampVersionRepository champVersionRepository;
	private final ChampNameRepository champNameRepository;
	private final ChampTagsRepository champTagsRepository;
	
	@Autowired
    public ChampService(ChampVersionRepository champVersionRepository,
    		ChampNameRepository champNameRepository,
    		ChampTagsRepository champTagsRepository) {
        this.champVersionRepository = champVersionRepository;
        this.champNameRepository = champNameRepository;
        this.champTagsRepository = champTagsRepository;
    }

	@Transactional
	public void updateVersion(String version) throws IOException {
		// TODO Auto-generated method stub
		String jsonData = dataService.fetchChampionData(version);
        List<ChampDTO> champDTOList = jsonService.parseChampionData(jsonData, version);
        ChampVersion champVersion = new ChampVersion();
        champVersion.setChamp_version(version);
        champVersionRepository.save(champVersion);

        for (ChampDTO champDTO : champDTOList) {
            // Champ 저장
            ChampName champName = new ChampName();
            champName.setChamp_version(version);
            champName.setChamp_id(champDTO.getChampID());
            champName.setChamp_name(champDTO.getChampName());
            champNameRepository.save(champName);

            // ChampTag 저장
            for (String tag : champDTO.getChampTags()) {
                ChampTags champTags = new ChampTags();
                champTags.setChamp_version(version);
                champTags.setChamp_id(champDTO.getChampID());
                champTags.setChamp_tags(tag);
                champTagsRepository.save(champTags);
            }
        }
	}

	public boolean checkVersion(String version) {
		// TODO Auto-generated method stub
		return champVersionRepository.existsByChampVersion(version);
	}
}
