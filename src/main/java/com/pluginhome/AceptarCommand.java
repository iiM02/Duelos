package com.pluginhome;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AceptarCommand implements CommandExecutor
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

        if(retador == null)
        {
            retado.sendMessage("§cEl jugador que te retó ya no está en línea");
            return true;
        }

        DueloManager.iniciarDuelo(retador, retado);

        retador.sendMessage("§e" + retado.getName() + " §aha aceptado tu duelo. ¡Que comience!");

        for (Player p : Bukkit.getOnlinePlayers()) 
        {
            p.sendMessage("§6⚔ §e" + retador.getName() + " §6vs §e" + retado.getName() + " §6¡Duelo comenzado!");
        }

        return true;
    }
}
