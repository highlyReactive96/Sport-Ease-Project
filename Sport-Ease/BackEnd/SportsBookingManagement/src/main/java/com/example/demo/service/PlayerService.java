package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PlayerDto;

public interface PlayerService {
    List<PlayerDto> getAllPlayers();
    PlayerDto getPlayerById(Long id);
    PlayerDto createPlayer(PlayerDto playerDto);
    PlayerDto updatePlayer(Long id, PlayerDto playerDto);
    void deletePlayer(Long id);
}