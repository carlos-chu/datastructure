package org.buptdavid.datastructure.sort;

/**
 * 选择排序<br>
 * 时间复杂度: 平均情况与最差情况都是O(n^2)<br>
 * 空间复杂度: O(1)
 * 
 * @author weijielu
 * @see ISort
 * @see SortTest
 */
public class SelectionSort implements ISort {

	public void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
			int k = i;
			for (int j = k + 1; j < arr.length; j++) {// 选最小的记录
				if (arr[j] < arr[k]) {
					k = j; // 记下目前找到的最小值所在的位置
				}
			}
			// 在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
			if (i != k) { // 交换a[i]和a[k]
				int temp = arr[i];
				arr[i] = arr[k];
				arr[k] = temp;
			}
		}
	}

}
