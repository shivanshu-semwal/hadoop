import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.IOException;
import java.util.Comparator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

//Jan 2017  [ {05,1,01-Jan-2017,IX-2342} {93,1,01-Jan-2017,IX-4644} {186,1,01-Jan-2017,IX-8345} {10,1,02-Jan-2017,IX-2342}......]

public class FlightReducer extends Reducer<Text, FlightWritable, Text, Text>
{
    @Override
    protected void reduce(Text key, Iterable<FlightWritable> values, Context c)	throws IOException, java.lang.InterruptedException
    {
	// Create a map to claculate total trips for each flight
	
    	                                          // flightNUmber, NumberOfTripsInThisMonth
	HashMap<String, Integer> TripCounts = new HashMap<String, Integer>();
	
	// Create a map to claculate total passengers for each flight
		                                           // flightNumber, TotalNumberOfPassengersForAllTripsIntHisMonth
	HashMap<String, Integer> PassengerCounts = new HashMap<String, Integer>();
	
	FlightWritable f = null;
	Iterator<FlightWritable> v1 = values.iterator();     // v1= 1,05,01-Jan-2017
	
	while (v1.hasNext())
	{	    
	    f = v1.next();             // f -->  05,1,01-Jan-2017,IX-2342
	   
	    String flightNumber = f.getFlightNumber();               //IX-2342
	    
	    if (TripCounts.containsKey(flightNumber))
	    {
		TripCounts.put(flightNumber, TripCounts.get(flightNumber)+1);
		
		PassengerCounts.put(flightNumber,PassengerCounts.get(flightNumber)+f.getPassengersCount());
	    }
	    else
	    {
		TripCounts.put(flightNumber, 1);                                // TripCounts       IX-2342, 2  IX-4644,1   IX-8345,1
		PassengerCounts.put(flightNumber, f.getPassengersCount());    // PassengerCounts     IX-2342,2859  IX-4644,93   IX-8345,186
	    }  }
	
	// AveragePassengers, FlightNUmber
	Map<Double, String> treeMap = new TreeMap<Double, String>(new Comparator<Double>() 
			{
		    @Override
		    public int compare(Double o1, Double o2)  //  {(131.0) IX-8567},{(129.0)IX-4656},{(128.0) IX-3563}
		    {
			return o2.compareTo(o1);
		    }
		 		});
	
	/* Calculate average passengers count for all flight for this month */
	String lessUtilizedFlitghts = "";
	
	for (Entry<String,Integer> e : PassengerCounts.entrySet())   //PassengerCounts  IX-2342,2859  IX-4644,93   IX-8345,186
	{
	    String flightNumber = e.getKey();      //  IX-2342
	    int passengersCount = e.getValue();      //  2859
	    int tripCount = TripCounts.get(flightNumber);     // 27

	    /* flights having more than 25 trips and less than 3000 passengers */
	    if ((passengersCount < 3000) && (tripCount > 25))
		lessUtilizedFlitghts += "," + flightNumber;             // lessUtilizedFlitghts = IX-2342
	    
	    Double avgPassengersPerMonth = new Double(passengersCount/tripCount*1.0);
	    treeMap.put(avgPassengersPerMonth, flightNumber);            
	}

	String out = "";
	int count = 0;
	
	for (Entry<Double, String> e : treeMap.entrySet())
	{
	    out += "," + e.getValue() + "(" + e.getKey() + ")";      //emit top three filghts for this month //
	    if (count++ >= 2)
		break;
	}

	/* emit less utilized flights as well */
	out += " - " + lessUtilizedFlitghts;

	/* format: month, top_3_flights - lessUtilizedFlights */
	c.write(key, new Text(out));
    } }

