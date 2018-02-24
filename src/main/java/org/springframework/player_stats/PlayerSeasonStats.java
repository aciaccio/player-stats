package org.springframework.player_stats;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerSeasonStats {

   private String id;
   private String esbid;
   private String gsisPlayerId;
   private String name;
   private String position;
   private String teamAbbr;
   private Map<String, String> seasonStats;
   private String seasonStdPts;
   private String seasonPprPts;

   
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

   public Map<String, String> getSeasonStats() {return this.seasonStats;}
   public void setSeasonStats(Map<String, String> seasonStats) {this.seasonStats = seasonStats;}

   public String getSeasonStdPts() {return this.seasonStdPts;}
   public void setSeasonStdPts(String seasonStdPts) {this.seasonStdPts = seasonStdPts;}
   
   public String getSeasonPprPts() {return this.seasonPprPts;}
   public void setSeasonPprPts(String seasonPprPts) {this.seasonPprPts = seasonPprPts;}

}

