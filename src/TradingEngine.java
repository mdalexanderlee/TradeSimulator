
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class TradingEngine {
	private HashMap<Integer, Stock> idToStock;
	
	public TradingEngine() {
		Random generator = new Random();
		idToStock = new HashMap(10);
		for(int i = 0; i < 10 ; i++) {
			Stock toAdd = new Stock(100, generator.nextDouble(), (int)(generator.nextDouble()*3));
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
			Entry<Integer, Stock> next = it.next();
			Stock newStock = next.getValue();
			newStock.refresh();
			idToStock.replace(next.getKey(), newStock);
		}
	}
}
