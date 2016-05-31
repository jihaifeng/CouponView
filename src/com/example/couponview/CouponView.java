package com.example.couponview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 优惠券自定义View
 * 
 * @author jihf
 * 
 */
public class CouponView extends LinearLayout {
	// 画图用到的类
	private Paint mPaint;
	// 两个圆之间的间距
	private float circleSpace = 8;
	// 圆的半径
	private float circleRadius = 10;
	// 圆心的位置
	private float centerPointX;
	// 圆的数量
	private float circleNum;
	// 不整除的剩余部分(即剩余的不能画圆的部分)
	private float remain;

	public CouponView(Context context) {
		super(context);
	}

	public CouponView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 构建Paint时直接加上抗锯齿属性
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// 设定使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
		mPaint.setDither(true);
		// 设置画笔的颜色
		mPaint.setColor(Color.WHITE);
		// 设置画笔的样式
		mPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (remain == 0) {
			/**
			 * 计算不可以画出圆的剩余部分
			 * 
			 * @思考：因为圆间距的个数比圆多一个
			 * 
			 * @算法:（屏幕宽度-一个圆间距）% （圆间距 + 圆直径） 求余
			 */
			remain = (int) ((w - circleSpace) % (circleSpace + 2 * circleRadius));
		}
		// 模运算
		circleNum = (int) ((w - circleSpace) / (circleSpace + 2 * circleRadius));
	}

	public CouponView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画圆
		for (int i = 0; i < circleNum; i++) {
			// 计算圆心的x轴位置
			centerPointX = remain / 2 + circleSpace + circleRadius
					+ ((circleSpace + 2 * circleRadius) * i);
			// 上方的圆
			canvas.drawCircle(centerPointX, 0, circleRadius, mPaint);
			// 下方的圆
			canvas.drawCircle(centerPointX, getHeight(), circleRadius, mPaint);
		}
	}

}
