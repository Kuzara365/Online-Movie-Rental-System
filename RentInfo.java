package CustomerRental;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentInfo {
    protected String username;
    protected String rentalDate;
    protected String returnDate;
    protected int charges;
    protected int Overdues;
    
    public RentInfo(String username){
        this.username = username;
        this.rentalDate = "" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.returnDate = "" + LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
    }
}
