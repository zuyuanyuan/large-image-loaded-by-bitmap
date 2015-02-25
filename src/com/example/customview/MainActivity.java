package com.example.customview;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageview);

		imageView.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.apple, 300, 300));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	 * 
	 * @param options
	 *            ����ͼƬ��������Ϣ
	 * @param reqWidth
	 *            ����ͼƬѹ���ߴ���С���
	 * @param reqHeight
	 *            ����ͼƬѹ���ߴ���С�߶�
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// ����ͼƬԭ���ֵ
		final int height = options.outHeight;
		final int width = options.outWidth;
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
