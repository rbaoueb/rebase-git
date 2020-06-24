package bowling;

public class BlockShot
{

	private int						scoreFirstShot;
	private int					scoreSecondShot;
	private static final int	STRIKE_SCORE	= 10;

	BlockShot(int scoreFirstShot, int scoreSecondShot)
	{
		this.scoreFirstShot = scoreFirstShot;
		this.scoreSecondShot = scoreSecondShot;
	}

	public boolean isStrike()
	{
		return scoreFirstShot == STRIKE_SCORE;
	}

	public boolean isSpare()
	{
		return !isStrike() && computeScoreBlock() == STRIKE_SCORE;
	}

	public int computeScoreBlock()
	{
		return scoreFirstShot + scoreSecondShot;
	}

	public int getScoreFirstShot()
	{
		return scoreFirstShot;
	}
}
