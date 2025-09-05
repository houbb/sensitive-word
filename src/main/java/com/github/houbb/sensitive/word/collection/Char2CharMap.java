package com.github.houbb.sensitive.word.collection;

/**
 * 原生无装箱、拆箱的实现
 *
 * @since 0.29.2
 */
public final class Char2CharMap {

    private static final char EMPTY_KEY = '\0'; // 特殊标记，表示空槽
    private static final float LOAD_FACTOR = 0.5f;

    private char[] keys;
    private char[] values;
    private int size;
    private int mask;   // capacity-1，用于快速取模
    private int maxSize;

    public Char2CharMap(int expectedSize) {
        int capacity = tableSizeFor((int) (expectedSize / LOAD_FACTOR) + 1);
        this.keys = new char[capacity];
        this.values = new char[capacity];
        this.mask = capacity - 1;
        this.maxSize = (int) (capacity * LOAD_FACTOR);
        this.size = 0;
    }

    /** 2 的幂次方容量 */
    private static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 2) ? 2 : (n >= (1 << 30) ? (1 << 30) : n + 1);
    }

    private int hash(char k) {
        return (k * 0x9E3779B9) & mask; // 乘法哈希 + mask
    }

    /** 插入或覆盖 */
    public void put(char key, char value) {
        if (key == EMPTY_KEY) {
            throw new IllegalArgumentException("Key '\0' is reserved as EMPTY_KEY.");
        }
        int idx = hash(key);
        while (true) {
            if (keys[idx] == EMPTY_KEY) {
                keys[idx] = key;
                values[idx] = value;
                if (++size >= maxSize) {
                    resize();
                }
                return;
            } else if (keys[idx] == key) {
                values[idx] = value;
                return;
            }
            idx = (idx + 1) & mask;
        }
    }

    /** 查询，不存在时返回 defaultValue */
    public char get(char key, char defaultValue) {
        if (key == EMPTY_KEY) return defaultValue;
        int idx = hash(key);
        while (true) {
            char k = keys[idx];
            if (k == EMPTY_KEY) return defaultValue;
            if (k == key) return values[idx];
            idx = (idx + 1) & mask;
        }
    }

    public char get(char key) {
        char defaultVal = 0;
        return get(key, defaultVal);
    }

    private void resize() {
        int newCap = keys.length << 1;
        char[] oldKeys = keys;
        char[] oldVals = values;

        keys = new char[newCap];
        values = new char[newCap];
        mask = newCap - 1;
        maxSize = (int) (newCap * LOAD_FACTOR);
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            char k = oldKeys[i];
            if (k != EMPTY_KEY) {
                put(k, oldVals[i]);
            }
        }
    }

    public int size() {
        return size;
    }
}


