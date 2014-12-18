package magicalzoo;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class MagicalZooOptimizedTest {

	private final MagicalZooOptimized magicalZoo = new MagicalZooOptimized();

	@Test
	public void testMagicalZoo() {
		assertArrayEquals(new int[] { 0, 0, 0 }, magicalZoo.maxRemainingNrAnimals(0, 0, 0));
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
		assertArrayEquals(new int[] { 0, 0, 23 }, magicalZoo.maxRemainingNrAnimals(17, 55, 6));
		assertArrayEquals(new int[] { 0, 4023, 0 }, magicalZoo.maxRemainingNrAnimals(2055, 2006, 2017));
	}

	@Test
	public void checkOptimizedSolutionAgainstStraightForwardSolution() {
		MagicalZooStraightForward magicalZooStraightForward = new MagicalZooStraightForward();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				for (int k=0; k<10; k++) {
					int[] animals = new int[] { i, j, k };
					int[] expectedAnimals = magicalZooStraightForward.maxRemainingNrAnimals(animals);
					Arrays.sort(expectedAnimals);
					int[] actualAnimals = magicalZoo.maxRemainingNrAnimals(animals);
					Arrays.sort(actualAnimals);
					assertEquals("Input: " + Arrays.toString(animals),
							Arrays.toString(expectedAnimals),
							Arrays.toString(actualAnimals));
				}
			}
		}
	}
}
