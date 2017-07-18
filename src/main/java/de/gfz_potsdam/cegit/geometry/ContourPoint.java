/*
 * Copyright (C) 2017
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

import com.vividsolutions.jts.geom.Geometry;

/**
 * <p>
 * Title: Contour Point</p>
 *
 * <p>
 * Description: Representation of a single point, which is part of a line string
 * (isoline, contourline) that is created from the intersection of a triangle
 * with a even surface</p>
 *
 * Copyright (c) Helmholtz-Zentrum Potsdam Deutsches GeoForschungsZentrum,
 * 2017-03-10
 *
 *
 * @version 1.0, 2017-03-10
 * @author Rainer Haener
 */
public class ContourPoint {

    /**
     * Field description
     */
    private boolean visited = false;

    /**
     * Field description
     */
    public Geometry geometry;

    /**
     * Field description
     */
    final int tn;

    /**
     * Constructs ...
     *
     *
     * @param geometry
     * @param tn
     */
    public ContourPoint(Geometry geometry, int tn) {
        this.geometry = geometry;
        this.tn = tn;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Method description
     *
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
