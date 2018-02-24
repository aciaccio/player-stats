package org.springframework.player_stats;

import java.util.ArrayList;
import java.util.Map;
import java.lang.Float;
import java.lang.Number;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//typedef Map<String, String> StatMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStatObject {
   
   @JsonIgnoreProperties(ignoreUnknown = true)
   public class Stat {
      private String id;
      private String value;
      
      public Stat() {}
      
      public String getId() {return this.id;}
      public void setId(String id) {this.id = id;}

      public String getValue() {return this.value;}
      public void setValue(String value) {this.value = value;}
   }

   public PlayerStatObject () {}
   
   private String id;
   private String esbid;
   private String gsisPlayerId;
   private String name;
   private String position;
   private String teamAbbr;
   private Map<String,String> stats; //TODO: make a typedef for this
   private String seasonPts;
   private String seasonProjectedPts;
   private String weekPts;
   private String weekProjectedPts;
   
   public String getId() {return this.id;}
   public void setId(String id) {this.id = id;}

   public String getEsbid() {return this.esbid;}
   public void setEsbid(String esbid) {this.esbid = esbid;}
   
   public String getGsIsPlayerId() {return this.gsisPlayerId;}
   public void setGsisPlayerId(String gisPlayerId) {this.gsisPlayerId = gisPlayerId;}
   
   public String getName() {return this.name;}
   public void setName(String name) {this.name = name;}
   
   public String getPosition() {return this.position;}
   public void setPosition (String position) {this.position = position;}

   public String getTeamAbbr() {return this.teamAbbr;}
   public void setTeamAbbr (String teamAbbr) {this.teamAbbr = teamAbbr;}
   
   public Map<String,String> getStats() {return this.stats;}
   public void setStats(Map<String,String> stats) {this.stats = stats;}

   public String getSeasonPts() {return this.seasonPts;}
   public void setSeasonPts(String seasonPts) {this.seasonPts = seasonPts;}

   public String getSeasonProjectedPts() {return this.seasonProjectedPts;}
   public void setSeasonProjectedPts(String seasonProjectedPts) {this.seasonProjectedPts = seasonProjectedPts;}

   public String getWeekPts() {return this.weekPts;}
   public void setWeekPts(String weekPts) {this.weekPts = weekPts;}

   public String getWeekProjectedPts() {return this.weekProjectedPts;}
   public void setWeekProjectedPts(String weekProjectedPts) {this.weekProjectedPts = weekProjectedPts;}
   
   public float getStatByShortName(String statShortName)
   {
      String statValString = "0";
      if ((statValString = this.getStats().get(Integer.toString(GameStats.GetStatIdFromShortName(statShortName)))) != null)
         return Float.valueOf(statValString);
      return 0;
   }

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
