package org.fuse.cidpoc.a;

import java.util.Collections;
import java.util.List;

import org.fuse.cidpoc.Item;
import org.fuse.cidpoc.Utils;

public class A extends Item {

    @Override
    public Capability getCapability() {
        return Utils.getCapability(A.class);
    }

    @Override
    public List<Item> getDependencies() {
        return Collections.emptyList();
    }
}
