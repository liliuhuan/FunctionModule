package com.liliuhuan.com.mylibrary.utils.glide;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bolooo.studyhomeparents.R;
import com.bumptech.glide.Glide;

import java.io.File;

/**
 * 图片加载框架封装
 *
 * @author nanfeifei
 * @since 2016年10月9日上午11:49:21
 * @version 1.0
 */
public class GlideUtils {
	public Context context;
	public GlideUtils(Context context){
		this.context = context;

	}
	public void loadFileImage(String pathurl, ImageView imageView){
		String path = Constants.imageUrl + pathurl + "?w=&h=";
		loadImage(path,imageView, R.drawable.noimage);
	}
	public void loadFileImageWH(String pathurl, ImageView imageView){
		String path = Constants.imageUrl + pathurl + "?w=400&h=400";
		loadImage(path,imageView, R.drawable.noimage);
	}
	public void loadFileNewImage(String pathurl, ImageView imageView){
		String path = Constants.imageUrl + pathurl + "?w=&h=";
		loadImage(path,imageView, R.drawable.noimage);
	}
	public void loadNewFileImageRound(String pathurl, ImageView imageView){
		String path = Constants.imageUrl + pathurl + "?w=400&h=400";
		loadImage(path,imageView, R.drawable.noavatar);
	}
	public void loadFileImageRound(String pathurl, ImageView imageView){
		String path = Constants.imageUrl + pathurl + "?w=400&h=400";
		loadRoundImage(path,imageView, R.drawable.noavatar,DensityUtil.dip2px(context,66));
	}
	public void loadFileNewImageRound(String pathurl, ImageView imageView){
		if(!pathurl.contains("http")){
			pathurl =  Constants.imageUrl + pathurl + "?w=400&h=400";
		}

		loadRoundImage(pathurl,imageView, R.drawable.noavatar,DensityUtil.dip2px(context,66));
	}
	/**
	 * 加载图片到ImageView
	 * @param imageurl  图片地址
	 * @param imageView  View
	 * @param placeholder  占位图
	 */
	public void loadImage(String imageurl, ImageView imageView, int placeholder){

		if(imageurl!=null){
			if(!imageurl.contains("http")){
				imageurl = Constants.imageUrl+imageurl;
			}
			if(placeholder>0){
				Glide.with(context)
						.load(imageurl)
						.placeholder(placeholder)
						.crossFade()
						.centerCrop()
						.into(imageView);
			}else{
				Glide.with(context)
						.load(imageurl)
						.crossFade()
						.into(imageView);
			}

		}

	}

	/**
	 * 加载图片到ImageView
	 * @param imageResouce  图片地址
	 * @param imageView  View
	 * @param placeholder  占位图
	 */
	public void loadImage(int imageResouce, ImageView imageView, int placeholder){

		if(imageResouce!=0){
			if(placeholder>0){
				Glide.with(context)
						.load(imageResouce)
						.placeholder(placeholder)
						.crossFade()
						.into(imageView);
			}else{
				Glide.with(context)
						.load(imageResouce)
						.crossFade()
						.into(imageView);
			}

		}

	}
	/**
	 * 加载图片到ImageView
	 * @param file  图片路径
	 * @param imageView  View
	 * @param placeholder  占位图
	 */
	public void loadImage(File file, ImageView imageView, int placeholder){

		if(file!=null){
			if(placeholder>0){
				Glide.with(context)
						.load(file)
						.placeholder(placeholder)
						.into(imageView);
			}else{
				Glide.with(context)
						.load(file)
						.into(imageView);
			}

		}

	}
	/**
	 * 加载图片到ImageView，显示圆角
	 * @param imageurl
	 * @param imageView
	 * @param placeholder
	 * @param radius   圆角半径
	 */
	public void loadRoundImage(String imageurl, ImageView imageView, int placeholder, int radius){
		if(imageurl!=null){
			if(!imageurl.contains("http")){
				imageurl = Constants.imageUrl+imageurl;
			}
			Glide.with(context)
					.load(imageurl)
					.placeholder(placeholder)
					.crossFade()
					.transform(new GlideRoundTransform(context.getApplicationContext(), radius))
					.into(imageView);
		}

	}
	/**
	 * 加载图片到ImageView
	 * @param imageurl  图片地址
	 * @param imageView  View
	 * @param placeholder  占位图
	 */
	public void loadImageCrop(String imageurl, ImageView imageView, int placeholder, int radius){

		if(imageurl!=null){
			if(!imageurl.contains("http")){
				imageurl = Constants.imageUrl+imageurl+"?w=400&h=400";
			}
			Glide.with(context)
					.load(imageurl)
					.placeholder(placeholder)
					.crossFade()
					.transform(new GlideRoundTransform(context.getApplicationContext(), radius))
					.into(imageView);
		}

	}

	/**
	 * 加载图片到ImageView，显示圆角
	 * @param file
	 * @param imageView
	 * @param placeholder
	 * @param radius   圆角半径
	 */
	public void loadRoundImage(File file, ImageView imageView, int placeholder, int radius){
		if(file!=null){
			Glide.with(context)
					.load(file)
					.placeholder(placeholder)
					.crossFade()
					.transform(new GlideRoundTransform(context.getApplicationContext(), radius))
					.into(imageView);
		}

	}
	/**
	 * 加载图片到ImageView，显示圆角
	 * @param uri
	 * @param imageView
	 * @param placeholder
	 * @param radius   圆角半径
	 */
	public void loadRoundImage(Uri uri, ImageView imageView, int placeholder, int radius){
		if(uri!=null){
			Glide.with(context)
					.load(uri)
					.placeholder(placeholder)
					.crossFade()
					.transform(new GlideRoundTransform(context.getApplicationContext(), radius))
					.into(imageView);
		}

	}

}
