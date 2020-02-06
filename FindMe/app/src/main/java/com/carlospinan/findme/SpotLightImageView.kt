package com.carlospinan.findme

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.floor
import kotlin.random.Random

class SpotLightImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var paint = Paint()
    private var shouldDrawSpotLight = false
    private var gameOver = false

    private lateinit var winnerRect: RectF
    private var androidBitmapX = 0f
    private var androidBitmapY = 0f

    private lateinit var shader: Shader
    private val shaderMatrix = Matrix()

    private val bitmapAndroid = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.android
    )

    private val spotLight = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.mask
    )

    init {
        val bitmap = Bitmap.createBitmap(
            spotLight.width,
            spotLight.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        val shaderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        // Draw a black rectangle.
        shaderPaint.color = Color.BLACK
        canvas.drawRect(
            0f,
            0f,
            spotLight.width.toFloat(),
            spotLight.height.toFloat(),
            shaderPaint
        )

        shaderPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas.drawBitmap(spotLight, 0f, 0f, shaderPaint)

        shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Color the background yellow.
        /*
        canvas.drawColor(Color.YELLOW)

        shaderMatrix.setTranslate(
            30f,
            650f
        )
        shader.setLocalMatrix(shaderMatrix)

         */

        /*
        canvas.drawRect(
            0f,
            0f,
            spotLight.width.toFloat(),
            spotLight.height.toFloat(),
            paint
        )

         */

        /*
        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat() / 2,
            paint
        )
         */

        /*
        canvas.drawCircle(
            width.toFloat() / 2,
            height.toFloat() / 2,
            height.toFloat() / 4,
            paint
        )
         */

        /*
        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            paint
        )

         */
        canvas.drawColor(Color.WHITE)

        canvas.drawBitmap(bitmapAndroid, androidBitmapX, androidBitmapY, paint)

        if (!gameOver) {
            if (shouldDrawSpotLight) {
                canvas.drawRect(
                    0f,
                    0f,
                    width.toFloat(),
                    height.toFloat(),
                    paint
                )
            } else {
                canvas.drawColor(Color.BLACK)
            }
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setupWinnerRect()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val motionEventX = event.x
        val motionEventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                shouldDrawSpotLight = true
                if (gameOver) {
                    gameOver = false
                    setupWinnerRect()
                }
            }
            MotionEvent.ACTION_UP -> {
                shouldDrawSpotLight = false
                gameOver = winnerRect.contains(motionEventX, motionEventY)
            }
        }

        shaderMatrix.setTranslate(
            motionEventX - spotLight.width / 2f,
            motionEventY - spotLight.height / 2f
        )
        shader.setLocalMatrix(shaderMatrix)
        invalidate()

        return true
    }

    private fun setupWinnerRect() {
        androidBitmapX = floor(Random.nextFloat() * (width - bitmapAndroid.width))
        androidBitmapY = floor(Random.nextFloat() * (height - bitmapAndroid.height))

        winnerRect = RectF(
            (androidBitmapX),
            (androidBitmapY),
            (androidBitmapX + bitmapAndroid.width),
            (androidBitmapY + bitmapAndroid.height)
        )
    }

}