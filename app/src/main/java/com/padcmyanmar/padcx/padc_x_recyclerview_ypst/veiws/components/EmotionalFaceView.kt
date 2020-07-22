package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R

class EmotionalFaceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR

    //view size
    private var size = 0

    //border width
    private var borderWidth = DEFAULT_BORDER_WIDTH

    private val mouthPath = Path()

    var happinessState = HAPPY
    set(state){
       field=state
       invalidate()
   }


    init {
        paint.isAntiAlias=true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        //get custom attribute from xml
        context.withStyledAttributes(attrs, R.styleable.EmotionalFaceView){
            happinessState = getString(R.styleable.EmotionalFaceView_state)?.toLongOrNull() ?: HAPPY
            faceColor = getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
            eyesColor = getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
            mouthColor = getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
            borderColor = getColor(R.styleable.EmotionalFaceView_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth =
                getDimension(R.styleable.EmotionalFaceView_borderWidth, DEFAULT_BORDER_WIDTH)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 1 get minimum value
        size = measuredWidth.coerceAtMost(measuredHeight)

        // 2 override width and height
        setMeasuredDimension(size, size)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMoutn(canvas)
    }

    private fun drawMoutn(canvas: Canvas?) {

        mouthPath.reset()

        /*
           the starting point of the path to (x0,y0) by using the moveTo()
           (22,70)
         */
        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY){

            /*
              draw a curved path from the starting point and through (x1,y1) that ends with (x2,y2)
              (50,80) , (78,70)
           */
            mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)


            /*
                   Draw a curved path starting from the last end point (x2,y2) and through (x3,y3) and that ends with (x0,y0)
                   (50,90) , (22,70)
                 */
            mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)

        }

        else{
            // 3
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }



        paint.color=mouthColor
        paint.style=Paint.Style.FILL
        canvas?.drawPath(mouthPath,paint)

    }

    private fun drawEyes(canvas: Canvas?) {
       paint.color=eyesColor
        paint.style=Paint.Style.FILL


        //left eye
        val leftEyeRect = RectF(size * 0.32f,size * 0.23f,size * 0.43f,size * 0.50f)
        canvas?.drawOval(leftEyeRect,paint)


        //right eye
        val rightEyeRect = RectF(size * 0.57f,size * 0.23f,size * 0.68f,size * 0.50f)
        canvas?.drawOval(rightEyeRect,paint)
    }

    private fun drawFaceBackground(canvas: Canvas?) {
       paint.color=faceColor
        paint.style=Paint.Style.FILL

        val radius = size / 2f

        canvas?.drawCircle(size/2f,size/2f,radius,paint)

        //set background stroke color
        paint.color=borderColor
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=borderWidth

        canvas?.drawCircle(size/2f,size/2f,radius - borderWidth/2f,paint)
    }


    companion object {

        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

}