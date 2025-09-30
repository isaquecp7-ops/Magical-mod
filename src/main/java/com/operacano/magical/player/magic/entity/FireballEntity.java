package com.operacano.magical.player.magic.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import com.operacano.magical.player.magic.magicphysics.MagicDamageCalculator;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class FireballEntity extends Entity {
    private int life;
    private static final int MAX_LIFE = 40; // 2 seconds at 20 tps
    private static final float SPEED = 0.5f;
    private static final float BASE_DAMAGE = 4.0f;
    private LivingEntity owner;

    public FireballEntity(EntityType<? extends FireballEntity> type, Level level) {
        super(type, level);
        this.life = 0;
    }

    public FireballEntity(Level level, LivingEntity shooter, Vec3 direction, EntityType<? extends FireballEntity> type) {
        this(type, level);
        this.owner = shooter;
        setPos(shooter.getX(), shooter.getEyeY(), shooter.getZ());
        setDeltaMovement(direction.normalize().scale(SPEED));
    }

    @Override
    public void tick() {
        super.tick();
        life++;
        if (life > MAX_LIFE) {
            discard();
            return;
        }
        move(MoverType.SELF, getDeltaMovement());
        if (level().isClientSide) {
            for (int i = 0; i < 3; i++) {
                level().addParticle(ParticleTypes.FLAME, getX(), getY(), getZ(), 0, 0, 0);
            }
        }
        // Check for collision with entities
        for (LivingEntity entity : level().getEntitiesOfClass(LivingEntity.class, new AABB(position().add(-0.5, -0.5, -0.5), position().add(0.5, 0.5, 0.5)))) {
            if (entity != this.owner) {
                explode(entity);
                return;
            }
        }
        // TODO: Add block collision if needed
    }

    private void explode(Entity hit) {
        if (!level().isClientSide) {
            float damage = BASE_DAMAGE;
            if (owner instanceof Player player) {
                damage = MagicDamageCalculator.calculate(player, BASE_DAMAGE);
            }
            hit.hurt(level().damageSources().magic(), damage);
            discard();
        }
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        // No custom data
    }
    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {}
    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {}
}
