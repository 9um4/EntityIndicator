package github.io.sweetenpotato.entityindicator

import github.io.sweetenpotato.entityindicator.player.getLookingEntity
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class CommandDispatcher(private val Plugin: EntityIndicator) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val commandName = command.name
        val player: Player? = sender as Player
        if ((commandName.equals("get", true)) and (player != null)) {
            if (args.isEmpty()) {
                val lookingEntity: Entity? = player?.getLookingEntity()
                if (lookingEntity != null) {
                    var entityName: String? = lookingEntity.name
                    if (entityName == "null") {
                        entityName = lookingEntity.type.toString()
                    }
                    sender.sendMessage(
                        "[LookingEntityInfo] $entityName (${(lookingEntity as LivingEntity).health}/${
                            (lookingEntity).getAttribute(
                                Attribute.GENERIC_MAX_HEALTH
                            )?.value
                        })"
                    )
                } else {
                    sender.sendMessage("[LookingEntityInfo] None.")
                }
            }
            return true
        }
        return false
    }
}
