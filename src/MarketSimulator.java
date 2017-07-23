
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class MarketSimulator {
	private HashMap<Integer, Stock> idToStock;
	private HashMap<Integer, HashMap<Long, Double>> idToTimeAndPrice;
	
	public MarketSimulator(int numStocks) {
		Random generator = new Random();
		idToStock = new HashMap(numStocks);
		idToTimeAndPrice = new HashMap(numStocks);
		for(int i = 0; i < numStocks ; i++) {
			Stock toAdd = new Stock(100, generator.nextDouble(), (int)(generator.nextDouble()*4));
			idToStock.put(i, toAdd);
			HashMap<Long, Double> toPut = new HashMap<Long, Double>();
			toPut.put(System.currentTimeMillis(), 100.00);
			idToTimeAndPrice.put(i, toPut);
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
			next.getValue().refresh();
			HashMap<Long, Double> timetoPrice = idToTimeAndPrice.get(next.getKey());
			if(timetoPrice == null)
				timetoPrice = new HashMap<Long, Double>();
			timetoPrice.put(System.nanoTime(), next.getValue().getPrice());
		}
	}
	
	public void generateCsv() throws IOException{
		Iterator<Entry<Integer, HashMap<Long, Double>>> it = idToTimeAndPrice.entrySet().iterator();
//		while(it.hasNext()) {
			Entry<Integer, HashMap<Long, Double>> next = it.next();
			HashMap<Long, Double> timetoPrice = next.getValue();
			Iterator<Entry<Long, Double>> itr = timetoPrice.entrySet().iterator();
			String output = "Time, Price(USD)";	
			while(itr.hasNext()) {
				output += "\n";
				Entry<Long, Double> entry = itr.next();
				output +=  entry.getKey() + ", " + entry.getValue();
			}

		//}
		File file = new File("marketdata/data_" + next.getKey() + ".csv");
		file.getParentFile().mkdirs();
		PrintWriter writer = new PrintWriter(file);
		writer.print(output);
		writer.close();
		System.out.println("Data printed to " + file.getAbsolutePath());
	}
}
