 


  
public class Address
{
   private int id;
   private int houseNumber;
   private String street;
   private String city;
    
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public int getHouseNumber() {
	return houseNumber;
}

public void setHouseNumber(int houseNumber) {
	this.houseNumber = houseNumber;
}  
   public Address(int  id,
		    int houseNumber,		    	   
		    String  street ,		   
		    String  city)
		{
	   System.out.println("Address(params)"); 
        this.id = id;
        this.houseNumber = houseNumber;         
        this.street = street;
        this.city = city;
         
   } 
   
   public Address()
   {
	  System.out.println("Address Default()");
   }   
   public String getStreet()
   {
      return street;
   }

   public void setStreet(String street)
   {
      this.street = street;
   }

    
   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }   
    
   public String toString()
   {
      /*return "{id:" +id+
               ",houseNumber:'" + houseNumber + '\'' +               
              ", street:'" + street + '\'' +
              ", city:'" + city + '\'' +               
              '}';*/
	   return "{\"id\":" +id+
               ",\"houseNumber\":" + houseNumber +                 
              ", \"street\":"+ "\"" + street +  "\"" +
              ", \"city\":\"" + city + "\""+               
              "}";
   }
}
