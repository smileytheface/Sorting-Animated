// Carl Mastny
// ITPRG247
// Lab 6 - 23.15 p640
// This class tracks a bubble sort step by step

public class BubbleStepper {
	
	private static boolean sorted = false;
	private static int i = 1;
	private static int j = 0;
	
	public static int getCurrentIndex() {
		return j;
	}
	
	public static void reset() {
		i = 1;
		j = 0;
		sorted = false;
	}
	
	public static boolean isSorted() {
		return sorted;
	}
	
	public static int[] step(int[] values) {
	
		if (i >= values.length)
			sorted = true;
	
		if (j < values.length - i) {
			if (values[j] > values[j + 1]) {
				// Swap list[i] with list[i + 1]
				int temp = values[j];
				values[j] = values[j + 1];
				values[j + 1] = temp;
			}
			
			j++;
		} else {
			i++;
			j = 0;
		}
	
		return values;
	}
}
