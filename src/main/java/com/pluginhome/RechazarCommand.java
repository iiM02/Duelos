package com.pluginhome;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.UUID;

public class RechazarCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("Solo los jugadores pueden usar este comando.");
            return true;
        }

        Player retado = (Player) sender;

        if(!DueloManager.tieneSolicitud(retado.getUniqueId()))
        {
            retado.sendMessage("§cNo tienes ninguna solicitud de duelo pendiente.");
            return true;
        }

        UUID retadorUUID = DueloManager.solicitudes.get(retado.getUniqueId());
        Player retador = Bukkit.getPlayer(retadorUUID);

        DueloManager.solicitudes.remove(retado.getUniqueId());

        retado.sendMessage("§cHas rechazado el duelo.");
        if(retador != null)
        {
            retador.sendMessage("§e" + retado.getName() + " §cha rechazado tu solicitud de duelo.");
        }

        return true;
    }   
}
