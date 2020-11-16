package com.akki.pencilloader

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat


class PencilLoader : View {
    private var mRadius: Int = 0
    private var mLoaderColor: Int = 0
    private var mStrokeWidth: Int = 0
    private lateinit var mPaint: Paint
    private var pencilIcon: VectorDrawableCompat? = null
    private var rectF: RectF? = null
    private var mSweepAngle = 180
    private val mDefaultPadding = 20f
    private var mAnimationDuration = 2000

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val attributes: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.PencilLoader)
        try {
            mAnimationDuration = attributes.getInteger(
                R.styleable.PencilLoader_pencil_loader_animation_duration,
                2000
            )
            mLoaderColor =
                attributes.getColor(R.styleable.PencilLoader_pencilloader_color, Color.BLACK)

            mStrokeWidth =
                attributes.getDimensionPixelSize(R.styleable.PencilLoader_pencil_loader_stroke, 10)

            pencilIcon =
                VectorDrawableCompat.create(
                    resources,
                    R.drawable.ic_iconfinder_3_edit_pencil_write_2990996,
                    null
                )
            init()
        } finally {
            attributes.recycle()
        }

    }

    private fun init() {
        mPaint = Paint()
        mPaint.color = mLoaderColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mStrokeWidth.toFloat()
        rectF = RectF()
//        DrawableCompat.setTint(
//            pencilIcon?.current!!,
//            mLoaderColor
//        )

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val left = paddingLeft + mDefaultPadding
        val top = paddingTop + mDefaultPadding
        val right = paddingRight + mDefaultPadding
        val bottom = paddingBottom + mDefaultPadding
        mRadius = if (width > height) {
            (height / 2f).toInt()
        } else {
            (width / 2f).toInt()
        }
        val center_x: Float
        val center_y: Float

        center_x = width / 2
        center_y = height / 2
        rectF?.set(
            center_x - mRadius + left, center_y - mRadius + top,
            center_x + mRadius - right, center_y + mRadius - bottom
        )

        pencilIcon?.setBounds(
            (center_x - mRadius + left).toInt(),
            (center_y - mRadius + top).toInt(),
            (center_x + mRadius - right).toInt(),
            (center_y + mRadius - bottom).toInt()
        )
        pencilIcon?.draw(canvas!!)
        canvas?.drawArc(rectF!!, 90f, mSweepAngle.toFloat(), false, mPaint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.v("[Pencil onMeasure w]", MeasureSpec.toString(widthMeasureSpec));
        Log.v("[Pencil onMeasure h]", MeasureSpec.toString(heightMeasureSpec));

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(
            measureDimension(desiredWidth, widthMeasureSpec),
            measureDimension(desiredHeight, heightMeasureSpec)
        )
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        if (result < desiredSize) {
            Log.e("PencilLoader", "The view is too small, the content might get cut")
        }
        return result
    }

    fun showProgress() {
        val rotate = RotateAnimation(
            0f, -360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = mAnimationDuration.toLong()
        rotate.repeatCount = Animation.INFINITE
        val linearInterpolator = LinearInterpolator()
        rotate.interpolator = linearInterpolator
        startAnimation(rotate)
    }

    fun hideProgress() {
        if (visibility == VISIBLE) {
            visibility = GONE
        }

    }
}