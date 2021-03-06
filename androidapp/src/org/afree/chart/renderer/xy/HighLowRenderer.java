/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * --------------------
 * HighLowRenderer.java
 * --------------------
 * 
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 *
 * Original Author:  shiraki  (for Icom Systech Co., Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2001-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1 (DG);
 * 23-Jan-2002 : Added DrawInfo parameter to drawItem() method (DG);
 * 28-Mar-2002 : Added a property change listener mechanism so that renderers
 *               no longer need to be immutable (DG);
 * 09-Apr-2002 : Removed translatedRangeZero from the drawItem() method, and
 *               changed the return type of the drawItem method to void,
 *               reflecting a change in the XYItemRenderer interface.  Added
 *               tooltip code to drawItem() method (DG);
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for
 *               HTML image maps (RA);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 31-Jul-2003 : Deprecated constructor (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Jan-2004 : Fixed bug (882392) when rendering with
 *               PlotOrientation.HORIZONTAL (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with
 *               getYValue() (DG);
 * 01-Nov-2005 : Added optional openTickPaint and closeTickPaint settings (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 06-Jul-2006 : Replace dataset methods getX() --> getXValue() (DG);
 * 08-Apr-2008 : Added findRangeBounds() override (DG);
 * 29-Apr-2008 : Added tickLength field (DG);
 * 25-Sep-2008 : Check for non-null entity collection (DG);
 *
 */

package org.afree.chart.renderer.xy;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.afree.ui.RectangleEdge;
import org.afree.chart.axis.ValueAxis;
import org.afree.data.xy.OHLCDataset;
import org.afree.data.Range;
import org.afree.data.xy.XYDataset;
import org.afree.data.general.DatasetUtilities;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.event.RendererChangeEvent;
import org.afree.chart.plot.CrosshairState;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.graphics.geom.LineShape;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;


/**
 * A renderer that draws high/low/open/close markers on an {@link XYPlot}
 * (requires a {@link OHLCDataset}).  This renderer does not include code to
 * calculate the crosshair point for the plot.
 *
 * The example shown here is generated by the
 * <code>HighLowChartDemo1.java</code> program included in the AFreeChart Demo
 * Collection:
 * <br><br>
 * <img src="../../../../../images/HighLowRendererSample.png"
 * alt="HighLowRendererSample.png" />
 */
public class HighLowRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, Cloneable, /*PublicCloneable,*/ Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -8135673815876552516L;

    /** A flag that controls whether the open ticks are drawn. */
    private boolean drawOpenTicks;

    /** A flag that controls whether the close ticks are drawn. */
    private boolean drawCloseTicks;

    /**
     * The paint used for the open ticks (if <code>null</code>, the series
     * paint is used instead).
     */
    private transient PaintType openTickPaintType;

    /**
     * The paint used for the close ticks (if <code>null</code>, the series
     * paint is used instead).
     */
    private transient PaintType closeTickPaintType;

    /**
     * The tick length (in Canvas units).
     *
     * @since JFreeChart 1.0.10
     */
    private double tickLength;

    /**
     * The default constructor.
     */
    public HighLowRenderer() {
        super();
        this.drawOpenTicks = true;
        this.drawCloseTicks = true;
        this.tickLength = 2.0;
    }

    /**
     * Returns the flag that controls whether open ticks are drawn.
     *
     * @return A boolean.
     *
     * @see #getDrawCloseTicks()
     * @see #setDrawOpenTicks(boolean)
     */
    public boolean getDrawOpenTicks() {
        return this.drawOpenTicks;
    }

    /**
     * Sets the flag that controls whether open ticks are drawn, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param draw  the flag.
     *
     * @see #getDrawOpenTicks()
     */
    public void setDrawOpenTicks(boolean draw) {
        this.drawOpenTicks = draw;
        //fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether close ticks are drawn.
     *
     * @return A boolean.
     *
     * @see #getDrawOpenTicks()
     * @see #setDrawCloseTicks(boolean)
     */
    public boolean getDrawCloseTicks() {
        return this.drawCloseTicks;
    }

    /**
     * Sets the flag that controls whether close ticks are drawn, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param draw  the flag.
     *
     * @see #getDrawCloseTicks()
     */
    public void setDrawCloseTicks(boolean draw) {
        this.drawCloseTicks = draw;
        //fireChangeEvent();
    }

    /**
     * Returns the paint used to draw the ticks for the open values.
     *
     * @return The paint type used to draw the ticks for the open values (possibly
     *         <code>null</code>).
     *
     * @see #setOpenTickPaintType(PaintType)
     */
    public PaintType getOpenTickPaintType() {
        return this.openTickPaintType;
    }

    /**
     * Sets the paint used to draw the ticks for the open values and sends a
     * {@link RendererChangeEvent} to all registered listeners.  If you set
     * this to <code>null</code> (the default), the series paint is used
     * instead.
     *
     * @param paintType  the paint (<code>null</code> permitted).
     *
     * @see #getOpenTickPaintType()
     */
    public void setOpenTickPaintType(PaintType paintType) {
        this.openTickPaintType = paintType;
        //fireChangeEvent();
    }

    /**
     * Returns the paint used to draw the ticks for the close values.
     *
     * @return The paint type used to draw the ticks for the close values (possibly
     *         <code>null</code>).
     *
     * @see #setCloseTickPaintType(PaintType)
     */
    public PaintType getCloseTickPaintType() {
        return this.closeTickPaintType;
    }

    /**
     * Sets the paint used to draw the ticks for the close values and sends a
     * {@link RendererChangeEvent} to all registered listeners.  If you set
     * this to <code>null</code> (the default), the series paint is used
     * instead.
     *
     * @param paintType  the paint (<code>null</code> permitted).
     *
     * @see #getCloseTickPaintType()
     */
    public void setCloseTickPaintType(PaintType paintType) {
        this.closeTickPaintType = paintType;
        //fireChangeEvent();
    }

    /**
     * Returns the tick length (in Canvas units).
     *
     * @return The tick length.
     *
     * @since JFreeChart 1.0.10
     *
     * @see #setTickLength(double)
     */
    public double getTickLength() {
        return this.tickLength;
    }

    /**
     * Sets the tick length (in Canvas units) and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     *
     * @since JFreeChart 1.0.10
     *
     * @see #getTickLength()
     */
    public void setTickLength(double length) {
        this.tickLength = length;
        //fireChangeEvent();
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     */
    public Range findRangeBounds(XYDataset dataset) {
        if (dataset != null) {
            return DatasetUtilities.findRangeBounds(dataset, true);
        }
        else {
            return null;
        }
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param canvas  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Canvas canvas,
                         XYItemRendererState state,
                         RectShape dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot,
                         ValueAxis domainAxis,
                         ValueAxis rangeAxis,
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {

        
        double x = dataset.getXValue(series, item);
        if (!domainAxis.getRange().contains(x)) {
            return;    // the x value is not within the axis range
        }
        double xx = domainAxis.valueToJava2D(x, dataArea,
                plot.getDomainAxisEdge());

        // setup for collecting optional entity info...
        Shape entityArea = null;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }

        PlotOrientation orientation = plot.getOrientation();
        RectangleEdge location = plot.getRangeAxisEdge();

        PaintType itemPaintType = getItemPaintType(series, item);
        float stroke = getItemStroke(series, item);
        PathEffect effect = getItemEffect(series, item);

        if (dataset instanceof OHLCDataset) {
            OHLCDataset hld = (OHLCDataset) dataset;

            double yHigh = hld.getHighValue(series, item);
            double yLow = hld.getLowValue(series, item);
            if (!Double.isNaN(yHigh) && !Double.isNaN(yLow)) {
                double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea,
                        location);
                double yyLow = rangeAxis.valueToJava2D(yLow, dataArea,
                        location);
                Paint itemPaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        itemPaintType,
                        stroke,
                        effect);
                if (orientation == PlotOrientation.HORIZONTAL) {
                    new LineShape(yyLow, xx, yyHigh, xx).draw(canvas, itemPaint);
                    entityArea = new RectShape(Math.min(yyLow, yyHigh),
                            xx - 1.0, Math.abs(yyHigh - yyLow), 2.0);
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    new LineShape(xx, yyLow, xx, yyHigh).draw(canvas, itemPaint);
                    entityArea = new RectShape(xx - 1.0,
                            Math.min(yyLow, yyHigh), 2.0,
                            Math.abs(yyHigh - yyLow));
                }
            }

            double delta = getTickLength();
            if (domainAxis.isInverted()) {
                delta = -delta;
            }
            if (getDrawOpenTicks()) {
                double yOpen = hld.getOpenValue(series, item);
                if (!Double.isNaN(yOpen)) {
                    double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea,
                            location);
                    PaintType paintType;
                    if (this.openTickPaintType != null) {
                        paintType = this.openTickPaintType;
                    }
                    else {
                        paintType = itemPaintType;
                    }
                    
                    Paint paint = PaintUtility.createPaint(
                            Paint.ANTI_ALIAS_FLAG,
                            paintType,
                            stroke,
                            effect);
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        new LineShape(yyOpen, xx + delta, yyOpen,
                                xx).draw(canvas, paint);
                    }
                    else if (orientation == PlotOrientation.VERTICAL) {
                        new LineShape(xx - delta, yyOpen, xx,
                                yyOpen).draw(canvas, paint);
                    }
                }
            }

            if (getDrawCloseTicks()) {
                double yClose = hld.getCloseValue(series, item);
                if (!Double.isNaN(yClose)) {
                    double yyClose = rangeAxis.valueToJava2D(
                        yClose, dataArea, location);
                    PaintType paintType;
                    if (this.closeTickPaintType != null) {
                        paintType = this.closeTickPaintType;
                    }
                    else {
                        paintType = itemPaintType;
                    }
                    Paint paint = PaintUtility.createPaint(
                            Paint.ANTI_ALIAS_FLAG,
                            paintType,
                            stroke,
                            effect);
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        new LineShape(yyClose, xx, yyClose,
                                xx - delta).draw(canvas, paint);
                    }
                    else if (orientation == PlotOrientation.VERTICAL) {
                        new LineShape(xx, yyClose, xx + delta,
                                yyClose).draw(canvas, paint);
                    }
                }
            }

        }
        else {
            // not a HighLowDataset, so just draw a line connecting this point
            // with the previous point...
            if (item > 0) {
                double x0 = dataset.getXValue(series, item - 1);
                double y0 = dataset.getYValue(series, item - 1);
                double y = dataset.getYValue(series, item);
                if (Double.isNaN(x0) || Double.isNaN(y0) || Double.isNaN(y)) {
                    return;
                }
                double xx0 = domainAxis.valueToJava2D(x0, dataArea,
                        plot.getDomainAxisEdge());
                double yy0 = rangeAxis.valueToJava2D(y0, dataArea, location);
                double yy = rangeAxis.valueToJava2D(y, dataArea, location);

                Paint itemPaint = PaintUtility.createPaint(
                        Paint.ANTI_ALIAS_FLAG,
                        itemPaintType,
                        stroke,
                        effect);
                
                if (orientation == PlotOrientation.HORIZONTAL) {
                    new LineShape(yy0, xx0, yy, xx).draw(canvas, itemPaint);
                }
                else if (orientation == PlotOrientation.VERTICAL) {
                    new LineShape(xx0, yy0, xx, yy).draw(canvas, itemPaint);
                }
            }
        }

        if (entities != null) {
            addEntity(entities, entityArea, dataset, series, item, 0.0, 0.0);
        }

    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HighLowRenderer)) {
            return false;
        }
        HighLowRenderer that = (HighLowRenderer) obj;
        if (this.drawOpenTicks != that.drawOpenTicks) {
            return false;
        }
        if (this.drawCloseTicks != that.drawCloseTicks) {
            return false;
        }
        //TODO:port PaintUtilities
//        if (!PaintUtilities.equal(this.openTickPaint, that.openTickPaint)) {
//            return false;
//        }
//        if (!PaintUtilities.equal(this.closeTickPaint, that.closeTickPaint)) {
//            return false;
//        }
        if (this.tickLength != that.tickLength) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
      //TODO:port SerialUtilities
//        this.openTickPaint = SerialUtilities.readPaint(stream);
//        this.closeTickPaint = SerialUtilities.readPaint(stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        
        //TODO:port SerialUtilities
//        SerialUtilities.writePaint(this.openTickPaint, stream);
//        SerialUtilities.writePaint(this.closeTickPaint, stream);
    }

}
