package _03_RemovingStuffFromArrayLists;

import java.util.ArrayList;

public class ArrayListRemove {

    class Stuff {
        public String type;
    }
    
    class Worm extends Stuff {
        public Worm() {
            type = "worm";
        }
    }
    
    class Dirt extends Stuff {
        public Dirt() {
            type = "dirt";
        }
    }

    // 1. Write a method that removes the dirt in the yard and returns the
    //    ArrayList
    public static ArrayList<Stuff> cleanOutTheYard( ArrayList<Stuff> yard ) {
        for(int i = 0; i<yard.size(); i++) {
        	for(int k = 0; k<yard.size(); k++) {
        		if (yard.get(k).type == "dirt") {
        			yard.remove(k);
        			
        		}
        	}
        }
        return yard;
    }
    
    // 2. Write a method that removes the hash tag ('#') characters from the
    //    ArrayList and returns it
    public static ArrayList<Character> removeHashTags(ArrayList<Character> list) {
    	for(int i =0; i<list.size(); i++) {
    		for(int k =0; k<list.size(); k++) {
    			if(list.get(k) == '#'){
    			list.remove(k);
    			}
    		}
    	}
        return list;
    }
}
