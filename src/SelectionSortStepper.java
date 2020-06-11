// Carl Mastny
// ITPRG247
// Lab 6 - 23.15 p640
// This class tracks a selection sort step by step

public class SelectionSortStepper {
	private static int i = 0;
	private static int currentMin = 0;
	private static int currentMinIndex = -1;
	private static boolean sorted;
	
	public static boolean isSorted() {
		return sorted;
	}
	
	public static int getCurrentIndex() {
		return i;
	}
	
	public static void reset() {
		i = 0;
		currentMin = 0;
		currentMinIndex = -1;
		sorted = false;
	}
	
	public static int[] step(int[] values) {
		if (i >= values.length - 1) {
			sorted = true;
		}
		// Find the minimum in the values[i..values.length-1]
		currentMin = values[i];
		currentMinIndex = i;
		
		if (!sorted) {
			for (int j = i + 1; j < values.length; j++) {
				if (currentMin > values[j]) {
					currentMin = values[j];
					currentMinIndex = j;
				}
			}

			// Swap values[i] with values[currentMinIndex] if necessary;
			if (currentMinIndex != i) {
				values[currentMinIndex] = values[i];
				values[i] = currentMin;
			}
			
			i++;
			
		}
		return values;
	}
}
