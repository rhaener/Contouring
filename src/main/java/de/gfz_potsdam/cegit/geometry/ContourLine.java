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
import java.util.HashMap;

/**
 * <p>
 * Title: Contour-Line Generator</p>
 *
 * <p>
 * Description: Generates contour lines from a TIN-Representation of a
 * (geological) structure by cross-cutting it with a plane of a given depth
 * (must be horizontal) </p>
 *
 * Copyright (c) Helmholtz-Zentrum Potsdam Deutsches GeoForschungsZentrum,
 * 2017-03-14
 *
 *
 * @version 1.0, 2017-07-14
 * @author Rainer Haener
 */
public class ContourLine {

    /**
     * List of line strings {@code TriangleXLine} yet to be processed (sorted,
     * linked, etc)
     */
    private ArrayList<TriangleXLine> temporaryLineStrings = new ArrayList<>();

    /**
     * Field description
     */
    private int counter = 0;

    /**
     * Field description
     */
    private HashMap<Integer, ArrayList<TriangleXLine>> contourLines = new HashMap<>();

    /**
     * Field description
     */
    Rectangle2D bounds2D = new Rectangle2D.Double();

    /**
     * Field description
     */
    private final AbstractHorizon h3d;

    /**
     * Constructs ...
     *
     *
     * @param horizon
     */
    public ContourLine(AbstractHorizon horizon) {
        h3d = horizon;
    }

    /**
     * connect triangles for each depth
     *
     *
     * @param csd cross section depth
     *
     *
     * @return
     */
    public HashMap<Integer, ArrayList<TriangleXLine>> buildContourLines(final double csd) {

        temporaryLineStrings = h3d.createContourLines(csd);

        this.bounds2D = h3d.getBounds2D();

        contourLines = new HashMap<>();
        this.process();

        return contourLines;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Rectangle2D getBounds2D() {
        return bounds2D;
    }

    /**
     * Triangles have to be sorted according to their position in order to
     * buildContourLines a valid starting point
     *
     */
    private void process() {
        if (!temporaryLineStrings.isEmpty()) {

            // sort it according to their geometry, what means,
            // that equal points of different triangles that however share the side with the point are neighbours in the list
            temporaryLineStrings.sort((TriangleXLine o1, TriangleXLine o2) -> o1.left.geometry.union(o1.right.geometry).compareTo(o2.left.geometry.union(o2.right.geometry)));

            this.collectLinePoints(temporaryLineStrings.get(0));
            //this.collectLinePoints(temps.getFirst());
        }
    }

    /**
     * Rekursively parses the triangle list for connection points
     *
     *
     *
     * @param t0
     */
    private void collectLinePoints(TriangleXLine t0) {

        int cnt = 0;
        while (!temporaryLineStrings.isEmpty()) {

            // the triangle for which a connection shall be found can now be removed from the list
            temporaryLineStrings.remove(t0);

            ArrayList<TriangleXLine> line = contourLines.get(counter);

            if (line == null) {
                line = new ArrayList<>();
                contourLines.put(counter, line);
            }

            line.add(t0);

            TriangleXLine t1 = this.getConnectedTriangle(t0);

            if (t1 != null) {
                this.collectLinePoints(t1);
            } else {

                // all remaining elements searched, so restart over with the first element of the remaining list and restart a new contour line
                if (!temporaryLineStrings.isEmpty()) {
                    t0 = temporaryLineStrings.get(0);
                }

                System.out.println("counter " + counter + " <> " + cnt + " cnt");
                cnt++;
                counter++;
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param t
     *
     * @return
     */
    private TriangleXLine getConnectedTriangle(TriangleXLine t) {
        TriangleXLine connection = null;

        for (int i = 0; i < temporaryLineStrings.size(); i++) {
            TriangleXLine ti = temporaryLineStrings.get(i);
            boolean connected = t.connect(ti);

            if (connected) {
                t.next = ti;
                connection = temporaryLineStrings.get(i);

                break;
            }
        }

        return connection;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public HashMap<Integer, ArrayList<TriangleXLine>> getContourLines() {
        return contourLines;
    }

    /**
     * Method description
     *
     */
    public void plot() {

        contourLines.forEach((Integer lineNumber, ArrayList<TriangleXLine> line) -> {
            StringBuilder strbui = new StringBuilder();
            TriangleXLine first = line.get(0);

            strbui.append("Line ").append(lineNumber).append(": ").append(first.index);

            TriangleXLine current = first;

            TriangleXLine next;

            while ((next = current.next) != null) {

                strbui.append(" >> ").append(next.index);
                current = next;
            }

            int indexOfLast = line.size() - 1;
            TriangleXLine last = line.get(indexOfLast);
            boolean connect = first.connect(last);

            if (connect && (line.size() > 2)) {
                strbui.append(" is closed ");
            }

            System.out.println(strbui.toString());
            System.out.println(first.firstPoint.geometry + " - > " + last.secondPoint.geometry);
        });
    }
}
