
import java.io.IOException;
import java.util.List;

public class main {

	public static void main(String[] args) throws IOException {
		MarketSimulator market = new MarketSimulator(10);
		for(int i = 0; i < 10000; i++)
			market.refresh();
		market.generateCsv(); 
	}
}


