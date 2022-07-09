package com.huihuijiang.ui

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.huihuijiang.R
import com.huihuijiang.tool.RadarData

@SuppressLint("ResourceAsColor")
/**
 * Created by fengzhiqi on 2017/6/10.
 */
class RadarView0 @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    private var dataList: MutableList<RadarData>? = null
    private var count = 6 //雷达网圈数
    private var angle //多边形弧度
            = 0f
    private var radius = 0f
    private var radiusStorage = 0f
    private val maxValue = 100f
    private var mainPaint //雷达区画笔
            : Paint? = null
    private var valuePaint //数据区画笔
            : Paint? = null
    private var textPaint //文本画笔
            : Paint? = null
    private val mainColor = -0x777778 //雷达区颜色
    private val valueColor = Color.valueOf(R.color.colorPrimary).toArgb() //数据区颜色
    private val textColor = -0x7f7f80 //文本颜色
    private val mainLineWidth = 1f //雷达网线宽度dp
    private val valueLineWidth = 1f //数据区边宽度dp
    private val valuePointRadius = 2f //数据区圆点半径dp
    private val textSize = 16f //字体大小sp
    private var Alpha = 128
    private var mWidth = 0
    private var mHeight = 0
    private fun setup() {
        mainPaint = Paint()
        mainPaint!!.isAntiAlias = true
        mainPaint!!.color = mainColor
        mainPaint!!.style = Paint.Style.STROKE
        valuePaint = Paint()
        valuePaint!!.isAntiAlias = true
        valuePaint!!.color = valueColor
        valuePaint!!.style = Paint.Style.FILL_AND_STROKE
        textPaint = Paint()
        textPaint!!.isAntiAlias = true
        textPaint!!.style = Paint.Style.FILL
        textPaint!!.color = textColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (Math.min(h, w) shr 1) * 0.6f
        radiusStorage = radius
        mWidth = w
        mHeight = h
        postInvalidate()
        super.onSizeChanged(w, h, oldw, oldh)
        startAnim()
    }

    fun startAnim() {
        val animator = ValueAnimator.ofFloat(0f, radiusStorage)
        animator.duration = 400
        animator.addUpdateListener { animation ->
            radius = animation.animatedValue as Float
            Alpha = (radius / radiusStorage * 128).toInt()
            invalidate()
        }
        animator.start()
    }

    fun refresh(index: Int, s: String) {
        val data = RadarData(dataList!![index].title, s.toDouble() * 100 / 1.3)
        dataList!![index] = data
        invalidate()
        startAnim()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        if (isDataListValid) {
            drawSpiderweb(canvas)
            drawText(canvas)
            drawRegion(canvas)
        }
    }

    /**
     * 绘制蜘蛛网
     *
     * @param canvas
     */
    private fun drawSpiderweb(canvas: Canvas) {
        mainPaint!!.strokeWidth =
            dip2px(context, mainLineWidth).toFloat()
        val webPath = Path()
        val linePath = Path()
        val r = radiusStorage / (count - 1) //蜘蛛丝之间的间距
        for (i in 0 until count) {
            val curR = r * i //当前半径
            webPath.reset()
            for (j in 0 until count) {
                val x = (curR * Math.sin((angle / 2 + angle * j).toDouble())).toFloat()
                val y = (curR * Math.cos((angle / 2 + angle * j).toDouble())).toFloat()
                if (j == 0) {
                    webPath.moveTo(x, y)
                } else {
                    webPath.lineTo(x, y)
                }
                if (i == count - 1) { //当绘制最后一环时绘制连接线
                    linePath.reset()
                    linePath.moveTo(0f, 0f)
                    linePath.lineTo(x, y)
                    canvas.drawPath(linePath, mainPaint!!)
                }
            }
            webPath.close()
            canvas.drawPath(webPath, mainPaint!!)
        }
    }

    /**
     * 绘制文本
     *
     * @param canvas
     */
    private fun drawText(canvas: Canvas) {
        textPaint!!.textSize = sp2px(context, textSize).toFloat()
        val fontMetrics = textPaint!!.fontMetrics
        val fontHeight = fontMetrics.descent - fontMetrics.ascent
        for (i in 0 until count) {
            val x =
                ((radiusStorage + fontHeight * 2) * Math.sin((angle / 2 + angle * i).toDouble())).toFloat()
            val y =
                ((radiusStorage + fontHeight * 2) * Math.cos((angle / 2 + angle * i).toDouble())).toFloat()
            val title = dataList!![i].title
            val dis = textPaint!!.measureText(title) //文本长度
            canvas.drawText(title, x - dis / 2, y, textPaint!!)
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private fun drawRegion(canvas: Canvas) {
        valuePaint!!.strokeWidth =
            dip2px(context, valueLineWidth).toFloat()
        val path = Path()
        valuePaint!!.alpha = Alpha
        path.reset()
        for (i in 0 until count) {
            val percent = dataList!![i].percentage / maxValue
            val x = (radius * Math.sin((angle / 2 + angle * i).toDouble()) * percent).toFloat()
            val y = (radius * Math.cos((angle / 2 + angle * i).toDouble()) * percent).toFloat()
            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
            //绘制小圆点
            canvas.drawCircle(
                x, y, dip2px(context, valuePointRadius).toFloat(),
                valuePaint!!
            )
        }
        path.close()
        valuePaint!!.style = Paint.Style.STROKE
        canvas.drawPath(path, valuePaint!!)
        valuePaint!!.alpha = 128
        //绘制填充区域
        valuePaint!!.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path, valuePaint!!)
    }

    private val isDataListValid: Boolean
        private get() = dataList != null && dataList!!.size >= 3

    fun setDataList(dataList: MutableList<RadarData>) {
        if (isDataListValid) {
            throw RuntimeException("The number of data can not be less than 3")
        } else {
            this.dataList = dataList
            count = dataList.size //圈数等于数据个数，默认为6
            angle = (Math.PI * 2 / count).toFloat()
            invalidate()
        }
    }

    companion object {
        private val TAG = RadarView::class.java.simpleName
        fun dip2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        fun sp2px(context: Context, spValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }
    }

    init {
        setup()
    }
}