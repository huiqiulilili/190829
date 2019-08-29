import java.util.Arrays;

public class Practice {
//    private static int partition1(int[] array,int left,int right){
//        int less = left;
//        int great = right;
//        int pivot = array[right];
//        while(less < great){
//            while(array[])
//        }
//    }
    public static int partition(int[] array,int left,int right){
        int less = left;
        int great = right;
        int pivot = array[right];
        while(less < great){
            while(less < great && array[less] < pivot){
                less++;
            }
            array[great] = array[less];
            while(less < great &&array[great] >= pivot){
                great--;
            }

            array[less] = array[great];
        }
        //也可以是
        //array[great] = pivot;
        array[less] = pivot;
        return less;
    }
    private static void p(int[] array,int left,int right){
        if(left < right) {
            int pivot = partition(array, left, right);
            p(array, left, pivot - 1);
            p(array, pivot + 1, right);
        }
    }
    public static void result(int[] array){
        p(array,0,array.length - 1);
    }

    public static int partion3(int[] array,int left,int right){
        int rivion = array[right];
        int less = left;//蓝色箭头
        for(int i = left;i < right;i ++){//红色箭头
            if(array[i] < rivion){
                swap(array,less,i);
                less++;
            }
        }
        array[right] = array[less];
        array[less] = rivion;
        return less;
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int[] array = new int[]{9,8,7,6};
        result(array);
        System.out.println(Arrays.toString(array));
    }
}
