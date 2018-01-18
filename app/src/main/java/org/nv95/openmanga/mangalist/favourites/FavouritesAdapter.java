package org.nv95.openmanga.mangalist.favourites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.nv95.openmanga.R;
import org.nv95.openmanga.common.utils.ImageUtils;
import org.nv95.openmanga.common.utils.LayoutUtils;
import org.nv95.openmanga.core.models.MangaFavourite;
import org.nv95.openmanga.core.providers.MangaProvider;
import org.nv95.openmanga.preview.PreviewActivity;

import java.util.ArrayList;

/**
 * Created by koitharu on 18.01.18.
 */

final class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouriteHolder> {

	private final ArrayList<MangaFavourite> mDataset;

	FavouritesAdapter(ArrayList<MangaFavourite> dataset) {
		setHasStableIds(true);
		mDataset = dataset;
	}

	@Override
	public FavouriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new FavouriteHolder(LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_manga_list, parent, false));
	}

	@Override
	public void onBindViewHolder(FavouriteHolder holder, int position) {
		MangaFavourite item = mDataset.get(position);
		holder.text1.setText(item.name);
		LayoutUtils.setTextOrHide(holder.text2, item.summary);
		holder.summary.setText(item.genres);
		ImageUtils.setThumbnail(holder.imageView, item.thumbnail, MangaProvider.getDomain(item.provider));
		holder.itemView.setTag(item);
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	@Override
	public long getItemId(int position) {
		return mDataset.get(position).id;
	}

	@Override
	public void onViewRecycled(FavouriteHolder holder) {
		ImageUtils.recycle(holder.imageView);
		super.onViewRecycled(holder);
	}

	class FavouriteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		final TextView text1;
		final TextView text2;
		final TextView summary;
		final ImageView imageView;

		FavouriteHolder(View itemView) {
			super(itemView);
			text1 = itemView.findViewById(android.R.id.text1);
			text2 = itemView.findViewById(android.R.id.text2);
			summary = itemView.findViewById(android.R.id.summary);
			imageView = itemView.findViewById(R.id.imageView);

			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			final Context context = view.getContext();
			final MangaFavourite item = mDataset.get(getAdapterPosition());
			switch (view.getId()) {
				default:
					context.startActivity(new Intent(context.getApplicationContext(), PreviewActivity.class)
							.putExtra("manga", item));
			}
		}
	}
}
