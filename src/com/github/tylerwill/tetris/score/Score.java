package com.github.tylerwill.tetris.score;

import com.github.tylerwill.tetris.Difficulty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Score implements Serializable {

  public int points;
  public int linesCleared;
  public int maxLevel;
  public int rank;
  public long gameTime;
  public String name;
  public Difficulty difficulty;
  public LocalDate dateAchieved;

  public Score(String name, int score, long gameTime, Difficulty difficulty, int linesCleared, int maxLevel) {
    this(-1, name, score, gameTime, difficulty, linesCleared, maxLevel, LocalDate.now());
  }

  Score(int rank, String name, int score, long gameTime, Difficulty difficulty, int linesCleared, int maxLevel, LocalDate date) {
    this.rank = rank;
    this.name = name;
    this.points = score;
    this.gameTime = gameTime;
    this.difficulty = difficulty;
    this.linesCleared = linesCleared;
    this.maxLevel = maxLevel;
    this.dateAchieved = date;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, name, points, gameTime, difficulty, linesCleared, maxLevel, dateAchieved);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Score) {
      Score other = (Score) o;
      return Objects.equals(name, other.name) &&
             Objects.equals(rank, other.rank) &&
             Objects.equals(points, other.points) &&
             Objects.equals(gameTime, other.gameTime) &&
             Objects.equals(difficulty, other.difficulty) &&
             Objects.equals(linesCleared, other.linesCleared) &&
             Objects.equals(maxLevel, other.maxLevel) &&
             Objects.equals(dateAchieved, other.dateAchieved);
    }
    return false;
  }

}