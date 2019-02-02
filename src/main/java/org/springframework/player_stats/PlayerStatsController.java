package org.springframework.player_stats;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.lang.Number;

@RestController
public class PlayerStatsController {

   @RequestMapping("/playerweeksstats")
   public PlayerWeeksStats playerSeasonWeeksStats(@RequestParam(value="season", defaultValue="2018") String season,
                                                  @RequestParam(value="weeks", defaultValue="1-17") String weeks,
                                                  @RequestParam(value="playerId", defaultValue="0") String playerId) 
   {
      //Parse 'weeks' for range
      String _weeks[] = weeks.split("-");

      PlayerWeeksStats pws = new PlayerWeeksStats();
      Map<String, PlayerWeeksStats.WeekStats> ws_map = new HashMap <String, PlayerWeeksStats.WeekStats>();

      PlayerStatObject pso = null;
      
      //If a single week was passed create stats for single week
      if (_weeks.length == 1)
      {
         pso = PlayersStats.GetPlayerStatsForSeasonWeek(playerId, season, _weeks[0]);
         ws_map.put(_weeks[0], CreateWeekStatsFromPlayerStatObject(pso));
      }
      
      //If a week range was passed create stats for each week in the range
      else if (_weeks.length > 1)
      {
      
         for (int i = Integer.valueOf(_weeks[0]); i <= Integer.valueOf(_weeks[1]); i++)
         {
            String week = Integer.toString(i);
            pso = PlayersStats.GetPlayerStatsForSeasonWeek(playerId, season, week);
            if (pso != null)
               ws_map.put(week, CreateWeekStatsFromPlayerStatObject(pso));
         }
      }
      
      if (pso != null)
      {
         pws.setId(pso.getId());
         pws.setGsisPlayerId(pso.getGsIsPlayerId());
         pws.setEsbid(pso.getEsbid());
         pws.setName(pso.getName());
         pws.setWeekStats(ws_map);
      }
      
      return pws;
   }

   @RequestMapping("/playerseasonstats")
   public PlayerSeasonStats playerSeasonStats(@RequestParam(value="season", defaultValue="2018") String season,
                                              @RequestParam(value="playerId", defaultValue="0") String playerId) 
   {
      PlayerStatObject pso = PlayersStats.GetPlayerStatsForSeason(playerId, season);
      PlayerSeasonStats pss = CreateSeasonStatsFromPlayerStatObject (pso);
      pss.setId(pso.getId());
      pss.setGsisPlayerId(pso.getGsIsPlayerId());
      pss.setEsbid(pso.getEsbid());
      pss.setName(pso.getName());
      
      return pss;
   }
   
   public PlayerWeeksStats.WeekStats CreateWeekStatsFromPlayerStatObject (PlayerStatObject pso)
   {
      PlayerWeeksStats pws = new PlayerWeeksStats();

      //PlayerStatObject pso = PlayersStats.GetPlayerStatsForSeasonWeek(playerId, "2017", week);         
      PlayerWeeksStats.WeekStats ws = pws.new WeekStats();
      ws.setStats(TranslateStatIdsToShortNames(pso.getStats()));
      ws.setPosition(pso.getPosition());
      ws.setTeamAbbr(pso.getTeamAbbr());
      ws.setWeekProjectedPts(pso.getWeekProjectedPts());
      ws.setWeekStdPts(new Float(CalcStdPts(pso)).toString()); //TODO:Calculate std pts
      ws.setWeekPprPts(new Float(CalcPprPts(pso)).toString()); //TODO:Calculate ppr pts
      
      return ws;
   }
   
   public PlayerSeasonStats CreateSeasonStatsFromPlayerStatObject (PlayerStatObject pso)
   {
      PlayerSeasonStats pss = new PlayerSeasonStats();

      pss.setSeasonStats(TranslateStatIdsToShortNames(pso.getStats()));
      pss.setPosition(pso.getPosition());
      pss.setTeamAbbr(pso.getTeamAbbr());
      pss.setSeasonStdPts(new Float(CalcStdPts(pso)).toString()); //TODO:Calculate std pts
      pss.setSeasonPprPts(new Float(CalcPprPts(pso)).toString()); //TODO:Calculate ppr pts
      
      return pss;
   }

   
   public Map<String,String> TranslateStatIdsToShortNames (Map<String, String> stats)
   {
      Set<String> keyset = stats.keySet();
     
      Map<String, String> renamed_map = new HashMap <String, String>();
      String statKey;
      String statValue;

      for (Iterator<String> it = keyset.iterator(); it.hasNext(); )
      {
         statKey = it.next();
         statValue = stats.get(statKey);
         statKey = GameStats.GetStatShortName(statKey);
         renamed_map.put(statKey, statValue);
      }
      
      return renamed_map;
   }

   public float CalcStdPts(PlayerStatObject pso)
   {
      float pts = 0;
      
      pts += pso.getStatByShortName("Pass Yds") * .04;
      pts += pso.getStatByShortName("Rush Yds") * .10;
      pts += pso.getStatByShortName("Rec Yds") * .10;
      pts += pso.getStatByShortName("Pass TD") * 6.0;
      pts += pso.getStatByShortName("Rush TD") * 6.0;
      pts += pso.getStatByShortName("Rec TD") * 6.0;
      pts += pso.getStatByShortName("Int") * -2.0;
      pts += pso.getStatByShortName("Fum Lost") * -2.0;

      return pts;
   }
   
   public float CalcPprPts (PlayerStatObject pso)
   {
      float pts = 0;

      pts += pso.getStatByShortName("Pass Yds") * .04;
      pts += pso.getStatByShortName("Rush Yds") * .10;
      pts += pso.getStatByShortName("Rec Yds") * .10;
      pts += pso.getStatByShortName("Receptions");
      pts += pso.getStatByShortName("Pass TD") * 6.0;
      pts += pso.getStatByShortName("Rush TD") * 6.0;
      pts += pso.getStatByShortName("Rec TD") * 6.0;
      pts += pso.getStatByShortName("Int") * -2.0;
      pts += pso.getStatByShortName("Fum Lost") * -2.0;

      return pts;
      
   }

}
