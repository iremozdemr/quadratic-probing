public class Main {
    public static void main(String[] args) {
        // create a QuadraticProbeMap with initial capacity 17
        QuadraticProbeMap<Integer, String> map = new QuadraticProbeMap<>(17);

        // adding elements
        map.put(1000, "value1");
        map.put(2000, "value2");
        map.put(3000, "value3");
        map.put(4000, "value4");
        map.put(5000, "value5");
        map.put(6000, "value6");
        map.put(7000, "value7");
        map.put(8000, "value8");
        map.put(9000, "value9");
        map.put(10000, "value10");
        map.put(11000, "value11");
        map.put(12000, "value12");
        map.put(13000, "value13");
        map.put(14000, "value14");
        map.put(15000, "value15");
        // map.put(16000, "value16");
        // map.put(17000, "value17");
       
        // getting elements
        System.out.println("value for key 1000: " + map.get(1000)); // output: Value1
        System.out.println("value for key 2000: " + map.get(2000)); // output: Value2
        System.out.println("value for key 3000: " + map.get(3000)); // output: Value3
        System.out.println("value for key 4000: " + map.get(4000)); // output: Value4
        System.out.println("value for key 5000: " + map.get(5000)); // output: Value5
        
        // printing size and capacity
        System.out.println("size of the map: " + map.size()); 
        System.out.println("capacity of the map: " + map.capacity()); // output: 37 (after expansion)
    }
}