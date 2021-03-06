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
 * ---------------------------------
 * BoxAndWhiskerCategoryDataset.java
 * ---------------------------------
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
 * (C) Copyright 2003-2008, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine
 *                   Science);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 27-Aug-2003 : Renamed getAverageValue --> getMeanValue, changed
 *               getAllOutliers to return a List rather than an array (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 *
 */

package org.afree.data.statistics;

import java.util.List;

import org.afree.data.category.CategoryDataset;

/**
 * A category dataset that defines various medians, outliers and an average
 * value for each item.
 */
public interface BoxAndWhiskerCategoryDataset extends CategoryDataset {

    /**
     * Returns the mean value for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The mean value.
     */
    public Number getMeanValue(int row, int column);

    /**
     * Returns the average value for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The average value.
     */
    public Number getMeanValue(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the median value for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The median value.
     */
    public Number getMedianValue(int row, int column);

    /**
     * Returns the median value for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The median value.
     */
    public Number getMedianValue(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the q1median value for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The q1median value.
     */
    public Number getQ1Value(int row, int column);

    /**
     * Returns the q1median value for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The q1median value.
     */
    public Number getQ1Value(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the q3median value for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The q3median value.
     */
    public Number getQ3Value(int row, int column);

    /**
     * Returns the q3median value for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The q3median value.
     */
    public Number getQ3Value(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the minimum regular (non-outlier) value for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The minimum regular value.
     */
    public Number getMinRegularValue(int row, int column);

    /**
     * Returns the minimum regular (non-outlier) value for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The minimum regular value.
     */
    public Number getMinRegularValue(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the maximum regular (non-outlier) value for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The maximum regular value.
     */
    public Number getMaxRegularValue(int row, int column);

    /**
     * Returns the maximum regular (non-outlier) value for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The maximum regular value.
     */
    public Number getMaxRegularValue(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the minimum outlier (non-farout) for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The minimum outlier.
     */
    public Number getMinOutlier(int row, int column);

    /**
     * Returns the minimum outlier (non-farout) for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The minimum outlier.
     */
    public Number getMinOutlier(Comparable rowKey, Comparable columnKey);

    /**
     * Returns the maximum outlier (non-farout) for an item.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return The maximum outlier.
     */
    public Number getMaxOutlier(int row, int column);

    /**
     * Returns the maximum outlier (non-farout) for an item.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return The maximum outlier.
     */
    public Number getMaxOutlier(Comparable rowKey, Comparable columnKey);

    /**
     * Returns a list of outlier values for an item. The list may be empty, but
     * should never be <code>null</code>.
     * 
     * @param row
     *            the row index (zero-based).
     * @param column
     *            the column index (zero-based).
     * 
     * @return A list of outliers for an item.
     */
    public List getOutliers(int row, int column);

    /**
     * Returns a list of outlier values for an item. The list may be empty, but
     * should never be <code>null</code>.
     * 
     * @param rowKey
     *            the row key.
     * @param columnKey
     *            the columnKey.
     * 
     * @return A list of outlier values for an item.
     */
    public List getOutliers(Comparable rowKey, Comparable columnKey);

}
