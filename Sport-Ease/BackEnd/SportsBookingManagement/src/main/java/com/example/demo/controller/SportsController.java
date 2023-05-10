package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GroundDto;
import com.example.demo.dto.SportsDto;
import com.example.demo.exception.GroundNotFoundException;
import com.example.demo.pojo.Ground;
import com.example.demo.pojo.Sports;
import com.example.demo.service.SportsService;

@RestController
@RequestMapping("/sports")
public class SportsController {
    @Autowired
    private SportsService sportsService;
    @Autowired
    private  ModelMapper modelMapper;
    
    public SportsController(SportsService sportsService, ModelMapper modelMapper) {
        this.sportsService = sportsService;
        this.modelMapper = modelMapper;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<SportsDto> getSportsByIdAndAcademyId(@PathVariable Long id, @RequestParam Long academyId) {
//        SportsDto sportsDto = sportsService.getSportsByIdAndAcademyId(id, academyId);
//        return ResponseEntity.ok(sportsDto);
//    }
    
    @PostMapping("/addtoacademy/{academyId}")
    public String addSportsToAcademy(@RequestBody Sports sport,@PathVariable Long academyId) {
    	return sportsService.addSportsToAcademy(academyId, sport);
    }

    @GetMapping("/academy/{academyId}")
    public ResponseEntity<List<SportsDto>> getSportsByAcademyId(@PathVariable Long academyId) {
        List<SportsDto> sportsDtoList = sportsService.getSportsByAcademyId(academyId);
        return ResponseEntity.ok(sportsDtoList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SportsDto> getSportsById(@PathVariable("id") Long id) {
        SportsDto sportsDto = sportsService.getSportsById(id);
        return ResponseEntity.ok().body(sportsDto);
    }
    
//    @GetMapping
//    public ResponseEntity<List<SportsDto>> getAllSports() {
//        List<SportsDto> sportsDtoList = sportsService.getAllSports();
//        return ResponseEntity.ok().body(sportsDtoList);
//    }
    
    @PostMapping
    public ResponseEntity<Sports> createSports(@RequestBody Sports sports) {
        Sports createdSports = sportsService.createSports(sports);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSports);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SportsDto> updateSports(@PathVariable("id") Long id, @RequestBody SportsDto sportsDto) {
        SportsDto updatedSportsDto = sportsService.updateSports(id, sportsDto);
        return ResponseEntity.ok().body(updatedSportsDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSports(@PathVariable("id") Long id) {
        sportsService.deleteSports(id);
        return ResponseEntity.noContent().build();
    }
    
//    @GetMapping("/{id}")
//    public ResponseEntity<SportsDto> getSportsById(@PathVariable Long id) {
//        Sports sports = sportsService.getSportsById(id);
//        if (sports == null) {
//            throw new SportsNotFoundException(id);
//        }
//        return ResponseEntity.ok(modelMapper.map(sports, SportsDto.class));
//    }

    @GetMapping
    public ResponseEntity<List<SportsDto>> getAllSports() {
        List<SportsDto> sportsList = sportsService.getAllSports();
        return ResponseEntity.ok(sportsList.stream()
                .map(sports -> modelMapper.map(sports, SportsDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}/grounds")
    public ResponseEntity<List<GroundDto>> getGroundsBySports(@PathVariable Long id) {
        List<GroundDto> groundList = sportsService.getGroundsBySports(id);
        if (groundList == null || groundList.isEmpty()) {
            throw new GroundNotFoundException(id);
        }
        return ResponseEntity.ok(groundList.stream()
                .map(ground -> modelMapper.map(ground, GroundDto.class))
                .collect(Collectors.toList()));
    }
}
