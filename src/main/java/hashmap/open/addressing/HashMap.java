package hashmap.open.addressing;

public class HashMap {
    private static final int DEFAULT_TABLE_SIZE = 16;

    private HashEntry[] table;
    private int size;
    private int tableSize;

    public HashMap() {
        tableSize = DEFAULT_TABLE_SIZE;
        table = new HashEntry[tableSize];
        size = 0;
    }

    public long get(int key) {
        int hash = (key % tableSize);
        while (table[hash] != null && table[hash].getKey() != key) {
            hash = (hash + 1) % tableSize;
        }
        if (table[hash] == null) {
            return -1;
        } else {
            return table[hash].getValue();
        }
    }

    public void put(int key, long value) {
        if (size >= tableSize) {
            resize();
        }
        int hash = (key % tableSize);
        while (table[hash] != null && table[hash].getKey() != key) {
            hash = (hash + 1) % tableSize;
        }
        if (table[hash] == null) {
            table[hash] = new HashEntry(key, value);
            size++;
        } else if (table[hash] != null && table[hash].getKey() == key) {
            table[hash].setValue(value);
        }
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (tableSize > Integer.MAX_VALUE / 2) {
            return;
        }
        int newTableSize = tableSize * 2;
        HashEntry[] newTable = new HashEntry[newTableSize];
        for (int i = 0; i < tableSize; i++) {
            int hash = (table[i].getKey() % newTableSize);
            while (newTable[hash] != null && newTable[hash].getKey() != table[i].getKey()) {
                hash = (hash + 1) % newTableSize;
            }
            if (newTable[hash] == null) {
                newTable[hash] = new HashEntry(table[i].getKey(), table[i].getValue());
            } else if (newTable[hash] != null && newTable[hash].getKey() == table[i].getKey()) {
                newTable[hash].setValue(table[i].getValue());
            }
        }
        table = newTable;
        tableSize = newTableSize;
    }

    private static class HashEntry {

        private int key;
        private long value;

        HashEntry(int key, long value) {
            this.key = key;
            this.value = value;
        }

        long getValue() {
            return value;
        }

        void setValue(long value) {
            this.value = value;
        }

        int getKey() {
            return key;
        }
    }
}
