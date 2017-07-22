
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class MarketSimulator {
	private HashMap<Integer, Stock> idToStock;
	
	public MarketSimulator() {
		Random generator = new Random();
		idToStock = new HashMap(10);
		for(int i = 0; i < 10 ; i++) {
			Stock toAdd = new Stock(1000, generator.nextDouble(), (int)(generator.nextDouble()*4));
			idToStock.put(i, toAdd);
		}
	}
	
	public String toString() {
		Iterator<Entry<Integer, Stock>> it = idToStock.entrySet().iterator();
		Entry<Integer, Stock> theNext;
		String ret = "";
        while(it.hasNext()){
        	theNext = it.next();
            ret += "(" + (theNext).getKey() + ", " + (theNext).getValue() + ") ";
        }
		return ret;
	}
	
	public void refresh() {
		Iterator<Entry<Integer, Stock>> it = idToStock.entrySet().iterator();
		while(it.hasNext()) {
			it.next().getValue().refresh();
		}
	}
}
