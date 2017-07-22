
import java.util.List;

public class main {

	public static void main(String[] args) {
		TradingEngine engine = new TradingEngine();
		for(int i = 0; i < 100; i++) {
			engine.refresh();
			System.out.println(engine);
		}
	}

}


