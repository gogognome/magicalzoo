package magicalzoo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MagicalZooStraightForward {

	private final Map<String, int[]> cache = new HashMap<>(100000);

	public int[] maxRemainingNrAnimals(int... animals) {
		String key = Arrays.toString(animals);
		int[] maxRemaining = cache.get(key);
		if (maxRemaining == null) {
			maxRemaining = calcMaxRemainingNrAnmials(animals);
		}
		cache.put(key, maxRemaining);
		return maxRemaining;
	}

	private int[] calcMaxRemainingNrAnmials(int... animals) {
		if (oneKindOfAnimalLeft(animals)) {
			return animals;
		}

		int[] maxRemaining = new int[] { 0, 0, 0 };
		for (int i=0; i<animals.length; i++) {
			int[] newAnimals = createAnimalUsingOthers(animals, i);
			if (newAnimals != null) {
				int[] newMaxRemainingAnimals = maxRemainingNrAnimals(newAnimals);
				if (sum(newMaxRemainingAnimals) > sum(maxRemaining)) {
					maxRemaining = newMaxRemainingAnimals;
				}
			}
		}

		return maxRemaining;
	}

	private int[] createAnimalUsingOthers(int[] animals, int newAnimal) {
		for (int i=0; i<animals.length; i++) {
			if (i != newAnimal && animals[i] == 0) {
				return null;
			}
		}

		int[] newAnimals = new int[animals.length];
		for (int i=0; i<animals.length; i++) {
			newAnimals[i] = animals[i] + (i == newAnimal ? 1 : -1);
		}
		return newAnimals;
	}

	private boolean oneKindOfAnimalLeft(int[] animals) {
		return Arrays.stream(animals).filter(n -> n > 0).count() == 1;
	}

	private int sum(int[] animals) {
		return Arrays.stream(animals).sum();
	}

}
