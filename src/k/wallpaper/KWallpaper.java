package k.wallpaper;

import k.framework.KActivity;
import k.framework.loader.KAutoCreate;
import k.framework.loader.KBind;
import k.framework.loader.KLayout;

@KLayout(R.layout.main)
public class KWallpaper extends KActivity {
	@KAutoCreate
	@KBind(R.id.mainG_Gallery)
	private GalleryAdapter adapter;
}