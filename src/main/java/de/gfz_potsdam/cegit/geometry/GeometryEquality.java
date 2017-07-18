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

import com.vividsolutions.jts.geom.Geometry;
import de.gfz_potsdam.cegit.util.function.Equality;

/**
 * Compares two geometries ({@link com.vividsolutions.jts.geom.Geometry})
 * concerning their congruence
 *
 *
 * @param <T>
 *
 * @version 1.0, 2017-07-16
 * @author Rainer Haener
 */
public class GeometryEquality<T extends Geometry> implements Equality<T> {

    /**
     * {@inheritDoc}
     *
     *
     * @param geometry
     *
     * @return
     */
    @Override
    public int hashOf(T geometry) {

        int hash = geometry.hashCode();

        //hash = 89 * hash + (int) (Double.doubleToLongBits(geometry.getCoordinate().y) ^ (Double.doubleToLongBits(geometry.getCoordinate().y) >>> 32));
        return hash;
    }

    /**
     * {@inheritDoc}
     *
     *
     * @param left
     * @param right
     *
     * @return
     */
    @Override
    public boolean equal(T left, T right) {
        return hashOf(left) == hashOf(right);
    }

    /**
     * {@inheritDoc}
     *
     *
     * @param left
     * @param right
     *
     * @return
     */
    @Override
    public int compare(T left, T right) {

        return left.compareTo(right);

    }
}
