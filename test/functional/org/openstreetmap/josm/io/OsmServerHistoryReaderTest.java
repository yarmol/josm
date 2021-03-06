// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.io;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.data.osm.OsmPrimitiveType;
import org.openstreetmap.josm.data.osm.history.History;
import org.openstreetmap.josm.data.osm.history.HistoryDataSet;
import org.openstreetmap.josm.gui.progress.NullProgressMonitor;

/**
 * History fetching tests. This test operates with production API.
 */
public class OsmServerHistoryReaderTest {

    /**
     * Setup tests.
     */
    @BeforeClass
    public static void init() {
        Main.initApplicationPreferences();
        Main.pref.put("osm-server.url", OsmApi.DEFAULT_API_URL);
    }

    /**
     * Tests node history fetching.
     * @throws OsmTransferException if any error occurs
     */
    @Test
    public void testNode() throws OsmTransferException {
        OsmServerHistoryReader reader = new OsmServerHistoryReader(OsmPrimitiveType.NODE, 266187);
        HistoryDataSet ds = reader.parseHistory(NullProgressMonitor.INSTANCE);
        History h = ds.getHistory(266187, OsmPrimitiveType.NODE);
        assertTrue("NumVersions", h.getNumVersions() >= 4);
    }

    /**
     * Tests way history fetching.
     * @throws OsmTransferException if any error occurs
     */
    @Test
    public void testWay() throws OsmTransferException {
        OsmServerHistoryReader reader = new OsmServerHistoryReader(OsmPrimitiveType.WAY, 3058844);
        HistoryDataSet ds = reader.parseHistory(NullProgressMonitor.INSTANCE);
        History h = ds.getHistory(3058844, OsmPrimitiveType.WAY);
        assertTrue("NumVersions", h.getNumVersions() >= 13);
    }

    /**
     * Tests relation history fetching.
     * @throws OsmTransferException if any error occurs
     */
    @Test
    public void testRelation() throws OsmTransferException {
        OsmServerHistoryReader reader = new OsmServerHistoryReader(OsmPrimitiveType.RELATION, 49);
        HistoryDataSet ds = reader.parseHistory(NullProgressMonitor.INSTANCE);
        History h = ds.getHistory(49, OsmPrimitiveType.RELATION);
        assertTrue("NumVersions", h.getNumVersions() >= 3);
    }
}
