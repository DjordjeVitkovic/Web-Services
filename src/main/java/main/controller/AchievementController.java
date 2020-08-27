package main.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.exception.NotFoundException;
import main.models.Achievement;
import main.models.Game;
import main.services.AchievementService;

@RestController
@RequestMapping(path = "/ach")
public class AchievementController {

	@Autowired
	private AchievementService achievementService;

	@PostMapping("/")
	public Achievement savePlayer(@Valid @RequestBody Achievement achievement) {

		achievement.setDate();
		achievementService.save(achievement);

		return achievement;
	}

	@GetMapping("/")
	public List<Achievement> getAllAccounts() {

		return achievementService.findAll();
	}

	@GetMapping("/game/{id}")
	public List<Achievement> getAllByGame(@PathVariable(name = "id") String id) {

		
		List<Achievement> list = achievementService.getAllByGame(id);
		
		if(list.size() == 0) {
			throw new NotFoundException("There are no Achievement with gameId: " + id);
		}
		
		return list;
	}

	@GetMapping("/{id}")
	public Achievement getAchievement(@PathVariable(name = "id") String id) {
		
		return achievementService.getAchievementById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteAchievement(@PathVariable(name = "id") String id) {
		achievementService.deleteAchievement(id);
	}

	@PutMapping("/{id}")
	public Achievement updateAchievement(@PathVariable(name = "id") String id,@Valid @RequestBody Achievement achievement) {

		achievement.updateDate();
		achievementService.updateAchievement(id, achievement);

		return achievement;
	}
}
