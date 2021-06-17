import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

// 0  IX-2342,Kingfisher,Bangalore,Hyderabad,05,01-Jan-2017

public class FlightMapper extends Mapper<LongWritable, Text, Text, FlightWritable>
{

    private FlightWritable flightInfo = new FlightWritable();

    @Override
    protected void map(LongWritable key, Text value, Context c)	throws IOException, java.lang.InterruptedException
    {

     	String line = value.toString();    // line -->    IX-2342,Kingfisher,Bangalore,Hyderabad,05,01-Jan-2017
		String[] words = line.split(",");   // words --> [ IX-2342  Kingfisher  Bangalore  Hyderabad  05  01-Jan-2017 ]

	            //  05        01-Jan-2017   IX-2342
	flightInfo.set(words[4], 1, words[5], words[0]);

	String monthYear = flightInfo.getFlightDate().split("-")[1];     
	monthYear += " " + flightInfo.getFlightDate().split("-")[2];        // key Jan 2017
	
	c.write(new Text(monthYear), flightInfo);
	               //Jan 2017    {05,1,01-Jan-2017,IX-2342}
    }
}
