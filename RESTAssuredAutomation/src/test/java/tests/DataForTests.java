package tests;

import org.testng.annotations.DataProvider;

public class DataForTests {
	
	@DataProvider(name = "DataForPost")
	public Object[][] dataForPost(){
		
//		Object[][] data = new Object[2][3];
//		
//		data[0][0] = "Peter";
//		data[0][1] = "Parker";
//		data[0][2] = 2;
//		
//		data[1][0] = "Steve";
//		data[1][1] = "Rogers";
//		data[1][2] = 1;
		
		//either we can use above approach or below
		
		
		return new Object[][] {
			{"Peter", "Parker", 2},
			{"Steve", "Rogers", 1}
		};
	}
	
	@DataProvider(name = "DeleteUsers")
	public Object[] dataForDelete() {
		return new Object[] {4,5};
	}
	
}
