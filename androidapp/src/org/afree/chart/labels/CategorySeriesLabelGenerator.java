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
 * CategorySeriesLabelGenerator.java
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
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Apr-2005 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-Nov-2006 : Updated API docs (DG);
 *
 */

package org.afree.chart.labels;

import org.afree.data.category.CategoryDataset;

/**
 * A generator that creates labels for the series in a {@link CategoryDataset}.
 * <P>
 * Classes that implement this interface should be either (a) immutable, or (b)
 * cloneable via the <code>PublicCloneable</code> interface (defined in the
 * AFreeChart class library). This provides a mechanism for the referring renderer
 * to clone the generator if necessary.
 */
public interface CategorySeriesLabelGenerator {

    /**
     * Generates a label for the specified series.
     * 
     * @param dataset
     *            the dataset (<code>null</code> not permitted).
     * @param series
     *            the series index.
     * 
     * @return A series label.
     */
    public String generateLabel(CategoryDataset dataset, int series);

}
