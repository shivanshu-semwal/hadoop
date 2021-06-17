import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class FlightWritable implements Writable {

    private int passengersCount;
    private int tripsCount;
    private String flightDate;
    private String flightNumber;
    
    public FlightWritable() 
    {
	set("0", 0, "", "");
    }
    
       /* getters and setters for custom values */
    public void set(String passengersCount,int tripsCount,String dateStr,String flightNumber) 
    {
    this.passengersCount = Integer.parseInt(passengersCount);
	this.flightDate = dateStr;
	this.tripsCount = tripsCount;
	this.flightNumber = flightNumber;
    }
    
    public int getPassengersCount() 
    {
    	return this.passengersCount;
    }
    public int getTripsCount()
    {
    	return this.tripsCount;
    }
    public String getFlightDate() 
    {
    	return this.flightDate;
    }
    public String getFlightNumber()
    {
    	return this.flightNumber;
    }
        
    @Override  // Mandatory function for Hadoop to know how to write values for this class //

    public void write(DataOutput out) throws IOException 
    {
	    out.writeInt(this.passengersCount);
       	out.writeInt(this.tripsCount);
	WritableUtils.writeString(out, this.flightDate);
	WritableUtils.writeString(out, this.flightNumber);
    }
   
    @Override    //Mandatory function for Hadoop to know how to read values for this class  //
    public void readFields(DataInput in) throws IOException
    {
	this.passengersCount = in.readInt();
	this.tripsCount = in.readInt();
	this.flightDate = WritableUtils.readString(in);
	this.flightNumber = WritableUtils.readString(in);
    }

}
