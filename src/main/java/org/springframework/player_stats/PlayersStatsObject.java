package org.springframework.player_stats;

import java.util.ArrayList;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayersStatsObject {

   private String statType;
   private String season;
   private String week;
   private ArrayList<PlayerStatObject> players;
     
   public PlayersStatsObject() {}
   
   public String getStatType() {return this.statType;}
   public void setStatType(String statType) {this.statType = statType;}
   
   public String getSeason() {return this.season;}
   public void setSeason(String season) {this.season = season;}

   public String getWeek() {return this.week;}
   public void setWeek(String week) {this.week = week;}
   
   public ArrayList<PlayerStatObject> getPlayers()
   {
      return this.players;
   }
   
   public void setStats(ArrayList<PlayerStatObject> players)
   {
      this.players = players;
   }
   
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("{" + "statType=" + this.statType + " week=" + this.week + " season=" + this.season);
      for(PlayerStatObject player : this.getPlayers())
      {
         builder.append(player).toString();
      }
      
      return builder.toString();

   }
   
   public PlayersStatsObject(String season, String week)
   {
      String transactionUrl = "http://api.fantasy.nfl.com/v1/players/stats";

      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionUrl)
          // Add query parameter
          .queryParam("statType", "weekStats")
          .queryParam("season", season)
          .queryParam("week", week)
          .queryParam("format", "json");
      
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<PlayersStatsObject> playersStatsResponse =
            restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<PlayersStatsObject>() {});
      PlayersStatsObject pso = playersStatsResponse.getBody();
      this.statType = pso.statType;
      this.season = pso.season;
      this.week = pso.week;
      this.players = pso.players;
   }
   
   public PlayersStatsObject(String season)
   {
      String transactionUrl = "http://api.fantasy.nfl.com/v1/players/stats";

      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionUrl)
          // Add query parameter
          .queryParam("statType", "seasonStats")
          .queryParam("season", season)
          .queryParam("format", "json");
      
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<PlayersStatsObject> playersStatsResponse =
            restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<PlayersStatsObject>() {});
      PlayersStatsObject pso = playersStatsResponse.getBody();
      this.statType = pso.statType;
      this.season = pso.season;
      this.week = pso.week;
      this.players = pso.players;
   }

}
