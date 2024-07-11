public class QuadraticProbeMap<K,V>{
    private int capacity;
    private int size;
    private MapEntry<K,V>[] array;

    private static class MapEntry<K,V>{
        private K key;
        private V value;

        MapEntry(K key,V value){
            this.key = key;
            this.value = value;
        }

        K getKey(){
            return key;
        }

        V getValue(){
            return value;
        }
    }

    @SuppressWarnings("unchecked")
    public QuadraticProbeMap(int startCap){
        capacity = startCap;
        size = 0;
        array = (QuadraticProbeMap.MapEntry<K,V>[]) new MapEntry[capacity];
    }

    public int size(){
        return size;
    }

    public int capacity(){
        return capacity;
    }

    private int hashKey(K key){
        int intKey = (Integer) key;
        int number = (102 * intKey) + 40790475;
        number = number % 109345121;
        return number;
    }

    private int findIndex(K key) {
        int hashCode = hashKey(key);
        int index = hashCode % array.length;
        int i = 0;

        while(array[index] != null && array[index].getKey().equals(key) == false){
            i++;
            index = (hashCode + i * i) % array.length;
            if (index < 0) {
                index += array.length;
            }
        }

        return index;
    }

    private int findPrime(int number){
        while(0<1){
            if (number <= 1) {
                number++;
                continue;
            }
            boolean isPrime = true;
            for(int i = 2; i * i <= number; i++){
                if(number % i == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                return number;
            }
            number++;
        }
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        int newCapacity = findPrime(2 * capacity);
        @SuppressWarnings("rawtypes")
        MapEntry[] oldArray = array;
        array = new MapEntry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (MapEntry<K,V> entry : oldArray) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    public void put(K key, V value) {
        // if(((double)size) / capacity > 0.4){
        //     expand();
        // }
        if(key == null){
            return;
        }
        else{
            int index = findIndex(key);

            if(array[index] == null){
                MapEntry<K,V> newEntry = new MapEntry<>(key,value);
                array[index] = newEntry;
                size++;
            } 
            else{
                array[index].value = value;
            }

            if(((double)size) / capacity > 0.4){
                expand();
            }
        }
    }

    public V get(K key){
        if(key == null){
            return null;
        }
        else{
            int index = findIndex(key);

            if(array[index] != null){
                K myKey = array[index].getKey();
                V myValue = array[index].getValue();
                if(myKey.equals(key)){
                    return myValue;
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public V delete(K key) {
        int index = findIndex(key);
        if(array[index] != null && array[index].getKey().equals(key)){
            V value = array[index].getValue();
            array[index] = null;
            size--;

            MapEntry<K, V>[] oldArray = array;
            array = (MapEntry<K, V>[]) new MapEntry[capacity];
            int oldSize = size;
            size = 0;

            for (MapEntry<K, V> entry : oldArray) {
                if (entry != null) {
                    put(entry.getKey(), entry.getValue());
                }
            }
            size = oldSize;
            return value;
        }
        return null;
    }
}