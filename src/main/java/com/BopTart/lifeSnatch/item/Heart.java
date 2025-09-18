package com.BopTart.lifeSnatch.item;

import com.BopTart.lifeSnatch.LifeSnatch;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class Heart {

    private static final NamespacedKey HEART_KEY = new NamespacedKey("lifesnatch", "heart");
    private static final LifeSnatch plugin = LifeSnatch.getInstance();

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

        // Crea l'oggetto cuore leggendo dal config.yml
        public static ItemStack getHeartItem() {
            FileConfiguration config = plugin.getConfig();
            String materialName = config.getString("heartItem.material", "NETHER_STAR");
            Material material = Material.matchMaterial(materialName);

            ItemStack item = new ItemStack(material != null ? material : Material.NETHER_STAR);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(config.getString("heartItem.name", "§cHeart"));
                item.setItemMeta(meta);
            }
            return item;
        }

        // Ricetta custom (puoi completarla se vuoi dal config)
        public static Recipe heartRecipe() {
            // esempio semplice di ricetta
            NamespacedKey key = new NamespacedKey(plugin, "heart_item");
            ShapedRecipe recipe = new ShapedRecipe(key, getHeartItem());

            recipe.shape("DDD", "DND", "DDD");
            recipe.setIngredient('D', Material.DIAMOND);
            recipe.setIngredient('N', Material.NETHER_STAR);

            return recipe;
        }
}
