package com.huihuijiang.ui

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.huihuijiang.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * 雷达图
 * 六维图
 * 三维图
 * Created by juan on 2021/07/20.
 */
class RadarView : View {
    /**
     * 字体颜色
     */
    private var mTextColor = 0
    /**
     * 绘制正多边形区域的颜色
     */
    private var mMainRegionColor = 0
    /**
     * 绘制正多边形边框或者圆圈的颜色
     */
    private var mMainFrameColor = 0
    /**
     * 绘制数据区域1的颜色
     */
    private var mRegion1Color = 0
    /**
     * 绘制数据区域1实际边框的颜色
     */
    private var mActualframe1Color = 0
    /**
     * 绘制数据区域1要不要使用渐变色
     */
    private var mRegion1GradientEnable = false
    /**
     * 绘制数据区域1渐变的开始颜色
     */
    private var mRegion1StartColor = 0
    /**
     * 绘制数据区域1渐变的结束颜色
     */
    private var mRegion1EndColor = 0
    /**
     * 要不要绘制数据区域1
     */
    private var mRegion1Enable = true
    /**
     * 要不要绘制数据区域1实际边框
     */
    private var mActualframe1Enable = true
    /**
     * 绘制数据区域2的颜色
     */
    private var mRegion2Color = 0
    /**
     * 绘制数据区域2实际边框的颜色
     */
    private var mActualFrame2Color = 0
    /**
     * 要不要绘制数据区域2
     */
    private var mRegion2Enable = true
    /**
     * 要不要绘制数据区域2实际边框
     */
    private var mActualframe2Enable = true
    /**
     * 要不要绘制正多边形区域
     */
    private var mMainRegionEnable = false
    /**
     * 用于创建线性渐变效果
     */
    private var gradient: LinearGradient? = null
    /**
     * 中心X
     */
    private var centerX = 0
    /**
     * 中心Y
     */
    private var centerY = 0
    /**
     * 网格最大半径
     */
    private var radiusStorage = 0f
    private var radius = 0f
    /**
     * 绘制正多边形边框或者圆圈的画笔
     */
    private var mainFramePaint: Paint? = null
    /**
     * 绘制正多边形区域的画笔
     */
    private var mainRegionPaint: Paint? = null
    /**
     * 文本的画笔
     */
    private var textPaint: Paint? = null
    /**
     * 数据区域1的画笔
     */
    private var regionPaint1: Paint? = null
    /**
     * 数据区域1实际边框的画笔
     */
    private var actualFramePaint1: Paint? = null
    /**
     * 数据区域2的画笔
     */
    private var regionPaint2: Paint? = null
    /**
     * 数据区域2实际边框的画笔
     */
    private var actualFramePaint2: Paint? = null
    /**
     * 文字大小
     */
    private var fontSize = 32f
    /**
     * 绘制几边形
     */
    private var count = 6
    private var angle = Math.PI / count * 2
    private var titles = arrayOf("力量", "技巧", "敏捷", "体质", "感知", "意志")
    private var data1 = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0) //各维度分值
    private var data2 = doubleArrayOf(80.0, 90.0, 95.0, 80.0, 95.0, 40.0, 90.0, 100.0) //各维度分值
    private var data3 = doubleArrayOf(90.0, 80.0, 65.0, 100.0, 65.0, 70.0, 50.0, 60.0) //各维度分值
    private val maxValue = 130f //数据最大值
    //多边形每一条先的起始坐标和终点坐标
    private var endX = 0f
    private var endY = 0f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
        initPaint()
    }

    /**
     * 获取自定义属性
     */
    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun initView(attrs: AttributeSet?) {
        val t = context.obtainStyledAttributes(attrs, R.styleable.RadarView)
        this.mTextColor = t.getColor(R.styleable.RadarView_color_text,mTextColor)
        this.mMainRegionColor = t.getColor(R.styleable.RadarView_color_main_region,mMainRegionColor)
        this.mMainFrameColor = t.getColor(R.styleable.RadarView_color_main_frame,mMainFrameColor)
        this.mRegion1Color = t.getColor(R.styleable.RadarView_color_region1,mRegion1Color)
        this.mActualframe1Color = t.getColor(R.styleable.RadarView_color_actual_frame1,mActualframe1Color)
        this.mRegion2Color = t.getColor(R.styleable.RadarView_color_region2,mRegion2Color)
        this.mActualFrame2Color = t.getColor(R.styleable.RadarView_color_actual_frame2,mActualFrame2Color)
        this.mRegion1GradientEnable = t.getBoolean(R.styleable.RadarView_region1_gradient_enable,false)
        this.mRegion1StartColor = t.getColor(R.styleable.RadarView_color_region1_start,mRegion1StartColor)
        this.mRegion1EndColor = t.getColor(R.styleable.RadarView_color_region1_end,mRegion1EndColor)
        this.mMainRegionEnable = t.getBoolean(R.styleable.RadarView_main_region_enable,false)
        this.mRegion1Enable = t.getBoolean(R.styleable.RadarView_region1_enable,true)
        this.mActualframe1Enable = t.getBoolean(R.styleable.RadarView_ractual_frame1_enable,true)
        this.mRegion2Enable = t.getBoolean(R.styleable.RadarView_region2_enable,true)
        this.mActualframe2Enable = t.getBoolean(R.styleable.RadarView_ractual_frame2_enable,true)
        this.fontSize = t.getDimension(R.styleable.RadarView_textSize, dpToPx(14f).toFloat())
    }

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        //绘制正多边形边框或者圆圈的画笔
        mainFramePaint = Paint()
        mainFramePaint!!.color = mMainFrameColor
        mainFramePaint!!.isAntiAlias = true
        mainFramePaint!!.strokeWidth = 4f
        mainFramePaint!!.style = Paint.Style.STROKE

        //绘制正多边形区域的画笔
        mainRegionPaint = Paint()
        mainRegionPaint!!.style = Paint.Style.FILL
        mainRegionPaint!!.color = mMainRegionColor

        //文本的画笔
        textPaint = Paint()
        textPaint!!.color = mTextColor
        textPaint!!.isAntiAlias = true
        textPaint!!.textSize = fontSize
        textPaint!!.style = Paint.Style.FILL

        //数据区域1的画笔
        regionPaint1 = Paint()
        regionPaint1!!.style = Paint.Style.FILL_AND_STROKE
        regionPaint1!!.color = mRegion1Color
        regionPaint1!!.isAntiAlias = true

        //数据区域1实际边框的画笔
        actualFramePaint1 = Paint()
        actualFramePaint1!!.color = mActualframe1Color
        actualFramePaint1!!.isAntiAlias = true
        actualFramePaint1!!.strokeWidth = 0f
        actualFramePaint1!!.style = Paint.Style.STROKE

        //数据区域2的画笔
        regionPaint2 = Paint()
        regionPaint2!!.style = Paint.Style.FILL_AND_STROKE
        regionPaint2!!.color = mRegion2Color
        regionPaint2!!.isAntiAlias = true

        //数据区域2实际边框的画笔
        actualFramePaint2 = Paint()
        actualFramePaint2!!.color = mActualFrame2Color
        actualFramePaint2!!.isAntiAlias = true
        actualFramePaint2!!.strokeWidth = 0f
        actualFramePaint2!!.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w / 2
        centerY = h / 2
        radius = h.coerceAtMost(w) / 2 - textPaint!!.measureText(titles[0]) //半径为宽高的一半减去文字的长度
        radiusStorage = radius
        invalidate() // 工作在ui线程
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mMainRegionEnable){
            drawPolygon(canvas, radius)
        }
        if (count < 4){
            drawmCircle(canvas)
        }else{
            drawPolygon(canvas)
        }
        drawLines(canvas)
        drawText(canvas)
        if (mRegion2Enable){
            drawRegion2(canvas)
        }
        if (mActualframe2Enable){
            drawFrame2(canvas)
        }
        if (mRegion1Enable){
            drawRegion1(canvas)
        }
        if (mActualframe1Enable){
            drawFrame1(canvas)
        }
    }

    /**
     * 绘制正多边形区域
     */
    private fun drawPolygon(canvas: Canvas, radius: Float) {
        val path = Path()
        path.moveTo(centerX.toFloat(), centerY - radius)
        for (i in 0 until count) {
            endX = (centerX + radius * sin(angle * i)).toFloat()
            endY = (centerY - radius * cos(angle * i)).toFloat()
            path.lineTo(endX, endY)
        }
        path.close()
        canvas.drawPath(path, mainRegionPaint!!)
    }

    /**
     * 绘制正多边形边框
     */
    private fun drawPolygon(canvas: Canvas) {
        val path = Path()
        val r = radiusStorage / 3 //r是蜘蛛丝之间的间距
        for (i in 1 until 4) { //中心点不用绘制
            val curR = r * i //当前半径
            path.reset()
            path.moveTo(centerX.toFloat(), centerY - curR)
            for (j in 0 until count) { //根据半径，计算出蜘蛛丝上每个点的坐标
                val x = (centerX + curR * sin(angle * j)).toFloat()
                val y = (centerY - curR * cos(angle * j)).toFloat()
                path.lineTo(x, y)
            }
            path.close() //闭合路径
            canvas.drawPath(path, mainFramePaint!!)
        }
    }

    /**
     * 绘制三个圆圈
     */
    private fun drawmCircle(canvas: Canvas) {
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius / 1.toFloat(), mainFramePaint!!)
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius / 1.5.toFloat(), mainFramePaint!!)
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius / 3.toFloat(), mainFramePaint!!)
    }

    /**
     * 绘制直线
     * 绘制从中心到末端的直线
     * 同样根据半径，计算出每个末端坐标
     */
    private fun drawLines(canvas: Canvas) {
        val path = Path()
        for (i in 0 until count) {
            path.reset()
            //设置每一次的起点为中心
            path.moveTo(centerX.toFloat(), centerY.toFloat())
            val x = (centerX + radiusStorage * sin(angle * i)).toFloat()
            val y = (centerY - radiusStorage * cos(angle * i)).toFloat()
            //画线
            path.lineTo(x, y)
            canvas.drawPath(path, mainFramePaint!!)
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas 对于文本的绘制，首先要找到末端的坐标，
     * 由于末端和文本有一定距离，给每个末端加上这个距离以后，再绘制文本。
     * 另外，当文本在左边时，由于不希望文本和蜘蛛网交叉，
     * 我们可以先计算出文本的长度，然后使起始绘制坐标向左偏移这个长度。
     */
    private fun drawText(canvas: Canvas) {
        val fontMetrics = textPaint!!.fontMetrics
        val fontHeight = fontMetrics.descent - fontMetrics.ascent
        for (i in 0 until count) {
            val x = (centerX + (radiusStorage + fontHeight / 2) * sin(angle * i)).toFloat()
            val y = (centerY - (radiusStorage + fontHeight / 2) * cos(angle * i)).toFloat()
            val dis = textPaint!!.measureText(titles[i]) //文本长度
            val title = titles[i]
            when (i) {
                0 -> {
                    canvas.drawText(title, x - dis / 2, y, textPaint!!)
                }
                1 -> {
                    canvas.drawText(title, x, y + 20, textPaint!!)
                }
                2 -> {
                    if (count == 3){
                        if (fontSize < 20){
                            canvas.drawText(title, x - 30, y + 20, textPaint!!)
                        }else{
                            canvas.drawText(title, x - 50, y + 30, textPaint!!)
                        }
                    }else{
                        canvas.drawText(title, x, y, textPaint!!)
                    }
                }
                3 -> {
                    canvas.drawText(title, x - dis / 2, y + 20, textPaint!!)
                }
                4 -> {
                    canvas.drawText(title, x - dis, y, textPaint!!)
                }
                else -> {
                    canvas.drawText(title, x - dis, y + 20, textPaint!!)
                }
            }
        }
    }

    /**
     * 绘制数据区域1
     *
     * @param canvas 使path包围区域被填充
     */
    private fun drawRegion1(canvas: Canvas) {
        val path = Path()
        if (mRegion1GradientEnable){
            gradient = LinearGradient(0f, 0f, 0f, radius * 2, mRegion1StartColor, mRegion1EndColor, Shader.TileMode.CLAMP)
            regionPaint1!!.shader = gradient
            regionPaint1!!.alpha = alpha
        }else{
            regionPaint1!!.alpha = 255
        }
        path.reset()
        for (i in 0 until count) {
            val percent = data1[i] / maxValue
            val x = (centerX + radius * sin(angle * i) * percent).toFloat()
            val y = (centerY - radius * cos(angle * i) * percent).toFloat()
            if (i == 0){
                path.moveTo(centerX.toFloat(), y)
            }
            path.lineTo(x, y)
        }
        regionPaint1!!.alpha = alpha
        //绘制填充区域
        regionPaint1!!.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path, regionPaint1!!)
    }

    /**
     * 绘制区域1实际边框
     */
    private fun drawFrame1(canvas: Canvas) {
        val path = Path()
        actualFramePaint1!!.alpha = 255
        path.reset()
        for (i in 0 until count) {
            val percent = data1[i] / maxValue
            val x = (centerX + radius * sin(angle * i) * percent).toFloat()
            val y = (centerY - radius * cos(angle * i) * percent).toFloat()
            if (i == 0){
                path.moveTo(centerX.toFloat(), y)
            }
            path.lineTo(x, y)
            //绘制小圆点
            canvas.drawCircle(x, y, dpToPx(3f).toFloat(), regionPaint1!!)
        }
        actualFramePaint1!!.style = Paint.Style.STROKE
        path.close() //闭合路径
        canvas.drawPath(path, actualFramePaint1!!)
    }

    /**
     * 绘制数据区域2
     *
     * @param canvas 使path包围区域被填充
     */
    private fun drawRegion2(canvas: Canvas) {
        val path = Path()
        regionPaint2!!.alpha = 255
        path.reset()
        for (i in 0 until count) {
            val percent = data2[i] / maxValue
            val x = (centerX + radius * sin(angle * i) * percent).toFloat()
            val y = (centerY - radius * cos(angle * i) * percent).toFloat()
            if (i == 0){
                path.moveTo(centerX.toFloat(), y)
            }
            path.lineTo(x, y)
        }
        regionPaint2!!.alpha = 127
        //绘制填充区域
        regionPaint2!!.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path, regionPaint2!!)
    }

    /**
     * 绘制区域2的实际边框
     */
    private fun drawFrame2(canvas: Canvas) {
        val path = Path()
        actualFramePaint2!!.alpha = 255
        path.reset()
        for (i in 0 until count) {
            val percent = data2[i] / maxValue
            val x = (centerX + radius * sin(angle * i) * percent).toFloat()
            val y = (centerY - radius * cos(angle * i) * percent).toFloat()
            if (i == 0){
                path.moveTo(centerX.toFloat(), y)
            }
            path.lineTo(x, y)
            //绘制小圆点
            canvas.drawCircle(x, y, 1f, regionPaint1!!)
        }
        actualFramePaint2!!.style = Paint.Style.STROKE
        path.close() //闭合路径
        canvas.drawPath(path, actualFramePaint2!!)
    }

    companion object {
        /**
         * dp转换为px
         */
        fun dpToPx(dp: Float): Int {
            return (dp * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
        }
    }

    fun setData(titles: Array<String>, data1: MutableList<Double>) {
        this.titles = titles
        this.data1 = data1.toDoubleArray()
        count = min(titles.size, data1.size)
        angle = Math.PI / count * 2
        invalidate()
    }

    fun setData(data: DoubleArray){
        for (i in 0..5) {
            data[i] = data[i] * 100
        }
        this.data1=data
        invalidate()
        startAnim()
    }

    fun reFlash(index: Int,data: Double){
        this.data1[index]=data*100
        invalidate()
        startAnim()
    }
    private var alpha = 128
    fun startAnim() {
        val animator = ValueAnimator.ofFloat(0f, radiusStorage)
        animator.duration = 600
        animator.addUpdateListener { animation ->
            radius = animation.animatedValue as Float
            alpha = (((radius / radiusStorage * 128).toInt()))
            invalidate()
        }
        animator.start()
    }
    fun setData(titles: Array<String>, data1: DoubleArray, data2: DoubleArray) {
        this.titles = titles
        this.data1 = data1
        this.data2 = data2
        count = min(titles.size, data1.size)
        angle = Math.PI / count * 2
        invalidate()
    }

    fun setData(titles: Array<String>, data1: DoubleArray, data2: DoubleArray, data3: DoubleArray) {
        this.titles = titles
        this.data1 = data1
        this.data2 = data2
        this.data3 = data3
        count = titles.size
        angle = Math.PI / count * 2
        invalidate()
    }

    /**
     * 是否要绘制数据区域1
     */
    fun setRegion1Enable(mEnable: Boolean){
        mRegion1Enable = mEnable
    }

    /**
     * 是否要绘制数据区域2
     */
    fun setRegion2Enable(mEnable: Boolean){
        mRegion2Enable = mEnable
    }


    /**
     * 是否要绘制数据区域1实际边框
     */
    fun setActualframe1Enable(mEnable: Boolean){
        mActualframe1Enable = mEnable
    }

    /**
     * 是否要绘制数据区域2实际边框
     */
    fun setActualframe2Enable(mEnable: Boolean){
        mActualframe2Enable = mEnable
    }
    fun setTextSize(size:Float){
        fontSize= size
    }
}
