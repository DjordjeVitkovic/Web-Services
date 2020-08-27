package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.models.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, String> {

	@Query("from Achievement a where a.gameId.id like ?1 order by a.displayOrder")
	public List<Achievement> getAllByGame(String id);
	
	public List<Achievement> findByGameIdOrderByDisplayOrder(String id);

}
