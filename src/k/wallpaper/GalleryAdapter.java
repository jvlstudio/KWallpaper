package k.wallpaper;

import k.framework.K;
import k.framework.adapters.KAdapter;
import k.framework.adapters.KAdapter.KAdapterConfig;
import k.framework.sub.KResources.ResType;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

@KAdapterConfig(selected = true, click = true)
@SuppressWarnings("deprecation")
public class GalleryAdapter extends
		KAdapter<Integer, SpinnerAdapter, ImageView> {
	private final static int ALPHA_ACTIVE = 255, ALPHA_UNACTIVE = 100;

	private static final String LAST = "last";

	private static final String SETTINGS = "settings";

	private ImageView lastView;

	public GalleryAdapter(Context context) {
		super(K.get(context).res().filter(ResType.Drawable, "wall_.*"));
	}

	@Override
	public void bind(AdapterView<SpinnerAdapter> adapterView) {
		super.bind(adapterView);
		if (adapterView != null) {
			adapterView.setSelection(k().settings(SETTINGS).get(LAST, 0));
		}
	}

	@Override
	protected void onBindView(int position, Integer item, ImageView view) {
		view.setImageResource(item);
		view.setAlpha(isSelected(position, GalleryAdapter.ALPHA_ACTIVE,
				GalleryAdapter.ALPHA_UNACTIVE));
	}

	@Override
	protected ImageView onCreateView(int position, Integer item) {
		return new ImageView(context());
	}

	@Override
	protected void onItemClick(int position, Integer item, ImageView view) {
		k().wallpaper().set(item).k().closeApplication();
	}

	@Override
	protected void onItemSelected(int position, Integer item, ImageView view) {
		if (lastView != null) {
			lastView.setAlpha(GalleryAdapter.ALPHA_UNACTIVE);
		}
		if (view != null) {
			(lastView = view).setAlpha(GalleryAdapter.ALPHA_ACTIVE);
		}
		k().settings(SETTINGS).put(LAST, position).save();
	}
}
