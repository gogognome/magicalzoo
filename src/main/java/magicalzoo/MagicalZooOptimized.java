package magicalzoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicalZooOptimized {

	public int[] maxRemainingNrAnimals(int... animals) {
		List<Integer> odd = new ArrayList<>();
		List<Integer> even = new ArrayList<>();
		for (int n : animals) {
			if (n % 2 == 0) {
				even.add(n);
			} else {
				odd.add(n);
			}
		}

		List<Integer> majority = even.size() > odd.size() ? even : odd;
		List<Integer> minority = even.size() > odd.size() ? odd : even;

		Collections.sort(majority);
		int indexWithSmallestValueInMajority = -1;
		for (int index=0; index<3; index++) {
			if (animals[index] == majority.get(0)) {
				indexWithSmallestValueInMajority = index;
			}
		}

		int valueOfWinner = majority.size() == 3 ? majority.get(2) : minority.get(0);
		int indexOfWinner = -1;
		for (int index=2; index>=0; index--) {
			if (animals[index] == valueOfWinner) {
				indexOfWinner = index;
			}
		}

		int[] result = new int[3];
		result[indexOfWinner] = animals[indexOfWinner] + animals[indexWithSmallestValueInMajority];
		return result;
	}
}
