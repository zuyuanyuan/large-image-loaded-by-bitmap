package com.example.customview;

import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.CollapsibleActionView;
import android.view.DragEvent;
import android.view.View;

public class MyView extends View {

	String text = "";
	int textcolor = -1;
	int textsize = -1;

	private Paint mPaint;
	private Rect mRect;
	Bitmap mBitmap = null;

	public MyView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		mBitmap = Bitmap.createBitmap(400, 400, Config.ARGB_8888);
		// mBitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.apple);
		int width = mBitmap.getWidth();
		int height = mBitmap.getHeight();
		System.out
				.println("the width is " + width + " the height is " + height);
		mBitmap = decodeSampledBitmapFromResource(getResources(),R.drawable.apple,500,500);
	}

	public MyView(Context context, AttributeSet attrs, int defstyle) {
		super(context, attrs, defstyle);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		// canvas.setBitmap(mBitmap);
		canvas.drawBitmap(mBitmap, new Matrix(), mPaint);
	}

	/**
	 * ��ȡѹ�����ͼƬ
	 * 
	 * @param res
	 * @param resId
	 * @param reqWidth
	 *            ����ͼƬѹ���ߴ���С���
	 * @param reqHeight
	 *            ����ͼƬѹ���ߴ���С�߶�
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// ���Ȳ�����ͼƬ,����ȡͼƬ�ߴ�
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// ��inJustDecodeBounds��Ϊtrueʱ,�������ͼƬ����ȡͼƬ�ߴ���Ϣ
		options.inJustDecodeBounds = true;
		// ��ʱ���ὫͼƬ��Ϣ�ᱣ����options������,decode�������᷵��bitmap����
		BitmapFactory.decodeResource(res, resId, options);

		// ����ѹ������,��inSampleSize=4ʱ,ͼƬ��ѹ����ԭͼ��1/4
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// ��inJustDecodeBounds��Ϊfalseʱ,BitmapFactory.decode...�ͻ᷵��ͼƬ������
		options.inJustDecodeBounds = false;
		// ���ü���ı���ֵ��ȡѹ�����ͼƬ����
		return BitmapFactory.decodeResource(res, resId, options);
	}
	
	
	/**
	* ����ѹ������ֵ
	* @param options       ����ͼƬ��������Ϣ
	* @param reqWidth            ����ͼƬѹ���ߴ���С���
	* @param reqHeight           ����ͼƬѹ���ߴ���С�߶�
	* @return
	*/
	public static int calculateInSampleSize(BitmapFactory.Options options,
	             int reqWidth, int reqHeight) {
	       // ����ͼƬԭ���ֵ
	       final int height = options. outHeight;
	       final int width = options. outWidth;
	       // ��ʼ��ѹ������Ϊ1
	       int inSampleSize = 1;

	       // ��ͼƬ���ֵ�κ�һ����������ѹ��ͼƬ���ֵʱ,����ѭ������ϵͳ
	       if (height > reqHeight || width > reqWidth) {

	             final int halfHeight = height / 2;
	             final int halfWidth = width / 2;

	             // ѹ������ֵÿ��ѭ����������,
	             // ֱ��ԭͼ���ֵ��һ�����ѹ��ֵ��~����������ֵΪֹ
	             while ((halfHeight / inSampleSize) >= reqHeight
	                        && (halfWidth / inSampleSize) >= reqWidth) {
	                  inSampleSize *= 2;
	            }
	      }

	       return inSampleSize;
	}

}
