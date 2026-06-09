package com.pluginhome;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class DueloManager 
{
    public static HashMap<UUID, UUID> solicitudes = new HashMap<>();
    public static HashMap<UUID, UUID> duelosActivos = new HashMap<>();

    public static boolean enDuelo(UUID uuid)
    {
        return duelosActivos.containsKey(uuid);
    }

    public static UUID getOponente(UUID uuid)
    {
        return duelosActivos.get(uuid);
    }

    public static boolean tieneSolicitud(UUID uuid) 
    {
        return solicitudes.containsKey(uuid);
    }
    
    public static void iniciarDuelo(Player retador, Player retado)
    {
        duelosActivos.put(retador.getUniqueId(), retado.getUniqueId());
        duelosActivos.put(retado.getUniqueId(), retador.getUniqueId());
        solicitudes.remove(retado.getUniqueId());
    }

    public static void terminarDuelo(Player ganador, Player perdedor)
    {
        duelosActivos.remove(ganador.getUniqueId());
        duelosActivos.remove(perdedor.getUniqueId());
    }

}
