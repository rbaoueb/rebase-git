package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Game
{
	private static final int	NB_BLOCKS	= 10;

	private List<BlockShot>		blockShots	= new LinkedList<>();

	public void playBlockShot(BlockShot frame)
	{
		blockShots.add(frame);
	}

	public int computeScore()
	{
		return IntStream.range(0, NB_BLOCKS).reduce(0, (score, index) -> score + getBlockAt(index).computeScoreBlock() + getBlockBonus(index));
	}

	private int getBlockBonus(int frameIndex)
	{
		BlockShot frame = getBlockAt(frameIndex);
		if (frame.isStrike())
		{
			return getStrikeBonus(frameIndex);
		}
		else if (frame.isSpare())
		{
			return getSpareBonus(frameIndex);
		}
		return 0;
	}

	private int getStrikeBonus(int index)
	{
		BlockShot nextFrame = getBlockAt(index + 1);
		if (nextFrame.isStrike())
		{
			return nextFrame.computeScoreBlock() + getBlockAt(index + 2).getScoreFirstShot();
		}
		return nextFrame.computeScoreBlock();

	}

	private int getSpareBonus(int index)
	{
		return getBlockAt(index + 1).getScoreFirstShot();
	}

	private BlockShot getBlockAt(int index)
	{
		if (index + 1 <= blockShots.size())
		{
			return blockShots.get(index);
		}
		throw new IllegalStateException("Frame " + index + " is missing...");
	}
}
