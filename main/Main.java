package it.toninosas.main;
import it.toninosas.main.Comandi.*;
import it.toninosas.main.Death.DeathEventSavePosition;
import it.toninosas.main.Death.tpDeathCommand;
import it.toninosas.main.Events.ContactToWaterEvent;
import it.toninosas.main.Home.DelHomeCommand;
import it.toninosas.main.Home.HomeCommand;
import it.toninosas.main.Home.SetHomeCommand;
import it.toninosas.main.KeyLogger.*;
import it.toninosas.main.LoginAndRegisterManager.*;

import it.toninosas.main.Permission.GivePermissionCommand;
import it.toninosas.main.Permission.RemovePermissionCommand;
import it.toninosas.main.PrivateWarp.DeletePrivateWarp;
import it.toninosas.main.PrivateWarp.NewPrivateWarp;
import it.toninosas.main.PrivateWarp.PrivateWarpCommand;
import it.toninosas.main.Protezione.ProtezioneCommand;
import it.toninosas.main.Protezione.ProtezioneEvents;
import it.toninosas.main.Scoreboard.GestoreBoard.ScoreBoardManager;
import it.toninosas.main.Scoreboard.GestoreBoard.UpdateScoreBoardCommand;
import it.toninosas.main.Scoreboard.GestoreDeath.DeathManager;
import it.toninosas.main.Scoreboard.GestoreKill.KillManager;
import it.toninosas.main.Spawn.SetSpawnCommand;
import it.toninosas.main.Spawn.SpawnCommand;
import it.toninosas.main.Tp.TpaCommand;
import it.toninosas.main.Tp.TpaDeny;
import it.toninosas.main.Tp.Tpaccept;
import it.toninosas.main.Zone.DeleteZonaCommand;
import it.toninosas.main.Zone.NewZonaCommand;
import it.toninosas.main.Zone.ZonaCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static Main plugin;
    public static String prefix = "[PROVA PLUGIN]";
    public static String permessi = "Non hai i permessi per eseguire questo comando!";
    public static String console = "Non puoi eseguire questo comando da console!";
    public static String auth = "§b>> §cDevi prima autenticarti.";

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info(prefix + "Caricato");
        GestioneConfigs.setup();
        GestioneConfigs.save();

        commands();
        events();
    }

    @Override
    public void onDisable() {
        getLogger().info(prefix + "Spento");

    }

    public void commands(){
        Objects.requireNonNull(getCommand("msg")).setExecutor(new PrivateMessageCommand());
        Objects.requireNonNull(getCommand("give")).setExecutor(new GiveCommand());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand());
        Objects.requireNonNull(getCommand("pvpkit")).setExecutor(new PvPKitCommand());
        Objects.requireNonNull(getCommand("waterkill")).setExecutor(new WaterKillCommand());
        Objects.requireNonNull(getCommand("register")).setExecutor(new RegisterCommand());
        Objects.requireNonNull(getCommand("login")).setExecutor(new LoginCommand());
        Objects.requireNonNull(getCommand("reloadconfig")).setExecutor(new ReloadConfigCommand());
        Objects.requireNonNull(getCommand("tpdeath")).setExecutor(new tpDeathCommand());
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("newzona")).setExecutor(new NewZonaCommand());
        Objects.requireNonNull(getCommand("zona")).setExecutor(new ZonaCommand());
        Objects.requireNonNull(getCommand("deletezona")).setExecutor(new DeleteZonaCommand());
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new SetHomeCommand());
        Objects.requireNonNull(getCommand("delhome")).setExecutor(new DelHomeCommand());
        Objects.requireNonNull(getCommand("changepassword")).setExecutor(new ChangePasswordCommand());
        Objects.requireNonNull(getCommand("unregister")).setExecutor(new UnregisterCommand());
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new TpaCommand());
        Objects.requireNonNull(getCommand("tpaccept")).setExecutor(new Tpaccept());
        Objects.requireNonNull(getCommand("tpadeny")).setExecutor(new TpaDeny());
        Objects.requireNonNull(getCommand("givep")).setExecutor(new GivePermissionCommand());
        Objects.requireNonNull(getCommand("removep")).setExecutor(new RemovePermissionCommand());
        Objects.requireNonNull(getCommand("updatescoreboard")).setExecutor(new UpdateScoreBoardCommand());
        Objects.requireNonNull(getCommand("coprifuoco")).setExecutor(new CoprifuocoSetCommand());
        Objects.requireNonNull(getCommand("keyregister")).setExecutor(new KeyRegisterCommand());
        Objects.requireNonNull(getCommand("keyinsert")).setExecutor(new KeyInsertCommand());
        Objects.requireNonNull(getCommand("key")).setExecutor(new KeyCommand());
        Objects.requireNonNull(getCommand("newpwarp")).setExecutor(new NewPrivateWarp());
        Objects.requireNonNull(getCommand("pwarp")).setExecutor(new PrivateWarpCommand());
        Objects.requireNonNull(getCommand("deletepwarp")).setExecutor(new DeletePrivateWarp());

        Objects.requireNonNull(getCommand("time")).setExecutor(new TimeSet());

        Objects.requireNonNull(getCommand("protezione")).setExecutor(new ProtezioneCommand());
    }
    public void events(){
        getServer().getPluginManager().registerEvents(new DeathEventSavePosition(), this);
        getServer().getPluginManager().registerEvents(new ContactToWaterEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinQuitEvent2(), this);

        getServer().getPluginManager().registerEvents(new DeathManager(), this);
        getServer().getPluginManager().registerEvents(new KillManager(), this);
        getServer().getPluginManager().registerEvents(new ScoreBoardManager(), this);

        getServer().getPluginManager().registerEvents(new CoprifuocoEvent(), this);

        getServer().getPluginManager().registerEvents(new ProtezioneEvents(), this);
    }
}
