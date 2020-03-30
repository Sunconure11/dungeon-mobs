package com.gw.dm.util;

import net.minecraft.item.Item;

public class RMFoodItem {
	private final Item item;
	private final int meta;
	
	public RMFoodItem(Item item, int meta) {
		this.item = item;
		this.meta = meta;
	}
	
	
	public RMFoodItem(Item item) {
		this.item = item;
		meta = -1;
	}
	
	@Override
	public int hashCode() {
		return item.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof RMFoodItem)) {
			RMFoodItem other = (RMFoodItem) obj;
			return ((other.item == item) && ((meta < 0) || (other.meta < 0) || (other.meta == meta)));
		}
		return false;
	}
	
}
