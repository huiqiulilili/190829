import java.util.Arrays;

public class Practice2 {

    //直接插入排序
    //需要循环 n - 1 次
    //有序区间：[0,i ]
    //无序区间：[i + 1,array.length)
    public static void insertSortV1(int[] array){
        for(int i = 0;i < array.length - 1;i ++){
            int right = array[i + 1];
            int j;
            for(j = i;j >= 0 && array[j] > right;j --){
                array[j + 1] = array[j];
            }
            array[j + 1] = right;
        }
    }
    //希尔排序

    public static void shellSort(int[] array){
        int gap = array.length;
        while(true){
            gap = gap / 3 + 1;
            insertSortShell(array,gap);
            if(gap == 1){
                break;
            }
        }
    }

    private static void insertSortShell(int[] array, int gap) {
        //需要进行 n - gap 次循环
        //有序区间：[0,i)
        //无序区间：[i + 1,array.length)
        for(int i = 0;i < array.length - gap;i ++){
            int right = array[i + gap];
            int j;
            for(j = i;j >= 0 && array[j] > right;j -= gap){
                array[j + gap] = array[j];
            }
            array[j + gap] = right;
        }
    }

    //直接选择排序
    //需要进行 n - 1 次 比较，因为做后一次比较只有两个元素，比较一次，就可以了
    //有序区间：[array.length - i,array.length]
    //无序区间：[0,array.length - i)
    //从无需区间中找出最大的数字 和 无序区间的最一个数字 交换
    public static void selectSort(int[] array){
        for(int i = 0;i < array.length - 1;i ++){
            int max = 0;
            for(int j = 0;j < array.length - i;j ++){
                if(array[j] > array[max]){
                    max = j;
                }
            }
            swap(array,max,array.length - i - 1);
        }
    }
    //选择排序2
    public static void selectSortVersion2(int[] array){
        int low = 0;
        int high = array.length - 1;
        while(low < high){
            int min = low;
            int max = low;
            for(int i = low + 1;i <= high;i ++){
                if(array[i] > array[max]){
                    max = i;
                }
                if(array[i] < array[min]){
                    min = i;
                }
            }
            swap(array,min,low);
            if(max == low){
                max = min;
            }
            swap(array,max,high);
            low++;
            high--;
        }
    }


    //堆排序
    public static void heapSort(int[] array){
        //建立大堆
        int size = array.length;
        createdHight(array,size);
        //将对顶元素与最后一个元素交换位置
        //重新堆化
        //重复以上两个操作
        for(int i = 0;i < array.length - 1;i ++){
            swap(array,0,array.length - i - 1);
            heapity(array,array.length - i - 1,0);
        }
    }

    //冒泡排序

    public static void bubbleSort(int[] array){
        //首先需要进行 n - 1 次比较   最后两个秩序比较一次
        //有序区间：[array.length - i,array.length)
        //无序区间：[0,array.length - i)
        //比较无序区间两两相邻的数
        for(int i = 0;i < array.length - 1;i ++){
            boolean result = true;
            for(int j = 0;j < array.length - i - 1;j ++){
                if(array[j + 1] < array[j]){
                    swap(array,j,j + 1);
                    result = false;
                }
            }
            if(result == false){
                break;
            }
        }
    }

    private static void createdHight(int[] array, int size) {
        int parent = (size - 2) / 2;
        for(int i = parent;i >=0;i --){
            heapity(array,size,i);
        }
    }

    private static void heapity(int[] array, int size, int index) {
        while(true) {
            int left = index * 2 + 1;
            if (left >= size) {
                return;
            }
            int max = left;
            if (left + 1 < size && array[left + 1] > array[left]) {
                max = left + 1;
            }

            if (array[index] < array[max]) {
                swap(array, max, index);
            }
            index = max;
        }
    }

    //快速排序

    public static void quickSort(int[] array){
        //首先找到基准值：
        //其次确定基准值的下表
        //便利 小的放在左边，  大的放在右边
        quickSortInternal(array,0,array.length - 1);
    }

    private static void quickSortInternal(int[] array, int left, int right){
        if(left >= right){
            return;
        }
        int[] indices = partition4(array,left,right);

//        int pivotIndex = partition3(array,left,right);
//        quickSortInternal(array,0,pivotIndex - 1);
//        quickSortInternal(array,pivotIndex + 1,right);
        quickSortInternal(array,left,indices[0] - 1);
        quickSortInternal(array,indices[1] + 1,right);
    }

    private static int partition(int[] array, int left, int right) {
        int less = left;
        int great = right;
        int pivot = array[right];
        while(less < great){
            while(less < great && array[less] <= pivot){
                less++;
            }
            while(less < great && array[great] >= pivot){
                great--;
            }
            swap(array,less,great);
        }
        swap(array,less,right);
        return less;
    }

    private static int partition2(int[] array,int left,int right){
        int less = left;
        int great = right;
        int pivot = array[right];
        while(less < great){
            while(less < great && array[less] < pivot){
                less++;
            }
            array[great] = array[less];
            while(less < great && array[great] > pivot){
                great--;
            }
            array[less] = array[great];
        }
        array[less] = pivot;
        return less;
    }
    private static int partition3(int[] array,int left,int right){
        int less = left;
        int pivot = array[right];
        for(int i = left;i < right; i++){
            if(array[i] < pivot){
                swap(array,i,less);
                less++;
            }
        }
        swap(array,less,right);
        return less;
    }

    private static int[] partition4(int[] array,int left,int right){

        int pivon = array[right];
        int less = left;
        int i = left;
        int great = right;
        while(i < great){
            if(array[i] == pivon){
                i ++;
            }else if(array[i] < pivon){
                swap(array,i,less);
                i ++;
                less ++;
            }else{
                while(less < great && array[great] > pivon){
                    great--;
                }
                swap(array,i,great);
            }
        }
        return new int[]{less,great - 1};
    }
    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }



    public static void main(String[] args) {
        int[] array = new int[]{9,6,4,7,3,5,1};
//        insertSortV1(array);
//        System.out.println(Arrays.toString(array));
//        shellSort(array);
//        System.out.println(Arrays.toString(array));
//        selectSort(array);
//        System.out.println(Arrays.toString(array));
//        selectSortVersion2(array);
//        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
        quickSort(array);
        System.out.println(Arrays.toString(array));

    }
}
