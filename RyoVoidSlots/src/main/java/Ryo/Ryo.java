package Ryo;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Ryo extends JavaPlugin {

    @Override
    public void onEnable() {
        new RyoPlaceholderAPI().register();
        this.getLogger().info("RyoVoidSlots已成功注册!");
        this.getLogger().info("作者：Ryo");
        this.getLogger().info("QQ：125512156");
    }

    @Override
    public void onDisable() {
        this.getLogger().warning("插件已卸载");
    }

    // PAPI扩展的实现
    public static class RyoPlaceholderAPI extends PlaceholderExpansion {

        @Override
        public String getIdentifier() {
            return "ryo";  // 标识符
        }

        @Override
        public String getAuthor() {
            return "Ryo";  // 作者名称
        }

        @Override
        public String getVersion() {
            return "1.0";  // 插件版本
        }

        @Override
        public String onPlaceholderRequest(Player player, String identifier) {
            // 如果是请求剩余槽位的占位符
            if ("void_slots".equals(identifier)) {
                return String.valueOf(getRemainingSlots(player)); // 确保返回非 null 值
            }
            return "";  // 对于其他未处理的占位符返回空字符串
        }

        private int getRemainingSlots(Player player) {
            Inventory inventory = player.getInventory();
            int remainingSlots = 0;

            // 只遍历物品栏的 36 个槽位（不排除任何槽位）
            for (int i = 0; i < 36; i++) {
                ItemStack item = inventory.getItem(i);
                if (item == null || item.getType().isAir()) { // 检查空槽位
                    remainingSlots++;
                }
            }

            return remainingSlots;
        }
    }
}
