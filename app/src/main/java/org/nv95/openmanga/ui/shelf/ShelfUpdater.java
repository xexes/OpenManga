package org.nv95.openmanga.ui.shelf;

import org.nv95.openmanga.R;
import org.nv95.openmanga.content.shelf.ShelfContent;
import org.nv95.openmanga.ui.common.ListHeader;

import java.util.ArrayList;

/**
 * Created by koitharu on 24.12.17.
 */

public final class ShelfUpdater {

	public static void update(ShelfAdapter adapter, ShelfContent content) {
		ArrayList<Object> dataset = new ArrayList<>();
		if (!content.favourites.isEmpty()) {
			dataset.add(new ListHeader(R.string.action_favourites));
			dataset.addAll(content.favourites);
		}
		adapter.updateData(dataset);
	}
}