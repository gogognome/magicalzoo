package magicalzoo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MagicalZooOptimized {

	private static class Animals {
		private final String key;
		private final int[] animals;
		private int[] maxRemainingAnimals;

		public Animals(int[] animals) {
			this.animals = animals;
			this.key = Arrays.toString(animals);
		}

		public String getKey() {
			return key;
		}

		public int[] getAnimals() {
			return animals;
		}

		public int[] getMaxRemainingAnimals() {
			return maxRemainingAnimals;
		}

		public void setMaxRemainingAnimals(int[] maxRemainingAnimals) {
			this.maxRemainingAnimals = maxRemainingAnimals;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Animals) {
				Animals that = (Animals) obj;
				return this.getKey().equals(that.getKey());
			}
			return false;
		}

		@Override
		public int hashCode() {
			return key.hashCode();
		}
	}

	public int[] maxRemainingNrAnimals(int... animals) {

		String keyWeAreLookingFor = new Animals(animals).getKey();

		Map<String, Animals> previousAnimals = new HashMap<>();
		Map<String, Animals> currentAnimals = new HashMap<>();
		for (int i=0; i<3; i++) {
			int[] singleAnimal = new int[3];
			singleAnimal[i] = 1;
			Animals tempAnimals = new Animals(singleAnimal);
			tempAnimals.setMaxRemainingAnimals(singleAnimal);
			put(currentAnimals, tempAnimals);
		}

		int nrAnimals = 1;
		while (!currentAnimals.containsKey(keyWeAreLookingFor)) {
			System.out.println("Nr animals: " + nrAnimals);
			previousAnimals = currentAnimals;
			currentAnimals = new HashMap<>();
			for (int indexToIncrease=0; indexToIncrease<3; indexToIncrease++) {
				for (Animals a : previousAnimals.values()) {
					int[] newAnimals = Arrays.copyOf(a.getAnimals(), 3);
					newAnimals[indexToIncrease]++;
					put(currentAnimals, new Animals(newAnimals));
				}
			}

			for (int indexToDecrease=0; indexToDecrease<3; indexToDecrease++) {
				for (Animals a : previousAnimals.values()) {
					if (a.getAnimals()[indexToDecrease] > 0) {
						int[] newAnimals = Arrays.copyOf(a.getAnimals(), 3);
						newAnimals[indexToDecrease]--;
						for (int index=0; index<3; index++) {
							if (index != indexToDecrease) {
								newAnimals[index]++;
							}
						}
						put(currentAnimals, new Animals(newAnimals));
					}
				}
			}

			for (Animals a : currentAnimals.values()) {
				a.setMaxRemainingAnimals(calcMaxRemainingNrAnmials(a.getAnimals(), previousAnimals));
			}

			nrAnimals++;
		}

		return currentAnimals.get(keyWeAreLookingFor).getMaxRemainingAnimals();
	}

	private void put(Map<String, Animals> map, Animals animals) {
		map.put(animals.getKey(), animals);
	}

	private int[] calcMaxRemainingNrAnmials(int[] animals, Map<String, Animals> previousAnimals) {
		if (oneKindOfAnimalLeft(animals)) {
			return animals;
		}

		int[] maxRemaining = new int[] { 0, 0, 0 };
		for (int i=0; i<animals.length; i++) {
			int[] newAnimals = createAnimalUsingOthers(animals, i);
			if (newAnimals != null) {
				int[] newMaxRemainingAnimals = previousAnimals.get(Arrays.toString(newAnimals)).getMaxRemainingAnimals();
				if (sum(newMaxRemainingAnimals) > sum(maxRemaining)) {
					maxRemaining = newMaxRemainingAnimals;
				}
			}
		}

		return maxRemaining;
	}

	private boolean oneKindOfAnimalLeft(int[] animals) {
		return Arrays.stream(animals).filter(n -> n > 0).count() == 1;
	}

	private int sum(int[] animals) {
		return Arrays.stream(animals).sum();
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
}
