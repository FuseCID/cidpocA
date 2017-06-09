package org.fuse.cidpoc.test;

import java.util.List;

import org.fuse.cidpoc.Item;
import org.fuse.cidpoc.Item.Capability;
import org.fuse.cidpoc.Item.Requirement;
import org.fuse.cidpoc.Utils;
import org.fuse.cidpoc.a.A;
import org.junit.Assert;
import org.junit.Test;

public class ATest {

    @Test
    public void testA() throws Exception {
        
        Item item = new A();
        item.transitiveStatus();

        List<Item> deps = item.getDependencies();
        Assert.assertEquals(0, deps.size());

        Capability cap = item.getCapability();
        Assert.assertEquals("A", cap.getName());
        Assert.assertTrue(cap.getValue() > 0);
        
        List<Requirement> reqs = item.getRequirements();
        Assert.assertEquals(0, reqs.size());

        String version = Utils.getVersion(item.getClass());
        Assert.assertEquals("A-" + version, item.getVName());
        Assert.assertEquals("is satisfied", item.getStatus());
    }
}
