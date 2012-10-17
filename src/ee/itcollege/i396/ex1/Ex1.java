package ee.itcollege.i396.ex1;

public class Ex1 {
	
	/**
	 * Method changed from private to public for testing purposes.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int coverage1(int i, int j) {
		int k = 0;
		if (i == 1) {
			k = 1;
		} else if (i == 2) {
			k = 2;
		} else {
			k = 3;
		}

		for (int z = k; z < j; z++) {
			k++;
		}

		while (k > 2) {
			k--;
		}

		return k;
	}

	public int coverage2(int[] a, int x) {
		int l = 0, r = 0, z = 0;
		if (a.length == 0) {
			z = 1;
		} else {
			if (x < a[0]) {
				z = 2;
			} else {
				if (x > a[a.length - 1]) {
					z = 3;
				} else {
					l = 0;
					r = a.length - 1;
					while (l < r - 1) {
						l++;
					}
				}
			}
		}

		return l + r + z;
	}
}
