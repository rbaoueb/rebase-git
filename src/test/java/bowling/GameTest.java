package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest
{
	private Game game;

	@BeforeEach
	void init()
	{
		game = new Game();
	}

	@Test
	void play_without_stike_and_spare()
	{
		/**** GIVEN ****/
		mockBlockShots(10, 1, 1);
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(20);
	}

	@Test
	void play_strike_test()
	{
		/**** GIVEN ****/
		mockBlockShots(2, 1, 1);
		playStrike();
		mockBlockShots(7, 1, 1);
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(30);
	}

	@Test
	void play_spare_test()
	{
		/**** GIVEN ****/
		playSpare();
		mockBlockShots(9, 1, 1);
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(29);
	}

	@Test
	void play_spare_with_bonus_test()
	{
		/**** GIVEN ****/
		mockBlockShots(9, 1, 1);
		playSpare();
		playBlockShot(2, 0);
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(30);
	}

	@Test
	void play_strike_with_bonus_test()
	{
		/**** GIVEN ****/
		mockBlockShots(9, 1, 1);
		playStrike();
		playBlockShot(1, 1);
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(30);
	}

	@Test
	void play_spare_and_strike_test()
	{
		/**** GIVEN ****/
		mockBlockShots(9, 1, 1);
		playStrike();
		playSpare();
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(38);
	}

	@Test
	void play_all_strike_test()
	{
		/**** GIVEN ****/
		mockBlockShots(12, 10, 0);
		/**** WHEN ****/
		int score = game.computeScore();
		/**** THEN ****/
		assertThat(score).isEqualTo(300);
	}

	/****
	 * AS IMPROVMENTS, THESE METHODS SHOULD BE IN BUSINESS LAYER AND TESTED
	 * SEPARATELY
	 ****/
	private void playStrike()
	{
		playBlockShot(10, 0);
	}

	private void playSpare()
	{
		playBlockShot(5, 5);
	}

	private void mockBlockShots(int nbBlocks, int firstShot, int secondShot)
	{
		IntStream.rangeClosed(1, nbBlocks).forEach(i -> playBlockShot(firstShot, secondShot));
	}

	private void playBlockShot(int firstShot, int secondShot)
	{
		game.playBlockShot(new BlockShot(firstShot, secondShot));
	}

}
