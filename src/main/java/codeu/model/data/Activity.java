package codeu.model.data;

import java.time.Instant;
import java.util.UUID;

//class to represent site activity sent to the activity feed
public class Activity {

  //output is the string ouput to the screen
  //creation is the time of creation
  private final String output;
  private final Instant creation;

  public Activity(String output, Instant creation){
    this.output = output;
    this.creation = creation;
  }

  public String getOutput(){
    return output;
  }

  public Instant getCreationTime(){
    return creation;
  }

}
