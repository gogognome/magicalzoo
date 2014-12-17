package magicalzoo;

import static org.junit.Assert.*;

import org.junit.Test;


public class MagicalZooStraightForwardTest {

	private final MagicalZooStraightForward magicalZoo = new MagicalZooStraightForward();

	@Test
	public void testMagicalZoo() {
		assertArrayEquals(new int[] { 1, 0, 0 }, magicalZoo.maxRemainingNrAnimals(1, 0, 0));
		assertArrayEquals(new int[] { 0, 1, 0 }, magicalZoo.maxRemainingNrAnimals(0, 1, 0));
		assertArrayEquals(new int[] { 0, 0, 1 }, magicalZoo.maxRemainingNrAnimals(0, 0, 1));

		assertArrayEquals(new int[] { 10, 0, 0 }, magicalZoo.maxRemainingNrAnimals(10, 0, 0));
		assertArrayEquals(new int[] { 0, 10, 0 }, magicalZoo.maxRemainingNrAnimals(0, 10, 0));
		assertArrayEquals(new int[] { 0, 0, 10 }, magicalZoo.maxRemainingNrAnimals(0, 0, 10));

		assertArrayEquals(new int[] { 0, 0, 1 }, magicalZoo.maxRemainingNrAnimals(1, 1, 0));
		assertArrayEquals(new int[] { 0, 1, 0 }, magicalZoo.maxRemainingNrAnimals(1, 0, 1));
		assertArrayEquals(new int[] { 1, 0, 0 }, magicalZoo.maxRemainingNrAnimals(0, 1, 1));

		assertArrayEquals(new int[] { 0, 0, 42 }, magicalZoo.maxRemainingNrAnimals(18, 20, 24));
	}

}
