package github.io.sweetenpotato.entityindicator.player

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.util.Vector

fun Player.isLooking(victim: LivingEntity): Boolean {
    val eyeLocation: Location = this.eyeLocation
    val playerToEntity: Vector = victim.eyeLocation.toVector().subtract(eyeLocation.toVector())
    val dot: Double = playerToEntity.normalize().dot(eyeLocation.direction)

    return dot > 0.99
}

fun Player.getLookingEntity(): LivingEntity? {
    val nearEntityList: ArrayList<Entity>? = ArrayList(this.getNearbyEntities(10.0, 10.0, 10.0))
    if (nearEntityList != null) {
        for (entity in nearEntityList) {
            if (entity is LivingEntity) {
                if (this.isLooking(entity)) {
                    return entity
                }
            }
        }
    }
    return null
}