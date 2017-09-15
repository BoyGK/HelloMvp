package com.gkpoter.hellomvp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/13.
 */

public class GraphView extends ImageView {

    private List<Point> point = null;
    private float offset = 50f;
    private float textSize = 40f;
    private float lineWidth = 4f;
    private float pointWidth = 20f;
    private float height;
    private float width;

    public GraphView(Context context) {
        this(context, null);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGraph(canvas);
    }

    private void drawGraph(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(lineWidth);
        canvas.drawLine(offset, offset, offset, getBottom() - offset, paint);
        canvas.drawLine(offset, getBottom() - offset, getRight() - offset, getBottom() - offset, paint);
        paint.setTextSize(textSize);
        canvas.drawText(height + "", offset + 10, offset + textSize / 2, paint);
        if (point != null && point.size() != 0) {
            float lx = (getRight() - offset * 2) / width * point.get(0).x + offset;
            float ly = getBottom() - offset - ((getHeight() - offset * 2) / height * (float) point.get(0).y);
            for (Point point1 : point) {
                float x = (getRight() - offset * 2) / width * point1.x + offset;
                float y = getBottom() - offset - ((getHeight() - offset * 2) / height * (float) point1.y);
                canvas.drawText(point1.x + "", x - textSize / 4, getBottom() - (offset - textSize), paint);
                paint.setStrokeWidth(pointWidth);
                canvas.drawPoint(x, y, paint);
                paint.setStrokeWidth(lineWidth);
                canvas.drawLine(lx, ly, x, y, paint);
                lx = x;
                ly = y;
            }
        }
    }

    public void drawGraph(Point... p) {
        point = new ArrayList<>();
        for (Point p1 : p) {
            point.add(p1);
        }
        invalidate();
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setPointWidth(float pointWidth) {
        this.pointWidth = pointWidth;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
