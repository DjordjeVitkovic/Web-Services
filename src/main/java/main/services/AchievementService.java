package main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import main.exception.MessageResponse;
import main.exception.NotFoundException;
import main.models.Achievement;
import main.models.Game;
import main.repositories.AchievementRepository;
import main.repositories.GameRepository;

@Service
public class AchievementService {

	@Autowired
	private AchievementRepository achievementRepo;
	@Autowired
	private GameRepository gameRepo;

	public void save(Achievement achievement) {

		Optional<Game> game = gameRepo.findById(achievement.getGameId().getId());
		achievement.setGameId(game.get());
		achievementRepo.save(achievement);
	}

	public List<Achievement> findAll() {

		return achievementRepo.findAll();
	}

	public List<Achievement> getAllByGame(String id) {

		if (!gameRepo.existsById(id)) {
			throw new NotFoundException("There are no Gane with id: " + id);
		}

		return achievementRepo.getAllByGame(id);
	}

	public Achievement getAchievementById(String id) {

		Achievement achievement = achievementRepo.getOne(id);

		if (achievement == null) {
			throw new NotFoundException("There are no Achievement with id: " + id);
		}
		return achievement;

	}

	public MessageResponse deleteAchievement(String id) {

		Achievement achievement = achievementRepo.getOne(id);

		if (achievement.getCreateDate() == null) {
			throw new NotFoundException("There are no Achievement with id: " + id);
		}

		achievementRepo.delete(achievement);

		return new MessageResponse(HttpStatus.OK.value(), "Success delete", System.currentTimeMillis());
	}

	// Znam da nije najbolji nacin za Update ali sam ovako odlucio kako bih mogao da
	// ga odradim preko ID iz path-a
	public Achievement updateAchievement(String id, Achievement achievement) {

		Achievement existingAchievement = achievementRepo.getOne(id);

		// Proveravam da li su svi fildovi popunjeni ako nisu da zadrzi stare
		if (achievement.getCreateDate() == null || achievement.getCreateDate() == "") {
			achievement.setCreateDate(existingAchievement.getCreateDate());
		}
		if (achievement.getDisplayName() == null || achievement.getDisplayName() == "") {
			achievement.setDisplayName(existingAchievement.getDisplayName());
		}
		if (achievement.getDescription() == null || achievement.getDescription() == "") {
			achievement.setDescription(existingAchievement.getDescription());
		}
		if (achievement.getIcon() == null || achievement.getIcon() == "") {
			achievement.setIcon(existingAchievement.getIcon());
		}
		if (achievement.getDisplayOrder() == 0) {
			achievement.setDisplayOrder(existingAchievement.getDisplayOrder());
		}
		if (achievement.getGameId() == null) {
			achievement.setGameId(existingAchievement.getGameId());
		}		
		if (achievement.getGameId().getId() == null || achievement.getGameId().getId() == "") {
			achievement.getGameId().setId(existingAchievement.getGameId().getId());
		}
		if (achievement.getGameId().getDisplayName() == null || achievement.getGameId().getDisplayName() == "") {
			achievement.getGameId().setDisplayName(existingAchievement.getGameId().getDisplayName());
		}

		// Mana je to sto fildovi koji se ne popune dodeli im se vrednost null
		BeanUtils.copyProperties(achievement, existingAchievement, "id");

		return achievementRepo.saveAndFlush(existingAchievement);

	}

}
