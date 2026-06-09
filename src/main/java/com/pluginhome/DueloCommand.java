package com.pluginhome;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DueloCommand implements CommandExecutor 
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {

        if (!(sender instanceof Player)) 
        {
            sender.sendMessage("Solo los jugadores pueden usar este comando.");
            return true;
        }

        Player retador = (Player) sender;

        if (!retador.hasPermission("duelos.usar")) 
        {
            retador.sendMessage("§cNo tienes permiso para usar este comando.");
            return true;
        }

        if (args.length == 0) 
        {
            retador.sendMessage("§cUso correcto: /duelo <jugador>");
            return true;
        }

        if (args[0].equalsIgnoreCase(retador.getName())) 
        {
            retador.sendMessage("§cNo puedes retarte a ti mismo.");
            return true;
        }

        if (DueloManager.enDuelo(retador.getUniqueId())) 
        {
            retador.sendMessage("§cYa estás en un duelo.");
            return true;
        }

        Player retado = Bukkit.getPlayer(args[0]);

        if (retado == null) 
        {
            retador.sendMessage("§cEl jugador §e" + args[0] + " §cno está en línea.");
            return true;
        }

        if (DueloManager.enDuelo(retado.getUniqueId())) 
        {
            retador.sendMessage("§e" + retado.getName() + " §cya está en un duelo.");
            return true;
        }

        if (DueloManager.tieneSolicitud(retado.getUniqueId())) 
        {
            retador.sendMessage("§e" + retado.getName() + " §cya tiene una solicitud pendiente.");
            return true;
        }

        DueloManager.solicitudes.put(retado.getUniqueId(), retador.getUniqueId());

        retador.sendMessage("§aSolicitud de duelo enviada a §e" + retado.getName() + "§a.");
        retado.sendMessage("§e" + retador.getName() + " §ate ha retado a un duelo!");
        retado.sendMessage("§7Usa §a/aceptar §7o §c/rechazar§7.");

        Bukkit.getScheduler().runTaskLater(Duelos.getInstancia(), () -> 
        {
            if (DueloManager.tieneSolicitud(retado.getUniqueId())) 
            {
                DueloManager.solicitudes.remove(retado.getUniqueId());
                retador.sendMessage("§cTu solicitud de duelo a §e" + retado.getName() + " §cha expirado.");
                retado.sendMessage("§cLa solicitud de duelo de §e" + retador.getName() + " §cha expirado.");
            }
        }, 600L);

        return true;
    }
}   