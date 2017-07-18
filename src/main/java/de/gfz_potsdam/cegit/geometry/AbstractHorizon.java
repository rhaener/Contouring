/*
 * Copyright (C) 2016
 * Helmholtz-Zentrum Potsdam Deutsches GeoForschungsZentrum
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; even without the implied WARRANTY OF MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see LICENSE.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation's web page, http://www.fsf.org.
 */
package de.gfz_potsdam.cegit.geometry;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * <p>
 * Title: Abstract Horizon </p>
 *
 * <p>
 * Description: Interface for a Horizon (uneven surface) implemented with a
 * Triangulated Irregular Network (TIN) </p>
 *
 * Copyright (c) Helmholtz-Zentrum Potsdam Deutsches GeoForschungsZentrum,
 * 2017-07-13
 *
 *
 * @version 1.0, 2017-07-13
 * @author Rainer Haener
 */
public abstract class AbstractHorizon {

    /**
     * tolerance for comparing geometries. Geometries that differ with an amount
     * less than TOLERANCE are regarde equal
     */
    public static final double TOLERANCE = 0.001;

    /**
     * 2D Bounding Box of the horizon
     */
    protected Rectangle2D bounds2D = new Rectangle2D.Double();

    /**
     * temporary value for computing the bounding box
     */
    protected double xmin = Double.MAX_VALUE;

    /**
     * temporary value for computing the bounding box
     */
    protected double xmax = Double.MIN_VALUE;

    /**
     * temporary value for computing the bounding box
     */
    protected double ymin = Double.MAX_VALUE;

    /**
     * temporary value for computing the bounding box
     */
    protected double ymax = Double.MIN_VALUE;

    /**
     * temporary value for computing the bounding box
     */
    protected double zmin = +10000.0;    // Double.MAX_VALUE;

    /**
     * temporary value for computing the bounding box
     */
    protected double zmax = -10000.0;    // Double.MIN_VALUE;

    /**
     * computes the line string (List) of lines that cross the triangles of a
     * horizon at a given depth
     *
     *
     *
     * @param csd iso value (depth) of the contour line
     *
     * @return List of lines that cross a triangle at a given depth
     */
    public abstract ArrayList<TriangleXLine> createContourLines(final double csd);

    /**
     * gets the bounding box of all triangles, matching the given depth
     *
     *
     * @return
     */
    public Rectangle2D getBounds2D() {
        return bounds2D;
    }

}
