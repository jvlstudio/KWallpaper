#!/bin/bash
select __dpi in "ldpi" "mdpi" "hdpi" "xhdpi"; do
	dpi=$__dpi
	break
done
echo -n "Enter wallpaper width: "
read width
echo -n "Enter wallpaper heigth: "
read height
if [ ! -e "../res/drawable-$dpi" ]; then
	mkdir "../res/drawable-$dpi"
fi
for z in *.jpg; do
	full_name=`basename "$z" .jpg`
	compile_name=`echo $full_name | tr '[A-Z ]' '[a-z_]'`
	echo $full_name save to $compile_name
	convert -strip -resize "!`echo $width`x`echo $height`" -antialias "$z" "../res/drawable-$dpi/wall_$compile_name.jpg"
done
