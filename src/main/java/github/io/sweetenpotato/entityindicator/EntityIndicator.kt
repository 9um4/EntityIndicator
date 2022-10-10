package github.io.sweetenpotato.entityindicator

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.Vector

class EntityIndicator : JavaPlugin() {
    val pluginVersion = "1.0"
    private val commandDispatcher = CommandDispatcher(this)
    private val playerListener = PlayerListener(this)

    var playerList: ArrayList<Player> = ArrayList()

    override fun onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(playerListener, this)
        getCommand("get")?.setExecutor(commandDispatcher)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin Disabled!")
    }
}