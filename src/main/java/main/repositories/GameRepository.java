package main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import main.models.Game;

public interface GameRepository extends JpaRepository<Game, String> {

}
