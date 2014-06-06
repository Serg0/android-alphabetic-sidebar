package com.travlesky.mycontact.core.comparator;

import java.util.Comparator;

import com.travlesky.mycontact.core.spell.SpellUtil;

public class SpellComparator implements Comparator {

	public int compare(Object object1, Object object2) {
		final String str1 = SpellUtil.getSpell((String) object1);
		final String str2 = SpellUtil.getSpell((String) object2);
		return str1.compareTo(str2);
	}

}
