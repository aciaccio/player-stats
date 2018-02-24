package org.springframework.player_stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatObject {
   
   private String id;
   private String abbr;
   private String name;
   private String shortName;
   
   StatObject () {}

   public String getId()
   {
      return this.id;
   }
   
   public void setId(String id)
   {
      this.id = id;
   }
   
   public String getAbbr()
   {
      return this.abbr;
   }
   
   public void setAbbr(String abbr)
   {
      this.abbr = abbr;
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public String getShortName()
   {
       return this.shortName;
   }
   
   public void setShortName(String shortName)
   {
      this.shortName = shortName;
   }
   
   @Override
   public String toString() {
       return "Stat {" + "id=" + id +", abbr ='" + abbr + ", name=" + name + ", shortName=" + shortName +  '}';
   }
}
