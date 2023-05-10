package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.GroundDto;
import com.example.demo.dto.SportsDto;
import com.example.demo.exception.SportsNotFoundException;
import com.example.demo.pojo.Academy;
import com.example.demo.pojo.Ground;
import com.example.demo.pojo.Sports;
import com.example.demo.repo.AcademyRepository;
import com.example.demo.repo.GroundRepository;
import com.example.demo.repo.SportsRepository;

@Service
@Transactional
public class SportsServiceImpl implements SportsService {
    @Autowired
    private SportsRepository sportsRepository;
    
    @Autowired
    private GroundRepository groundRepository;
    
    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SportsDto getSportsByIdAndAcademyId(Long id, Long academyId) {
        Optional<Sports> optionalSports = sportsRepository.findByIdAndAcademyId(id, academyId);
        if (optionalSports.isPresent()) {
            Sports sports = optionalSports.get();
            return modelMapper.map(sports, SportsDto.class);
        } else {
            throw new SportsNotFoundException("Sports not found for id " + id + " and academyId " + academyId);
        }
    }
    
    @Override
    public SportsDto getSportsById(Long id) {
        Sports sports = sportsRepository.findById(id)
                .orElseThrow(() -> new SportsNotFoundException("Sports not found with id " + id));
        return modelMapper.map(sports, SportsDto.class);
    }
    
    @Override
    public List<SportsDto> getAllSports() {
        List<Sports> sportsList = sportsRepository.findAll();
        return sportsList.stream()
                .map(sports -> modelMapper.map(sports, SportsDto.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public Sports createSports(Sports sports) {
        return sportsRepository.save(sports);
    }
    
    @Override
    public SportsDto updateSports(Long id, SportsDto sportsDto) {
        Sports existingSports = sportsRepository.findById(id)
                .orElseThrow(() -> new SportsNotFoundException("Sports not found with id " + id));
        Sports updatedSports = modelMapper.map(sportsDto, Sports.class);
        updatedSports.setId(existingSports.getId());
        Sports savedSports = sportsRepository.save(updatedSports);
        return modelMapper.map(savedSports, SportsDto.class);
    }
    
    @Override
    public void deleteSports(Long id) {
        Sports existingSports = sportsRepository.findById(id)
                .orElseThrow(() -> new SportsNotFoundException("Sports not found with id " + id));
        sportsRepository.delete(existingSports);
    }
    
  
    @Override
    public List<SportsDto> getSportsByAcademyId(Long academyId) {
        List<Sports> sportsList = sportsRepository.findByAcademyId(academyId);
        return sportsList.stream()
                .map(sports -> modelMapper.map(sports, SportsDto.class))
                .collect(Collectors.toList());
    }

//	@Override
//	public Sports getSportsById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<Sports> getAllSports() {
//		// TODO Auto-generated method stub
//		return null;
//	}

    @Override
    public List<GroundDto> getGroundsBySports(Long sportsId) {
        Sports sports = sportsRepository.findById(sportsId)
                .orElseThrow(() -> new SportsNotFoundException("Sports not found with id: " + sportsId));
        List<Ground> groundList = groundRepository.findBySports(sports);
        return groundList.stream()
                .map(ground -> modelMapper.map(ground, GroundDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public String addSportsToAcademy(Long id, Sports sport) {
		try {
			Academy academy=academyRepository.findById(id).orElseThrow(() -> new Exception("No academy"));
			sport.setAcademy(academy);
			Set<Sports> sportList=academy.getSportList();
			sportList.add(sport);
			academy.setSportList(sportList);
			
			sportsRepository.save(sport);
			academyRepository.save(academy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}