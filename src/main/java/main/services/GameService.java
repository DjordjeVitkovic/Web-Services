package main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.models.Game;
import main.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;

	public Game saveGame(Game game) {

		gameRepo.save(game);

		return game;
	}

	public Game getGame(String id) {

		Game game = gameRepo.getOne(id);

		return game;
	}
}
