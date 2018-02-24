package org.springframework.player_stats;

import java.util.HashMap;

public class PlayersStats {
   
   private static HashMap<String, PlayersStatsObject> playersStatsBySeasonWeek = new HashMap<String, PlayersStatsObject>();
   private static HashMap<String, HashMap<String, PlayerStatObject>> playerStatsByIdForSeasonWeek = new HashMap<String, HashMap<String, PlayerStatObject>>();
   private static HashMap<String, PlayersStatsObject> playersStatsBySeason = new HashMap<String, PlayersStatsObject>();
   private static HashMap<String, HashMap<String, PlayerStatObject>> playerStatsByIdForSeason = new HashMap<String, HashMap<String, PlayerStatObject>>();
   
   public static PlayerStatObject GetPlayerStatsForSeasonWeek(String playerId, String season, String week)
   {
      //If we don't find an entry for the player for the given season/week go get it and index it
      if (!playersStatsBySeasonWeek.containsKey(season + "-" + week))
      {
         //Grab Players stats from NFL
         PlayersStatsObject playersStatsForSeasonWeek = new PlayersStatsObject(season, week);
         
         //Index Players stats by season week
         playersStatsBySeasonWeek.put(season + "-" + week, playersStatsForSeasonWeek);

         HashMap<String, PlayerStatObject> tempPlayerIdToStatsMap = new HashMap<String, PlayerStatObject>();
         for(int i = 0; i < playersStatsForSeasonWeek.getPlayers().size(); i++)
         {
            tempPlayerIdToStatsMap.put(playersStatsForSeasonWeek.getPlayers().get(i).getId(),
                                       playersStatsForSeasonWeek.getPlayers().get(i));
         }

         playerStatsByIdForSeasonWeek.put(season + "-" + week, tempPlayerIdToStatsMap);
      }
      
      return playerStatsByIdForSeasonWeek.get(season + "-" + week).get(playerId);
   }

   public static PlayerStatObject GetPlayerStatsForSeason(String playerId, String season)
   {
      //If we don't find an entry for the player for the given season go get it and index it
      if (!playersStatsBySeason.containsKey(season))
      {
         //Grab Players stats from NFL
         PlayersStatsObject playersStatsForSeason = new PlayersStatsObject(season);
         HashMap<String, PlayerStatObject> tempPlayerIdToStatsMap = new HashMap<String, PlayerStatObject>();
         for(int i = 0; i < playersStatsForSeason.getPlayers().size(); i++)
         {
            tempPlayerIdToStatsMap.put(playersStatsForSeason.getPlayers().get(i).getId(),
                                       playersStatsForSeason.getPlayers().get(i));
         }

         playerStatsByIdForSeason.put(season, tempPlayerIdToStatsMap);
      }
      
      return playerStatsByIdForSeason.get(season).get(playerId);
      
   }

}
