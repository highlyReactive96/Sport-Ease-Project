package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PlayerDto;
import com.example.demo.exception.PlayerNotFoundException;
import com.example.demo.pojo.Player;
import com.example.demo.pojo.Role;
import com.example.demo.repo.PlayerRepository;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(player -> modelMapper.map(player, PlayerDto.class))
                .collect(Collectors.toList());
    }

    public PlayerDto getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(p -> modelMapper.map(p, PlayerDto.class)).orElse(null);
    }

    public PlayerDto createPlayer(PlayerDto playerDTO) {
        Player player = modelMapper.map(playerDTO, Player.class);
        player.setRole(Role.ROLE_PLAYER);
        player = playerRepository.save(player);
        return modelMapper.map(player, PlayerDto.class);
    }

    public PlayerDto updatePlayer(PlayerDto playerDTO, Long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }

        Player player = playerOptional.get();
        modelMapper.map(playerDTO, player);
        player = playerRepository.save(player);
        return modelMapper.map(player, PlayerDto.class);
    }

    public void deletePlayer(Long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }

        playerRepository.delete(playerOptional.get());
    }

	@Override
	public PlayerDto updatePlayer(Long id, PlayerDto playerDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
