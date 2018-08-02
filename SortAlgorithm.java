package sort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/7/21
 * @Description    排序算法
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);        //控制台输入完成后，以“;”作为结束符
        System.out.println("请输入想使用的算法的序号：");
        int kind = in.nextInt();
        System.out.println("请输入想要排序的数组:");
        ArrayList<Integer> a = new ArrayList<>();
        while (!in.hasNext(";")){
            a.add(in.nextInt());
        }
        switch (kind){

            case 1:
                bubbleSort(a);
                break;

            case 2:
                bubbleSort1(a);
                break;

            case 3:
                bubbleSort2(a);
                break;

            case 4:
                selectSort(a);
                break;

            case 5:
                insertSort(a);
                break;

            case 6:
                shellSort(a);
                break;

            case 7:
                heapSort(a);
                break;

            case 8:
                mergeSort(a);
                break;

            case 9:
                quickSort(a);
                break;
        }

        System.out.println(a);
    }


    public static void bubbleSort(ArrayList<Integer> a){                           //简单冒泡排序
        int temp = 0;
        for(int i = 0;i<a.size();i++){
            for(int j=i+1;j<a.size();j++){
                if(a.get(i) > a.get(j)) {
                    temp = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,temp);
                }
            }
        }

    }

    public static void bubbleSort1(ArrayList<Integer> a){                          //正宗冒泡排序
        int temp = 0;
        for(int i = 0; i<a.size();i++){
            for(int j=a.size()-2;j>=i;j--){
                if(a.get(j) > a.get(j+1)) {
                    temp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, temp);
                }
            }
        }

    }

    private static void bubbleSort2(ArrayList<Integer> a){                        //改进冒泡排序
        Boolean Flag = true;
        int temp = 0;
        for(int i = 0; i<a.size() && Flag;i++){
            Flag = false;
            for(int j=a.size()-2;j>=i;j--){
                if(a.get(j) > a.get(j+1)) {
                    temp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, temp);
                    Flag = true;
                }
            }
        }

    }

    public static void selectSort(ArrayList<Integer> a){                              //简单选择排序

        int i,j,min;

        for(i = 0;i<a.size();i++){
            min = i;
            for(j=i+1;j<a.size();j++){
                if(a.get(min) > a.get(j))
                    min = j;
            }
            if(i!=min) {
                int temp = a.get(i);
                a.set(i, a.get(min));
                a.set(min, temp);
            }
        }

    }

    public static void insertSort(ArrayList<Integer> a){                        //直接插入排序

        int i,j;
        int s = 0;
        for(i = 1; i<a.size();i++){
            if (a.get(i) < a.get(i - 1)) {
                s=a.get(i);
                for(j=i-1;j>=0&&a.get(j)>s;j--){
                    a.set(j+1,a.get(j));
                }

                a.set(j+1,s);
            }
        }

    }

    public static void shellSort(ArrayList<Integer> a){                                  //希尔排序

        int i,j;
        int increment=a.size();                       //增量
        do{
            increment = increment/3+1;
            for(i=increment;i<a.size();i++){
                if(a.get(i)<a.get(i-increment)){
                    int s = a.get(i);

                    for(j=i-increment;j>=0&&s<a.get(j);j-=increment)
                        a.set(j+increment,a.get(j));

                    a.set(j+increment,s);
                }
            }
        }
        while (increment > 1);

    }

    public static void heapSort(ArrayList<Integer> a){                              //堆排序

        int i;
        for(i = a.size()/2-1;i >= 0 ; i--){
            HeapAdjust(a,i,a.size());
        }

        for(i = a.size()-1;i>0;i--){
            int t = a.get(0);
            a.set(0,a.get(i));
            a.set(i,t);
            HeapAdjust(a,0,i);
        }

    }

    public static void mergeSort(ArrayList<Integer> a){                                        //归并排序

        mSort(a,0,a.size()-1);

    }

    public static void quickSort(ArrayList<Integer> a){                                        //快速排序
        QSort(a,0,a.size()-1);

    }

    private static void QSort(ArrayList<Integer> a,int low,int high){
        if(low<high){
            int pivotloc=Partition(a,  low,  high);
            QSort(a,low,pivotloc-1);
            QSort(a,pivotloc+1,high);
        }
    }

    private static int Partition(ArrayList<Integer> a,int low,int high){
        int pivotkey=a.get(low);
        int x=a.get(low);
        while(low<high){
            while(low<high&&a.get(high)>=pivotkey)
                --high;
            int t = a.get(low);
            a.set(low,a.get(high));
            a.set(high,t);
            while(low<high&&a.get(low)<=pivotkey)
                ++low;
            int y = a.get(low);
            a.set(low,a.get(high));
            a.set(high,y);
        }
        a.set(low,x);
        return low;
    }

    private static void mSort(ArrayList<Integer> a,int s,int m){
        int t;
        if(s >= m){
            return ;
        }
        else {
            t = (s+m)/2;
            mSort(a,s,t);
            mSort(a,t+1,m);
            Merge(a,s,t,m);
        }
    }

    private static void Merge(ArrayList<Integer> a, int s, int t, int m){
        int[] b = new int[a.size()];
        int k = t + 1;
        int j = s;
        int l = s;

        while(s <=t && k <= m) {
            if (a.get(s) <= a.get(k))
                b[j++] = a.get(s++);
            else
                b[j++] = a.get(k++);
        }

        while (s <= t ) {
            b[j++] = a.get(s++);
        }

        while ( k <= m ) {
            b[j++] = a.get(k++);
        }

        while(l <= m){
            a.set(l,b[l]);
            l++;
        }
    }
    private static void HeapAdjust(ArrayList<Integer> a, int s ,int m){
        int t,j;
        t = a.get(s);

        for(j = 2*s+1; j < m; j = 2 * j+1 ) {
            if (j+1 < m && a.get(j) < a.get(j + 1))
                ++j;
            if (t >= a.get(j))
                break;

            a.set(s, a.get(j));
            s = j;
        }
        a.set(s,t);
    }

}
