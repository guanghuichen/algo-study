package five_Array;

public class GenericArray<T> {
    private T[] data;
    private int size;//当前元素的个数

    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public GenericArray() {
        this(10);
    }

    private int getCapacity() {
        return data.length;
    }

    private int getCount() {
        return size;//当前元素的个数
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 修改 index 位置的元素
    public void set(int index, T e) {
        checkIndex(index);
        data[index] = e;
    }

    // 获取对应 index 位置的元素
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    // 查看数组是否包含元素e
    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 获取对应元素的下标, 未找到，返回 -1
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 向数组头插入元素
    public void addFirst(T e) {
        add(0, e);
    }

    // 向数组尾插入元素
    public void addLast(T e) {
        add(size, e);
    }

    // 删除第一个元素
    public T removeFirst() {
        return remove(0);
    }

    // 删除末尾元素
    public T removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除指定元素
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // 扩容方法，时间复杂度 O(n)
    private void resize(int capacity) {
        //创建一个新的数组
        T[] newData = (T[]) new Object[capacity];
        //遍历旧数组。将数据转移到新的数组中去
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 在 index 位置，插入元素e, 时间复杂度 O(m+n)
    public void add(int index, T e) {
        //检查当前的地址是否超出了代销限制
        checkIndex(index);
        if (size == data.length) {//存储的数据已经满了
            //扩容
            resize(2 * data.length);//两倍的容量
        }
        T t = get(index);
        if (null != t) {//如果当前位置有数据进行移位
            for (int i = data.length - 1; i > index; i--) {//对原本的元素进行移位操作
                data[i] = data[i - 1];
            }
        }
        //将数据进行插入
        data[index] = e;
        size++;
    }

    // 删除 index 位置的元素，并返回
    public T remove(int index) {
        checkIndexForRemove(index);
        //取出数据
        T ret = data[index];
        for (int i = index + 1; i < data.length; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        // 缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }

    private void checkIndexForRemove(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < data.length; i++) {
            builder.append(data[i]);
            if (i != data.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    public static void main(String[] args) {
        GenericArray genericArray = new GenericArray(10);
        genericArray.add(1, 10);
        System.out.println(genericArray.toString());
        genericArray.add(8, 1);
        System.out.println(genericArray.toString());
//        genericArray.add(1,2);
        genericArray.add(3, 2);
        genericArray.add(3, 3);
        genericArray.add(3, 4);
        genericArray.add(3, 5);
        genericArray.add(3, 6);
        genericArray.add(3, 7);
        genericArray.add(3, 8);

        System.out.println(genericArray.toString());
    }
}
