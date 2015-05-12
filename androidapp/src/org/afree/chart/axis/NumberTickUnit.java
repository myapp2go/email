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
 * -------------------
 * NumberTickUnit.java
 * -------------------
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
 * (C) Copyright 2001-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Dec-2001 : Added standard header (DG);
 * 01-May-2002 : Updated for changed to TickUnit class (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 09-Jan-2002 : Added a new constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 05-Jul-2005 : Added equals() implementation (DG);
 * 05-Sep-2005 : Implemented hashCode(), thanks to Thomas Morgner (DG);
 * 02-Aug-2007 : Added new constructor with minorTickCount (DG);
 *
 */

package org.afree.chart.axis;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * A numerical tick unit.
 */
public class NumberTickUnit extends TickUnit implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 3849459506627654442L;

    /** A formatter for the tick unit. */
    private NumberFormat formatter;

    /**
     * Creates a new number tick unit.
     * 
     * @param size
     *            the size of the tick unit.
     */
    public NumberTickUnit(double size) {
        this(size, NumberFormat.getNumberInstance());
    }

    /**
     * Creates a new number tick unit.
     * 
     * @param size
     *            the size of the tick unit.
     * @param formatter
     *            a number formatter for the tick unit (<code>null</code> not
     *            permitted).
     */
    public NumberTickUnit(double size, NumberFormat formatter) {
        super(size);
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.formatter = formatter;
    }

    /**
     * Creates a new number tick unit.
     * 
     * @param size
     *            the size of the tick unit.
     * @param formatter
     *            a number formatter for the tick unit (<code>null</code> not
     *            permitted).
     * @param minorTickCount
     *            the number of minor ticks.
     * 
     * @since JFreeChart 1.0.7
     */
    public NumberTickUnit(double size, NumberFormat formatter,
            int minorTickCount) {
        super(size, minorTickCount);
        if (formatter == null) {
            throw new IllegalArgumentException("Null 'formatter' argument.");
        }
        this.formatter = formatter;
    }

    /**
     * Converts a value to a string.
     * 
     * @param value
     *            the value.
     * 
     * @return The formatted string.
     */
    public String valueToString(double value) {
        return this.formatter.format(value);
    }

    /**
     * Tests this formatter for equality with an arbitrary object.
     * 
     * @param obj
     *            the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberTickUnit)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        NumberTickUnit that = (NumberTickUnit) obj;
        if (!this.formatter.equals(that.formatter)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representing this unit.
     * 
     * @return A string.
     */
    public String toString() {
        return "[size=" + this.valueToString(this.getSize()) + "]";
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result = super.hashCode();
        result = 29 * result
                + (this.formatter != null ? this.formatter.hashCode() : 0);
        return result;
    }

}
