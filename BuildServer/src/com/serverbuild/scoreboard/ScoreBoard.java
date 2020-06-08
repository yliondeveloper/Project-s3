package com.serverbuild.scoreboard;

import com.serverbuild.events.PingServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreBoard
{
  public static void update(Player p)
  {
    uScore scoreboard = new uScore("§e§lBUILD - SERVER");
    scoreboard.addLine("§3   ", Integer.valueOf(8));
    scoreboard.addLine("§fOnline: §7" + Bukkit.getOnlinePlayers().size(), Integer.valueOf(7));
    scoreboard.addLine("  ", Integer.valueOf(6));
    scoreboard.addLine("§fKitPvP: §a " + KitPvP(), Integer.valueOf(5));
    scoreboard.addLine("§fLobby: §a" + Lobby(), Integer.valueOf(4));
    scoreboard.addLine("§fFullPvP §cOffline", Integer.valueOf(3));
    scoreboard.addLine("§6   ", Integer.valueOf(2));
    scoreboard.addLine("§fGrupo: §2§lBUILDER", Integer.valueOf(1));
    scoreboard.setScoreboard();
    p.setScoreboard(scoreboard.getScoreboard());
  }
  
  public static String KitPvP()
  {
    PingServer a = new PingServer("127.0.0.1", 41456);
    if (a.isOnline()) {
      return "§aOnline";
    }
    return "§Offline";
  }
  
  public static String Lobby()
  {
    PingServer a = new PingServer("127.0.0.1", 36745);
    if (a.isOnline()) {
      return "§aOnline";
    }
    return "§Offline";
  }
}
