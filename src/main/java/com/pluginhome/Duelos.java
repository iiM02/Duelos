package com.pluginhome;

import org.bukkit.plugin.java.JavaPlugin;

public class Duelos extends JavaPlugin
{
    private static Duelos instancia;

    @Override
    public void onEnable()
    {
        instancia = this;

        getCommand("duelo").setExecutor(new DueloCommand());
        getCommand("aceptar").setExecutor(new AceptarCommand());
        getCommand("rechazar").setExecutor(new RechazarCommand());
        
        getServer().getPluginManager().registerEvents(new DueloListener(), this);

        getLogger().info("Duelos activado.");
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Duelos desactivado.");
    }

    public static Duelos getInstancia()
    {
        return instancia;
    }
}
