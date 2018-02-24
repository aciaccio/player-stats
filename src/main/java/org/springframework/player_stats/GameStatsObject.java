package org.springframework.player_stats;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStatsObject {

   private ArrayList<StatObject> stats;
   
   public GameStatsObject() {}
   
   public ArrayList<StatObject> getStats()
   {
      return this.stats;
   }
   
   public void setStats(ArrayList<StatObject> stats)
   {
      this.stats = stats;
   }

}
