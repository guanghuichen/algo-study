package five_Array;

/**
 * 1) 数组的插入、删除、按照下标随机访问操作；
 * 2）数组中的数据是int类型的；
 */
public class Array {
    private int count;//当前的数据个数
    private int[] data;//数组
    private int n;//当前数组的容量

    public Array(int n) {
        this.n = n;
        this.count = 0;
        this.data = new int[n];
    }

    /**
     * 检查是否越界
     *
     * @return
     */
    private boolean checklength(int index) {
        if (index < 0 || index >= n) {
            return true;
        }
        return false;
    }

    /**
     * 根据数组下标获取当前值
     *
     * @param index
     * @return
     */
    private int find(int index) {
        if (index < 0 || index >= n) {
            return -1;
        }
        return data[index];
    }

    /**
     * 输出所有数据
     */
    private void prinfAll() {
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * 根据下角标删除数据
     *
     * @param index
     * @return
     */
    private boolean delete(int index) {
        if (index < 0 || index >= n) {//数组越界
            return false;
        }
        //从删除位置开始，将后面的元素向前移动一位
        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        count--;
        return true;
    }

    /**
     * 根据下角标插入数据
     *
     * @param index
     * @param vaule
     * @return
     */
    private boolean insert(int index, int vaule) {
        if (index < 0 || index >= n) {//数组越界
            System.out.println("数组越界");
            return false;
        }
        if (count == n) {
            System.out.println("数据满了");
            return false;
        }
        //将数组以后的数据进行移位
        for (int i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        count++;
        data[index] = vaule;
        return true;
    }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.prinfAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        //array.insert(3, 11);
        array.prinfAll();
    }
}
