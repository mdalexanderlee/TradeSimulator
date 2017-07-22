
import java.util.List;

public class main {

	public static void main(String[] args) {
		MarketSimulator market = new MarketSimulator();
		for(int i = 0; i < 100; i++) {
			market.refresh();
			System.out.println(market);
		}
	}

}


