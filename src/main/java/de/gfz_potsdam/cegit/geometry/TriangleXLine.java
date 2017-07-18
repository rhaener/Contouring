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

/**
 * <p>
 * Title: TriangleXLine</p>
 *
 * <p>
 * Description: Implementation of a Line String the crosses a triangle at a
 * certain depth. The "X" stand for cross  </p>
 *
 * Copyright (c) Helmholtz-Zentrum Potsdam Deutsches GeoForschungsZentrum,
 * 2017-03-14
 *
 *
 * @version 1.0, 2017-07-14
 * @author Rainer Haener
 */
public final class TriangleXLine {

    /**
     * Left (first) point(temporary) of the line that is the result of the
     * intersection of an even plane with a trinagle of a Triangulated Irregular
     * Network (TIN)
     */
    public ContourPoint left;

    /**
     * Right (second) point(temporary) of the line that is the result of the
     * intersection of an even plane with a trinagle of a Triangulated Irregular
     * Network (TIN)
     */
    public ContourPoint right;

    /**
     * the line string representation {@link TriangleXLine} of the adjacent
     * triangle that also intersects the given even plane
     */
    public TriangleXLine next;

    /**
     * Field description
     */
    public int index;

    /**
     * First point of the line that is the result of the intersection of an even
     * plane with a trinagle of a Triangulated Irregular Network (TIN)
     */
    public ContourPoint firstPoint;

    /**
     * Second point of the line that is the result of the intersection of an
     * even plane with a trinagle of a Triangulated Irregular Network (TIN)
     */
    public ContourPoint secondPoint;

    /**
     * connects two {@link TriangleXLine} in the correct order
     *
     *
     * @param t1
     *
     * @return
     */
    boolean connect(TriangleXLine t1) {
        boolean thlt1l = this.left.geometry.equalsExact(t1.left.geometry, AbstractHorizon.TOLERANCE);
        boolean thlt1r = this.left.geometry.equalsExact(t1.right.geometry, AbstractHorizon.TOLERANCE);
        boolean thrt1l = this.right.geometry.equalsExact(t1.left.geometry, AbstractHorizon.TOLERANCE);
        boolean thrt1r = this.right.geometry.equalsExact(t1.right.geometry, AbstractHorizon.TOLERANCE);

        if (thlt1l || thlt1r) {
            firstPoint = this.right;
            secondPoint = this.left;

            if (thlt1l) {
                t1.firstPoint = t1.left;
                t1.secondPoint = t1.right;
            } else if (thlt1r) {
                t1.firstPoint = t1.right;
                t1.secondPoint = t1.left;
            }
        } else if (thrt1l || thrt1r) {
            firstPoint = this.left;
            secondPoint = this.right;

            if (thrt1l) {
                t1.firstPoint = t1.left;
                t1.secondPoint = t1.right;
            } else if (thrt1r) {
                t1.firstPoint = t1.right;
                t1.secondPoint = t1.left;
            }
        }

        return thlt1l || thlt1r || thrt1l || thrt1r;
    }
}
