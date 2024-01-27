package org.cofezuwo.nsuluofuo.inventory;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.item.Item;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.worlds.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

	private boolean itemScreen;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private int invListCenterY = 252;
	private int invListSpacing = 40;
	private int invImageX = 531, invImageY = 208;
	private int invImageWidth = 64, invImageHeight = 64;
	private int selectedItem = 0;
	private Color color;
	private Color color2;
	private Item sword1;
	private Item sword2;
	private Item sword3;
	private Item trivel;

	public Inventory() {
		this.trivel = Item.trivel;
		sword1 = Item.dSword;
		sword2 = Item.mSword;
		sword3 = Item.tSword;
		inventoryItems = new ArrayList<Item>();
		inventoryItems.add(trivel);
		inventoryItems.add(sword1);
		inventoryItems.add(sword2);
		inventoryItems.add(sword3);

		color = new Color(255, 255, 255, 200);
		color2 = new Color(192, 192, 192, 200);
	}

	public void update() {

		if (!active) {
			return;
		}


		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_LEFT)) {
			active = !active;
			World.getInstance().getEntityManager().getPlayer().getInfo().setActive(true);
		}

		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_PAGE_UP)) {
			selectedItem--;
		}
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_PAGE_DOWN)) {
			selectedItem++;

		}

		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_ENTER)) {
			if (inventoryItems.get(selectedItem).getCount() - 1 == 0) {
				return;
			}
			if (selectedItem == (inventoryItems.size() - 1)) {
				return;
			} else {
				inventoryItems.get(selectedItem).setCount(inventoryItems.get(selectedItem).getCount() - 1);
				World.getInstance().getEntityManager().getPlayer()
						.setMoney(World.getInstance().getEntityManager().getPlayer().getMoney()
								+ inventoryItems.get(selectedItem).getPrize());
			}

		}
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_H)) {
			if (inventoryItems.get(selectedItem).getCount() - 1 == 0) {
				return;
			}
			if (inventoryItems.get(selectedItem).getCount() - 1 == 0) {
				return;
			}
			if (inventoryItems.get(selectedItem).getStrength() <= 0) {

				return;
			} else if (selectedItem == (inventoryItems.size() - 1)) {
				return;
			} else {
				inventoryItems.get(selectedItem).setCount(inventoryItems.get(selectedItem).getCount() - 1);
				World.getInstance().getEntityManager().getPlayer()
						.setHealth(World.getInstance().getEntityManager().getPlayer().getHealth()
								+ inventoryItems.get(selectedItem).getStrength());
			}

		}
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_U)) {
			if (inventoryItems.get(selectedItem).getCount() - 1 == 0) {
				return;
			}
			if (inventoryItems.get(selectedItem).getAttack() <= 0) {

				return;
			}

			World.getInstance().getEntityManager().getPlayer().setCurrentWeapon(inventoryItems.get(selectedItem));
			World.getInstance().getEntityManager().getPlayer().setStrength(inventoryItems.get(selectedItem).getAttack());
		}

		if (selectedItem < 0) {
			selectedItem = inventoryItems.size() - 1;

		} else if (selectedItem >= inventoryItems.size()) {
			selectedItem = 0;
		}

	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}

		g.setColor(color);
		g.fillRoundRect(20, 20, 600, 440, 40, 40);
		g.setColor(Color.GRAY);
		g.fillRect(40, 80, Game.getInstance().getWidth() - 80, 2);
		Text.drawString(g, "Items", 320, 60, true, Color.gray, Assets.text);
		int length = inventoryItems.size();
		if (length == 0) {
			return;
		}
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(40, 220, 492, 40);

		g.setColor(Color.BLACK);
		g.fillRect(304, 466, 8, 8);
		g.fillRect(316, 466, 8, 8);
		g.fillRect(328, 466, 8, 8);

		g.setColor(Color.WHITE);
		g.fillRect(305, 467, 6, 6);
		g.setColor(Color.WHITE);
		g.fillRect(317, 467, 6, 6);
		g.setColor(Color.BLUE);
		g.fillRect(329, 467, 6, 6);

		for (int i = -5; i < 6; i++) {
			if (selectedItem + i < 0 || selectedItem + i >= length) {
				continue;
			}
			if (i == 0) {

				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", 40,
						invListCenterY + i * invListSpacing, false, Color.WHITE, Assets.smallText);
			} else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), 40,
						invListCenterY + i * invListSpacing, false, Color.GRAY, Assets.smallText);
			}
		}

		Item item = inventoryItems.get(selectedItem);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(526, 203, invImageWidth + 10, invImageHeight + 10);
		if (item.getCount() - 1 == 0) {
			g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
			g.setColor(color2);
			g.fillRect(invImageX, invImageY, invImageWidth, invImageHeight);
		} else {
			g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		}

		Text.drawString(g, "" + item.getStrength(), 563, 191, true, Color.gray, Assets.smallText);
		Text.drawString(g, "" + item.getPrize(), 563, 293, true, Color.gray, Assets.smallText);
		Text.drawString(g, "" + (item.getCount() - 1) + "x", 508, 242, true, Color.WHITE, Assets.smallText);

	}

	public void addItem(Item item) {
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isItemScreen() {
		return itemScreen;
	}

	public void setItemScreen(boolean itemScreen) {
		this.itemScreen = itemScreen;
	}

}
