package com.BopTart.lifeSnatch.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class Heart {

    private static final NamespacedKey HEART_KEY = new NamespacedKey("lifesnatch", "heart");

    public static ItemStack createHeartItem() {
        ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = heart.getItemMeta();

        if (meta != null) {
            // Nome visibile
            meta.setDisplayName("§cHeart robbed");

            // Lore (descrizione)
            meta.setLore(Arrays.asList("§7a Heart as been robbed"));

            // Glow tramite incanto nascosto
            meta.addEnchant(Enchantment.UNBREAKING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            // Tag univoco NBT
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(HEART_KEY, PersistentDataType.STRING, "true");

            heart.setItemMeta(meta);
        }

        return heart;
    }

    public static boolean isHeartItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        PersistentDataContainer data = meta.getPersistentDataContainer();
        return data.has(HEART_KEY, PersistentDataType.STRING);
    }
            }
