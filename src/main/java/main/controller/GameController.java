package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.models.Game;
import main.services.GameService;

@RestController
@RequestMapping("/gamee")
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping("/")
	public Game saveGame(@RequestBody Game game) {

		gameService.saveGame(game);

		return game;
	}

	@GetMapping("/{id}")
	public Game getGame(@PathVariable(name = "id") String id) {

		return gameService.getGame(id);

	}
}
