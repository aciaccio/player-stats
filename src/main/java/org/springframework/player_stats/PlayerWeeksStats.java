package org.springframework.player_stats;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerWeeksStats {

   public class WeekStats {
      
      private String position;
      private String teamAbbr;
      private Map<String,String> stats;
      private String weekStdPts;
      private String weekPprPts;
      private String weekProjectedPts;
   
      public String getPosition() {return this.position;}
      public void setPosition (String position) {this.position = position;}

      public String getTeamAbbr() {return this.teamAbbr;}
      public void setTeamAbbr (String teamAbbr) {this.teamAbbr = teamAbbr;}

      public Map<String,String> getStats() {return this.stats;}
      public void setStats(Map<String,String> stats) {this.stats = stats;}

      public String getWeekStdPts() {return this.weekStdPts;}
      public void setWeekStdPts(String weekStdPts) {this.weekStdPts = weekStdPts;}

      public String getWeekPprPts() {return this.weekPprPts;}
      public void setWeekPprPts(String weekPprPts) {this.weekPprPts = weekPprPts;}

      public String getWeekProjectedPts() {return this.weekProjectedPts;}
      public void setWeekProjectedPts(String weekProjectedPts) {this.weekProjectedPts = weekProjectedPts;}

      public String toString()
      {
         StringBuilder builder = new StringBuilder();
      
         builder.append("id=" + id + " esbid=" + esbid + 
                     " gsisPlayerId= " + gsisPlayerId + 
                     " name=" + name + " position=" + position + 
                     " teamAbbr=" + teamAbbr + " stats={" + stats.toString()+ "} \n");
      
         return builder.toString();
      }
   }

   private String id;
   private String esbid;
   private String gsisPlayerId;
   private String name;
   private Map<String, WeekStats> statsByWeek;

   public String getId() {return this.id;}
   public void setId(String id) {this.id = id;}

   public String getEsbid() {return this.esbid;}
   public void setEsbid(String esbid) {this.esbid = esbid;}

   public String getGsIsPlayerId() {return this.gsisPlayerId;}
   public void setGsisPlayerId(String gisPlayerId) {this.gsisPlayerId = gisPlayerId;}

   public String getName() {return this.name;}
   public void setName(String name) {this.name = name;}

   public Map<String, WeekStats> getWeekStats() {return this.statsByWeek;}
   public void setWeekStats(Map<String, WeekStats> statsByWeek) {this.statsByWeek = statsByWeek;}

}
