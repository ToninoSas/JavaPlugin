package it.toninosas.main.Comandi;

import it.toninosas.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PvPKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if(args.length == 0){
                //define materials
                Material Sword = Material.NETHERITE_SWORD;
                Material Helmet = Material.NETHERITE_HELMET;
                Material Chestplate = Material.NETHERITE_CHESTPLATE;
                Material Leggings = Material.NETHERITE_LEGGINGS;
                Material Boots = Material.NETHERITE_BOOTS;
                Material Apples = Material.GOLDEN_APPLE;

                //creation materials
                ItemStack sword = new ItemStack(Sword);
                ItemStack helmet = new ItemStack(Helmet);
                ItemStack chestplate = new ItemStack(Chestplate);
                ItemStack leggings = new ItemStack(Leggings);
                ItemStack boots = new ItemStack(Boots);
                ItemStack apples = new ItemStack(Apples, 16);

                //creation materials meta
                ItemMeta swordMeta = sword.getItemMeta();
                ItemMeta helmetMeta = helmet.getItemMeta();
                ItemMeta chestplateMeta = chestplate.getItemMeta();
                ItemMeta leggingsMeta = leggings.getItemMeta();
                ItemMeta bootsMeta = boots.getItemMeta();
                ItemMeta applesMeta = apples.getItemMeta();

                //creating lore (description of items)
                ArrayList<String> toolsLore = new ArrayList<String>();
                toolsLore.add("Strumenti per pvp");

                ArrayList<String> foodLore = new ArrayList<String>();
                foodLore.add("Il frutto della vittoria");

                //modifying material

                swordMeta.addEnchant(Enchantment.KNOCKBACK, 2, false);
                swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
                swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, false);
                swordMeta.addEnchant(Enchantment.DURABILITY, 3, false);
                swordMeta.setDisplayName("A SPED D SANDOKAN");
                swordMeta.setLore(toolsLore);

                helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
                helmetMeta.addEnchant(Enchantment.DURABILITY, 3, false);
                helmetMeta.addEnchant(Enchantment.THORNS, 3, false);
                helmetMeta.setLore(toolsLore);

                chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
                chestplateMeta.addEnchant(Enchantment.DURABILITY, 3, false);
                chestplateMeta.addEnchant(Enchantment.THORNS, 3, false);
                chestplateMeta.setLore(toolsLore);

                leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
                leggingsMeta.addEnchant(Enchantment.DURABILITY, 3, false);
                leggingsMeta.addEnchant(Enchantment.THORNS, 3, false);
                leggingsMeta.setLore(toolsLore);

                bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
                bootsMeta.addEnchant(Enchantment.DURABILITY, 3, false);
                bootsMeta.addEnchant(Enchantment.THORNS, 3, false);
                bootsMeta.setLore(toolsLore);

                applesMeta.setLore(foodLore);

                sword.setItemMeta(swordMeta);
                helmet.setItemMeta(helmetMeta);
                chestplate.setItemMeta(chestplateMeta);
                leggings.setItemMeta(leggingsMeta);
                boots.setItemMeta(bootsMeta);
                apples.setItemMeta(applesMeta);

                //ItemStack[] armor = new ItemStack[]{helmet, chestplate, leggings, boots};
                //p.getInventory().setArmorContents(armor);

                p.getInventory().setHelmet(helmet);
                p.getInventory().setChestplate(chestplate);
                p.getInventory().setLeggings(leggings);
                p.getInventory().setBoots(boots);

                p.getInventory().addItem(sword);
                p.getInventory().addItem(apples);

                return true;
            }
        }else{
            System.out.println(Main.console);
        }
        return false;
    }
}
