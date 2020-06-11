// Carl Mastny
// ITPRG247
// Lab 6 - 23.15 p640
// This class tracks an insertion sort step by step

public class InsertionSortStepper {
	private static int i = 0;
	private static int currentElement;
	private static boolean sorted = false;
	
	public static boolean isSorted() {
		return sorted;
	}
	
	public static void reset() {
		i = 0;
		currentElement = 0;
		sorted = false;
	}
	
	public static int getCurrentIndex() {
		return i;
	}
	
	public static int[] step(int[] values) {
		int k;
		
		currentElement = values[i];
		
		for (k = i - 1; k >= 0 && values[k] > currentElement; k--) {
			values[k + 1] = values[k];
		}
		
		values[k + 1] = currentElement;
		
		i++;
		
		if (i >= values.length) {
			sorted = true;
			i--;
		}
		
		return values;
	}
}
