package lru;

import java.util.HashMap;
import java.util.Map;

public class LRUBasedArray<T> {
    private static final int DEFAULT_CAPACITY = (1 << 3);

    private int capacity;

    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        value = (T[]) new Object[capacity];//初始化缓存数组
        count = 0;
        holder = new HashMap<T, Integer>(capacity);//初始化
    }


    /**
     * 模拟访问某个值
     *
     * @param object
     */
    public void offer(T object) {
        if (object == null) {
            throw new IllegalArgumentException("该缓存容器不支持null!");
        }
        //产看当前数据在 缓存中是否存在
        Integer integer = holder.get(object);
        if (null == integer) {
            if (isFull()) {//当前缓存是否已满
                removeAndCache(object);
            } else {
                cache(object, count);//没有满直接插入
            }
        } else {//存在则直接更新
            update(object);
        }
    }

    /**
     * 缓存数组是否已经满了
     *
     * @return
     */
    private boolean isFull() {
        return count == capacity;
    }

    /**
     * 当前数据在缓存中不存在，且当前缓存数组已经满了。。
     * 将数组最后一个数据剔除数组，并将当前的数据插入到数组的开头
     *
     * @param obj
     */
    public void removeAndCache(T obj) {
        //获取最后一个数据的的值
        T key = value[--count];
        holder.remove(obj);
        //插入数据到头部
        cache(obj, count);
    }

    /**
     * 将所有数组内的所有数据右移。
     *
     * @param end
     */
    public void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holder.put(value[i], i + 1);
        }
    }

    /**
     * 更新数据：当前的数据在数组中是已经存在的。此时直接更新即可
     *
     * @param obj
     */
    public void update(T obj) {
        //获取当前数据的位置
        Integer index = holder.get(obj);
        //将这个数据位置之前的所有数据右移
        rightShift(index);
        value[0] = obj;//插入数据
        holder.put(obj, 0);
    }

    /**
     * 当前数据是第一次访问。将当前数据放在数组的头部
     *
     * @param obj ---要存入的对象
     * @param end ---是数组中存了多少个值
     */
    public void cache(T obj, int end) {
        //将所有数据右移一位
        rightShift(end);
        //将数据放再 0 的位置
        value[0] = obj;
        count++;
        //将当前数据存入map中
        holder.put(obj, 0);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    static class TestLRUBasedArray {

        public static void main(String[] args) {
//            testDefaultConstructor();
            testSpecifiedConstructor(4);
//            testWithException();
        }

        private static void testWithException() {
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
            lru.offer(null);
        }

        public static void testDefaultConstructor() {
            System.out.println("======无参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>();
            lru.offer(1);
            lru.offer(2);
            lru.offer(3);
            lru.offer(4);
            lru.offer(5);
            System.out.println(lru);
            lru.offer(6);
            lru.offer(7);
            lru.offer(8);
            lru.offer(9);
            System.out.println(lru);
        }

        public static void testSpecifiedConstructor(int capacity) {
            System.out.println("======有参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<Integer>(capacity);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(3);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(7);
            System.out.println(lru);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
        }
    }
}
