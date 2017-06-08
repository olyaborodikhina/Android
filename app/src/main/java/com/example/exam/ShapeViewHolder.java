package com.example.exam;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ольга on 08.06.2017.
 */

public class ShapeViewHolder extends RecyclerView.ViewHolder{
    private TextView shapeTitle;
    private TextView shapeDescription;
    private ImageView shapeImage;

    public ShapeViewHolder(ViewGroup viewGroup, int layoutId) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));
        shapeImage = (ImageView)itemView.findViewById(R.id.image);
        shapeTitle = (TextView)itemView.findViewById(R.id.title);
        shapeDescription= (TextView)itemView.findViewById(R.id.description);
    }

    public void bindView(Shape shape) {
        shapeTitle.setText(shape.getTitle());
        shapeDescription.setText(shape.getDescription());
        shapeImage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        GradientDrawable shapeDraw = new GradientDrawable();
        switch (shape.getType()) {
            case "Rectangle":
                shapeDraw.setShape(GradientDrawable.RECTANGLE);
                break;
            case "Circle":
                shapeDraw.setShape(GradientDrawable.OVAL);
                break;
            case "Line":
                shapeDraw.setShape(GradientDrawable.RECTANGLE);
                break;
        }

        shapeDraw.setSize(R.dimen.shape_item_shape_width, R.dimen.shape_item_shape_height);
        switch (shape.getColor()) {
            case "Red":
                if (shape.getType().equals("Line")) {
                    shapeDraw.setStroke(10, Color.RED);
                } else {
                    shapeDraw.setColor(Color.RED);
                }
                break;
            case "Green":
                if (shape.getType().equals("Line")) {
                    shapeDraw.setStroke(10,Color.GREEN);
                } else {
                    shapeDraw.setColor(Color.GREEN);
                }
                break;
            case "Blue":
                if (shape.getType().equals("Line")) {
                    shapeDraw.setStroke(10,Color.BLUE);
                } else {
                    shapeDraw.setColor(Color.BLUE);
                }
        }


        shapeImage.setImageDrawable(shapeDraw);
    }
}
