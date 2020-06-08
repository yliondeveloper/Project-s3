package com.serverbuild.events;

import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class other
  implements Listener
{
  public EnderCrystal plugin;
  
  @EventHandler
  public void onEnity(EntityExplodeEvent event)
  {
    if ((event.getEntity() instanceof EnderCrystal)) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onEnityBoom(EntityDamageByEntityEvent event)
  {
    if ((event.getEntity() instanceof EnderCrystal))
    {
      if (event.getEntity().getWorld().getEnvironment() == World.Environment.THE_END)
      {
        event.setCancelled(false);
      }
      else
      {
        if ((event.getDamager() instanceof Arrow))
        {
          Arrow arrow = (Arrow)event.getDamager();
          if ((arrow.getShooter() instanceof Player))
          {
            Player player = (Player)arrow.getShooter();
            if (player.hasPermission("ec.destroy")) {
              event.setCancelled(false);
            } else {
              event.setCancelled(true);
            }
          }
        }
        if ((event.getDamager() instanceof Player)) {
          if (((Player)event.getDamager()).hasPermission("ec.destroy")) {
            event.setCancelled(false);
          } else {
            event.setCancelled(true);
          }
        }
      }
    }
  }
}
