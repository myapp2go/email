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
 * ------------------------------
 * AbstractIntervalXYDataset.java
 * ------------------------------
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
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited).
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-May-2004 : Version 1 (DG);
 * 15-Jul-2004 : Switched getStartX() and getStartXValue() methods and
 *               others (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 *
 */

package org.afree.data.xy;

import org.afree.data.xy.IntervalXYDataset;


/**
 * An base class that you can use to create new implementations of the
 * {@link IntervalXYDataset} interface.
 */
public abstract class AbstractIntervalXYDataset extends AbstractXYDataset
        implements IntervalXYDataset {

    /**
     * 
     */
    private static final long serialVersionUID = 5449690395635948009L;

    /**
     * Returns the start x-value (as a double primitive) for an item within a
     * series.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public double getStartXValue(int series, int item) {
        double result = Double.NaN;
        Number x = getStartX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }

    /**
     * Returns the end x-value (as a double primitive) for an item within a
     * series.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public double getEndXValue(int series, int item) {
        double result = Double.NaN;
        Number x = getEndX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }

    /**
     * Returns the start y-value (as a double primitive) for an item within a
     * series.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public double getStartYValue(int series, int item) {
        double result = Double.NaN;
        Number y = getStartY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }

    /**
     * Returns the end y-value (as a double primitive) for an item within a
     * series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value.
     */
    public double getEndYValue(int series, int item) {
        double result = Double.NaN;
        Number y = getEndY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }

}
