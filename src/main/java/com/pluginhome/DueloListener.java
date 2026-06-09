package com.pluginhome;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.UUID;

public class DueloListener implements Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player perdedor = e.getEntity();
        
        if(!DueloManager.enDuelo(perdedor.getUniqueId())) return;

        UUID oponenteUUID = DueloManager.getOponente(perdedor.getUniqueId());
        Player ganador = Bukkit.getPlayer(oponenteUUID);

        DueloManager.terminarDuelo(perdedor, ganador != null ? ganador : perdedor);

        if(ganador != null)
        {
            ganador.sendMessage("§a¡Ganaste el duelo contra §e" + perdedor.getName() + "§a!");
            perdedor.sendMessage("§cPerdiste el duelo contra §e" + ganador.getName() + "§c.");
            for(Player p : Bukkit.getOnlinePlayers())
            {
                p.sendMessage("§6⚔ §e" + ganador.getName() + " §6ha ganado el duelo contra §e" + perdedor.getName() + "§6!");
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player perdedor = e.getPlayer();

        if(!DueloManager.enDuelo(perdedor.getUniqueId())) return;

        UUID oponenteUUID = DueloManager.getOponente(perdedor.getUniqueId());
        Player ganador = Bukkit.getPlayer(oponenteUUID);
        
        DueloManager.terminarDuelo(perdedor, ganador != null ? ganador : perdedor);

        if(ganador != null)
        {
            ganador.sendMessage("§a¡Ganaste el duelo! §e" + perdedor.getName() + " §ase desconectó.");
            for(Player p : Bukkit.getOnlinePlayers())
            {
                p.sendMessage("§6⚔ §e" + ganador.getName() + " §6ha ganado el duelo. §e" + perdedor.getName() + " §6se desconectó.");
            }
        }
    }
}